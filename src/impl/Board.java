package impl;

import javax.swing.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Board {

    private  int BOARD_SIZE;
    private CellButton[][] cellButtons;

    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private boolean isStopped = true;

    public Board(int boardSize) {
        this.BOARD_SIZE = boardSize;
        cellButtons = new CellButton[BOARD_SIZE][BOARD_SIZE];

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                cellButtons[i][j] = new CellButton(j, i, BOARD_SIZE);
            }
        }

    }

    public void startRepeatedUpdates() {
        executorService.scheduleAtFixedRate(this::update, 0, 100, TimeUnit.MILLISECONDS);
    }

    public void stopRepeatedUpdates() {
        executorService.shutdownNow();
    }

    public void update() {

    }

    public CellButton[][] getCellButtons() {
        return cellButtons;
    }

}
