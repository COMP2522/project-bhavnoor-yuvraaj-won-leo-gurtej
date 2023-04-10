package org.bcit.comp2522.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WindowTest {

    @Test
    void testinit() {
        Window window = new Window();
        window.init();
//        assertNotNull(window.player);
        assertNotNull(window.wall);
        assertNotNull(window.saveState);
    }
}
