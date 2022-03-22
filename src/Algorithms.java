import java.util.Collections;
import java.util.Vector;

public class Algorithms {



    public static void costFuction(Vector<Vector<Integer>> matrix){

    }

    public static void kRandom(int k){
        Vector<Vector<Integer>> matrix = LoadDataTSP.matrix;
        int dimension = LoadDataTSP.dimension;
        String format = LoadDataTSP.format;
        String type = LoadDataTSP.type;

        Vector<Vector<Integer>> permutations = new Vector<>();

        for(int i=0; i<k; i++){
            Vector<Integer> dimVector = new Vector<>();
            for(int j=0; j<dimension; j++){
                dimVector.add(j);
            }
            Collections.shuffle(dimVector);
            permutations.add(dimVector);
//            System.out.println(dimVector);
//            System.out.println(permutations);
        }
        System.out.println(permutations);

        costFuction(permutations);
        System.out.println(permutations.get(0).indexOf(5));
//        System.out.println(Collections.max(permutations));

    }

    public static void closestNeighbour(int startNode){
        Vector<Vector<Integer>> matrix = LoadDataTSP.matrix;
        int dimension = LoadDataTSP.dimension;
        String format = LoadDataTSP.format;
        String type = LoadDataTSP.type;

        Vector<Integer> visited = new Vector<>();
        Vector<Vector<Integer>> values = new Vector<>();
        while(visited.size()!=LoadDataTSP.dimension){
            for(int i=0; i<startNode; i++){
                if(!visited.contains(i)){
                    Vector<Integer> vec = new Vector<>();
                    vec.add(i);
                    vec.add(matrix.get(startNode).get(i));
                    values.add(vec);
                    visited.add(i);
                }
            }

            for(int i=startNode+1; i<dimension; i++){
                if(!visited.contains(i)){
                    
                }
            }

        }
    }
}
