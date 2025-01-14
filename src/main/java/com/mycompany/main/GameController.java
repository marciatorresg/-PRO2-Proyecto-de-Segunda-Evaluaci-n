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
    private final Tree<char[][]> gameTree; // Árbol de estados del juego
    private int turno; // Contador de turnos

    public GameController() {
        this.board = new Board();
        this.humanPlayer = new HumanPlayer(Board.HUMAN);
        this.aiPlayer = new AIPlayer(Board.AI);
        this.scanner = new Scanner(System.in);
        this.gameTree = new Tree<>();
        this.turno = 0;

        // Inicializar el árbol con el estado inicial del tablero
        NodeTree<char[][]> rootNode = new NodeTree<>(board.getBoardCopy());
        gameTree.setRoot(rootNode);
    }

    public void startGame() {
        System.out.println("Bienvenido a Tres en Raya");
        System.out.println("Humano: O, Computadora: X");

        while (true) {
            turno++;
            board.printBoard();

            // Turno del humano
            System.out.println("Tu turno. Ingresa fila y columna (0-2):");
            humanPlayer.makeMove(board, scanner);
            saveBoardState();

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
            turno++;
            System.out.println("Turno de la computadora...");
            aiPlayer.makeMove(board, null);
            saveBoardState();

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

    private void saveBoardState() {
        // Guardar el estado actual del tablero como un nuevo nodo en el árbol
        NodeTree<char[][]> currentNode = new NodeTree<>(board.getBoardCopy());
        gameTree.getRoot().getChildren().add(new Tree<>(currentNode));
    }
}