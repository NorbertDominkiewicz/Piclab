package App.Layout.UpperPanel.SettingsPanel;

import App.App;
import App.Layout.MiddlePanel.Photo.Histogram.Viewer;
import App.Layout.MiddlePanel.Photo.Photo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Button extends JButton {
    BufferedImage image;
    int width, height;
    public Button(String text, App app) {
        setPreferredSize(new Dimension(40, 40));
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setOpaque(false);
        try{
            if (text.equals("image")) {
                image = ImageIO.read(getClass().getResource("/graphics/upperPanel/Image File.png"));
                addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        JFileChooser chooser = new JFileChooser();
                        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                        chooser.setAcceptAllFileFilterUsed(true);
                        int returnVal = chooser.showOpenDialog(null);
                        if (returnVal == JFileChooser.APPROVE_OPTION) {
                            BufferedImage image = null;
                            try{
                                image = ImageIO.read(chooser.getSelectedFile());
                                app.layout.middlePanel.drawingPanel.photoManager.addPhoto(new Photo(image));
                            } catch (IOException er) {
                                er.printStackTrace();
                            }
                        }
                    }
                });
            }
            else if (text.equals("settings")) {
                image = ImageIO.read(getClass().getResource("/graphics/upperPanel/Settings.png"));
            }
            else if (text.equals("save")) {
                image = ImageIO.read(getClass().getResource("/graphics/upperPanel/Save.png"));
                addActionListener(e -> {
                    app.layout.middlePanel.drawingPanel.photoManager.getActivePhoto().saveImage();
                });
            } else {
                image = ImageIO.read(getClass().getResource("/graphics/upperPanel/Chart.png"));
                addActionListener(e -> {
                    new Viewer(app.layout.middlePanel.drawingPanel.photoManager.getActivePhoto());
//                    new HistogramViewer(app.layout.middlePanel.drawingPanel.photoManager.getActivePhoto());
                    app.layout.middlePanel.drawingPanel.photoManager.makeAllPhotosInactive();
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            width = image.getWidth();
            height = image.getHeight();
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillOval(0, 0, width + 10, height + 10);
        g.drawImage(image, 5, 5, width, height, null);
    }
}
