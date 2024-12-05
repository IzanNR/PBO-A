import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class ImageFileManager {
    public static OFImage getImage() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                return new OFImage(ImageIO.read(file));
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error loading image!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }
}
