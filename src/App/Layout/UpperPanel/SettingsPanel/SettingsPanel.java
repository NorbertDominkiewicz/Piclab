package App.Layout.UpperPanel.SettingsPanel;

import App.App;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SettingsPanel extends JPanel {
    ArrayList<Button> buttons;
    GridBagConstraints constraints;
    public SettingsPanel(App app) {
        setOpaque(false);
        setBackground(Color.green);
        setLayout(new GridBagLayout());
        buttons = new ArrayList<>();
        constraints = new GridBagConstraints();
        initButtons(app);
        layoutButtons();
    }
    private void initButtons(App app) {
        buttons.add(new Button("image",app));
        buttons.add(new Button("save",app));
        buttons.add(new Button("settings",app));
        buttons.add(new Button("histogram",app));
    }
    private void layoutButtons() {
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(10, 10, 10, 10);
        add(buttons.get(0), constraints);
        constraints.gridx = 1;
        add(buttons.get(1), constraints);
        constraints.gridx = 2;
        add(buttons.get(2), constraints);
        constraints.gridx = 3;
        add(buttons.get(3), constraints);

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(App.BICE_BLUE);
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 70, 70);
    }
}
