package problems;

/**
 * A Pythagorean triplet is a set of three natural numbers, 
 * a < b < c, for which, a2 + b2 = c2 
 * For example, 32 + 42 = 9 + 16 = 25 = 52. 
 * 
 * There exists exactly one Pythagorean triplet for which a + b + c = 1000. 
 * Find the product abc.
 * 
 * @author Jake Holden - jholden88@live.co.uk
 *
 */
public class Problem9 extends Problem 
{
	protected void solve() 
	{
		// Use Euclid's formula.
		int a, b, c;
	    int sqrt = (int) Math.sqrt(1000);
	    for (int n = 1; n <= sqrt; n++) 
	    {
	        for (int m = n + 1; m <= sqrt; m++) 
	        {
	            a = m * m - n * n;
	            b = 2 * m * n;
	            c = m * m + n * n;
	            
	            if (a + b + c == 1000) 
	            {
	                System.out.print(a * b * c);
	                return;
	            }
	        }
	    }				
	}
}
