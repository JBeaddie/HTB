package Animal;

import impl.Pair;

import java.awt.*;

public abstract class Animal {
	// Attributes
	protected double hungerFactor;
	protected int hungerLevel;
	protected double matingFactor;
	protected int matingLevel;
	private Pair currentPosition;
	private Color cellColour;

	// Constructor

	// Method s
	public abstract double calculateHungerFactor();

	public abstract double calculateMatingFactor();

	public abstract Pair nextPosition();

    public abstract Color getColor();
}
