import java.util.Scanner;
import java.io.File;

class Solution {
    public static void main(String[] args) {

        int old_depth = 0;
        int new_depth = 0;
        int increase_count = 0;

        try {
            File file = new File("./data.txt");
            Scanner sc = new Scanner(file);

            old_depth = sc.nextInt();
            while (sc.hasNextInt()) {
                new_depth = sc.nextInt();
                if (new_depth > old_depth) {
                    increase_count++;
                }
                old_depth = new_depth;
            }
            System.out.println("The depth increased: " + increase_count + " times.");

        } catch (Exception e) {
            System.out.println("Cannot open the data file");
            e.printStackTrace();
        }
        return;
    }
}
