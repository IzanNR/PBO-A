import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FinanceApp {
    private final JFrame frame;
    private final TransactionManager manager;
    private TransactionUI transactionUI;
    private String currentMonthFile;
    private JComboBox<String> monthSelector;
    private Set<String> categories;

    public FinanceApp() {
        manager = new TransactionManager(".");
        categories = new HashSet<>();
        frame = new JFrame("Finance Manager");
        initializeUI();
    }

    private void initializeUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);
        frame.setLayout(new BorderLayout());

        // Set global font and color palette
        UIManager.put("Label.font", new Font("Sans Serif", Font.PLAIN, 14));
        UIManager.put("Button.font", new Font("Sans Serif", Font.PLAIN, 14));
        UIManager.put("ComboBox.font", new Font("Sans Serif", Font.PLAIN, 14));
        UIManager.put("TextField.font", new Font("Sans Serif", Font.PLAIN, 14));

        Color primaryColor = new Color(32, 33, 36);
        Color accentColor = new Color(66, 133, 244);
        Color backgroundColor = new Color(248, 249, 250);

        frame.getContentPane().setBackground(backgroundColor);

        // Initialize TransactionUI
        currentMonthFile = getDefaultCSVFile();
        transactionUI = new TransactionUI(manager);

        // Top Bar: Title and File Selector
        JPanel topBar = new JPanel();
        topBar.setLayout(new BoxLayout(topBar, BoxLayout.Y_AXIS));
        topBar.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        topBar.setBackground(backgroundColor);

        JLabel titleLabel = new JLabel("Finance Manager", JLabel.CENTER);
        titleLabel.setFont(new Font("Sans Serif", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(primaryColor);

        monthSelector = new JComboBox<>(manager.getCSVFiles().toArray(new String[0]));
        monthSelector.setMaximumSize(new Dimension(200, 30));
        monthSelector.setAlignmentX(Component.CENTER_ALIGNMENT);
        monthSelector.addActionListener(e -> {
            currentMonthFile = (String) monthSelector.getSelectedItem();
            loadTransactions(currentMonthFile);
        });

        if (currentMonthFile != null) {
            monthSelector.setSelectedItem(currentMonthFile);
        }

        topBar.add(titleLabel);
        topBar.add(Box.createRigidArea(new Dimension(0, 10)));
        topBar.add(monthSelector);
        frame.add(topBar, BorderLayout.NORTH);

        // Center Panel: Transactions
        JScrollPane scrollPane = new JScrollPane(transactionUI.getPanel());
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        scrollPane.getViewport().setBackground(backgroundColor);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Bottom Panel: Summary and Input Form
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        bottomPanel.setBackground(backgroundColor);

        // Summary Panel
        JPanel summaryPanel = new JPanel(new GridLayout(1, 3, 20, 0));
        summaryPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        summaryPanel.setBackground(backgroundColor);

        summaryPanel.add(centeredLabel(transactionUI.getIncomeLabel()));
        summaryPanel.add(centeredLabel(transactionUI.getExpenseLabel()));
        summaryPanel.add(centeredLabel(transactionUI.getTotalLabel()));

        bottomPanel.add(summaryPanel, BorderLayout.NORTH);

        // Input Form Panel
        JPanel inputPanel = new JPanel(new GridLayout(2, 5, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        inputPanel.setBackground(backgroundColor);

        JTextField dateField = new JTextField(LocalDate.now().toString());
        JTextField amountField = new JTextField();
        JComboBox<String> categoryField = new JComboBox<>(categories.toArray(new String[0]));
        categoryField.setEditable(true);
        JTextField noteField = new JTextField();
        JComboBox<String> typeField = new JComboBox<>(new String[]{"Expense", "Income"});
        JButton addButton = new JButton("Add");

        addButton.setBackground(accentColor);
        addButton.setForeground(Color.WHITE);

        inputPanel.add(new JLabel("Date:"));
        inputPanel.add(dateField);
        inputPanel.add(new JLabel("Amount:"));
        inputPanel.add(amountField);
        inputPanel.add(typeField);
        inputPanel.add(new JLabel("Category:"));
        inputPanel.add(categoryField);
        inputPanel.add(new JLabel("Note:"));
        inputPanel.add(noteField);
        inputPanel.add(addButton);

        bottomPanel.add(inputPanel, BorderLayout.SOUTH);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Add Button Action
        addButton.addActionListener(e -> {
            try {
                String category = (String) categoryField.getSelectedItem();
                if (category != null && !categories.contains(category)) {
                    categories.add(category);
                    categoryField.addItem(category);
                }

                Transaction transaction = new Transaction(
                        dateField.getText(),
                        (String) typeField.getSelectedItem(),
                        Double.parseDouble(amountField.getText()),
                        category,
                        noteField.getText()
                );

                manager.saveTransaction(transaction);

                String inputFile = manager.getFileNameFromDate(transaction.getDate());
                if (!inputFile.equals(currentMonthFile)) {
                    currentMonthFile = inputFile;
                    updateMonthSelectorAndChangeSelection(currentMonthFile);
                } else {
                    loadTransactions(currentMonthFile);
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid Input!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        loadTransactions(currentMonthFile);
        frame.setVisible(true);
    }

    private String getDefaultCSVFile() {
        List<String> files = manager.getCSVFiles();
        LocalDate now = LocalDate.now();
        String thisMonthFile = now.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM")) + ".csv";

        if (files.contains(thisMonthFile)) {
            return thisMonthFile;
        }

        String lastValidFile = null;
        for (String file : files) {
            String fileDate = file.replace(".csv", "");
            LocalDate fileLocalDate;
            try {
                fileLocalDate = LocalDate.parse(fileDate + "-01", java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (Exception e) {
                continue;
            }

            if (fileLocalDate.isBefore(now) || fileLocalDate.isEqual(now)) {
                lastValidFile = file;
            } else {
                break;
            }
        }

        return lastValidFile;
    }

    private JLabel centeredLabel(JLabel label) {
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        return label;
    }

    private void loadTransactions(String fileName) {
        if (fileName != null && transactionUI != null) {
            Map<String, List<Transaction>> data = manager.readCSV(fileName);
            transactionUI.updateTransactions(data, fileName);

            for (List<Transaction> transactions : data.values()) {
                for (Transaction transaction : transactions) {
                    categories.add(transaction.getCategory());
                }
            }
        }
    }

    private void updateMonthSelectorAndChangeSelection(String newSelection) {
        List<String> files = manager.getCSVFiles();
        monthSelector.removeAllItems();
        for (String file : files) {
            monthSelector.addItem(file);
        }
        monthSelector.setSelectedItem(newSelection);
    }

    public static String formatCurrency(double amount) {
        return String.format("%,.2f", amount).replace(",", ".").replace(".", ",");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FinanceApp::new);
    }
}
