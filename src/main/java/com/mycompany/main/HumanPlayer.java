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

class HumanPlayer extends Player {
    public HumanPlayer(char symbol) {
        super(symbol);
    }

    @Override
    public void makeMove(Board board, Scanner scanner) {
        int row, col;
        while (true) {
            row = scanner.nextInt();
            col = scanner.nextInt();
            if (board.isCellEmpty(row, col)) {
                board.setCell(row, col, symbol);
                break;
            } else {
                System.out.println("Casilla ocupada. Intenta nuevamente.");
            }
        }
    }
}
