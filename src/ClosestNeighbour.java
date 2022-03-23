//import java.util.Collections;
//import java.util.Random;
//import java.util.Vector;
//
//
//public class ClosestNeighbour {
//
//    private static Random rand;
//    public ClosestNeighbour(){
//        rand = new Random();
//    }
//
//
//    private static Vector<Integer> solve(int startNode){
//
//        Vector<Integer> visited = new Vector<>();
//
//        int currentNode = startNode;
//        int neighbour = currentNode;
//
//        visited.add(currentNode);
//
//        while(visited.size() != LoadDataTSP.dimension) {
//
//            int min = Integer.MAX_VALUE;
//
//            for(int i : LoadDataTSP.matrix.get(currentNode)) {
//                int possibleNeighbour = LoadDataTSP.matrix.get(currentNode).indexOf(i);
//
//
//                if (possibleNeighbour != currentNode && i < min &&
//                        !visited.contains(possibleNeighbour)) {
//                    min = i;
//                    neighbour = possibleNeighbour;
//                }
//            }
//
//            for (int i = currentNode + 1; i < LoadDataTSP.dimension; i++) {
//
//                if (min > LoadDataTSP.matrix.get(i).get(currentNode) &&
//                            !visited.contains(i)) {
//
//                    min = LoadDataTSP.matrix.get(i).get(currentNode);
//                    neighbour = i;
//                }
//            }
//            currentNode = neighbour;
//            visited.add(neighbour);
//        }
//        return  visited;
//    }
//
//    public static Vector<Integer> solveRandom(){
//        return solve(rand.nextInt(LoadDataTSP.dimension));
//    }
//
//    public static Vector<Integer> solveExtended() {
//
//        Vector<Integer> result = new Vector<>();
//        long smallestGoalFunctionValue = Long.MAX_VALUE ;
//
//        for(int i =0 ; i < LoadDataTSP.dimension; i++){
//
//            Vector<Integer> possibleResult = solve(i);
//            long goalFunctionValue = Utils.calculateGoalFunction(possibleResult);
//
//            if( goalFunctionValue < smallestGoalFunctionValue){
//             result = possibleResult;
//             smallestGoalFunctionValue = goalFunctionValue;
//            }
//        }
//        return result;
//    }
//}
