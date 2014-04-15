package u7a1;

public class Student {
	private String name;
	private int legi;
	private int points;
	
	public Student(String name, int legi)
	{
		this.name = name;
		this.legi = legi;
		points = 0;
	}
	
	public int getLegi()
	{
		return this.legi;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public Student setPoints(int points)
	{
		this.points = points;
		return this;
	}

	public int getPoints()
	{
		return points;
	}
	
	public String toString()
	{
		return String.format("%s (%d)", name, legi);
	}
}
