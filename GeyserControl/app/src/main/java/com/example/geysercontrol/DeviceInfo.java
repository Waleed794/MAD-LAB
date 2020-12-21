package com.example.geysercontrol;

public class DeviceInfo {

    private String motor;
    private String temp;

    public DeviceInfo(){}

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public DeviceInfo(String motor, String temp) {
        this.motor = motor;
        this.temp = temp;
    }
}
