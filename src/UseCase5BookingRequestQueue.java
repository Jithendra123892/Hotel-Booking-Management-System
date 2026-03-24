import java.util.*;

/**
 * Book My Stay App
 * Use Case 5: Booking Request Queue (FIFO)
 *
 * Demonstrates fair handling of booking requests using Queue (FIFO principle).
 *
 * @author Jithendra
 * @version 5.0
 */
public class UseCase5BookingRequestQueue {

    // -------- Reservation Class --------
    static class Reservation {
        private String guestName;
        private String roomType;

        public Reservation(String guestName, String roomType) {
            this.guestName = guestName;
            this.roomType = roomType;
        }

        public String getGuestName() {
            return guestName;
        }

        public String getRoomType() {
            return roomType;
        }

        public void display() {
            System.out.println("Guest: " + guestName + " | Room Type: " + roomType);
        }
    }

    // -------- Booking Queue --------
    static class BookingRequestQueue {
        private Queue<Reservation> queue;

        public BookingRequestQueue() {
            queue = new LinkedList<>();
        }

        // Add booking request
        public void addRequest(Reservation reservation) {
            queue.offer(reservation);
            System.out.println("Request added for: " + reservation.getGuestName());
        }

        // Display all requests (FIFO order)
        public void displayQueue() {
            System.out.println("\n--- Booking Requests (FIFO Order) ---");

            if (queue.isEmpty()) {
                System.out.println("No booking requests available.");
                return;
            }

            for (Reservation r : queue) {
                r.display();
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println(" Book My Stay App - Version 5.0");
        System.out.println("=====================================\n");

        BookingRequestQueue queue = new BookingRequestQueue();

        // Simulating booking requests
        queue.addRequest(new Reservation("Alice", "Single Room"));
        queue.addRequest(new Reservation("Bob", "Double Room"));
        queue.addRequest(new Reservation("Charlie", "Suite Room"));

        // Display queue
        queue.displayQueue();

        System.out.println("\nAll requests are stored in FIFO order.");
    }
}
