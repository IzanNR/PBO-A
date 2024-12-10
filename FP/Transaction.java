public class Transaction {
    private String date;
    private String type;
    private double amount;
    private String category;
    private String note;

    public Transaction(String date, String type, double amount, String category, String note) {
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.category = category;
        this.note = note == null || note.isEmpty() ? "NULL" : note;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getNote() {
        return note;
    }

    public String[] toArray() {
        return new String[]{date, type, String.valueOf(amount), category, note};
    }

    @Override
    public String toString() {
        return String.join(",", toArray());
    }
}
