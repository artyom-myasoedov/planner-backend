package ru.vsu.UI;

public interface UI {

    String getInput();

    void showException(Throwable e);

    void showMessage(String message);
}
