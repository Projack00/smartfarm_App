package com.example.kuhas.smartfarm_04.models;

public class Load_Data_ {
     String mode;
//    public int hummidMax, hummidMin, temMax, temMin;

    public Load_Data_(String mode) {
        this.mode = mode;
    }

    public Load_Data_() {
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
