import java.util.*;

/**
 * ============================================================
 * MAIN CLASS - UseCase11ConcurrentBookingSimulation
 * ============================================================
 *
 * Demonstrates thread-safe booking using synchronization.
 *
 * @version 11.0
 */

// -------- Reservation --------
class Reservation {
    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// -------- Shared Inventory --------
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 1); // only 1 to show conflict
    }

    // Critical section
    public synchronized boolean allocateRoom(String type, String guest) {

        int available = inventory.getOrDefault(type, 0);

        if (available > 0) {
            System.out.println(guest + " is booking " + type);

            // simulate delay (race condition scenario)
            try { Thread.sleep(100); } catch (Exception e) {}

            inventory.put(type, available - 1);

            System.out.println("Booking SUCCESS for " + guest);
            return true;
        } else {
            System.out.println("Booking FAILED for " + guest + " (No rooms)");
            return false;
        }
    }

    public void display() {
        System.out.println("Final Inventory: " + inventory);
    }
}

// -------- Booking Processor (Thread) --------
class BookingProcessor extends Thread {

    private Reservation reservation;
    private RoomInventory inventory;

    public BookingProcessor(Reservation reservation, RoomInventory inventory) {
        this.reservation = reservation;
        this.inventory = inventory;
    }

    public void run() {
        inventory.allocateRoom(reservation.roomType, reservation.guestName);
    }
}

// -------- Main --------
public class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println(" Book My Stay App - Version 11.0");
        System.out.println("=====================================\n");

        RoomInventory inventory = new RoomInventory();

        // Simulate multiple users
        Reservation r1 = new Reservation("Alice", "Single Room");
        Reservation r2 = new Reservation("Bob", "Single Room");

        // Two threads (concurrent requests)
        Thread t1 = new BookingProcessor(r1, inventory);
        Thread t2 = new BookingProcessor(r2, inventory);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (Exception e) {}

        inventory.display();
    }
}