package u6a3;

public class Rectangle extends GeometricObject {

    private final int a;
    private final int b;

    @Override
    public String toString() {
        return String.format("Rectangle(%d,%d)", a, b);
    }

    @Override
    public int area() {
        return a * b;
    }

    public Rectangle(int base, int height) {
        this.a = base;
        this.b = height;
    }
}
