/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

/**
 *
 * @author User
 */
class BoardUtils {
    public static boolean checkWin(char[][] board, char player) {
        int size = board.length;
        for (int i = 0; i < size; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) return true;
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) return true;
        }
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
               (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    public static boolean isFull(char[][] board) {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == Board.EMPTY) return false;
            }
        }
        return true;
    }
}