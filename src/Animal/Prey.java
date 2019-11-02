package Animal;

import impl.Cell;
import impl.CellButton;
import impl.Pair;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
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
	public Pair update(Cell currentCell) {
		if(!isUpdated()) {
			//get neighbours
			Pair nextMove = nextPosition(currentCell.getNeighbours());
			// get random move

			if(nextMove == null) {
				return new Pair(currentCell.getXcoord(), currentCell.getYcoord());
			} else {
				return nextMove;
			}

		}
		return new Pair(currentCell.getXcoord(), currentCell.getYcoord());
	}


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
			System.out.println(randomCellIndex);
			Cell moveTo = emptyCells.get(randomCellIndex);
			return new Pair(moveTo.getXcoord(),moveTo.getYcoord());
		}
	}

	@Override
	public Color getColor(){
		return COLOR;
	}
}
