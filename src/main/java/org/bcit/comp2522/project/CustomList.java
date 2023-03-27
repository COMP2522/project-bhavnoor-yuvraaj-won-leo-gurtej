package org.bcit.comp2522.project;

public class CustomList<K, V> {
    public Node<K, V> head;


    void add(K key, V value) {
        Node<K, V> temp = new Node<K, V>(key, value);
        if (head == null) {
            head = temp;
        } else {
            Node<K, V> curr = head;
            while(curr.next != null) {
                curr = curr.next;
            }
            curr.next = temp;
        }
    }

    V get(K key) {
        if (head == null){
            return null;
        } else {
            Node<K, V> curr = head;
            if (curr.key == key){
                return curr.value;
            }
            while (curr.next != null){
                if (curr.key == key){
                    return curr.value;
                }
                curr = curr.next;
            }
        }
        return null;
    }

    public Node<K,V> getHead() {
         return head;
    }
}
