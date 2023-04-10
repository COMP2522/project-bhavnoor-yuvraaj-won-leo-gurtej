package org.bcit.comp2522.project;

import java.util.Iterator;

/**
 * The type Custom list.
 *
 * @author Bhavnoor Saroya
 *
 * @param <K> Key
 * @param <V> Value
 */
public class CustomList<K, V> {
  /**
   * The Head.
   */
  public Node<K, V> head;


  /**
   * Add.
   *
   * @param key   the key
   * @param value the value
   */
  void add(K key, V value) {
    Node<K, V> temp = new Node<K, V>(key, value);
    if (head == null) {
      head = temp;
    } else {
      Node<K, V> curr = head;
      while (curr.next != null) {
        curr = curr.next;
      }
      curr.next = temp;
    }
  }

  /**
   * Add.
   *
   * @param node the node
   */
  void add(Node<K, V> node) {
    K key = node.getKey();
    V value = node.getValue();
    add(key, value);
  }

  /**
   * Get v.
   *
   * @param key the key
   * @return the v
   */
  V get(K key) {
    if (head == null) {
      return null;
    } else {
      Node<K, V> curr = head;
      if (curr.key == key) {
        return curr.value;
      }
      while (curr.next != null) {
        if (curr.key == key) {
          return curr.value;
        }
        curr = curr.next;
      }
    }
    return null;
  }



  /**
   * Gets head.
   *
   * @return the head
   */
  public Node<K, V> getHead() {
    return head;
  }

  /**
   * Sets head.
   *
   * @param next the next
   */
  public void setHead(Node<K, V> next) {
    head = next;
  }

  /**
   * Remove node.
   *
   * @param key the key
   * @return the node
   */
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
