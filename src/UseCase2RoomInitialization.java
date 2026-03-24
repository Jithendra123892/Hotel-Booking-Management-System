abstract class Room {
    protected String type;
    protected int beds;
    protected double price;

    public Room(String type, int beds, double price) {
        this.type = type;
        this.beds = beds;
        this.price = price;
    }

    public void displayDetails() {
        System.out.println("Room Type: " + type);
        System.out.println("Beds: " + beds);
        System.out.println("Price: ₹" + price);
    }
}

// Single Room
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 1000);
    }
}

// Double Room
class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 2000);
    }
}

// Suite Room
class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 5000);
    }
}

// Main Application
public class UseCase2RoomInitialization {

    public static void main(String[] args) {

        // Create room objects (Polymorphism)
        Room single = new SingleRoom();
        Room dbl = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Static availability
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        // Display details
        System.out.println("---- Single Room ----");
        single.displayDetails();
        System.out.println("Available: " + singleAvailable + "\n");

        System.out.println("---- Double Room ----");
        dbl.displayDetails();
        System.out.println("Available: " + doubleAvailable + "\n");

        System.out.println("---- Suite Room ----");
        suite.displayDetails();
        System.out.println("Available: " + suiteAvailable + "\n");

        System.out.println("Application executed successfully!");
    }
}