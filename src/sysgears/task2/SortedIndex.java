package sysgears.task2;

import java.util.Arrays;

public class SortedIndex {
	
	/**
	 * 
	 * returns k-th smallest element of ascending sorted input array
	 * 
	 * @param array
	 * @param k
	 * @return k-th smallest element of ascending sorted input array
	 * 
	 * @throws IllegalArgumentException if k < 1 
	 * 
	 */
	public int getSortedIndex(int[] array, int k){
        if (k < 1) {
        	throw new IllegalArgumentException("Not positive element number is not permitted in getSortedIndex() method");
        }
		Arrays.sort(array);
		
		return array[k - 1];
	}
}
