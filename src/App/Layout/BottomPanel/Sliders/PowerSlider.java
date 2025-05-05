package App.Layout.BottomPanel.Sliders;

import App.App;
import App.Layout.MiddlePanel.Photo.Photo;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class PowerSlider extends JSlider {
    private int previousValue = 100;
    public PowerSlider(App app) {
        super(-100, 100, 0);
        setBackground(Color.WHITE);
        addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                int currentValue = source.getValue();
                int difference = currentValue - previousValue;
                previousValue = currentValue;
                double a = mapA(previousValue);
                app.layout.middlePanel.drawingPanel.photoManager.getActivePhoto().changeBrightnessPow(a,difference);
            }
        });
    }
    private double mapA(int sliderValue) {
        return 0.5 + (sliderValue / 255.0) * 1.5;
    }
}
