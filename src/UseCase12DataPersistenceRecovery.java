import java.io.*;
import java.util.*;

/**
 * ============================================================
 * MAIN CLASS - UseCase12DataPersistenceRecovery
 * ============================================================
 *
 * Demonstrates saving and restoring system state using serialization.
 *
 * @version 12.0
 */

// -------- Serializable Inventory --------
class RoomInventory implements Serializable {
    private static final long serialVersionUID = 1L;

    Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    public void display() {
        System.out.println("Inventory: " + inventory);
    }
}

// -------- Serializable Reservation --------
class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;

    String id;
    String guestName;
    String roomType;

    public Reservation(String id, String guestName, String roomType) {
        this.id = id;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public void display() {
        System.out.println(id + " | " + guestName + " | " + roomType);
    }
}

// -------- Persistence Service --------
class PersistenceService {

    private static final String FILE_NAME = "hotel_data.ser";

    // Save state
    public void save(RoomInventory inventory, List<Reservation> bookings) {
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            out.writeObject(inventory);
            out.writeObject(bookings);

            System.out.println("Data saved successfully!");

        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Load state
    public Object[] load() {
        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            RoomInventory inventory = (RoomInventory) in.readObject();
            List<Reservation> bookings = (List<Reservation>) in.readObject();

            System.out.println("Data loaded successfully!");
            return new Object[]{inventory, bookings};

        } catch (Exception e) {
            System.out.println("No previous data found. Starting fresh...");
            return null;
        }
    }
}

// -------- Main --------
public class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println(" Book My Stay App - Version 12.0");
        System.out.println("=====================================\n");

        PersistenceService service = new PersistenceService();

        RoomInventory inventory;
        List<Reservation> bookings;

        // Load existing data
        Object[] data = service.load();

        if (data != null) {
            inventory = (RoomInventory) data[0];
            bookings = (List<Reservation>) data[1];
        } else {
            inventory = new RoomInventory();
            bookings = new ArrayList<>();
        }

        // Simulate new booking
        Reservation r = new Reservation("R101", "Alice", "Single Room");
        bookings.add(r);

        System.out.println("\nCurrent Bookings:");
        for (Reservation res : bookings) {
            res.display();
        }

        inventory.display();

        // Save state before exit
        service.save(inventory, bookings);
    }
}