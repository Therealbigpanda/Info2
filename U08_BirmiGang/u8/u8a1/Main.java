package u8a1;

import java.util.ArrayList;

class Main {

    public static void main(String[] args) {
        int[] testArray = {3, 7, 17, 25, 33, 47, 56, 62, 65, 66, 68, 70, 78, 89, 92};
        ArrayList<Unit<Integer, String>> testArrayList = new ArrayList();
        BinarySearch search = new BinarySearch();
        for (int i : testArray) {
            testArrayList.add(new Unit((Comparable) i, String.valueOf(i)));
        }
        ///B///
        System.out.println("b:\n");
        for (int i : testArray) {
            search.find(testArrayList, i);
        }
        System.out.println("1. " + search.getNumberofCalls());
        search.numberofCalls = 0;

        for (int i = 0; i < 100; ++i) {
            search.find(testArrayList, i);
        }
        System.out.println("2. " + search.getNumberofCalls());
        search.numberofCalls = 0;

        for (int i = 0; i < 9; ++i) {
            search.find(testArrayList, i);
        }
        System.out.println("3. " + search.getNumberofCalls());
        search.numberofCalls = 0;

        ///C///
        search.setFactor(0x3);
        System.out.println("c:\n");
        for (int i : testArray) {
            search.find(testArrayList, i);
        }
        System.out.println("1. " + search.getNumberofCalls());
        search.numberofCalls = 0;

        for (int i = 0; i < 100; ++i) {
            search.find(testArrayList, i);
        }
        System.out.println("2. " + search.getNumberofCalls());
        search.numberofCalls = 0;

        for (int i = 0; i < 10; ++i) {
            search.find(testArrayList, i);
        }
        System.out.println("3. " + search.getNumberofCalls());
        search.numberofCalls = 0;
    }
}
