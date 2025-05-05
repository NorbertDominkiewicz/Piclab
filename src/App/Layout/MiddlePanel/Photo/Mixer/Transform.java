package App.Layout.MiddlePanel.Photo.Mixer;

import App.Layout.MiddlePanel.Photo.Photo;

import java.awt.*;

public class Transform {
    public static void apply(Photo firstPhoto, Photo secondPhoto, TransformMode type) {
        if (firstPhoto == null || secondPhoto == null) {
            throw new NullPointerException();
        }
        if (firstPhoto.width != secondPhoto.width || firstPhoto.height != secondPhoto.height) {
            throw new IllegalArgumentException("You need same width and height");
        }

        for (int x = 0; x < firstPhoto.width; x++) {
            for (int y = 0; y < firstPhoto.height; y++) {
                Color firstPhotoColor = new Color(firstPhoto.image.getRGB(x, y));
                Color secondPhotoColor = new Color(secondPhoto.image.getRGB(x, y));

                int red = mixColors(firstPhotoColor.getRed(), secondPhotoColor.getRed(),type,0.1f);
                int green = mixColors(firstPhotoColor.getGreen(), secondPhotoColor.getGreen(),type,0.9f);
                int blue = mixColors(firstPhotoColor.getBlue(), secondPhotoColor.getBlue(),type,0.5f);

                firstPhoto.image.setRGB(x, y, new Color(red, green, blue).getRGB());
            }
        }
    }
    private static int mixColors(int colorA, int colorB, TransformMode type, float alfa) {
        float a = colorA / 255.0f;
        float b = colorB / 255.0f;

        switch (type) {
            case ADDITIVE :
                return (int) (Math.min(1f, a +b) * 255);
            case SUBTRACTIVE :
                return (int) (Math.max(0f, a + b - 1f) * 255);
            case DIFFERENCE :
                return (int) (Math.abs(a - b) * 255);
            case MULTIPLY :
                return (int) ((a * b) * 255);
            case SCREEN :
                return (int) ((1f - (1f - a) * (1f - b)) * 255);
            case NEGATION :
                return (int) ((1f - Math.abs(1f - a - b)) * 255);
            case DARKEN :
                return (int) ((!(a < b) ? b : a) * 255);
            case LIGHTEN :
                return (int) ((!(a > b) ? b : a) * 255);
            case EXCLUSION :
                return (int) ((a + b - (2f * a * b)) * 255);
            case OVERLAY :
                return (int) ((a < 0.5f ? 2f * a * b : (1f - 2 * (1f - a) * (1f - b))) * 255);
            case HARD_LIGHT :
                return (int) ((b < 0.5f ? 2f * a * b : (1f - 2 * (1f - a) * (1f - b))) * 255);
            case SOFT_LIGHT :
                return (int) ((b < 0.5f ? 2f * a * b + (a * a) * (1f - 2f * b) : Math.sqrt(a) * (2f * b - 1f) + (2f * a) * (1f - b)) * 255);
            case COLOR_DODGE :
                if (b >= 1f) return (a > 0f) ? 255 : 0;
                return (int) (Math.min(1f, a / (1f - b)) * 255);
            case COLOR_BURN :
                if (b <= 0f) return 0;
                return (int) (Math.max(0f, Math.min(1f, 1f - (1f - a) / b)) * 255);
            case REFLECT :
                return (int) (Math.min(1f, (a * a) / (1f - b + 0.00000000000001f)) * 255);
            case TRANSPARENCY:
                return (int) (((1f - alfa) * b + alfa * a) * 255);
            default :
                return colorA;
        }
    }
}
