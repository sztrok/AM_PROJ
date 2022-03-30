import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Vector;

public class TestEucRand {

    public static void generateData(int repeats) throws IOException {
        FileWriter newFile = new FileWriter("test_euc_rand.txt");
        Vector<Vector<Integer>> matrix;


        Vector<Long> prdData;


        Vector<Integer> result0;
        Vector<Integer> result1;
        Vector<Integer> result2;
        double start;
        double end;
        double time;
        String data;


        for(int i=15; i<50; i++){


            long totalTimeCN = 0;
            long totalTimeCNE = 0;
            long totalTime2Opt = 0;

            float totalPrdCN =0.0f;
            float totalPrdCNE =0.0f;
            float totalPrd2Opt =0.0f;
            for(int k=0; k<repeats; k++){
                data = "";
                prdData = new Vector<>();

                matrix = RandomInstationGenerator.getEuclidean(i);
                DataMatrix.matrix = matrix;
                DataMatrix.dimension = i;
                DataMatrix.format = "LOWER_DIAG_ROW";
                DataMatrix.type = "EXPLICIT";


                start = System.nanoTime();
                result0 = Algorithms.extendedClosestNeighbour();
                end = System.nanoTime();
                time = end - start;

                totalTimeCNE += time;

                prdData.add(Utils.calculateGoalFunction(result0));

                data+=i;
                data+=";";
                data+=time;



                start = System.nanoTime();
                result1 = Algorithms.twoOpt("CNE");
                end = System.nanoTime();
                time = end - start;

                totalTime2Opt += time;

                prdData.add(Utils.calculateGoalFunction(result1));

                data+=i;
                data+=";";
                data+=time;


                start = System.nanoTime();
                result2 = Algorithms.closestNeighbour(10);
                end = System.nanoTime();
                time = end - start;

                totalTimeCN += time;

                prdData.add(Utils.calculateGoalFunction(result2));

                data+=i;
                data+=";";
                data+=time;



                int opt = prdData.indexOf(Collections.min(prdData));
//            System.out.println("SIZE: "+prdData.size());

                Vector<Integer> optimalRoute = switch (opt) {
                    case 0 -> result0;
                    case 1 -> result1;
                    case 2 -> result2;
                    default -> new Vector<>();
                };

//                System.out.println(optimalRoute);
                float prd0 = Utils.calculatePRD(optimalRoute,result0);
                float prd1 = Utils.calculatePRD(optimalRoute,result1);
                float prd2 = Utils.calculatePRD(optimalRoute,result2);


                data+=";";
                data+=prd0;
                data+=";";
                data+=prd1;
                data+=";";
                data+=prd2;

                totalPrdCNE += prd0;
                totalPrd2Opt += prd1;
                totalPrdCN += prd2;

//                newFile.write(data);
//
//                newFile.write("\n");
            }
            data = "";
            data += i +";" +totalTimeCNE/repeats + ";" + totalTime2Opt/repeats + ";" +totalTimeCN/repeats +';' +totalPrdCNE/repeats+ ";" +
                    totalPrd2Opt/repeats +";" + totalPrdCN/repeats;
            newFile.write(data);
            newFile.write("\n");
        }
        newFile.close();

    }

}
