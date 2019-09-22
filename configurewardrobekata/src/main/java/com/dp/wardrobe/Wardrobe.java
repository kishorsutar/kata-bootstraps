package com.dp.wardrobe;

import java.util.ArrayList;
import java.util.List;

class Wardrobe {

    private static final int WALL_LENGTH = 250;

    private List<Integer> elementsAvailable = new ArrayList<Integer>();

    Wardrobe() {
        elementsAvailable.add(50);
        elementsAvailable.add(75);
        elementsAvailable.add(100);
        elementsAvailable.add(120);
    }

    int calculateWardrobeElements() {
        int total = 0;
        for (Integer element : elementsAvailable
        ) {
            if (total+element <= WALL_LENGTH) {
                total += element;
            }
        }
        return total;
    }

}
