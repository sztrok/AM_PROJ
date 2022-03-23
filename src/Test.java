import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class Test {


    public static void main(String[] args){
        LoadDataTSP.loadData("fri26.tsp");
//        System.out.println(LoadDataTSP.matrix);
//        Algorithms.kRandom(2);

//        Vector<Integer> vector = Algorithms.twoOpt();
//        System.out.println(vector);
//        System.out.println(Utils.calculateGoalFunction(vector));

        int k = 20;
        Vector<Vector<Integer>> matrix = Algorithms.kRandom(k);
        for(int i=0; i<k; i++){
            System.out.println(Utils.calculateGoalFunction(matrix.get(i)));
        }

    }

}
