import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Solution {
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
            int inputSize = inputCodes.size();

            int result = 0;
            for (int i = 0; i < inputSize; i++) {
                // wiresMap maps segments real positions to their position in the input
                Map<Character, Character> wiresMap = getWiresMap(inputCodes.get(i));
                result += translateOutput(outputCodes.get(i), wiresMap);
            }

            System.out.println("The sum of all digits is " + result);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return;
    }

    public static Map<Character, Character> getWiresMap(String[] digits) {
        Map<Character, Integer> frequencyMap =  new HashMap<>();
        frequencyMap.put('a', 0);
        frequencyMap.put('b', 0);
        frequencyMap.put('c', 0);
        frequencyMap.put('d', 0);
        frequencyMap.put('e', 0);
        frequencyMap.put('f', 0);
        frequencyMap.put('g', 0);

        // Those two digits will be used to found the values of segments d and a respectively
        String digitFour = "";
        String digitOne = "";
        for (String digit : digits) {
            if (digit.length() == 4) {
                digitFour = digit;
            } else if (digit.length() == 2) {
                digitOne = digit;
            }
            for (Character segment : digit.toCharArray()) {
                frequencyMap.put(segment, frequencyMap.get(segment) + 1);
            }
        }
        return decipher(frequencyMap, digitOne, digitFour);
    }

    public static Map<Character, Character> decipher(Map<Character, Integer> frequencyMap, String digitOne, String digitFour) {
        Map<Character, Character> decipheredMap = new HashMap<>();
        for (Character segment : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(segment);
            if (frequency == 4) {
                decipheredMap.put('e', segment);
            } else if (frequency == 6) {
                decipheredMap.put('b', segment);
            } else if (frequency == 7) {
                if (digitFour.indexOf(segment) != -1) {
                    decipheredMap.put('d', segment);
                } else {
                    decipheredMap.put('g', segment);
                }
            } else if (frequency == 8) {
                if (digitOne.indexOf(segment) != -1) {
                    decipheredMap.put('c', segment);
                } else {
                    decipheredMap.put('a', segment);
                }
            } else if (frequency == 9) {
                decipheredMap.put('f', segment);
            }
        }
        return decipheredMap;
    }

    public static int translateOutput(String[] outputDigits, Map<Character, Character> wiresMap) {
        String result = "";
        for (String digit : outputDigits) {
            int length = digit.length();
            if (length == 2) {
                result += "1";
            } else if (length == 3) {
                result += "7";
            } else if (length == 4) {
                result += "4";
            } else if (length == 5) {
                if (digit.indexOf(wiresMap.get('f')) == -1) {
                    result += "2";
                } else if (digit.indexOf(wiresMap.get('b')) == -1) {
                    result += "3";
                } else {
                    result += "5";
                }
            } else if (length == 6) {
                if (digit.indexOf(wiresMap.get('d')) == -1) {
                    result += "0";
                } else if (digit.indexOf(wiresMap.get('c')) == -1) {
                    result += "6";
                } else {
                    result += "9";
                }
            } else if (length == 7) {
                result += "8";
            }
        }
        return Integer.parseInt(result);
    }
}