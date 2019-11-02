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

    private JPanel panel = new JPanelWithBackground("mars-terrain.jpg");
    private JPanel greyPanel = new JPanel();
    private JLayeredPane layeredPane = new JLayeredPaneWithBackground("stars-3.jpg");

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

        panel.setBounds(25, 25, 925, 1000);


        layeredPane.add(greyPanel, 1, 0);
        layeredPane.add(panel, 0, 0);
        pack();
        setVisible(true);



        board = new Board(boardSize);

        addButtons();
    }

    public void addButtons() {
        for ( CellButton[] cellRow : board.getCellButtons()) {
            for (CellButton cell : cellRow) {
                greyPanel.add(cell.getButton());
            }
        }

        addButton(startButton, 830, 50);
        addButton(stopButton, 830, 150);
        addButton(resetButton, 830, 250);


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



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            board.startRepeatedUpdates();
        } else if (e.getSource() == stopButton) {
            board.stopRepeatedUpdates();
        } else if (e.getSource() == resetButton) {
            board.reset();
//            repaint();
        }
    }
}
