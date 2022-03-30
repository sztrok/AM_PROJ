import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class KRandomTest {

    public static void generateData() throws IOException {

        Vector<Vector<Integer>> matrix;


        Vector<Long> prdData;


        Vector<Integer> result;

        double start;
        double end;
        double time;
        String data;
        ArrayList<String> files = new ArrayList<>();
        files.add("berlin52.tsp");
        files.add("bays29.tsp");
        files.add("gr120.tsp");
        for (String file: files) {


            String fileName = file.replace(".tsp", "_kRand.txt");

            FileWriter newFile = new FileWriter(fileName);

            LoadDataTSP.loadData(file);
            DataMatrix.matrix = LoadDataTSP.matrix;
            DataMatrix.dimension = LoadDataTSP.dimension;
            DataMatrix.format = LoadDataTSP.format;
            DataMatrix.type = LoadDataTSP.type;

            Vector<Integer> twoOptResult = Algorithms.twoOpt("CNE");
            for(int k = 10; k < 1000; k++) {


                start = System.nanoTime();
                result = Algorithms.kRandom(k);
                end = System.nanoTime();
                time = end - start;


                float prd = Utils.calculatePRD(twoOptResult, result);


                data = "";
                data += k + ";" + time + ";" + prd;
                newFile.write(data);
                newFile.write("\n");
            }
            newFile.close();
            LoadDataTSP.resetData();
        }
    }
}
