public class Room {

    private int roomNumber;
    private RoomType type;
    private double pricePerNight;
    private int capacity;
    private boolean available;

    public Room(int roomNumber,
                RoomType type,
                double pricePerNight,
                int capacity) {

        this.roomNumber = roomNumber;
        this.type = type;
        this.pricePerNight = pricePerNight;
        this.capacity = capacity;
        this.available = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public RoomType getType() {
        return type;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isAvailable() {
        return available;
    }

    public void book() {
        available = false;
    }

    public void release() {
        available = true;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " (" + type + ")";
    }
    
   
}