
/**
 * Abstract class to run the daily programmer classes from.
 * 
 * @author Jake Holden - jholden88@live.co.uk
 *
 */
public abstract class DailyProgrammer 
{	
	/**
	 * Solve the daily programmer problems. 
	 */
	protected abstract void solve();
	
	public static void main(String[] args)
	{
		// Construct a subclass.
		DailyProgrammer dailyProgrammer = new ConsonantsAndVowels();
		// Solve the problem.
		dailyProgrammer.solve();
	}
}
