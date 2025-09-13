// Assignment1.java
public class M3Assignment1 {
    public static void main(String[] args) {
        Circle c1 = new Circle(5);
        Circle c2 = new Circle(7);
        Circle c3 = new Circle(5);

        System.out.println("Circle 1 radius: " + c1.getRadius());
        System.out.println("Circle 2 radius: " + c2.getRadius());
        System.out.println("Circle 3 radius: " + c3.getRadius());

        // Comparable test
        System.out.println("Compare c1 to c2: " + c1.compareTo(c2));
        System.out.println("Compare c1 to c3: " + c1.compareTo(c3));

        // Equals test
        System.out.println("c1 equals c2? " + c1.equals(c2));
        System.out.println("c1 equals c3? " + c1.equals(c3));
    }
}

// Abstract base class
abstract class GeometricObject {
    private String color = "white";
    private boolean filled;

    public GeometricObject() {
    }

    public GeometricObject(String color, boolean filled) {
        this.color = color;
        this.filled = filled;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public abstract double getArea();
    public abstract double getPerimeter();
}

// Circle class that extends GeometricObject and implements Comparable
class Circle extends GeometricObject implements Comparable<Circle> {
    private double radius;

    public Circle() {
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    public Circle(double radius, String color, boolean filled) {
        super(color, filled);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    // Compare circles by radius
    @Override
    public int compareTo(Circle o) {
        if (this.radius < o.radius) return -1;
        else if (this.radius > o.radius) return 1;
        else return 0;
    }

    // Two circles are equal if radii are the same
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; 
        if (!(obj instanceof Circle)) return false;
        Circle other = (Circle) obj;
        return this.radius == other.radius;
    }
}
