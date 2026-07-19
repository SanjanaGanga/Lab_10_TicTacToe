import java.util.Scanner;

public class TicTacToe {
    // Class level variables & constants
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String[][] board = new String[ROWS][COLS];
    private static String currentPlayer = "X";
    private static int moveCount = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean playingAgain = true;

        while (playingAgain) {
            // 1. Clear the board and set the player to X
            clearBoard();
            currentPlayer = "X";
            moveCount = 0;
            boolean gameOver = false;

            System.out.println("Welcome to Console Tic Tac Toe!");

            // Game loop
            while (!gameOver) {
                display();
                System.out.println("Player " + currentPlayer + "'s turn.");

                int rowMove = -1;
                int colMove = -1;
                boolean validMoveFound = false;

                // Loop until a valid, empty space is selected
                while (!validMoveFound) {
                    rowMove = SafeInput.getRangedInt(in, "Enter row", 1, ROWS) - 1;
                    colMove = SafeInput.getRangedInt(in, "Enter column", 1, COLS) - 1;

                    if (isValidMove(rowMove, colMove)) {
                        validMoveFound = true;
                    } else {
                        System.out.println("That cell is already taken! Try again.");
                    }
                }

                // Record valid move
                board[rowMove][colMove] = currentPlayer;
                moveCount++;

                // Check for win or tie (Win is only possible after 5 moves)
                if (moveCount >= 5 && isWin(currentPlayer)) {
                    display();
                    System.out.println("Player " + currentPlayer + " wins the game!");
                    gameOver = true;
                } else if (moveCount >= 7 && isTie()) {
                    display();
                    System.out.println("It's a Tie game!");
                    gameOver = true;
                } else {
                    // Toggle player
                    currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
                }
            }

            // Prompt to play again
            playingAgain = SafeInput.getYNConfirm(in, "Do you want to play again?");
        }
        System.out.println("Thanks for playing!");
    }

    // Helper Methods
    private static void clearBoard() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                board[r][c] = " ";
            }
        }
    }

    private static void display() {
        System.out.println("\n-------------");
        for (int r = 0; r < ROWS; r++) {
            System.out.print("| ");
            for (int c = 0; c < COLS; c++) {
                System.out.print(board[r][c] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {

        return isRowWin(player) || isColWin(player) || isDiagnalWin(player);
    }

    private static boolean isRowWin(String player) {
        for (int r = 0; r < ROWS; r++) {
            if (board[r][0].equals(player) && board[r][1].equals(player) && board[r][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int c = 0; c < COLS; c++) {
            if (board[0][c].equals(player) && board[1][c].equals(player) && board[2][c].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagnalWin(String player) {
        // Top-left to bottom-right
        if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) {
            return true;
        }
        // Top-right to bottom-left
        if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)) {
            return true;
        }
        return false;
    }

    //Checks for a tie condition based on either a full board or blocked vectors.
    private static boolean isTie() {
        // Condition A: If the board is full and nobody won, it's a tie
        if (moveCount == ROWS * COLS) {
            return true;
        }

        // Condition B: Alternatively check if all win vectors are blocked by containing both an X and an O
        boolean allVectorsBlocked = true;

        // Check rows
        for (int r = 0; r < ROWS; r++) {
            if (!hasBothPlayers(board[r][0], board[r][1], board[r][2])) {
                allVectorsBlocked = false;
            }
        }
        // Check columns
        for (int c = 0; c < COLS; c++) {
            if (!hasBothPlayers(board[0][c], board[1][c], board[2][c])) {
                allVectorsBlocked = false;
            }
        }
        // Check diagonals
        if (!hasBothPlayers(board[0][0], board[1][1], board[2][2])) allVectorsBlocked = false;
        if (!hasBothPlayers(board[0][2], board[1][1], board[2][0])) allVectorsBlocked = false;

        return allVectorsBlocked;
    }

    //Helper method to be used by isTie() to determine if a vector has both tokens.
    private static boolean hasBothPlayers(String c1, String c2, String c3) {
        boolean hasX = c1.equals("X") || c2.equals("X") || c3.equals("X");
        boolean hasO = c1.equals("O") || c2.equals("O") || c3.equals("O");
        return hasX && hasO;
    }
}
