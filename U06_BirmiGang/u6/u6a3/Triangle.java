package u6a3;

public class Triangle extends GeometricObject {

    private final int base;
    private final int height;

    @Override
    public String toString() {
        return String.format("Triangle(%d,%d)", base, height);
    }

    @Override
    public int area() {
        return base * height / 2;
    }

    public Triangle(int base, int height) {
        this.base = base;
        this.height = height;
    }
}
