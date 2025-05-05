package App.Layout.MiddlePanel.Photo.Histogram;

import App.App;
import App.Layout.MiddlePanel.Photo.Photo;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

class CustomButton extends JButton {
    public CustomButton(String text) {
        super(text);
        setOpaque(false);
        setContentAreaFilled(false);
        setForeground(Color.WHITE);
        setFont(new Font("Monospaced", Font.BOLD, 14));
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(App.PUMPKIN);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
        super.paintComponent(g2);
        g2.dispose();
    }
}

public class Viewer extends JFrame {
    private Histogram processor;
    private BufferedImage originalImage;
    private JLabel imageLabel;
    private JTextArea histogramTextArea;

    public Viewer(Photo photo) {
        this.originalImage = photo.image;
        this.processor = new Histogram(photo.image);
        initUI();
        setVisible(true);
    }

    private void initUI() {
        setTitle("Histogram Viewer");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));
        getContentPane().setBackground(App.BICE_BLUE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(App.BICE_BLUE);

        imageLabel = new JLabel(new ImageIcon(scaleImage(originalImage, originalImage.getWidth(), originalImage.getHeight())));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JScrollPane imageScrollPane = new JScrollPane(imageLabel);
        imageScrollPane.setBackground(App.SILVER);
        imageScrollPane.setBorder(BorderFactory.createLineBorder(App.SILVER, 2));

        histogramTextArea = new JTextArea();
        histogramTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        histogramTextArea.setEditable(false);
        histogramTextArea.setBackground(new Color(240, 240, 240));
        updateHistogramText();

        JScrollPane textScrollPane = new JScrollPane(histogramTextArea);
        textScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(App.PUMPKIN, 2), "RGB Histogram Values", TitledBorder.CENTER, TitledBorder.TOP, new Font("Monospaced", Font.BOLD, 14), App.PUMPKIN));

        JPanel controlPanel = createControlPanel();

        JPanel leftPanel = new JPanel(new BorderLayout(10, 10));
        leftPanel.add(imageScrollPane, BorderLayout.CENTER);
        leftPanel.add(controlPanel, BorderLayout.SOUTH);

        mainPanel.add(leftPanel, BorderLayout.CENTER);
        mainPanel.add(textScrollPane, BorderLayout.EAST);

        add(mainPanel);
    }

    private BufferedImage scaleImage(BufferedImage src, int width, int height) {
        BufferedImage scaled = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = scaled.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(src, 0, 0, width, height, null);
        g2.dispose();
        return scaled;
    }

    private void updateHistogramText() {
        StringBuilder sb = new StringBuilder();
        int[] red = processor.getRedHistogram();
        int[] green = processor.getGreenHistogram();
        int[] blue = processor.getBlueHistogram();

        sb.append(String.format("%-10s | %-8s %-8s %-8s%n", "Intensity", "Red", "Green", "Blue"));
        sb.append("-----------|---------------------------\n");

        for (int i = 0; i < 256; i++) {
            if (red[i] > 0 || green[i] > 0 || blue[i] > 0) {
                sb.append(String.format("%-10d | %-8d %-8d %-8d%n",
                        i, red[i], green[i], blue[i]));
            }
        }
        histogramTextArea.setText(sb.toString());
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panel.setBackground(App.BICE_BLUE);

        CustomButton equalizeBtn = new CustomButton("EQUALIZE HISTOGRAM");
        equalizeBtn.addActionListener(e -> {
            BufferedImage result = processor.equalizeHistogram();
            imageLabel.setIcon(new ImageIcon(scaleImage(result, result.getWidth(), result.getHeight())));
            processor = new Histogram(result);
            updateHistogramText();
        });

        CustomButton stretchBtn = new CustomButton("STRETCH HISTOGRAM");
        stretchBtn.addActionListener(e -> showStretchDialog());

        panel.add(equalizeBtn);
        panel.add(stretchBtn);

        return panel;
    }

    private void showStretchDialog() {
        JDialog dialog = new JDialog(this, "Histogram Stretching", true);
        dialog.setLayout(new GridLayout(5, 2, 10, 10));
        dialog.setSize(400, 250);
        dialog.getContentPane().setBackground(App.BICE_BLUE);
        dialog.setLocationRelativeTo(this);

        JSpinner aSpinner = createSpinner(-1, -1, 254, 1);
        JSpinner bSpinner = createSpinner(-1, -1, 255, 1);
        JSpinner cSpinner = createSpinner(0, 0, 254, 1);
        JSpinner dSpinner = createSpinner(255, 1, 255, 1);

        JButton autoBtn = new CustomButton("AUTO STRETCH");
        autoBtn.addActionListener(e -> {
            aSpinner.setValue(-1);
            bSpinner.setValue(-1);
        });

        JButton applyBtn = new CustomButton("APPLY STRETCH");
        applyBtn.addActionListener(e -> {
            int a = (int) aSpinner.getValue();
            int b = (int) bSpinner.getValue();
            int c = (int) cSpinner.getValue();
            int d = (int) dSpinner.getValue();

            BufferedImage result = processor.stretchHistogram(a, b, c, d);
            imageLabel.setIcon(new ImageIcon(scaleImage(result, 700, 500)));
            processor = new Histogram(result);
            updateHistogramText();
            dialog.dispose();
        });

        addLabelAndSpinner(dialog, "Input Min (a):", aSpinner);
        addLabelAndSpinner(dialog, "Input Max (b):", bSpinner);
        addLabelAndSpinner(dialog, "Output Min (c):", cSpinner);
        addLabelAndSpinner(dialog, "Output Max (d):", dSpinner);
        dialog.add(autoBtn);
        dialog.add(applyBtn);

        dialog.setVisible(true);
    }

    private JSpinner createSpinner(int value, int min, int max, int step) {
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(value, min, max, step));
        spinner.setFont(new Font("Monospaced", Font.PLAIN, 14));
        return spinner;
    }

    private void addLabelAndSpinner(JDialog dialog, String labelText, JSpinner spinner) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Monospaced", Font.BOLD, 14));
        label.setForeground(Color.WHITE);
        dialog.add(label);
        dialog.add(spinner);
    }
}