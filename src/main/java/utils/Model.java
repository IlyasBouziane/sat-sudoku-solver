package main.java.utils;

public class Model {

    public static void printSudokuModel(int[] model,int n) {
        int[] result = cleanModel(model,n);
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                int index =0;
                String resultString =  Integer.toString(result[i * n + j]);
                if(result[i * n + j]%10 ==0){
                    index =resultString.substring(0,resultString.length()-2).lastIndexOf("0");
                }else{
                    index = Integer.toString(result[i * n + j]).lastIndexOf("0");
                }

                int res = Integer.parseInt(Integer.toString(result[i * n + j]).substring(index));
                System.out.print(res +" | ");
            }
            System.out.println("\n-----------------------------------");
        }
    }

    private static int[] cleanModel(int[] model, int n) {
        int[] result = new int[n*n];
        int count = 0;
        for(int i=0;i<model.length;i++){
            if(count<=n*n && model[i]>0) {
                    result[count] = model[i];
                    count++;
            }
            if(count>n*n && model[i]>0){
                System.out.println("ERROR: more than 1 number in one field");
                break;
            }
        }
        return result;

    }
}
