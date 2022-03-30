import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class Utils {


    static Random rand = new Random();

    public static void printSolution(ArrayList<Integer> solution){

        StringBuilder sb = new StringBuilder();
        for (Integer s : solution)
        {
            sb.append(s);
            sb.append("\t");
        }
        System.out.println(sb.toString());
    }

    public static long calculateGoalFunction(Vector<Integer> solution){

        long result = 0;

        for(int i = 0; i < solution.size() - 1; i++){

            int x = solution.get(i);
            int y = solution.get(i+1);

            if(DataMatrix.type.equals("FULL_MATRIX")){
                result += DataMatrix.matrix.get(x).get(y);
            }
            else{
                if(x > y) {
                    result += DataMatrix.matrix.get(x).get(y);
                }
                else
                    result += DataMatrix.matrix.get(y).get(x);
            }
        }

        int last = solution.lastElement();
        int first = solution.firstElement();
        if(last > first){
            result+=DataMatrix.matrix.get(last).get(first);
        }
        else result+=DataMatrix.matrix.get(first).get(last);


        return result;
    }
    public static float calculatePRD(Vector<Integer> optimalSolution, Vector<Integer> solution){

        return  (float) (calculateGoalFunction(solution) - calculateGoalFunction(optimalSolution))
                *100 / calculateGoalFunction(optimalSolution);

    }

}
