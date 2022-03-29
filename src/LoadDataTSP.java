import java.io.File;
import java.util.Scanner;
import java.util.Vector;

public class LoadDataTSP {

    private static String path="";
    public static Vector<Vector<Integer>> matrix = new Vector<>();
    private static Vector<Vector<Double>> euclides = new Vector<>();

    public static int dimension=0;
    public static String format = "";
    public static String type = "";

    private static void setPath(String fileName){
        path +=".//Data//";
        path +=fileName;
    }

    public static void loadData(String fileName){
        setPath(fileName);

        try{
            File myFile = new File(path);
            Scanner myScanner = new Scanner(myFile);
            while(myScanner.hasNext()){
                String data = myScanner.next();
                if(data.equals("EDGE_WEIGHT_TYPE:")){
                    data = myScanner.next();
                    type = data;
                }
                if(data.equals("EDGE_WEIGHT_FORMAT:")){
                    data = myScanner.next();
                    format = data;
                }
                if(data.equals("DIMENSION:")){
                    data = myScanner.next();
                    dimension = Integer.parseInt(data);
                }
                if(!type.equals("EUC_2D")){
                    if(dimension!=0 && !type.equals("") && !format.equals("")) break;
                }
                else{
                    if(dimension!=0) break;
                }

            }

            System.out.println("DIMENSION: "+dimension);
            System.out.println("TYPE: "+type);
            System.out.println("FORMAT: "+format);
            if(type.equals("EXPLICIT")){
                int counter = dimension*(dimension+1)/2;
                while(myScanner.hasNext()){
                    if(myScanner.next().equals("EDGE_WEIGHT_SECTION")) break;
                }

                if(format.equals("LOWER_DIAG_ROW")){
                    Vector<Integer> vector = new Vector<>();

                    for(int i=0; i<counter; i++){
                        String data = myScanner.next();

                        int numb = Integer.parseInt(data);

                        vector.add(numb);
                        if(numb == 0){
                            matrix.add(vector);
                            vector = new Vector<>();

                        }
                    }
                }
                else if(format.equals("FULL_MATRIX")){
                    Vector<Integer> vector = new Vector<>();
                    for(int i=0; i<dimension; i++){
                        for(int j=0; j<dimension; j++){
                            String data = myScanner.next();

                            int numb = Integer.parseInt(data);
                            vector.add(numb);
                        }
                        matrix.add(vector);
                        vector = new Vector<>();
                    }
                }
                else System.out.println("FORMAT NOT SUPPORTED: "+format);
            }
            else if(type.equals("EUC_2D")){
                while(myScanner.hasNext()){
                    if(myScanner.next().equals("NODE_COORD_SECTION")) break;
                }

                Vector<Double> vector = new Vector<>();
                while(myScanner.hasNext()){
                    if(myScanner.next().equals("EOF")) break;

                    double x = Double.parseDouble(myScanner.next());
                    double y = Double.parseDouble(myScanner.next());
                    vector.add(x);
                    vector.add(y);
                    euclides.add(vector);
                    vector = new Vector<>();
                }

                Vector<Integer> eucVector = new Vector<>();
                eucVector.add(0);
                matrix.add(eucVector);
                eucVector = new Vector<>();

                for(int i=1; i<dimension; i++){
                    for(int j=0; j<i; j++){
                        int numb = (int) Math.sqrt(calcDistances(euclides.get(i).get(0),euclides.get(j).get(0)) + calcDistances(euclides.get(i).get(1),euclides.get(j).get(1)));
                        eucVector.add(numb);

                    }
                    eucVector.add(0);
                    matrix.add(eucVector);
                    eucVector = new Vector<>();
                }

            }
            else System.out.println("TYPE NOT SUPPORTED: "+type);



        }
        catch (Exception e){
            System.out.println("ERROR");
        }
    }

    private static double calcDistances(double v1, double v2){
        return  Math.pow(v1-v2,2);
    }

    public static void resetData(){
        path="";
        matrix = new Vector<>();
        euclides = new Vector<>();
        dimension=0;
        format = "";
        type = "";
    }

}
