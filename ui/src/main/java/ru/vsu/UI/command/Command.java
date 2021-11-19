package ru.vsu.UI.command;

@FunctionalInterface
public interface Command<T, R> {

    R apply(T t);
}
