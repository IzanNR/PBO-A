import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImageViewer {
    private JFrame frame;
    private ImagePanel imagePanel;
    private OFImage currentImage;

    public ImageViewer() {
        makeFrame();
    }

    private void makeFrame() {
        frame = new JFrame("Image Viewer");
        makeMenuBar(frame);

        Container contentPane = frame.getContentPane();
        imagePanel = new ImagePanel();
        contentPane.add(imagePanel, BorderLayout.CENTER);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void makeMenuBar(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        // File Menu
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem openItem = new JMenuItem("Open");
        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });
        fileMenu.add(openItem);

        JMenuItem quitItem = new JMenuItem("Quit");
        quitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(quitItem);

        // Edit Menu
        JMenu editMenu = new JMenu("Edit");
        menuBar.add(editMenu);

        JMenuItem darkerItem = new JMenuItem("Darker");
        darkerItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyDarker();
            }
        });
        editMenu.add(darkerItem);

        JMenuItem lighterItem = new JMenuItem("Lighter");
        lighterItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyLighter();
            }
        });
        editMenu.add(lighterItem);

        JMenuItem thresholdItem = new JMenuItem("Threshold");
        thresholdItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyThreshold();
            }
        });
        editMenu.add(thresholdItem);
    }

    private void openFile() {
        OFImage image = ImageFileManager.getImage();
        if (image != null) {
            currentImage = image;
            imagePanel.setImage(image);
            frame.pack();
        }
    }

    private void applyDarker() {
        if (currentImage != null) {
            currentImage.darker();
            frame.repaint();
        } else {
            JOptionPane.showMessageDialog(frame, "No image loaded!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void applyLighter() {
        if (currentImage != null) {
            currentImage.lighter();
            frame.repaint();
        } else {
            JOptionPane.showMessageDialog(frame, "No image loaded!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void applyThreshold() {
        if (currentImage != null) {
            currentImage.threshold();
            frame.repaint();
        } else {
            JOptionPane.showMessageDialog(frame, "No image loaded!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new ImageViewer();
    }
}
