package Animal;

import Actions.Action;
import Actions.Move;
import impl.Cell;
import impl.Pair;

import java.awt.*;

public class Water extends Animal {
    // Attributes
    private final Color COLOR = Color.BLUE;

    // Constructor

    // Methods
    @Override
    public Action update(Cell currentCell) {
        Pair pair = new Pair(currentCell.getXcoord(), currentCell.getYcoord());
        return new Move(pair);
    }

    @Override
    public Color getColor() {
        return COLOR;
    }
}
