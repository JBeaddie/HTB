package impl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display extends JFrame implements ActionListener {

    private Board board;

    private JButton startButton = new JButton("Start");
    private JButton stopButton = new JButton("Stop");
    private JButton preyButton = new JButton("Prey");
    private final Color PANEL_BACKGROUND_COLOUR = Color.PINK;

    private JPanel panel = new JPanel();

    public Display(int boardSize) {
        setTitle("Species survival of the fittest");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setSize(1000, 1000);
        setLayout(null);
        setVisible(true);

        panel.setBounds(50, 50, 900, 900);
        panel.setBackground(PANEL_BACKGROUND_COLOUR);
        panel.setOpaque(true);
        add(panel);

        board = new Board(boardSize);

        addButtons();
    }

    public void addButtons() {
        for ( CellButton[] cellRow : board.getCellButtons()) {
            for (CellButton cell : cellRow) {
                panel.add(cell.getButton());
            }
        }
        repaint();
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
