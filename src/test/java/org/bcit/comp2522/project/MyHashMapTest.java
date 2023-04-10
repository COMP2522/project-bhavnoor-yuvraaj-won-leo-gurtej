package org.bcit.comp2522.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;


import static org.junit.jupiter.api.Assertions.*;

class MyHashMapTest {

    private MyHashMap<String, Integer> map;

    @BeforeEach
    void setUp() {
        map = new MyHashMap<>();
    }

    @Test
    void testAddAndGet() {
        map.add("one", 1);
        map.add("two", 2);
        assertEquals(1, map.get("one"));
        assertEquals(2, map.get("two"));
        assertNull(map.get("three"));
    }

    @Test
    void testRemove() {
        map.add("one", 1);
        map.add("two", 2);
        map.remove("one");
        assertNull(map.get("one"));
        assertEquals(2, map.get("two"));
        assertEquals(16, map.getLength());
    }

    @Test
    void testContainsKey() {
        map.add("one", 1);
        map.add("two", 2);
        assertTrue(map.containsKey("one"));
        assertTrue(map.containsKey("two"));
        assertFalse(map.containsKey("three"));
    }

    @Test
    void testRehash() {
        MyHashMap<String, Integer> map = new MyHashMap<>(2);
        map.add("one", 1);
        map.add("two", 2);
        map.add("three", 3);
        assertEquals(4, map.getLength());
        assertTrue(map.containsKey("one"));
        assertTrue(map.containsKey("two"));
        assertTrue(map.containsKey("three"));
        assertEquals(1, map.get("one"));
        assertEquals(2, map.get("two"));
        assertNull(map.get("three"));
    }

    @Test
    void testIterator() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.add("one", 1);
        map.add("two", 2);
        map.add("three", 3);
        Iterator<Node<String, Integer>> iter = map.iterator();
        assertTrue(iter.hasNext());
        Node<String, Integer> node1 = iter.next();
        assertEquals("two", node1.getKey());
        assertEquals(2, node1.getValue());
        assertTrue(iter.hasNext());
        Node<String, Integer> node2 = iter.next();
        assertEquals("three", node2.getKey());
        assertEquals(3, node2.getValue());
        assertFalse(iter.hasNext());
    }


    @Test
    void testIteratorWithCollision() {
        MyHashMap<String, Integer> map = new MyHashMap<>(1);
        map.add("one", 1);
        map.add("neo", 2);
        Iterator<Node<String, Integer>> iter = map.iterator();
        assertTrue(iter.hasNext());
        Node<String, Integer> node1 = iter.next();
        assertEquals("one", node1.getKey());
        assertEquals(1, node1.getValue());
        assertFalse(iter.hasNext());
    }

}
