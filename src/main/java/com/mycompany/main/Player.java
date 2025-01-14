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

abstract class Player {
    protected final char symbol;

    public Player(char symbol) {
        this.symbol = symbol;
    }

    public abstract void makeMove(Board board, Scanner scanner);
}
