package impl;

import Animal.Animal;

public class Cell{

    private int xcoord;
    private int ycoord;
    private Animal animal;

    public Cell(int xcoord, int ycoord) {
        this.xcoord = xcoord;
        this.ycoord = ycoord;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}
