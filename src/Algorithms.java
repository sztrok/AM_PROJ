import java.sql.SQLOutput;
import java.util.*;

import EnumPack.*;
import EnumPack.TabuExceed;

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


    public static  Vector<Integer> twoOpt(String basicSolution, int maxTime){

        long start = System.currentTimeMillis();
        Vector<Integer> solution = switch (basicSolution) {
            case "KRand" -> kRandom(1000);
            case "CN" -> closestNeighbour(Utils.rand.nextInt(DataMatrix.dimension));
            case "CNE" -> extendedClosestNeighbour();
            default ->  null;
        };

        //GF = Goal Function

        Vector<Integer> currentSolution = new Vector<>();
        Vector<Integer> tempSolution;

        long currentGFValue = Utils.calculateGoalFunction(solution);
        long smallestGFValue = 0;

        while(smallestGFValue < currentGFValue){
            long end = System.currentTimeMillis();
            if(end-start > maxTime){
                return  currentSolution;
            }
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

    public static Vector<Integer> geneticAlgorithm(int islandsNumb,Integer populationSize,
                                                   GeneratingStartingPopulationMethod generatingStartingPopulationMethod,
                                                   Integer kRandValue, ParentSelectionMethod parentSelectionMethod,
                                                   CrossoverMethod crossoverMethod, MutationMethod mutationMethod,
                                                   double mutationProbability,
                                                   EndCondition end_condition, Integer maxIteration, Integer maxTimeMillis,
                                                   Integer maxIterationsWithoutImprovement,
                                                   int chromosomeSize,
                                                   double crossProbability,int iterationWithoutImprovementLimit,
                                                   int oldUnitsNumberAfterStagnation
                                                   ) {
        double startingProb = mutationProbability;
        long start = System.currentTimeMillis();
        Vector<Integer> bestSolutionGlobally = new Vector<>();
        int bestCostGlobally = Integer.MAX_VALUE;


        Parents.chromosomeSize = chromosomeSize;
        Parents.crossProbability = crossProbability;
        Parents.crossoverMethod = crossoverMethod;
        Random rand = new Random();
        Vector<Vector<Unit>> islands = new Vector<>();
        Vector<Vector<Unit>> relocation = new Vector<>();
        for (int i = 0; i < islandsNumb; i++) {
            islands.add(new Vector<Unit>());
        }

//        Vector<Unit> population = new Vector<>();
        int iterationWithoutImprovement = 0;
        //generating starting population
        for (int k = 0; k < islandsNumb; k++) {
            for (int i = 0; i < populationSize; i++) {
                Unit unit = new Unit();
                for (int j = 0; j < chromosomeSize; j++) {

                    switch (generatingStartingPopulationMethod) {

                        case HEURISTIC_2OPT -> unit.addChromosome(Algorithms.twoOpt("kRand"));
                        case HEURISTIC_CLOSEST_NEIGHBOUR -> unit.addChromosome(closestNeighbour(0));
                        case HEURISTIC_EXTENDED_CLOSEST_NEIGHBOUR -> unit.addChromosome(extendedClosestNeighbour());
                        case HEURISTIC_KRAND -> unit.addChromosome(kRandom(kRandValue));
                    }
                }
                islands.get(k).add(unit);
            }
        }
//        System.out.println(islands.get(0).size());
//        System.out.println(islands.get(1).size());
        int currentIsland = 0;

        for (int z = 0; true; z++) {
            currentIsland %= 2;
//            System.out.println("ISLAND: "+currentIsland);
            for (int i = 0; i < islands.get(currentIsland).size(); i++) {
                for (int j = 0; j < chromosomeSize; j++) {

                    int fitness = (int) Utils.calculateGoalFunction(islands.get(currentIsland).get(i).genotype.get(j));
                    islands.get(currentIsland).get(i).fenotype.set(j, fitness);
                    if (fitness < bestCostGlobally) {
                        iterationWithoutImprovement = 0;
                        bestCostGlobally = fitness;
                        bestSolutionGlobally = islands.get(currentIsland).get(i).genotype.get(j);
                        System.out.println("BEST SOLUTION: " + bestCostGlobally);
                        mutationProbability = startingProb;
                    }
                }
//                if(i==80){
//                    System.out.println("EE "+(int) Utils.calculateGoalFunction(population.get(i).genotype.get(0)));
//                }
            }
            double best = Integer.MAX_VALUE;
            double worst = 0d;
            for (Unit unit : islands.get(currentIsland)) {
                if (unit.getFenotypeSum() > worst) {
                    worst = unit.getFenotypeSum();
                }
                if (unit.getFenotypeSum() < best) {
                    best = unit.getFenotypeSum();
                }
            }
//            System.out.println("BEST: "+best+" | WORST: "+worst);
            if ((worst / best) <= 1.05) {
//                System.out.println("RELOCATING...");

                    mutationProbability += 0.02d;

//                if(mutationProbability>1.5d) return bestSolutionGlobally;
                for (Vector<Unit> island : islands) {
                    Vector<Unit> relocatingPopulation = new Vector<>();
                    for (int i = 0; i < populationSize / 2; i++) {
                        relocatingPopulation.add(island.get(0));
//                        relocation.get(islands.indexOf(island)).add(island.get(0));
                        island.remove(0);
                    }
                    relocation.add(relocatingPopulation);
                }
                for (int i = 0; i < islandsNumb; i++) {
                    if (i == islandsNumb - 1) {
                        for (int j = 0; j < populationSize / 2; j++) {
                            islands.get(0).add(relocation.get(i).get(j));
                        }
                    } else {
                        for (int j = 0; j < populationSize / 2; j++) {
                            islands.get(i + 1).add(relocation.get(i).get(j));
                        }
                    }
                }
            }
            if (end_condition != EndCondition.ITERATION_WITHOUT_IMPROVEMENT) {


                if (iterationWithoutImprovement == iterationWithoutImprovementLimit) {
//                    System.out.println("PURGE");
                    int maxTime = 200;
                    for (; maxTime < 600; maxTime += 200) {
                        int value = (int) Utils.calculateGoalFunction(twoOpt("KRand", maxTime));
//                        System.out.println(bestCostGlobally + " " + value);
//                        System.out.println(bestCostGlobally / 1.5d + " " + bestCostGlobally * 1.1d);
                        if (bestCostGlobally / 1.5d <= value && bestCostGlobally * 1.1d >= value) {
//                            System.out.println("JAZDAAA");
                            for(Vector<Unit> island : islands){
                                for (int i = 0; i < 10; i++) {

                                    island.remove(0);
                                    Unit unit = new Unit();
                                    for (int j = 0; j < chromosomeSize; j++) {
                                        unit.addChromosome(twoOpt("KRand", maxTime));
                                    }
                                    island.add(unit);
                                }
                            }
                            iterationWithoutImprovement=0;
                            break;
                        }
                    }
                }

                //wybieranie populacji rodzicow
                Vector<Parents> parents = new Vector<>();


                switch (parentSelectionMethod) {
                    case RANDOM -> {
                        ArrayList<Integer> list = new ArrayList<>();
                        for (int i = 0; i < populationSize; i++) {
                            list.add(i);
                        }
                        Collections.shuffle(list);
//                    System.out.println("LIST: "+list.size());
                        for (int i = 0; i < populationSize - 1; i += 2) {

                            Unit parent1 = islands.get(currentIsland).get(list.get(i));
                            Unit parent2 = islands.get(currentIsland).get(list.get(i + 1));

                            parents.add(new Parents(parent1, parent2));

                        }
//                    System.out.println("NEW PARENTS");
                    }
                    case ROULETTE -> {

                        double sumOfFitness = 0.0d;
                        for (int i = 0; i < islands.get(currentIsland).size(); i++) {
                            sumOfFitness += 1 / (double) islands.get(currentIsland).get(i).getFenotypeSum();
                        }
                        double[] probabilities = new double[islands.get(currentIsland).size()];
                        double[] singleProb = new double[islands.get(currentIsland).size()];
                        double sumOfProbabilities = 0.0d;
                        probabilities[0] = 0.0d;
                        for (int i = 0; i < islands.get(currentIsland).size() - 1; i++) {
                            singleProb[i] = ((double) 1 / islands.get(currentIsland).get(i).getFenotypeSum()) / sumOfFitness;
                            double probability = sumOfProbabilities + ((double) 1 / islands.get(currentIsland).get(i).getFenotypeSum()) / sumOfFitness;
                            singleProb[i] = ((double) 1 / islands.get(currentIsland).get(i).getFenotypeSum()) / sumOfFitness;
                            sumOfProbabilities += ((double) 1 / islands.get(currentIsland).get(i).getFenotypeSum()) / sumOfFitness;
                            probabilities[i + 1] = probability;
                        }

                        for (int i = 0; i < populationSize / 2; i++) {

                            double r = rand.nextDouble();

                            int index1 = 0;
                            for (int j = 0; j < populationSize; j++) {
                                index1 = j;
                                if (probabilities[j] > r) {
                                    index1 = index1 - 1;

                                    break;
                                }
                            }
                            int index2 = -1;
                            do {
                                r = rand.nextDouble();
//                            System.out.println("R2 " + r);
                                for (int j = 0; j < populationSize; j++) {
                                    index2 = j;

                                    if (probabilities[j] > r) {
                                        index2 = index2 - 1;

                                        break;
                                    }
                                }

                            } while (islands.get(currentIsland).get(index2).equals(islands.get(currentIsland).get(index1)));

                            Unit parent1 = islands.get(currentIsland).get(index1);
                            Unit parent2 = islands.get(currentIsland).get(index2);

                            parents.add(new Parents(parent1, parent2));
                        }
//                    System.out.println("NEW PARENTS");
                    }
                }

                islands.get(currentIsland).clear();
//            population.removeAllElements();

                for (Parents parentsPair : parents) {
//                System.out.println("CROSS");
                    islands.get(currentIsland).addAll(parentsPair.cross());
                }

//            System.out.println("NEW POLULATION SIZE: "+population.size());


                switch (mutationMethod) {
                    case SWAP -> {
                        for (Unit unit : islands.get(currentIsland)) {
                            if (mutationProbability >= rand.nextDouble()) {
//                            System.out.println("MUTATION SWAP");
                                for (int k = 0; k < chromosomeSize; k++) {
                                    int i = rand.nextInt(unit.genotype.get(k).size());
                                    int j = rand.nextInt(unit.genotype.get(k).size());
                                    Vector<Integer> newGenotype = Utils.swap(unit.genotype.get(k), i, j);

                                    int fitness = (int) Utils.calculateGoalFunction(newGenotype);
                                    unit.fenotype.set(k, fitness);
                                }
                            }
                        }
                    }
                    case INVERT -> {
                        for (Unit unit : islands.get(currentIsland)) {
                            if (mutationProbability >= rand.nextDouble()) {
//                            System.out.println("MUTATION INVERT");
                                for (int index = 0; index < chromosomeSize; index++) {
                                    int i = rand.nextInt(unit.genotype.get(index).size() - 1);
                                    int j = i;

                                    while (j <= i) {
                                        j = rand.nextInt(unit.genotype.get(index).size());
                                    }
                                    Vector<Integer> newGenotype = Utils.invert(unit.genotype.get(index), i, j);
                                    int fitness = (int) Utils.calculateGoalFunction(newGenotype);
                                    unit.fenotype.set(index, fitness);
                                }
                            }
                        }
                    }
                }
//            System.out.println("ITERATION: "+maxIteration);

                currentIsland += 1;
                switch (end_condition) {
                    case ITERATION_NUMBER_EXCEEDED -> {

                        maxIteration--;
                        if (maxIteration < 0) {
                            System.out.println("ITER");
                            return bestSolutionGlobally;
                        }

                    }
                    case MAX_TIME_EXCEEDED -> {

                        long end = System.currentTimeMillis();
                        if (end - start > maxTimeMillis) {
                            System.out.println("TIME");
                            return bestSolutionGlobally;
                        }

                    }
                    case ITERATION_WITHOUT_IMPROVEMENT -> {

                        if (maxIterationsWithoutImprovement - iterationWithoutImprovement == 0) {
                            System.out.println("IMPROVE");
                            return bestSolutionGlobally;
                        }
                    }
                    default -> {
                    }
                    // code block
                }
                iterationWithoutImprovement++;
            }

//        return new Vector<>();
        }
    }

    public static Vector<Integer> tabuSearch(EndCondition end_condition, TabuExceed tabuExceed, String basicSolution, Integer maxIteration, Integer environmentSize,
                                             Integer environmentSizeIncrease, String mode, Integer maxTabuListSize, Double worseDeviationPercent,
                                             Integer maxIterationsWithoutImprovement, Integer maxTimeMillis, boolean checkWorseOption, Vector<Integer> startingSolution){


        int c = 6942;
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
        System.out.println( "starting: " +bestSolutionCost + " " + Utils.calculatePRD(c, bestSolutionCost) );

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
                System.out.println( i + " " +bestSolutionCost + " " + Utils.calculatePRD(c, bestSolutionCost));
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
