package ru.vsu.UI.impl;

import ru.vsu.UI.EntityCollectionUI;
import ru.vsu.di.annotation.Component;

import java.util.Collection;
import java.util.Scanner;

@Component
public class ConsoleGetEntityCollectionUI implements EntityCollectionUI {

    private final Scanner scanner;

    public ConsoleGetEntityCollectionUI() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public <T> void showEntityCollection(Collection<T> entities) {
        entities.forEach(System.out::println);
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
