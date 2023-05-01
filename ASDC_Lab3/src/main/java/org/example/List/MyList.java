package org.example.List;

import org.example.Competitor;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class MyList implements LinkedList , Iterable<Competitor>{
    private Node head;
    private int size;

    ///Вставка элемента в конец списка
    @Override
    public void addToTail(Competitor competitor) {
        if (head == null) {
            head = new Node(competitor);
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node(competitor);
        }
        size++;
    }

    ///Вставка элемента в начало списка
    @Override
    public void addToHead(Competitor competitor) {
        size++;
        Node newNode = new Node(competitor);
        newNode.next = head;
        head = newNode;
    }

    ///Метод вставки элемента по индексу
    @Override
    public void add(Competitor competitor, int index) {
        if (index < 0 || index > size) {
            System.out.println("Incorrect index");
            return;
        }
        if (index == 0) {
            addToHead(competitor);
            return;
        } else {
            Node current = head;
            Node previous = current;
            for (int i = 0; i < index; i++) {
                previous = current;
                current = current.next;
            }
            Node newNode = new Node(competitor);
            previous.next = newNode;
            newNode.next = current;
            size++;
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    ///Метод удаления элемента по индексу
    @Override
    public boolean remove(int index) {
        if (index < 0 || index > size) {
            System.out.println("Incorrect index");
            return false;
        }
        if (index == 0) {
            head = head.next;
            size--;
            return true;
        } else {
            Node current = head;
            Node previous = current;
            for (int i = 0; i < index; i++) {
                previous = current;
                current = current.next;
            }
            previous.next = current.next;
            size--;
        }
        return true;
    }

    ///Метод удаления элемента
    @Override
    public boolean remove(Competitor competitor) {
        if(head.value.equals(competitor)){
            head = head.next;
            return true;
        }
        Node current = head;
        Node previous = current;
        while(current != null){
            if(current.value.equals(competitor)){
                previous.next = current.next;
                size --;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    ///Метод вывода списка
    @Override
    public void print() {
        if (head == null) {
            System.out.println("List is empty");
        } else {
            Node temp = head;
            while (temp.next != null) {
                System.out.println(temp.value);
                temp = temp.next;
            }
        }

    }

    ///Метод поиска элемента в списка
    @Override
    public boolean contains(Competitor competitor) {
        if(head == null) {
            return false;
        }else{
            Node current = head;
            while(current.next != null){
                if(current.value.equals(competitor)){
                    return true;
                }
            }
        }
        return false;
    }

    ///Метод обхода списка
    @NotNull
    @Override
    public Iterator<Competitor> iterator() {
        return new Iterator<Competitor>() {
            private Node node = head;
            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public Competitor next() {
                Node current = node;
                node = node.next;
                return current.value;
            }
        };
    }
    MyList(){}

    ///Конструктор спсика с файла
    public MyList(File file){
        head = null;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String st;
            while ((st = br.readLine()) != null) {
                if (st.isEmpty()) {
                } else {
                    addToHead(Competitor.createCompetitorFromString(st));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getSize() {
        return size;
    }
}
