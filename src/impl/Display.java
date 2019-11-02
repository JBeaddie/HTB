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
    private final Color PANEL_BACKGROUND_COLOUR = Color.GRAY;
    private final Color DEFAULT_COLOUR = Color.DARK_GRAY;

    private JPanel panel = new JPanelWithBackground("terrain.jpg");
    private JPanel greyPanel = new JPanel();
    private JLayeredPane layeredPane = new JLayeredPane();

    public Display(int boardSize) {

        setPreferredSize(new Dimension(700, 700));
        setLayout(new BorderLayout());
        add(layeredPane, BorderLayout.CENTER);
        layeredPane.setBounds(0, 0, 700, 700);
        setTitle("Species survival of the fittest");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        greyPanel.setBounds(25, 25, 400, 400);
        greyPanel.setBackground(PANEL_BACKGROUND_COLOUR);
        greyPanel.setOpaque(true);

        panel.setBounds(25, 25, 650, 400);


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

        addButton(startButton, 445, 50);
        addButton(stopButton, 445, 150);
        addButton(resetButton, 445, 250);


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
