package problems;

/**
 * Abstract class to run the project euler classes from.
 * 
 * @author Jake Holden - jholden88@live.co.uk
 *
 */
public abstract class Problem
{
	/**
	 * Solve the project euler problems. 
	 */
	protected abstract void solve();
	
	public static void main(String[] args)
	{
		Problem problem = new Problem10();
		problem.solve();
	}
}
