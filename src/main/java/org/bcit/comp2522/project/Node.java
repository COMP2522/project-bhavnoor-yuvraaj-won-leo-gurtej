package org.bcit.comp2522.project;


/**
 * The type Node.
 *
 * @param <K> the type parameter
 * @param <V> the type parameter
 * @author Bhavnoor Saroya
 */
public class Node<K, V> {
  /**
   * The Next.
   */
  Node<K, V> next;
  /**
   * The Key.
   */
  K key;
  /**
   * The Value.
   */
  V value;

  /**
   * Instantiates a new Node.
   *
   * @param key   the key
   * @param value the value
   */
  Node(K key, V value) {
    this.key = key;
    this.value = value;
  }

  /**
   * Gets key.
   *
   * @return the key
   */
  public K getKey() {
    return key;
  }

  /**
   * Gets value.
   *
   * @return the value
   */
  public V getValue() {
    return value;
  }

  /**
   * Gets next.
   *
   * @return the next
   */
  public Node<K, V> getNext() {
    return next;
  }

  /**
   * Sets next.
   *
   * @param next the next
   */
  public void setNext(Node<K, V> next) {
    this.next = next;
  }
}
