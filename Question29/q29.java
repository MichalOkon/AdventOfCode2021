import java.io.File;
import java.util.*;

class Solution {

    public static void main(String[] args) {
        try {
            File file = new File("./data.txt");
            Scanner sc = new Scanner(file);
            List<List<Integer>> grid = new ArrayList<>();
            while (sc.hasNext()) {
                grid.add(new ArrayList<>());
                String line = sc.nextLine();
                Scanner inner_sc = new Scanner(line).useDelimiter("");
                while (inner_sc.hasNextInt()) {
                    grid.get(grid.size() - 1).add(inner_sc.nextInt());
                }
            }
            sc.close();

            int columns = grid.get(0).size();
            int rows = grid.size();
            PriorityQueue<Point> priorityQueue = new PriorityQueue<>();
            Point current = new Point(0, 0, grid.get(0).get(0), 0);
            Set<Point> visited = new HashSet<>();
            while (current.x != columns - 1 || current.y != rows - 1) {
                if (!visited.contains(current)) {
                    visited.add(current);
                    if (current.x != 0) {
                        int newLeftValue = grid.get(current.y).get(current.x - 1);
                        priorityQueue.add(new Point(current.x - 1, current.y, newLeftValue, newLeftValue + current.shortestPath));
                    }
                    if (current.x != columns - 1) {
                        int newRightValue = grid.get(current.y).get(current.x + 1);
                        priorityQueue.add(new Point(current.x + 1, current.y, newRightValue, newRightValue + current.shortestPath));
                    }
                    if (current.y != 0) {
                        int newTopValue = grid.get(current.y - 1).get(current.x);
                        priorityQueue.add(new Point(current.x, current.y - 1, newTopValue, newTopValue + current.shortestPath));
                    }
                    if (current.y != rows - 1) {
                        int newBotValue = grid.get(current.y + 1).get(current.x);
                        priorityQueue.add(new Point(current.x, current.y + 1, newBotValue, newBotValue + current.shortestPath));
                    }
                }
                current = priorityQueue.poll();
            }
            System.out.println("The minimal path is " + current.shortestPath);

        } catch (
                Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return;
    }

    public static class Point implements Comparable<Point>{
        int x;
        int y;
        int shortestPath;
        int value;

        Point(int x, int y, int value, int shortestPath) {
            this.x = x;
            this.y = y;
            this.shortestPath = shortestPath;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y && value == point.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, value);
        }

        @Override
        public int compareTo(Point other) {
            return this.shortestPath - other.shortestPath;
        }
    }
}