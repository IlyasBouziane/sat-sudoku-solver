package main.java.generator;

import java.util.*;

public class Solver {
    public static int SIZE = 9;
    public static int[][] GRID9 = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private int[][] board;
    public static final int EMPTY = 0;
    public static HashMap<int[], List<Integer>> POSSVALUESALLCELLS = new HashMap<>();
    private static int[] RANDOM = generateRandomValues();

    public Solver(int[][] board) {
        this.board = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.board[i][j] = board[i][j];
            }
        }
    }
    public Solver(){}

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
        int r = row - row % (int)Math.sqrt(SIZE);
        int c = col - col % (int)Math.sqrt(SIZE);
        for (int i = r; i < r + (int)Math.sqrt(SIZE); i++) {
            for (int j = c; j < c + (int)Math.sqrt(SIZE); j++) {
                if (board[i][j] == number)
                    return true;
            }
        }
        return false;
    }

    private boolean isOk(int row, int col, int number) {
        return !isInRow(row, number) && !isInColumn(col, number) && !isInBox(row, col, number);
    }

    boolean solveForGenerator()
        {
            for (int row = 0; row < SIZE; row++) {
                for (int col = 0; col < SIZE; col++) {
                    if (board[row][col] == EMPTY) {
                        for(int i=0;i<SIZE;i++){
                            int randNumber = RANDOM[i];
                            if (isOk(row, col, randNumber)) {
                                board[row][col] = randNumber;
                                if (solveForGenerator()) {
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


    private static int[] generateRandomValues() {
        int[] values = new int[SIZE]; 
        if(SIZE==4){
            values = new int[]{1, 2, 3, 4};
        } else if(SIZE == 9){
            values = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        } else if(SIZE == 16) {
            values = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        } else if(SIZE == 25) {
            values = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19 ,20, 21 ,22, 23, 24 ,25};
        }
        

        Random random = new Random();
        for (int i = 0, j = random.nextInt(SIZE), tmp = values[j]; i < values.length;
             i++, j = random.nextInt(SIZE), tmp = values[j]) {
            if(i == j) continue;

            values[j] = values[i];
            values[i] = tmp;
        }
        return values;
    }


    public int[][] getBoard() {
        return board;
    }

    public int getSIZE() {
        return SIZE;
    }
}
