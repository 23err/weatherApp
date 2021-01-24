package ru.geekbrains;

import android.graphics.drawable.Drawable;

public class DayTemp {
    private int temperature;
    private Drawable pic;
    private String day;

    public DayTemp() {
    }

    public DayTemp(String day, int temperature, Drawable pic) {
        this.day = day;
        this.temperature = temperature;
        this.pic = pic;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public Drawable getPic() {
        return pic;
    }

    public void setPic(Drawable pic) {
        this.pic = pic;
    }
}
