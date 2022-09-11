

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sudoku {
    private int[][] sudoku;
    private int size;
    private int boxRow;
    private int boxCol;

    public Sudoku() {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter size of sudoku: ");
        size = s.nextInt();
        System.out.print("Enter # rows of inner box: ");
        boxRow = s.nextInt();
        System.out.print("Enter # columns of inner box: ");
        boxCol = s.nextInt();
        s.close();
        sudoku = new int[size][size];
    }

    public int getSize() {
        return sudoku.length;
    }

    public void setValue(int row, int col, int value){
        this.sudoku[row][col] = value;
    }

    public int getValue(int row, int col){
        return sudoku[row][col];
    }

    public int getBoxRow() {
        return boxRow;
    }

    public int getBoxCol() {
        return boxCol;
    }

    public boolean loadSudoku(String file) {
        try {
            Scanner scan = new Scanner(new File(file));

            if (!scan.hasNextLine()) {
                System.out.println("Empty File. Exiting...");
                return false;
            }

            for (int i = 0; i < sudoku.length; i++) {
                for (int j = 0; j < sudoku[i].length; j++) {
                    sudoku[i][j] = scan.nextInt();
                }
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found. Please create a sudokuzle.txt in the same folder this app is in. Exiting...");
        }
        System.out.println("Sudoku is now loaded.");
        return true;
    }

    public void printSudoku() {
        int r = 0;
        int c = 0;
        String repeatedStar = new String(new char[4 * sudoku.length + 4]).replace('\0', '-');
        for (int[] i : sudoku) {
            if (r % boxRow == 0)
                System.out.print(repeatedStar + '\n');
            for (int j : i) {
                if (c % boxCol == 0)
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
}
