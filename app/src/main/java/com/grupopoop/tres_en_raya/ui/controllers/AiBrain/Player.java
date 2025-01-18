package com.grupopoop.tres_en_raya.ui.controllers.AiBrain;

import com.grupopoop.tres_en_raya.ui.controllers.AiBrain.Board;

import java.util.Scanner;

public abstract class Player {
    protected final char symbol;

    public Player(char symbol) {
        this.symbol = symbol;
    }

    public abstract void makeMove(Board board, Scanner scanner);
}