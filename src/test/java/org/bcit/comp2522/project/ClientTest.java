package org.bcit.comp2522.project;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.text.ParseException;
import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    private Client client;
    private SaveState saveState;

    @BeforeEach
    void setUp() {
        client = new Client(8000);
        saveState = new SaveState();
    }

    @Test
    void testCreateJSONPostRequest() {
        String expected = "{\"score\":0.0,\"reqType\":\"POST\",\"health\":0.0}";
        String actual = client.createJSON("POST", saveState);
        assertEquals(expected, actual);
    }

    @Test
    void testCreateJSONGetRequest() {
        String expected = "{\"reqType\":\"GET\"}";
        String actual = client.createJSON("GET", saveState);
        assertEquals(expected, actual);
    }
}
