import java.util.Scanner;

public class TresEnRaya {

    private static final char EMPTY = '-';
    private static final char HUMAN = 'O';
    private static final char AI = 'X';
    private static final int SIZE = 3;

    public static void main(String[] args) {
        char[][] board = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido a Tres en Raya");
        System.out.println("Humano: O, Computadora: X");
        
        while (true) {
            // Mostrar tablero
            printBoard(board);

            // Turno humano
            System.out.println("Tu turno. Ingresa fila y columna (0-2):");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            if (board[row][col] != EMPTY) {
                System.out.println("Casilla ocupada. Intenta nuevamente.");
                continue;
            }
            board[row][col] = HUMAN;

            if (checkWin(board, HUMAN)) {
                printBoard(board);
                System.out.println("¡Felicidades, ganaste!");
                break;
            }

            if (isFull(board)) {
                printBoard(board);
                System.out.println("Es un empate.");
                break;
            }

            // Turno de la computadora
            System.out.println("Turno de la computadora...");
            Move bestMove = findBestMove(board);
            board[bestMove.row][bestMove.col] = AI;

            if (checkWin(board, AI)) {
                printBoard(board);
                System.out.println("La computadora ganó. Mejor suerte la próxima vez.");
                break;
            }

            if (isFull(board)) {
                printBoard(board);
                System.out.println("Es un empate.");
                break;
            }
        }

        scanner.close();
    }

    private static void printBoard(char[][] board) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean checkWin(char[][] board, char player) {
        // Verificar filas, columnas y diagonales
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) return true;
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) return true;
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) return true;
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) return true;

        return false;
    }

    private static boolean isFull(char[][] board) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == EMPTY) return false;
            }
        }
        return true;
    }

    private static Move findBestMove(char[][] board) {
        int bestValue = Integer.MIN_VALUE;
        Move bestMove = new Move(-1, -1);

        System.out.println("Evaluando todas las posibilidades para el turno de la computadora...");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == EMPTY) {
                    // Simular el movimiento
                    board[i][j] = AI;
                    System.out.println("Posibilidad:");
                    printBoard(board);

                    // Evaluar el movimiento usando Minimax
                    int moveValue = minimax(board, 0, false);
                    System.out.println("Valor del movimiento: " + moveValue);

                    // Deshacer el movimiento
                    board[i][j] = EMPTY;

                    // Actualizar el mejor movimiento
                    if (moveValue > bestValue) {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestValue = moveValue;
                    }
                }
            }
        }
        return bestMove;
    }

    private static int minimax(char[][] board, int depth, boolean isMaximizing) {
        if (checkWin(board, AI)) return 10 - depth;
        if (checkWin(board, HUMAN)) return depth - 10;
        if (isFull(board)) return 0;

        if (isMaximizing) {
            int best = Integer.MIN_VALUE;
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (board[i][j] == EMPTY) {
                        board[i][j] = AI;
                        best = Math.max(best, minimax(board, depth + 1, false));
                        board[i][j] = EMPTY;
                    }
                }
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (board[i][j] == EMPTY) {
                        board[i][j] = HUMAN;
                        best = Math.min(best, minimax(board, depth + 1, true));
                        board[i][j] = EMPTY;
                    }
                }
            }
            return best;
        }
    }

    static class Move {
        int row, col;
        Move(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}

