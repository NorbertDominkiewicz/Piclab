package App.Layout.BottomPanel.Options;

import App.App;
import App.Layout.MiddlePanel.Photo.Photo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button extends JButton {
    public Button(App app, ButtonType type) {
        if (type.equals(ButtonType.NEGATIVE)){
            setText("Negative");
        } else if (type.equals(ButtonType.RESTORE)){
            setText("Restore");
        } else if (type.equals(ButtonType.DELETE)){
            setText("Delete");
        }
        setBorderPainted(false);
        setFocusPainted(true);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setContentAreaFilled(false);
        setFont(new Font("Courier", Font.BOLD, 16));
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (type.equals(ButtonType.NEGATIVE)){
                    app.layout.middlePanel.drawingPanel.photoManager.getActivePhoto().imageInNegative();
                } else if (type.equals(ButtonType.RESTORE)){
                    app.layout.middlePanel.drawingPanel.photoManager.getActivePhoto().restoreOriginalImage();
                } else if (type.equals(ButtonType.DELETE)){
                    app.layout.middlePanel.drawingPanel.photoManager.removePhoto(app.layout.middlePanel.drawingPanel.photoManager.getActivePhoto());
                }
            }
        });
    }
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        super.paintComponent(g);
    }
}

