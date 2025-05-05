package App;

import App.Layout.Layout;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    public static final Color PUMPKIN = new Color(255, 103, 0);
    public static final Color FLASH_WHITE = new Color(235, 235, 235);
    public static final Color SILVER = new Color(192, 192, 192);
    public static final Color BICE_BLUE = new Color(58, 110, 165);
    public static final Color POLYNESIAN_BLUE = new Color(0, 78, 152);
    public static final Color BLUE = new Color(0, 0, 255);
    //
    public static final Dimension APP_SIZE = new Dimension(1400, 840);//
    public Layout layout;
    public App() {
        setTitle("Picture Lab");
        setSize(APP_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(APP_SIZE);
        layout = new Layout(this);
        add(layout);
        setVisible(true);
    }
    public static void main(String[] args) {
        new App();
    }
}