package com.dp.wardrobe;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class WardrobeTest {

    private Wardrobe wardrobe;

    @Before
    public void setUp() {
        wardrobe = new Wardrobe();
    }

    @Test
    public void calculateWardrobeElementsTest() {

        int total = wardrobe.calculateWardrobeElements();
        assertTrue("Value: " + total, total <= 250);
        System.out.println(total);
    }
}