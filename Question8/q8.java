import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.io.File;
import java.lang.String;


class Solution {
    public static final int BINGO_LENGTH = 5;

    public static void main(String[] args) {
        try {
            File file = new File("./data.txt");
            Scanner sc = new Scanner(file);

            // Filling in the numbers
            int[] numbers = Arrays.stream(sc.nextLine().split(","))
                    .mapToInt(num -> Integer.parseInt(num)).toArray();

            // Filling in the boards
            HashSet<Integer[][]> boards = new HashSet<>();
            while (sc.hasNextInt()) {
                Integer[][] newBoard = new Integer[BINGO_LENGTH][BINGO_LENGTH];
                boards.add(newBoard);
                for (int i = 0; i < BINGO_LENGTH; i++) {
                    for (int j = 0; j < BINGO_LENGTH; j++) {
                        newBoard[i][j] = sc.nextInt();
                    }
                }
            }
            sc.close();

            // Iterate over each number and check each board with that number
            HashSet<Integer[][]> toRemoveSet;
            for (int number : numbers) {
                System.out.println("Number: " + number);
                toRemoveSet = new HashSet<>();
                for (Integer[][] board : boards) {
                    printBoard(board);
                    if (checkNumber(number, board)) {
                        System.out.println("Winner found!\n");
                        if (boards.size() == 1) {
                            printBoard(board);
                            System.out.println("The final score of the last board is " +
                                    calculateScore(board, number));
                            return;
                        }
                        toRemoveSet.add(board);
                    }
                }
                boards.removeAll(toRemoveSet);
            }

        } catch (
                Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return;
    }

    public static boolean checkNumber(int number, Integer[][] board) {
        // This function iterates over the board, updates the board with a new number
        // and checks if the bingo was found
        // Returns true if there is a bingo

        boolean[] verticalBingoArray = new boolean[BINGO_LENGTH];
        for (int i = 0; i < verticalBingoArray.length; i++) {
            verticalBingoArray[i] = true;
        }

        for (int i = 0; i < BINGO_LENGTH; i++) {
            boolean horizontalBingo = true;
            for (int j = 0; j < BINGO_LENGTH; j++) {
                if (number == board[i][j]) {
                    board[i][j] = -1;
                }
                if (board[i][j] != -1) {
                    verticalBingoArray[j] = false;
                    horizontalBingo = false;
                }
            }
            if (horizontalBingo) {
                return true;
            }
        }

        for (boolean verticalBingo : verticalBingoArray) {
            if (verticalBingo) {
                return true;
            }
        }

        return false;
    }

    public static int calculateScore(Integer[][] board, int lastNumber) {
        // calculates the final score for the winning board

        int score = 0;
        for (int i = 0; i < BINGO_LENGTH; i++) {
            for (int j = 0; j < BINGO_LENGTH; j++) {
                if (board[i][j] != -1) {
                    score += board[i][j];
                }
            }
        }

        return score * lastNumber;
    }

    public static void printBoard(Integer [][] board) {
        for (Integer[] row: board) {
            for(int element : row) {
                System.out.print(element + " ");
            }
            System.out.println("");
        }
        System.out.println("\n");
    }
}