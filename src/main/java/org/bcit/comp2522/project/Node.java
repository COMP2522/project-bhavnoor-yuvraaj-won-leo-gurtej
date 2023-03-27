package org.bcit.comp2522.project;


public class Node<K, V> {
    Node<K, V> next;
    K key;
    V value;
    Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public Node<K,V> getNext() {
        return next;
    }
}
