package Animal;

import impl.Cell;
import impl.Pair;

import java.awt.*;
import java.util.LinkedList;


public abstract class Animal {

	protected double hungerFactor;
	protected int hungerLevel;
	protected double hungerThreshold;
	protected double matingFactor;
	protected int matingLevel;
	protected double matingThreshold;
	protected Pair currentPosition;
	protected Color cellColour;
	protected boolean isUpdated;

	public Animal() {}

	// Method s
	public abstract double calculateHungerFactor();

	public abstract double calculateMatingFactor();

	public abstract void update(Cell currentCell);

	public abstract Pair nextPosition(LinkedList<Cell> neighbours);

    public abstract Color getColor();
}
