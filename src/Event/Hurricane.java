package Event;

import Animal.Water;
import impl.CellButton;

import java.util.Random;

public class Hurricane extends NaturalDisaster {
    @Override
    public double getFatalityFactor() {
        return 0.5;
    }

    @Override
    public double getRangeFactor() {
        return 0;
    }

    @Override
    public void occur(CellButton[][] cellButtons) {
        int x = random.nextInt(cellButtons.length);
        int y = random.nextInt(cellButtons.length);

        //length of hurricane (is a square)
        int radius = random.nextInt(cellButtons.length/2);

        for (int i = x; i < x + radius; i++) {
            for (int j = y; j < y + radius; j++) {
                if (y < cellButtons.length && x < cellButtons.length) {
                    if (getFatalityFactor() > random.nextDouble() && !(cellButtons[i][j].getCell().getAnimal() instanceof Water)) {
                        cellButtons[i][j].getCell().setAnimal(null);
                    }
                }
            }
        }
    }
}
