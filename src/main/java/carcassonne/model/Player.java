package carcassonne.model;

import java.util.HashMap;
import java.util.Map;

import carcassonne.model.terrain.TerrainType;

/**
 * The class for the player objects. It manages the meeples and the score.
 * @author Timur Saglam
 */
public class Player {
    private static final int MAX_MEEPLES = 7;
    private int freeMeeples;
    private Map<TerrainType, Integer> multiplierMap;
    private final int number;
    private int overallScore;
    private Map<TerrainType, Integer> scoreMap;

    /**
     * Simple constructor.
     * @param number is the number of the player.
     */
    public Player(int number) {
        this.number = number;
        freeMeeples = MAX_MEEPLES;
        initializeMultiplierMap();
        initializeScores();
    }

    /**
     * Adds score to the players score value and keeps track of the type of score.
     * @param amount is the amount of points the player gets.
     * @param scoreType determines the score multiplier.
     * @param gameOver determines if the game is running or not. Changes score multipliers.
     */
    public void addScore(int amount, TerrainType scoreType, boolean gameOver) {
        int scoreToAdd = calculateScore(amount, scoreType, gameOver);
        scoreMap.put(scoreType, scoreMap.get(scoreType) + scoreToAdd);
        overallScore += scoreToAdd;
    }

    /**
     * Getter for the amount of free meeples.
     * @return the amount of free meeples.
     */
    public int getFreeMeeples() {
        return freeMeeples;
    }

    public Meeple getMeeple() {
        if (hasFreeMeeples()) {
            freeMeeples--;
            return new Meeple(this);
        }
        throw new IllegalStateException("No unused meeples are left.");
    }

    /**
     * Getter for number of the player.
     * @return the player number.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Getter for the score of the player.
     * @return the score
     */
    public int getScore() {
        return overallScore;
    }

    /**
     * Getter for a specific terrain score.
     * @param scoreType is the type of the specific terrain score.
     * @return the specific score.
     */
    public int getTerrainScore(TerrainType scoreType) {
        if (scoreMap.containsKey(scoreType)) {
            return scoreMap.get(scoreType);
        }
        return -1; // error
    }

    /**
     * Checks whether the player can still place Meeples.
     * @return true if he has at least one free Meeple.
     */
    public boolean hasFreeMeeples() {
        return freeMeeples > 0;
    }

    /**
     * Returns a meeple after its job is down. Allows the player to place another meeple.
     */
    public void returnMeeple() {
        freeMeeples++;
    }

    @Override
    public String toString() {
        return "Player[number: " + number + ", score: " + overallScore + ", free meeples: " + freeMeeples + "]";
    }

    /**
     * Multiplies the amount of score by the multiplier of the type of the score.
     * @param amount sets the amount of score.
     * @param scoreType is the type of score, which influences the multiplier.
     * @param gameOver determines if the game is running or not. Changes score multipliers.
     * @return the multiplied score.
     */
    private int calculateScore(int amount, TerrainType scoreType, boolean gameOver) {
        if (scoreType == TerrainType.CASTLE && gameOver) {
            return amount;
        }
        return amount * multiplierMap.get(scoreType); // TODO (HIGH) divide after multiplier
    }

    private void initializeMultiplierMap() {
        multiplierMap = new HashMap<>();
        multiplierMap.put(TerrainType.CASTLE, 2);
        multiplierMap.put(TerrainType.ROAD, 1);
        multiplierMap.put(TerrainType.MONASTERY, 1);
        multiplierMap.put(TerrainType.FIELDS, 3);
    }

    private void initializeScores() {
        overallScore = 0;
        scoreMap = new HashMap<>();
        for (int i = 0; i < TerrainType.values().length - 1; i++) {
            scoreMap.put(TerrainType.values()[i], 0); // initial scores are zero
        }
    }

}
