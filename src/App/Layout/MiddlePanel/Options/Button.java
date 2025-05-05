package App.Layout.MiddlePanel.Options;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {
    public Button(String text) {
        super(text);
        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setContentAreaFilled(false);
        setMaximumSize(new Dimension(110,30));
        setPreferredSize(new Dimension(110,30));
        setMinimumSize(new Dimension(110,30));
    }
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
        super.paintComponent(g);
    }
}
