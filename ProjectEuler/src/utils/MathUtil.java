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
	 * @return True is x is a multiple of y, false if otherwise.
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
	 * @return True if the number is even, false if otherwise.
	 */
	public static boolean isEven(int n)
	{
		if (n % 2 == 0)
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * Find the largest prime factor of n.
	 * 
	 * @param n
	 * @return The largest prime factor of n.
	 */
	public static long findLargestPrimeFactor(long n)
	{
		long largestPrimeFactor = 0;
		
		for (long i = 3; i <= (long) Math.sqrt(n); i += 2)
		{
			if (isPrime(i))
			{
				if (n % i == 0)
				{
					largestPrimeFactor = i;
				}				
			}
		}
		
		return largestPrimeFactor;
	}
	
	/**
	 * Check if a number is prime.
	 * 
	 * @param n
	 * @return True if the n is prime, false if otherwise.
	 */
	public static boolean isPrime(long n )
	{					
		if (n > 3)
		{
			if (n % 2 == 0)
			{
				return false;
			}
			
			for (long i = 3; i < (long) Math.sqrt(n); i += 2)
			{
				if (n % i == 0)
				{
					return false;
				}
			}
		}
		
		return true;
	}
}
