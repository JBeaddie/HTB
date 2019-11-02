package impl;

import Actions.Move;
import Animal.Animal;
import Animal.*;

import javax.swing.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import Actions.Action;

public class Board {
    // Attributes
    private int BOARD_SIZE;
    private CellButton[][] cellButtons;

    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private boolean isStopped = true;

    // Constructor
    public Board(int boardSize) {
        this.BOARD_SIZE = boardSize;
        cellButtons = new CellButton[BOARD_SIZE][BOARD_SIZE];

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                cellButtons[i][j] = new CellButton(i, j, BOARD_SIZE);
            }
        }

        linkCells();
        cellButtons[10][0].getCell().setAnimal(new Prey());
        cellButtons[5][0].getCell().setAnimal(new Prey());
    }

    // Methods
    public void update() {
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
            executorService.scheduleAtFixedRate(this::update, 0, 1, TimeUnit.MILLISECONDS);
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
}
