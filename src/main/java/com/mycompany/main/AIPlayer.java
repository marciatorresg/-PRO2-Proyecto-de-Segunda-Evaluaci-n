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

class AIPlayer extends Player {
    public AIPlayer(char symbol) {
        super(symbol);
    }

    @Override
    public void makeMove(Board board, Scanner scanner) {
        Move bestMove = findBestMove(board.getBoardCopy());
        board.setCell(bestMove.row, bestMove.col, symbol);
    }

    private Move findBestMove(char[][] board) {
        int bestValue = Integer.MIN_VALUE;
        Move bestMove = new Move(-1, -1);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == Board.EMPTY) {
                    board[i][j] = Board.AI;
                    int moveValue = minimax(board, 0, false);
                    board[i][j] = Board.EMPTY;

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

    private int minimax(char[][] board, int depth, boolean isMaximizing) {
        if (BoardUtils.checkWin(board, Board.AI)) return 10 - depth;
        if (BoardUtils.checkWin(board, Board.HUMAN)) return depth - 10;
        if (BoardUtils.isFull(board)) return 0;

        if (isMaximizing) {
            int best = Integer.MIN_VALUE;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == Board.EMPTY) {
                        board[i][j] = Board.AI;
                        best = Math.max(best, minimax(board, depth + 1, false));
                        board[i][j] = Board.EMPTY;
                    }
                }
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == Board.EMPTY) {
                        board[i][j] = Board.HUMAN;
                        best = Math.min(best, minimax(board, depth + 1, true));
                        board[i][j] = Board.EMPTY;
                    }
                }
            }
            return best;
        }
    }
}