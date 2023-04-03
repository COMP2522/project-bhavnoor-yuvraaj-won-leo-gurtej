package org.bcit.comp2522.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}
