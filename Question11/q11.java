import java.io.File;
import java.util.Scanner;

class Solution {
    //
    // This solution uses recursion, much more efficient solution is present in the answer to the next question!
    //

    public static final int START_DAYS = 80;
    public static final int FRESH_TIMER = 9;
    public static final int OLD_TIMER = 7;

    public static void main(String[] args) {
        try {
            File file = new File("./data.txt");
            Scanner sc = new Scanner(file).useDelimiter("(,)|\\n");

            int count = 0;
            while (sc.hasNextInt()) {
                count += countFish(sc.nextInt(), START_DAYS);
            }
            sc.close();
            System.out.println("At the end, there will be " + count + " fish.");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return;
    }

    public static int countFish(int daysToReproduce, int daysLeft) {
        if (daysToReproduce >= daysLeft) {
            return 1;
        }
        return countFish(FRESH_TIMER, daysLeft - daysToReproduce) + countFish(OLD_TIMER, daysLeft - daysToReproduce);
    }
}