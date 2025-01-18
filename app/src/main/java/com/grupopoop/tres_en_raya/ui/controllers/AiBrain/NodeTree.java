package com.grupopoop.tres_en_raya.ui.controllers.AiBrain;

import com.grupopoop.tres_en_raya.ui.controllers.AiBrain.Tree;

import java.util.LinkedList;

public class NodeTree<E> {
    private E content;
    private LinkedList<Tree<E>> children;

    public NodeTree(E content) {
        this.content = content;
        this.children = new LinkedList<>();
    }

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public LinkedList<Tree<E>> getChildren() {
        return children;
    }

    public void setChildren(LinkedList<Tree<E>> children) {
        this.children = children;
    }
}