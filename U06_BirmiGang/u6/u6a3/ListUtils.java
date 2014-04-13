package u6a3;

/**
 * An implementation of {@link IListUtils} to manage a {@link GenericList}.
 *
 * @author Andrea-Pacal Willi
 */
public class ListUtils implements IListUtils {

    @Override
    public String toString(GenericList list) {
        if (list == null) return "null";

        StringBuilder buf = new StringBuilder();
        buf.append(list.value).append(", ").append(toString(list.next));
        return buf.toString();
    }

    @Override
    public GenericList add(GenericList list, Object value) {
        return new GenericList(value, list);
    }

    @Override
    public int size(GenericList list) {
        if (list == null) return 0;
        return size(list.next) + 1;
    }

    @Override
    public GenericList sort(GenericList list) {
        if (list == null) return null;
        list.next = sort(list.next);
        return insertSorted(list.next, (Comparable)list.value);
    }

    private GenericList insertSorted(GenericList list, Comparable value) {
        if (list == null || value.smallerThan((Comparable)list.value)) return new GenericList(value, list);
        list.next = insertSorted(list.next, value);
        return list;
    } 
}