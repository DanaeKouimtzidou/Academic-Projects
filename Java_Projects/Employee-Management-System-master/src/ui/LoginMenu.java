package ui;

import java.util.Scanner;

import service.UserService;

public class LoginMenu {

    private Scanner scanner;
    private UserService userService;

    public LoginMenu() {

        scanner = new Scanner(System.in);
        userService = new UserService();

    }

    public boolean login() {

        while (true) {

            System.out.println("========== LOGIN ==========");

            System.out.print("Username: ");
            String username = scanner.nextLine();

            System.out.print("Password: ");
            String password = scanner.nextLine();

            if (userService.login(username, password)) {

                System.out.println("Login successful.");

                return true;

            }

            System.out.println("Wrong username or password. Try again.\n");

        }

    }

}