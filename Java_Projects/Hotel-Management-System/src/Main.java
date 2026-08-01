import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        Hotel hotel = new Hotel(
                1,
                "Bellevue",
                "Athens",
                5
        );

        // Single Rooms
        hotel.addRoom(new Room(101, RoomType.SINGLE, 80, 1));
        hotel.addRoom(new Room(102, RoomType.SINGLE, 80, 1));
        hotel.addRoom(new Room(103, RoomType.SINGLE, 80, 1));
        hotel.addRoom(new Room(104, RoomType.SINGLE, 80, 1));
        hotel.addRoom(new Room(105, RoomType.SINGLE, 80, 1));

        // Double Rooms
        hotel.addRoom(new Room(201, RoomType.DOUBLE, 120, 2));
        hotel.addRoom(new Room(202, RoomType.DOUBLE, 120, 2));
        hotel.addRoom(new Room(203, RoomType.DOUBLE, 120, 2));
        hotel.addRoom(new Room(204, RoomType.DOUBLE, 120, 2));
        hotel.addRoom(new Room(205, RoomType.DOUBLE, 120, 2));

        // Suites
        hotel.addRoom(new Room(301, RoomType.SUITE, 220, 4));
        hotel.addRoom(new Room(302, RoomType.SUITE, 220, 4));
        hotel.addRoom(new Room(303, RoomType.SUITE, 220, 4));

        SwingUtilities.invokeLater(() -> {
            new InputFrame(hotel);
        });

    }

}