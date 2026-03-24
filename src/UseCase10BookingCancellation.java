import java.util.*;

/**
 * ============================================================
 * MAIN CLASS - UseCase10BookingCancellation
 * ============================================================
 *
 * Use Case 10: Booking Cancellation & Inventory Rollback
 *
 * Demonstrates safe cancellation using Stack (LIFO rollback).
 *
 * @version 10.0
 */

// -------- Reservation --------
class Reservation {
    String reservationId;
    String guestName;
    String roomType;
    String roomId;

    public Reservation(String reservationId, String guestName, String roomType, String roomId) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
    }
}

// -------- Inventory --------
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 1);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    public void increase(String type) {
        inventory.put(type, inventory.get(type) + 1);
    }

    public void display() {
        System.out.println("Inventory: " + inventory);
    }
}

// -------- Cancellation Service --------
class CancellationService {

    // Stack for rollback (LIFO)
    private Stack<String> rollbackStack = new Stack<>();

    // Store active reservations
    private Map<String, Reservation> activeBookings = new HashMap<>();

    // Add booking (simulate confirmed booking)
    public void addBooking(Reservation r) {
        activeBookings.put(r.reservationId, r);
    }

    // Cancel booking
    public void cancel(String reservationId, RoomInventory inventory) {

        // Validate existence
        if (!activeBookings.containsKey(reservationId)) {
            System.out.println("Cancellation failed: Reservation not found");
            return;
        }

        Reservation r = activeBookings.get(reservationId);

        // Push to stack (rollback tracking)
        rollbackStack.push(r.roomId);

        // Restore inventory
        inventory.increase(r.roomType);

        // Remove booking
        activeBookings.remove(reservationId);

        System.out.println("Booking cancelled for: " + r.guestName);
        System.out.println("Room released: " + r.roomId + "\n");
    }

    public void showRollbackStack() {
        System.out.println("Rollback Stack (LIFO): " + rollbackStack);
    }
}

// -------- Main --------
public class UseCase10BookingCancellation {

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println(" Book My Stay App - Version 10.0");
        System.out.println("=====================================\n");

        RoomInventory inventory = new RoomInventory();
        CancellationService service = new CancellationService();

        // Simulate confirmed bookings
        service.addBooking(new Reservation("R101", "Alice", "Single Room", "SR1"));
        service.addBooking(new Reservation("R102", "Bob", "Double Room", "DR1"));

        // Cancel booking
        service.cancel("R101", inventory);

        // Try invalid cancellation
        service.cancel("R999", inventory);

        // Show rollback stack
        service.showRollbackStack();

        // Show updated inventory
        inventory.display();
    }
}