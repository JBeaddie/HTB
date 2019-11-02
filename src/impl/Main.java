package impl;

public class Main {
    public static double PRED_MATING_FACTOR;
    public static double PRED_HUNGER_FACTOR;
    public static double PREY_MATING_FACTOR;
    public static double PREY_HUNGER_FACTOR;
    public static double WATER_DENSITY_FACTOR;
    public static double MIN_WATER;

    public static void resetFactors(){
        PRED_MATING_FACTOR = 0.2;
        PRED_HUNGER_FACTOR = 0.05;
        PREY_MATING_FACTOR = 0.05;
        PREY_HUNGER_FACTOR = 0.01;
        WATER_DENSITY_FACTOR = 0.3;
        MIN_WATER = 30;
    }

    public static void main(String[] args) {
        resetFactors();

        int boardSize = 40;

        Display display = new Display(boardSize);

    }

}
