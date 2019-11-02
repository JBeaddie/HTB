package Animal;

import impl.Cell;
import impl.Pair;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

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
	public void update(Cell currentCell) {
		if(!isUpdated) {
			//get neghbours
			// get random move
			// move to next position
			// set currentpos to null
		}
	}

	@Override
	public Pair nextPosition(LinkedList<Cell> neighbours) {
		LinkedList<Cell> emptyCells = new LinkedList<>();
		for (Cell cell: neighbours) {
			if(cell.getAnimal() == null) {
				emptyCells.add(cell);
			}
		}
		if(emptyCells.size() == 0) {
			return null;
		} else {
			Random rand = new Random();
			int randomCellIndex = rand.nextInt(emptyCells.size());
			Cell moveTo = emptyCells.get(randomCellIndex);
			return new Pair(moveTo.getXcoord(),moveTo.getYcoord());
		}
	}

	@Override
	public Color getColor(){
		return COLOR;
	}
}
