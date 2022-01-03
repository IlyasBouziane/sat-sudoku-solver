package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ReadFile {
        private static File myObj;

        public static List<Integer> getSudoku(String filename) throws FileNotFoundException {
            myObj = new File("./SudokuToTest/"+filename+".txt");
            List<String> mySudoku = new ArrayList<>();
            Scanner myReader = new Scanner(myObj);
            //read n
            String n = myReader.nextLine();

            //read sudoku
            int i=1;
            try {
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine().replace(" ","");
                    for(int j=0;j<Integer.parseInt(n);j++){
                        if(Character.compare(data.charAt(j),'.') ==0 ||
                                Character.compare(data.charAt(j),'-') ==0 ||
                                Character.compare(data.charAt(j),'_') ==0) {
                            continue;
                        }
                        mySudoku.add(Integer.toString(i)+Integer.toString(j+1)+data.charAt(j));
                    }
                    i++;
                }
            } catch (IndexOutOfBoundsException e){
                System.out.println("Index out of bound. Check please your sudoku numbers");
            }
            myReader.close();
            return mySudoku.stream().map(element->Integer.parseInt(element)).collect(Collectors.toList());
        }

        public static Integer getN(String filename) throws Exception {
            myObj = new File("./SudokuToTest/"+filename+".txt");
            Scanner myReader = new Scanner(myObj);
            Integer n = Integer.parseInt(myReader.nextLine());
            if( ! (n == 4 || n == 9 || n == 16 || n == 25) ){
                throw new Exception("n must be 4, 9, 16, 25");
            } else {
                return n;
            }
        }

}
