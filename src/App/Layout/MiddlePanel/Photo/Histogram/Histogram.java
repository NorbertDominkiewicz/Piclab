package App.Layout.MiddlePanel.Photo.Histogram;

import java.awt.image.BufferedImage;
import java.awt.Color;

public class Histogram {
    private BufferedImage image;
    private int[][] rgbHistograms;
    private int[][] rgbCumulativeHistograms;

    public Histogram(BufferedImage image) {
        this.image = image;
        calculateRGBHistograms();
        calculateRGBCumulativeHistograms();
    }

    private void calculateRGBHistograms() {
        rgbHistograms = new int[3][256];
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = new Color(image.getRGB(x, y));
                rgbHistograms[0][color.getRed()]++;
                rgbHistograms[1][color.getGreen()]++;
                rgbHistograms[2][color.getBlue()]++;
            }
        }
    }

    private void calculateRGBCumulativeHistograms() {
        rgbCumulativeHistograms = new int[3][256];
        for (int ch = 0; ch < 3; ch++) {
            rgbCumulativeHistograms[ch][0] = rgbHistograms[ch][0];
            for (int i = 1; i < 256; i++) {
                rgbCumulativeHistograms[ch][i] = rgbHistograms[ch][i] + rgbCumulativeHistograms[ch][i - 1];
            }
        }
    }

    public BufferedImage equalizeHistogram() {
        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        int totalPixels = image.getWidth() * image.getHeight();

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = new Color(image.getRGB(x, y));
                int r = (255 * rgbCumulativeHistograms[0][color.getRed()]) / totalPixels;
                int g = (255 * rgbCumulativeHistograms[1][color.getGreen()]) / totalPixels;
                int b = (255 * rgbCumulativeHistograms[2][color.getBlue()]) / totalPixels;

                r = Math.max(0, Math.min(255, r));
                g = Math.max(0, Math.min(255, g));
                b = Math.max(0, Math.min(255, b));

                result.setRGB(x, y, new Color(r, g, b).getRGB());
            }
        }
        return result;
    }

    public BufferedImage stretchHistogram(int a, int b, int c, int d) {
        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

        if (a < 0 || b < 0) {
            int[] minMaxR = findMinMax(0);
            int[] minMaxG = findMinMax(1);
            int[] minMaxB = findMinMax(2);
            a = Math.min(Math.min(minMaxR[0], minMaxG[0]), minMaxB[0]);
            b = Math.max(Math.max(minMaxR[1], minMaxG[1]), minMaxB[1]);
        }

        if (a == b) {
            a = 0;
            b = 255;
        }

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = new Color(image.getRGB(x, y));
                int r = stretchValue(color.getRed(), a, b, c, d);
                int g = stretchValue(color.getGreen(), a, b, c, d);
                int bVal = stretchValue(color.getBlue(), a, b, c, d);

                r = Math.max(0, Math.min(255, r));
                g = Math.max(0, Math.min(255, g));
                bVal = Math.max(0, Math.min(255, bVal));

                result.setRGB(x, y, new Color(r, g, bVal).getRGB());
            }
        }
        return result;
    }

    private int stretchValue(int value, int a, int b, int c, int d) {
        return (value - a) * (d - c) / (b - a) + c;
    }

    private int[] findMinMax(int channel) {
        int min = 255;
        int max = 0;
        for (int i = 0; i < 256; i++) {
            if (rgbHistograms[channel][i] > 0) {
                min = Math.min(min, i);
                max = Math.max(max, i);
            }
        }
        return new int[]{min, max};
    }

    public int[] getRedHistogram() { return rgbHistograms[0]; }
    public int[] getGreenHistogram() { return rgbHistograms[1]; }
    public int[] getBlueHistogram() { return rgbHistograms[2]; }
}