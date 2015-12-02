package problems;

/**
 * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder. 
 * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
 * 
 * @author Jake Holden - jholden88@live.co.uk
 *
 */
public class Problem5 extends Problem 
{
	protected void solve()
	{
		boolean numberFound = false;
		int n = 20;
		
		while (!numberFound)
		{
			boolean isDivisible = true;
			
			for (int i = 1; i <= 20; i++)
			{
				if (n % i != 0)
				{
					isDivisible = false;
					break;
				}
			}
			
			if (isDivisible)
			{
				numberFound = true;
			}
			else
			{
				n++;
			}
		}
		
		System.out.println("The smallest positive number that is evenly divisible by all of the number from 1 to 20 is " + n);
	}
}
