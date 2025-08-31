 /*
 * Kameron Cole
 * SDEV 200
 * Module 1 Programming Assignment (4)
 */

public class Assignment4 {
    public static void main(String[] args) {
        // Create three RegularPolygon objects
        RegularPolygon polygon1 = new RegularPolygon();                  // no-arg constructor
        RegularPolygon polygon2 = new RegularPolygon(6, 4);              // 6 sides, side length 4
        RegularPolygon polygon3 = new RegularPolygon(10, 4, 5.6, 7.8);  // 10 sides, side 4, center at (5.6,7.8)

        // Display perimeter and area for each polygon
        System.out.println("Polygon 1: Perimeter = " + polygon1.getPerimeter() +
                           ", Area = " + polygon1.getArea());
        System.out.println("Polygon 2: Perimeter = " + polygon2.getPerimeter() +
                           ", Area = " + polygon2.getArea());
        System.out.println("Polygon 3: Perimeter = " + polygon3.getPerimeter() +
                           ", Area = " + polygon3.getArea());
    }
}

class RegularPolygon {
    // Private fields
    private int n;
    private double side;
    private double x;
    private double y;

    // No-arg constructor
    public RegularPolygon() {
        this.n = 3;
        this.side = 1;
        this.x = 0;
        this.y = 0;
    }

    // Constructor with n and side
    public RegularPolygon(int n, double side) {
        this.n = n;
        this.side = side;
        this.x = 0;
        this.y = 0;
    }

    // Constructor with n, side, x, and y
    public RegularPolygon(int n, double side, double x, double y) {
        this.n = n;
        this.side = side;
        this.x = x;
        this.y = y;
    }

    // Accessor and mutator methods
    public int getN() { return n; }
    public void setN(int n) { this.n = n; }

    public double getSide() { return side; }
    public void setSide(double side) { this.side = side; }

    public double getX() { return x; }
    public void setX(double x) { this.x = x; }

    public double getY() { return y; }
    public void setY(double y) { this.y = y; }

    // Calculate perimeter
    public double getPerimeter() {
        return n * side;
    }

    // Calculate area
    public double getArea() {
        return (n * side * side) / (4 * Math.tan(Math.PI / n));
    }
}