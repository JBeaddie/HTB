package Event;

import impl.CellButton;

import java.util.Random;

public abstract class NaturalDisaster {
    //Attributes
    protected Random random = new Random();
    protected double fatalityFactor;
    protected double rangeFactor;

    //Methods
    public abstract double getFatalityFactor();

    public abstract double getRangeFactor();

    public abstract void occur(CellButton[][] cells);

}
