package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ReadFile {
        public static List<Integer> getSudoku() throws FileNotFoundException {
            List<String> mySudoku = new ArrayList<>();
            File myObj = new File("./SudokuToTest/myfile.txt");
            Scanner myReader = new Scanner(myObj);


            //read n
            int n = Integer.parseInt(myReader.nextLine());
            //mySudoku.add(n);

            //read sudoku
            int i=1;
            try {
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine().replace(" ","");
                    System.out.println(data);
                    for(int j=0;j<n;j++){
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
            //System.out.println(mySudoku);
            myReader.close();
            return mySudoku.stream().map(element->Integer.parseInt(element)).collect(Collectors.toList());
        }

}
