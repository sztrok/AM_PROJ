import javax.xml.crypto.Data;
import java.util.Vector;

public class Main {
    public static void main(String[] args){



        LoadDataTSP.loadData("fri26.tsp");
        DataMatrix.dimension = LoadDataTSP.dimension;
        DataMatrix.matrix = LoadDataTSP.matrix;
        DataMatrix.type = LoadDataTSP.type;
        DataMatrix.format = LoadDataTSP.format;

//        Vector<Integer> y = Algorithms.extendedClosestNeighbour();
//
//
//        System.out.println(Utils.calculateGoalFunction(y));

        Vector<Integer> x = Algorithms.kRandom(10);
        System.out.println(Utils.calculateGoalFunction(x));
        x = Algorithms.kRandom(1000);
        System.out.println(Utils.calculateGoalFunction(x));
    }
}
