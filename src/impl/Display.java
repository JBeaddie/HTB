package impl;

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
    private JLabel predMatingFactorLabel = new JLabel("Predator Mating Factor:");
    private JLabel predHungerFactorLabel = new JLabel("Predator Hunger Factor:");
    private JLabel preyMatingFactorLabel = new JLabel("Prey Mating Factor:");
    private JLabel preyHungerFactorLabel = new JLabel("Prey Hunger Factor:");
    private JLabel waterDensityFactorLabel = new JLabel("Water Density Factor:");
    private JTextField waterDensityFactor = new JTextField();
    private JLabel minAmountOfWaterLabel = new JLabel("Min amount of Water:");
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
        addLabel(predHungerFactorLabel, 825, 350);
        addLabel(predMatingFactorLabel, 825, 400);
        addLabel(preyHungerFactorLabel, 825, 450);
        addLabel(preyMatingFactorLabel, 825, 500);
        addLabel(waterDensityFactorLabel, 825, 550);
        addLabel(minAmountOfWaterLabel, 825, 600);
        addTextField(predHungerFactor,975, 350 );
        addTextField(predMatingFactor,975, 400 );
        addTextField(preyHungerFactor,975, 450 );
        addTextField(preyMatingFactor,975, 500 );
        addTextField(waterDensityFactor, 975, 550);
        addTextField(minAmountOfWater, 975, 600);
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
        Main.PRED_HUNGER_FACTOR = PRED_HUNGER_FACTOR;
        Main.PRED_MATING_FACTOR = PRED_MATING_FACTOR;
        Main.PREY_HUNGER_FACTOR = PREY_HUNGER_FACTOR;
        Main.PREY_MATING_FACTOR = PREY_MATING_FACTOR;
        Main.WATER_DENSITY_FACTOR = WATER_DENSITY_FACTOR;
        Main.MIN_WATER = MIN_WATER;

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
            Main.resetFactors();
//            repaint();
        } else if (e.getSource() == submitFactors){
            getInitialFactors();
        }
    }
}
