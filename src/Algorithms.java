import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class Algorithms {


    public static Vector<Integer> kRandom(int k){
        Vector<Vector<Integer>> matrix = DataMatrix.matrix;
        int dimension = DataMatrix.dimension;
        String format = DataMatrix.format;
        String type = DataMatrix.type;

        Vector<Integer> result = new Vector<>();
        for(int j=0; j<dimension; j++){
            result.add(j);
        }
        Collections.shuffle(result);


        for(int i=0; i<k; i++){

            Vector<Integer> dimVector = new Vector<>();
            for(int j=0; j<dimension; j++){
                dimVector.add(j);
            }
            Collections.shuffle(dimVector);

            if(Utils.calculateGoalFunction(dimVector) < Utils.calculateGoalFunction(result)) {

                result = dimVector;
            }
//            System.out.println(dimVector);
//            System.out.println(permutations);
        }
        return result;
//        Utils.calculateGoalFunction(permutations);
//        System.out.println(permutations.get(0).indexOf(5));
//        System.out.println(Collections.max(permutations));

    }


    public static Vector<Integer> closestNeighbour(int startNode){

        Vector<Integer> visited = new Vector<>();

        int currentNode = startNode;
        int neighbour = currentNode;

        visited.add(currentNode);

        while(visited.size() != DataMatrix.dimension) {

            int min = Integer.MAX_VALUE;

            for(int i : DataMatrix.matrix.get(currentNode)) {
                int possibleNeighbour = DataMatrix.matrix.get(currentNode).indexOf(i);


                if (possibleNeighbour != currentNode && i < min &&
                        !visited.contains(possibleNeighbour)) {
                    min = i;
                    neighbour = possibleNeighbour;
                }
            }

            for (int i = currentNode + 1; i < DataMatrix.dimension; i++) {

                if (min > DataMatrix.matrix.get(i).get(currentNode) &&
                        !visited.contains(i)) {

                    min = DataMatrix.matrix.get(i).get(currentNode);
                    neighbour = i;
                }
            }
            currentNode = neighbour;
            visited.add(neighbour);
        }
        return  visited;
    }

    public static Vector<Integer> extendedClosestNeighbour() {

        Vector<Integer> result = new Vector<>();
        long smallestGoalFunctionValue = Long.MAX_VALUE ;
        for(int i =0 ; i < DataMatrix.dimension; i++){

            Vector<Integer> possibleResult = closestNeighbour(i);
            long goalFunctionValue = Utils.calculateGoalFunction(possibleResult);

            if( goalFunctionValue < smallestGoalFunctionValue){
                result = possibleResult;
                smallestGoalFunctionValue = goalFunctionValue;
            }
        }

        return result;

    }


    public static  Vector<Integer> twoOpt(String basicSolution){


        Vector<Integer> solution = switch (basicSolution) {
            case "KRand" -> kRandom(1000);
            case "CN" -> closestNeighbour(Utils.rand.nextInt(DataMatrix.dimension));
            case "CNE" -> extendedClosestNeighbour();
            default ->  null;
        };

        //GF = Goal Function

        Vector<Integer> currentSolution;
        Vector<Integer> tempSolution;

        long currentGFValue = Utils.calculateGoalFunction(solution);
        long smallestGFValue = 0;

        while(smallestGFValue < currentGFValue){

            currentGFValue = Utils.calculateGoalFunction(solution);
            currentSolution = solution;
            smallestGFValue = currentGFValue;

            for(int i =0; i < DataMatrix.dimension; i++){
                for(int j = i + 1; j < DataMatrix.dimension; j++){
                    for(int k =0; k < (j-i)/2 + 1 ; k++){

                        tempSolution = new Vector<>(currentSolution);

                        int temp = tempSolution.get(i + k);
                        tempSolution.set(i + k, tempSolution.get(j - k));
                        tempSolution.set(j - k, temp);

                        long possibleGFValue = Utils.calculateGoalFunction(tempSolution);


                        if(possibleGFValue < smallestGFValue){
                            smallestGFValue = possibleGFValue;
                            solution = tempSolution;
                        }
                    }
                }
            }
        }
        return solution;
    }



    public static Vector<Integer> tabuSearch(EndCondition end_condition, TabuExceed tabuExceed, String basicSolution, Integer maxIteration, Integer environmentSize,
                                             Integer environmentSizeIncrease, String mode, Integer maxTabuListSize, Double worseDeviationPercent,
                                             Integer maxIterationsWithoutImprovement, Integer maxTimeMillis, boolean checkWorseOption, Vector<Integer> startingSolution){

        Vector<Integer> solution;
        int iterationWithoutImprovement =0;
        long start = System.currentTimeMillis();

        double deviation = 0.0d;

        if (basicSolution != null) {
           solution = twoOpt(basicSolution);
        }else {
           solution = startingSolution;
        }

            int solutionCost = (int) Utils.calculateGoalFunction(solution);

            Vector<Integer> bestSolution = solution;
            int bestSolutionCost = solutionCost;


        ArrayList<Integer> tabuList = new ArrayList<>();

        Vector<Vector<Integer>> environment = new Vector<>();


        for(int i =0; true; i++){
            int bestCostLocally = Integer.MAX_VALUE;
            Vector<Integer> bestCandidate = new Vector<>();

            bestCandidate.add(Integer.MAX_VALUE);
            bestCandidate.add(Integer.MAX_VALUE);

            int differenceOfBestCandidate = 0;
            environment = Utils.generateEnvironment(environmentSize);
            for(int j = 0; j < environment.size(); j++){

                int firstElement = environment.get(j).get(0);
                int secondElement = environment.get(j).get(1);

                int difference = secondElement - firstElement;

                if(!tabuList.contains(difference)){


                    int currentCost = Integer.MAX_VALUE;
                    if(mode.equals("invert")){
                         currentCost  = (int) Utils.calculateGoalFunction(Utils.invert(solution, firstElement, secondElement));
                    } else if(mode.equals("swap")){
                        currentCost  = (int) Utils.calculateGoalFunction(Utils.swap(solution, firstElement, secondElement));
                    }

                    if(currentCost < bestCostLocally){
                        bestCostLocally = currentCost;
                        differenceOfBestCandidate = difference;
                        bestCandidate.set(0, firstElement);
                        bestCandidate.set(1, secondElement);
                    }
                }
            }

            if(bestCostLocally <= solutionCost + deviation * solutionCost){
                solutionCost = bestCostLocally;

                if(mode.equals("invert")){

                    solution = Utils.invert(solution, bestCandidate.get(0), bestCandidate.get(1) );
                } else {
                    solution = Utils.swap(solution, bestCandidate.get(0), bestCandidate.get(1) );
                }

            }

            tabuList.add(differenceOfBestCandidate);
            if(tabuList.size() >= maxTabuListSize){


                switch(tabuExceed) {
                    case RETURN: {
                        return bestSolution;
                    }
                    case REMOVE_FIRST_ELEMENT:{

                        tabuList.remove(0);

                    }
                    break;

                    case RESTART:{

                        return tabuSearch(end_condition, tabuExceed,
                                basicSolution, maxIteration, environmentSize,
                                environmentSizeIncrease, mode, maxTabuListSize, worseDeviationPercent,
                                maxIterationsWithoutImprovement, maxTimeMillis, checkWorseOption, twoOpt(basicSolution));
                    }


                    case USE_WIDER_ENVIRONMENT:{

                        environmentSize += environmentSizeIncrease;
                    }
                    break;

                }
            }
            if(solutionCost < bestSolutionCost){
                bestSolutionCost = solutionCost;
                bestSolution = solution;
                iterationWithoutImprovement =0;
                deviation =0.0d;
            } else {
                iterationWithoutImprovement ++;
            }

            if(checkWorseOption && iterationWithoutImprovement > maxIterationsWithoutImprovement){
                deviation = worseDeviationPercent;
                iterationWithoutImprovement =0;
            }


            switch (end_condition) {
                case ITERATION_NUMBER_EXCEEDED -> {
                    maxIteration--;
                    if (maxIteration < 0)
                        return bestSolution;
                }
                case MAX_TIME_EXCEEDED -> {

                    long end = System.currentTimeMillis();
                    if (end - start > maxTimeMillis)
                        return bestSolution;

                }
                case ITERATION_WITHOUT_IMPROVEMENT -> {

                    if (maxIterationsWithoutImprovement - iterationWithoutImprovement == 0)
                        return bestSolution;
                }
                default -> {
                }
                // code block
            }

        }


        //if element z envoirment nie jest w tabu
        //

    }

//    public static void closestNeighbour(int startNode){
//        Vector<Vector<Integer>> matrix = LoadDataTSP.matrix;
//        int dimension = LoadDataTSP.dimension;
//        String format = LoadDataTSP.format;
//        String type = LoadDataTSP.type;
//
//        Vector<Integer> visited = new Vector<>();
//        Vector<Vector<Integer>> values = new Vector<>();
//        while(visited.size()!=LoadDataTSP.dimension){
//            for(int i=0; i<startNode; i++){
//                if(!visited.contains(i)){
//                    Vector<Integer> vec = new Vector<>();
//                    vec.add(i);
//                    vec.add(matrix.get(startNode).get(i));
//                    values.add(vec);
//                    visited.add(i);
//                }
//            }
//
//            for(int i=startNode+1; i<dimension; i++){
//                if(!visited.contains(i)){
//
//                }
//            }
//
//        }
//    }
}
