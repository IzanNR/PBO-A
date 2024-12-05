import java.awt.image.BufferedImage;
import java.awt.Color;

public class OFImage extends BufferedImage {

    // Konstruktor baru untuk menerima BufferedImage
    public OFImage(BufferedImage image) {
        super(image.getWidth(), image.getHeight(), image.getType());
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                setRGB(x, y, image.getRGB(x, y));
            }
        }
    }

    public OFImage(int width, int height, int imageType) {
        super(width, height, imageType);
    }

    public void darker() {
        int height = getHeight();
        int width = getWidth();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(getRGB(x, y));
                setRGB(x, y, color.darker().getRGB());
            }
        }
    }

    public void lighter() {
        int height = getHeight();
        int width = getWidth();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(getRGB(x, y));
                setRGB(x, y, color.brighter().getRGB());
            }
        }
    }

    public void threshold() {
        int height = getHeight();
        int width = getWidth();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(getRGB(x, y));
                int brightness = (color.getRed() + color.getGreen() + color.getBlue()) / 3;

                if (brightness < 85) {
                    setRGB(x, y, Color.BLACK.getRGB()); // Darker threshold
                } else if (brightness < 170) {
                    setRGB(x, y, Color.GRAY.getRGB()); // Middle threshold
                } else {
                    setRGB(x, y, Color.WHITE.getRGB()); // Brighter threshold
                }
            }
        }
    }
}
