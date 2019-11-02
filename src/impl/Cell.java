package impl;

import interfaces.ICell;

public class Cell implements ICell {

    private int xcoord;
    private int ycoord;

    public Cell(int xcoord, int ycoord) {
        this.xcoord = xcoord;
        this.ycoord = ycoord;
    }
}
