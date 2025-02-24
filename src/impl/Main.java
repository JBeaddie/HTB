package impl;

public class Main {
    public static double PRED_MATING_FACTOR;
    public static double PRED_HUNGER_FACTOR;
    public static double PREY_MATING_FACTOR;
    public static double PREY_HUNGER_FACTOR;
    public static double WATER_DENSITY_FACTOR = 0.3;
    public static double MIN_WATER = 120;

    public static void resetFactors(){
        PRED_MATING_FACTOR = 0.2;
        PRED_HUNGER_FACTOR = 0.01;
        PREY_MATING_FACTOR = 0.08;
        PREY_HUNGER_FACTOR = 0.1;
    }

    public static void main(String[] args) {
        resetFactors();

        int boardSize = 40;

        Display display = new Display(boardSize);

    }

}
