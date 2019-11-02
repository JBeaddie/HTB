package impl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CellButton implements ActionListener {

    private Cell cell;
    private JButton button = new JButton();
    private final Color DEFAULT_COLOUR = Color.GREEN;

    public CellButton(int xcoord, int ycoord, int boardSize) {

        cell = new Cell(xcoord, ycoord);
        button.setBounds(2 + (xcoord * 400) / boardSize, 2 + (ycoord * 400) / boardSize, 350 / boardSize, 350 / boardSize);
        button.setBackground(DEFAULT_COLOUR);
        button.setForeground(DEFAULT_COLOUR); //EXPERIMENT WITH FOREGRND/BACKGRND
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.addActionListener(this);


    }

    public JButton getButton() {
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {

        }
    }
}
