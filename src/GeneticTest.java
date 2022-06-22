import EnumPack.*;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Vector;

public class GeneticTest {




    public static void PRD_iterationNumber() throws IOException {
        Vector<String> filenames = new Vector<>();
        Vector<Integer> bestSolution = new Vector<>();

        filenames.add("gr21.tsp");
        filenames.add("bays29.tsp");
        filenames.add("dantzig42.tsp");
        filenames.add("berlin52.tsp");
        filenames.add("ftv64.atsp");
        filenames.add("ftv70.atsp");
        filenames.add("gr48.tsp");

        bestSolution.add(2707);
        bestSolution.add(2020);
        bestSolution.add(699);
        bestSolution.add(7542);
        bestSolution.add(1839);
        bestSolution.add(1950);
        bestSolution.add(5046);

        Vector<Integer> prdResults = new Vector<>();

        for(int i =0; i <  bestSolution.size(); i++){
            prdResults.add(0);

        }
        Vector<Integer> maxIterations = new Vector<>();

        for(int iterationNumber = 100; iterationNumber < 1000; iterationNumber +=200){

            maxIterations.add(iterationNumber);
        }


        for(int filenameIndex = 0; filenameIndex < filenames.size();filenameIndex++){
            LoadDataTSP.resetData();
            LoadDataTSP.loadData(filenames.get(filenameIndex));
            DataMatrix.matrix = LoadDataTSP.matrix;
            DataMatrix.dimension = LoadDataTSP.dimension;
            DataMatrix.format = LoadDataTSP.format;
            DataMatrix.type = LoadDataTSP.type;

            for(int i =0 ; i <maxIterations.size(); i++) {
                System.out.println(filenames.get(filenameIndex) + " " + maxIterations.get(i));
                int prd = (int) Utils.calculatePRD(bestSolution.get(filenameIndex), Algorithms.geneticAlgorithm(5, 200,
                        GeneratingStartingPopulationMethod.HEURISTIC_KRAND, 1,
                        ParentSelectionMethod.RANDOM, CrossoverMethod.OrderCrossover, MutationMethod.SWAP,
                        0.1d, EndCondition.ITERATION_NUMBER_EXCEEDED, maxIterations.get(i),
                        Integer.MAX_VALUE, Integer.MAX_VALUE, 6, 0.7d, 2000, 100));

                System.out.println("PRD " + prd);
                System.out.println("best solution " + bestSolution.get(filenameIndex));
                prdResults.set(i, prdResults.get(i) + prd);

            }
        }
        System.out.println("PRD -ITERATION NUMBER " + prdResults);
        for (int i = 0; i < prdResults.size(); i++) {
            prdResults.set(i, prdResults.get(i)/filenames.size());
        }
        System.out.println("PRD -ITERATION NUMBER " + prdResults);

        FileWriter fileWriter= new FileWriter("MaxIterationNumber_Genetic.txt");
        for (int i = 0; i < filenames.size(); i++) {

            String data ="";
            data += maxIterations.get(i) + ";" + prdResults.get(i);
            fileWriter.write(data);
            fileWriter.write("\n");
        }
        fileWriter.close();

    }

    public static void PRD_populationSize() throws IOException {
        Vector<String> filenames = new Vector<>();
        Vector<Integer> bestSolution = new Vector<>();

        filenames.add("gr21.tsp");
        filenames.add("bays29.tsp");
        filenames.add("dantzig42.tsp");
        filenames.add("berlin52.tsp");
        filenames.add("ftv64.atsp");
        filenames.add("ftv70.atsp");
        filenames.add("gr48.tsp");

        bestSolution.add(2707);
        bestSolution.add(2020);
        bestSolution.add(699);
        bestSolution.add(7542);
        bestSolution.add(1839);
        bestSolution.add(1950);
        bestSolution.add(5046);

        Vector<Integer> prdResults = new Vector<>();

        for(int i =0; i <  bestSolution.size(); i++){
            prdResults.add(0);

        }
        Vector<Integer> populationSizes = new Vector<>();


        for(int populationSize = 20; populationSize <= 120; populationSize += 20){

            populationSizes.add(populationSize);
        }


        for(int filenameIndex = 0; filenameIndex < filenames.size();filenameIndex++){
            LoadDataTSP.resetData();
            LoadDataTSP.loadData(filenames.get(filenameIndex));
            DataMatrix.matrix = LoadDataTSP.matrix;
            DataMatrix.dimension = LoadDataTSP.dimension;
            DataMatrix.format = LoadDataTSP.format;
            DataMatrix.type = LoadDataTSP.type;

            for(int i =0 ; i <populationSizes.size(); i++) {
                System.out.println(filenames.get(filenameIndex) + " " + populationSizes.get(i));
                int prd = (int) Utils.calculatePRD(bestSolution.get(filenameIndex), Algorithms.geneticAlgorithm(2, populationSizes.get(i),
                        GeneratingStartingPopulationMethod.HEURISTIC_KRAND, 1,
                        ParentSelectionMethod.RANDOM, CrossoverMethod.OrderCrossover, MutationMethod.SWAP,
                        0.1d, EndCondition.ITERATION_NUMBER_EXCEEDED, populationSizes.get(i),
                        Integer.MAX_VALUE, Integer.MAX_VALUE, 6, 0.7d, 2000, 100));

                System.out.println("PRD " + prd);
                System.out.println("best solution " + bestSolution.get(filenameIndex));
                prdResults.set(i, prdResults.get(i) + prd);

            }
        }
        System.out.println("PRD -POPULATION SIZE " + prdResults);
        for (int i = 0; i < prdResults.size(); i++) {
            prdResults.set(i, prdResults.get(i)/filenames.size());
        }

        for(int i =0 ;i < prdResults.size();i ++){
            System.out.println(populationSizes.get(i) + " " + prdResults.get(i));
        }

    }

    public static void GoalFunction_PopulationSize(){

        Vector<Integer> problemSizes = new Vector<>();
        problemSizes.add(30);
        problemSizes.add(60);
        problemSizes.add(90);
        problemSizes.add(120);

        Vector<Vector<Integer>> city30 = RandomInstationGenerator.getSymmetric(30);
        Vector<Vector<Integer>> city60 = RandomInstationGenerator.getSymmetric(60);
        Vector<Vector<Integer>> city90 = RandomInstationGenerator.getSymmetric(90);
        Vector<Vector<Integer>> city120 = RandomInstationGenerator.getSymmetric(120);
        Vector<Vector<Vector<Integer>>> cities = new Vector<>();
        cities.add(city30);
        cities.add(city60);
        cities.add(city90);
        cities.add(city120);

        Vector<Integer> populationSizes = new Vector<>();

        for(int populationSize = 20; populationSize <= 120; populationSize += 20){

            populationSizes.add(populationSize);
        }

        for(Vector<Vector<Integer>> city: cities){

            DataMatrix.matrix = city;
            DataMatrix.dimension = city.size();
            DataMatrix.format = "LOWER_DIAG_ROW";
            DataMatrix.type = "EXPLICIT";

            System.out.println("Size " + city.size());
            for (int i = 0; i < populationSizes.size(); i++) {

                int goalFunction = (int) Utils.calculateGoalFunction( Algorithms.geneticAlgorithm(2, populationSizes.get(i), GeneratingStartingPopulationMethod.HEURISTIC_KRAND, 1,
                        ParentSelectionMethod.RANDOM, CrossoverMethod.OrderCrossover, MutationMethod.SWAP,
                        0.1d, EndCondition.ITERATION_NUMBER_EXCEEDED,populationSizes.get(i) *2,
                        Integer.MAX_VALUE, Integer.MAX_VALUE, 6, 0.7d, 2000, 100));
                System.out.println(populationSizes.get(i) + " " + goalFunction);
            }
        }

    }

    public static void PRD_islands(){

        Vector<String> filenames = new Vector<>();
        Vector<Integer> bestSolution = new Vector<>();

        filenames.add("dantzig42.tsp");
        filenames.add("berlin52.tsp");
        filenames.add("gr48.tsp");
        filenames.add("gr120.tsp");

        bestSolution.add(699);
        bestSolution.add(7542);
        bestSolution.add(5046);
        bestSolution.add(6942);


        Vector<Integer> prdResults = new Vector<>();


        Vector<Integer> islands = new Vector<>();



        for(int islandcount = 2; islandcount < 6; islandcount++ ){

            islands.add(islandcount);
        }


        for(int i =0; i <  islands.size(); i++){
            prdResults.add(0);

        }
        int totalPopulation = 800;

        for(int filenameIndex = 0; filenameIndex < filenames.size();filenameIndex++){
            LoadDataTSP.resetData();
            LoadDataTSP.loadData(filenames.get(filenameIndex));
            DataMatrix.matrix = LoadDataTSP.matrix;
            DataMatrix.dimension = LoadDataTSP.dimension;
            DataMatrix.format = LoadDataTSP.format;
            DataMatrix.type = LoadDataTSP.type;

            for(int i =0 ; i <islands.size(); i++) {
                System.out.println(filenames.get(filenameIndex) + " " + islands.get(i));
                int prd = (int) Utils.calculatePRD(bestSolution.get(filenameIndex), Algorithms.geneticAlgorithm(islands.get(i), totalPopulation/ islands.get(i),
                        GeneratingStartingPopulationMethod.HEURISTIC_KRAND, 1,
                        ParentSelectionMethod.RANDOM, CrossoverMethod.OrderCrossover, MutationMethod.SWAP,
                        0.1d, EndCondition.ITERATION_NUMBER_EXCEEDED, 1000,
                        Integer.MAX_VALUE, Integer.MAX_VALUE, 6, 0.7d, 2000, 100));

                System.out.println("PRD " + prd);
                System.out.println("best solution " + bestSolution.get(filenameIndex));
                prdResults.set(i, prdResults.get(i) + prd);

            }
        }
        System.out.println("PRD -ISLAND SIZE " + prdResults);
        for (int i = 0; i < prdResults.size(); i++) {
            prdResults.set(i, prdResults.get(i)/filenames.size());
        }
        System.out.println("PRD -ISLAND SIZE " + prdResults);

    }

    public static void parentSelectionMethod(){
        Vector<String> filenames = new Vector<>();
        Vector<Integer> bestSolution = new Vector<>();





        filenames.add("gr21.tsp");
        filenames.add("bays29.tsp");
        filenames.add("dantzig42.tsp");
        filenames.add("berlin52.tsp");
        filenames.add("gr48.tsp");
        filenames.add("gr120.tsp");

        bestSolution.add(2707);
        bestSolution.add(2020);
        bestSolution.add(699);
        bestSolution.add(7542);
        bestSolution.add(5046);
        bestSolution.add(6942);

        Vector<Integer> prdResults = new Vector<>();


        Vector<ParentSelectionMethod> parentSelectionMethod = new Vector<>();

        parentSelectionMethod.add(ParentSelectionMethod.ROULETTE);
        parentSelectionMethod.add(ParentSelectionMethod.RANDOM);


        for(int i =0; i <  parentSelectionMethod.size(); i++){
            prdResults.add(0);

        }


        for(int filenameIndex = 0; filenameIndex < filenames.size();filenameIndex++){
            LoadDataTSP.resetData();
            LoadDataTSP.loadData(filenames.get(filenameIndex));
            DataMatrix.matrix = LoadDataTSP.matrix;
            DataMatrix.dimension = LoadDataTSP.dimension;
            DataMatrix.format = LoadDataTSP.format;
            DataMatrix.type = LoadDataTSP.type;

            for(int i =0 ; i <parentSelectionMethod.size(); i++) {
                System.out.println(filenames.get(filenameIndex) + " " + parentSelectionMethod.get(i));
                int prd = (int) Utils.calculatePRD(bestSolution.get(filenameIndex), Algorithms.geneticAlgorithm(5, 300,
                        GeneratingStartingPopulationMethod.HEURISTIC_KRAND, 1,
                       parentSelectionMethod.get(i), CrossoverMethod.OrderCrossover, MutationMethod.SWAP,
                        0.1d, EndCondition.ITERATION_NUMBER_EXCEEDED, 800,
                        Integer.MAX_VALUE, Integer.MAX_VALUE, 6, 0.7d, 2000, 100));

                System.out.println("PRD " + prd);
                System.out.println("best solution " + bestSolution.get(filenameIndex));
                prdResults.set(i, prdResults.get(i) + prd);

            }
        }
        System.out.println("PRD - PARENT SELECTION METHOD " + prdResults);
        for (int i = 0; i < prdResults.size(); i++) {
            prdResults.set(i, prdResults.get(i)/filenames.size());
        }
        System.out.println("PRD -PARENT SELECTION METHOD ");
        for (int i = 0; i < prdResults.size() ; i++) {
            System.out.println(prdResults.get(i) + " " + parentSelectionMethod.get(i));
        }

    }



    public static void prd_crossoverMethod(){
        Vector<String> filenames = new Vector<>();
        Vector<Integer> bestSolution = new Vector<>();





        filenames.add("gr21.tsp");
        filenames.add("bays29.tsp");
        filenames.add("dantzig42.tsp");
        filenames.add("gr48.tsp");
        filenames.add("berlin52.tsp");
        filenames.add("gr120.tsp");

        bestSolution.add(2707);
        bestSolution.add(2020);
        bestSolution.add(699);
        bestSolution.add(5046);
        bestSolution.add(7542);
        bestSolution.add(6942);

        Vector<Integer> prdResults = new Vector<>();


        Vector<CrossoverMethod> crossoverMethods = new Vector<>();

        crossoverMethods.add(CrossoverMethod.PartiallyMappedCrossover);
        crossoverMethods.add(CrossoverMethod.OrderCrossover);

        Vector<Vector<Integer>> prdVector = new Vector<>();
        for(int i =0; i <  crossoverMethods.size(); i++){
            prdVector.add(new Vector<>());

        }



        for(int filenameIndex = 0; filenameIndex < filenames.size();filenameIndex++){
            LoadDataTSP.resetData();
            LoadDataTSP.loadData(filenames.get(filenameIndex));
            DataMatrix.matrix = LoadDataTSP.matrix;
            DataMatrix.dimension = LoadDataTSP.dimension;
            DataMatrix.format = LoadDataTSP.format;
            DataMatrix.type = LoadDataTSP.type;

            for(int i =0 ; i < crossoverMethods.size(); i++) {
                System.out.println(filenames.get(filenameIndex) + " " + crossoverMethods.get(i));
                int prd = (int) Utils.calculatePRD(bestSolution.get(filenameIndex), Algorithms.geneticAlgorithm(5, 300,
                        GeneratingStartingPopulationMethod.HEURISTIC_KRAND, 1,
                        ParentSelectionMethod.RANDOM,crossoverMethods.get(i), MutationMethod.SWAP,
                        0.1d, EndCondition.ITERATION_NUMBER_EXCEEDED, 1000,
                        Integer.MAX_VALUE, Integer.MAX_VALUE, 6, 0.7d, 2000, 100));

                System.out.println("PRD " + prd);
                System.out.println("best solution " + bestSolution.get(filenameIndex));
                prdVector.get(i).add(prd);

            }
        }
        for(int i =0; i < crossoverMethods.size(); i++){
            System.out.println(crossoverMethods.get(i) + " " + prdVector.get(i));
        }
    }



    public static void prd_mutationMethod(){
        Vector<String> filenames = new Vector<>();
        Vector<Integer> bestSolution = new Vector<>();





        filenames.add("gr21.tsp");
        filenames.add("bays29.tsp");
        filenames.add("dantzig42.tsp");
        filenames.add("gr48.tsp");
        filenames.add("berlin52.tsp");
        filenames.add("gr120.tsp");

        bestSolution.add(2707);
        bestSolution.add(2020);
        bestSolution.add(699);
        bestSolution.add(5046);
        bestSolution.add(7542);
        bestSolution.add(6942);

        Vector<Integer> prdResults = new Vector<>();


        Vector<MutationMethod> mutationMethods= new Vector<>();

        mutationMethods.add(MutationMethod.SWAP);
        mutationMethods.add(MutationMethod.INVERT);

        Vector<Vector<Integer>> prdVector = new Vector<>();
        for(int i =0; i <  mutationMethods.size(); i++){
            prdVector.add(new Vector<>());

        }



        for(int filenameIndex = 0; filenameIndex < filenames.size();filenameIndex++){
            LoadDataTSP.resetData();
            LoadDataTSP.loadData(filenames.get(filenameIndex));
            DataMatrix.matrix = LoadDataTSP.matrix;
            DataMatrix.dimension = LoadDataTSP.dimension;
            DataMatrix.format = LoadDataTSP.format;
            DataMatrix.type = LoadDataTSP.type;

            for(int i =0 ; i < mutationMethods.size(); i++) {
                System.out.println(filenames.get(filenameIndex) + " " + mutationMethods.get(i));
                int prd = (int) Utils.calculatePRD(bestSolution.get(filenameIndex), Algorithms.geneticAlgorithm(5, 300,
                        GeneratingStartingPopulationMethod.HEURISTIC_KRAND, 1,
                        ParentSelectionMethod.RANDOM,CrossoverMethod.OrderCrossover, mutationMethods.get(i),
                        0.1d, EndCondition.ITERATION_NUMBER_EXCEEDED, 1000,
                        Integer.MAX_VALUE, Integer.MAX_VALUE, 6, 0.7d, 2000, 100));

                System.out.println("PRD " + prd);
                System.out.println("best solution " + bestSolution.get(filenameIndex));
                prdVector.get(i).add(prd);

            }
        }
        for(int i =0; i < mutationMethods.size(); i++){
            System.out.println(mutationMethods.get(i) + " " + prdVector.get(i));
        }
    }
    //todo
    //run this
    public static void prd_MutationProbability(){

        Vector<String> filenames = new Vector<>();
        Vector<Integer> bestSolution = new Vector<>();

        filenames.add("gr21.tsp");
        filenames.add("bays29.tsp");
        filenames.add("dantzig42.tsp");
        filenames.add("gr48.tsp");
        filenames.add("berlin52.tsp");
        filenames.add("gr120.tsp");

        bestSolution.add(2707);
        bestSolution.add(2020);
        bestSolution.add(699);
        bestSolution.add(5046);
        bestSolution.add(7542);
        bestSolution.add(6942);


        Vector<Integer> prdResults = new Vector<>();


        Vector<Double> mutationProbability = new Vector<>();

        for(double mutationProb = 0.0d; mutationProb < 0.8d; mutationProb += 0.1d ){

            mutationProbability.add(mutationProb);
        }


        for(int i =0; i <  mutationProbability.size(); i++){
            prdResults.add(0);

        }
        int totalPopulation = 800;

        for(int filenameIndex = 0; filenameIndex < filenames.size();filenameIndex++){
            LoadDataTSP.resetData();
            LoadDataTSP.loadData(filenames.get(filenameIndex));
            DataMatrix.matrix = LoadDataTSP.matrix;
            DataMatrix.dimension = LoadDataTSP.dimension;
            DataMatrix.format = LoadDataTSP.format;
            DataMatrix.type = LoadDataTSP.type;

            for(int i =0 ; i <mutationProbability.size(); i++) {
                System.out.println(filenames.get(filenameIndex) + " " + mutationProbability.get(i));
                int prd = (int) Utils.calculatePRD(bestSolution.get(filenameIndex), Algorithms.geneticAlgorithm(3, 300,
                        GeneratingStartingPopulationMethod.HEURISTIC_KRAND, 1,
                        ParentSelectionMethod.RANDOM, CrossoverMethod.OrderCrossover, MutationMethod.SWAP,
                        mutationProbability.get(i), EndCondition.ITERATION_NUMBER_EXCEEDED, 1000,
                        Integer.MAX_VALUE, Integer.MAX_VALUE, 6, 0.7d, 2000, 100));

                System.out.println("PRD " + prd);
                System.out.println("best solution " + bestSolution.get(filenameIndex));
                prdResults.set(i, prdResults.get(i) + prd);

            }
        }

        System.out.println("PRD -MUTATION PROB " + prdResults);

        for (int i = 0; i < prdResults.size() ; i++) {
            System.out.println(prdResults.get(i) + " " + mutationProbability.get(i));

        }
    }

    //todo
    //run this
    public static void prd_crossProbability(){

        Vector<String> filenames = new Vector<>();
        Vector<Integer> bestSolution = new Vector<>();

        filenames.add("gr21.tsp");
        filenames.add("bays29.tsp");
        filenames.add("dantzig42.tsp");
        filenames.add("gr48.tsp");
        filenames.add("berlin52.tsp");
        filenames.add("gr120.tsp");

        bestSolution.add(2707);
        bestSolution.add(2020);
        bestSolution.add(699);
        bestSolution.add(5046);
        bestSolution.add(7542);
        bestSolution.add(6942);


        Vector<Integer> prdResults = new Vector<>();


        Vector<Double> crossoverProbability = new Vector<>();

        for(double mutationProb = 0.4d; mutationProb <= 0.8d; mutationProb += 0.1d ){

            crossoverProbability.add(mutationProb);
        }


        for(int i =0; i <  crossoverProbability.size(); i++){
            prdResults.add(0);

        }
        int totalPopulation = 800;

        for(int filenameIndex = 0; filenameIndex < filenames.size();filenameIndex++){
            LoadDataTSP.resetData();
            LoadDataTSP.loadData(filenames.get(filenameIndex));
            DataMatrix.matrix = LoadDataTSP.matrix;
            DataMatrix.dimension = LoadDataTSP.dimension;
            DataMatrix.format = LoadDataTSP.format;
            DataMatrix.type = LoadDataTSP.type;

            for(int i =0 ; i <crossoverProbability.size(); i++) {
                System.out.println(filenames.get(filenameIndex) + " " + crossoverProbability.get(i));
                int prd = (int) Utils.calculatePRD(bestSolution.get(filenameIndex), Algorithms.geneticAlgorithm(3, 300,
                        GeneratingStartingPopulationMethod.HEURISTIC_KRAND, 1,
                        ParentSelectionMethod.RANDOM, CrossoverMethod.OrderCrossover, MutationMethod.SWAP,
                        0.1d, EndCondition.ITERATION_NUMBER_EXCEEDED, 1000,
                        Integer.MAX_VALUE, Integer.MAX_VALUE, 6, crossoverProbability.get(i), 2000, 100));

                System.out.println("PRD " + prd);
                System.out.println("best solution " + bestSolution.get(filenameIndex));
                prdResults.set(i, prdResults.get(i) + prd);

            }
        }

        System.out.println("PRD -CROSS PROB " + prdResults);

        for (int i = 0; i < prdResults.size() ; i++) {
            System.out.println(prdResults.get(i) + " " + crossoverProbability.get(i));

        }
    }

}
