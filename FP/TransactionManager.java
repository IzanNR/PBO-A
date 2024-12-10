import java.io.*;
import java.util.*;

public class TransactionManager {
    private final File directory;

    public TransactionManager(String directoryPath) {
        this.directory = new File(directoryPath);
        if (!directory.exists()) directory.mkdirs();
    }

    public List<String> getCSVFiles() {
        File[] files = directory.listFiles((dir, name) -> name.matches("\\d{4}-\\d{2}\\.csv"));
        if (files == null) return new ArrayList<>();
        List<String> fileNames = new ArrayList<>();
        for (File file : files) fileNames.add(file.getName());
        Collections.sort(fileNames);
        return fileNames;
    }

    public Map<String, List<Transaction>> readCSV(String fileName) {
        Map<String, List<Transaction>> data = new TreeMap<>();
        File file = new File(directory, fileName);

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    Transaction transaction = new Transaction(
                            parts[0],
                            parts[1],
                            Double.parseDouble(parts[2]),
                            parts[3],
                            parts[4]
                    );
                    data.computeIfAbsent(transaction.getDate(), k -> new ArrayList<>()).add(transaction);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public void saveTransaction(Transaction transaction) {
        String fileName = getFileNameFromDate(transaction.getDate());
        Map<String, List<Transaction>> data = readCSV(fileName);
        data.computeIfAbsent(transaction.getDate(), k -> new ArrayList<>()).add(transaction);
        saveCSV(fileName, data);
    }

    public void saveCSV(String fileName, Map<String, List<Transaction>> data) {
        File file = new File(directory, fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String date : data.keySet()) {
                for (Transaction transaction : data.get(date)) {
                    writer.write(transaction.toString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Ubah metode ini dari private ke public
    public String getFileNameFromDate(String date) {
        // Format tanggal menjadi file CSV berdasarkan "YYYY-MM"
        String[] parts = date.split("-");
        return parts[0] + "-" + parts[1] + ".csv";
    }
}
