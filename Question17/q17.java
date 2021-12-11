import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

class Solution {

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

            int lowPointsSum = 0;
            int width = grid.get(0).size();
            int height = grid.size();
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    int element = grid.get(i).get(j);
                    if (i != 0 && element >= grid.get(i - 1).get(j)) {
                        continue;
                    }
                    if (j != 0 && element >= grid.get(i).get(j - 1)) {
                        continue;
                    }
                    if (i != height-1 && element >= grid.get(i + 1).get(j)) {
                        continue;
                    }
                    if (j != width-1 && element >= grid.get(i).get(j+1)) {
                        continue;
                    }
                    lowPointsSum += element + 1;
                }
            }

            System.out.println("The sum of lowpoint elements is " + lowPointsSum);
        } catch (
                Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return;
    }
}