### Imagine you're creating a basic model for a traffic simulator. All vehicles can move, but a car does so in a special way.

#### - To start, create an abstract class called Vehicle with an abstract void move() method, which will represent the general concept of movement.

#### - Then, implement one type of vehicle—the Car class. It should inherit Vehicle and implement the move() method so that when called, the phrase "Car is moving" appears on the screen.

#### - To verify that your model works as intended, in the main method, create an instance of the Car class and call its move() method. You should see "Car is moving" on the screen.

```java
public class TrafficLauncherApp {
    public static void main(String[] args) {
        // Polymorphism: a variable of type Vehicle refers to a Car object
        Vehicle car = new Car();
        
        // Call the overridden move() method on the car
        car.move(); // Expected output: "The car is moving"
    }
}
```
