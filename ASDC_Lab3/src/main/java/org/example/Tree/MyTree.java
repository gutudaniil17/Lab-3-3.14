package org.example.Tree;

import org.example.Competitor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MyTree {
    private NodeTree rootNode;
    private int size;

    public MyTree() {
        rootNode = null;
        size = 0;
    }

    ///Вставка узла в дерево
    public void addNode(Competitor competitor) {
        NodeTree newNodeTree = new NodeTree(competitor);
        if (rootNode == null) {
            rootNode = newNodeTree;
            size++;
        } else {
            NodeTree current = rootNode;
            NodeTree parent;
            while (true) {
                parent = current;
                if (newNodeTree.equals(current)) {
                    System.out.println("Этот элемент уже существует");
                    return;
                } else if (competitor.getNumberInTable() < current.value.getNumberInTable()) {
                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = newNodeTree;
                        size++;
                        return;
                    }
                } else{
                    current = current.rightChild;
                    if(current == null){
                        parent.rightChild = newNodeTree;
                        size++;
                        return;
                    }
                }
            }
        }
    }

    ///Удаление узла из дерева
    public boolean remove(Competitor competitor){
        NodeTree current = rootNode;
        NodeTree parent = null;
        ///Flag which child of parent is current
        boolean rightChild = false;
        while(current != null){
            if(current.value.equals(competitor)){
                break;
            }
            parent = current;
            if(competitor.getNumberInTable() < current.value.getNumberInTable()){
                current = current.leftChild;
                rightChild = false;
            }else {
                current = current.rightChild;
                rightChild = true;
            }
        }
        if(current == null){
            System.out.println("No such competitor");
            return false;
        }
        if(current.leftChild == null && current.rightChild == null){
            if(current == rootNode){
                rootNode = null;
            } else if (rightChild) {
                parent.rightChild = null;
            }else {
                parent.leftChild = null;
            }
            return true;
        }else if(current.leftChild == null){
            if (current == rootNode){
                rootNode = null;
            }else if(rightChild){
                parent.rightChild = null;
            }else{
                parent.leftChild = null;
            }
        }else{
            NodeTree helper = getHelper(current);
            if(current == rootNode){
                rootNode = null;
            }else if(rightChild){
                parent.rightChild = helper;
            }else {
                parent.leftChild = helper;
            }
        }
        size--;
        return true;
    }
    ///Вспомогательный метод для удаления узла
    private NodeTree getHelper(NodeTree node){
        NodeTree helperParent = node;
        NodeTree helper = node;
        NodeTree current = helper.rightChild;
        while(current != null){
            helperParent = helper;
            helper = current;
            current = current.leftChild;
        }if(helper != node.rightChild){
            helperParent.leftChild = helper.rightChild;
            helper.rightChild = node.rightChild;
        }
        return helper;
    }
    ///Поиск узла в дереве
    public Competitor find(Competitor competitor){
        NodeTree current = rootNode;
        while (!current.value.equals(competitor)){
            if(competitor.getNumberInTable() < current.value.getNumberInTable()){
                current = current.leftChild;
            }else {
                current = current.rightChild;
            }
            if(current == null){
                System.out.println("No such competitor");
                return null;
            }
        }
        return current.value;
    }

    ///Прямой порядок обхода
    public void printPreOrder(){
        printPreOrderHelper(rootNode);
    }
    ///Обратный порядок обхода
    public void printPostOrder(){
        printPostOrderHelper(rootNode);
    }
    ///Центрированный порядок обхода
    public void printInOrder(){
        printInOrderHelper(rootNode);
    }
    private void printPreOrderHelper(NodeTree node){
        if(node == null){
            return;
        }
        System.out.println(node.value);
        printPreOrderHelper(node.leftChild);
        printPreOrderHelper(node.rightChild);
    }
    private void printPostOrderHelper(NodeTree node){
        if(node == null){
            return;
        }
        printPostOrderHelper(node.leftChild);
        printPreOrderHelper(node.rightChild);
        System.out.println(node.value);
    }
    private void printInOrderHelper(NodeTree node){
        if(node == null){
            return;
        }
        printInOrderHelper(node.leftChild);
        System.out.println(node.value);
        printInOrderHelper(node.rightChild);
    }
    public MyTree createTree(File file){
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String st;
            while ((st = br.readLine()) != null) {
                if (st.isEmpty()) {
                } else {
                    addNode(Competitor.createCompetitorFromString(st));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public int getSize() {
        return size;
    }
}
