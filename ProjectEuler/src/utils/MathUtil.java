package utils;

/**
 * Utility class containing helper methods for mathematical problems.
 * 
 * @author Jake Holden - jholden88@live.co.uk
 *
 */
public class MathUtil 
{
	/**
	 * Check if x is a multiple of y.
	 * 
	 * @param x
	 * @param y
	 * @return True is x is a multiple of y.
	 */
	public static boolean isMultiple(int x, int y)
	{
		if (x % y == 0)
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * Check if a number is even.
	 * 
	 * @param n
	 * @return True if the number is even.
	 */
	public static boolean isEven(int n)
	{
		if (n % 2 == 0)
		{
			return true;
		}
		
		return false;
	}
}
