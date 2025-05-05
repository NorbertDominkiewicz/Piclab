package App.Layout.BottomPanel.Options;

import App.App;
import App.Layout.BottomPanel.Sliders.LinearSlider;

import javax.swing.*;
import java.awt.*;

public class BrightnessLinear extends JPanel {
    Label label;
    LinearSlider linear;
    public BrightnessLinear(App app) {
        setOpaque(false);
        label = new Label("Brightness Linear");
        linear = new LinearSlider(app);
        add(label);
        add(linear);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
    }
}
