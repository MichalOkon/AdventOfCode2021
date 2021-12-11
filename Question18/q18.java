import java.io.File;
import java.util.*;
import java.util.Collections.*;


class Solution {

    public static void main(String[] args) {
        try {
            File file = new File("./data.txt");
            Scanner sc = new Scanner(file).useDelimiter("\\n");

            List<List<Point>> grid = new ArrayList<>();
            HashSet<Point> remainingElements = new HashSet<Point>();
            int x = 0;
            while (sc.hasNext()) {
                int y = 0;
                String line = sc.nextLine();
                System.out.println(line);
                Scanner inner_sc = new Scanner(line).useDelimiter("");
                List<Point> row = new ArrayList<>();
                while (inner_sc.hasNextInt()) {

                    Point point = new Point(x, y, inner_sc.nextInt());
                    row.add(point);
                    remainingElements.add(point);
                    y++;
                }
                grid.add(row);
                x++;
            }
            sc.close();

            Iterator<Point> pointIterator = remainingElements.iterator();
            PriorityQueue<Integer> basins = new PriorityQueue<>(100, Collections.reverseOrder());
            while (remainingElements.size() != 0) {
                basins.add(checkPoint(remainingElements.iterator().next(), grid, remainingElements));

            }

            int topSum = 1;
            for(int i = 0; i < 3; i++) {
                topSum *= basins.poll();
            }
            System.out.println("The sum of lowpoint elements is " + topSum);
        } catch (
                Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return;
    }

    public static int checkPoint(Point point, List<List<Point>> grid, HashSet<Point> remainingPoints) {
        if(!remainingPoints.contains(point)) {
            return 0;
        }
        remainingPoints.remove(point);
        if (point.value== 9) {
            return 0;
        }
        int sum = 0;
        if (point.x != 0) {
            sum +=checkPoint(grid.get(point.x-1).get(point.y), grid, remainingPoints);
        }
        if (point.y != 0) {
            sum +=checkPoint(grid.get(point.x).get(point.y-1), grid, remainingPoints);
        }
        if (point.x != grid.size()-1) {
            sum +=checkPoint(grid.get(point.x+1).get(point.y), grid, remainingPoints);
        }
        if (point.y != grid.get(0).size()-1) {
            sum +=checkPoint(grid.get(point.x).get(point.y+1), grid, remainingPoints);
        }
        System.out.println(sum);
        return sum+1;
    }

    public static class Point {
        public int x;
        public int y;
        public int value;

        Point(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }
}

