import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.ISolver;

public class SudokuSolver {
    public static ISolver solver;
    public static void main(String[] args) {
        int n=4;


        //each field has only one number
        //row
        for(int i=1; i<=n;i++){
            //column
            for(int j=1;j<=n;j++){
                int[] literals = new int[n];
                for(int k=1;k<=n;k++){
                    literals[k-1]=100*i+10*j+k;
                }
                generateOnlyOnceClauses(literals);
            }
        }

        //row
        for(int i=1; i<=n;i++){
            //each number exactly once
            for(int k=1;k<=n;k++){
                int[] literals = new int[n];
                for(int j=1;j<=n;j++){
                    literals[j-1]=100*i+10*j+k;
                }
                generateOnlyOnceClauses(literals);
            }
        }

        //each field has only one number
        //row
        for(int j=1; j<=n;j++){
            //each number exactly once
            for(int k=1;k<=n;k++){
                int[] literals = new int[n];
                for(int i=1;i<=n;i++){
                    literals[i-1]=100*i+10*j+k;
                }
                generateOnlyOnceClauses(literals);
            }
        }

        for(int i=0;i<3;i++){
            for(int j=1; j<=3;j++){
                //each number exactly once
                for(int z=1;z<=n;z++){
                    int[] literals = new int[n];
                    int counter=0;
                    for(int k=3*i+1;k<=3*i+3;k++){
                        for(int l=3*j+1;k<=3*j+3;l++){
                            literals[counter]=100*k+10*l+z;
                            counter++;
                        }
                    }
                    generateOnlyOnceClauses(literals);
                }
            }
        }
    }

    private static void generateOnlyOnceClauses(int[] literals) {
        if(literals.length!=9){
            return;
        }
        for(int i=0;i<9;i++){
            if(literals[i]/100 ==0 ||literals[i]/10 < 10 ||literals[i]<100 ){
                return;
            }
        }
        for(int i=-1;i<2;i+=2){
            for(int j=-1;j<2;j+=2){
                for(int k=-1;k<2;k+=2){
                    for(int l=-1;l<2;l+=2){
                        for(int m=-1;m<2;m+=2){
                            for(int n=-1;n<2;n+=2){
                                for(int o=-1;o<2;o+=2){
                                    for(int p=-1;p<2;p+=2){
                                        for(int q=-1;q<2;q+=2){
                                            int[] clause = new int[n];
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
