package App.Layout.UpperPanel.FiltersPanel;
import javax.swing.*;
import java.awt.*;

public class Button extends JButton {
    public Button(String text) {
        super(text);
        setPreferredSize(new Dimension(120, 40));
        setBorderPainted(false);
        setFocusPainted(true);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setContentAreaFilled(false);
    }
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
        super.paintComponent(g);
    }
}