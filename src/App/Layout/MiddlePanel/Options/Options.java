package App.Layout.MiddlePanel.Options;

import App.App;
import App.Layout.MiddlePanel.Photo.Mixer.TransformMode;
import App.Layout.MiddlePanel.Photo.Photo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Options extends JPanel {
    ArrayList<Button> buttons;
    public Options(App app) {
        setPreferredSize(new Dimension(20,100));
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        buttons = new ArrayList<>();
        initButtons();
        layoutButtons();
        initListeners(app);
    }
    private void initButtons() {
        addButton(new Button("Additive"));
        addButton(new Button("Subtractive"));
        addButton(new Button("Difference"));
        addButton(new Button("Multiply"));
        addButton(new Button("Screen"));
        addButton(new Button("Negation"));
        addButton(new Button("Darken"));
        addButton(new Button("Lighten"));
        addButton(new Button("Exclusion"));
        addButton(new Button("Overlay"));
        addButton(new Button("Hard light"));
        addButton(new Button("Soft light"));
        addButton(new Button("Color dodge"));
        addButton(new Button("Color burn"));
        addButton(new Button("Reflect"));
        addButton(new Button("Transparency"));
    }
    private void initListeners(App app) {
        buttons.getFirst().addActionListener(e -> {
            app.layout.middlePanel.drawingPanel.awaitImage(TransformMode.ADDITIVE);
        });
        buttons.get(1).addActionListener(e -> {
            app.layout.middlePanel.drawingPanel.awaitImage(TransformMode.SUBTRACTIVE);
        });
        buttons.get(2).addActionListener(e -> {
            app.layout.middlePanel.drawingPanel.awaitImage(TransformMode.DIFFERENCE);
        });
        buttons.get(3).addActionListener(e -> {
            app.layout.middlePanel.drawingPanel.awaitImage(TransformMode.MULTIPLY);
        });
        buttons.get(4).addActionListener(e -> {
            app.layout.middlePanel.drawingPanel.awaitImage(TransformMode.SCREEN);
        });
        buttons.get(5).addActionListener(e -> {
            app.layout.middlePanel.drawingPanel.awaitImage(TransformMode.NEGATION);
        });
        buttons.get(6).addActionListener(e -> {
            app.layout.middlePanel.drawingPanel.awaitImage(TransformMode.DARKEN);
        });
        buttons.get(7).addActionListener(e -> {
            app.layout.middlePanel.drawingPanel.awaitImage(TransformMode.LIGHTEN);
        });
        buttons.get(8).addActionListener(e -> {
            app.layout.middlePanel.drawingPanel.awaitImage(TransformMode.EXCLUSION);
        });
        buttons.get(9).addActionListener(e -> {
            app.layout.middlePanel.drawingPanel.awaitImage(TransformMode.OVERLAY);
        });
        buttons.get(10).addActionListener(e -> {
            app.layout.middlePanel.drawingPanel.awaitImage(TransformMode.HARD_LIGHT);
        });
        buttons.get(11).addActionListener(e -> {
            app.layout.middlePanel.drawingPanel.awaitImage(TransformMode.SOFT_LIGHT);
        });
        buttons.get(12).addActionListener(e -> {
            app.layout.middlePanel.drawingPanel.awaitImage(TransformMode.COLOR_DODGE);
        });
        buttons.get(13).addActionListener(e -> {
            app.layout.middlePanel.drawingPanel.awaitImage(TransformMode.COLOR_BURN);
        });
        buttons.get(14).addActionListener(e -> {
            app.layout.middlePanel.drawingPanel.awaitImage(TransformMode.REFLECT);
        });
        buttons.get(15).addActionListener(e -> {
            app.layout.middlePanel.drawingPanel.awaitImage(TransformMode.TRANSPARENCY);
            System.out.println("Transparency");
        });

    }
    private void layoutButtons() {
        for (Button button : buttons) {
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setAlignmentY(Component.CENTER_ALIGNMENT);
            add(Box.createRigidArea(new Dimension(0, 5)));
            add(button);
        }
    }
    public void updateButtons(App app) {
//        if (app.layout.middlePanel.drawingPanel.photoManager.getActivePhoto().active){
//            for (Button button : buttons) {
//                button.setEnabled(true);
//            }
//        }
    }
    private void addButton(Button b) {
        buttons.add(b);
    }
}
