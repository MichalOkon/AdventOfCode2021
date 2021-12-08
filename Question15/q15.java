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
            Scanner sc = new Scanner(file).useDelimiter("( \\| )|(\\n)");

            ArrayList<String[]> inputCodes = new ArrayList<>();
            ArrayList<String[]> outputCodes = new ArrayList<>();
            while (sc.hasNext()) {
                String[] inputCode = sc.next().split("\\W");
                String[] outputCode = sc.next().split("\\W");
                inputCodes.add(inputCode);
                outputCodes.add(outputCode);
                System.out.println(inputCode[0]);
            }
            sc.close();

            int count = countValues(outputCodes);
            System.out.println("The number of digits 1, 4, 7, 8 is " + count);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return;
    }

    public static int countValues(ArrayList<String[]> outputCodes) {

        int count = 0;

        for (String[] outputCode : outputCodes) {
            for(String number : outputCode) {
                System.out.println(number);
                int length = number.length();
                if(length == 2 || length == 3 || length == 4 || length == 7) {
                    count++;
                }
            }
        }
        return count;
    }
}