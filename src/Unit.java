import java.util.Vector;

public class Unit {
    Vector<Vector<Integer>> genotype = new Vector<>();
    Vector<Integer> fenotype = new Vector<>();
    int fenotypeSum;



    public void addChromosome(Vector<Integer> chromosome){
        genotype.add(chromosome);
        fenotype.add((int) Utils.calculateGoalFunction(chromosome));
        int sum = 0;
        for (Integer integer : fenotype) {
            sum += integer;
        }
        fenotypeSum = sum;
    }


    int getFenotypeSum(){
        int sum = 0;
        for (Integer integer : fenotype) {
            sum += integer;
        }
        return sum;
    }


}
