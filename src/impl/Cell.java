package impl;

import Animal.Animal;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Cell{
    // Attributes
    private final Color BASE_COLOR = Color.LIGHT_GRAY;
    private List neighbours = new ArrayList<Cell>();

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

    public void



}
