import org.sat4j.minisat.SolverFactory;
import org.sat4j.specs.IProblem;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.TimeoutException;

import static utils.Clauses.addSudokuClauses;
import static utils.Clauses.generateClauses;
import static utils.Model.printSudokuModel;

public class SudokuSolver {
    public static ISolver solver;
    public static void main(String[] args) {
        solver= SolverFactory.newDefault();
        int n=9;

        //Each entry has unique value
        //row
        for(int i=1; i<=n;i++){
            //column
            for(int j=1;j<=n;j++){
                int[] literals = new int[n];
                for(int k=1;k<=n;k++){
                    literals[k-1]=100*i+10*j+k;
                }
                generateClauses(solver,literals);
            }
        }

        /*System.out.println(solver);*/


        // ​Each row has all the numbers, each number only once
        for(int i=1; i<=n;i++){
            for(int k=1;k<=n;k++){
                int[] literals = new int[n];
                for(int j=1;j<=n;j++){
                    literals[j-1]=100*i+10*j+k;
                }
                generateClauses(solver,literals);
            }
        }

        /*System.out.println(solver);*/

        //Each column has all the numbers, each number only once
        for(int j=1; j<=n;j++){
            //each number exactly once
            for(int k=1;k<=n;k++){
                int[] literals = new int[n];
                for(int i=1;i<=n;i++){
                    literals[i-1]=100*i+10*j+k;
                }
                generateClauses(solver,literals);
            }
        }

        /*System.out.println(solver);*/

        //​Each block has all the numbers
        for(int i=0;i<3;i++){
            for(int j=0; j<3;j++){
                //each number exactly once
                for(int k=1;k<=n;k++){
                    int[] literals = new int[n];
                    int counter=0;
                    for(int h=3*i+1;h<=3*i+3;h++){
                        for(int v=3*j+1;v<=3*j+3;v++){
                            literals[counter]=100*h+10*v+k;
                            counter++;
                        }
                    }
                    generateClauses(solver,literals);
                }
            }
        }

        addSudokuClauses(solver);

        IProblem problem = solver;
        try {
            if(problem.isSatisfiable()){
                int[] model=problem.model();
                printSudokuModel(model);
            } else {
                System.out.println("Problem unsatisfiable");
            }
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
