package Task1;
public class Theater {
    private int availableSeats;

    public Theater(int totalSeats) {
        this.availableSeats = totalSeats;
    }


    public synchronized boolean reserve(int customerId, int tickets) {
        if (tickets <= availableSeats) {
            availableSeats -= tickets;
            System.out.println("Customer " + customerId + " reserved " + tickets + " tickets.");
            return true;
        } else {
            System.out.println("Customer " + customerId + " couldn't reserve " + tickets + " tickets.");
            return false;
        }
    }

    public synchronized int getAvailableSeats() {
        return availableSeats;
    }
}