package Task1;
import java.util.Random;

public class TicketReservationSystem {
    private static final int TOTAL_SEATS = 10;
    private static final int CUSTOMERS = 15;

    public static void main(String[] args) {
        Theater theater = new Theater(TOTAL_SEATS);
        Random random = new Random();
        Thread[] customers = new Thread[CUSTOMERS];


        for (int i = 0; i < CUSTOMERS; i++) {
            int tickets = random.nextInt(4) + 1;
            customers[i] = new Thread(new Customer(theater, i + 1, tickets));
        }


        for (Thread customer : customers) {
            customer.start();
        }


        for (Thread customer : customers) {
            try {
                customer.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("\nSeats left: " + theater.getAvailableSeats());
    }
}
