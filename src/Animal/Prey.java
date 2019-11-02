package Animal;

import Actions.Action;
import Actions.Die;
import Actions.Mate;
import Actions.Move;
import impl.Cell;
import impl.Pair;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Prey extends Animal {
	// Attributes
	private final Color COLOR = Color.GREEN;

	// Constructor
	public Prey(boolean isUpdated){
		// Hunger levels
		this.hungerThreshold = 0.1;
		this.hungerFactor = 0.011;
		this.hungerLevel = 0;

		// Mating levels
		this.matingThreshold = 0.7;
		this.matingLevel = 0;
		this.matingFactor = 0.01;
		setUpdated(isUpdated);
	}

	public Prey(){
		this(false);
	}

	// Methods
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

	@Override
	public Action update(Cell currentCell) {
		System.out.println(this.calculateMatingFactor());

		if(!isUpdated()) {
			// Check if mating level is above 0.7
			if(calculateHungerFactor() <= hungerThreshold) {
				return new Die();
			}else if (calculateMatingFactor() >= matingThreshold) {
				boolean canMate = false;
				Cell emptyCell = null;
				Cell mateCell = null;

				// Now check if there is any other prey
				for (Cell cell : currentCell.getNeighbours()) {
					if (cell.getAnimal() != null && cell.getAnimal() instanceof Prey && cell.getAnimal().calculateMatingFactor() > matingThreshold) {
						canMate = true;
						mateCell = cell;
						if (emptyCell != null)
							break;
					} else if (emptyCell == null && cell.getAnimal() == null) {
						emptyCell = cell;
					}
				}

				if (canMate && emptyCell != null) {
					emptyCell.setAnimal(new Prey(true));
					mateCell.getAnimal().setUpdated(true);
					mateCell.getAnimal().updateLevels();
					mateCell.getAnimal().setMatingLevel(0);
					matingLevel = 0;
					return new Mate();
				} else {
					return getMove(currentCell);
				}
			} else {
				return getMove(currentCell);
			}
		}

		return null;
	}

	private Move getMove(Cell currentCell){
		// Get neighbors
		Pair nextMove = nextPosition(currentCell.getNeighbours());

		if(nextMove == null){
			nextMove = new Pair(currentCell.getXcoord(), currentCell.getYcoord());
		}

		return new Move(nextMove);
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
			Cell moveTo = emptyCells.get(randomCellIndex);
			return new Pair(moveTo.getXcoord(),moveTo.getYcoord());
		}
	}

	@Override
	public Color getColor(){
		return COLOR;
	}
}
