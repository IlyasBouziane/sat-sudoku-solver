package main.java.generator;

import java.util.Random;
import java.util.Scanner;

import static main.java.generator.Solver.GRID9;

public class SudokuGenerator {
    public static int SIZE;

    public void generate (String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        System.out.print("Select a (number) level :\n" +
                "1- Easy\n" +
                "2- Medium\n" +
                "3- Hard\n" +
                "4- Super Hard\n" +
                "");
        String choice = sc.next();

        int numberOfEmptyCells = selectLevel(choice);

        int[][] board;

        SIZE=9;
        Solver sudoku = new Solver(GRID9);
        if(sudoku.solveForGenerator()){
            board=sudoku.getBoard();
        } else {
            throw new Exception("Unsolvable");
        }
        eraseCells(board, numberOfEmptyCells);


        display(board);
        }

        private static int selectLevel(String choice){
            int numberOfEmptyCells;
            Random random = new Random();
            switch (choice){
                case "1":
                    numberOfEmptyCells = 1+random.nextInt(20);
                    break;
                case "2":
                    numberOfEmptyCells = 21+random.nextInt(20);
                    break;
                case "3":
                    numberOfEmptyCells = 41+random.nextInt(20);
                    break;
                case "4":
                    numberOfEmptyCells = 61+random.nextInt(20);
                    break;
                default:
                    numberOfEmptyCells = 1+random.nextInt(20);

            }
            return numberOfEmptyCells;
        }

        private static int[][] eraseCells(int[][] board, int numberOfEmptyCells){
            Random random = new Random();
            for (int i = 0; i < numberOfEmptyCells; i++) {
                int randomRow = random.nextInt(9);
                int randomColumn = random.nextInt(9);

                int cell=  board[randomRow][randomColumn];
                if (cell !=0 ) {
                    board[randomRow][randomColumn] = 0;
                } else {
                    i--;
                }
            }
            return board;
        }

        public static void display(int[][] board){
            for(int i=0;i<SIZE;i++){
                for(int j=0;j<SIZE;j++){
                    System.out.print(" "+board[i][j]);
                }
                System.out.println();
            }
        }

}
