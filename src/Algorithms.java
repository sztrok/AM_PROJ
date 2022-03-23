import java.util.Collections;
import java.util.Vector;

public class Algorithms {



    public static Vector<Vector<Integer>> kRandom(int k){
        Vector<Vector<Integer>> matrix = LoadDataTSP.matrix;
        int dimension = LoadDataTSP.dimension;
        String format = LoadDataTSP.format;
        String type = LoadDataTSP.type;

        Vector<Vector<Integer>> permutations = new Vector<>();

        for(int i=0; i<k; i++){
            Vector<Integer> dimVector = new Vector<>();
            for(int j=0; j<dimension; j++){
                dimVector.add(j);
            }
            Collections.shuffle(dimVector);
            permutations.add(dimVector);
//            System.out.println(dimVector);
//            System.out.println(permutations);
        }

        return permutations;

//        Utils.calculateGoalFunction(permutations);
//        System.out.println(permutations.get(0).indexOf(5));
//        System.out.println(Collections.max(permutations));

    }



    public static Vector<Integer> closestNeighbour(int startNode){

        Vector<Integer> visited = new Vector<>();

        int currentNode = startNode;
        int neighbour = currentNode;

        visited.add(currentNode);

        while(visited.size() != LoadDataTSP.dimension) {

            int min = Integer.MAX_VALUE;

            for(int i : LoadDataTSP.matrix.get(currentNode)) {
                int possibleNeighbour = LoadDataTSP.matrix.get(currentNode).indexOf(i);


                if (possibleNeighbour != currentNode && i < min &&
                        !visited.contains(possibleNeighbour)) {
                    min = i;
                    neighbour = possibleNeighbour;
                }
            }

            for (int i = currentNode + 1; i < LoadDataTSP.dimension; i++) {

                if (min > LoadDataTSP.matrix.get(i).get(currentNode) &&
                        !visited.contains(i)) {

                    min = LoadDataTSP.matrix.get(i).get(currentNode);
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

        for(int i =0 ; i < LoadDataTSP.dimension; i++){

            Vector<Integer> possibleResult = closestNeighbour(i);
            long goalFunctionValue = Utils.calculateGoalFunction(possibleResult);

            if( goalFunctionValue < smallestGoalFunctionValue){
                result = possibleResult;
                smallestGoalFunctionValue = goalFunctionValue;
            }
        }
        return result;
    }

    public static  Vector<Integer> twoOpt(){

        //GF = Goal Function
        Vector<Integer> solution = extendedClosestNeighbour();
        Vector<Integer> currentSolution;
        Vector<Integer> tempSolution;

        long currentGFValue = Utils.calculateGoalFunction(solution);
        long smallestGFValue = Long.MAX_VALUE;

        while(currentGFValue < smallestGFValue){

            currentGFValue = Utils.calculateGoalFunction(solution);
            currentSolution = solution;

            for(int i =0; i < LoadDataTSP.dimension; i++){
                for(int j = i + 1; j < LoadDataTSP.dimension; j++){
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