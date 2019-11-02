package Event;

import impl.CellButton;

import java.util.Random;

public class Winter extends NaturalDisaster {
    @Override
    public double getFatalityFactor() {
        return 0.4;
    }

    @Override
    public double getRangeFactor() {
        return 1;
    }

    @Override
    public void occur(CellButton[][] cellButtons) {
        Random rnd = new Random();
        for (CellButton[] cellButtonRow : cellButtons) {
            for (CellButton cellButton : cellButtonRow) {

                //Chance that the natural disaster effects the cell.
                int chance = rnd.nextInt(10);

                if ((rangeFactor*10) >= chance) {

                    //Chance that any animal in cell dies based on FatalityFactor
                    chance = rnd.nextInt(10);

                    if ((fatalityFactor * 10) >= chance) {
                        cellButton.getCell().setAnimal(null);
                        cellButton.display();
                    }
                }

            }
        }
    }
}
