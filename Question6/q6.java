import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.lang.String;

class Solution {
    public static void main(String[] args) {
        try {
            File file = new File("./data.txt");
            Scanner sc = new Scanner(file);

            // Store all binary numbers in one array
            ArrayList<String> numList = new ArrayList<>();
            while (sc.hasNext()) {
                numList.add(sc.next());
            }

            String oxygenRating = findFilter(numList, 0, true);
            int oxygenResult = Integer.parseUnsignedInt(oxygenRating, 2);
            String CO2Rating = findFilter(numList, 0, false);
            int CO2Result = Integer.parseUnsignedInt(CO2Rating, 2);

            System.out.println("Oxygen generator rating: " + oxygenResult + ", CO2 generator rating " + CO2Result +
                    "\nLife support rating: " + oxygenResult * CO2Result);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return;
    }

    public static char findMostCommon(ArrayList<String> numbers, int position) {
        int count = 0;
        for (String number : numbers) {
            if (number.charAt(position) == '0') {
                // Add 0 from the i'th element of binNum if the character i is 0
                count--;
            } else {
                // Add 1 to the i'th element of binNum if the character i is 1
                count++;
            }
        }
        if (count == numbers.size() || count == -numbers.size()) {
            return '2';
        } else if (count >= 0) {
            return '1';
        } else {
            return '0';
        }
    }

    public static String findFilter(ArrayList<String> numbers, int position, boolean oxygen) {

        // Define the most common number at the position
        char filterNum = findMostCommon(numbers, position);
        // If there is only one uniqe character at the position, propagate to the next position
        if (filterNum == '2') {
            return findFilter(numbers, position + 1, oxygen);
        }

        ArrayList<String> filteredNumbers = new ArrayList<>();
        // Add numbers with the most common number at the position to the new array
        if (oxygen) {
            for (String binNum : numbers) {
                if (binNum.charAt(position) == filterNum) {
                    filteredNumbers.add(binNum);
                }
            }
        }
        // Add numbers without the most common number at the position to the new array
        else {
            for (String binNum : numbers) {
                if (binNum.charAt(position) != filterNum) {
                    filteredNumbers.add(binNum);
                }
            }
        }

        // Stop when only one number remains
        if (filteredNumbers.size() == 1) {
            return filteredNumbers.get(0);
        }
        return findFilter(filteredNumbers, position + 1, oxygen);
    }

}