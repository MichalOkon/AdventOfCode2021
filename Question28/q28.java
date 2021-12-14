import java.io.File;
import java.util.*;
import java.util.regex.Pattern;

class Solution {

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

            Map<String, Long> pairCount = initializeLine(line);
            Map<String, Long> results = insertRules(rules, pairCount, 40);

            Map<Character, Long> occurences = new HashMap<>();
            occurences.put(line.charAt(line.length()-1), Long.valueOf(1));
            for (String pair : results.keySet()) {
                Character c = pair.charAt(0);
                occurences.put(c, results.get(pair) + (occurences.containsKey(c) ? occurences.get(c) : 0));
            }

            Long min = Long.MAX_VALUE;
            Long max = Long.MIN_VALUE;

            for (Long count : occurences.values()) {
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

    public static Map<String, Long> initializeLine(String line) {
        Map<String, Long> pairCount = new HashMap<>();
        for (int j = 0; j < line.length() - 1; j++) {
            String pair = line.substring(j, j + 2);
            if (!pairCount.containsKey(pair)) {
                pairCount.put(pair, Long.valueOf(1));
            } else {
                pairCount.put(pair, pairCount.get(pair) + 1);
            }
        }
        return pairCount;
    }

    public static Map<String, Long> insertRules(Map<String, String> rules, Map<String, Long> pairCount, int steps) {
        for(int i = 0; i < steps; i++) {
            Map<String, Long> newPairCount = new HashMap<>(pairCount);
            for (String pair : rules.keySet()) {
                if (pairCount.containsKey(pair)) {
                    String firstNewPair = Character.toString(pair.charAt(0)) + rules.get(pair);
                    String secondNewPair = rules.get(pair) + Character.toString(pair.charAt(1));
                    if(firstNewPair.equals(secondNewPair)) {
                        newPairCount.put(firstNewPair, pairCount.get(pair)*2);
                    }
                    else {
                        newPairCount.put(firstNewPair, pairCount.get(pair) + (newPairCount.containsKey(firstNewPair) ? newPairCount.get(firstNewPair) : 0));
                        newPairCount.put(secondNewPair,  pairCount.get(pair) + (newPairCount.containsKey(secondNewPair) ? newPairCount.get(secondNewPair) : 0));

                        newPairCount.put(pair, newPairCount.get(pair) - pairCount.get(pair));
                    }
                }
            }
            pairCount = newPairCount;
        }
        return pairCount;
    }
}