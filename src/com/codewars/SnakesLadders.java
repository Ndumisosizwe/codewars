package com.codewars;

import java.util.HashMap;
import java.util.Map;

public class SnakesLadders {
    public Player[] players = {new Player(1), new Player(2)};
    private Player currentPlayer = players[0];
    private Player nextPlayer = currentPlayer;
    private boolean isGameOver;
    private static final Map<Integer, Integer> LADDER_MAPPINGS = new HashMap<>();
    private static final Map<Integer, Integer> SNAKE_MAPPINGS = new HashMap<>();

    static {
        //LADDERS
        LADDER_MAPPINGS.put(2, 38);
        LADDER_MAPPINGS.put(7, 14);
        LADDER_MAPPINGS.put(8, 31);
        LADDER_MAPPINGS.put(15, 26);
        LADDER_MAPPINGS.put(51, 67);
        LADDER_MAPPINGS.put(28, 84);
        LADDER_MAPPINGS.put(21, 42);
        LADDER_MAPPINGS.put(36, 44);
        LADDER_MAPPINGS.put(78, 98);
        LADDER_MAPPINGS.put(71, 91);
        LADDER_MAPPINGS.put(87, 94);
        //SNAKES HEADS
        SNAKE_MAPPINGS.put(99, 80);
        SNAKE_MAPPINGS.put(95, 75);
        SNAKE_MAPPINGS.put(92, 88);
        SNAKE_MAPPINGS.put(89, 68);
        SNAKE_MAPPINGS.put(74, 53);
        SNAKE_MAPPINGS.put(64, 60);
        SNAKE_MAPPINGS.put(62, 19);
        SNAKE_MAPPINGS.put(46, 25);
        SNAKE_MAPPINGS.put(49, 11);
        SNAKE_MAPPINGS.put(16, 6);
    }

    public String play(int die1, int die2) {
//        if (die1 < 1 || die1 > 6 || die2 < 1 || die2 > 6) throw new RuntimeException("Invalid dice value.");
        this.currentPlayer = nextPlayer;
        String GAME_OVER_TEXT = "Game over!";
        if (isGameOver)
            return GAME_OVER_TEXT;
        int sumOfDice = die1 + die2;
        int destinationPosition = currentPlayer.getCurrentPosition() + sumOfDice;
        if (LADDER_MAPPINGS.get(destinationPosition) != null)
            this.currentPlayer.setCurrentPosition(LADDER_MAPPINGS.get(destinationPosition));
        else if (SNAKE_MAPPINGS.get(destinationPosition) != null)
            this.currentPlayer.setCurrentPosition(SNAKE_MAPPINGS.get(destinationPosition));
        else {
            this.currentPlayer.setCurrentPosition(destinationPosition);
            if (currentPlayer.getCurrentPosition() > 100) {
                int positionBouncedTo = bounceBackPlayer(currentPlayer);
                if (SNAKE_MAPPINGS.get(positionBouncedTo) != null)
                    this.currentPlayer.setCurrentPosition(SNAKE_MAPPINGS.get(positionBouncedTo));
            }
            if (currentPlayer.getCurrentPosition() == 100) {
                this.isGameOver = true;
                return "Player " + this.currentPlayer.getName() + " Wins!";
            }
        }
        if (die1 == die2)
            nextPlayer = this.currentPlayer;
        else {
            if (this.currentPlayer.getName() == 1)
                nextPlayer = players[1];
            else
                nextPlayer = players[0];
        }
        return "Player " + this.currentPlayer.getName() + " is on square " + currentPlayer.getCurrentPosition();
    }

    private int bounceBackPlayer(Player currentPlayer) {
        int numberOfSetBacks = currentPlayer.getCurrentPosition() - 100;
        currentPlayer.setCurrentPosition(100 - numberOfSetBacks);
        return currentPlayer.getCurrentPosition();
    }
}

class Player {

    private int currentPosition = 0;
    private int name;

    public Player(int name) {
        this.name = name;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }
}