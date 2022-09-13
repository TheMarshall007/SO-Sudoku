

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
        boxCol = boxRow;
        s.close();
        this.sudoku = new int[size][size];
    }

    //Get the size value from the matrix
    public int getSize() {
        return this.sudoku.length;
    }

    //Set the value of the element that is in row x col in matrix
    public void setValue(int row, int col, int value) {
        this.sudoku[row][col] = value;
    }

    //Get the value of the element that is in row x col in matrix
    public int getValue(int row, int col) {
        return this.sudoku[row][col];
    }

    //Get the total value from the box row
    public int getBoxRow() {
        return boxRow;
    }

    //Get the total value from the box column
    public int getBoxCol() {
        return boxCol;
    }

    //Load the game from a file
    public boolean loadSudoku(String file) {
        try {
            Scanner scan = new Scanner(new File(file));

            if (!scan.hasNextLine()) {
                System.out.println("Empty File. Exiting...");
                return false;
            }

            for (int i = 0; i < this.sudoku.length; i++) {
                for (int j = 0; j < this.sudoku[i].length; j++) {
                    this.sudoku[i][j] = scan.nextInt();
                }
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found. Please create a sudokuzle.txt in the same folder this app is in. Exiting...");
        }
        System.out.println("Sudoku is now loaded.");
        return true;
    }

    //Save the solved game to a file

    public void saveSudoku(String file) {
        try {
            FileWriter f = new FileWriter(file);
            int r = 0;
            int c = 0;
            String content = "";
            for (int[] i : this.sudoku) {
                for (int j : i) {
                    if (j < 10) content = content + j + " ";
                    else content = content.concat(j + " ");
                    c++;
                }
                r++;
                content = content + "\n";
            }
            f.write(content);
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Print the matrix
    public void printSudoku() {
        int r = 0;
        int c = 0;
        String repeatedStar = new String(new char[4 * this.sudoku.length + 4]).replace('\0', '-');
        for (int[] i : this.sudoku) {
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
