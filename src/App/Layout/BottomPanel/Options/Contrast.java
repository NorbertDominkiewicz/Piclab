package App.Layout.BottomPanel.Options;

import App.App;
import App.Layout.BottomPanel.Sliders.ContrastSlider;
import App.Layout.BottomPanel.Sliders.PowerSlider;

import javax.swing.*;
import java.awt.*;

public class Contrast extends JPanel {
    Label label;
    ContrastSlider contrast;
    public Contrast(App app) {
        setOpaque(false);
        label = new Label("Contrast");
        contrast = new ContrastSlider(app);
        add(label);
        add(contrast);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
    }
}
