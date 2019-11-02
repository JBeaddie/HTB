package Animal;

import java.awt.*;

public class Prey extends Animal {
	// Attributes
	private final Color COLOR = Color.GREEN;

	// Constructor

	// Methods
	@Override
	public double calculateHungerFactor() {
		double hunger = Math.exp(-1 * hungerFactor * matingLevel);
		hungerLevel++;
		return hunger;
	}

	@Override
	public double calculateMatingFactor() {
		double mating =1 - Math.exp(-1 * matingFactor * matingLevel);
		matingLevel++;
		return mating;
	}

	@Override
	public Color getColor(){
		return COLOR;
	}
}
