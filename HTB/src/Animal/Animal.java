package Animal;

import Actions.Action;
import impl.Cell;
import impl.Pair;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public abstract class Animal {
    // Attributes
    protected double hungerFactor;
    protected int hungerLevel;
    protected double hungerThreshold;
    protected double matingFactor;
    protected int matingLevel;
    protected double matingThreshold;
    protected Pair currentPosition;
    protected Color cellColour;
    private boolean isUpdated;

    // Method
    public double calculateHungerFactor() {
        double hunger = Math.exp(-1 * hungerFactor * hungerLevel);
        return hunger;
    }

    public double calculateMatingFactor() {
        double mating = 1 - Math.exp(-1 * matingFactor * matingLevel);
        return mating;
    }

    public abstract Action update(Cell currentCell);

    public Pair nextPosition(List<Cell> neighbours) {
        // empty list for adjacent cells
        List<Cell> emptyCells = new LinkedList<>();
        // go through cells and add any that don't have an animal
        for (Cell cell: neighbours) {
            if(cell.getAnimal() == null) {
                emptyCells.add(cell);
            }
        }
        // checks that there is a possible move
        if(emptyCells.size() == 0) {
            return null;
        } else {
            // picks a random move and returns it
            Random rand = new Random();
            int randomCellIndex = rand.nextInt(emptyCells.size());
            Cell moveTo = emptyCells.get(randomCellIndex);
            return new Pair(moveTo.getXcoord(),moveTo.getYcoord());
        }
    }

    public abstract Color getColor();

    public boolean isUpdated() {
        return isUpdated;
    }

    public void setUpdated(boolean updated) {
        isUpdated = updated;
    }


    public void updateLevels() {
        matingLevel++;
        hungerLevel++;
    }

    public int getMatingLevel() {
        return matingLevel;
    }

    public void setMatingLevel(int matingLevel) {
        this.matingLevel = matingLevel;
    }
}
