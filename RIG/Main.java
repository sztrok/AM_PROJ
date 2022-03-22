import java.util.Vector;

public class Main {
    public static void main(String[] args){



        LoadDataTSP.loadData("fri26.tsp");

        RandomInstationGenerator randomInstationGenerator = new RandomInstationGenerator();

        Vector<Vector<Integer>> v =  randomInstationGenerator.getSymmetric(5);
//        for(Vector<Integer> s : v){
//            System.out.println(s);
//        }
//
        for(Vector<Integer> s :LoadDataTSP.matrix){
            System.out.println(s);
        }

        Vector<Integer> y = ClosestNeighbour.solveExtended();


        System.out.println(Utils.calculateGoalFunction(y));

        Vector<Integer> x = TwoOpt.solve();
        System.out.println(Utils.calculateGoalFunction(x));


    }
}
