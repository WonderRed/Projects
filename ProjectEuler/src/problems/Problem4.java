package problems;

import utils.MathUtil;

/**
 * Largest palindrome product.
 * 
 * A palindromic number reads the same both ways. 
 * The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99. 
 * Find the largest palindrome made from the product of two 3-digit numbers.
 * 
 * @author Jake Holden - jholden88@live.co.uk
 *
 */
public class Problem4 extends Problem 
{
	protected void solve() 
	{
		int x = 999;
		long largestPalindrome = 0;
		
		while (x > 0)
		{
			for (long i = 999; i > 0; i--)
			{
				if (MathUtil.isPalindromic(x * i))
				{
					if (x * i > largestPalindrome)
					{
						largestPalindrome = x * i;
					}
				}
			}
			
			x--;
		}
		
		System.out.println("The largest palindrome made from the product of two 3 digit numbers is " + largestPalindrome);
	}
}
