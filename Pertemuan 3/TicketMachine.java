public class TicketMachine {
    private int price;
    private int balance;
    private int total;

    public TicketMachine(int ticketCost) {
        if(ticketCost > 0) {
            this.price = ticketCost;
        } else {
            this.price = 20; //default
        }
        this.balance = 0;
        this.total = 0;
    }

    public void insertMoney(int amount) {
        if(amount > 0){
            balance += amount;
        }else{
            System.out.println("Invalid amount");
        }
    }

    public int getPrice() {
        return price;
    }

    public int getBalance() {
        return balance;
    }

    public int getTotal() {
        return total;
    }

    public void printTicket() {
        // Simulate the printing of a ticket.
        if(balance >= price) {
            // Print the ticket and update the total and balance
            System.out.println("Ticket Price : " + price + " cents.");

            // Update the total collected with the price.
            total += price;
            // Reduce the balance by the price.
            balance -= price;
        } else {
            System.out.println("You must insert at least : " + (price - balance) + " more cencts.");
        }
    }
}
