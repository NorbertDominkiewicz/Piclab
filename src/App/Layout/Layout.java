package App.Layout;

import App.App;
import App.Layout.BottomPanel.BottomPanel;
import App.Layout.MiddlePanel.MiddlePanel;
import App.Layout.UpperPanel.UpperPanel;

import javax.swing.*;
import java.awt.*;

public class Layout extends JPanel {
    public UpperPanel upperPanel;
    public MiddlePanel middlePanel;
    public BottomPanel bottomPanel;
    GridBagConstraints gbc;
    public Layout(App app) {
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        initComponents(app);
        layoutComponents();
    }
    private void initComponents(App app) {
        upperPanel = new UpperPanel(app);
        middlePanel = new MiddlePanel(app);
        bottomPanel = new BottomPanel(app);
    }
    private void layoutComponents() {
        addUpperPanel();
        addMiddlePanel();
        addBottomPanel();
    }
    private void addUpperPanel() {
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        gbc.insets = new Insets(0, 5, 0, 5);
        add(upperPanel, gbc);
    }
    private void addMiddlePanel() {
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.8;
        gbc.insets = new Insets(10, 5, 10, 5);
        add(middlePanel, gbc);
    }
    private void addBottomPanel() {
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(bottomPanel, gbc);
    }
}
