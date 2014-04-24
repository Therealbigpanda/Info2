package u8a1;

import java.util.ArrayList;

public class BinarySearch<Key extends Comparable<Key>, Value> implements
        IBinarySearch<Key, Value>, IMeasure {

    private int factor = 2;
    int numberofCalls;

    @Override
    public Value find(ArrayList<Unit<Key, Value>> haystack, Key needle) {
        if (needle == null || haystack.isEmpty()) {
            return null;
        }
        ++numberofCalls;

        int splitPos = haystack.size() / factor;
        Key splitKey = haystack.get(splitPos).key;
        int comparisonResult = needle.compareTo(splitKey);

        if (comparisonResult == 0) {
            return haystack.get(splitPos).value;
        }
        if (haystack.size() <= 1) {
            return null;
        }
        if (comparisonResult > 0) {
            ArrayList<Unit<Key, Value>> newHaystack = new ArrayList(haystack.subList(splitPos + 1, haystack.size()));
            return find(newHaystack, needle);
        }
        if (comparisonResult < 0) {
            ArrayList<Unit<Key, Value>> newHaystack = new ArrayList(haystack.subList(0, splitPos));
            return find(newHaystack, needle);
        }
        return null;
    }

    @Override
    public void setFactor(int factor) {
        this.factor = factor;
    }

    @Override
    public int getNumberofCalls() {
        return numberofCalls;
    }
}
