package u8a4;

public class RucksackFactory {

    public static IRucksack create() {
        return new BacktrackingRucksack();
    }
}
