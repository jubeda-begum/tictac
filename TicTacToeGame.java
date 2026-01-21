import java.util.Scanner;

public class TicTacToe {

    static char[][] board = new char[3][3];
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("Player 1: X");
        System.out.println("Player 2: O");
        System.out.println();

        boolean playAgain;

        do {
            initializeBoard();
            playGame();
            playAgain = askPlayAgain();
        } while (playAgain);

        System.out.println("Thanks for playing!");
    }

    // Initialize empty board
    static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // Main game loop
    static void playGame() {
        char currentPlayer = 'X';
        boolean gameOver = false;

        while (!gameOver) {
            printBoard();
            playerMove(currentPlayer);

            if (checkWinner(currentPlayer)) {
                printBoard();
                System.out.println("Player " + (currentPlayer == 'X' ? "1" : "2")
                        + " (" + currentPlayer + ") wins!");
                gameOver = true;
            } else if (isBoardFull()) {
                printBoard();
                System.out.println("Game is a draw!");
                gameOver = true;
            } else {
                // Switch player
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    // Print board with row & column numbers
    static void printBoard() {
        System.out.println("\nCurrent Board:");
        System.out.println("   0   1   2");

        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + board[i][j] + " ");
                if (j < 2) System.out.print("|");
            }
            System.out.println();
            if (i < 2) System.out.println("  ---+---+---");
        }
        System.out.println();
    }

    // Handle player input
    static void playerMove(char player) {
        int row, col;

        while (true) {
            System.out.println("Player " + (player == 'X' ? "1" : "2")
                    + "'s turn (" + player + ")");
            System.out.print("Enter row and column: ");
            row = sc.nextInt();
            col = sc.nextInt();

            if (row < 0 || row > 2 || col < 0 || col > 2) {
                System.out.println("Invalid position! Try again.");
            } else if (board[row][col] != ' ') {
                System.out.println("Cell already occupied! Try again.");
            } else {
                board[row][col] = player;
                break;
            }
        }
    }

    // Check winner
    static boolean checkWinner(char player) {
        // Rows & Columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player &&
                 board[i][1] == player &&
                 board[i][2] == player) ||
                (board[0][i] == player &&
                 board[1][i] == player &&
                 board[2][i] == player)) {
                return true;
            }
        }

        // Diagonals
        return (board[0][0] == player &&
                board[1][1] == player &&
                board[2][2] == player) ||
               (board[0][2] == player &&
                board[1][1] == player &&
                board[2][0] == player);
    }

    // Check draw
    static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') return false;
            }
        }
        return true;
    }
    // Ask to play again
    static boolean askPlayAgain() {
        System.out.print("Do you want to play again? (y/n): ");
        char choice = sc.next().toLowerCase().charAt(0);
        return choice == 'y';
    }
}
