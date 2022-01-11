package main.java.solver;

import org.sat4j.minisat.SolverFactory;
import org.sat4j.specs.IProblem;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.TimeoutException;

import java.util.Scanner;

import static main.java.utils.Clauses.addSudokuClauses;
import static main.java.utils.Clauses.generateClauses;
import static main.java.utils.Model.printSudokuModel;
import static main.java.utils.ReadFile.getN;

public class SudokuSolver {
    public static ISolver solver;
    public static void main(String[] args) throws Exception {
        // choose file
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter filename absolute path : ");
        String filename = sc.next();


        solver= SolverFactory.newDefault();
        int n=getN(filename);


        //Each entry has unique value
        //row
        for(int i=1; i<=n;i++){
            //column
            for(int j=1;j<=n;j++){
                int[] literals = new int[n];
                for(int k=1;k<=n;k++){
                        literals[k-1]=Integer.parseInt(Integer.toString(i)+"0"+Integer.toString(j)+"0"+Integer.toString(k));
                }
                generateClauses(solver,literals,n);
            }
        }

        // ​Each row has all the numbers, each number only once
        for(int i=1; i<=n;i++){
            for(int k=1;k<=n;k++){
                int[] literals = new int[n];
                for(int j=1;j<=n;j++){
                    literals[j-1]=Integer.parseInt(Integer.toString(i)+"0"+Integer.toString(j)+"0"+Integer.toString(k));
                }
                generateClauses(solver,literals,n);
            }
        }

        //Each column has all the numbers, each number only once
        for(int j=1; j<=n;j++){
            //each number exactly once
            for(int k=1;k<=n;k++){
                int[] literals = new int[n];
                for(int i=1;i<=n;i++){
                   literals[i-1]=Integer.parseInt(Integer.toString(i)+"0"+Integer.toString(j)+"0"+Integer.toString(k));

                }
                generateClauses(solver,literals,n);
            }
        }

        //​Each block has all the numbers
        for(int i=0;i<Math.sqrt(n);i++){
            for(int j=0; j<Math.sqrt(n);j++){
                //each number exactly once
                for(int k=1;k<=n;k++){
                    int[] literals = new int[n];
                    int counter=0;
                    for(int h = (int) (Math.sqrt(n)*i+1); h<=Math.sqrt(n)*i+Math.sqrt(n); h++){
                        for(int v = (int) (Math.sqrt(n)*j+1); v<=Math.sqrt(n)*j+Math.sqrt(n); v++){
                                literals[counter]=Integer.parseInt(Integer.toString(h)+"0"+Integer.toString(v)+"0"+Integer.toString(k));
                            counter++;
                        }
                    }
                    generateClauses(solver,literals,n);
                }
            }
        }

        addSudokuClauses(solver,filename);

        IProblem problem = solver;
        try {
            if(problem.isSatisfiable()){
                int[] model=problem.model();
                printSudokuModel(model,n);
            } else {
                System.out.println("Problem unsatisfiable");
            }
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
