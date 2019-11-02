package impl;

import Actions.Die;
import Actions.Move;
import Animal.Animal;
import Animal.*;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import Actions.Action;
import Event.NaturalDisaster;
import Event.Winter;

public class Board {
    // Attributes
    private int BOARD_SIZE;
    private CellButton[][] cellButtons;

    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private boolean isStopped = true;

    private final int DEFAULT_NUM_PREY = 10;

    //TODO randomly pick which disaster is the next one.
    private NaturalDisaster currentDisaster = new Winter();
    private final int DISASTER_LOTTERY_NUM = 1;
    boolean naturalDisasters = true;
    private final int DISASTER_CHANCE = 10;

    // Constructor
    public Board(int boardSize) {
        this.BOARD_SIZE = boardSize;

        //Makes sure that the number of prey is not more than the number of cells.
        if (DEFAULT_NUM_PREY > BOARD_SIZE*BOARD_SIZE) {
            System.err.println("Error: you chose more prey than tiles.");
            System.exit(0);
        }

        cellButtons = new CellButton[BOARD_SIZE][BOARD_SIZE];

        //Randomly adds prey to the Board.
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                cellButtons[i][j] = new CellButton(i, j, BOARD_SIZE);
            }
        }

        linkCells();


        for (Pair p : getRandomCoords()) {
            cellButtons[p.getX()][p.getY()].getCell().setAnimal(new Prey());
        }



    }

    // Methods
    public void update() {

        if (naturalDisasters) {
            Random rnd = new Random();
            int chance = rnd.nextInt(DISASTER_CHANCE);
            if (DISASTER_LOTTERY_NUM == chance) {
                System.out.println("DISASTER OCCURRED");
                currentDisaster.occur(cellButtons);
            }
        }



        // Loop through each cell in the board
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                // Get each animal in the cell
                Animal animal = cellButtons[x][y].getCell().getAnimal();

                // Check the animal is non null
                if (animal == null)
                    continue;

                Action action = animal.update(cellButtons[x][y].getCell());

                // Check if current action is a move
                if(action instanceof Move) {
                    Pair pair = ((Move) action).getPair();

                    if (pair.getX() != x && pair.getY() != y) {
                        // Move the animal
                        cellButtons[pair.getX()][pair.getY()].getCell().setAnimal(animal);
                        cellButtons[x][y].getCell().setAnimal(null);
                    }
                }

                // Animal has been updated
                animal.setUpdated(true);
                animal.updateLevels();

                // Check if action is die
                if(action instanceof Die){
                    cellButtons[x][y].getCell().setAnimal(null);
                }
            }
        }

        // Loop through each cell in the board
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                // Display button
                cellButtons[x][y].display();
            }
        }
    }

    public void startRepeatedUpdates() {
        if (isStopped) {
            executorService = Executors.newSingleThreadScheduledExecutor();
            executorService.scheduleAtFixedRate(this::update, 0, 100, TimeUnit.MILLISECONDS);
            isStopped = false;
        }
    }

    public void stopRepeatedUpdates() {
        if (!isStopped) {
            executorService.shutdownNow();
            isStopped = true;
        }
    }


    public Cell getCell(int x, int y) {
        return cellButtons[x][y].getCell();
    }

    public void linkCells() {
        for (CellButton[] cellButtonRow : cellButtons) {
            for (CellButton cellButton : cellButtonRow) {
                cellButton.getCell().addNeighbours(this);
            }
        }
    }

    public int getBOARD_SIZE() {
        return BOARD_SIZE;
    }

    public CellButton[][] getCellButtons() {
        return cellButtons;
    }

    public Set<Pair> getRandomCoords() {

        Set randomCoords = new HashSet();
        Random random = new Random();
        do {
            int x = random.nextInt(BOARD_SIZE);
            int y = random.nextInt(BOARD_SIZE);

            randomCoords.add(new Pair(x, y));
        } while (randomCoords.size() < DEFAULT_NUM_PREY);

        return randomCoords;
    }

    public void naturalDisaster() {
        if (currentDisaster != null) {
            currentDisaster.occur(cellButtons);
        }
    }


}
