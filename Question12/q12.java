import java.io.File;
import java.util.PriorityQueue;
import java.util.Scanner;

class Solution {
    public static final int START_DAYS = 256;
    public static final int FRESH_TIMER = 9;
    public static final int OLD_TIMER = 7;

    public static void main(String[] args) {
        try {
            File file = new File("./data.txt");
            Scanner sc = new Scanner(file).useDelimiter("(,)|\\n");

            long[] reproductionArray = new long[FRESH_TIMER];
            while (sc.hasNextInt()) {
                reproductionArray[sc.nextInt()]++;
            }
            sc.close();

            for (int i = 0; i < START_DAYS; i++) {
                long numberReproducing = reproductionArray[i % FRESH_TIMER];
                reproductionArray[(i+OLD_TIMER)%FRESH_TIMER] += numberReproducing;
            }

            long totalSum = 0;
            for(int i = 0; i < FRESH_TIMER; i++) {
                totalSum += reproductionArray[i];
            }

            System.out.println("At the end, there will be " + totalSum + " fish.");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return;
    }
}