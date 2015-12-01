package problems;

import utils.MathUtil;

/**
 * The prime factors of 13195 are 5, 7, 13 and 29. 
 * What is the largest prime factor of the number 600851475143?
 * 
 * @author Jake Holden - jholden88@live.co.uk
 *
 */
public class Problem3 extends Problem 
{
	protected void solve() 
	{
		System.out.println("The largest prime factor of the number 600851475143 is " + MathUtil.findLargestPrimeFactor(600851475143L));
	}
}
