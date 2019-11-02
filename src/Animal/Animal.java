package Animal;

import impl.Pair;

import java.awt.*;

public abstract class Animal {

	protected double hungerFactor;
	protected int hungerLevel;
	protected double matingFactor;
	protected int matingLevel;
	private Pair currentPosition;
	private Color cellColour;

	public Animal() {}

	public abstract double calculateHungerFactor();

	public abstract double calculateMatingFactor();

	public void update() {

	}

	public Pair nextPosition() {
		// TODO
		return null;
	}




}
