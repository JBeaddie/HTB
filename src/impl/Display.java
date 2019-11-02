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
    private final Color PANEL_BACKGROUND_COLOUR = Color.GRAY;
    private final Color DEFAULT_COLOUR = Color.DARK_GRAY;

    private JPanel panel = new JPanel();

    public Display(int boardSize) {
        setTitle("Species survival of the fittest");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setSize(700, 500);
        setLayout(null);
        setVisible(true);

        panel.setBounds(50, 50, 600, 400);
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

        addButton(startButton, 445, 100);
        addButton(stopButton, 445, 200);


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
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
