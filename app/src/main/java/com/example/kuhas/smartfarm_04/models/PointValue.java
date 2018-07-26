package com.example.kuhas.smartfarm_04.models;

class PointValue {
    public int humidity, temperature;
    public String time;

    public PointValue() {
    }

    public PointValue(int humidity, int temperature, String time) {
        this.humidity = humidity;
        this.temperature = temperature;
        this.time = time;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
