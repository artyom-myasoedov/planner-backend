package ru.vsu.UI.impl;

import ru.vsu.UI.UI;
import ru.vsu.di.annotation.Component;

import java.util.Arrays;
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
        StringBuilder str = new StringBuilder("----- Exception! -----\nMessage: "
                + e.getMessage() + "\n StackTrace: ");
        Arrays.stream(e.getStackTrace()).forEach((s) -> {
            str.append("\n");
            str.append(s);
        });
        str.append("\n----------------------\n");
        System.out.println(str);
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

}
