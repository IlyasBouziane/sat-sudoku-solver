package utils;

import org.sat4j.core.VecInt;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.ISolver;

public class Clauses {

    public static void addSudokuClauses(ISolver solver) {
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

    public static void generateClauses(ISolver solver, int[] literals) {
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
