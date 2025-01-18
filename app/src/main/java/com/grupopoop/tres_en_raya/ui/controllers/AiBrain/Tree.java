package com.grupopoop.tres_en_raya.ui.controllers.AiBrain;

public class Tree<E> {
    private NodeTree<E> root;

    public Tree() {
        this.root = null;
    }

    public Tree(NodeTree<E> root) {
        this.root = root;
    }

    public NodeTree<E> getRoot() {
        return root;
    }

    public void setRoot(NodeTree<E> root) {
        this.root = root;
    }
}
