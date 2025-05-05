package App.Layout.BottomPanel.Options;

import App.App;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class Options extends JPanel {
    // brightness liniowo
    // brightness potega
    // negatyw
    // contrast
    // delete
    // original restore
    ArrayList<Button> buttons;
    BrightnessLinear brightnessLinear;
    BrightnessPower brightnessPower;
    Contrast contrast;
    GridBagConstraints gbc;
    public Options(App app) {
        setOpaque(false);
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        buttons = new ArrayList<>();
        initComponents(app);
        layoutComponents();
    }
    private void initComponents(App app) {
        buttons.add(new Button(app,ButtonType.NEGATIVE));
        buttons.add(new Button(app,ButtonType.RESTORE));
        buttons.add(new Button(app,ButtonType.DELETE));
        brightnessLinear = new BrightnessLinear(app);
        brightnessPower = new BrightnessPower(app);
        contrast = new Contrast(app);
    }
    private void layoutComponents() {
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(0, 5, 0, 5);
        gbc.weightx = 0.4;
        gbc.weighty = 1;
        add(buttons.getFirst(), gbc);
        gbc.gridx = 1;
        add(brightnessLinear, gbc);
        gbc.gridx = 2;
        add(brightnessPower, gbc);
        gbc.gridx = 3;
        add(contrast, gbc);
        gbc.gridx = 4;
        add(buttons.get(1), gbc);
        gbc.gridx = 5;
        add(buttons.getLast(), gbc);
    }
}
