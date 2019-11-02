package impl;

import interfaces.ICell;
import interfaces.ICellButton;

import javax.swing.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Board {

    private  int BOARD_SIZE;
    private ICellButton[][] cellButtons;

    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private boolean isStopped = true;

    public Board(int boardSize) {
        this.BOARD_SIZE = boardSize;
        cellButtons = new ICellButton[BOARD_SIZE][BOARD_SIZE];

    }

}
