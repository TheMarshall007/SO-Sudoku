
public class Solver {
    private Sudoku sud;
    boolean rowValid = true;
    boolean colValid = true;

    public Solver() {
        sud = new Sudoku();
    }

    //Recursively control which row and column should be valid
    public boolean solve(int row, int col) {
        if (col == sud.getSize()) {
            col = 0;
            row += 1;
            if (row == sud.getSize()) {
                return true;
            }
        }

        for (int num = 1; num < sud.getSize() + 1; num++) {
            if (sud.getValue(row, col) == 0) {
                if (isValid(row, col, num)) {
                    sud.setValue(row, col, num);
                    if (solve(row, col + 1)) {
                        return true;
                    } else {
                        sud.setValue(row, col, 0);
                    }
                }
            } else
                return solve(row, col + 1);
        }
        return false;
    }

    //Controls validations
    public boolean isValid(int row, int col, int num) {

        if (!isRowValid(row, num)) return false;

        if (!isColValid(col, num)) return false;

        if (!is3x3Valid(row, col, num)) return false;

        return true;
    }

    //Valid if the number has already been placed on the row
    private boolean isRowValid(int row, int num) {
        for (int j = 0; j < sud.getSize(); j++) {
            if (sud.getValue(row, j) == num) {
                return false;
            }
        }
        return true;
    }

    //Valid if the number has already been placed on the column
    private boolean isColValid(int col, int num) {
        for (int r = 0; r < sud.getSize(); r++) {
            if (sud.getValue(r, col) == num) {
                return false;
            }
        }
        return true;
    }

    //Valid if the number has already been placed on the 3x3 subsection
    private boolean is3x3Valid(int row, int col, int num) {
        int topRightRow = (row / sud.getBoxRow()) * sud.getBoxRow();
        int topRightCol = (col / sud.getBoxCol()) * sud.getBoxCol();
        for (int i = 0; i < sud.getBoxRow(); i++) {
            for (int j = 0; j < sud.getBoxCol(); j++) {
                if (sud.getValue(topRightRow + i, topRightCol + j) == num) {
                    return false;
                }
            }
        }
        return true;
    }

    //Load the game from a file
    public boolean loadSudoku(String file) {
        return sud.loadSudoku(file);
    }

    //Save the solved game to a file
    public void saveSudoku(String file) {
        sud.saveSudoku(file);
    }

    //Print the matrix
    public void printSudoku() {
        sud.printSudoku();
    }

}
