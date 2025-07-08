package domain;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Booking {
    private String bookingId;
    private Room room;
    private Guest guest;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public Booking(Room room, Guest guest, LocalDate checkInDate, LocalDate checkOutDate) {
        if (room == null || guest == null || checkInDate == null || checkOutDate == null) {
            throw new IllegalArgumentException("Everything has to be non-null");
        }

        if (!checkOutDate.isAfter(checkInDate)) {
            throw new IllegalArgumentException("Check-out date must be after check-in date");
        }

        this.bookingId = UUID.randomUUID().toString();
        this.room = room;
        this.guest = guest;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public String getBookingId() {
        return bookingId;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int calculateTotalCost() {
        long nights = java.time.temporal.ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        return (int) nights * room.getRate();
    }

    @Override
    public String toString() {
        return String.format(
                "Booking{ID='%s', Guest='%s %s', RoomID='%s', CheckIn=%s, CheckOut=%s, TotalCost=$%d}",
                bookingId,
                guest.getFirstName(), guest.getLastName(),
                room.getRoomId(),
                checkInDate,
                checkOutDate,
                calculateTotalCost()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking)) return false;
        Booking booking = (Booking) o;
        return Objects.equals(bookingId, booking.bookingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId);
    }
}
