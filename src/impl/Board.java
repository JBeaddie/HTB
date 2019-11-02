package impl;

import javax.swing.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

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

    public CellButton[][] getCellButtons() {
        return cellButtons;
    }


}
