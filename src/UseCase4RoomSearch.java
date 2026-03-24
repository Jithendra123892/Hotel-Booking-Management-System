import java.util.*;

public class UseCase4RoomSearch {

    // Room classes (needed because other files are removed)
    abstract static class Room {
        protected String type;
        protected int beds;
        protected double price;

        public Room(String type, int beds, double price) {
            this.type = type;
            this.beds = beds;
            this.price = price;
        }

        public String getType() {
            return type;
        }

        public void displayDetails() {
            System.out.println("Room Type: " + type);
            System.out.println("Beds: " + beds);
            System.out.println("Price: \u20b9" + price);
        }
    }

    static class SingleRoom extends Room {
        public SingleRoom() {
            super("Single Room", 1, 1000);
        }
    }

    static class DoubleRoom extends Room {
        public DoubleRoom() {
            super("Double Room", 2, 2000);
        }
    }

    static class SuiteRoom extends Room {
        public SuiteRoom() {
            super("Suite Room", 3, 5000);
        }
    }

    // Inventory
    static class RoomInventory {
        private HashMap<String, Integer> inventory = new HashMap<>();

        public RoomInventory() {
            inventory.put("Single Room", 5);
            inventory.put("Double Room", 0);
            inventory.put("Suite Room", 2);
        }

        public int getAvailability(String type) {
            return inventory.getOrDefault(type, 0);
        }
    }

    // Search
    static class RoomSearchService {
        public void search(RoomInventory inventory, List<Room> rooms) {
            System.out.println("---- Available Rooms ----\n");

            for (Room room : rooms) {
                int available = inventory.getAvailability(room.getType());

                if (available > 0) {
                    room.displayDetails();
                    System.out.println("Available: " + available);
                    System.out.println();
                }
            }
        }
    }

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        List<Room> rooms = new ArrayList<>();
        rooms.add(new SingleRoom());
        rooms.add(new DoubleRoom());
        rooms.add(new SuiteRoom());

        new RoomSearchService().search(inventory, rooms);
    }
}
