package App.Layout.MiddlePanel;

import App.App;
import App.Layout.MiddlePanel.Options.OptionsPanel;

import javax.swing.*;
import java.awt.*;

public class MiddlePanel extends JPanel {
    GridBagConstraints gbc;
    public OptionsPanel optionsPanel;
    public DrawingPanel drawingPanel;
    public MiddlePanel(App app) {
        setOpaque(true);
        setBackground(App.FLASH_WHITE);
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        initComponents(app);
        layoutComponents();
    }
    private void initComponents(App app) {
        optionsPanel = new OptionsPanel(app);
        drawingPanel = new DrawingPanel(app);
    }
    private void layoutComponents() {
        addThis();
        addOptions();
    }
    private void addThis(){
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.9;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(drawingPanel,gbc);
    }
    private void addOptions(){
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.10;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(optionsPanel,gbc);
    }
}
