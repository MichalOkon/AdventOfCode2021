import java.util.Scanner;
import java.io.File;
import java.lang.String;

class Solution {
    public static void main(String[] args) {
        try {
            File file = new File("./data.txt");
            Scanner sc = new Scanner(file);

            // Identify the length of each string
            String binNum = sc.next();
            int len = binNum.length();
            // numCount hold the difference between the number of occurances of 0s and 1s in binary numbers
            int[] numCount = new int[len];

            // Start counting
            countNumbers(numCount, binNum);
            while (sc.hasNext()) {
                countNumbers(numCount, sc.next());
            }

            // Put the final results together
            String resultGamma = "";
            String resultEpsilon = "";
            for (int el : numCount) {
                if (el > 0) {
                    resultGamma += "1";
                    resultEpsilon += "0";
                } else {
                    resultGamma += "0";
                    resultEpsilon += "1";
                }
            }
            int gamma = Integer.parseUnsignedInt(resultGamma, 2);
            int epsilon = Integer.parseUnsignedInt(resultEpsilon, 2);
            System.out.println("gamma: " + gamma + ", epsilon: " + epsilon + ", power consumption: " + gamma * epsilon);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return;
    }

    public static void countNumbers(int[] numCount, String binNum) {
        for (int i = 0; i < numCount.length; i++) {
            if (binNum.charAt(i) == '0') {
                // Add 0 from the i'th element of binNum if the character i is 0
                numCount[i]--;
            } else {
                // Add 1 to the i'th element of binNum if the character i is 1
                numCount[i]++;
            }
        }
    }
}