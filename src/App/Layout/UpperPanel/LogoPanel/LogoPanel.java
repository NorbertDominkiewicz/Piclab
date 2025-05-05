package App.Layout.UpperPanel.LogoPanel;

import javax.swing.*;
import java.awt.*;

public class LogoPanel extends JPanel {
    LogoView logoView;
    GridBagConstraints gbc;
    public LogoPanel() {
        setOpaque(false);
        gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());
        initComponents();
        layoutComponents();
    }
    private void initComponents() {
        logoView = new LogoView();
    }
    private void layoutComponents() {
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(logoView, gbc);
    }
}
