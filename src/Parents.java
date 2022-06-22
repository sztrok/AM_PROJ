import java.util.*;

import EnumPack.CrossoverMethod;

public class Parents {

   public Unit parent1, parent2;
   public static int chromosomeSize;
   public static double crossProbability;
   public Random rand = new Random();
   public static CrossoverMethod crossoverMethod;
   public Parents(Unit parent1, Unit parent2){
       this.parent1 = parent1;
       this.parent2 = parent2;
   }



    public Vector<Unit> cross(){
       Random random = new Random();
       boolean transcription = false;
//        System.out.println("CX M: "+crossoverMethod);
        Unit childrenUnit1 = new Unit();
        Unit childrenUnit2 = new Unit();
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0; i<parent1.genotype.get(0).size(); i++){
            list.add(i);
        }
        Collections.shuffle(list);
        int crossoverPoint1 = list.get(0);
        int crossoverPoint2 = list.get(1);
        if(crossoverPoint1 > crossoverPoint2){
            int temp = crossoverPoint1;
            crossoverPoint1 = crossoverPoint2;
            crossoverPoint2 = temp;
        }
//        int crossoverPoint1 = rand.nextInt(parent1.genotype.get(0).size());
//        int crossoverPoint2 = crossoverPoint1;

//        while(crossoverPoint2 <= crossoverPoint1){
//            crossoverPoint2 = rand.nextInt(parent1.genotype.get(0).size());
//            System.out.println("LOOP");
//        }

        Vector<Unit> children = new Vector<>();
        if(crossProbability >= rand.nextDouble()){

            if(random.nextDouble() < 0.8d){
                transcription = true;
                Vector<Integer> parent1Best =  parent1.genotype.get( parent1.fenotype.indexOf( Collections.min(parent1.fenotype)));
                Vector<Integer> parent1Worst =  parent1.genotype.get( parent1.fenotype.indexOf( Collections.max(parent1.fenotype)));
                Vector<Integer> parent2Best =  parent2.genotype.get( parent2.fenotype.indexOf( Collections.min(parent2.fenotype)));
                Vector<Integer> parent2Worst =  parent2.genotype.get( parent2.fenotype.indexOf( Collections.max(parent2.fenotype)));

                Vector<Integer> worst;
                Vector<Integer> best;

                if( Collections.max(parent1.fenotype) >  Collections.max(parent2.fenotype)){
                    worst = parent1Worst;
                    best = parent2Best;
                    parent1.genotype.remove(parent1Worst);
                    parent2.genotype.remove(parent2Best);
                }else{
                    worst = parent2Worst;
                    best = parent1Best;
                    parent2.genotype.remove(parent2Worst);
                    parent1.genotype.remove(parent2Best);
                }
                childrenUnit1.addChromosome(best);
                childrenUnit2.addChromosome(best);

//            System.out.println("P1 " + parent1.genotype.size());
//            System.out.println("P2 " + parent2.genotype.size());
//            System.out.println("ch " + chromosomeSize);
                if( Collections.max(parent1.fenotype) >  Collections.max(parent2.fenotype)){

                    parent1.genotype.add(parent1Worst);
                    parent2.genotype.add(parent2Best);
                }else{
                    parent2.genotype.add(parent2Worst);
                    parent1.genotype.add(parent1Best);
                }
            }
            if(transcription){
                for(int i =0; i < chromosomeSize-1; i++){
                    Vector<Vector<Integer>> result = new Vector<>();


                    switch (crossoverMethod){
                        case PartiallyMappedCrossover -> result = partiallyMappedCrossover(parent1.genotype.get(i), parent2.genotype.get(i),
                                crossoverPoint1, crossoverPoint2);
                        case OrderCrossover -> result = orderCrossover(parent1.genotype.get(i), parent2.genotype.get(i),
                                crossoverPoint1, crossoverPoint2);
                    }

                    childrenUnit1.addChromosome(result.get(0));
                    childrenUnit2.addChromosome(result.get(1));
                }
            }
            else{
                for(int i =0; i < chromosomeSize; i++){
                    Vector<Vector<Integer>> result = new Vector<>();


                    switch (crossoverMethod){
                        case PartiallyMappedCrossover -> result = partiallyMappedCrossover(parent1.genotype.get(i), parent2.genotype.get(i),
                                crossoverPoint1, crossoverPoint2);
                        case OrderCrossover -> result = orderCrossover(parent1.genotype.get(i), parent2.genotype.get(i),
                                crossoverPoint1, crossoverPoint2);
                    }

                    childrenUnit1.addChromosome(result.get(0));
                    childrenUnit2.addChromosome(result.get(1));
                }
            }
            children.add(childrenUnit1);
            children.add(childrenUnit2);
        }else{
            children.add(parent1);
            children.add(parent2);
        }
        return children;
    }



    public Vector<Vector<Integer>> orderCrossover(Vector<Integer> parentChromosome1, Vector<Integer> parentChromosome2,
                                                            int crossoverPoint1, int crossoverPoint2){
        assert  crossoverPoint2 >crossoverPoint1;

//        System.out.println("ORDER START");
        Vector<Integer> childrenChromosome1 = new Vector<>();
        Vector<Integer> childrenChromosome2 = new Vector<>();

        for(int i =0; i < parentChromosome1.size() ; i ++){
            childrenChromosome1.add(-1);
            childrenChromosome2.add(-1);
        }

        for(int i = crossoverPoint1; i < crossoverPoint2; i++){

            childrenChromosome1.set(i, parentChromosome1.get(i));
            childrenChromosome2.set(i, parentChromosome2.get(i));
        }
        int childrenIndex =0;
        int index =0;
        for(; childrenIndex < crossoverPoint1; index++){
//            System.out.println("child index= " + childrenIndex + " childValue = " + childrenChromosome1.get(childrenIndex)
//                    +" index = " + index + " parentValue = " + parentChromosome2.get(index));
//            System.out.println("child1 " + childrenChromosome1);
//            System.out.println("parent2 " + parentChromosome2);
//            System.out.println("\n");
            if(!childrenChromosome1.contains(parentChromosome2.get(index))){
                childrenChromosome1.set(childrenIndex, parentChromosome2.get(index));
                childrenIndex++;
            }
        }

        childrenIndex = crossoverPoint2;

        for(; childrenIndex < parentChromosome1.size(); index++){

//            System.out.println("child index= " + childrenIndex + " childValue = " + childrenChromosome1.get(childrenIndex)
//                    +" index = " + index + " parentValue = " + parentChromosome2.get(index));
//            System.out.println("child1 " + childrenChromosome1);
//            System.out.println("parent2 " + parentChromosome2);
//            System.out.println("\n");
            if(!childrenChromosome1.contains(parentChromosome2.get(index))){
                childrenChromosome1.set(childrenIndex, parentChromosome2.get(index));
                childrenIndex++;
            }

        }



        childrenIndex =0;
        index =0;
        for(; childrenIndex < crossoverPoint1; index++){

            if(!childrenChromosome2.contains(parentChromosome1.get(index))){
                childrenChromosome2.set(childrenIndex, parentChromosome1.get(index));
                childrenIndex++;
            }
        }

        childrenIndex = crossoverPoint2;

        for(; childrenIndex < parentChromosome2.size(); index++){

//            System.out.println("child index= " + childrenIndex + " childValue = " + childrenChromosome1.get(childrenIndex)
//                    +" index = " + index + " parentValue = " + parentChromosome2.get(index));
//            System.out.println("child1 " + childrenChromosome1);
//            System.out.println("parent2 " + parentChromosome2);
//            System.out.println("\n");
            if(!childrenChromosome2.contains(parentChromosome1.get(index))){
                childrenChromosome2.set(childrenIndex, parentChromosome1.get(index));
                childrenIndex++;
            }

        }

        Vector<Vector<Integer>>children =  new Vector<>();
        children.add(childrenChromosome1);
        children.add(childrenChromosome2);
//        System.out.println("ORDER END");
        return  children;
    }



   public Vector<Vector<Integer>> partiallyMappedCrossover(Vector<Integer> parentChromosome1, Vector<Integer> parentChromosome2,
                                                            int crossoverPoint1, int crossoverPoint2){
        assert  crossoverPoint2 >crossoverPoint1;


        Vector<Integer> childrenChromosome1 = new Vector<>();
        Vector<Integer> childrenChromosome2 = new Vector<>();


        HashMap<Integer, Integer> mapping1 = new HashMap<>();
        HashMap<Integer, Integer> mapping2 = new HashMap<>();


       for(int i = 0; i < parentChromosome1.size(); i++){
           childrenChromosome1.add(parentChromosome1.get(i));
           childrenChromosome2.add(parentChromosome2.get(i));
       }

        for(int i = crossoverPoint1; i < crossoverPoint2; i++){
            mapping1.put(parentChromosome2.get(i), parentChromosome1.get(i));
            mapping2.put(parentChromosome1.get(i), parentChromosome2.get(i));
            childrenChromosome1.set(i, parentChromosome2.get(i));
            childrenChromosome2.set(i, parentChromosome1.get(i));
        }


        for(int i =0; i < crossoverPoint1; i++){
            while(mapping1.containsKey(childrenChromosome1.get(i))){
                childrenChromosome1.set(i, mapping1.get(childrenChromosome1.get(i)));
            }
            while(mapping2.containsKey(childrenChromosome2.get(i))) {
                childrenChromosome2.set(i, mapping2.get(childrenChromosome2.get(i)));
            }
        }


       for(int i =crossoverPoint2; i < parentChromosome1.size(); i++){
           while(mapping1.containsKey(childrenChromosome1.get(i))){
               childrenChromosome1.set(i, mapping1.get(childrenChromosome1.get(i)));
           }
           while(mapping2.containsKey(childrenChromosome2.get(i))) {
               childrenChromosome2.set(i, mapping2  .get(childrenChromosome2.get(i)));
           }
       }

        Vector<Vector<Integer>>children =  new Vector<>();
        children.add(childrenChromosome1);
        children.add(childrenChromosome2);
        return  children;
   }

}
