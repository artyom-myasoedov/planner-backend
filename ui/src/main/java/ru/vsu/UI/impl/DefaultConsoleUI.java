package ru.vsu.UI.impl;

import ru.vsu.UI.UI;
import ru.vsu.di.annotation.Component;

import java.util.Scanner;

@Component
public class DefaultConsoleUI implements UI {

    private final Scanner scanner;

    public DefaultConsoleUI() {
        scanner = new Scanner(System.in);
    }

    @Override
    public String getInput() {
        return scanner.nextLine();
    }

    @Override
    public void showException(Throwable e) {

        System.out.println("----- Exception! -----\n"
                + e.getMessage()
                + "\n----------------------\n");
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

}
