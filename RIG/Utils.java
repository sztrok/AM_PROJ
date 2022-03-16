import java.util.ArrayList;
import java.util.Vector;

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


    int calculateGoalFunction(ArrayList<Integer> solution, Vector<Vector<Integer>> distances){


        int result = 0;

        for(int i = 0; i < solution.size()-1; i++){

            result += distances.get(solution.get(i)).get(solution.get(i+1));
        }

        return result;
    }

    float calculatePRD(ArrayList<Integer> optimalSolution, ArrayList<Integer> solution, Vector<Vector<Integer>> distances){

    {


        return  (float) (calculateGoalFunction(solution, distances) - calculateGoalFunction(optimalSolution, distances))
                *100 / calculateGoalFunction(optimalSolution, distances);

    }
}
