package org.bcit.comp2522.project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomListTest {
  private CustomList<Integer, String> list;

  @BeforeEach
  public void setup() {
    list = new CustomList<>();
  }

  @Test
  public void testAddAndGet() {
    list.add(1, "apple");
    list.add(2, "banana");
    list.add(3, "cherry");

    assertEquals("apple", list.get(1));
    assertEquals("banana", list.get(2));
    assertNull(list.get(3));
  }

  @Test
  public void testAddNode() {
    Node<Integer, String> node1 = new Node<>(1, "apple");
    Node<Integer, String> node2 = new Node<>(2, "banana");
    Node<Integer, String> node3 = new Node<>(3, "cherry");

    list.add(node1);
    list.add(node2);
    list.add(node3);

    assertEquals("apple", list.get(1));
    assertEquals("banana", list.get(2));
    assertNull(list.get(3));
  }

  @Test
  public void testGetKey() {
    list.add(1, "apple");
    list.add(2, "banana");
    list.add(3, "cherry");

    assertEquals(Integer.valueOf(1), list.getKey("apple"));
    assertEquals(Integer.valueOf(1), list.getKey("banana"));
    assertEquals(Integer.valueOf(1), list.getKey("cherry"));
  }

  @Test
  public void testRemove() {
    list.add(1, "apple");
    list.add(2, "banana");
    list.add(3, "cherry");

    Node<Integer, String> removedNode = list.remove(2);

    assertEquals("apple", list.get(1));
    assertNull(list.get(2));
    assertNull(list.get(3));
    assertEquals(2, removedNode.getKey());
    assertEquals("banana", removedNode.getValue());
  }
}
