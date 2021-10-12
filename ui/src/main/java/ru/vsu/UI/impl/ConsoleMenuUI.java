package ru.vsu.UI.impl;

import ru.vsu.UI.MenuUI;
import ru.vsu.di.annotation.Component;

import java.util.Scanner;

@Component
public class ConsoleMenuUI implements MenuUI {

    private final Scanner scanner;
    private String menuMessage;

    public ConsoleMenuUI() {
        scanner = new Scanner(System.in);
    }


    @Override
    public String getInput() {
        return scanner.nextLine();
    }

    public String getMenuMessage() {
        return menuMessage;
    }

    @Override
    public void setMenuMessage(String menuMessage) {
        this.menuMessage = menuMessage;
    }

    @Override
    public void showMenu() {
        System.out.println(menuMessage);
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

    @Override
    public void showShutDownMessage() {
        showMessage("Завершение работы\n------------------");
    }
}
