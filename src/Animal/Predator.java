package Animal;

import Actions.Action;
import Actions.Die;
import Actions.Mate;
import Actions.Move;
import impl.Cell;
import impl.Pair;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public class Predator extends Animal {
    // Attributes
    private final Color COLOR = Color.RED;
    private double deathThreshold = 0.01;

    // Constructor
    public Predator(boolean isUpdated) {
        // Hunger levels
        this.hungerThreshold = 0.6;
        this.hungerFactor = 0.01;
        this.hungerLevel = 0;

        // Mating levels
        this.matingThreshold = 0.7;
        this.matingLevel = 0;
        this.matingFactor = 0.05;
        setUpdated(isUpdated);
    }

    public Predator() {
        this(false);
    }

    // Methods
    @Override
    public Action update(Cell currentCell) {
        if (!isUpdated()) {
            if (calculateHungerFactor() <= deathThreshold) {
                return new Die();
            } else if (calculateHungerFactor() <= hungerThreshold) {
                // The Predator is looking for food
                List<Cell> randomNeighbours = currentCell.getNeighbours();
                Collections.shuffle(randomNeighbours);

                for (Cell cell : randomNeighbours) {
                    if (cell.getAnimal() instanceof Prey) {
                        // The predator has found a prey to eat
                        // Set its hunger level to 0
                        this.hungerLevel = 0;

                        // NOTE do not need a eat action as can just move to position of prey
                        Pair preyPosition = new Pair(cell.getXcoord(), cell.getYcoord());
                        return new Move(preyPosition);
                    }
                }
            } else if (calculateMatingFactor() >= matingThreshold) {
                // The predator is looking for a mate
                boolean canMate = false;
                Cell emptyCell = null;
                Cell mateCell = null;

                // Now check if there is any other predators
                for (Cell cell : currentCell.getNeighbours()) {
                    if (cell.getAnimal() != null && cell.getAnimal() instanceof Predator && cell.getAnimal().calculateMatingFactor() > matingThreshold) {
                        canMate = true;
                        mateCell = cell;
                        if (emptyCell != null)
                            break;
                    } else if (emptyCell == null && (cell.getAnimal() == null || cell.getAnimal() instanceof Prey)) {
                        emptyCell = cell;
                    }
                }

                if (canMate && emptyCell != null) {
                    emptyCell.setAnimal(new Predator(true));
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

    private Move getMove(Cell currentCell) {
        // Get neighbors
        Pair nextMove = nextPosition(currentCell.getNeighbours());

        if (nextMove == null) {
            nextMove = new Pair(currentCell.getXcoord(), currentCell.getYcoord());
        }

        return new Move(nextMove);
    }

    @Override
    public Color getColor() {
        return COLOR;
    }
}