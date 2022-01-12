package main.java.backtracking;

import main.java.utils.ReadFile;

import java.util.*;

public class SudokuBacktracking {

    public static int[][] GRID1 =       {
            {3,0, 0, 5, 7, 16, 2, 0, 12,11,0,0,0,0,10,0},
            {0, 1, 0, 16, 10, 0, 5, 0, 0,0,8,4,2,0,0,3},
            {0, 0, 11, 0, 0, 0, 4, 6, 0,1,10,0,16,15,0,0},
            {12, 9, 0, 0, 8, 0, 0, 0, 0,0,0,0,6,14,4,0},
            {13, 15, 0, 3, 1, 9, 0, 0, 0,10,0,0,0,0,14,0},
            {7, 0, 0, 0, 5, 0, 0, 2,0,12,0,0,0,3,13,0},
            {9, 14, 6, 0, 0, 0, 0, 7, 0,0,11,2,0,10,0,1},
            {0,0,2,0,0,6,10,0,13,0,0,0,0,0,0,7},
            {10,0,0,0,0,0,0,15,0,6,9,0,0,16,0,0},
            {1,0,3,0,9,5,0,0,4,0,0,0,0,13,8,2},
            {0,5,16,0,0,0,3,0,11,0,0,8,0,0,0,12},
            {0,11,0,0,0,0,13,0,0,0,3,15,1,0,6,14},
            {0, 12, 14, 1, 0, 0, 0, 0, 0,0,0,7,0,0,3,10},
            {0, 0, 7, 2, 0, 10, 15, 0, 6,4,0,0,0,5,0,0},
            {11, 0, 0, 10, 3, 1, 0, 0, 0,2,0,14,7,0,12,0},
            {0, 6, 0, 0, 0, 0, 8, 4, 0,15,1,3,14,0,0,13}
    };
    public static int[][] GRID2 =    {
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
    public static int SIZE;
    public static HashMap<int[], List<Integer>> POSSVALUESALLCELLS = new HashMap<>();

    public SudokuBacktracking(int[][] board) {
        this.board = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.board[i][j] = board[i][j];
            }
        }
    }

    public SudokuBacktracking() {

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

    public boolean solve()
    {
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

    public boolean solve2()
    {
        int i=0;
                for (Map.Entry<int[], List<Integer>> entry : POSSVALUESALLCELLS.entrySet()) {
                    int row = entry.getKey()[0];
                    int col = entry.getKey()[1];
                    List<Integer> possibleValues = entry.getValue();
                    if(board[row][col] == EMPTY){
                        for (int k = 0; k < possibleValues.size(); k++) {
                            if (isOk(row, col, possibleValues.get(k))) {
                                board[row][col] = possibleValues.get(k);
                                if (solve2()) {
                                    return true;
                                } else {
                                    board[row][col] = EMPTY;
                                }
                            }
                        }
                        return false;
                    }

                }

        return true;
    }


    public void generateLists(){
        HashMap<int [], List<Integer>> map = new HashMap<>();
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                ArrayList<Integer> list = new ArrayList<>();
                for (int number = 1; number <= SIZE; number++) {
                    if (isOk(row, col, number)) {
                        list.add(number);
                    }
                }
                map.put(new int[] {row,col},list);
            }
        }
        POSSVALUESALLCELLS = sortByPossibleValuesSize(map);
    }
    private HashMap<int [], List<Integer>> sortByPossibleValuesSize(HashMap<int [], List<Integer>> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<int [], List<Integer>> > list =
                new LinkedList<Map.Entry<int [], List<Integer>> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<int [], List<Integer>> >() {
            public int compare(Map.Entry<int [], List<Integer>> o1,
                               Map.Entry<int [], List<Integer>> o2)
            {
                return (o1.getValue().size())-(o2.getValue().size());
            }
        });

        // put data from sorted list to hashmap
        HashMap<int [], List<Integer>> temp = new LinkedHashMap<int [], List<Integer>>();
        for (Map.Entry<int [], List<Integer>> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
    public void display(){
        for(int i=0;i<SIZE;i++){
            for(int j=0;j<SIZE;j++){
                System.out.print(" "+board[i][j]);
            }
            System.out.println();
        }
    }

    public void backtrack(String[] args) throws Exception {
        // Test GRID1 and GRID2


        SudokuBacktracking sudoku;
        for(int i=1;i<=2;i++){
            if(i==1){
                SIZE = 16;
                sudoku= new SudokuBacktracking(GRID1);
            } else if(i==2){
                SIZE = 9;
                sudoku= new SudokuBacktracking(GRID2);
            } else {
                throw new Exception("Error at Grid number");
            }

            System.out.println("Sudoku grid to solve") ;
            sudoku.display();

            long startTime = System.currentTimeMillis();
            if(sudoku.solve()){
                System.out.println("\n\nSudoku grid solved with simple Recursive BT");
                sudoku.display();
            } else {
                System.out.println("Unsolvable");
            }
            long stopTime = System.currentTimeMillis();
            System.out.println("Execution time (ms) : " + (stopTime - startTime));


            sudoku.generateLists();
            long startTime2 = System.currentTimeMillis();
            if(sudoku.solve2()){
                System.out.println("\n\nSudoku grid solved with improved Recursive BT");
                sudoku.display();
            } else {
                System.out.println("Unsolvable");
            }
            long stopTime2 = System.currentTimeMillis();
            System.out.println("Execution time (ms) : " + (stopTime2 - startTime2));

            System.out.println("---------------------------------------");
        }






    }
}