package App.Layout.BottomPanel.Options;

import javax.swing.*;
import java.awt.*;

public class Label extends JLabel {
    Label(String text) {
        super(text);
        setFont(new Font("Courier", Font.BOLD, 16));
    }
}
