import java.util.*;

// -------- Reservation --------
class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void display() {
        System.out.println("ID: " + reservationId +
                " | Guest: " + guestName +
                " | Room: " + roomType);
    }
}

// -------- Booking History --------
class BookingHistory {
    private List<Reservation> history = new ArrayList<>();

    // Add confirmed booking
    public void addBooking(Reservation reservation) {
        history.add(reservation);
    }

    // Get all bookings
    public List<Reservation> getAllBookings() {
        return history;
    }
}

// -------- Report Service --------
class BookingReportService {

    // Display all bookings
    public void showAllBookings(List<Reservation> bookings) {
        System.out.println("\n--- Booking History ---");

        for (Reservation r : bookings) {
            r.display();
        }
    }

    // Generate summary report
    public void generateSummary(List<Reservation> bookings) {
        System.out.println("\n--- Booking Summary Report ---");

        Map<String, Integer> countMap = new HashMap<>();

        for (Reservation r : bookings) {
            String type = r.getRoomType();
            countMap.put(type, countMap.getOrDefault(type, 0) + 1);
        }

        for (String type : countMap.keySet()) {
            System.out.println(type + " bookings: " + countMap.get(type));
        }
    }
}

// -------- Main --------
public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println(" Book My Stay App - Version 8.0");
        System.out.println("=====================================\n");

        BookingHistory history = new BookingHistory();

        // Simulate confirmed bookings
        history.addBooking(new Reservation("R101", "Alice", "Single Room"));
        history.addBooking(new Reservation("R102", "Bob", "Double Room"));
        history.addBooking(new Reservation("R103", "Charlie", "Single Room"));
        history.addBooking(new Reservation("R104", "David", "Suite Room"));

        BookingReportService reportService = new BookingReportService();

        // Show all bookings
        reportService.showAllBookings(history.getAllBookings());

        // Show summary
        reportService.generateSummary(history.getAllBookings());

        System.out.println("\nReport generated successfully!");
    }
}