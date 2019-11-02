package impl;

import Animal.Animal;
import Animal.*;

import javax.swing.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Board {
    // Attributes
    private  int BOARD_SIZE;
    private CellButton[][] cellButtons;

    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private boolean isStopped = true;

    // Constructor
    public Board(int boardSize) {
        this.BOARD_SIZE = boardSize;
        cellButtons = new CellButton[BOARD_SIZE][BOARD_SIZE];

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                cellButtons[i][j] = new CellButton(j, i, BOARD_SIZE);
            }
        }

        linkCells();
        cellButtons[0][0].getCell().setAnimal(new Prey());
    }

    // Methods
    public void update(){
        // Loop through each cell in the board
        for(int x = 0; x < BOARD_SIZE; x++){
            for(int y = 0; y < BOARD_SIZE; y++){
                // Get each animal in the cell
                Animal animal = cellButtons[x][y].getCell().getAnimal();

                // Check the animal is non null
                if(animal == null)
                    continue;

                Pair pair = animal.update(cellButtons[x][y].getCell());

                // Check if pair is different from current
                if(pair.getX() != x && pair.getY() != y){
                    // Move the animal
                    cellButtons[pair.getX()][pair.getX()].getCell().setAnimal(animal);
                    cellButtons[x][y].getCell().setAnimal(null);
                }

                // Animal has been updated
                animal.setUpdated(true);
            }
        }

        // Loop through each cell in the board
        for(int x = 0; x < BOARD_SIZE; x++){
            for(int y = 0; y < BOARD_SIZE; y++){
                // Display button
                cellButtons[x][y].display();
            }
        }
    }

    public void startRepeatedUpdates() {
        if (isStopped) {
            System.out.println("banana");
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
        try {
            return cellButtons[y][x].getCell();
        } catch(ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public void linkCells() {
        for (CellButton[] cellButtonRow: cellButtons) {
            for (CellButton cellButton: cellButtonRow) {
                cellButton.getCell().addNeighbours(this);
            }
        }
    }

    public CellButton[][] getCellButtons() {
        return cellButtons;
    }
}
