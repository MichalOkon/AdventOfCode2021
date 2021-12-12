import java.io.File;
import java.util.*;

class Solution {

    public static int MIN_EXPLOSION_NUMBER = 9;
    public static int END_STEP = 100;

    public static void main(String[] args) {
        try {
            File file = new File("./data.txt");
            Scanner sc = new Scanner(file).useDelimiter("\\n");

            List<List<Integer>> grid = new ArrayList<>();
            while (sc.hasNext()) {
                String line = sc.nextLine();
                Scanner inner_sc = new Scanner(line).useDelimiter("");
                List<Integer> row = new ArrayList<>();
                while (inner_sc.hasNextInt()) {
                    row.add(inner_sc.nextInt());
                }
                grid.add(row);
            }
            sc.close();

            boolean allFlashed = false;
            int step = 0;
            while(!allFlashed){
                step++;
                boolean[][] flashed = new boolean[grid.size()][grid.get(0).size()];
                for (int i = 0; i < grid.size(); i++) {
                    for (int j = 0; j < grid.get(0).size(); j++) {
                        flash(i, j, grid, flashed);
                    }
                }
                allFlashed = verifyAllFlashed(flashed);
            }

            System.out.println("All octopuses are flashed after " + step + " steps");
        } catch (
                Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return;
    }

    public static int flash(int row, int column, List<List<Integer>> grid, boolean[][] flashed) {
        int value = grid.get(row).get(column);
        if (flashed[row][column]) {
            return 0;
        }
        if (++value > MIN_EXPLOSION_NUMBER) {
            int flashCount = 1;
            grid.get(row).set(column, 0);
            flashed[row][column] = true;

            if (row != 0) {
                flashCount += flash(row - 1, column, grid, flashed);
                if (column != 0) {
                    flashCount += flash(row - 1, column - 1, grid, flashed);
                }
                if (column != grid.get(0).size() - 1) {
                    flashCount += flash(row - 1, column + 1, grid, flashed);
                }
            }
            if (column != 0) {
                flashCount += flash(row, column - 1, grid, flashed);
            }
            if (column != grid.get(0).size() - 1) {
                flashCount += flash(row, column + 1, grid, flashed);
            }
            if (row != grid.size() - 1) {
                flashCount += flash(row + 1, column, grid, flashed);
                if (column != 0) {
                    flashCount += flash(row + 1, column - 1, grid, flashed);
                }
                if (column != grid.get(0).size() - 1) {
                    flashCount += flash(row + 1, column + 1, grid, flashed);
                }
            }
            grid.get(row).set(column, 0);
            return flashCount;
        } else {
            grid.get(row).set(column, value);
            return 0;
        }
    }


    public static boolean verifyAllFlashed(boolean[][] flashed) {
        for(boolean[] row : flashed) {
            for (boolean element : row) {
                if(!element) {
                    return false;
                }
            }
        }
        return true;
    }

}