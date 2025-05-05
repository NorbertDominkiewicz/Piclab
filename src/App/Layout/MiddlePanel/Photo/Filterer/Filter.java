package App.Layout.MiddlePanel.Photo.Filterer;

import App.Layout.MiddlePanel.Photo.Photo;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

enum Direction {
    X,
    Y
}

public class Filter {
    public static void apply(Photo photo, FilterMode filter, BorderMode border, int size) {
        BufferedImage result = null;

        switch (filter) {
            case ROBERTS:
                result = applyOnEdges(photo, filter, border);
                break;
            case PREWITT:
                result = applyOnEdges(photo, filter, border);
                break;
            case SOBEL:
                result = applyOnEdges(photo, filter, border);
                break;
            case BOX_BLUR:
                result = applyBoxBlur(photo, size, border);
                break;
            case MINIMUM:
                result = applyMinimumFilter(photo, border);
                break;
            case MAXIMUM:
                result = applyMaximumFilter(photo, border);
                break;
            case MEDIAN:
                result = applyMedianFilter(photo, border);
                break;
            case LAPLACIAN_BASIC:
            case LAPLACIAN_EXTENDED:
            case LAPLACIAN_ALTERNATIVE:
                result = applyLaplacianFilter(photo, filter, border);
                break;
        }

        if (result != null) {
            photo.image = result;
        }
    }
    private static BufferedImage applyLaplacianFilter(Photo photo, FilterMode filter, BorderMode border) {
        int[][] matrix;
        switch (filter) {
            case LAPLACIAN_BASIC:
                matrix = new int[][]{{0, -1, 0}, {-1, 4, -1}, {0, -1, 0}};
                break;
            case LAPLACIAN_EXTENDED:
                matrix = new int[][]{{-1, -1, -1}, {-1, 8, -1}, {-1, -1, -1}};
                break;
            case LAPLACIAN_ALTERNATIVE:
                matrix = new int[][]{{-2, 1, -2}, {1, 4, 1}, {-2, 1, -2}};
                break;
            default:
                matrix = new int[][]{{0, -1, 0}, {-1, 4, -1}, {0, -1, 0}};
        }

        BufferedImage result = new BufferedImage(photo.width, photo.height, photo.image.getType());
        int radius = matrix.length / 2;

        for (int y = 0; y < photo.height; y++) {
            for (int x = 0; x < photo.width; x++) {
                int sum = 0;

                for (int ky = -radius; ky <= radius; ky++) {
                    for (int kx = -radius; kx <= radius; kx++) {
                        int px = getPixel(x + kx, photo.width, border);
                        int py = getPixel(y + ky, photo.height, border);
                        int gray = getGrayValue(photo.image.getRGB(px, py));
                        sum += gray * matrix[ky + radius][kx + radius];
                    }
                }

                int value = clamp(sum + 128);
                result.setRGB(x, y, new Color(value, value, value).getRGB());
            }
        }
        return result;
    }
    private static BufferedImage applyMinimumFilter(Photo photo, BorderMode border) {
        BufferedImage result = new BufferedImage(photo.width, photo.height, photo.image.getType());
        int radius = 1;

        for (int y = 0; y < photo.height; y++) {
            for (int x = 0; x < photo.width; x++) {
                int minValue = getExtremeValue(photo, x, y, radius, true, border);
                result.setRGB(x, y, new Color(minValue, minValue, minValue).getRGB());
            }
        }
        return result;
    }
    private static BufferedImage applyMaximumFilter(Photo photo, BorderMode border) {
        BufferedImage result = new BufferedImage(photo.width, photo.height, photo.image.getType());
        int radius = 1;

        for (int y = 0; y < photo.height; y++) {
            for (int x = 0; x < photo.width; x++) {
                int maxValue = getExtremeValue(photo, x, y, radius, false, border);
                result.setRGB(x, y, new Color(maxValue, maxValue, maxValue).getRGB());
            }
        }
        return result;
    }
    private static BufferedImage applyMedianFilter(Photo photo, BorderMode border) {
        BufferedImage result = new BufferedImage(photo.width, photo.height, photo.image.getType());
        int radius = 1;

        for (int y = 0; y < photo.height; y++) {
            for (int x = 0; x < photo.width; x++) {
                int[] neighbors = getNeighborValues(photo, x, y, radius, border);
                int medianValue = median(neighbors);
                result.setRGB(x, y, new Color(medianValue, medianValue, medianValue).getRGB());
            }
        }
        return result;
    }

    private static int getExtremeValue(Photo photo, int x, int y, int radius, boolean findMin, BorderMode border) {
        int [] neighbours = getNeighborValues(photo,x,y,radius, border);
        if (findMin){
            return min(neighbours);
        } else {
            return max(neighbours);
        }
    }
    private static int min(int[] array) {
        int min = array[0];
        for (int val : array){
            if (val < min) {
                min = val;
            }
        };
        return min;
    }
    private static int median(int[] array) {
        int[] sorted = array.clone();
        Arrays.sort(sorted);
        return sorted[sorted.length / 2];
    }
    private static int max(int[] array) {
        int max = array[0];
        for (int val : array) {
            if (val > max) {
                max = val;
            }
        }
        return max;
    }
    private static int[] getNeighborValues(Photo photo, int x, int y, int radius, BorderMode border) {
        int[] values = new int[(2 * radius + 1) * (2 * radius + 1)];
        int index = 0;

        for (int ky = -radius; ky <= radius; ky++) {
            for (int kx = -radius; kx <= radius; kx++) {
                int px = getPixel(x + kx, photo.width, border);
                int py = getPixel(y + ky, photo.height, border);
                values[index++] = getGrayValue(photo.image.getRGB(px, py));
            }
        }
        return values;
    }
    private static BufferedImage applyBoxBlur(Photo photo, int size, BorderMode border) {
        double[][] matrix = new double[size][size];
        double value = 1.0 / (Math.pow(size, 2));
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = value;
            }
        }
        return applyConvolution(photo, matrix, border);
    }
    private static BufferedImage applyConvolution(Photo photo, double[][] matrix, BorderMode border) {
        BufferedImage result = new BufferedImage(photo.width, photo.height, photo.image.getType());
        int radius = matrix.length / 2;

        for (int y = 0; y < photo.height; y++) {
            for (int x = 0; x < photo.width; x++) {
                double r = 0, g = 0, b = 0;

                for (int ky = -radius; ky <= radius; ky++) {
                    for (int kx = -radius; kx <= radius; kx++) {
                        int px = getPixel(x + kx, photo.width, border);
                        int py = getPixel(y + ky, photo.height, border);
                        Color color = new Color(photo.image.getRGB(px, py));
                        double weight = matrix[ky + radius][kx + radius];

                        r += color.getRed() * weight;
                        g += color.getGreen() * weight;
                        b += color.getBlue() * weight;
                    }
                }

                result.setRGB(x, y, new Color(
                        clamp((int) r),
                        clamp((int) g),
                        clamp((int) b)
                ).getRGB());
            }
        }
        return result;
    }
    private static BufferedImage applyOnEdges(Photo photo, FilterMode filter, BorderMode border) {
        BufferedImage result = new BufferedImage(photo.width, photo.height, photo.image.getType());

        for (int x = 0; x < photo.width; x++) {
            for (int y = 0; y < photo.height; y++) {
                int gx = 0;
                int gy = 0;

                switch (filter) {
                    case ROBERTS:
                        gx = applyRoberts(photo, x, y, Direction.X, border);
                        gy = applyRoberts(photo, x, y, Direction.Y, border);
                        break;
                    case PREWITT:
                        gx = applyPrewitt(photo, x, y, Direction.X, border);
                        gy = applyPrewitt(photo, x, y, Direction.Y, border);
                        break;
                    case SOBEL:
                        gx = applySobel(photo, x, y, Direction.X, border);
                        gy = applySobel(photo, x, y, Direction.Y, border);
                        break;
                }

                int rgb = clamp((int) Math.sqrt(gx * gx + gy * gy));
                result.setRGB(x, y, new Color(rgb, rgb, rgb).getRGB());
            }
        }
        return result;
    }
    private static int applyRoberts(Photo photo, int x, int y, Direction direction, BorderMode border) {
        if(direction == Direction.X) {
            int p1 = getGrayPixel(photo, x, y, border);
            int p2 = getGrayPixel(photo, x + 1, y + 1, border);
            return p1 - p2;
        } else {
            int p1 = getGrayPixel(photo, x + 1, y, border);
            int p2 = getGrayPixel(photo, x, y + 1, border);
            return p1 - p2;
        }
    }
    private static int applyPrewitt(Photo photo, int x, int y, Direction direction, BorderMode border) {
        int[][] matrix;
        if (direction == Direction.X) {
            matrix = new int[][]{{-1, 0, 1}, {-1, 0, 1}, {-1, 0, 1}};
        } else {
            matrix = new int[][]{{-1, -1, -1}, {0, 0, 0}, {1, 1, 1}};
        }
        return applyChanges(photo, x, y, matrix, border);
    }

    private static int applySobel(Photo photo, int x, int y, Direction direction, BorderMode border) {
        int[][] matrix;
        if (direction == Direction.X) {
            matrix = new int[][]{{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}};
        } else {
            matrix = new int[][]{{-1, -2, -1}, {0, 0, 0}, {1, 2, 1}};
        }
        return applyChanges(photo, x, y, matrix, border);
    }
    private static int getGrayPixel(Photo photo, int x, int y, BorderMode border) {
        x = getPixel(x, photo.width, border);
        y = getPixel(y, photo.height, border);
        return getGrayValue(photo.image.getRGB(x, y));
    }
    private static int getPixel(int cord, int max, BorderMode border) {
        if (cord >= 0 && cord < max) {
            return cord;
        }
        switch (border) {
            case CONSTANT :
                return 0;
            case REFLECT:
                if (cord < 0){
                    return -1 * cord -1;
                }
                return 2 * max - cord -1;
            default:
                return 0;
        }
    }
    private static int applyChanges(Photo photo, int x, int y, int[][] matrix, BorderMode border) {
        int sum = 0;
        int radius = matrix.length / 2;
        for (int i = -radius; i <= radius; i++) {
            for (int j = -radius; j <= radius; j++) {
                int px = getPixel(x + j, photo.width, border);
                int py = getPixel(y + i, photo.height, border);
                int gray = getGrayValue(photo.image.getRGB(px, py));
                sum += gray * matrix[i + radius][j + radius];
            }
        }
        return sum;
    }
    private static int getGrayValue(int rgb){
        Color c = new Color(rgb);
        return (c.getRed() + c.getGreen() + c.getBlue())/3;
    }
    private static int clamp(int value) {
        return Math.max(0, Math.min(255, value));
    }
}
