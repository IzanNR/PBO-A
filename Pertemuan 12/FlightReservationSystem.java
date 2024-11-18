// Superclass: Ticket
class Ticket {
    protected double baseFare;
    protected String passengerName;

    // Constructor
    public Ticket(double baseFare, String passengerName) {
        this.baseFare = baseFare;
        this.passengerName = passengerName;
    }

    // Method untuk menghitung tarif, akan di-override oleh subclass
    public double calculateFare() {
        return baseFare;
    }

    // Method untuk mendapatkan jenis tiket (akan di-override di subclass)
    public String getTicketType() {
        return "General";
    }

    // Method untuk menampilkan detail tiket
    public void displayTicketInfo() {
        System.out.println("Passenger: " + passengerName);
        System.out.println("Ticket Type: " + getTicketType());
        System.out.println("Base Fare: $" + baseFare);
        System.out.println("Total Fare: $" + calculateFare());
    }
}

// Subclass: EconomyTicket
class EconomyTicket extends Ticket {
    public EconomyTicket(double baseFare, String passengerName) {
        super(baseFare, passengerName);
    }

    // Override metode calculateFare untuk diskon 10%
    @Override
    public double calculateFare() {
        return baseFare * 0.9; // Diskon 10%
    }

    // Override metode getTicketType untuk jenis tiket
    @Override
    public String getTicketType() {
        return "Economy";
    }
}

// Subclass: BusinessTicket
class BusinessTicket extends Ticket {
    public BusinessTicket(double baseFare, String passengerName) {
        super(baseFare, passengerName);
    }

    // Override metode calculateFare untuk biaya tambahan 25%
    @Override
    public double calculateFare() {
        return baseFare * 1.25; // Tambahan 25%
    }

    // Override metode getTicketType untuk jenis tiket
    @Override
    public String getTicketType() {
        return "Business";
    }
}

// Subclass: FirstClassTicket
class FirstClassTicket extends Ticket {
    public FirstClassTicket(double baseFare, String passengerName) {
        super(baseFare, passengerName);
    }

    // Override metode calculateFare untuk biaya tambahan 50%
    @Override
    public double calculateFare() {
        return baseFare * 1.5; // Tambahan 50%
    }

    // Override metode getTicketType untuk jenis tiket
    @Override
    public String getTicketType() {
        return "First Class";
    }
}

// Main Class untuk menjalankan program
public class FlightReservationSystem {
    public static void main(String[] args) {
        // Membuat objek dari masing-masing jenis tiket
        Ticket economyTicket = new EconomyTicket(200, "Izan");
        Ticket businessTicket = new BusinessTicket(300, "Nafis");
        Ticket firstClassTicket = new FirstClassTicket(500, "Rahman");

        // Menampilkan informasi tiket menggunakan polimorfisme
        System.out.println("= Flight Reservation System =");
        economyTicket.displayTicketInfo();
        System.out.println();
        businessTicket.displayTicketInfo();
        System.out.println();
        firstClassTicket.displayTicketInfo();
    }
}
