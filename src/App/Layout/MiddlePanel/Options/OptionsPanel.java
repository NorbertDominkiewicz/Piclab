package App.Layout.MiddlePanel.Options;

import App.App;

import javax.swing.*;
import java.awt.*;

public class OptionsPanel extends JPanel {
    GridBagConstraints gbc;
    public Options options;
    public OptionsPanel(App app) {
        setOpaque(false);
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        initComponents(app);
        layoutComponents();
    }
    private void initComponents(App app) {
        options = new Options(app);
    }
    private void layoutComponents() {
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(options, gbc);
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(App.PUMPKIN);
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 70, 70);
    }
}
