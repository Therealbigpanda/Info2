package u7a1;

import java.util.ArrayList;

/**
 * Filters for students who obtain the "Testat".
 * 
 * The requirements for the testat are to have at least {@link IFilter#criteria} percent 
 * of {@link IFilter#maxNumberofPoints} points.
 */
public interface IFilter {
	public final int maxNumberofPoints = 80;
	public final double criteria = 50;
	
	/**
	 * Filter all students that achieved enough points for the "Testat".
	 * 
	 * @param groups a ArrayList of groups, where a group is a ArrayList of students
	 * @return the ArrayList of all students who achieved enough points for the "Testat".
	 */
	public ArrayList filterRaw(ArrayList groups);

	/**
	 * Filter all students that achieved enough points for the "Testat".
	 * 
	 * @param groups a ArrayList of groups, where a group is a ArrayList of students
	 * @return the ArrayList of all students who achieved enough points for the "Testat".
	 */
	public ArrayList<Student> filterGeneric(ArrayList<ArrayList<Student>> groups);
}