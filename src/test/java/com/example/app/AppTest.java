package com.example;
import com.example.app.App; 

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest {

    @Test
    public void testAddNumbers() {
        int result = App.addNumbers(2, 3);  // Assuming App.addNumbers is defined in your code
        assertEquals(5, result, "The sum of 2 and 3 should be 5");
    }

    @Test
    public void testCondition() {
        assertTrue(2 > 1, "2 should be greater than 1");
    }
}
