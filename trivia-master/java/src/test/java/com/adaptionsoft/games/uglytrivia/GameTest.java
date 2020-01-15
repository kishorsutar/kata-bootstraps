package com.adaptionsoft.games.uglytrivia;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    private Game game;

    @Before
    public void setup() {

        game = new Game();
    }

    @Test
    public void createRockQuestion() {
    }

    @Test
    public void isPlayable() {
        game.players.add("test1");
        game.players.add("test2");
        game.players.add("test3");
        game.players.add("test4");
        assertTrue(game.isPlayable());

        game.players.remove(1);
        game.players.remove(2);
        game.players.remove(0);
        assertFalse(game.isPlayable());
    }

    @Test
    public void add() {

        assertTrue(game.add("Kishor"));
        assertTrue(game.add("Rupali"));
        assertTrue(game.add("Kaushik"));
        assertTrue(game.add("Kuldya"));
        assertTrue(game.add("OMkya"));
    }

    @Test
    public void howManyPlayers() {
    }

    @Test
    public void roll() {
        game.add("Kishor");
        game.add("Rupali");
        game.roll(2);
    }

    @Test
    public void wasCorrectlyAnswered() {
    }

    @Test
    public void wrongAnswer() {
    }
}