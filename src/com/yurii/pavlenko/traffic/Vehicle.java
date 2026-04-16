package com.yurii.pavlenko.traffic;

/**
 * Abstract blueprint for any vehicle in the simulator.
 */
public abstract class Vehicle {

    /**
     * Abstract method representing movement.
     * Must be implemented by any concrete vehicle type.
     */
    public abstract void move();
}