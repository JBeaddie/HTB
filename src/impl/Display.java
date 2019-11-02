package impl;

import jdk.internal.org.objectweb.asm.tree.IntInsnNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display extends JFrame implements ActionListener {

    private Board board;

    private JButton startButton = new JButton("Start");
    private JButton stopButton = new JButton("Stop");
    private JButton resetButton = new JButton("Reset");
    private JButton preyButton = new JButton("Prey");
    private final Color PANEL_BACKGROUND_COLOUR = new Color(156, 112, 75);
    private final Color DEFAULT_COLOUR = new Color(156, 112, 75);
    private JTextField predMatingFactor = new JTextField();
    private JTextField predHungerFactor = new JTextField();
    private JTextField preyMatingFactor = new JTextField();
    private JTextField preyHungerFactor = new JTextField();
    private JTextField numOfPrey = new JTextField();
    private JTextField numOfPred = new JTextField();
    private JLabel predMatingFactorLabel = new JLabel("Pred Mating Factor:");
    private JLabel numOfPreyLabel = new JLabel("Number Of Prey");
    private JLabel numOfPredLabel = new JLabel("Number of Predators");
    private JLabel predHungerFactorLabel = new JLabel("Pred Hunger Factor:");
    private JLabel preyMatingFactorLabel = new JLabel("Prey Mating Factor:");
    private JLabel preyHungerFactorLabel = new JLabel("Prey Hunger Factor:");
    private JLabel waterDensityFactorLabel = new JLabel("Water Density:");
    private JTextField waterDensityFactor = new JTextField();
    private JLabel minAmountOfWaterLabel = new JLabel("Min Water:");
    private JTextField minAmountOfWater = new JTextField();

    private JButton submitFactors = new JButton("Submit Factors");

    private JPanel panel = new JPanelWithBackground("mars-terrain.jpg");
    private JPanel greyPanel = new JPanel();
    private JLayeredPane layeredPane = new JLayeredPaneWithBackground("stars-3.jpg");

    // Constructor
    public Display(int boardSize) {

        setPreferredSize(new Dimension(1920, 1080));
        setLayout(new BorderLayout());
        add(layeredPane, BorderLayout.CENTER);
        layeredPane.setBounds(0, 0, 1920, 1080);
        setTitle("Species survival of the fittest");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        greyPanel.setBounds(25, 25, 800, 800);
        greyPanel.setBackground(PANEL_BACKGROUND_COLOUR);
        greyPanel.setOpaque(true);

        panel.setBounds(25, 25, 1100, 800);


        layeredPane.add(greyPanel, 1, 0);
        layeredPane.add(panel, 0, 0);
        pack();
        setVisible(true);



        board = new Board(boardSize);

        addButtons();
        addLabelsAndTextFields();
        repaint();
    }

    // Methods

    // ********** BUTTONS **********
    public void addButtons() {
        for ( CellButton[] cellRow : board.getCellButtons()) {
            for (CellButton cell : cellRow) {
                greyPanel.add(cell.getButton());
            }
        }

        addButton(startButton, 900, 50);
        addButton(stopButton, 900, 150);
        addButton(resetButton, 900, 250);


        repaint();
    }

    public void addButton(JButton button, int x, int y) {
        button.setBounds(x, y, 100, 50);
        button.setBackground(DEFAULT_COLOUR);
        button.setForeground(Color.WHITE);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.addActionListener(this);
        panel.add(button);
    }

    public void addLabel(JLabel label, int x, int y) {
        label.setBounds(x, y, 150, 50);
        label.setBackground(DEFAULT_COLOUR);
        label.setForeground(Color.WHITE);
        label.setOpaque(true);
        panel.add(label);
    }

    public void addTextField(JTextField field, int x, int y) {
        field.setBounds(x, y, 100, 50);
        field.setBackground(DEFAULT_COLOUR);
        field.setForeground(Color.WHITE);
        field.setOpaque(true);
        panel.add(field);
    }

    public void addLabelsAndTextFields() {
        addLabel(predHungerFactorLabel, 825, 325);
        addLabel(predMatingFactorLabel, 825, 375);
        addLabel(preyHungerFactorLabel, 825, 425);
        addLabel(preyMatingFactorLabel, 825, 475);
        addLabel(waterDensityFactorLabel, 825, 525);
        addLabel(minAmountOfWaterLabel, 825, 575);
        addTextField(predHungerFactor,975, 325 );
        addTextField(predMatingFactor,975, 375 );
        addTextField(preyHungerFactor,975, 425 );
        addTextField(preyMatingFactor,975, 475 );
        addTextField(waterDensityFactor, 975, 525);
        addTextField(minAmountOfWater, 975, 575);
        addLabel(numOfPreyLabel, 825, 625);
        addLabel(numOfPredLabel, 825, 675);
        addTextField(numOfPrey, 975, 625);
        addTextField(numOfPred, 975, 675);
        addButton(submitFactors, 900, 750);

    }

    //********* END OF BUTTONS *********//

    public void getInitialFactors() {
        double PRED_MATING_FACTOR = 0.2;
        double PRED_HUNGER_FACTOR = 0.05;
        double PREY_MATING_FACTOR = 0.05;
        double PREY_HUNGER_FACTOR = 0.01;
        double WATER_DENSITY_FACTOR = 0.3;
        double MIN_WATER = 30;
        int NO_OF_PREY = 15;
        int NO_OF_PRED = 3;
        try {
            if (Double.parseDouble(predHungerFactor.getText()) <= 1 && Double.parseDouble(predHungerFactor.getText()) > 0) {
                PRED_HUNGER_FACTOR = Double.parseDouble(predHungerFactor.getText());
            }
        } catch (NumberFormatException e) { }
        try {
            if (Double.parseDouble(predMatingFactor.getText()) <= 1 && Double.parseDouble(predMatingFactor.getText()) > 0) {
                PRED_MATING_FACTOR = Double.parseDouble(predMatingFactor.getText());
            }
        } catch (NumberFormatException e) { }
        try {
            if (Double.parseDouble(preyHungerFactor.getText()) <= 1 && Double.parseDouble(preyHungerFactor.getText()) > 0) {
                PREY_HUNGER_FACTOR = Double.parseDouble(predHungerFactor.getText());
            }
        } catch (NumberFormatException e) { }
        try {
            if (Double.parseDouble(preyMatingFactor.getText()) <= 1 && Double.parseDouble(preyMatingFactor.getText()) > 0) {
                PREY_MATING_FACTOR = Double.parseDouble(preyMatingFactor.getText());
            }
        } catch (NumberFormatException e) { }
        try {
            if (Double.parseDouble(waterDensityFactor.getText()) <= 1 && Double.parseDouble(waterDensityFactor.getText()) > 0) {
                WATER_DENSITY_FACTOR = Double.parseDouble(waterDensityFactor.getText());
            }
        } catch (NumberFormatException e) { }
        try {
            if (Double.parseDouble(minAmountOfWater.getText()) <= board.getBOARD_SIZE() * board.getBOARD_SIZE() && Double.parseDouble(minAmountOfWater.getText()) >= 0) {
                MIN_WATER = Double.parseDouble(minAmountOfWater.getText());
            }
        } catch (NumberFormatException e) { }
        try {
            if (Integer.parseInt(numOfPred.getText()) <= board.getBOARD_SIZE() * board.getBOARD_SIZE() && Integer.parseInt(numOfPred.getText()) >= 0) {
                NO_OF_PRED = Integer.parseInt(numOfPred.getText());
            }
        } catch (NumberFormatException e) { }
        try {
            if (Integer.parseInt(numOfPrey.getText()) <= board.getBOARD_SIZE() * board.getBOARD_SIZE() && Integer.parseInt(numOfPrey.getText()) >= 0) {
                NO_OF_PREY = Integer.parseInt(numOfPrey.getText());
            }
        } catch (NumberFormatException e) { }
        Main.PRED_HUNGER_FACTOR = PRED_HUNGER_FACTOR;
        Main.PRED_MATING_FACTOR = PRED_MATING_FACTOR;
        Main.PREY_HUNGER_FACTOR = PREY_HUNGER_FACTOR;
        Main.PREY_MATING_FACTOR = PREY_MATING_FACTOR;
        Main.WATER_DENSITY_FACTOR = WATER_DENSITY_FACTOR;
        Main.MIN_WATER = MIN_WATER;
        Board.DEFAULT_NUM_PRED = NO_OF_PRED;
        Board.DEFAULT_NUM_PREY = NO_OF_PREY;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            getInitialFactors();
            board.startRepeatedUpdates();
        } else if (e.getSource() == stopButton) {
            board.stopRepeatedUpdates();
        } else if (e.getSource() == resetButton) {
            board.reset();
//            Main.resetFactors();
//            repaint();
        } else if (e.getSource() == submitFactors){
            getInitialFactors();
            board.reset();
        }
    }
}
