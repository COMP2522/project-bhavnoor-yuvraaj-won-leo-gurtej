package org.bcit.comp2522.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PVector;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class GameOverPageTest {

    private Player player;
    private GameOverPage gameOverPage;

    @BeforeEach
    public void setUp() {
        gameOverPage = new GameOverPage(player);
        gameOverPage.setup();
    }

    @Test
    public void testRetryButtonInitialization() {
        Button retryButton = gameOverPage.getRetryButton();
        assertEquals("Retry", retryButton.getLabel());
        assertEquals(new PVector(gameOverPage.width / 2, gameOverPage.height - 100), retryButton.getPosition());
        assertEquals(new Color(255, 255, 255), retryButton.getBackgroundColor());
        assertEquals(new Color(255, 0, 0), retryButton.getHoverColor());

    }

    @Test
    public void testRetryButtonClicked() {
        Button retryButton = gameOverPage.getRetryButton();
        assertFalse(retryButton.isClicked());
    }

}
