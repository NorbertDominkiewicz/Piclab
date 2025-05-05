package App.Layout.UpperPanel;

import App.App;
import App.Layout.UpperPanel.FiltersPanel.FiltersPanel;
import App.Layout.UpperPanel.SettingsPanel.SettingsPanel;

import javax.swing.*;
import java.awt.*;

public class OptionsPanel extends JPanel {
    SettingsPanel settingsPanel;
    FiltersPanel filtersPanel;
    GridBagConstraints gbc;
    public OptionsPanel(App app) {
        setOpaque(false);
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        initComponents(app);
        layoutComponents();
    }
    private void initComponents(App app) {
        settingsPanel = new SettingsPanel(app);
        filtersPanel = new FiltersPanel(app);
    }
    private void layoutComponents() {
        addFiltersPanel();
        addSettingsPanel();
    }
    private void addFiltersPanel() {
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(filtersPanel, gbc);
    }
    private void addSettingsPanel() {
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(settingsPanel, gbc);
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(App.BICE_BLUE);
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 70, 70);
    }
}
