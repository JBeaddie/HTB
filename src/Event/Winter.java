package Event;

import Animal.Water;
import impl.CellButton;

import java.util.Random;

public class Winter extends NaturalDisaster {
    // Attributes

    // Methods
    @Override
    public double getFatalityFactor() {
        return 1;
    }

    @Override
    public double getRangeFactor() {
        return 1;
    }

    @Override
    public void occur(CellButton[][] cellButtons) {
        for(int x = 0; x < cellButtons.length; x++){
            for(int y = 0; y < cellButtons[0].length; y++){

                // Check if this cell should be affected
                if(random.nextDouble() >= 1 - getRangeFactor()){
                    if(cellButtons[x][y].getCell().getAnimal() != null && !(cellButtons[x][y].getCell().getAnimal() instanceof Water)){

                        // Should we kill this animal
                        if (random.nextDouble() >= 1 - getFatalityFactor()) {
                            cellButtons[x][y].getCell().setAnimal(null);
                            cellButtons[x][y].display();
                        }
                    }
                }
            }
        }
    }
}
