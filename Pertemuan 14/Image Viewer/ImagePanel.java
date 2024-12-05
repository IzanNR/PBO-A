import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    private OFImage image;

    public void setImage(OFImage image) {
        this.image = image;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, null);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        if (image != null) {
            return new Dimension(image.getWidth(), image.getHeight());
        } else {
            return new Dimension(400, 300); // Default size
        }
    }
}
