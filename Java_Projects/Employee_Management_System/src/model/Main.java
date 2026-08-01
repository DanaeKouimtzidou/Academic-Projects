package model;

import ui.ConsoleMenu;
import ui.LoginMenu;

public class Main {

    public static void main(String[] args) {

        LoginMenu loginMenu = new LoginMenu();

        if (loginMenu.login()) {

            ConsoleMenu consoleMenu = new ConsoleMenu();
            consoleMenu.start();

        }

    }

}