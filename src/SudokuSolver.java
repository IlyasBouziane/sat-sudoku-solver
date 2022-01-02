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


        //Each entry has unique value
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

        System.out.println(solver);


        // ​Each row has all the numbers, each number only once
        for (int i = 1; i <= n; i++) {
            for (int k = 1; k <= n; k++) {
                int[] literals = new int[n];
                for (int j = 1; j <= n; j++) {
                    literals[j - 1] = 100 * i + 10 * j + k;
                }
                generateClauses(literals);
            }
        }

        System.out.println(solver);

        //Each column has all the numbers, each number only once
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
        System.out.println(solver);

        //​Each block has all the numbers
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


    private static void generateClauses(int[] literals) {
        for(int i=-1;i<2;i+=2){
            for(int j=-1;j<2;j+=2){
                for(int k=-1;k<2;k+=2){
                    for(int l=-1;l<2;l+=2){
                        for(int m=-1;m<2;m+=2){
                            for(int n=-1;n<2;n+=2){
                                for(int o=-1;o<2;o+=2){
                                    for(int p=-1;p<2;p+=2){
                                        for(int q=-1;q<2;q+=2){
                                            int[] clause = new int[9];
                                            if(i+j+k+l+m+n+o+p+q ==7){
                                                continue;
                                            }
                                            clause[0]=i*literals[0];
                                            clause[1]=j*literals[1];
                                            clause[2]=k*literals[2];
                                            clause[3]=l*literals[3];
                                            clause[4]=m*literals[4];
                                            clause[5]=n*literals[5];
                                            clause[6]=o*literals[6];
                                            clause[7]=p*literals[7];
                                            clause[8]=q*literals[8];

                                            try {
                                                solver.addClause(new VecInt(clause));

                                            } catch (ContradictionException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }


}
