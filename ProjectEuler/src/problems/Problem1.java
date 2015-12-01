package problems;

import utils.MathUtil;

/**
 * Multiples of 3 and 5.
 * 
 * If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. 
 * The sum of these multiples is 23. 
 * 
 * Find the sum of all the multiples of 3 or 5 below 1000.
 * 
 * @author Jake Holden - jholden88@live.co.uk
 *
 */
public class Problem1 extends Problem 
{
	protected void solve() 
	{
		System.out.println("The sum of all multiples of 3 or 5 below 1000 is " + sumOfAllMultiples(999, 3, 5));
	}
	
	private long sumOfAllMultiples(int maximum, int factor1, int factor2)
	{
		long sum = 0;
		
		while (maximum > 0)
		{
			if (MathUtil.isMultiple(maximum, factor1))
			{
				sum += maximum;
			}
			else if (MathUtil.isMultiple(maximum, factor2))
			{
				sum += maximum;
			}
			
			maximum--;
		}
		
		return sum;
	}
}
