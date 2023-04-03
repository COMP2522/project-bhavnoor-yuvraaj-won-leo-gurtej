package org.bcit.comp2522.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import processing.core.PVector;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class WindowTest {

    private Window window;

    @BeforeEach
    void setUp() {
        window = new Window();
        window.init();
    }

    @Test
    void testinit() {
        Window window = new Window();
        window.init();
        assertNotNull(window.player);
        assertNotNull(window.wall);
        assertNotNull(window.sprites);
        assertNotNull(window.enemies);
        assertNotNull(window.saveState);
    }
    @Test
    void testSettings() {
        assertEquals(100, window.width);
        assertEquals(100, window.height);
    }

    @Test
    void testCreateEnemies() {
        assertEquals(4, window.numbEnemies);

        window.createEnemies(2);
        assertEquals(5, window.numbEnemies);

        window.createEnemies(3);
        assertEquals(6, window.numbEnemies);
    }




}
