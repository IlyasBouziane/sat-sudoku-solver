import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.IProblem;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.TimeoutException;

public class SudokuSolver {
    public static ISolver solver;

    public static void main(String[] args) {
        solver = SolverFactory.newDefault();
        int n = 9;


        //each field has only one number
        //row
        for (int i = 1; i <= n; i++) {
            //column
            for (int j = 1; j <= n; j++) {
                int[] literals = new int[n];
                for (int k = 1; k <= n; k++) {
                    literals[k - 1] = 100 * i + 10 * j + k;
                }
                generateClauses(literals);
            }
        }

        //row
        for (int i = 1; i <= n; i++) {
            //each number exactly once
            for (int k = 1; k <= n; k++) {
                int[] literals = new int[n];
                for (int j = 1; j <= n; j++) {
                    literals[j - 1] = 100 * i + 10 * j + k;
                }
                generateClauses(literals);
            }
        }

        //each field has only one number
        //row
        for (int j = 1; j <= n; j++) {
            //each number exactly once
            for (int k = 1; k <= n; k++) {
                int[] literals = new int[n];
                for (int i = 1; i <= n; i++) {
                    literals[i - 1] = 100 * i + 10 * j + k;
                }
                generateClauses(literals);
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                //each number exactly once
                for (int k = 1; k <= n; k++) {
                    int[] literals = new int[n];
                    int counter = 0;
                    for (int h = 3 * i + 1; h <= 3 * i + 3; h++) {
                        for (int v = 3 * j + 1; v <= 3 * j + 3; v++) {
                            literals[counter] = 100 * h + 10 * v + k;
                            counter++;
                        }
                    }
                    generateClauses(literals);
                }
            }
        }

    }
}