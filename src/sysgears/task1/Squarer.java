package sysgears.task1;

public class Squarer {
	
	/**
	 * 
	 * returns square root of int input number (it must be >= 0) rounded down to the closest int number .
	 * 
	 * @param n
	 * @return int value of Square root of 'n'
	 * @throws IllegalArgumentException if n < 0 
	 * 
	 */
	public static int getRoot(int n) {
		
		// long is used to avoid outing the int range
        long r = 0;
        
        if (n < 0) {
        	throw new IllegalArgumentException("Negative values are not permitted in getRoot() method");
        }
        
        while (true) {
            r += 1;
            // as soon as r*r > n we have to break the loop: eventualy, than (r-1) has to be returned
            if (r * r > n) {
                break;
            }
        }
        return (int)(r - 1);
    }
}
