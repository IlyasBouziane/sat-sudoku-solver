package utils;

public class Model {

    public static void printSudokuModel(int[] model) {
        int[] result = cleanModel(model);
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                System.out.print(result[i * 9 + j] % 10+" | ");
            }
            System.out.println("\n-----------------------------------");
        }
    }

    private static int[] cleanModel(int[] model) {
        int[] result = new int[81];
        int count = 0;
        int lastField = 0;
        for(int i=0;i<model.length;i++){
            if(count<=81 && model[i]>0) {
                if (lastField == model[i] / 10) {
                    System.out.println("Browsed twice the box "+lastField);
                } else {
                    lastField = model[i] / 10;
                    result[count] = model[i];
                    count++;
                }
            }
            if(count>81 && model[i]>0){
                System.out.println("ERROR: more than 1 number in one field");
                break;
            }
        }
        return result;

    }
}
