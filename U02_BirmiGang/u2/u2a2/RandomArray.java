package u2a2;

import java.util.Random;

/**
 * An array with random values.
 */
public class RandomArray {
	/**
	 * internal storage of the numbers
	 */
    private  int numbers[];

    /**
     * Create a new array with random values between 0 (inclusive) and 1000 (exclusive).
     * 
     * @param length the length of the new array.
     */
    public RandomArray(int length)
    {
        numbers = new int[length];
        for (int i = 0; i<length; i++){
        Random rnd = new Random();            
            numbers[i]= rnd.nextInt(101);
        }
    }

    /**
     * Return string-representation of array.
     * 
     * Example: the string-representation of int array[] = {1,2,3} is '[1, 2, 3]'
     * 
     * @return a list of the values, separated by a comma and a space, and enclosed in squared brackets.
     */
    @Override
    public String toString()
    {
        String returnString = "[";
        for (int i=0; i<numbers.length-1; i++){
            returnString = returnString + numbers[i] + ", ";
        }
        if(numbers.length==0){
            returnString = returnString +"]";
        }else{
            returnString = returnString + numbers[numbers.length-1]+"]";
        }
        return returnString;
    }

    /**
     * recursive sort of the array in descending order in place.
     * 
     * @param until value between 0 and length (inclusive) of {@link RandomArray#numbers}. <br>
     * When the function returns the <em>until</em> largest values of {@link RandomArray#numbers} 
     * are placed in the first <em>until</em> positions of the array and sorted in descending order.
     */
    private void recursiveSort(int until)
    {
        if (until != 0) {

            recursiveSort(until-1);
            int highestUnsortedValueIndex = until-1;
            for(int i = until-1; i<=numbers.length-1;i++){
                if (numbers[i]>numbers[highestUnsortedValueIndex]) highestUnsortedValueIndex = i;
            }

            if(until-1 != highestUnsortedValueIndex){
                
                numbers[until-1] = numbers[until-1] + numbers[highestUnsortedValueIndex];
                numbers[highestUnsortedValueIndex] = numbers[until-1]-numbers[highestUnsortedValueIndex];
                numbers[until-1] = numbers[until-1]-numbers[highestUnsortedValueIndex];
            }
        }
    }

    /**
     * sort the array in descending order in place.
     */
    public void sort()
    {
                  System.out.println("1) "+this.toString());
    	recursiveSort(numbers.length);
                  System.out.println("2) "+this.toString());
    }
}
