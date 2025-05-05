package App.Layout.UpperPanel;

import App.App;
import App.Layout.UpperPanel.LogoPanel.LogoPanel;

import javax.swing.*;
import java.awt.*;

public class UpperPanel extends JPanel {
    GridBagConstraints gbc;
    LogoPanel logoPanel;
    OptionsPanel optionsPanel;
    public UpperPanel(App app) {
        setOpaque(false);
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        initComponents(app);
        layoutComponents();
    }
    private void initComponents(App app) {
        logoPanel = new LogoPanel();
        optionsPanel = new OptionsPanel(app);
    }
    private void layoutComponents() {
        addLogo();
        addOptions();
    }
    private void addLogo() {
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.2;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(logoPanel, gbc);
    }
    private void addOptions() {
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.8;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(optionsPanel, gbc);
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 70, 70);
    }
}
