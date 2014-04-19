package u7a1;

import java.util.ArrayList;

/**
 *
 * @author Andrea-Pascal Willi
 */
public class Filter implements IFilter {

    @Override
    public ArrayList filterRaw(ArrayList groups) {
        ArrayList returnList = new ArrayList();

        for (Object i : groups) {
            for (Object j : (ArrayList) i) {
                if (((Student) j).getPoints() >= criteria / 100 * maxNumberofPoints) {
                    returnList.add((Student) j);
                }
            }
        }
        return returnList;
    }

    @Override
    public ArrayList filterGeneric(ArrayList<ArrayList<Student>> groups) {
        ArrayList returnList = new ArrayList();

        for (ArrayList<Student> i : groups) {
            for (Student j : i) {
                if (j.getPoints() >= criteria / 100 * maxNumberofPoints) {
                    returnList.add(j);
                }
            }
        }
        return returnList;
    }
}
