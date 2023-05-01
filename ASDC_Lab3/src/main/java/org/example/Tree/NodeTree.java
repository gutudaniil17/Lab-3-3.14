package org.example.Tree;

import org.example.Competitor;

public class NodeTree {
    Competitor value;
    NodeTree leftChild;
    NodeTree rightChild;

    NodeTree(Competitor competitor){
        value = competitor;
        leftChild = null;
        rightChild = null;
    }
}
