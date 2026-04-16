## Implementation of abstractions and hierarchies.

### 1. Building a Hierarchy: From Abstraction to Detail

Implementing abstractions and hierarchies is a way to structure code from general rules to specific details. First, we describe what all objects should be able to do (abstraction), and then we clarify how each specific class does this.

In programming, as in life, everything starts with questions. For example: "What do a circle and a rectangle have in common?" Answer: they are both shapes. And what do shapes have in common? They usually have an area and can be drawn.

In Java, this is expressed through an abstract class:

```java
public abstract class Shape {
    public abstract double area();
    public abstract void draw();
}
```

**Here we say:**

- Any shape should be able to calculate its area (**area()**).
- Any shape should be able to be drawn (**draw()**).
- How exactly it does this is none of our business (for now).

Now let's create specific shapes:

```java
public class Circle extends Shape {
    private double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
    
    @Override
    public void draw() {
        System.out.println("Draw a circle with radius " + radius);
    }
}

public class Rectangle extends Shape {
    private double width, height;
    
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    
    @Override
    public double area() {
        return width * height;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing a rectangle " + width + "x" + height);
    }
}
```

**What have we done?**

- Moved the general behavior into an abstract class.
- Detailed the behavior in the derived classes.

#### Schematic:

```
    Shape
    /   \
Circle Rectangle
```

#### Table: What is implemented where

| **Class** | **area()** | **draw()** | **Custom fields** |
| --- | --- | --- | --- |
| Shape | `abstract` | `abstract` | - |
| Circle | implemented | implemented | `radius` |
| Rectangle | implemented | implemented | `width, height` |

### 2. Why is this convenient? (and why it works at all)

#### A unified interface for working with different objects

Let's say you have a collection of shapes:

```java
Shape[] shapes = {
    new Circle(5),
    new Rectangle(3, 4),
    new Circle(2.5)
};
```

You can iterate over them uniformly, without worrying about the type:

```java
    for (Shape shape : shapes) {
        shape.draw();
        System.out.println("Area: " + shape.area());
    }
```

Let the JVM figure out what's a circle and what's a rectangle! This is polymorphism (we've already talked about it, and we'll talk about it in more detail in the next section).

#### Ease of extension

Want to add a triangle? Simply write (new type, no change to old code): **Triangle extends Shape**.

```java
public class Triangle extends Shape {
    private double base, height;
    
    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }
    
    @Override
    public double area() {
        return 0.5 * base * height;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing a triangle: base " + base + ", height " + height);
    }
}
```

All other code (for example, iterating over the list of shapes) does not need to be changed.

#### Avoiding Code Duplication

If all shapes share a common property (for example, color), it's convenient to move it into an abstract class:

```java
public abstract class Shape {
    private String color = "black";
    
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    
    public abstract double area();
    public abstract void draw();
}
```

Now any descendant—whether a circle or a triangle—will inherit the color.

### 3. Practice: Developing a Mini Graphics Editor

Let's try putting it all together. Let's imagine you're making a simple graphics editor.

#### Abstract Class Figure

```java
public abstract class Figure {
    private String color = "black";
    
    public String getColor() { return color; } 
    public void setColor(String color) { this.color = color; } 
    
    public abstract void draw(); 
    public abstract void resize(double factor);
}
```

#### Specific figures

```java
public class Line extends Figure { 
    private double length; 
    
    public Line(double length) { 
        this.length = length; 
    } 
    
    @Override 
    public void draw() { 
        System.out.println("Draw a line with length " + length + " color " + getColor()); 
    } 
    
    @Override 
    public void resize(double factor) { 
        length *= factor; 
        System.out.println("New line length: " + length); 
    }
}

public class Ellipse extends Figure { 
    private double a, b; 
    
    public Ellipse(double a, double b) { 
        this.a = a; 
        this.b = b; 
    }
    
    @Override
    public void draw() {
        System.out.println("Draw an ellipse with axes " + a + " and " + b + ", color " + getColor());
    }
    
    @Override
    public void resize(double factor) {
        a *= factor;
        b *= factor;
        System.out.println("New ellipse axes: " + a + ", " + b);
    }
}
```

Want to add a new tool, like **Polygon**? Just create a new class—all editor code works through the abstract **Figure**.

#### Usage in code

```java
Figure[] figures = {
    new Line(10),
    new Ellipse(5, 3)
};

    for (Figure figure : figures) {
        figure.setColor("red");
        figure.draw();
        figure.resize(1.5);
    }
```

**Output:**

```
Draw a line of length 10.0 using the color red
New line length: 15.0
Draw an ellipse with axes 5.0 and 3.0 using the color red
New ellipse axes: 7.5, 4.5
```

#### Visualizing the Hierarchy

```
  Figure
  /    \
Line Ellipse
```

### 4. Avoiding Duplication: Common Fields and Methods

Sometimes all derived classes have not only common methods but also common fields (for example, center coordinates). An abstract class is the perfect place for this:

```java
public abstract class Figure {
    private double x, y; // center coordinates
    
    public Figure(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public void moveTo(double newX, double newY) {
        x = newX;
        y = newY;
        System.out.println("The shape has been moved to the point (" + x + ", " + y + ")");
    }
    
    public abstract void draw();
}
```

Now any **Line** or **Ellipse** can move without reimplementing this method.

### 5. Another example: payment systems

Abstraction isn't just about shapes! Imagine you're writing a payment processing system.

#### Abstract class Payment

```java
public abstract class Payment {
    public abstract void process();
}
```

#### Concrete Implementations

```java
public class CreditCardPayment extends Payment {
    @Override
    public void process() {
        System.out.println("Processing credit card payment");
    }
}

public class PaypalPayment extends Payment {
    @Override
    public void process() {
        System.out.println("Processing payment via PayPal");
    }
}
```

#### Usage

```java
Payment[] payments = {
    new CreditCardPayment(),
    new PaypalPayment()
};

    for (Payment payment : payments) {
        payment.process();
    }
```

**Output:**

```
Processing a credit card payment
Processing a PayPal payment
```

### 6. Advantages of this approach

- **Unified interface**: you can work with different objects in the same way.
- **Extensibility**: adding new types of objects does not require rewriting old code.
- **Minimal duplication**: common parts are extracted into a base abstract class.
- **Flexibility**: you can use collections of abstract types without worrying about the details.

### 7. Real-World Example: Transportation

Abstraction isn't just found in textbooks. For example, if you're designing a transportation management system:

```java
public abstract class Transport {
    public abstract void move();
    public abstract void fuelUp();
}
```

Concrete types of transportation implement the details:

```java
public class Car extends Transport {
    @Override
    public void move() {
        System.out.println("The car is driving along the road");
    }
    
    @Override
    public void fuelUp() {
        System.out.println("Filling up with gas");
    }
}
    
public class Bicycle extends Transport {
    @Override
    public void move() {
        System.out.println("The bicycle is pedaling");
    }
    
    @Override
    public void fuelUp() {
        System.out.println("A bicycle doesn't need fuel, only sandwiches for the cyclist!");
    }
}
```

### 8. A Useful Diagram: How to Build an Abstraction Hierarchy

```
[Abstract Class]
    |
[Concrete Subclass]
    |
[Even More Concrete Subclass] (if needed)
```

- Everything common is at the top!
- Everything unique is at the bottom!

### 9. Common Mistakes in Implementing Abstractions and Hierarchies

**Mistake №1: Duplicating Code in Subclasses.**

If you suddenly notice that you're writing the same fields or methods in each subclass, this is a sign that they should be moved to an abstract class. Don't be afraid to make an abstraction "wider" if it reduces duplication.

**Mistake №2: Violating the "from the general to the specific" principle.**

Sometimes novice programmers start building a hierarchy "from the details" down, forgetting about the general. This results in strange classes like **RedCircleWithShadow** that don't fit well into the overall structure. Always prioritize the abstraction first, then the details.

**Mistake №3: Hierarchies that are too deep.**

If your inheritance chain is longer than 3-4 levels, consider whether it's time to use composition or interfaces instead of inheritance.

**Mistake №4: Forcing implementation of irrelevant methods.**

If an abstract class contains too many abstract methods that are irrelevant to some of its descendants, it might be worth reconsidering the structure. For example, not all vehicles need a fuelUp() method (bicycles don't).

**Mistake №5: Confusion between an abstract class and an interface.**

An abstract class is one that has shared state and/or partial implementation. An interface is one that only "promises" the presence of methods, without storing data or implementing behavior. Don't mix these approaches unnecessarily.
