public class AllInclusive extends Reservation {

    private int meals;

    public AllInclusive(Customer customer,
                        Room room,
                        int days,
                        int meals) {

        super(customer, room, days);
        this.meals = meals;
    }

    @Override
    public double calculateCharge() {

        return getDays() *
                (getRoom().getPricePerNight() + meals * 30);
    }

    public int getMeals() {
        return meals;
    }
}