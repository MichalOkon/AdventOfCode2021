import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

class Solution {
    public static final int START_DAYS = 256;
    public static final int FRESH_TIMER = 9;
    public static final int OLD_TIMER = 7;

    public static void main(String[] args) {
        try {
            File file = new File("./data.txt");
            Scanner sc = new Scanner(file).useDelimiter(",|\\n");

            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            ArrayList<Integer> positions = new ArrayList<>();
            while (sc.hasNextInt()) {
                int nextPosition = sc.nextInt();
                if (nextPosition > max) {
                    max = nextPosition;
                }
                if (nextPosition < min) {
                    min = nextPosition;
                }
                positions.add(nextPosition);
            }
            sc.close();
            
            int minimum = findMinimum(positions, min, max);
            System.out.println("The minimum distance is " + minimum);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return;
    }

    public static int findMinimum(ArrayList<Integer> positions, int from, int to) {

        int min = from;
        int max = to;

        while (max - min > 1) {
            int middle = (max + min) / 2;
            int leftValue = calculateDistance(positions, middle - 1);
            int middleValue = calculateDistance(positions, middle);
            int rightValue = calculateDistance(positions, middle + 1);

            if (leftValue < middleValue) {
                max = middle - 1;
            } else if(rightValue < middleValue) {
                min = middle + 1;
            } else {
                return middleValue;
            }
        }

        return Math.min(calculateDistance(positions, min), calculateDistance(positions, max));
    }

    public static int calculateDistance(ArrayList<Integer> positions, int point) {
        int distance = 0;
        for (Integer position : positions) {
            distance += Math.abs(position - point);
        }
        return distance;
    }
}