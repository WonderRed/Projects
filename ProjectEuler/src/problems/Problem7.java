package problems;

import utils.MathUtil;

/**
 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
 * What is the 10001st prime number?
 * 
 * @author Jake Holden - jholden88@live.co.uk
 *
 */
public class Problem7 extends Problem 
{
	protected void solve() 
	{
		int n = 1;
		int x = 0;
		
		while (n <= 10001)
		{		
			x++;
			if (MathUtil.isPrime(x))
			{
				System.out.println(x);
				n++;
			}
		}
		
		System.out.println("The 10001st prime number is " + x);
	}
}
