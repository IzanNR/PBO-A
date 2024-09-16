public class Main {
    public static void main(String[] args) {
        TicketMachine X = new TicketMachine(30);

        X.insertMoney(50);
        System.out.println("Balance : " + X.getBalance() + " cents.");
        X.printTicket();
        System.out.println("Balance : " + X.getBalance() + " cents.");
    }
}
