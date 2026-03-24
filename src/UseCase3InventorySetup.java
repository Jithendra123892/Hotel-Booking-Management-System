import java.util.HashMap;
import java.util.Map;
class RoomInventory {

    private HashMap<String, Integer> inventory;
    public RoomInventory() {
        inventory = new HashMap<>();

        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }


    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
    public void updateAvailability(String roomType, int count) {
        inventory.put(roomType, count);
    }
    public void displayInventory() {
        System.out.println("---- Room Inventory ----");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
public class UseCase3InventorySetup {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        inventory.displayInventory();
        System.out.println("\nUpdating Single Room availability...\n");
        inventory.updateAvailability("Single Room", 4);
        inventory.displayInventory();
        System.out.println("\nAvailable Double Rooms: " +
                inventory.getAvailability("Double Room"));

        System.out.println("\nApplication executed successfully!");
    }
}