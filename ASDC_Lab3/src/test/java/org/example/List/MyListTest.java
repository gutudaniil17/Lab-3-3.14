package org.example.List;

import org.example.Competitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

class MyListTest {
    MyList list;
    @BeforeEach
    public void init(){
        File fileInput = new File("src/MOCK_DATA.csv");
        list = new MyList(fileInput);
    }
    @Test
    public void addingNewCompetitorToHeadShouldIncreaseSize(){
        Competitor competitor = new Competitor();
        int before = list.getSize();
        list.addToHead(competitor);
        int after = list.getSize();
        assertEquals(before,after-1);
    }
    @Test
    public void addingNewCompetitorToTailShouldIncreaseSize(){
        Competitor competitor = new Competitor();
        int before = list.getSize();
        list.addToTail(competitor);
        int after = list.getSize();
        assertEquals(before,after-1);
    }
    @Test
    public void addingNewCompetitorByIndexShouldIncreaseSize(){
        Competitor competitor = new Competitor();
        int before = list.getSize();
        list.add(competitor,1);
        int after = list.getSize();
        assertEquals(before,after-1);
    }
    @Test
    public void removingCompetitorByIndexShouldDecreaseSize(){
        int before = list.getSize();
        list.remove(2);
        int after = list.getSize();
        assertEquals(before,after+1);
    }
    @Test
    public void removingCompetitorByNodeShouldDecreaseSize(){
        Competitor competitor = Competitor.createCompetitorFromString("Bryna;Jaine;Chile;2008;26;II");
        int before = list.getSize();
        list.remove(competitor);
        int after = list.getSize();
        assertEquals(before,after+1);
    }
    @Test
    public void ifCompetitorIsAddedToListContainsShouldReturnTrue(){
        Competitor competitor = Competitor.createCompetitorFromString("Bryna;Jaine;Chile;2008;26;II");
        list.addToHead(competitor);
        boolean exists = list.contains(competitor);
        assertEquals(exists,true);
    }
}