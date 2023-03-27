package org.bcit.comp2522.project;

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

    void add(K key, V value) {
        int index = hash(key);
        table[index].add(key, value);
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