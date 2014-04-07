package u5a3;

import list.List;

/**
 * Contains basic tools to sort lists with linked list elements from list.List.
 *
 * @author Andrea-Pacal Willi
 */
public class SortedLists {
    /**
     * Inserts a value into a sorted list so that the resulting list is still sorted.
     * The sort order is ascending.
     * 
     * @param list a sorted list. 
     * After the operation the state of this list is undefined.
     * Don't use it any more. Use the returned list instead.
     * @param value the value which is inserted into the list
     * @return
     */
    public static List insertSorted(List list, int value) {
        if (list == null) return new List(value, null);
        if (list.value > value) return new List(value, list);

        list.next = insertSorted(list.next, value);
        return list;
    }

    /**
     * Sorts a list in ascending order.
     * 
     * @param list the list which is sorted.
     * After the operation the state of this list is undefined.
     * Don't use it any more. Use the returned list instead.
     * @return the sorted variant of the given list
     */
    public static List sort(List list) {
        if (list == null) return null;
        list.next = sort(list.next);
        return insertSorted(list.next, list.value);
    }
}
