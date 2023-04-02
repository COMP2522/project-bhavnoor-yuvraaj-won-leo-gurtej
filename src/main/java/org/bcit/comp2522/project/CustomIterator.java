package org.bcit.comp2522.project;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomIterator implements Iterator {
  Node current;

  MyHashMap map;



  public CustomIterator(MyHashMap map) {
    this.current = map.getNextNode(null);
    this.map = map;
  }

  /**
   * this is the most genius thing ever, the getNextNode allows me to get the next node in the hashmap
   * but protects the encapsulation.
   * @return whether there is another element in the map
   */
  public boolean hasNext() {
    if (map.getNextNode(current) != null){
      return true;
    }
//    if (current.next == null) {
//        return map.hasNextNode(current);
//    }
    return false;
  }

  /**
   * Returns the next element in the customList
   * @return the next element in the iteration
   * @throws NoSuchElementException if the iteration has no more elements
   */
  @Override
  public Object next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
//    Node temp = current;
//    current = current.next;
    current = map.getNextNode(current);
    return current;

  }
}
