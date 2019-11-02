package Animal;

public class Prey extends Animal {


	@Override
	public double calculateHungerFactor() {
		double hunger = Math.exp(-1 * hungerFactor * matingLevel);
		return hunger;
	}

	@Override
	public double calculateMatingFactor() {
		double mating =1 - Math.exp(-1 * matingFactor * matingLevel);
		return mating;
	}
}
