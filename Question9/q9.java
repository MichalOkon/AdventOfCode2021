import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

class Solution {
    public static final int BINGO_LENGTH = 5;

    public static void main(String[] args) {
        try {
            File file = new File("./data.txt");
            Scanner sc = new Scanner(file).useDelimiter(",|( -> )|\\n");

            // We assume that the size of the grid is not known
            // We scan the input and find the maximum element
            ArrayList<Integer> inputArray = new ArrayList<>();
            int maxPoint = 0;
            while (sc.hasNextInt()) {
                int nextPoint = sc.nextInt();
                inputArray.add(nextPoint);
                if (maxPoint < nextPoint) {
                    maxPoint = nextPoint;
                }
            }
            sc.close();

            int[][] grid = new int[maxPoint + 1][maxPoint + 1];
            int overlaps = fillGrid(inputArray, grid);

            System.out.println("Lines overlap at least two times at " + overlaps + " points.");
        } catch (
                Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return;
    }

    public static int fillGrid(ArrayList<Integer> inputArray, int[][] grid) {
        // Fills the grid with the elements from the inputArray and returns the number of overlapping elements
        int overlaps = 0;
        for (int i = 0; i < inputArray.size(); i += 4) {
            int x1 = inputArray.get(i);
            int y1 = inputArray.get(i + 1);
            int x2 = inputArray.get(i + 2);
            int y2 = inputArray.get(i + 3);

            if (x1 == x2) {
                overlaps += insertVerticalLine(grid, y1, y2, x1);
            } else if (y1 == y2) {
                overlaps += insertHorizontalLine(grid, x1, x2, y1);
            }
        }
        return overlaps;
    }

    public static int insertHorizontalLine(int[][] grid, int x1, int x2, int y) {
        int newOverlaps = 0;

        for (int i = Math.min(x1, x2); i <= Math.max(x1, x2); i++) {
            grid[y][i]++;
            if (grid[y][i] == 2) {
                newOverlaps++;
            }
        }
        return newOverlaps;
    }

    public static int insertVerticalLine(int[][] grid, int y1, int y2, int x) {
        int newOverlaps = 0;

        for (int i = Math.min(y1, y2); i <= Math.max(y1, y2); i++) {
            grid[i][x]++;
            if (grid[i][x] == 2) {
                newOverlaps++;
            }
        }
        return newOverlaps;
    }

    public static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println("");
        }
        System.out.println("\n");
    }
}