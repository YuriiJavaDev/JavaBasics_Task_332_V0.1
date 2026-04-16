# Traffic Simulator: Basic Movement Model (JavaBasics_Task_332_V0.1)

## 📖 Description
In simulation systems, abstraction allows us to manage complex behaviors through simplified interfaces. This project establishes a basic **Traffic Simulator Model**. By defining an abstract `Vehicle` class, we set a universal rule for any transport unit: it must be capable of movement. The `Car` class provides the first concrete realization of this rule. This task demonstrates the core of **Polymorphism**, where a variable of a general base type can successfully trigger specific subclass behavior.

## 📋 Requirements Compliance
- **Abstract Foundation**: Created a `Vehicle` class that cannot be instantiated on its own.
- **Contract Enforcement**: Defined the abstract `move()` method as a mandatory requirement.
- **Subclass Implementation**: Realized the `Car` class with specific movement output.
- **Polymorphic Reference**: Verified that a `Vehicle` type variable can hold and control a `Car` object.

## 🚀 Architectural Stack
- Java 8+ (Abstraction, Inheritance, Polymorphism)

## 🏗️ Implementation Details
- **Vehicle**: The abstract base defining the general movement contract.
- **Car**: A concrete vehicle implementing the driving logic.
- **TrafficLauncherApp**: The application entry point to test the simulator's logic.

## 📋 Expected result
```text
Car is moving
```

## 💻 Code Example

Project Structure:

    JavaBasics_Task_332/
    ├── src/
    │   └── com/yurii/pavlenko/
    │                 ├── app/
    │                 │   └── TrafficLauncherApp.java
    │                 └── traffic/
    │                     ├── types/
    │                     │   └── Car.java
    │                     └── Vehicle.java
    ├── LICENSE
    ├── TASK.md
    ├── THEORY.md
    └── README.md

Code
```java
package com.yurii.pavlenko.app;

import com.yurii.pavlenko.traffic.Vehicle;
import com.yurii.pavlenko.traffic.types.Car;

public class TrafficLauncherApp {

    public static void main(String[] args) {

        Vehicle car = new Car();

        car.move();
    }
}
```
```java
package com.yurii.pavlenko.traffic;

public abstract class Vehicle {
    public abstract void move();
}
```
```java
package com.yurii.pavlenko.traffic.types;

import com.yurii.pavlenko.traffic.Vehicle;

public class Car extends Vehicle {

    @Override
    public void move() {
        System.out.println("Car is moving");
    }
}
```

## ⚖️ License
This project is licensed under the **MIT License**.

Copyright (c) 2026 Yurii Pavlenko

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files...

License: [MIT](LICENSE)
