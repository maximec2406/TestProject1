package ru.abr.dit;

import org.springframework.stereotype.Component;

@Component
public class ClassicalMusic implements Music {

    public void doMyInit() {
        System.out.println("Doing my initialization");
    }

    public void doMyDestroy() {
        System.out.println("Doing my destruction");
    }

    @Override
    public String getSong() {
        return "Какая-то классическая песня";
    }
}
