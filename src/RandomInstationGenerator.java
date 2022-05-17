import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class RandomInstationGenerator {

    static Random rand = new Random();


    //n - size
    public static Vector<Vector<Integer>> getAsymmetric(int n){

//        int[][] matrix = new int [n][n];
        Vector<Vector<Integer>> matrix = new Vector<>();

        for(int i=0; i<n; i++){
            Vector<Integer> vector = new Vector<>();
            for(int j = 0; j<n; j++){

                if(i ==j){
                    vector.add(0);
                }else {
                    vector.add(Math.abs(rand.nextInt()%500 + 100));
                }
            }

            matrix.add(vector);
        }
        return  matrix;
    }

    public static Vector<Vector<Integer>> getSymmetric(int n){
//        int[][] matrix = new int[n][n];

        Vector<Vector<Integer>> matrix = new Vector<>();
        for(int i=0; i<n; i++){
            Vector<Integer> vector = new Vector<>();
            for(int j = 0; j <= i; j++){
                if(i == j){
                    vector.add(0);
                }else {
                    vector.add( Math.abs(rand.nextInt()%500 + 100));

                }
            }
            matrix.add(vector);
        }
        return  matrix;
    }

    public static Vector<Vector<Integer>> getEuclidean(int n){

        ArrayList<int []> points = new ArrayList<>();

        Vector<Vector<Integer>> matrix = new Vector<>();

        for(int i =0; i <n; i++){
            int[] coordinates = new int[2];
            coordinates[0] = Math.abs(rand.nextInt()%500 + 100);
            coordinates[1] = Math.abs(rand.nextInt()%500 + 100);
            points.add(coordinates);


        }

        for(int i = 0; i < n; i++) {
            Vector<Integer> vector = new Vector<>();

            for (int j = 0; j <= i; j++) {

                if (i == j) {
                    vector.add(0);
                } else {

                    int[] point1 = points.get(i);
                    int[] point2 = points.get(j);


                    int distance = (int) Math.sqrt(Math.pow((point1[0] - point2[0]), 2) + Math.pow(point1[1] - point2[1], 2));
                    vector.add(distance);

                }
            }
            matrix.add(vector);
        }
        return matrix;
    }
}
