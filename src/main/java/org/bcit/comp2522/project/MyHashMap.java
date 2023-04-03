package org.bcit.comp2522.project;

import java.util.ArrayList;
import java.util.Iterator;

public class MyHashMap<K, V> implements Iterable{

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
                //size++ is done by this.add()

                current = current.next;
                if ((float) size / table.length > LOAD_FACTOR) {
                    rehash();
                }
            }
        }

        //done implement add all method
        //see java arraylist addAll docs, code needs cleanup soon
    }

    public void addAll(ArrayList<V> list){
        if (!(list.get(0) instanceof Sprite)) {
            throw new ClassCastException();
        }
        for (V val : list) {
            if (val == null) {
                throw new NullPointerException();
            }
            else {
                K key = (K) Integer.valueOf(val.hashCode());
//                K key = (K) Integer.valueOf(id);
                this.add(key, val);
//                size++;//this is done by the add method
                if ((float) size / table.length > LOAD_FACTOR) {
                    rehash();
                }
            }
//            id = (id.toInteger)//not used
        }

        //done implement add all method with arraylist param
        //look at this polymorphism
    }

    void add(K key, V value) {
        int index = hash(key);
        table[index].add(key, value);
        size++;
        if ((float) size / table.length > LOAD_FACTOR) {
            rehash();
        }
    }

    void add(V value) {
        K key = (K) Integer.valueOf(value.hashCode());
        int index = hash(key);
        table[index].add(key, value);
        size++;
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
        CustomList<K, V>[] doubledTable = new CustomList[newSize];
        createLists(doubledTable);

        for (int i = 0; i < table.length; i++) {
            Node<K, V> current = table[i].getHead();
            while (current != null) {
                int index = hash(current.getKey(), doubledTable.length);
                doubledTable[index].add(current.getKey(), current.getValue());
                current = current.getNext();
            }
        }
        table = doubledTable;
    }

    //the method overloading is a form of polymorphism, should help aid the rest of the team in their development

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

    public int getLength(){
        return table.length;
    }

    @Override
    public Iterator iterator() {
        return new CustomIterator(this);
    }

    /**
     * getNextNode method.
     * @param n a hashmap node that we wish to get the next node from
     * @return next node in the map, null if none remaining
     */
    public boolean hasNextNode(Node<K, V> n) {
        if (n.next != null){
            return true;
        }
        int index = hash(n.getKey());
        index++;
        if (index>table.length){
            return false;
        }
        return true;
    }

    public Node<K, V> getNextNode(Node<K, V> n) {
        if (n == null){
            for(int i = 0; i < table.length; i++){
                if (table[i].getHead() != null){
                    return table[i].head;
                }
            }
        }
        if (n.next != null){
            return n.next;
        }
        int index = hash(n.getKey());
        for(int i = index + 1; i< table.length; i++){
            if (table[i].getHead() != null){
                return table[i].head;
            }
        }
        return null;
    }

    public boolean containsKey(K key){
        int index = hash(key);
        if (index > table.length){
            return false;
        }
        Node curr = table[index].getHead();
        while (curr != null){
            if (curr.getKey() == key){
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

}