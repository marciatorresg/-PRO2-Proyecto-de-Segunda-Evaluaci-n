/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

/**
 *
 * @author User
 */

import java.util.LinkedList;

public class NodeTree<E> {
    private E content;
    // Esa lista de hijos es una colección de otros árboles
    private LinkedList<Tree<E>> children;
    
    // ESTO NO SE HARÁ EN ESTE CURSO
    // Colección de otros nodos que son hijos
    // private LinkedList<NodeTree<E>> children;

    public NodeTree() {
        this.content = null;
        this.children = null;
    }
    
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
