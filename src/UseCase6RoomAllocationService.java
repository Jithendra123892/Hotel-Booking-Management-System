import java.util.*;

/**
 * Book My Stay App
 * Use Case 6: Reservation Confirmation & Room Allocation
 *
 * Demonstrates allocation using Queue, HashMap, and Set
 * to prevent double booking and maintain inventory consistency.
 *
 * @author Jithendra
 * @version 6.0
 */
public class UseCase6RoomAllocationService {

    // -------- Reservation --------
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
    }

    // -------- Booking Queue --------
    static class BookingQueue {
        private Queue<Reservation> queue = new LinkedList<>();

        public void addRequest(Reservation r) {
            queue.offer(r);
        }

        public Reservation getNext() {
            return queue.poll(); // FIFO
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    // -------- Inventory --------
    static class RoomInventory {
        private HashMap<String, Integer> inventory = new HashMap<>();

        public RoomInventory() {
            inventory.put("Single Room", 2);
            inventory.put("Double Room", 1);
            inventory.put("Suite Room", 1);
        }

        public int getAvailability(String type) {
            return inventory.getOrDefault(type, 0);
        }

        public void reduceAvailability(String type) {
            inventory.put(type, inventory.get(type) - 1);
        }
    }

    // -------- Allocation Service --------
    static class RoomAllocationService {

        // Map: Room Type → Set of allocated room IDs
        private HashMap<String, Set<String>> allocatedRooms = new HashMap<>();

        // Global set for uniqueness
        private Set<String> allRoomIds = new HashSet<>();

        // Allocate room
        public void allocate(Reservation r, RoomInventory inventory) {

            String type = r.getRoomType();

            // Check availability
            if (inventory.getAvailability(type) <= 0) {
                System.out.println("No rooms available for " + type + " for " + r.getGuestName());
                return;
            }

            // Generate unique room ID
            String roomId;
            do {
                roomId = type.substring(0, 2).toUpperCase() + new Random().nextInt(100);
            } while (allRoomIds.contains(roomId));

            // Store ID
            allRoomIds.add(roomId);

            allocatedRooms.putIfAbsent(type, new HashSet<>());
            allocatedRooms.get(type).add(roomId);

            // Update inventory
            inventory.reduceAvailability(type);

            // Confirm booking
            System.out.println("Booking Confirmed:");
            System.out.println("Guest: " + r.getGuestName());
            System.out.println("Room Type: " + type);
            System.out.println("Room ID: " + roomId + "\n");
        }
    }

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println(" Book My Stay App - Version 6.0");
        System.out.println("=====================================\n");

        BookingQueue queue = new BookingQueue();

        // Add booking requests
        queue.addRequest(new Reservation("Alice", "Single Room"));
        queue.addRequest(new Reservation("Bob", "Double Room"));
        queue.addRequest(new Reservation("Charlie", "Suite Room"));
        queue.addRequest(new Reservation("David", "Single Room"));

        RoomInventory inventory = new RoomInventory();
        RoomAllocationService service = new RoomAllocationService();

        // Process queue (FIFO)
        while (!queue.isEmpty()) {
            Reservation r = queue.getNext();
            service.allocate(r, inventory);
        }

        System.out.println("All bookings processed.");
    }
}
