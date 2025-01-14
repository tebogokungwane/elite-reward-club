package com.erc.controller;

public class Main {

    public static void main(String[] args) {

        Car car1 = new Car("Toyota", 2020);
        Car car2 = new Car(car1);

        System.out.println(car1.model + " " + car1.year);

    }
}

class Car{
    String model;
    int year;

    Car(String model, int year){
        this.model = model;
        this.year = year;
    }

    Car(Car car){
        this.model = car.model;
        this.year = car.year;
    }
}
