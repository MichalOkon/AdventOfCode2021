import java.util.Scanner;
import java.io.File;
import java.lang.String;

class Solution {
    public static void main(String[] args) {

        int horizontal = 0;
        int vertical = 0;
        int aim = 0;

        try {
            File file = new File("./data.txt");
            Scanner sc = new Scanner(file);

            while (sc.hasNext()) {
                String nextPosition = sc.next();
                int nextValue = sc.nextInt();
                switch (nextPosition) {
                    case "forward":
                        horizontal += nextValue;
                        vertical += aim * nextValue;
                        break;

                    case "up":
                        aim -= nextValue;
                        break;

                    case "down":
                        aim += nextValue;
                        break;

                    default:
                        throw new Exception("Incorrect string in the data file");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Horizontal position: " + horizontal + "\nVertuical position:" + vertical);
        System.out.println("Horizontal*Vertical = " + horizontal * vertical);
        return;
    }
}