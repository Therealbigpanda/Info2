package u8a1;

public class BinarySearchFactory {
	public static IBinarySearch<Integer, String> create()
	{
		return new BinarySearch<Integer, String>();
	}
}
