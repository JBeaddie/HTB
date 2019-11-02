package Animal;

import Actions.Action;
import impl.Cell;
import impl.Pair;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;


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
	public abstract double calculateHungerFactor();

	public abstract double calculateMatingFactor();

	public abstract Action update(Cell currentCell);

	public abstract Pair nextPosition(List<Cell> neighbours);

    public abstract Color getColor();

	public boolean isUpdated() {
		return isUpdated;
	}

	public void setUpdated(boolean updated) {
		isUpdated = updated;
	}


	public void updateLevels(){
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
