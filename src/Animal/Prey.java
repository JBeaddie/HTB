package Animal;

import impl.Cell;
import impl.CellButton;
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
			//get neighbours
			Pair nextMove = nextPosition(currentCell.getNeighbours());
			// get random move

			if(nextMove == null) {
				return currentCell;
			} else {
				return nextMove;
			}

		}
	}

	@Override
	public Pair nextPosition(LinkedList<Cell> neighbours) {
		// empty list for adjacent cells
		LinkedList<Cell> emptyCells = new LinkedList<>();
		// go through cells and add any that don't have an animal
		for (Cell cell: neighbours) {
			if(cell.getAnimal() == null) {
				emptyCells.add(cell);
			}
		}
		// checks that there is a possinle move
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

	@Override
	public Color getColor(){
		return COLOR;
	}
}
