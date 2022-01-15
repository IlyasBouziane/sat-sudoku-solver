package main.java;

import main.java.backtracking.SudokuBacktracking;
import main.java.generator.SudokuGenerator;
import main.java.satsolver.SudokuSATSolver;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws Exception {
        /*  choose type of application
            1- SAT Solving
            2- Backtracking
            3- Sudoku Generator

         */
        Scanner sc = new Scanner(System.in);
        System.out.print("Type a number corresponding to the wanted application \n1- SAT Solving\n" +
                "2- Backtracking\n" +
                "3- Sudoku Generator\n");
        String choice = sc.next();

        if(choice.equals("1")){
            SudokuSATSolver sudokuSolver = new SudokuSATSolver();
            sudokuSolver.satSolve(args);
        } else if(choice.equals("2")){
            SudokuBacktracking sudokuBacktracking = new SudokuBacktracking();
            sudokuBacktracking.backtrack(args);
        } else if(choice.equals("3")){
            SudokuGenerator sudokuGenerator = new SudokuGenerator();
            sudokuGenerator.generate(args);
        } else {
            System.out.println("Wrong number");
        }

    }

}
