package org.bcit.comp2522.project;

import java.util.ArrayList;

public class MyHashMap<K, V> {

    private static final int STARTING_SIZE = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private CustomList<K, V>[] table;
    private int size;


    public MyHashMap() {
        this.table = new CustomList[STARTING_SIZE];
        createLists();
    }

    public MyHashMap(int length) {
        this.table = new CustomList[length];
        createLists();
    }

    public void addAll(MyHashMap map){
        for (CustomList<K, V> list : map.table) {
            Node<K, V> current = list.getHead();
            while (current != null) {
                K key = current.getKey();
                V val = current.getValue();
                this.add(key, val);

                current = current.next;
                if ((float) size / table.length > LOAD_FACTOR) {
                    rehash();
                }
            }
        }

        //todo: implement add all method
        // see java arraylist addAll docs
    }

    public void allAll(ArrayList<V> list){
        if (!(list.get(0) instanceof Sprite)) {
            throw new ClassCastException();
        }
        for (V val : list) {
            if (val == null) {
                throw new NullPointerException();
            }
            else {
                K key = (K) Integer.valueOf(val.hashCode());
                this.add(key, val);
                if ((float) size / table.length > LOAD_FACTOR) {
                    rehash();
                }
            }

        }

        //todo: implement add all method with arraylist param
        // later code could utilize either version of the overloaded method
    }

    void add(K key, V value) {
        int index = hash(key);
        table[index].add(key, value);
        if ((float) size / table.length > LOAD_FACTOR) {
            rehash();
        }
    }

    public Node remove(K key) {
        int index = hash(key);
        Node removedNode = table[index].getHead();
        if (removedNode != null) {
            removedNode = table[index].remove(key);
            size--;
        }
        return removedNode;
    }



    V get(K key) {
        int index = hash(key);

        return table[index].get(key);
    }



    void rehash() {
        int newSize = table.length * 2;
        CustomList<K, V>[] newTable = new CustomList[newSize];
        createLists(newTable);

        for (int i = 0; i < table.length; i++) {
            Node<K, V> current = table[i].getHead();
            while (current != null) {
                int index = hash(current.getKey(), newTable.length);
                newTable[index].add(current.getKey(), current.getValue());
                current = current.getNext();
            }
        }

        table = newTable;
    }

    //the method overloading is a form of polymorphism, should help aid further development

    private void createLists(){
        for (int i = 0; i < table.length; i++) {
            table[i] = new CustomList<K, V>();
        }
    }

    private void createLists(CustomList<K, V>[] table) {
        for (int i = 0; i < table.length; i++) {
            table[i] = new CustomList<K, V>();
        }
    }

    int hash(K key) {
        return key.hashCode() % table.length;
    }
    private int hash(K key, int tableLength) {
        return key.hashCode() % tableLength;
    }
}