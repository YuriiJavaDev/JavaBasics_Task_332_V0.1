package com.yurii.pavlenko.traffic.types;

import com.yurii.pavlenko.traffic.Vehicle;

/**
 * Concrete implementation of a car.
 */
public class Car extends Vehicle {

    @Override
    public void move() {
        System.out.println("Car is moving");
    }
}