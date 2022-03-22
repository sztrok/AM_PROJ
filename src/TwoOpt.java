import java.util.Vector;

public class TwoOpt {

   public static  Vector<Integer> solve(){

        //GF = Goal Function
        Vector<Integer> solution = ClosestNeighbour.solveExtended();
        Vector<Integer> currentSolution;
        Vector<Integer> tempSolution;

        long currentGFValue = Utils.calculateGoalFunction(solution);
        long smallestGFValue = Long.MAX_VALUE;

        while(currentGFValue < smallestGFValue){

            currentGFValue = Utils.calculateGoalFunction(solution);
            currentSolution = solution;

            for(int i =0; i < LoadDataTSP.dimension; i++){
                for(int j = i + 1; j < LoadDataTSP.dimension; j++){
                    for(int k =0; k < (j-i)/2 + 1 ; k++){

                        tempSolution = new Vector<>(currentSolution);

                        int temp = tempSolution.get(i + k);
                        tempSolution.set(i + k, tempSolution.get(j - k));
                        tempSolution.set(j - k, temp);

                        long possibleGFValue = Utils.calculateGoalFunction(tempSolution);

                        if(possibleGFValue < smallestGFValue){
                            smallestGFValue = possibleGFValue;
                            solution = tempSolution;
                        }
                    }
                }
            }

        }
        return solution;
    }
}
