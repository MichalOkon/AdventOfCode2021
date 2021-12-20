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

            int[][] expandedGrid = new int[grid.size()*5][grid.size()*5];
            for(int i = 0; i < grid.size(); i++) {
                for (int j = 0; j < grid.get(0).size(); j++) {
                    for(int k = 0; k < 5; k++) {
                        for(int l = 0; l < 5; l++) {
                            int gridValue = grid.get(i).get(j) + k + l;
                            if(gridValue > 9) {
                                gridValue = gridValue%10 + 1;
                            }
                            expandedGrid[i + k * grid.size()][j + l * grid.get(0).size()] = gridValue;
                        }
                    }
                }
            }

            int columns = expandedGrid[0].length;
            int rows = expandedGrid.length;
            PriorityQueue<Point> priorityQueue = new PriorityQueue<>();
            Point current = new Point(0, 0, expandedGrid[0][0], 0);
            Set<Point> visited = new HashSet<>();
            while (current.x != columns - 1 || current.y != rows - 1) {
                //System.out.println("Current:" + current.x + " " + current.y);
                if (!visited.contains(current)) {
                    visited.add(current);
                    if (current.x != 0) {
                        int newLeftValue = expandedGrid[current.y][current.x-1];
                        priorityQueue.add(new Point(current.x - 1, current.y, newLeftValue, newLeftValue + current.shortestPath));
                    }
                    if (current.x != columns - 1) {
                        int newRightValue = expandedGrid[current.y][current.x+1];
                        priorityQueue.add(new Point(current.x + 1, current.y, newRightValue, newRightValue + current.shortestPath));
                    }
                    if (current.y != 0) {
                        int newTopValue = expandedGrid[current.y-1][current.x];
                        priorityQueue.add(new Point(current.x, current.y - 1, newTopValue, newTopValue + current.shortestPath));
                    }
                    if (current.y != rows - 1) {
                        int newBotValue = expandedGrid[current.y+1][current.x];
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