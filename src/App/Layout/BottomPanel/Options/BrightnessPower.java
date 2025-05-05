package App.Layout.BottomPanel.Options;

import App.App;
import App.Layout.BottomPanel.Sliders.PowerSlider;

import javax.swing.*;
import java.awt.*;

public class BrightnessPower extends JPanel{
    Label label;
    PowerSlider power;
    public BrightnessPower(App app) {
        setOpaque(false);
        label = new Label("Brightness Linear");
        power = new PowerSlider(app);
        add(label);
        add(power);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
    }
}
