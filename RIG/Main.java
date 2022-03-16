import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class Main {
    public static void main(String[] args){



        RandomInstationGenerator randomInstationGenerator = new RandomInstationGenerator();

        Vector<Vector<Integer>> v = randomInstationGenerator.getEuclidean(3);
        for(Vector<Integer> s :v){
            System.out.println(s);
        }


        Utils u  = new Utils();


        ArrayList<Integer> x = new ArrayList<>();
        x.add(10);
        x.add(12);
        x.add(11);
        x.add(1);
        u.printSolution(x);
    }
}
