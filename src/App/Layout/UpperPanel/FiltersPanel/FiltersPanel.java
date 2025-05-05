package App.Layout.UpperPanel.FiltersPanel;

import App.App;

import javax.swing.*;
import java.awt.*;

public class FiltersPanel extends JPanel {
    FilterButtons filterButtons;
    public FiltersPanel(App app) {
        setOpaque(true);
        setBackground(App.SILVER);
        setLayout(new BorderLayout());
        filterButtons = new FilterButtons(app);
        add(filterButtons, BorderLayout.CENTER);
        setPreferredSize((new Dimension(40,20)));
    }
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(new Color(225, 225, 225));
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        super.paintComponent(g);
    }
}
