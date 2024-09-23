import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

public class Clock extends JFrame {
    private ClockDisplay clockDisplay;
    private JLabel timeLabel;
    private Timer timer;

    public Clock() {
        LocalTime now = LocalTime.now();
        clockDisplay = new ClockDisplay(now.getHour(), now.getMinute());

        setTitle("Clock Display");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        timeLabel = new JLabel(clockDisplay.getTime(), SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 40));
        add(timeLabel, BorderLayout.CENTER);

        timer = new Timer(60000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clockDisplay.timeTick();
                timeLabel.setText(clockDisplay.getTime());
            }
        });

        timer.start();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Clock();
            }
        });
    }
}
