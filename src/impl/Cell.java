package impl;

import Animal.Animal;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Cell{
    // Attributes
    private final Color BASE_COLOR = Color.LIGHT_GRAY;
    private List<Cell> neighbours = new ArrayList<>();

    private int xcoord;
    private int ycoord;

    private Animal animal;

    // Constructor
    public Cell(int xcoord, int ycoord) {
        this.xcoord = xcoord;
        this.ycoord = ycoord;
    }

    // Methods
    public Color getColor(){
        if(animal == null)
            return BASE_COLOR;

        return animal.getColor();
    }

    public void addNeighbours(Board board) {
        addNeighbour(board.getCell(x - 1, y - 1));
        addNeighbour(board.getCell(x, y - 1));
        addNeighbour(board.getCell(x + 1, y - 1));
        addNeighbour(board.getCell(x - 1, y));
        addNeighbour(board.getCell(x + 1, y));
        addNeighbour(board.getCell(x - 1, y + 1));
        addNeighbour(board.getCell(x, y + 1));
        addNeighbour(board.getCell(x + 1, y + 1));

    }

    public void addNeighbour(Cell cell) {
        if (cell != null && !neighbours.contains(cell)) {
            neighbours.add(cell);
        }
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

	public int getXcoord() {
		return xcoord;
	}

	public int getYcoord() {
		return ycoord;
	}

}
