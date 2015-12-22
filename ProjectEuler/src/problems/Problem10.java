package problems;

import utils.MathUtil;

/**
 * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17. 
 * 
 * Find the sum of all the primes below two million.
 * 
 * @author Jake Holden - jholden88@live.co.uk
 *
 */
public class Problem10 extends Problem
{
	protected void solve() 
	{
		long sum = 0;
		long x = 1;
		
		while (x < 2000000)
		{
			if (MathUtil.isPrime(x))
			{
				sum += x;
			}
			
			x++;
		}
		
		System.out.println("The sum of all primes below two million is " + sum);
	}	
}
