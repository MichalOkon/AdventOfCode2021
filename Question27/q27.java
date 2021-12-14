import java.io.File;
import java.util.*;
import java.util.regex.Pattern;

class Solution {
    // This is not the most efficient solution. The better one is show in the answer to question 28.

    public static void main(String[] args) {
        try {
            File file = new File("./data.txt");
            Scanner sc = new Scanner(file);
            String line = sc.next();
            sc.skip(Pattern.compile("(\\W)*"));
            sc.useDelimiter("\\W");
            Map<String, String> rules = new HashMap<>();
            while (sc.hasNext()) {
                String letters = sc.next();
                sc.skip(" -> ");
                String insertion = sc.next();
                rules.put(letters, insertion);
            }
            sc.close();

            String result = insertRules(rules, line, 40);

            Map<Character, Integer> occurences = new HashMap<>();

            System.out.println(result);
            for (Character c : result.toCharArray()) {
                if (!occurences.containsKey(c)) {
                    occurences.put(c, 1);
                } else {
                    System.out.println(occurences.get(c ) + 1);
                    occurences.put(c, occurences.get(c) + 1);
                }
            }

            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            for (int count : occurences.values()) {
                //System.out.println(count);
                if (count < min) {
                    min = count;
                }
                if (count > max) {
                    max = count;
                }
            }

            System.out.println("The result is " + (max - min));

        } catch (
                Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return;
    }

    public static String insertRules(Map<String, String> rules, String line, int steps) {
        for (int i = 0; i < steps; i++) {
            System.out.println(i);
            String newLine = Character.toString(line.charAt(0));
            for (int j = 0; j < line.length() - 1; j++) {
                String pair = line.substring(j, j + 2);
                if (rules.containsKey(pair)) {
                    newLine += rules.get(pair) + Character.toString(pair.charAt(1));
                } else {
                    newLine += Character.toString(pair.charAt(1));
                }
            }
            line = newLine;
        }
        return line;
    }
}