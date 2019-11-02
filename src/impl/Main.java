package impl;

public class Main {

    public static void main(String[] args) {

        try {

            if (args.length != 1) {
                throw new ArrayIndexOutOfBoundsException();
            }

            int boardSize = Integer.parseInt(args[0]);

            if (boardSize <= 9) {
                throw new NumberFormatException();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Usage: java Main <board size>");
        } catch (NumberFormatException e) {
            System.out.println("The board size must be an integer >= 10");
        }

        Display display = new Display();

    }

}
