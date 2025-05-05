package App.Layout.UpperPanel.LogoPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LogoView extends JPanel {
    BufferedImage image;
    int width, height;
    public LogoView() {
        setOpaque(false);
        try{
            image = ImageIO.read(getClass().getResource("/graphics/upperPanel/logo/logo.png"));
            width = image.getWidth();
            height = image.getHeight();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, getWidth()/6, getHeight()/3, width/3, height/3, null);
    }
}
