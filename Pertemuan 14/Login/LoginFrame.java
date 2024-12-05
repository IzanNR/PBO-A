import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    // Components
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton resetButton;
    private JLabel statusLabel;

    public LoginFrame() {
        // Frame setup
        setTitle("User Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame

        // Layout
        setLayout(new BorderLayout());

        // Panels
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        JPanel buttonPanel = new JPanel(new FlowLayout());

        // Labels and Fields
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        // Buttons
        loginButton = new JButton("Login");
        resetButton = new JButton("Reset");

        // Status Label
        statusLabel = new JLabel(" ", SwingConstants.CENTER);
        statusLabel.setForeground(Color.RED);

        // Add components to form panel
        formPanel.add(usernameLabel);
        formPanel.add(usernameField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);

        // Add buttons to button panel
        buttonPanel.add(loginButton);
        buttonPanel.add(resetButton);

        // Add panels to frame
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(statusLabel, BorderLayout.NORTH);

        // Button actions
        loginButton.addActionListener(new LoginAction());
        resetButton.addActionListener(new ResetAction());
    }

    // Login Action
    private class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // Example hardcoded credentials
            if (username.equals("admin") && password.equals("1234")) {
                statusLabel.setForeground(Color.GREEN);
                statusLabel.setText("Login successful!");
            } else {
                statusLabel.setForeground(Color.RED);
                statusLabel.setText("Invalid username or password.");
            }
        }
    }

    // Reset Action
    private class ResetAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            usernameField.setText("");
            passwordField.setText("");
            statusLabel.setText(" ");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFrame frame = new LoginFrame();
            frame.setVisible(true);
        });
    }
}
