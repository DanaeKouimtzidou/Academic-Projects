public class Reservation {

    private Customer customer;
    private Room room;
    private int days;

    public Reservation(Customer customer,
                       Room room,
                       int days) {

        this.customer = customer;
        this.room = room;
        this.days = days;
    }

    public double calculateCharge() {
        return room.getPricePerNight() * days;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Room getRoom() {
        return room;
    }

    public int getDays() {
        return days;
    }

    @Override
    public String toString() {
        return customer.getFullName() +
                " - Room " +
                room.getRoomNumber();
    }
}