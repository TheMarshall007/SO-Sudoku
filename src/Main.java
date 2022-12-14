public class Main {
    public static void main(String[] args) {
        Solver solver = new Solver();
        String file ="puzzle2.txt";
        String fileSave= "puzzleSolved2.txt";
        if (!solver.loadSudoku(file)) {
            return;
        }
        long start = System.currentTimeMillis();
        if (solver.solve(0, 0)) {
            System.out.println("Solution found!\n");
            solver.printSudoku();
            solver.saveSudoku(fileSave);
            System.out.println("\nIt took me " + (System.currentTimeMillis() - start) + "ms to solve this puzzle.");
        } else {
            solver.saveSudoku(fileSave);
            System.out.println("No Solution Found!");
        }

    }


}
