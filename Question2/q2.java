import java.util.Scanner;
import java.io.File;

class Solution {
    public static void main(String[] args) {

        int[] depths = new int[3]; // Thre last depths are stored in this array
        int old_depth = 0;
        int new_depth = 0;
        int increase_count = 0;

        try {
            File file = new File("./data.txt");
            Scanner sc = new Scanner(file);

            // initialize depths with 3 first values
            depths[0] = sc.nextInt();
            depths[1] = sc.nextInt();
            depths[2] = sc.nextInt();
            // calculate first sum of depths
            int sum = depths[0] + depths[1] + depths[2];
            int old_sum = 0;
            int i = 0;

            while (sc.hasNextInt()) {
                // save old sum
                old_sum = sum;
                // subtract old depth from sum
                sum -= depths[i % 3];
                // save new depth
                depths[i % 3] = sc.nextInt();
                // calculate new sum of depths
                sum += depths[i % 3];
                if (sum > old_sum) {
                    increase_count++;
                }
                i++;
            }
            System.out.println("The depth increased: " + increase_count + " times.");

        } catch (Exception e) {
            System.out.println("Cannot open the data file");
            e.printStackTrace();
        }
        return;
    }
}
