package problems;

import utils.MathUtil;

/**
 * The sum of the squares of the first ten natural numbers is, 1squared + 2squared... = 385 
 * 
 * The square of the sum of the first ten natural numbers is, (1 + 2 + ... + 10)squared = 55squared = 3025 
 * 
 * Hence the difference between the sum of the squares of the first ten natural numbers and 
 * the square of the sum is 3025 − 385 = 2640.
 * 
 * Find the difference between the sum of the squares of the first one hundred natural numbers 
 * and the square of the sum.
 * 
 * @author Jake Holden - jholden88@live.co.uk
 *
 */
public class Problem6 extends Problem 
{
	protected void solve()
	{
		long sumOfSquares = 0;
		long squareOfSum = 0;
		
		for (int i = 1; i <= 100; i++)
		{
			sumOfSquares += MathUtil.square(i);
			squareOfSum += i;
		}
		
		squareOfSum = MathUtil.square(squareOfSum);
		System.out.println("The difference between the sum of the squares of the first one hundred "
				+ "natural numbers and the square of the sum is " + squareOfSum + " - " + sumOfSquares + " = " + (squareOfSum - sumOfSquares));
	}
}
