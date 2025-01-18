package com.grupopoop.tres_en_raya.ui.controllers.AiBrain;
import com.grupopoop.tres_en_raya.ui.controllers.AiBrain.Board;

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
