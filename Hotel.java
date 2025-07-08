package domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private String name;
    private List<Room> rooms;
    private List<Booking> bookings;

    public Hotel(String name) {
        this.name = name;
        this.rooms = new ArrayList<>();
        this.bookings = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addRoom(Room room) {
        if (room != null && !rooms.contains(room)) {
            rooms.add(room);
        }
    }

    public List<Room> getRooms() {
        return List.copyOf(rooms);
    }

    public List<Booking> getBookings() {
        return List.copyOf(bookings);
    }

    private boolean isRoomAvailable(Room room, LocalDate checkIn, LocalDate checkOut) {
        for (Booking booking : bookings) {
            if (booking.getRoom().equals(room)) {
                if (!(checkOut.isBefore(booking.getCheckInDate()) || checkIn.isAfter(booking.getCheckOutDate()))) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean makeBooking(Room room, Guest guest, LocalDate checkIn, LocalDate checkOut) {
        if (room == null || guest == null || checkIn == null || checkOut == null) {
            return false;
        }
        if (!rooms.contains(room)) {
            return false;
        }
        if (checkOut.isBefore(checkIn) || checkOut.isEqual(checkIn)) {
            return false;
        }
        if (!isRoomAvailable(room, checkIn, checkOut)) {
            return false;
        }

        Booking booking = new Booking(room, guest, checkIn, checkOut);
        bookings.add(booking);
        room.setAvailable(false);
        return true;
    }

    public boolean cancelBooking(String bookingId) {
        Booking toRemove = null;
        for (Booking booking : bookings) {
            if (booking.getBookingId().equals(bookingId)) {
                toRemove = booking;
                break;
            }
        }

        if (toRemove != null) {
            bookings.remove(toRemove);

            Room room = toRemove.getRoom();
            boolean roomStillBooked = false;
            for (Booking booking : bookings) {
                if (booking.getRoom().equals(room)) {
                    roomStillBooked = true;
                    break;
                }
            }

            if (!roomStillBooked) {
                room.setAvailable(true);
            }

            return true;
        }

        return false;
    }

    public void displayRooms() {
        System.out.println("Rooms in hotel '" + name + "':");
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    public void displayBookings() {
        System.out.println("Bookings in hotel '" + name + "':");
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }
}
