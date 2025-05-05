package App.Layout.MiddlePanel.Photo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Photo {
    public static int imageId = 0;
    public BufferedImage image;
    public BufferedImage originalImage;
    public BufferedImage imageCopy;
    public Rectangle photoArea;
    //
    public int width;
    public int height;
    public int id = 0;
    //
    public boolean active;
    public boolean visible;
    public boolean canBeSelected;
    public Photo(BufferedImage img) {
        id = ++imageId;
        image = img;
        originalImage = copyImg(image);
        imageCopy = copyImg(image);
        visible = true;
        canBeSelected = true;
        photoArea = new Rectangle(0, 0, img.getWidth(), img.getHeight());
        width = image.getWidth();
        height = image.getHeight();
        System.out.println("Photo added: " + this);
    }
    @Override
    public String toString() {
        return "ID: " + id + "\nWidth: " + width + "\nHeight: " + height;
    }
    private BufferedImage copyImg(BufferedImage img) {
        BufferedImage copy = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        Graphics2D g = copy.createGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();
        return copy;
    }
    public void imageInNegative() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixel = image.getRGB(x, y);
                Color color = new Color(pixel);
                int r = 255 - color.getRed();
                int g = 255 - color.getGreen();
                int b = 255 - color.getBlue();
                Color newColor = new Color(r, g, b);
                image.setRGB(x, y, newColor.getRGB());
            }
        }
        active = !active;
    }
    public void restoreOriginalImage() {
        System.out.println("Image restored");
        image = copyImg(originalImage);
    }
    public Photo getPhoto(){
        return this;
    }
    public void saveImage() {
        saveImage("photo_" + id + ".png");
    }

    public void saveImage(String fileName) {
        try {
            File outputFile = new File(fileName);
            ImageIO.write(image, "png", outputFile);
            System.out.println("Image saved to: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error saving image: " + e.getMessage());
        }
    }
    public void changeBrightness(double a, int brightness) {
        image = new BufferedImage(imageCopy.getWidth(), imageCopy.getHeight(), imageCopy.getType());
        Graphics g = image.createGraphics();
        g.drawImage(imageCopy, 0, 0, null);
        g.dispose();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int rgb = image.getRGB(x, y);
                Color color = new Color(rgb);
                int red = Math.min(255, Math.max(0, (int) (a * color.getRed() + brightness)));
                int green = Math.min(255, Math.max(0, (int) (a * color.getGreen() + brightness)));
                int blue = Math.min(255, Math.max(0, (int) (a * color.getBlue() + brightness)));
                image.setRGB(x, y, new Color(red, green, blue).getRGB());
            }
        }
    }
    public void changeBrightnessPow(double a, double brightness) {
        image = new BufferedImage(imageCopy.getWidth(), imageCopy.getHeight(), imageCopy.getType());
        Graphics g = image.createGraphics();
        g.drawImage(imageCopy, 0, 0, null);
        g.dispose();

        a = Math.max(0, Math.min(1, a));
        brightness = Math.max(0.1, Math.min(5, brightness));

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color color = new Color(image.getRGB(x, y));

                double r = color.getRed() / 255.0;
                double gg = color.getGreen() / 255.0;
                double b = color.getBlue() / 255.0;

                double newR = Math.pow(a, r) * Math.pow(r, brightness);
                double newG = Math.pow(a, gg) * Math.pow(gg, brightness);
                double newB = Math.pow(a, b) * Math.pow(b, brightness);

                int red = (int) (255 * Math.max(0, Math.min(1, newR)));
                int green = (int) (255 * Math.max(0, Math.min(1, newG)));
                int blue = (int) (255 * Math.max(0, Math.min(1, newB)));

                image.setRGB(x, y, new Color(red, green, blue).getRGB());
            }
        }
    }
    public void changeContrast(int value){
        image = new BufferedImage(imageCopy.getWidth(), imageCopy.getHeight(), imageCopy.getType());
        Graphics g = image.createGraphics();
        g.drawImage(imageCopy, 0, 0, null);
        g.dispose();
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                Color c = new Color(image.getRGB(x, y));
                double r = adjustContrast(c.getRed(), value);
                double gg = adjustContrast(c.getGreen(), value);
                double b = adjustContrast(c.getBlue(), value);
                image.setRGB(x,y, new Color((int) r,(int) gg,(int) b).getRGB());
            }
        }
    }
    public double adjustContrast(int rgb, int value){
        if (rgb < 128) {
            if (value > 0){
                return Math.max(0, Math.min(255, ((127 - value) / 128) * rgb));
            } else {
                return Math.max(0, Math.min(255, ((127.0 / (127 + value)) * rgb)));
            }
        } else {
            if (value > 0){
                return Math.max(0, Math.min(255, ((127.0 - value) / 127) * (rgb - 255) + 255));
            } else {
                return Math.max(0, Math.min(255, ((127.0 / (127 - value)) * (rgb - 127) + 127)));
            }
        }
    }
    public void drawPhoto(Graphics g, double scaledWidth, double scaledHeight) {
        if (visible) {
            g.drawImage(image,photoArea.x, photoArea.y, photoArea.width, photoArea.height, null);
        }
        if (active) {
            g.setColor(Color.GREEN);
            g.drawRect(photoArea.x, photoArea.y, photoArea.width, photoArea.height);
        }
        if (visible && !active) {
            g.setColor(Color.BLACK);
            g.drawRect(photoArea.x, photoArea.y, photoArea.width, photoArea.height);
        }
    }
}
