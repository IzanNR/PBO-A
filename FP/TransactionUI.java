import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.*;
import java.util.List;

public class TransactionUI {
    private final JPanel panel;
    private final JLabel incomeLabel;
    private final JLabel expenseLabel;
    private final JLabel totalLabel;
    private final TransactionManager manager;

    public TransactionUI(TransactionManager manager) {
        this.manager = manager;

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        incomeLabel = new JLabel("Income: Rp 0,00");
        expenseLabel = new JLabel("Expense: Rp 0,00");
        totalLabel = new JLabel("Total: Rp 0,00");
    }

    public JPanel getPanel() {
        return panel;
    }

    public JLabel getIncomeLabel() {
        return incomeLabel;
    }

    public JLabel getExpenseLabel() {
        return expenseLabel;
    }

    public JLabel getTotalLabel() {
        return totalLabel;
    }

    public void updateTransactions(Map<String, List<Transaction>> data, String currentMonthFile) {
        panel.removeAll();
        double totalIncome = 0;
        double totalExpense = 0;

        // Urutkan tanggal secara terbalik
        List<String> sortedDates = new ArrayList<>(data.keySet());
        sortedDates.sort(Collections.reverseOrder()); // Tanggal terbaru di atas

        for (String date : sortedDates) {
            JPanel dayPanel = new JPanel();
            dayPanel.setBorder(new TitledBorder(date));
            dayPanel.setLayout(new BoxLayout(dayPanel, BoxLayout.Y_AXIS));

            for (Transaction transaction : data.get(date)) {
                JPanel transactionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                JLabel transactionLabel = new JLabel(transaction.getType() + " - Rp "
                        + FinanceApp.formatCurrency(transaction.getAmount()) + " - "
                        + transaction.getCategory() + " (" + transaction.getNote() + ")");

                JButton deleteButton = new JButton("Delete");

                // Delete Action
                deleteButton.addActionListener(e -> {
                    int confirmation = JOptionPane.showConfirmDialog(
                            null,
                            "Are you sure you want to delete this transaction?",
                            "Confirm Deletion",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirmation == JOptionPane.YES_OPTION) {
                        // Tentukan file CSV berdasarkan tanggal transaksi
                        String transactionFile = manager.getFileNameFromDate(transaction.getDate());

                        // Hapus transaksi dari struktur data
                        data.get(transaction.getDate()).remove(transaction);
                        if (data.get(transaction.getDate()).isEmpty()) {
                            data.remove(transaction.getDate()); // Hapus tanggal jika kosong
                        }

                        // Perbarui data di file CSV yang sesuai
                        manager.saveCSV(transactionFile, data);

                        // Jika file yang aktif adalah file transaksi, perbarui tampilan
                        if (transactionFile.equals(currentMonthFile)) {
                            updateTransactions(data, currentMonthFile);
                        }
                    }
                });

                transactionPanel.add(transactionLabel);
                transactionPanel.add(deleteButton);
                dayPanel.add(transactionPanel);

                if (transaction.getType().equals("Income")) {
                    totalIncome += transaction.getAmount();
                } else {
                    totalExpense += transaction.getAmount();
                }
            }
            panel.add(dayPanel);
        }

        incomeLabel.setText("Income: Rp " + FinanceApp.formatCurrency(totalIncome));
        expenseLabel.setText("Expense: Rp " + FinanceApp.formatCurrency(totalExpense));
        totalLabel.setText("Total: Rp " + FinanceApp.formatCurrency(totalIncome - totalExpense));
        panel.revalidate();
        panel.repaint();
    }
}
