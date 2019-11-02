package Animal;

import java.awt.*;

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
    public Color getColor() {
        return COLOR;
    }
}
