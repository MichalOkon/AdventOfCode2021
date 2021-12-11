import java.io.File;
import java.util.Stack;
import java.util.Scanner;


class Solution {

    public static void main(String[] args) {
        try {
            File file = new File("./data.txt");
            Scanner sc = new Scanner(file).useDelimiter("\\n");
            int score = 0;
            while (sc.hasNext()) {
                String[] brackets = sc.nextLine().split("");
                score += checkLine(brackets);
            }
            sc.close();

            System.out.println("The total score is " + score);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return;
    }

    public static int checkLine(String[] brackets) {
        Stack<String> controlStack = new Stack<>();
        for (String bracket : brackets) {
            if (bracket.equals("(") || bracket.equals("[") || bracket.equals("{") || bracket.equals("<")) {
                controlStack.push(bracket);
            } else if (bracket.equals(")")) {
                if (!controlStack.pop().equals("(")) {
                    return 3;
                }
            } else if (bracket.equals("]")) {
                if (!controlStack.pop().equals("[")) {
                    return 57;
                }
            } else if (bracket.equals("}")) {
                if (!controlStack.pop().equals("{")) {
                    return 1197;
                }
            } else if (bracket.equals(">")) {
                if (!controlStack.pop().equals("<")) {
                    return 25137;
                }
            }
        }
        return 0;
    }
}