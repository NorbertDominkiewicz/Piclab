package App.Layout.BottomPanel;

import App.App;
import App.Layout.BottomPanel.Options.Options;

import javax.swing.*;
import java.awt.*;

public class BottomPanel extends JPanel {
    Options options;
    GridBagConstraints gbc;
    public BottomPanel(App app) {
        setOpaque(true);
        setBackground(App.POLYNESIAN_BLUE);
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
        gbc.fill = GridBagConstraints.BOTH;
        add(options, gbc);
    }
}
