package Animal;

import Actions.Action;
import impl.Cell;
import impl.Pair;

import java.awt.*;
import java.util.List;

public class Predator extends Animal {
    // Attributes
    private final Color COLOR = Color.RED;

    // Constructor

    // Methods
    @Override
    public double calculateHungerFactor() {
        return 0;
    }

    @Override
    public double calculateMatingFactor() {
        return 0;
    }

    @Override
    public Action update(Cell currentCell) {
        return null;
    }

    @Override
    public Pair nextPosition(List<Cell> neighbours) {
        return null;
    }

    @Override
    public Color getColor() {
        return COLOR;
    }
}
