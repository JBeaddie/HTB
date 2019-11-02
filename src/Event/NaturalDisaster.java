package Event;

import impl.CellButton;

public abstract class NaturalDisaster {
    //Attributes
    protected double fatalityFactor;
    protected double rangeFactor;

    //Methods
    public abstract double getFatalityFactor();

    public abstract double getRangeFactor();

    public abstract void occur(CellButton[][] cells);

}
