package impl;

import Animal.Animal;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Cell{
    // Attributes
    private final Color BASE_COLOR = new Color(82, 62, 53);
    private int[][] touchingPoints = new int[][]{{1,0},{-1,0},{0,1},{0,-1},{1,-1},{1,1},{-1,-1},{-1,1}};
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

	public List<Cell> getNeighbours() {
		return neighbours;
	}

	public void addNeighbours(Board board) {
        for(int[] pair : touchingPoints){
            // Check within bounds
            int newx = xcoord + pair[0];
            int newy = ycoord + pair[1];

            // Check within bounds
            if(newx >= 0 && newx < board.getBOARD_SIZE() && newy >= 0 && newy < board.getBOARD_SIZE()){
                neighbours.add(board.getCell(newx, newy));
            }
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
