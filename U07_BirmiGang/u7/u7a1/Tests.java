package u7a1;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import java.util.ArrayList;
import java.util.HashSet;

public class Tests {
	private ArrayList<ArrayList<Student>> groups;
	private IFilter filter;
	private HashSet<Integer> legis;
	
	private void add(ArrayList<Student> group, String name, int legi, int points, boolean passed)
	{
		Student s = new Student(name, legi);
		s.setPoints(points);
		if (passed) {
			legis.add(legi);
		}
		group.add(s);
	}
	
	@Before public void setup()
	{
		groups = new ArrayList<ArrayList<Student>>();
		legis = new HashSet<Integer>();
		filter = FilterFactory.create();
		
		ArrayList<Student> group1 = new ArrayList<Student>();
		add(group1, "Maria Bernasconi", 9, 30, false);
		add(group1, "Max Mustermann", 2, 20, false);
		add(group1, "John Doe", 13, 0, false);
		groups.add(group1);
		
		ArrayList<Student> group2 = new ArrayList<Student>();
		add(group2, "Mr. Smith", 4, 40, true);
		add(group2, "A.N. Other", 19, 42, true);
		add(group2, "Joe Bloggs", 17, 39, false);
		add(group2, "Pierre Dupont", 3, 23, false);
		groups.add(group2);
		
		ArrayList<Student> group3 = new ArrayList<Student>();
		add(group3, "Iwan Iwanowitsch Iwanow", 12, 45, true);
		add(group3, "Jan Kowalskí", 53, 80, true);
		add(group3, "Jakob Maria Mierscheid", 79, 70, true);
		groups.add(group3);
	}
	
	private void check(ArrayList<Student> students)
	{
		for(Student student: students) {
			Assert.assertTrue("Student " + student + " should not have passed the filter", legis.contains(student.getLegi()));
			legis.remove(student.getLegi());
		}
		Assert.assertTrue("The following students should have passed the filter: " + legis, legis.isEmpty());
	}
	
	@Test public void filterRaw()
	{
		check(filter.filterRaw(groups));
	}
	
	@Test public void filterGeneric()
	{
		check(filter.filterGeneric(groups));
	}
}
