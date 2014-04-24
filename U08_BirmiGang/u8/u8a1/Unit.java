package u8a1;

/**
 * A key-value pair
 *
 * @param <Key> the type of the key. Must be {@link Comparable}
 * @param <Value> the type of the value
 */
public class Unit<Key extends Comparable<Key>, Value> {

    public Key key;
    public Value value;

    public Unit(Key key, Value value) {
        this.key = key;
        this.value = value;
    }
}