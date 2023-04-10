package org.bcit.comp2522.project;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NodeTest {
  @Test
  public void testGetKey() {
    Node<Integer, String> node = new Node<>(42, "foo");
    assertEquals(42, node.getKey());
  }

  @Test
  public void testGetValue() {
    Node<Integer, String> node = new Node<>(42, "foo");
    assertEquals("foo", node.getValue());
  }

  @Test
  public void testGetNext() {
    Node<Integer, String> node1 = new Node<>(42, "foo");
    Node<Integer, String> node2 = new Node<>(43, "bar");
    node1.setNext(node2);
    assertEquals(node2, node1.getNext());
  }

  @Test
  public void testSetNext() {
    Node<Integer, String> node1 = new Node<>(42, "foo");
    Node<Integer, String> node2 = new Node<>(43, "bar");
    node1.setNext(node2);
    assertEquals(node2, node1.getNext());
  }
  @Test
  public void testSetNextNull() {
    Node<Integer, String> node = new Node<>(42, "foo");
    node.setNext(null);
    assertNull(node.getNext());
  }

  @Test
  public void testGetValueNull() {
    Node<Integer, String> node = new Node<>(42, null);
    assertNull(node.getValue());
  }

  @Test
  public void testGetKeyNull() {
    Node<String, String> node = new Node<>(null, "foo");
    assertNull(node.getKey());
  }

  @Test
  public void testSetNextTwice() {
    Node<Integer, String> node1 = new Node<>(42, "foo");
    Node<Integer, String> node2 = new Node<>(43, "bar");
    Node<Integer, String> node3 = new Node<>(44, "baz");
    node1.setNext(node2);
    node2.setNext(node3);
    assertEquals(node3, node2.getNext());
  }

  @Test
  public void testEquals() {
    Node<Integer, String> node1 = new Node<>(42, "foo");
    Node<Integer, String> node3 = new Node<>(43, "bar");
    assertFalse(node1.equals(node3));
  }

}

