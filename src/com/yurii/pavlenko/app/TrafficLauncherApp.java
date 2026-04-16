package com.yurii.pavlenko.app;

import com.yurii.pavlenko.traffic.Vehicle;
import com.yurii.pavlenko.traffic.types.Car;

/**
 * Entry point for testing the traffic simulator model.
 */
public class TrafficLauncherApp {

    public static void main(String[] args) {
        // Polymorphism in action:
        // Base type reference = Subclass object
        Vehicle car = new Car();

        // Calling the method defined in the abstract base
        car.move();
    }
}