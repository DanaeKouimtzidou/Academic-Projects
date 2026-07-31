import javax.swing.*;
import java.awt.*;

public class InputFrame extends JFrame {

    private Hotel hotel;
    private static int customerIdCounter = 1;

    // Hotel Info
    private JTextArea hotelInfoArea;

    // Customer
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;

    // Reservation
    private JComboBox<RoomType> roomTypeCombo;
    private JTextField daysField;
    private JTextField mealsField;

    // Revenue
    private JTextField revenueField;

    // Buttons
    private JButton reserveButton;
    private JButton revenueButton;

    public InputFrame(Hotel hotel) {

        this.hotel = hotel;

        setTitle("Hotel Reservation System");
        setSize(850, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout(10,10));

        //-----------------------------
        // HOTEL INFO PANEL
        //-----------------------------

        hotelInfoArea = new JTextArea();
        hotelInfoArea.setEditable(false);
        hotelInfoArea.setFont(new Font("Monospaced", Font.BOLD, 14));

        JScrollPane infoScroll = new JScrollPane(hotelInfoArea);

        add(infoScroll, BorderLayout.NORTH);

        //-----------------------------
        // FORM PANEL
        //-----------------------------

        JPanel formPanel = new JPanel(new GridLayout(6,2,15,15));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        formPanel.add(new JLabel("First Name"));
        firstNameField = new JTextField();
        formPanel.add(firstNameField);

        formPanel.add(new JLabel("Last Name"));
        lastNameField = new JTextField();
        formPanel.add(lastNameField);

        formPanel.add(new JLabel("Email"));
        emailField = new JTextField();
        formPanel.add(emailField);

        formPanel.add(new JLabel("Room Type"));
        roomTypeCombo = new JComboBox<>(RoomType.values());
        formPanel.add(roomTypeCombo);

        formPanel.add(new JLabel("Days"));
        daysField = new JTextField();
        formPanel.add(daysField);

        formPanel.add(new JLabel("Meals (0-3)"));
        mealsField = new JTextField("0");
        formPanel.add(mealsField);

        add(formPanel, BorderLayout.CENTER);

        //-----------------------------
        // BOTTOM PANEL
        //-----------------------------

        JPanel bottomPanel = new JPanel(new GridLayout(2,2,10,10));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        reserveButton = new JButton("Create Reservation");
        revenueButton = new JButton("Calculate Revenue");

        revenueField = new JTextField();
        revenueField.setEditable(false);

        bottomPanel.add(reserveButton);
        bottomPanel.add(revenueButton);
        bottomPanel.add(new JLabel("Hotel Revenue"));
        bottomPanel.add(revenueField);

        add(bottomPanel, BorderLayout.SOUTH);

        //-----------------------------

        updateHotelInfo();

        reserveButton.addActionListener(e -> createReservation());

        revenueButton.addActionListener(e ->
                revenueField.setText(
                        String.format("%.2f €", hotel.calculateRevenue())
                )
        );

        setVisible(true);
    }
    
    private void createReservation() {

        try {

            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String email = emailField.getText().trim();

            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill all customer information."
                );

                return;
            }

            int days = Integer.parseInt(daysField.getText());
            int meals = Integer.parseInt(mealsField.getText());

            if (days <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Days must be greater than 0."
                );

                return;
            }

            if (meals < 0 || meals > 3) {

                JOptionPane.showMessageDialog(
                        this,
                        "Meals must be between 0 and 3."
                );

                return;
            }

            RoomType roomType =
                    (RoomType) roomTypeCombo.getSelectedItem();

            Room room = hotel.findAvailableRoom(roomType);

            if (room == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "No available " + roomType + " rooms."
                );

                return;
            }

            Customer customer = new Customer(
                    customerIdCounter++,
                    firstName,
                    lastName,
                    email,
                    "-"
            );

            Reservation reservation;

            if (meals == 0) {

                reservation = new Reservation(
                        customer,
                        room,
                        days
                );

            } else {

                reservation = new AllInclusive(
                        customer,
                        room,
                        days,
                        meals
                );

            }

            hotel.addReservation(reservation);

            updateHotelInfo();

            JOptionPane.showMessageDialog(
                    this,
                    "Reservation Created!\n\n"
                            + "Customer : "
                            + customer.getFullName()
                            + "\nRoom : "
                            + room.getRoomNumber()
                            + "\nType : "
                            + room.getType()
                            + "\nCost : "
                            + reservation.calculateCharge()
                            + " €"
            );

            clearFields();

        }
        catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Days and Meals must be numbers."
            );

        }

    }
    
    private void clearFields() {

        firstNameField.setText("");
        lastNameField.setText("");
        emailField.setText("");

        daysField.setText("");

        mealsField.setText("0");

        roomTypeCombo.setSelectedIndex(0);

    }
    
    private void updateHotelInfo() {

        StringBuilder sb = new StringBuilder();

        sb.append("========================================\n");
        sb.append("          ")
          .append(hotel.getName())
          .append(" HOTEL\n");
        sb.append("========================================\n\n");

        sb.append("Single Rooms : ")
          .append(hotel.getRoomsCount(RoomType.SINGLE))
          .append(" (Available: ")
          .append(hotel.getAvailableRoomsCount(RoomType.SINGLE))
          .append(")\n\n");

        sb.append("Double Rooms : ")
          .append(hotel.getRoomsCount(RoomType.DOUBLE))
          .append(" (Available: ")
          .append(hotel.getAvailableRoomsCount(RoomType.DOUBLE))
          .append(")\n\n");

        sb.append("Suite Rooms  : ")
          .append(hotel.getRoomsCount(RoomType.SUITE))
          .append(" (Available: ")
          .append(hotel.getAvailableRoomsCount(RoomType.SUITE))
          .append(")");

        hotelInfoArea.setText(sb.toString());

    }
}