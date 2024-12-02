package org.bcit.comp2522.project;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Myhashmap class, certified awesome hashmap, featuring iterable and o(1) lookup times.
 *
 * @param <K> key
 * @param <V> value
 *
 * @author Bhavnoor Saroya
 * @author Gurtej Malik
 */
public class MyHashMap<K, V> implements Iterable {

  /**
   * The initial size of the table.
   * */
  private static final int STARTING_SIZE = 16;

  /**
   * The load factor at which the table should be resized.
   * */
  private static final float LOAD_FACTOR = 0.75f;

  /**
   * The table of lists used to store key-value pairs.
   */
  private CustomList<K, V>[] table;

  /**
   * The number of key-value pairs stored in the map.
   */
  private int size;

  /**
   * Instantiates a new My hash map.
   */
  public MyHashMap() {
    this.table = new CustomList[STARTING_SIZE];
    createLists();
  }

  /**
   * Instantiates a new My hash map.
   *
   * @param length the length
   */
  public MyHashMap(int length) {
    this.table = new CustomList[length];
    createLists();
  }

  /**
   * Adds all the key-value pairs from the specified
   * hash map to this one.
   *
   * @param map The map containing the key-value pairs to add.
   */
  public void addAll(MyHashMap map) {
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
  }

  /**
   * Adds all the values from the specified list to this map.
   *
   * @param list The list containing the values to add.
   * @throws ClassCastException if an element in the list cannot be cast to type {@code Sprite}.
   * @throws NullPointerException if an element in the list is null
   */
  public void addAll(ArrayList<V> list) {
    if (!(list.get(0) instanceof Sprite)) {
      throw new ClassCastException();
    }
    for (V val : list) {
      if (val == null) {
        throw new NullPointerException();
      } else {
        K key = (K) Integer.valueOf(val.hashCode());
        this.add(key, val);
        if ((float) size / table.length > LOAD_FACTOR) {
          rehash();
        }
      }
    }

  }

  /**
   * Adds a key-value pair to the map.
   *
   * @param key The key used to index the value.
   * @param value The value to store in the map.
   */
  void add(K key, V value) {
    int index = hash(key);
    table[index].add(key, value);
    size++;
    if ((float) size / table.length > LOAD_FACTOR) {
      rehash();
    }
  }

  /**
   * Adds a value to the map.
   * The key used to index the value is derived from the value's hash code.
   *
   * @param value The value to store in the map.
   */
  void add(V value) {
    K key = (K) Integer.valueOf(value.hashCode());
    int index = hash(key);
    table[index].add(key, value);
    size++;
    if ((float) size / table.length > LOAD_FACTOR) {
      rehash();
    }
  }

  /**
   * Remove node.
   *
   * @param key the key
   * @return the node
   */
  public Node remove(K key) {
    int index = hash(key);
    Node removedNode = table[index].getHead();
    if (removedNode != null) {
      removedNode = table[index].remove(key);
      size--;
    }
    return removedNode;
  }


  /**
   * Get v.
   *
   * @param key the key
   * @return the v
   */
  V get(K key) {
    int index = hash(key);
    return table[index].get(key);
  }


  /**
   * Rehash method to redistribute data in the hashmap
   * when load factor is breached.
   */
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

  /**
   * Method to create CustomList.
   */
  private void createLists() {
    for (int i = 0; i < table.length; i++) {
      table[i] = new CustomList<K, V>();
    }
  }

  /**
   * Overloaded method to create new CustomList.
   *
   * @param table - CustomList object
   */
  private void createLists(CustomList<K, V>[] table) {
    for (int i = 0; i < table.length; i++) {
      table[i] = new CustomList<K, V>();
    }
  }

  /**
   * Hash int.
   *
   * @param key the key
   * @return the int
   */
  int hash(K key) {
    return key.hashCode() % table.length;
  }

  private int hash(K key, int tableLength) {
    return key.hashCode() % tableLength;
  }

  /**
   * Get length int.
   *
   * @return the int
   */
  public int getLength() {
    return table.length;
  }

  @Override
  public Iterator iterator() {
    return new CustomIterator(this);
  }

  /**
   * getNextNode method.
   *
   * @param n a hashmap node that we wish to get the next node from
   * @return next node in the map, null if none remaining
   */
  public boolean hasNextNode(Node<K, V> n) {
    if (n.next != null) {
      return true;
    }
    int index = hash(n.getKey());
    index++;
    if (index > table.length) {
      return false;
    }
    return true;
  }

  /**
   * Gets next node.
   *
   * @param n the n
   * @return the next node
   */
  public Node<K, V> getNextNode(Node<K, V> n) {
    if (n == null) {
      for (int i = 0; i < table.length; i++) {
        if (table[i].getHead() != null) {
          return table[i].head;
        }
      }
    }
    if (n.next != null) {
      return n.next;
    }
    int index = hash(n.getKey());
    for (int i = index + 1; i < table.length; i++) {
      if (table[i].getHead() != null) {
        return table[i].head;
      }
    }
    return null;
  }

  /**
   * Contains key boolean.
   *
   * @param key the key
   * @return the boolean
   */
  public boolean containsKey(K key) {
    int index = hash(key);
    if (index > table.length) {
      return false;
    }
    Node curr = table[index].getHead();
    while (curr != null) {
      if (curr.getKey() == key) {
        return true;
      }
      curr = curr.next;
    }
    return false;
  }

}