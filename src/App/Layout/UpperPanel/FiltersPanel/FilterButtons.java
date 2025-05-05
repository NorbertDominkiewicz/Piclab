package App.Layout.UpperPanel.FiltersPanel;

import App.App;
import App.Layout.MiddlePanel.Photo.Filterer.BorderMode;
import App.Layout.MiddlePanel.Photo.Filterer.Filter;
import App.Layout.MiddlePanel.Photo.Filterer.FilterMode;
import App.Layout.MiddlePanel.Photo.Photo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FilterButtons extends JPanel {
    GridBagConstraints gbc = new GridBagConstraints();
    ArrayList<Button> buttons;

    public FilterButtons(App app) {
        setOpaque(false);
        buttons = new ArrayList<>();
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        setLayout(new GridBagLayout());
        initButtons(app);
        layoutComponents();
    }

    private void layoutComponents() {
        for (int i = 0; i < buttons.size() - 2; i++) {
            gbc.gridx = i / 2;
            gbc.gridy = i % 2;
            add(buttons.get(i), gbc);
        }
        gbc.gridx = 6;
        gbc.gridy = 1;
        add(buttons.get(buttons.size() - 2), gbc);
        gbc.gridx = 6;
        gbc.gridy = 0;
        add(buttons.get(buttons.size() - 1), gbc);
    }

    private void initButtons(App app) {
        buttons.add(new Button("Roberts"));
        buttons.get(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Filter.apply(app.layout.middlePanel.drawingPanel.photoManager.getActivePhoto(), FilterMode.ROBERTS, BorderMode.CONSTANT,0);
            }
        });
        buttons.add(new Button("Min"));
        buttons.get(1).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Filter.apply(app.layout.middlePanel.drawingPanel.photoManager.getActivePhoto(), FilterMode.MINIMUM, BorderMode.CONSTANT,0);
            }
        });
        buttons.add(new Button("Prewitt"));
        buttons.get(2).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Filter.apply(app.layout.middlePanel.drawingPanel.photoManager.getActivePhoto(), FilterMode.PREWITT, BorderMode.CONSTANT,0);
            }
        });
        buttons.add(new Button("Max"));
        buttons.get(3).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Filter.apply(app.layout.middlePanel.drawingPanel.photoManager.getActivePhoto(), FilterMode.MAXIMUM, BorderMode.CONSTANT,0);
            }
        });
        buttons.add(new Button("Sobela"));
        buttons.get(4).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Filter.apply(app.layout.middlePanel.drawingPanel.photoManager.getActivePhoto(), FilterMode.SOBEL, BorderMode.CONSTANT,0);
            }
        });
        buttons.add(new Button("Medianowy"));
        buttons.get(5).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Filter.apply(app.layout.middlePanel.drawingPanel.photoManager.getActivePhoto(), FilterMode.MEDIAN, BorderMode.CONSTANT,0);
            }
        });
        buttons.add(new Button("Laplace B"));
        buttons.get(6).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Filter.apply(app.layout.middlePanel.drawingPanel.photoManager.getActivePhoto(), FilterMode.LAPLACIAN_BASIC, BorderMode.CONSTANT,0);
            }
        });
        buttons.add(new Button("Laplace E"));
        buttons.get(7).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Filter.apply(app.layout.middlePanel.drawingPanel.photoManager.getActivePhoto(), FilterMode.LAPLACIAN_EXTENDED, BorderMode.CONSTANT,0);
            }
        });
        buttons.add(new Button("Laplace A"));
        buttons.get(8).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Filter.apply(app.layout.middlePanel.drawingPanel.photoManager.getActivePhoto(), FilterMode.LAPLACIAN_ALTERNATIVE, BorderMode.CONSTANT,0);
            }
        });
        buttons.add(new Button("Rozmycie"));
        buttons.get(9).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Filter.apply(app.layout.middlePanel.drawingPanel.photoManager.getActivePhoto(), FilterMode.BOX_BLUR, BorderMode.CONSTANT,5);
            }
        });
    }
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(App.SILVER);
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}
