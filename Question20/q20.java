import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Stack;
import java.util.Scanner;
import java.lang.Integer;


class Solution {

    public static void main(String[] args) {
        try {
            File file = new File("./data.txt");
            Scanner sc = new Scanner(file).useDelimiter("\\n");
            ArrayList<Long> scores = new ArrayList<>();
            while (sc.hasNext()) {
                String[] brackets = sc.nextLine().split("");
                checkLine(brackets, scores);
            }
            sc.close();

            Long[] scoresArray = scores.toArray(new Long[0]);
            Arrays.sort(scoresArray);

            System.out.println("The middle score is " + scoresArray[scoresArray.length / 2]);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return;
    }

    public static void checkLine(String[] brackets, List<Long> scores) {
        Stack<String> controlStack = new Stack<>();
        for (String bracket : brackets) {
            if (bracket.equals("(") || bracket.equals("[") || bracket.equals("{") || bracket.equals("<")) {
                controlStack.push(bracket);
            } else if (bracket.equals(")")) {
                if (!controlStack.pop().equals("(")) {
                    return;
                }
            } else if (bracket.equals("]")) {
                if (!controlStack.pop().equals("[")) {
                    return;
                }
            } else if (bracket.equals("}")) {
                if (!controlStack.pop().equals("{")) {
                    return;
                }
            } else if (bracket.equals(">")) {
                if (!controlStack.pop().equals("<")) {
                    return;
                }
            }
        }
        checkScore(controlStack, scores);
        return;
    }

    public static void checkScore(Stack<String> controlStack, List<Long> scores) {
        long score = 0;
        while (!controlStack.isEmpty()) {
            score *= 5;
            String bracket = controlStack.pop();
            if (bracket.equals("(")) {
                score += 1;
            } else if (bracket.equals("[")) {
                score += 2;
            } else if (bracket.equals("{")) {
                score += 3;
            } else if (bracket.equals("<")) {
                score += 4;
            }
        }
        System.out.println(score);
        scores.add(score);
    }
}