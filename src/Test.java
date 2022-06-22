import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import EnumPack.*;


public class Test {


    public static void main(String[] args) throws IOException {

//        if(args.length==0) System.out.println("Not enough arguments");
//        if(args[0].equals("tsp")){
//            LoadDataTSP.loadData(args[1]);
//            DataMatrix.matrix = LoadDataTSP.matrix;
//            DataMatrix.type = LoadDataTSP.type;
//            DataMatrix.dimension = LoadDataTSP.dimension;
//            DataMatrix.format = LoadDataTSP.format;
////            dataMatrix = new DataMatrix(LoadDataTSP.matrix,LoadDataTSP.dimension,LoadDataTSP.format,LoadDataTSP.type);
//        }
//        else if(args[0].equals("rand")){
//            Vector<Vector<Integer>> matrix;
//
//            switch (args[1]) {
//                case "sym" ->{
//                    matrix = RandomInstationGenerator.getSymmetric(Integer.parseInt(args[2]));
//                    DataMatrix.matrix = matrix;
//                    DataMatrix.dimension = Integer.parseInt(args[2]);
//                    DataMatrix.format = "LOWER_DIAG_ROW";
//                    DataMatrix.type = "EXPLICIT";
////                    dataMatrix = new DataMatrix(matrix,Integer.parseInt(args[2]),"LOWER_DIAG_ROW","EXPLICIT");
//                }
//                case "asym" ->{
//                    matrix = RandomInstationGenerator.getAsymmetric(Integer.parseInt(args[2]));
//                    DataMatrix.matrix = matrix;
//                    DataMatrix.dimension = Integer.parseInt(args[2]);
//                    DataMatrix.format = "FULL_MATRIX";
//                    DataMatrix.type = "EXPLICIT";
////                    dataMatrix = new DataMatrix(matrix,Integer.parseInt(args[2]),"FULL_MATRIX","EXPLICIT");
//                }
//                case "euc" ->{
//                    matrix = RandomInstationGenerator.getEuclidean(Integer.parseInt(args[2]));
//                    DataMatrix.matrix = matrix;
//                    DataMatrix.dimension = Integer.parseInt(args[2]);
//                    DataMatrix.format = "";
//                    DataMatrix.type = "EUC_2D";
////                    dataMatrix = new DataMatrix(matrix,Integer.parseInt(args[2]),"","EUC_2D");
//                }
//                default -> System.out.println("Wrong arguments");
//            }
//
//        }
//
//
////        System.out.println(LoadDataTSP.matrix);
////        Algorithms.kRandom(2);
//
////        Vector<Integer> vector = Algorithms.twoOpt();
////        System.out.println(vector);
////        System.out.println(Utils.calculateGoalFunction(vector));
//
//        LoadDataTSP.loadData("fri26.tsp");
//
//        try{
//            FileWriter newFile = new FileWriter("symmetric_test.txt");
//            Vector<Long> prdData = new Vector<>();
//
//            double start = System.currentTimeMillis();
//            Vector<Integer> result = Algorithms.extendedClosestNeighbour();
//            double end = System.currentTimeMillis();
//            double time = end - start;
//            String data = "";
//            data+= DataMatrix.dimension;
//            data+= time;
//            data+= Utils.calculateGoalFunction(result);
//            prdData.add(Utils.calculateGoalFunction(result));
//
//            start = System.currentTimeMillis();
//            result = Algorithms.twoOpt();
//            end = System.currentTimeMillis();
//            time = end - start;
//            data+=time;
//            data+= Utils.calculateGoalFunction(result);
//            prdData.add(Utils.calculateGoalFunction(result));
//
//
//        }
//        catch (Exception e){
//            System.out.println("eeor");
//        }

//        TestSymRand.generateData(50);
//        TestAsymRand.generateData(50);
//        TestEucRand.generateData(50);
//
//        TestTSP.generateData();

//
//
//       /        KRandomTest.generateData();
//        int k = 20;
//        Vector<Vector<Integer>> matrix = Algorithms.kRandom(k);
//        for(int i=0; i<k; i++){
//            System.out.println(Utils.calculateGoalFunction(matrix.get(i)));
//        }



//        Vector<Vector<Integer>> x =RandomInstationGenerator.getEuclidean(15);
//        for(Vector<Integer> i: x){
//            System.out.println(i);
//        }
//
//        LoadDataTSP.loadData("gr120.tsp");
//        DataMatrix.format = LoadDataTSP.format;
//        DataMatrix.matrix = LoadDataTSP.matrix;
//        DataMatrix.type = LoadDataTSP.type;
//        DataMatrix.dimension = LoadDataTSP.dimension;
//        Vector<Integer> result1 = Algorithms.kRandom(10);
//        Vector<Integer> result2 = Algorithms.kRandom(1000);
//        Vector<Integer> result3 = Algorithms.kRandom(10000);
//        Vector<Integer> result4 = Algorithms.closestNeighbour(Utils.rand.nextInt(LoadDataTSP.dimension));
//        Vector<Integer> result5 = Algorithms.extendedClosestNeighbour();
//        Vector<Integer> result6 = Algorithms.twoOpt("KRand");
//        Vector<Integer> result7 = Algorithms.twoOpt("CNE");
//        Vector<Integer> result8 = Algorithms.twoOpt("CN");
//
//
//        System.out.println("krand 10 " + result1 + "\n" + Utils.calculateGoalFunction(result1) + " " + Utils.calculatePRD(6942, result1));
//        System.out.println("krand 1000 " + result2 + "\n" + Utils.calculateGoalFunction(result2)+ " " + Utils.calculatePRD(6942, result2));
//        System.out.println("krand 100000 " + result3 + "\n" + Utils.calculateGoalFunction(result3)+ " " + Utils.calculatePRD(6942, result3));
//        System.out.println("CN " + result4 + "\n" + Utils.calculateGoalFunction(result4)+ " " + Utils.calculatePRD(6942, result4));
//        System.out.println("CNE " + result5 + "\n" + Utils.calculateGoalFunction(result5)+ " " + Utils.calculatePRD(6942, result5));
//        System.out.println("2opt - krand " + result6 + "\n" + Utils.calculateGoalFunction(result6)+ " " + Utils.calculatePRD(6942, result6));
//        System.out.println("2opt - CNE " + result7 + "\n" + Utils.calculateGoalFunction(result7)+ " " + Utils.calculatePRD(6942, result7));
//        System.out.println("2opt - CN " + result8 + "\n" + Utils.calculateGoalFunction(result8)+ " " + Utils.calculatePRD(6942, result8));
//
//
//

//        Algorithms.twoOpt("CN");

        LoadDataTSP.loadData("berlin52.tsp");
        DataMatrix.format = LoadDataTSP.format;
        DataMatrix.matrix = LoadDataTSP.matrix;
        DataMatrix.type = LoadDataTSP.type;
        DataMatrix.dimension = LoadDataTSP.dimension;
//
//        Vector<Integer> x = Algorithms.twoOpt("CN");
//        System.out.println(Utils.calculateGoalFunction(x));
//        System.out.println(Utils.calculateGoalFunction(Algorithms.tabuSearch(Enum.EndCondition.ITERATION_WITHOUT_IMPROVEMENT, TabuExceed.REMOVE_FIRST_ELEMENT, "CN", 100, 10,null, "swap", 20, 10d,100000,1000,false,x )));

//
//        Vector<Integer> x = new Vector<>();
//        Vector<Integer> y = new Vector<>();
////
//        x.add(1);
//        x.add(2);
//        x.add(3);
//        x.add(4);
//        x.add(5);
//        x.add(6);
//        x.add(7);
//        x.add(8);
//
//
//
//        y.add(4);
//        y.add(2);
//        y.add(5);
//        y.add(1);
//        y.add(6);
//        y.add(8);
//        y.add(3);
//        y.add(7);
//        System.out.println(x);
//        System.out.println(y);
//
//        Parents p = new Parents(null, null);
//        Vector<Vector<Integer>> k  = p.partiallyMappedCrossover(x,y,0,4);
//
//        System.out.println(k);



//        Unit u1 = new Unit();
//        Vector<Integer> a = Algorithms.kRandom(1000);
//        Vector<Integer> b = Algorithms.kRandom(1000);
//        Vector<Integer> c = Algorithms.kRandom(1000);
//
//        u1.addChromosome(a);
//        u1.addChromosome(b);
//        u1.addChromosome(c);
//
//        Unit u2 = new Unit();
//        Vector<Integer> d = Algorithms.kRandom(1000);
//        Vector<Integer> e = Algorithms.kRandom(1000);
//        Vector<Integer> f = Algorithms.kRandom(1000);
//        u2.addChromosome(d);
//        u2.addChromosome(e);
//        u2.addChromosome(f);
//        Vector<Unit> x = new Vector<>();
//
//        x.add(u1);
//        x.add(u2);
//        System.out.println(u1.fenotypeSum);
//        System.out.println(u2.fenotypeSum);
//
//
//        x.sort(new Comparator<Unit>() {
//            @Override
//            public int compare(Unit o1, Unit o2) {
//                return o1.fenotypeSum - o2.fenotypeSum;
//            }
//        });
//       System.out.println(x.get(0).fenotypeSum + " " +x.get(1).fenotypeSum);
//    System.out.println("RES: "+Utils.calculateGoalFunction(


//
//        Vector<Unit> population = new Vector<>();
//        Unit u1 = new Unit();
//        u1.fenotypeSum = 100;
//        Unit u2 = new Unit();
//        u2.fenotypeSum = 100;
//        Unit u3 = new Unit();
//        u3.fenotypeSum = 400;
//        population.add(u1);
//        population.add(u2);
//        population.add(u3);
//
//        double sumOfFitness = 0.0d;
//        for (int i = 0; i < population.size(); i++) {
//            sumOfFitness += 1/ (double) population.get(i).fenotypeSum;
//        }
//        double[] probabilities = new double[population.size()];
//        double[] singleProb = new double[population.size()];
//
//        double sumOfProbabilities = 0.0d;
//        probabilities[0] = 0.0d;
//        for (int i = 0; i < population.size() - 1; i++) {
//
//            double probability =  sumOfProbabilities + ((double)  1/population.get(i).fenotypeSum)/sumOfFitness ;
//            sumOfProbabilities += probability;
//            probabilities[i+1] = probability;
//        }
//        for(int i =0; i < probabilities.length; i++ ){
//            System.out.println(probabilities[i]);
//
//        }
//        for(int i =0; i < probabilities.length; i++ ){
//           ;
//            System.out.println(singleProb[i]);
//        }
//        System.out.println(Utils.calculateGoalFunction( Algorithms.twoOpt("KRand")));
//        System.out.println(Utils.calculateGoalFunction( Algorithms.twoOpt("KRand",800)));
        System.out.println(Utils.calculateGoalFunction( Algorithms.geneticAlgorithm(500,GeneratingStartingPopulationMethod.HEURISTIC_KRAND,1000,
                ParentSelectionMethod.ROULETTE, CrossoverMethod.OrderCrossover, MutationMethod.INVERT,
                0.2d,EndCondition.ITERATION_NUMBER_EXCEEDED, 500,
                Integer.MAX_VALUE, Integer.MAX_VALUE, 6,0.9d, 50, 40)));
//        Vector<Integer> v1 = new Vector<>();
//        Vector<Integer> v2 = new Vector<>();
//        v1.add(1);
//        v2.add(v1.get(0));
//        v1.set(0,10);
//        System.out.println(v2);
    }

}
