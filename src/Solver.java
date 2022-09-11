
public class Solver {
    private Sudoku sud;

    public Solver() {
        sud = new Sudoku();
    }

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

    public boolean isValid(int row, int col, int num) {

        if (!isRowValid(row, num)) return false;

        if (!isColValid(col, num)) return false;

        if (!isBoxValid(row, col, num)) return false;

        return true;
    }

    private boolean isRowValid(int row, int num) {
        for (int j = 0; j < sud.getSize(); j++) {
            if (sud.getValue(row, j) == num) {
                return false;
            }
        }
        return true;
    }

    private boolean isColValid(int col, int num) {
        for (int r = 0; r < sud.getSize(); r++) {
            if (sud.getValue(r, col) == num) {
                return false;
            }
        }
        return true;
    }

    private boolean isBoxValid(int row, int col, int num) {
        int topRightRow = (row / sud.getBoxRow()) * sud.getBoxRow();
        int topRightCol = (col / sud.getBoxCol()) * sud.getBoxCol();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (sud.getValue(topRightRow + i, topRightCol + j) == num) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean loadSudoku(String file) {
        return sud.loadSudoku(file);
    }

    public void saveSudoku(String file) {
        sud.saveSudoku(file);
    }

    public void printSudoku() {
        sud.printSudoku();
    }
}
