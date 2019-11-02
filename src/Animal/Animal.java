package Animal;

import impl.Cell;
import impl.Pair;

import java.awt.*;

public abstract class Animal {

	protected double hungerFactor;
	protected int hungerLevel;
	protected double hungerThreshold;
	protected double matingFactor;
	protected int matingLevel;
	protected double matingThreshold;
	private Pair currentPosition;
	private Color cellColour;
	private boolean isUpdated;

	public Animal() {}

	public abstract double calculateHungerFactor();

	public abstract double calculateMatingFactor();

	public void update(Cell currentCell) {
		if(!isUpdated) {
			cellColour;

		}
	}

	public Pair nextPosition(List<Cell> neigbours) {
		// TODO
		return null;
	}


    public abstract Color getColor();
}
