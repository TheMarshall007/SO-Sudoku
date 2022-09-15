import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Validator {
    private int[][] sudoku;
    private int size;
    private int count = 0;
    boolean[] auxValidator;

    public Validator() throws InterruptedException {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter size of sudoku: ");
        size = s.nextInt();
        s.close();
        this.sudoku = new int[size][size];

        loadSudoku("puzzleSolved2.txt");
        printSudoku();
        startValidation();
        startValidation1();
        startValidation2();
    }

    private void startValidation() throws InterruptedException {

        auxValidator = new boolean[11];

        Thread row = new Thread(() -> {
            for (int i = 0; i < size; i++) {
                auxValidator[0] = isRowValid(i);
            }
        });
        Thread col = new Thread(() -> {
            for (int i = 0; i < size; i++) {
                auxValidator[1] = isColValid(i);
            }
        });
        Thread box1 = new Thread(() -> {
            auxValidator[2] = is3x3Valid(0, 0);
        });
        Thread box2 = new Thread(() -> {
            auxValidator[3] = is3x3Valid(0, 3);
        });
        Thread box3 = new Thread(() -> {
            auxValidator[4] = is3x3Valid(0, 6);
        });
        Thread box4 = new Thread(() -> {
            auxValidator[5] = is3x3Valid(3, 0);
        });
        Thread box5 = new Thread(() -> {
            auxValidator[6] = is3x3Valid(3, 3);
        });
        Thread box6 = new Thread(() -> {
            auxValidator[7] = is3x3Valid(3, 6);
        });
        Thread box7 = new Thread(() -> {
            auxValidator[8] = is3x3Valid(6, 0);
        });
        Thread box8 = new Thread(() -> {
            auxValidator[9] = is3x3Valid(6, 3);
        });
        Thread box9 = new Thread(() -> {
            auxValidator[10] = is3x3Valid(6, 6);
        });

        long start = System.currentTimeMillis();
        row.start();
        col.start();
        box1.start();
        box2.start();
        box3.start();
        box4.start();
        box5.start();
        box6.start();
        box7.start();
        box8.start();
        box9.start();

        row.join();
        col.join();
        box1.join();
        box2.join();
        box3.join();
        box4.join();
        box5.join();
        box6.join();
        box7.join();
        box8.join();
        box9.join();

        for (boolean valid : auxValidator) {
            if (!valid) {
                System.out.println("Sudoku validation is invalid!");
                return;
            }
        }
        System.out.println("\nSudoku validation 0 is valid!");
        System.out.println("It took me " + (System.currentTimeMillis() - start) + "ms to validate this puzzle.");
    }

    private void startValidation1() throws InterruptedException {

        auxValidator = new boolean[11];

        Thread row = new Thread(() -> {
            for (int i = 0; i < size; i++) {
                auxValidator[0] = isRowValid(i);
            }
        });
        Thread col = new Thread(() -> {
            for (int i = 0; i < size; i++) {
                auxValidator[1] = isColValid(i);
            }
        });
        Thread box = new Thread(() -> {
            auxValidator[2] = is3x3Valid(0, 0);
            auxValidator[3] = is3x3Valid(0, 3);
            auxValidator[4] = is3x3Valid(0, 6);
            auxValidator[5] = is3x3Valid(3, 0);
            auxValidator[6] = is3x3Valid(3, 3);
            auxValidator[7] = is3x3Valid(3, 6);
            auxValidator[8] = is3x3Valid(6, 0);
            auxValidator[9] = is3x3Valid(6, 3);
            auxValidator[10] = is3x3Valid(6, 6);
        });


        long start = System.currentTimeMillis();
        row.start();
        col.start();
        box.start();

        row.join();
        col.join();
        box.join();

        for (boolean valid : auxValidator) {
            if (!valid) {
                System.out.println("Sudoku validation is invalid!");
                return;
            }
        }
        System.out.println("\nSudoku validation 1 is valid!");
        System.out.println("It took me " + (System.currentTimeMillis() - start) + "ms to validate this puzzle.");
    }

    private void startValidation2() {
        auxValidator = new boolean[11];
        for (int i = 0; i < size; i++) {
            auxValidator[0] = isRowValid(i);
            auxValidator[1] = isColValid(i);
        }
        auxValidator[2] = is3x3Valid(0, 0);
        auxValidator[3] = is3x3Valid(0, 3);
        auxValidator[4] = is3x3Valid(0, 6);
        auxValidator[5] = is3x3Valid(3, 0);
        auxValidator[6] = is3x3Valid(3, 3);
        auxValidator[7] = is3x3Valid(3, 6);
        auxValidator[8] = is3x3Valid(6, 0);
        auxValidator[9] = is3x3Valid(6, 3);
        auxValidator[10] = is3x3Valid(6, 6);
        long start = System.currentTimeMillis();
        for (boolean valid : auxValidator) {
            if (!valid) {
                System.out.println("Sudoku validation is invalid!");
                return;
            }
        }
        System.out.println("\nSudoku validation 2 is valid!");
        System.out.println("It took me " + (System.currentTimeMillis() - start) + "ms to validate this puzzle.");
    }

    //Load the solved game from a file
    private void loadSudoku(String file) {
        try {
            Scanner scan = new Scanner(new File(file));
            if (!scan.hasNextLine()) {
                System.out.println("Empty File. Exiting...");
            }
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    this.sudoku[i][j] = scan.nextInt();
                }
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found. Please create a sudokuzle.txt in the same folder this app is in. Exiting...");
        }
        System.out.println("Sudoku is now loaded.");

    }

    //Valid if the numbers 1-9 appear only once in the row
    private boolean isRowValid(int row) {
        boolean[] validityArray = new boolean[9];
        for (int j = 0; j < size; j++) {
            int num = sudoku[row][j];
            if (num < 1 || num > 9 || validityArray[num - 1]) {
                System.out.println("Element found more than one. Row: " + (row + 1) + " Col: " + (j + 1));
                return false;
            } else if (!validityArray[num - 1]) {
                validityArray[num - 1] = true;
            }
        }
        return true;
    }

    //Valid if the numbers 1-9 appear only once in the column
    private boolean isColValid(int col) {
        boolean[] validityArray = new boolean[9];
        for (int j = 0; j < size; j++) {
            int num = sudoku[j][col];
            if (num < 1 || num > 9 || validityArray[num - 1]) {
                System.out.println("Element found more than one. Row: " + (j + 1) + " Col: " + (col + 1));
                return false;
            } else if (!validityArray[num - 1]) {
                validityArray[num - 1] = true;
            }
        }
        return true;
    }

    //Valid if the numbers 1-9 appear only once in a 3x3 subsection
    private boolean is3x3Valid(int row, int col) {
        boolean[] validityArray = new boolean[9];
        for (int i = row; i < row + 3; i++) {
            for (int j = col; j < col + 3; j++) {
                int num = sudoku[i][j];
                if (num < 1 || num > 9 || validityArray[num - 1]) {
                    System.out.println("Element found more than one. Row: " + (i + 1) + " Col: " + (j + 1));
                    return false;
                } else {
                    validityArray[num - 1] = true;
                }
            }
        }
        return true;
    }

    //Print the matrix
    public void printSudoku() {
        int r = 0;
        int c = 0;
        String repeatedStar = new String(new char[4 * this.sudoku.length + 4]).replace('\0', '-');
        for (int[] i : this.sudoku) {
            if (r % 3 == 0)
                System.out.print(repeatedStar + '\n');
            for (int j : i) {
                if (c % 3 == 0)
                    System.out.print('|');
                if (j < 10)
                    System.out.print("  " + j + " ");
                else
                    System.out.print(" " + j + " ");
                c++;
            }
            r++;
            System.out.print("|");
            System.out.println();
        }
        System.out.print(repeatedStar + '\n');
    }

    public static void main(String[] args) throws InterruptedException {
        new Validator();
    }
}


