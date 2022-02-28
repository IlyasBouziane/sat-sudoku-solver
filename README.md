# sat-sudoku-solver

<!-- ABOUT THE PROJECT -->
## About The Project

This is a Java application for handling Sudokus using SAT methods. it allows to pick one of 3 applications :
* SAT Solving : considered the Sudoku puzzle as a SAT problem, and solved it based on CNF using a SAT solver (MiniSat is used here)
* Backtracking : implemented a solving algorithm through backtracking (DPLL), and focused on picking a good heuristic related to variables’ assignments.
* Sudoku Generator : the improved backtracking algorithm is used and the level of the Sudoku chosen is associated with a certain number of empty cells.
