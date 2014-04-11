package u6a3;

/**
 *
 * @author Andrea-Pascal Willi
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
    
    private GenericList insertSorted(GenericList list, Comparable value) {	
        if (list == null || value.smallerThan((Comparable) list.value))	
            return new GenericList(value, list);	
        list.next = insertSorted(list.next, value);	
        return list;	
    }	
	
    @Override	
    public GenericList sort(GenericList list) {	
        if (list == null) return null;	
        return insertSorted(sort(list.next), (Comparable) list.value);	
    } 
}
