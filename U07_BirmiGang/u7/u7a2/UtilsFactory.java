package u7a2;

public class UtilsFactory {
	public static IBinarySearchTreeUtils<String> create()
	{
            return new BinarySearchTreeUtils(0, null);
	}
}
