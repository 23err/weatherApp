package ru.geekbrains;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Publisher implements Serializable {
    private List<Observer> observers;

    public Publisher() {
        observers = new ArrayList<>();
    }

    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    public void notify(String text) {
        for (Observer observer : observers) {
            observer.updateCity(text);
        }
    }


}
