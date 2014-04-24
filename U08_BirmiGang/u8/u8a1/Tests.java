package u8a1;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;
import java.util.ArrayList;

public class Tests {

    private IBinarySearch<Integer, String> search;

    @Before
    public void setup() {
        search = BinarySearchFactory.create();
    }

    private void runTest(int item, String expected, int... items) {
        ArrayList<Unit<Integer, String>> haystack = new ArrayList<Unit<Integer, String>>(items.length);
        for (int i : items) {
            haystack.add(new Unit<Integer, String>(i, String.valueOf(i)));
        }
        String result = search.find(haystack, item);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void empty() {
        runTest(1, null);
    }

    @Test
    public void notFound() {
        runTest(0, null, 1, 2, 3, 4);
    }

    @Test
    public void single() {
        runTest(1, "1", 1);
    }

    @Test
    public void even() {
        runTest(1, "1", 0, 1, 2, 3);
    }

    @Test
    public void odd() {
        runTest(1, "1", 0, 1, 2, 3, 4);
    }

    @Test
    public void example() {
        runTest(78, "78", 3, 7, 17, 25, 33, 47, 56, 62, 65, 66, 68, 70, 78, 89, 92);
    }

    @Test
    public void generic() {
        ArrayList<Unit<Integer, String>> haystack = new ArrayList<Unit<Integer, String>>();
        int count = 100;
        for (int i = 0; i < count; i++) {
            if (i % 3 == 0) {
                continue;
            }
            haystack.add(new Unit<Integer, String>(i, String.valueOf(i)));
        }

        for (int i = 0; i < count; i++) {
            if (i % 3 == 0) {
                continue;
            }
            String result = search.find(haystack, i);
            Assert.assertEquals(String.valueOf(i), result);
        }

        for (int i = 0; i <= count; i++) {
            if (i % 3 == 0) {
                String result = search.find(haystack, i);
                Assert.assertEquals(null, result);
            }
        }
    }
}
