import javax.xml.crypto.Data;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Vector;
import java.util.zip.DataFormatException;

public class TestTSP {

    public static void generateData() throws IOException{
        FileWriter asymFile = new FileWriter("test_asym_TSPLIB.txt");
        FileWriter symFile = new FileWriter("test_sym_TSPLIB.txt");
        FileWriter eucFile = new FileWriter("test_euc_TSPLIB.txt");
        Vector<Vector<Integer>> matrix;

        Vector<Long> prdData;


        Vector<Integer> result0;
        Vector<Integer> result1;
        Vector<Integer> result2;
        double start;
        double end;
        double time;
        String data;

        String[] fileNames = {"bays29.tsp","berlin52.tsp","br17.atsp","dantzig42.tsp","fri26.tsp","ft70.atsp","gr17.tsp","gr21.tsp","gr24.tsp",
        "gr48.tsp","hk48.tsp","pr76.tsp","kroA100.tsp","kroB100.tsp","kroC100.tsp","kroD100.tsp","ch130.tsp","ch150.tsp","eil101.tsp"};

//        for(String name: fileNames) System.out.println(name);
        for(String file:fileNames){
            System.out.println("-------------------");
            System.out.println(file);
            LoadDataTSP.loadData(file);
            LoadDataTSP.resetData();
        }


//        LoadDataTSP.loadData(fileNames[0]);
//        LoadDataTSP.loadData(fileNames[1]);
//        LoadDataTSP.resetData();
//        LoadDataTSP.loadData(fileNames[2]);

        for(String file:fileNames){
            LoadDataTSP.loadData(file);
            DataMatrix.matrix = LoadDataTSP.matrix;
            DataMatrix.dimension = LoadDataTSP.dimension;
            DataMatrix.format = LoadDataTSP.format;
            DataMatrix.type = LoadDataTSP.type;


            long totalTimeCN = 0;
            long totalTimeCNE = 0;
            long totalTime2Opt = 0;

            float totalPrdCN =0.0f;
            float totalPrdCNE =0.0f;
            float totalPrd2Opt =0.0f;

            int repeats = 50;
            for(int r=0; r < repeats; r++){


                prdData = new Vector<>();
                data = "";

                start = System.nanoTime();
                result0 = Algorithms.extendedClosestNeighbour();
                end = System.nanoTime();
                time = end - start;

                totalTimeCNE += time;

                prdData.add(Utils.calculateGoalFunction(result0));

                data+=DataMatrix.dimension;
                data+=";";
                data+=time;



                start = System.nanoTime();
                result1 = Algorithms.twoOpt("CNE");
                end = System.nanoTime();
                time = end - start;

                totalTime2Opt += time;

                prdData.add(Utils.calculateGoalFunction(result1));


                data+=";";
                data+=time;


                start = System.nanoTime();
                result2 = Algorithms.closestNeighbour(10);
                end = System.nanoTime();
                time = end - start;

                totalTimeCN += time;

                prdData.add(Utils.calculateGoalFunction(result2));


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


            FileWriter newFile = switch (DataMatrix.format){

                case "FULL_MATRIX" -> asymFile;
                case "LOWER_DIAG_ROW" -> symFile;
                default -> eucFile;
            };
            data = "";
            data += DataMatrix.dimension +";" +totalTimeCNE/repeats + ";" + totalTime2Opt/repeats + ";" +totalTimeCN/repeats +';' +totalPrdCNE/repeats+ ";" +
                    totalPrd2Opt/repeats +";" + totalPrdCN/repeats;
            newFile.write(data);
            newFile.write("\n");

            LoadDataTSP.resetData();
        }
        asymFile.close();
        symFile.close();
        eucFile.close();
    }
}
