package impl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CellButton implements ActionListener {
    // Attributes
    private Cell cell;
    private JButton button = new JButton();
    private final Color DEFAULT_COLOUR = new Color(82, 62, 53);

    // Constructor
    public CellButton(int xcoord, int ycoord, int boardSize) {

        cell = new Cell(xcoord, ycoord);
        button.setBounds(10 + (xcoord * 790) / boardSize, 10 + (ycoord * 790) / boardSize, 650 / boardSize, 650 / boardSize);
        button.setBackground(DEFAULT_COLOUR);
        button.setForeground(DEFAULT_COLOUR); //EXPERIMENT WITH FOREGRND/BACKGRND
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.addActionListener(this);


    }

    // Methods
    public void display(){
        // Set new colour of button
        button.setBackground(cell.getColor());
        button.setForeground(cell.getColor());

        // Reset animal update
        if(cell.getAnimal() != null)
            cell.getAnimal().setUpdated(false);
    }

    public JButton getButton() {
        return button;
    }

    public Cell getCell() {
        return cell;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {

        }
    }
}
