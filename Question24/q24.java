import java.io.File;
import java.util.*;

class Solution {

    public static int MIN_EXPLOSION_NUMBER = 9;
    public static int END_STEP = 100;

    public static void main(String[] args) {
        try {
            File file = new File("./data.txt");
            Scanner sc = new Scanner(file).useDelimiter("\\n");

            Map<String, List<String>> caveMap = new HashMap<>();

            while (sc.hasNext()) {
                String line = sc.nextLine();
                Scanner inner_sc = new Scanner(line).useDelimiter("-");
                while (inner_sc.hasNext()) {
                    String startCave = inner_sc.next();
                    if (!caveMap.containsKey(startCave)) {
                        caveMap.put(startCave, new ArrayList<>());
                    }
                    String endCave = inner_sc.next();
                    if (!caveMap.containsKey(endCave)) {
                        caveMap.put(endCave, new ArrayList<>());
                    }
                    caveMap.get(startCave).add(endCave);
                    caveMap.get(endCave).add(startCave);
                }
            }
            sc.close();

            int foundPaths = checkPath("start", caveMap, new HashSet<>(), false);
            System.out.println("Found paths = " + foundPaths);
        } catch (
                Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return;
    }

    public static int checkPath(String path, Map<String, List<String>> caveMap, Set<String> visited, boolean visitedTwice) {
        if (path.equals("end")) {
            return 1;
        }
        Set<String> newVisited = new HashSet<>(visited);
        if (path.toLowerCase().equals(path)) {
            newVisited.add(path);
        }
        int sumOfPaths = 0;
        for (String cave : caveMap.get(path)) {
            if (!newVisited.contains(cave)) {
                sumOfPaths += checkPath(cave, caveMap, newVisited, visitedTwice);
            } else if (!cave.equals("start") && !visitedTwice){
                sumOfPaths += checkPath(cave, caveMap, newVisited, true);
            }
        }
        return sumOfPaths;
    }

}