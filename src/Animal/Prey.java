package Animal;

import Actions.Action;
import Actions.Die;
import Actions.Mate;
import Actions.Move;
import impl.Cell;
import impl.CellButton;
import impl.Main;
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
		this.hungerThreshold = Main.PREY_HUNGER_THRESHOLD;
		this.hungerFactor = 0.00001;
		this.hungerLevel = 0;

		// Mating levels
		this.matingThreshold = Main.PREY_MATING_THRESHOLD;
		this.matingLevel = 0;
		this.matingFactor = 0.01;
		setUpdated(isUpdated);
	}

	public Prey(){
		this(false);
	}

	// Methods
	@Override
	public Action update(Cell currentCell) {
		if(!isUpdated()) {
			if(calculateHungerFactor() <= hungerThreshold) {
				// The prey has died from old age
				return new Die();
			}else if (calculateMatingFactor() >= matingThreshold) {
				// The prey is looking for a mate
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

	@Override
	public Color getColor(){
		return COLOR;
	}
}
