package main.java.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SudokuBacktracking {

    public static int[][] GRID = /*{
            {9, 0, 0, 1, 0, 0, 0, 0, 5},
            {0, 0, 5, 0, 9, 0, 2, 0, 1},
            {8, 0, 0, 0, 4, 0, 0, 0, 0},
            {0, 0, 0, 0, 8, 0, 0, 0, 0},
            {0, 0, 0, 7, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 2, 6, 0, 0, 9},
            {2, 0, 0, 3, 0, 0, 0, 0, 6},
            {0, 0, 0, 2, 0, 0, 0, 0, 0},
            {0, 0, 1, 9, 0, 4, 5, 7, 0}
    };*/    {
            {0, 0, 3, 2, 0, 8, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 0, 4, 0},
            {0, 0, 0, 0, 0, 7, 0, 0, 0},
            {8, 0, 0, 5, 0, 2, 0, 9, 0},
            {0, 0, 5, 0, 4, 0, 0, 0, 0},
            {0, 0, 0, 0, 7, 0, 0, 0, 2},
            {9, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 5, 0, 0, 0, 0},
            {0, 0, 8, 3, 0, 6, 0, 0, 4}
    };

    private int[][] board;
    public static final int EMPTY = 0;
    public static final int SIZE = 9;
    public static ArrayList<ArrayList<Integer>> LISTS = new ArrayList<>();

    public SudokuBacktracking(int[][] board) {
        this.board = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.board[i][j] = board[i][j];
            }
        }
    }

    private boolean isInRow(int row, int number) {
        for (int j = 0; j < SIZE; j++) {
            if (board[row][j] == number)
                return true;
        }
        return false;
    }

    private boolean isInColumn(int col, int number) {
        for (int i = 0; i < SIZE; i++) {
            if (board[i][col] == number)
                return true;
        }
        return false;
    }

    private boolean isInBox(int row, int col, int number) {
        int r = row - row % 3;
        int c = col - col % 3;
        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                if (board[i][j] == number)
                    return true;
            }
        }
        return false;
    }

    private boolean isOk(int row, int col, int number) {
        return !isInRow(row, number) && !isInColumn(col, number) && !isInBox(row, col, number);
    }

    public boolean solve() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == EMPTY) {
                    for (int number = 1; number <= SIZE; number++) {
                        if (isOk(row, col, number)) {
                            board[row][col] = number;
                            if (solve()) {
                                return true;
                            } else {
                                board[row][col] = EMPTY;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public void generateLists(){
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                ArrayList<Integer> list = new ArrayList<>();
                if(board[row][col] != EMPTY){
                    lists.add(null);
                } else {
                    for (int number = 1; number <= SIZE; number++) {
                        if (isOk(row, col, number)) {
                            list.add(number);
                        }
                    }
                    lists.add(list);
                }
            }
        }

        LISTS=lists;
    }

    public boolean solve2() {
        int i=0;
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == EMPTY) {
                    ArrayList<Integer> possibleValuesInCell = LISTS.get(i);
                    for (int k = 0; k < possibleValuesInCell.size(); k++) {
                        if(possibleValuesInCell.size() == 0){
                            return false;
                        }else{
                            if (isOk(row, col, possibleValuesInCell.get(k))) {
                                board[row][col] = possibleValuesInCell.get(k);
                                if (solve2()) {
                                    return true;
                                } else {
                                    board[row][col] = EMPTY;
                                    //ArrayList<Integer> l = LISTS.get(i);
                                    //l.remove(k);
                                    //LISTS.set(i,l);
                                }
                            }
                        }

                    }

                    return false;
                } else {
                    i++;
                }

            }
        }
        return true;
    }

    public void display(){
        for(int i=0;i<SIZE;i++){
            for(int j=0;j<SIZE;j++){
                System.out.print(" "+board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SudokuBacktracking sudoku = new SudokuBacktracking(GRID);
        System.out.println("Sudoku grid to solve") ;
        sudoku.display();
        sudoku.generateLists();
        System.out.println(sudoku.LISTS);
        long startTime = System.currentTimeMillis();
        if(sudoku.solve2()){
            System.out.println("Sudoku grid solved with Recursive BT");
            sudoku.display();
        } else {
            System.out.println("Unsolvable");
        }
        long stopTime = System.currentTimeMillis();
        System.out.println("Execution time with better heurstic : " + (stopTime - startTime));

        long startTime1 = System.currentTimeMillis();
        if(sudoku.solve()){
            System.out.println("Sudoku grid solved with Recursive BT");
            sudoku.display();
        } else {
            System.out.println("Unsolvable");
        }
        long stopTime1 = System.currentTimeMillis();
        System.out.println("Execution time with normal heurstic : " + (stopTime1 - startTime1));

    }
}