import java.util.ArrayList;
import java.util.Random;

public class RandomInstationGenerator {

    Random rand;

    public RandomInstationGenerator(){

        rand = new Random();
    }
    //n - size
    public int[][] getAsymmetric(int n){

        int[][] matrix = new int [n][n];
        for(int i=0; i<n; i++){
            for(int j = 0; j<n; j++){

                if(i ==j){
                    matrix[i][j] = 0;
                }else {
                    matrix[i][j] = rand.nextInt();
                }
            }
        }
        return  matrix;
    }

    public int[][] getSymmetric(int n){
        int[][] matrix = new int[n][n];
        for(int i=0; i<n; i++){
            matrix[i][i] = 0;
            for(int j = 0; j <= i; j++){
                if(i == j){
                    matrix[i][j] =0;
                }else {
                    matrix[i][j] = rand.nextInt();
                    matrix[j][i] = matrix[i][j];
                }
            }
        }
        return  matrix;
    }

    public int[][] getEuclidean(int n){

        ArrayList<int []> points = new ArrayList<>();

        int[][] matrix = new int[n][n];

        for(int i =0; i <n; i++){
            int[] coordinates = new int[2];
            coordinates[0] = rand.nextInt();
            coordinates[1] = rand.nextInt();
            points.add(coordinates);

            matrix[i][i] = 0;
        }

        for(int i = 0; i < n; i++){
            for(int j = i+1; j < n; j++){

                int[] point1 = points.get(i);
                int[] point2 = points.get(j);

                int distance = (int) Math.sqrt(Math.pow((point1[0] -point2[0]),2) + Math.pow(point1[1] - point2[1],2));
                matrix[i][j] = distance;
                matrix[j][i] = distance;
            }
        }
        return  matrix;
    }
}
