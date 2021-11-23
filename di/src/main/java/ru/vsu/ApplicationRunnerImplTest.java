package ru.vsu;

import ru.vsu.di.ApplicationRunner;
import ru.vsu.di.annotation.Component;

@Component
public class ApplicationRunnerImplTest implements ApplicationRunner {
    @Override
    public void start() {
        System.out.println("Started!");
    }
}
