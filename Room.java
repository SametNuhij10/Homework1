package domain;

import java.util.Objects;
import java.util.UUID;

public class Room {
    private String roomId;
    private String type;
    private int rate;
    private boolean isAvailable;

    public Room() {

    }

    public Room(String type, int rate) {
        this.roomId = UUID.randomUUID().toString();
        this.type = type;
        this.rate = rate;
        this.isAvailable = true;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int Rate) {
        this.rate = Rate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    @Override
    public String toString() {
        return String.format("Room{ID='%s', Type='%s', Rate=$%d, Available=%s}",
                roomId, type, rate, isAvailable);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Room room = (Room) obj;
        return Objects.equals(roomId, room.roomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId);
    }
}
