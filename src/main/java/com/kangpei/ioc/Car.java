package com.kangpei.ioc;

public class Car {

    private String brand;

    private String cost;

    private Wheel wheel;


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public Wheel getWheel() {
        return wheel;
    }

    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }


    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", cost='" + cost + '\'' +
                ", wheel=" + wheel +
                '}';
    }
}
