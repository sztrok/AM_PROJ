import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class TwoOptTest {

    public static void generateData(int repeats) throws IOException {
        FileWriter newFile = new FileWriter("test_two_opt.txt");
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

            matrix = RandomInstationGenerator.getAsymmetric(i);
            DataMatrix.matrix = matrix;
            DataMatrix.dimension = i;
            DataMatrix.format = "LOWER_DIAG_ROW";
            DataMatrix.type = "EXPLICIT";
            long totalTimeCN = 0;
            long totalTimeCNE = 0;
            long totalTimeKRand = 0;

            float totalPrdCN =0.0f;
            float totalPrdCNE =0.0f;
            float totalPrdKRand =0.0f;

            for(int k=0; k<repeats; k++){
                data = "";
                prdData = new Vector<>();

                start = System.nanoTime();
                result0 =  Algorithms.twoOpt("KRand");
                end = System.nanoTime();
                time = end - start;

                totalTimeKRand += time;

                prdData.add(Utils.calculateGoalFunction(result0));

                data+=i;
                data+=";";
                data+=time;

                start = System.nanoTime();
                result1 = Algorithms.twoOpt("CNE");
                end = System.nanoTime();
                time = end - start;

                totalTimeCN += time;

                prdData.add(Utils.calculateGoalFunction(result1));

                data+=i;
                data+=";";
                data+=time;


                start = System.nanoTime();
                result2 = Algorithms.twoOpt("CN");
                end = System.nanoTime();
                time = end - start;

                totalTimeCNE += time;

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

                totalPrdKRand += prd0;
                totalPrdCNE += prd1;
                totalPrdCN += prd2;

            }

            data = "";
            data += i +";" + totalTimeKRand/repeats + ";" + totalTimeCNE/repeats + ";" +totalTimeCN/repeats +';'
                    +totalPrdKRand/repeats+ ";" + totalPrdCNE/repeats +";" + totalPrdCN/repeats;
            newFile.write(data);
            newFile.write("\n");
        }
        newFile.close();
    }
}
