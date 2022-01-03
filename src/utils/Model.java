package utils;

public class Model {

    public static void printSudokuModel(int[] model,int n) {
        int[] result = cleanModel(model,n);
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(result[i * n + j] % 10+" | ");
            }
            System.out.println("\n-----------------------------------");
        }
    }

    private static int[] cleanModel(int[] model, int n) {
        int[] result = new int[n*n];
        int count = 0;
        int lastField = 0;
        for(int i=0;i<model.length;i++){
            if(count<=n*n && model[i]>0) {
                if (lastField == model[i] / 10) {
                    System.out.println("Browsed twice the box "+lastField);
                } else {
                    lastField = model[i] / 10;
                    result[count] = model[i];
                    count++;
                }
            }
            if(count>n*n && model[i]>0){
                System.out.println("ERROR: more than 1 number in one field");
                break;
            }
        }
        return result;

    }
}
