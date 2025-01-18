package com.grupopoop.tres_en_raya.ui.controllers.AiBrain;

import com.grupopoop.tres_en_raya.ui.controllers.AiBrain.Board;

import java.util.Scanner;
public  class AIPlayer extends Player {
    public AIPlayer(char symbol) {
        super(symbol);
    }


    @Override
    public void makeMove(Board board, Scanner scanner) {
        Tree<GameState> gameTree = generateGameTree(board, symbol);
        NodeTree<GameState> bestNode = findBestMoveUsingTree(gameTree);

        if (bestNode != null) {
            Move bestMove = bestNode.getContent().getMove();
            board.setCell(bestMove.row, bestMove.col, symbol);
        }
    }
    public int makeMode2(Board board){
        Tree<GameState> gameTree = generateGameTree(board, symbol);
        NodeTree<GameState> bestNode = findBestMoveUsingTree(gameTree);
        int value =-1;
        if (bestNode != null) {
            Move bestMove = bestNode.getContent().getMove();
            board.setCell(bestMove.row, bestMove.col, symbol);
            value=boardToArray(bestMove);
        }
        return value;
    }

    private Tree<GameState> generateGameTree(Board board, char currentSymbol) {
        Tree<GameState> tree = new Tree<>();
        NodeTree<GameState> root = new NodeTree<>(new GameState(board.getBoardCopy(), null));
        tree.setRoot(root);
        generateChildren(root, currentSymbol);
        return tree;
    }

    private void generateChildren(NodeTree<GameState> node, char currentSymbol) {
        char[][] board = node.getContent().getBoard();

        if (BoardUtils.checkWin(board, Board.AI) || BoardUtils.checkWin(board, Board.HUMAN) || BoardUtils.isFull(board)) {
            return;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == Board.EMPTY) {
                    // Crear una copia profunda del tablero
                    char[][] newBoard = deepCopyBoard(board);
                    newBoard[i][j] = currentSymbol;
                    Move move = new Move(i, j);
                    NodeTree<GameState> child = new NodeTree<>(new GameState(newBoard, move));
                    node.getChildren().add(new Tree<>(child));
                    generateChildren(child, switchPlayer(currentSymbol));
                }
            }
        }
    }

    private char[][] deepCopyBoard(char[][] board) {
        char[][] newBoard = new char[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, newBoard[i], 0, board[i].length);
        }
        return newBoard;
    }

    private char switchPlayer(char currentSymbol) {
        return currentSymbol == Board.AI ? Board.HUMAN : Board.AI;
    }

    private NodeTree<GameState> findBestMoveUsingTree(Tree<GameState> tree) {
        return minimax(tree.getRoot(), true).node;
    }

    private MinimaxResult minimax(NodeTree<GameState> node, boolean isMaximizing) {
        if (node.getChildren().isEmpty()) {
            char[][] board = node.getContent().getBoard();
            int score = evaluateBoard(board);
            return new MinimaxResult(score, node);
        }

        MinimaxResult bestResult = new MinimaxResult(isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE, null);

        for (Tree<GameState> childTree : node.getChildren()) {
            MinimaxResult childResult = minimax(childTree.getRoot(), !isMaximizing);
            if (isMaximizing && childResult.score > bestResult.score) {
                bestResult = new MinimaxResult(childResult.score, childTree.getRoot());
            } else if (!isMaximizing && childResult.score < bestResult.score) {
                bestResult = new MinimaxResult(childResult.score, childTree.getRoot());
            }
        }

        return bestResult;
    }

    private int evaluateBoard(char[][] board) {
        if (BoardUtils.checkWin(board, Board.AI)) return 10;
        if (BoardUtils.checkWin(board, Board.HUMAN)) return -10;
        if (BoardUtils.isFull(board)) return 0; // Empate
        return 0; // Estado neutro
    }

    private static class MinimaxResult {
        int score;
        NodeTree<GameState> node;

        MinimaxResult(int score, NodeTree<GameState> node) {
            this.score = score;
            this.node = node;
        }
    }
    private int boardToArray(Move bestMove){
        if(bestMove.row==0){
            return bestMove.col;
        } else if (bestMove.row==1) {
            return bestMove.col+3;
        } else{
            return bestMove.col+6;
        }
    }
}

class GameState {
    private final char[][] board;
    private final Move move;

    public GameState(char[][] board, Move move) {
        this.board = board;
        this.move = move;
    }

    public char[][] getBoard() {
        return board;
    }

    public Move getMove() {
        return move;
    }
}

