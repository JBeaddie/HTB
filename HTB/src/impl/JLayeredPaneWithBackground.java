package impl;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class JLayeredPaneWithBackground extends JLayeredPane {

    private Image backgroundImage;

    public JLayeredPaneWithBackground(String fileName) {

        try {
             backgroundImage = ImageIO.read(new File(fileName));
        } catch (IOException e) {
            System.err.println("Error with background image.");
            System.exit(-1);
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
    }

}
