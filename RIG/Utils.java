import java.util.ArrayList;

public class Utils {

    void printSolution(ArrayList<Integer> solution){

        StringBuilder sb = new StringBuilder();
        for (Integer s : solution)
        {
            sb.append(s);
            sb.append("\t");
        }
        System.out.println(sb.toString());
    }

    int getGoalFunction(ArrayList<Integer> solution, int[][] distances){

        int result = 0;

        for(int i = 0; i < solution.size()-1; i++){
            result += distances[solution.get(i)][solution.get(i+1)];
        }

        return result;
    }
}