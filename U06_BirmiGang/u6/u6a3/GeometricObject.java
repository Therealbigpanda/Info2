package u6a3;

/**
 * abstract class of geometric objects
 */
public abstract class GeometricObject implements Comparable {

    public abstract int area();

    @Override
    public boolean smallerThan(Comparable rhs) {
            GeometricObject i = (GeometricObject)rhs;
            return area() < i.area();
    }
}
