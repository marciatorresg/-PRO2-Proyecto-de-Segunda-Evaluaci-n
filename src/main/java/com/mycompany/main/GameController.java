/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

/**
 *
 * @author User
 */

import java.util.Scanner;

class GameController {
    private final Board board;
    private final Player humanPlayer;
    private final Player aiPlayer;
    private final Scanner scanner;

    public GameController() {
        this.board = new Board();
        this.humanPlayer = new HumanPlayer(Board.HUMAN);
        this.aiPlayer = new AIPlayer(Board.AI);
        this.scanner = new Scanner(System.in);
    }

    public void startGame() {
        System.out.println("Bienvenido a Tres en Raya");
        System.out.println("Humano: O, Computadora: X");

        while (true) {
            board.printBoard();

            // Turno del humano
            System.out.println("Tu turno. Ingresa fila y columna (0-2):");
            humanPlayer.makeMove(board, scanner);

            if (board.checkWin(Board.HUMAN)) {
                board.printBoard();
                System.out.println("¡Felicidades, ganaste!");
                break;
            }

            if (board.isFull()) {
                board.printBoard();
                System.out.println("Es un empate.");
                break;
            }

            // Turno de la computadora
            System.out.println("Turno de la computadora...");
            aiPlayer.makeMove(board, null);

            if (board.checkWin(Board.AI)) {
                board.printBoard();
                System.out.println("La computadora ganó. Mejor suerte la próxima vez.");
                break;
            }

            if (board.isFull()) {
                board.printBoard();
                System.out.println("Es un empate.");
                break;
            }
        }
        scanner.close();
    }
}