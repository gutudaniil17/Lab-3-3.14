package org.example.List;

import org.example.Competitor;

public interface LinkedList {
    void addToTail(Competitor competitor);
    void addToHead(Competitor competitor);
    void add(Competitor competitor,int index);
    boolean isEmpty();
    boolean remove(int index);
    boolean remove(Competitor competitor);
    void print();
    boolean contains(Competitor competitor);
}
