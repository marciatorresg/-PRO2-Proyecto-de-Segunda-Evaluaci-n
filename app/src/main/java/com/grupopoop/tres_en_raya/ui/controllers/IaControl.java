package com.grupopoop.tres_en_raya.ui.controllers;

import com.grupopoop.tres_en_raya.MainActivity;
import java.util.concurrent.ThreadLocalRandom;
import com.grupopoop.tres_en_raya.ui.controllers.AiBrain.*;

public class IaControl {
    private boolean isActivated;
    private Board board;
    private MainActivity mainActivity;
    private AIPlayer aiPlayer;
    private final Tree<char[][]> gameTree;


    public IaControl(MainActivity mainActivity, AIPlayer aiPlayer) {
        this.board = new Board();
        this.mainActivity = mainActivity;
        this.aiPlayer= aiPlayer;
        this.gameTree = new Tree<>();

        NodeTree<char[][]> rootNode = new NodeTree<>(board.getBoardCopy());
        gameTree.setRoot(rootNode);


    }

    public int makePlay(int[] gameState){
        converToBoard(gameState);
        int value = aiPlayer.makeMode2(board);
        saveBoardState();
        if(value==-1){
            value= makePlay2(gameState);
        }

        return value;
    }
    public int makePlay2(int[] gameState){

        int aleatorio =-1;
        if(isActivated){
            aleatorio =ThreadLocalRandom.current().nextInt(0, 9);
            while(gameState[aleatorio]!=0) {
                aleatorio =ThreadLocalRandom.current().nextInt(0, 9);
            }
        }
        return aleatorio;
    }
    public void converToBoard(int[] gameState){
        for(int i =0 ; i<gameState.length;i++){
            if(i<3){
                if(gameState[i]==1) {
                    board.setCell(0, i % 3, Board.HUMAN);
                } else if (gameState[i]==2) {
                    board.setCell(0, i % 3, Board.AI);

                }
            } else if (i<6) {
                if(gameState[i]==1) {
                    board.setCell(1, (i-3) % 3, Board.HUMAN);
                } else if (gameState[i]==2) {
                    board.setCell(1, (i-3) % 3, Board.AI);

                }

            }else{
                if(gameState[i]==1) {
                    board.setCell(2, (i-6) % 3, Board.HUMAN);
                } else if (gameState[i]==2) {
                    board.setCell(2, (i-6) % 3, Board.AI);

                }
            }

        }

    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    private void saveBoardState() {
        // Guardar el estado actual del tablero como un nuevo nodo en el árbol
        NodeTree<char[][]> currentNode = new NodeTree<>(board.getBoardCopy());
        gameTree.getRoot().getChildren().add(new Tree<>(currentNode));
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
