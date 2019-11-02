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
        button.setBounds(100 + (xcoord * 300) / n, 10 + (ycoord * 300) / n, 250 / n, 250 / n);
        button.setBackground(DEFAULT_COLOUR);
        button.setForeground(DEFAULT_COLOUR); //EXPERIMENT WITH FOREGRND/BACKGRND
        button.setOpaque(true);
        button.setBorderPainted(false);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
