import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Hotel implements Serializable {

    private int id;
    private String name;
    private String city;
    private int stars;

    private List<Room> rooms;
    private List<Reservation> reservations;

    public Hotel(int id, String name, String city, int stars) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.stars = stars;
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public boolean addReservation(Reservation reservation) {

        Room room = reservation.getRoom();

        if (!room.isAvailable()) {
            return false;
        }

        room.book();
        reservations.add(reservation);

        return true;
    }

    public void cancelReservation(Reservation reservation) {

        reservation.getRoom().release();
        reservations.remove(reservation);

    }

    public double calculateRevenue() {

        double total = 0;

        for (Reservation reservation : reservations) {
            total += reservation.calculateCharge();
        }

        return total;
    }

    public Room findAvailableRoom(RoomType type) {

        for (Room room : rooms) {

            if (room.getType() == type && room.isAvailable()) {
                return room;
            }

        }

        return null;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public int getStars() {
        return stars;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    @Override
    public String toString() {
        return name + " (" + stars + "★)";
    }
    
    public int getRoomsCount(RoomType type) {

        int count = 0;

        for (Room room : rooms) {

            if (room.getType() == type) {
                count++;
            }

        }

        return count;
    }
    
    public int getBookedRoomsCount(RoomType type) {

        int count = 0;

        for (Room room : rooms) {

            if (room.getType() == type && !room.isAvailable()) {
                count++;
            }

        }

        return count;
    }
    
 
    
    public int getTotalRooms() {

        return rooms.size();

    }
    
    public int getAvailableRooms() {

        int count = 0;

        for (Room room : rooms) {

            if (room.isAvailable()) {
                count++;
            }

        }

        return count;
    }
    
    public double getOccupancyRate() {

        if (rooms.isEmpty()) {
            return 0;
        }

        int booked = getTotalRooms() - getAvailableRooms();

        return (booked * 100.0) / getTotalRooms();

    }
    
    
    public int getAvailableRoomsCount(RoomType type) {

        int count = 0;

        for (Room room : rooms) {

            if (room.getType() == type && room.isAvailable()) {
                count++;
            }

        }

        return count;

    }

}