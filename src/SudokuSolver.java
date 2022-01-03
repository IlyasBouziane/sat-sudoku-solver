import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.IProblem;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.TimeoutException;

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
                generateClauses(literals);
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
                generateClauses(literals);
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
                generateClauses(literals);
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
                    generateClauses(literals);
                }
            }
        }

        addSudokuClauses();

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

    private static void addSudokuClauses() {
        try {
            /* Sudoku 1
            solver.addClause(new VecInt(new int[]{115}));
            solver.addClause(new VecInt(new int[]{144}));
            solver.addClause(new VecInt(new int[]{156}));
            solver.addClause(new VecInt(new int[]{167}));
            solver.addClause(new VecInt(new int[]{173}));
            solver.addClause(new VecInt(new int[]{199}));
            solver.addClause(new VecInt(new int[]{219}));
            solver.addClause(new VecInt(new int[]{233}));
            solver.addClause(new VecInt(new int[]{248}));
            solver.addClause(new VecInt(new int[]{251}));
            solver.addClause(new VecInt(new int[]{274}));
            solver.addClause(new VecInt(new int[]{282}));
            solver.addClause(new VecInt(new int[]{297}));
            solver.addClause(new VecInt(new int[]{311}));
            solver.addClause(new VecInt(new int[]{327}));
            solver.addClause(new VecInt(new int[]{334}));
            solver.addClause(new VecInt(new int[]{342}));
            solver.addClause(new VecInt(new int[]{363}));
            solver.addClause(new VecInt(new int[]{412}));
            solver.addClause(new VecInt(new int[]{423}));
            solver.addClause(new VecInt(new int[]{431}));
            solver.addClause(new VecInt(new int[]{449}));
            solver.addClause(new VecInt(new int[]{457}));
            solver.addClause(new VecInt(new int[]{466}));
            solver.addClause(new VecInt(new int[]{478}));
            solver.addClause(new VecInt(new int[]{485}));
            solver.addClause(new VecInt(new int[]{494}));
            solver.addClause(new VecInt(new int[]{518}));
            solver.addClause(new VecInt(new int[]{525}));
            solver.addClause(new VecInt(new int[]{537}));
            solver.addClause(new VecInt(new int[]{541}));
            solver.addClause(new VecInt(new int[]{552}));
            solver.addClause(new VecInt(new int[]{564}));
            solver.addClause(new VecInt(new int[]{589}));
            solver.addClause(new VecInt(new int[]{614}));
            solver.addClause(new VecInt(new int[]{629}));
            solver.addClause(new VecInt(new int[]{636}));
            solver.addClause(new VecInt(new int[]{643}));
            solver.addClause(new VecInt(new int[]{668}));
            solver.addClause(new VecInt(new int[]{671}));
            solver.addClause(new VecInt(new int[]{687}));
            solver.addClause(new VecInt(new int[]{692}));
            solver.addClause(new VecInt(new int[]{758}));
            solver.addClause(new VecInt(new int[]{769}));
            solver.addClause(new VecInt(new int[]{772}));
            solver.addClause(new VecInt(new int[]{786}));
            solver.addClause(new VecInt(new int[]{817}));
            solver.addClause(new VecInt(new int[]{828}));
            solver.addClause(new VecInt(new int[]{832}));
            solver.addClause(new VecInt(new int[]{846}));
            solver.addClause(new VecInt(new int[]{854}));
            solver.addClause(new VecInt(new int[]{861}));
            solver.addClause(new VecInt(new int[]{895}));
            solver.addClause(new VecInt(new int[]{921}));
            solver.addClause(new VecInt(new int[]{977}));
            solver.addClause(new VecInt(new int[]{998}));

             */
            solver.addClause(new VecInt(new int[]{133}));
            solver.addClause(new VecInt(new int[]{142}));
            solver.addClause(new VecInt(new int[]{168}));
            solver.addClause(new VecInt(new int[]{196}));
            solver.addClause(new VecInt(new int[]{226}));
            solver.addClause(new VecInt(new int[]{284}));
            solver.addClause(new VecInt(new int[]{367}));
            solver.addClause(new VecInt(new int[]{418}));
            solver.addClause(new VecInt(new int[]{445}));
            solver.addClause(new VecInt(new int[]{462}));
            solver.addClause(new VecInt(new int[]{489}));
            solver.addClause(new VecInt(new int[]{535}));
            solver.addClause(new VecInt(new int[]{554}));
            solver.addClause(new VecInt(new int[]{657}));
            solver.addClause(new VecInt(new int[]{692}));
            solver.addClause(new VecInt(new int[]{719}));
            solver.addClause(new VecInt(new int[]{771}));
            solver.addClause(new VecInt(new int[]{855}));
            solver.addClause(new VecInt(new int[]{938}));
            solver.addClause(new VecInt(new int[]{943}));
            solver.addClause(new VecInt(new int[]{966}));
            solver.addClause(new VecInt(new int[]{994}));




        } catch (ContradictionException e) {
            e.printStackTrace();
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

    private static void printSudokuModel(int[] model) {
        int[] result = cleanModel(model);
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                System.out.print(result[i * 9 + j] % 10+" | ");
            }
            System.out.println("\n-----------------------------------");
        }
    }

    private static int[] cleanModel(int[] model) {
        int[] result = new int[81];
        int count = 0;
        int lastField = 0;
        for(int i=0;i<model.length;i++){
            if(count<=81 && model[i]>0) {
                if (lastField == model[i] / 10) {
                    System.out.println("Browsed twice the box "+lastField);
                } else {
                    lastField = model[i] / 10;
                    result[count] = model[i];
                    count++;
                }
            }
            if(count>81 && model[i]>0){
                System.out.println("ERROR: more than 1 number in one field");
                break;
            }
        }
        return result;

    }


}
