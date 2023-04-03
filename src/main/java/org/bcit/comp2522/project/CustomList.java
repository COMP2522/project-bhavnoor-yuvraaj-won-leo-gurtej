package org.bcit.comp2522.project;

import java.util.Iterator;

public class CustomList<K, V>{
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

    void add(Node<K, V> node){
        K key = node.getKey();
        V value = node.getValue();
        add(key, value);
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


    K getKey(V value){
        //todo get key from value
        // see above: get val from key if you need help
        return head.key;//this is incorrect just left it to avoid ide errors
    }

    public Node<K,V> getHead() {
         return head;
    }

  public void setHead(Node<K, V> next) {
        head = next;
  }

  public Node remove(K key) {
        Node<K, V> current = head;
        Node<K, V> previous = null;
        Node removedNode = null;

        while (current != null) {
            if (current.getKey().equals(key)) {
                // Remove the node
                removedNode = current;
                if (previous == null) {
                    // Remove head node
                    head = current.getNext();
                } else {
                    // Remove node in middle or end
                    previous.setNext(current.getNext());
                }
                break;
            }
            previous = current;
            current = current.getNext();
        }
        return removedNode;
    }


}
