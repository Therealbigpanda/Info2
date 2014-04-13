package u6a3;

public class Rectangle extends GeometricObject {

    private int a;
    private int b;

    public String toString() {
        return String.format("Rectangle(%d,%d)", a, b);
    }

    public int area() {
    }

    public Rectangle(int base, int height) {
        this.a = base;
        this.b = height;
    }
}
