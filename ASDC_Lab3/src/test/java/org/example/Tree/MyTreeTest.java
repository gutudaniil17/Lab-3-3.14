package org.example.Tree;

import org.example.Competitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class MyTreeTest {
    MyTree tree;
    @BeforeEach
    public void newMyTree(){
        File fileInput = new File("src/MOCK_DATA.csv");
        tree = new MyTree().createTree(fileInput);
    }
    @Test
    public void addingNewNodeShouldIncreaseSize(){
        int before = tree.getSize();
        tree.addNode(new Competitor());
        assertEquals(tree.getSize(),before + 1);
    }
    @Test
    public void removingNodeShouldDecreaseSize(){
        Competitor competitor = Competitor.createCompetitorFromString("Bryna;Jaine;Chile;2008;26;II");
        int before = tree.getSize();
        tree.remove(competitor);
        int after = tree.getSize();
        assertEquals(before,after + 1);
    }
    @Test
    public void ifNodeExistInTreeSearchingShouldReturnIt(){
        Competitor competitor = Competitor.createCompetitorFromString("Simonne;Oldacre;Thailand;1975;27;III");
        MyTree tree1 = new MyTree();
        tree1.addNode(competitor);
        Competitor competitor1 = tree1.find(competitor);
        assertEquals(competitor1,competitor);
    }
}