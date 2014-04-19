package u8a4;

import org.junit.Test;
import org.junit.Before;
import java.util.ArrayList;

import junit.framework.Assert;

public class Tests {
	private IRucksack rucksack;

	@Before public void setup()
	{
		rucksack = RucksackFactory.create();
	}
	
	private static ArrayList<Integer> createArrayList(int[] values)
	{
		ArrayList<Integer> result = new ArrayList<Integer>(values.length);
		for (int value: values) {
			result.add(value);
		}
		return result;
	}
	
	private void runTest(int[] values, int[] weights, int maxWeight,  boolean[] selection, boolean report)
	{
		assert(values.length == weights.length);
		assert(values.length == selection.length);

		ArrayList<Integer> vvalues = createArrayList(values);
		ArrayList<Integer> vweights = createArrayList(weights);
		
		final long start = System.currentTimeMillis();
		Selection result = rucksack.findBest(vvalues, vweights, maxWeight);
		final long end = System.currentTimeMillis();

		Assert.assertEquals("result vector is too small or too large", selection.length, result.size());
		for (int i=0; i<selection.length; i++) {
			if (selection[i]) {
				Assert.assertTrue("Element " + i + " should have been selected. " + result, result.get(i));
			} else {
				Assert.assertFalse("Element " + i + " should not have been selected. " + result, result.get(i));
			}
		}
		if (report) {
			System.out.println("test took " + (end - start) + " milliseconds");			
		}
	}

	@Test public void empty()
	{
		int[] values = {};
		int[] weights = { };
		int maxWeight = 23;
		boolean[] selection = {};
		runTest(values, weights, maxWeight, selection, false);
	}
	
	@Test public void noWeight()
	{
		int[] values = {3, 9, 7};
		int[] weights = {7, 10, 3};
		int maxWeight = 0;
		boolean[] selection = {false, false, false};
		runTest(values, weights, maxWeight, selection, false);
	}
	
	@Test public void sameWeight()
	{
		int[] values = {1, 2, 3, 4, 5};
		int[] weights = {10, 10, 10, 10, 10};
		int maxWeight = 23;
		boolean[] selection = {false, false, false, true, true};
		runTest(values, weights, maxWeight, selection, false);
	}
	
	@Test public void sameValue()
	{
		int[] values = {10, 10, 10, 10, 10};
		int[] weights = {1, 2, 3, 4, 5};
		int maxWeight = 3;
		boolean[] selection = {true, true, false, false, false};
		runTest(values, weights, maxWeight, selection, false);
	}
	
	@Test public void complex()
	{
		int[] values = {19839, 27392, 9718, 32386, 8611, 6008, 10445, 11465, 20456, 23949, 
				                   23633, 19221, 2294, 31024, 28707, 28685, 3239, 2038, 42, 529};
		int[] weights = {2347, 26305, 18656, 12066, 25924, 27267, 18074, 3601, 5965, 5762,
				                     27550, 29598, 24984, 29845, 27854, 20923, 25762, 31094, 22961, 25805};
		int maxWeight = 100000;
		boolean selection[] = {true, false, false, true, false, false, true, true, true, true, false, 
				                                  false, false, true, false, true, false, false, false, false};

		runTest(values, weights, maxWeight, selection, true);
	}
}
