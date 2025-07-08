package domain;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel("Tetove");

        Room room1 = new Room("Standard", 100);
        Room room2 = new Room("Deluxe", 150);
        Room room3 = new Room("Suite", 250);
        hotel.addRoom(room1);
        hotel.addRoom(room2);
        hotel.addRoom(room3);

        Guest guest1 = new Guest("John", "Doe", "john.doe@example.com");
        Guest guest2 = new Guest("Jane", "Smith", "jane.smith@example.com");

        boolean booked1 = hotel.makeBooking(room1, guest1,
                LocalDate.of(2025, 7, 10), LocalDate.of(2025, 7, 15));

        boolean booked2 = hotel.makeBooking(room2, guest2,
                LocalDate.of(2025, 7, 12), LocalDate.of(2025, 7, 14));

        System.out.println("\nBooking 1 successful? " + booked1);
        System.out.println("Booking 2 successful? " + booked2);

        System.out.println("\n--- Current Rooms ---");
        hotel.displayRooms();

        System.out.println("\n--- Current Bookings ---");
        hotel.displayBookings();

        if (!hotel.getBookings().isEmpty()) {
            String bookingIdToCancel = hotel.getBookings().get(0).getBookingId();
            boolean cancelled = hotel.cancelBooking(bookingIdToCancel);
            System.out.println("\nCancellation of booking " + bookingIdToCancel + " successful? " + cancelled);
        }

        System.out.println("\n--- Updated Rooms ---");
        hotel.displayRooms();

        System.out.println("\n--- Updated Bookings ---");
        hotel.displayBookings();
    }
}
