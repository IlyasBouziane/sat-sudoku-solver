package main.java.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFile {
        private static File myObj;

    public static List<Integer> getSudoku(String filename) throws FileNotFoundException {
        System.out.println(System.getProperty("user.dir"));
        myObj = new File("C:\\Users\\hp\\Documents\\2021\\sat-sudoku-solver\\SudokuToTest\\"+filename+".txt");
        List<Integer> mySudoku = new ArrayList<>();
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
                    switch(data.charAt(j)){
                        case 'A':
                        case 'a':
                            mySudoku.add(Integer.parseInt(Integer.toString(i)+"0"+Integer.toString(j+1)+"0"+"10"));
                            break;
                        case 'B':
                        case 'b':
                            mySudoku.add(Integer.parseInt(Integer.toString(i)+"0"+Integer.toString(j+1)+"0"+"11"));
                            break;
                        case 'C':
                        case 'c':
                            mySudoku.add(Integer.parseInt(Integer.toString(i)+"0"+Integer.toString(j+1)+"0"+"12"));
                            break;
                        case 'D':
                        case 'd':
                            mySudoku.add(Integer.parseInt(Integer.toString(i)+"0"+Integer.toString(j+1)+"0"+"13"));
                            break;
                        case 'E':
                        case 'e':
                            mySudoku.add(Integer.parseInt(Integer.toString(i)+"0"+Integer.toString(j+1)+"0"+"14"));
                            break;
                        case 'F':
                        case 'f':
                            mySudoku.add(Integer.parseInt(Integer.toString(i)+"0"+Integer.toString(j+1)+"0"+"15"));
                            break;
                        case 'G':
                        case 'g':
                            mySudoku.add(Integer.parseInt(Integer.toString(i)+"0"+Integer.toString(j+1)+"0"+"16"));
                            break;
                        case 'H':
                        case 'h':
                            mySudoku.add(Integer.parseInt(Integer.toString(i)+"0"+Integer.toString(j+1)+"0"+"17"));
                            break;
                        case 'I':
                        case 'i':
                            mySudoku.add(Integer.parseInt(Integer.toString(i)+"0"+Integer.toString(j+1)+"0"+"18"));
                            break;
                        case 'J':
                        case 'j':
                            mySudoku.add(Integer.parseInt(Integer.toString(i)+"0"+Integer.toString(j+1)+"0"+"19"));
                            break;
                        case 'K':
                        case 'k':
                            mySudoku.add(Integer.parseInt(Integer.toString(i)+"0"+Integer.toString(j+1)+"0"+"20"));
                            break;
                        case 'L':
                        case 'l':
                            mySudoku.add(Integer.parseInt(Integer.toString(i)+"0"+Integer.toString(j+1)+"0"+"21"));
                            break;
                        case 'M':
                        case 'm':
                            mySudoku.add(Integer.parseInt(Integer.toString(i)+"0"+Integer.toString(j+1)+"0"+"22"));
                            break;
                        case 'N':
                        case 'n':
                            mySudoku.add(Integer.parseInt(Integer.toString(i)+"0"+Integer.toString(j+1)+"0"+"23"));
                            break;
                        case 'O':
                        case 'o':
                            mySudoku.add(Integer.parseInt(Integer.toString(i)+"0"+Integer.toString(j+1)+"0"+"24"));
                            break;
                        case 'P':
                        case 'p':
                            mySudoku.add(Integer.parseInt(Integer.toString(i)+"0"+Integer.toString(j+1)+"0"+"25"));
                            break;
                        default:
                            mySudoku.add(Integer.parseInt(Integer.toString(i)+"0"+Integer.toString(j+1)+"0"+String.valueOf(data.charAt(j))));


                    }

                }
                i++;
            }
        } catch (IndexOutOfBoundsException e){
            System.out.println("Index out of bound. Check please your sudoku numbers");
        }
        myReader.close();
        return mySudoku;
    }


        public static Integer getN(String filename) throws Exception {
            myObj = new File("C:\\Users\\hp\\Documents\\2021\\sat-sudoku-solver\\SudokuToTest\\"+filename+".txt");
            Scanner myReader = new Scanner(myObj);
            Integer n = Integer.parseInt(myReader.nextLine());
            if( ! (n == 4 || n == 9 || n == 16 || n == 25) ){
                throw new Exception("n must be 4, 9, 16, 25");
            } else {
                return n;
            }
        }

}
