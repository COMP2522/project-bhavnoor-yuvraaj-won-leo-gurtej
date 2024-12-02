package org.bcit.comp2522.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for CustomIterator class.
 */
class CustomIteratorTest {

  private MyHashMap<Integer, String> hashMap;
  private CustomIterator iterator;

  /**
   * Initialises MyHashMap and CustomIterator objects before each test.
   */
  @BeforeEach
  void setUp() {
    hashMap = new MyHashMap<>();
    hashMap.add(1, "one");
    hashMap.add(2, "two");
    hashMap.add(3, "three");
    hashMap.add(4, "four");
    iterator = new CustomIterator(hashMap);
  }

  @Test
  void testHasNext() {
    assertTrue(iterator.hasNext());
    iterator.next();
    assertTrue(iterator.hasNext());
    iterator.next();
    assertTrue(iterator.hasNext());
    iterator.next();
    assertFalse(iterator.hasNext());
  }
}
