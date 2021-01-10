package ru.geekbrains;

public class DataContainer {
    private static DataContainer instance;

    private String temperature;

    private DataContainer(){};

    public static DataContainer getInstance(){
        if (instance == null) instance = new DataContainer();
        return instance;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
