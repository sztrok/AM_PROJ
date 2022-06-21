import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Vector;
import Enum.*;
public class TabuTest {

    //baseIterationNumber - poczatkowa ilosc iteracji
    //iterationNumberIncrease - wartosc o ile zwiekszamy iteracje
    //loopIterator - ile razy będziemy zwiększać


    private static void solveAndWriteToFileForMaxIteration(FileWriter fileWriter, int repeats,int baseIterationNumber,
                                                           int iterationNumberIncrease, int loopIterator, Integer optimalSolution) throws IOException {
        float prd;
        Vector<Long> goalFunctionData;
        Vector<Integer> result;
        String data;


        Vector<Integer> startingSolution = Algorithms.kRandom(1000);
        goalFunctionData = new Vector<>();

        for(int k=0 ; k< repeats; k++) {



            //init goalFunctionData
            for(int i =0; i < loopIterator; i++){
                goalFunctionData.add(0L);
            }

            for (int i = 0; i < loopIterator; i++) {
                int maxIterationNumber = baseIterationNumber + i * iterationNumberIncrease;
                result = Algorithms.tabuSearch(EndCondition.ITERATION_NUMBER_EXCEEDED, TabuExceed.REMOVE_FIRST_ELEMENT,
                        null, maxIterationNumber, 50, null, "invert",
                        50, null, null, null,
                        false, startingSolution);

                Long cost = Utils.calculateGoalFunction(result);
                goalFunctionData.set(i, goalFunctionData.get(i) + cost);
            }
        }

        //calculating avg goal function
        for (int i = 0; i < loopIterator; i++) {

            goalFunctionData.set(i, goalFunctionData.get(i)/repeats);

        }
        //getting optimal solution
        Long optimalResult = Collections.min(goalFunctionData);


        for (int i = 0; i < loopIterator; i++) {
            int maxIterationNumber = baseIterationNumber + i * iterationNumberIncrease;
            prd = Utils.calculatePRD(optimalSolution, goalFunctionData.get(i));

            data ="";
            data += DataMatrix.dimension + ";" + maxIterationNumber + ";" + prd;
            fileWriter.write(data);
            fileWriter.write("\n");
        }

    }

    private static void solveAndWriteToFileForMaxTime(FileWriter fileWriter, int repeats,int baseTime,
                                                      int timeIncrease, int loopIterator, Integer optimalSolution) throws IOException {
        float prd;
        Vector<Long> goalFunctionData;
        Vector<Integer> result;
        String data;
        int maxTime;

        Vector<Integer> startingSolution = Algorithms.kRandom(1000);
        goalFunctionData = new Vector<>();



        for(int k=0 ; k< repeats; k++) {

            //init goalFunctionData
            for(int i =0; i < loopIterator; i++){
                goalFunctionData.add(0L);
            }

            for (int i = 0; i < loopIterator; i++) {
                maxTime = baseTime + i * timeIncrease;
                result = Algorithms.tabuSearch(EndCondition.MAX_TIME_EXCEEDED, TabuExceed.REMOVE_FIRST_ELEMENT,
                        null    , null, 50, null, "invert",
                        50, null, null, maxTime,
                        false, startingSolution);

                Long cost = Utils.calculateGoalFunction(result);
                goalFunctionData.set(i, goalFunctionData.get(i) + cost);
            }
        }

        //calculating avg goal function
        for (int i = 0; i < loopIterator; i++) {
            goalFunctionData.set(i, goalFunctionData.get(i)/repeats);
        }
        //getting optimal solution
        Long optimalResult = Collections.min(goalFunctionData);


        for (int i = 0; i < loopIterator; i++) {
            maxTime = baseTime + i * timeIncrease;
            prd = Utils.calculatePRD(optimalSolution, goalFunctionData.get(i));
            data ="";
            data += DataMatrix.dimension + ";" + maxTime + ";" + prd;
            fileWriter.write(data);
            fileWriter.write("\n");
        }
    }

    private static void     solveAndWriteToFileForEnvSize(FileWriter fileWriter, int repeats,int baseEnvSize,
                                                      int envSizeIncrease, int loopIterator, Integer optimalSolution) throws IOException {
        float prd;
        Vector<Long> goalFunctionData;
        Vector<Integer> result;
        String data;
        int envSize;

        Vector<Integer> startingSolution = Algorithms.kRandom(1000);
        goalFunctionData = new Vector<>();


        for(int k=0 ; k< repeats; k++) {



            //init goalFunctionData
            for(int i =0; i < loopIterator; i++){
                goalFunctionData.add(0L);
            }

            for (int i = 0; i < loopIterator; i++) {
                envSize = baseEnvSize + i * envSizeIncrease;
                result = Algorithms.tabuSearch(EndCondition.MAX_TIME_EXCEEDED, TabuExceed.RETURN,
                        null, null, envSize, null, "invert",
                        100, null, null, Integer.MAX_VALUE,
                        false, startingSolution);

                Long cost = Utils.calculateGoalFunction(result);
                goalFunctionData.set(i, goalFunctionData.get(i) + cost);
            }
        }

        //calculating avg goal function
        for (int i = 0; i < loopIterator; i++) {
            goalFunctionData.set(i, goalFunctionData.get(i)/repeats);
        }
        //getting optimal solution
        Long optimalResult = Collections.min(goalFunctionData);


        for (int i = 0; i < loopIterator; i++) {
            envSize = baseEnvSize + i * envSizeIncrease;
            prd = Utils.calculatePRD(optimalSolution, goalFunctionData.get(i));
            data ="";
            data += DataMatrix.dimension + ";" + envSize + ";" + prd;
            fileWriter.write(data);
            fileWriter.write("\n");
        }
    }


    private static void solveAndWriteToFileForTabuSize(FileWriter fileWriter, int repeats, int baseTabuSize,
                                                       int tabuSizeIncrease, int loopIterator, Integer optimalSolution) throws IOException {
        float prd;
        Vector<Long> goalFunctionData;
        Vector<Integer> result;
        String data;
        int tabuSize;

        Vector<Integer> startingSolution = Algorithms.kRandom(1000);
        goalFunctionData = new Vector<>();



        for (int k = 0; k < repeats; k++) {


            //init goalFunctionData
            for (int i = 0; i < loopIterator; i++) {
                goalFunctionData.add(0L);
            }

            for (int i = 0; i < loopIterator; i++) {
                tabuSize = baseTabuSize + i * tabuSizeIncrease;
                result = Algorithms.tabuSearch(EndCondition.MAX_TIME_EXCEEDED, TabuExceed.RETURN,
                        null, null, 50, null, "invert",
                        tabuSize, null, null, Integer.MAX_VALUE,
                        false, startingSolution);

                Long cost = Utils.calculateGoalFunction(result);
                goalFunctionData.set(i, goalFunctionData.get(i) + cost);
            }
        }
        //calculating avg goal function
        for (int i = 0; i < loopIterator; i++) {
            goalFunctionData.set(i, goalFunctionData.get(i) / repeats);
        }
        //getting optimal solution
        Long optimalResult = Collections.min(goalFunctionData);


        for (int i = 0; i < loopIterator; i++) {
            tabuSize = baseTabuSize + i * tabuSizeIncrease;
            prd = Utils.calculatePRD(optimalSolution, goalFunctionData.get(i));
            data = "";
            data += DataMatrix.dimension + ";" + tabuSize + ";" + prd;
            fileWriter.write(data);
            fileWriter.write("\n");
        }
    }


    private static void solveAndWriteToFileForMaxWithoutImpr(FileWriter fileWriter, int repeats, int baseIteration,
                                                             int iterationIncrease, int loopIterator, Integer optimalSolution) throws IOException {
        float prd;
        Vector<Long> goalFunctionData;
        Vector<Integer> result;
        String data;
        int iterationWithoutImpr;

        Vector<Integer> startingSolution = Algorithms.kRandom(1000);
        goalFunctionData = new Vector<>();



        for (int k = 0; k < repeats; k++) {

            //init goalFunctionData
            for (int i = 0; i < loopIterator; i++) {
                goalFunctionData.add(0L);
            }

            for (int i = 0; i < loopIterator; i++) {
                iterationWithoutImpr = baseIteration + i * iterationIncrease;
                result = Algorithms.tabuSearch(EndCondition.ITERATION_WITHOUT_IMPROVEMENT, TabuExceed.REMOVE_FIRST_ELEMENT,
                        null, null, 50, null, "invert",
                        50, null, iterationWithoutImpr, null,
                        false, startingSolution);

                Long cost = Utils.calculateGoalFunction(result);
                goalFunctionData.set(i, goalFunctionData.get(i) + cost);
            }
        }
        //calculating avg goal function
        for (int i = 0; i < loopIterator; i++) {
            goalFunctionData.set(i, goalFunctionData.get(i) / repeats);
        }
        //getting optimal solution
        Long optimalResult = Collections.min(goalFunctionData);


        for (int i = 0; i < loopIterator; i++) {
            iterationWithoutImpr = baseIteration + i * iterationIncrease;
            prd = Utils.calculatePRD(optimalSolution, goalFunctionData.get(i));
            data = "";
            data += DataMatrix.dimension + ";" + iterationWithoutImpr + ";" + prd;
            fileWriter.write(data);
            fileWriter.write("\n");
        }
    }


//    private static void solveAndWriteToFileForWorseDeviation(FileWriter fileWriter, int repeats, double baseDeviation,
//                                                           double deviationIncrease, int loopIterator) throws IOException {
//        float prd;
//        Vector<Integer> goalFunctionData;
//        Vector<Integer> result;
//        String data;
//        double deviation;
//
//        Vector<Vector<Integer>> startingSolution = new Vector<>();
//        startingSolution.add(Algorithms.twoOpt("KRand"));
//
//        Vector<Integer> temp = new Vector<>();
//        goalFunctionData = new Vector<>();
//
////        for (int i = 0; i < loopIterator; i++) {
////            goalFunctionData.add(0L);
////        }
//
//
//
//        for (int i = 0; i < loopIterator; i++) {
//            Vector<Integer> innerResult = new Vector<>();
//            for (int k = 0; k < repeats; k++) {
//
//                deviation = baseDeviation + i * deviationIncrease;
//                startingSolution.add (Algorithms.tabuSearch(Enum.EndCondition.ITERATION_WITHOUT_IMPROVEMENT, TabuExceed.REMOVE_FIRST_ELEMENT,
//                        null, null, 50, null, "invert",
//                        50, deviation, 2000, null,
//                        true, startingSolution.get(i)));
//
//                int cost = (int) Utils.calculateGoalFunction(startingSolution.get(i));
//                innerResult.add(cost);
////                goalFunctionData.set(i, goalFunctionData.get(i) + cost);
//            }
//
//            int sum = innerResult.stream()
//                    .mapToInt(Integer::valueOf) // or .map(i -> i)
//                    .sum();
//            goalFunctionData.add( sum / repeats);
//        }
//        //getting optimal solution
//        Integer optimalResult = Collections.min(goalFunctionData);
//
//
//        for (int i = 0; i < loopIterator; i++) {
//            deviation = baseDeviation + i * deviationIncrease;
//            prd = Utils.calculatePRD(7542, goalFunctionData.get(i));
//            data = "";
//            data += DataMatrix.dimension + ";" + deviation + ";" + prd+ ";" + goalFunctionData.get(i)+ ";" + optimalResult;
//            fileWriter.write(data);
//            fileWriter.write("\n");
//        }
//        for(int i =0; i < loopIterator; i++){
//            System.out.println(startingSolution.get(i));
//        }
//    }

    public static void test() throws IOException {



//
//      String[] fileNames = {"berlin52.tsp", "dantzig42.tsp", "bays29.tsp"};
////, "eil101.tsp"
//
//       Vector<Integer> optimalSolutions = new Vector<>();
////       optimalSolutions.add(7542);
////       optimalSolutions.add(699.
////       optimalSolutions.add(2020);
//       optimalSolutions.add(629);
//       //createData(fileNames, optimalSolutions, "maxIteration");
//       //createData(fileNames, optimalSolutions, "tabuSize");
//       createData(fileNames, optimalSolutions, "maxTime");
//       //createData(fileNames, optimalSolutions, "envSize");
//       //createData(fileNames, optimalSolutions, "withoutImprove");
//
////        for(String name: fileNames) System.out.println(name);
////        int i =0;
////        for(String file:fileNames){
////            LoadDataTSP.loadData(file);
////            DataMatrix.matrix = LoadDataTSP.matrix;
////            DataMatrix.dimension = LoadDataTSP.dimension;
////            DataMatrix.format = LoadDataTSP.format;
////            DataMatrix.type = LoadDataTSP.type;
////
////            String filename = "tabu_size_TSPLIB";
////            if(Objects.equals(DataMatrix.format, ""))
////                DataMatrix.format = "EUC";
////            filename += "_" + DataMatrix.format + ".txt";
////
////            FileWriter fileWriter = new FileWriter(filename);
//////            solveAndWriteToFileForMaxIteration(maxIteration,10 ,10, 30,
//////                    100,optimalSolutions.get(i));
////
////
////
////            solveAndWriteToFileForTabuSize(fileWriter,10, 5, 5, 40, optimalSolutions.get(i));
//////            solveAndWriteToFileForMaxTime(fileWriter, 10, 200,100,20, optimalSolutions.get(i));
//////            solveAndWriteToFileForEnvSize(fileWriter,1,10,10,50, optimalSolutions.get(i));
//////            solveAndWriteToFileForMaxWithoImpr(fileWriter, 10,10,30,100, optimalSolutions.get(i));
////
////            LoadDataTSP.resetData();
////            i++;
////            fileWriter.close();
////        }
//    }
//
//    public static void createData(String[] fileNames, Vector<Integer> optimalSolutions, String testMode) throws IOException {
//        int i = 0;
//        for (String file : fileNames) {
//            LoadDataTSP.loadData(file);
//            DataMatrix.matrix = LoadDataTSP.matrix;
//            DataMatrix.dimension = LoadDataTSP.dimension;
//            DataMatrix.format = LoadDataTSP.format;
//            DataMatrix.type = LoadDataTSP.type;
//
//            String filename =testMode+ "_TSPLIB_";
//            if (Objects.equals(DataMatrix.format, ""))
//                DataMatrix.format = "EUC";
//            filename += DataMatrix.format + ".txt";
//
//            FileWriter fileWriter = new FileWriter(filename);
////            System.out.println(testMode);
//            //                    System.out.println("elo");
//            switch (testMode) {
//                case "maxIteration" -> solveAndWriteToFileForMaxIteration(fileWriter, 20, 10, 30, 100, optimalSolutions.get(i));
//                case "tabuSize" -> solveAndWriteToFileForTabuSize(fileWriter, 20, 5, 5, 40, optimalSolutions.get(i));
//                case "maxTime" -> solveAndWriteToFileForMaxTime(fileWriter, 10, 200,100,20, optimalSolutions.get(i));
//                case "envSize" -> solveAndWriteToFileForEnvSize(fileWriter, 20, 10, 10, 50, optimalSolutions.get(i));
//                case "withoutImprove" -> solveAndWriteToFileForMaxWithoutImpr(fileWriter, 20, 10, 30, 100, optimalSolutions.get(i));
//            }
//            LoadDataTSP.resetData();
//            i++;
//            fileWriter.close();
//
//        }
        String file = "gr120.tsp";
        LoadDataTSP.loadData(file);
        DataMatrix.matrix = LoadDataTSP.matrix;
        DataMatrix.dimension = LoadDataTSP.dimension;
        DataMatrix.format = LoadDataTSP.format;
        DataMatrix.type = LoadDataTSP.type;
        Vector<Integer> result = Algorithms.tabuSearch(EndCondition.ITERATION_NUMBER_EXCEEDED, TabuExceed.REMOVE_FIRST_ELEMENT,
                null, 100000, 100, null, "invert",
                120, null, null, null,
                false, Algorithms.kRandom(1000));
        System.out.println(result);
    }
}
