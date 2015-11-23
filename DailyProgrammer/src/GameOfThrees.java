
/**
 * Threes Game:
 * 
 * Start with a random large number then do the following:
 * 
 * - If the number is divisible by 3, divide it by 3. 
 * - If it's not, either add 1 or subtract 1 (to make it divisible by 3), then divide it by 3.
 * 
 * Challenge Description:
 * 
 * The input is a single number: the number at which the game starts. Write a program that plays 
 * the Threes game, and outputs a valid sequence of steps you need to take to get to 1. Each step 
 * should be output as the number you start at, followed by either -1 or 1 (if you are 
 * adding/subtracting 1 before dividing), or 0 (if you are just dividing). The last line should 
 * simply be 1.
 * 
 * @author Jake Holden - jholden88@live.co.uk
 *
 */
public class GameOfThrees extends DailyProgrammer
{
	protected void solve() 
	{
		gameOfThrees(31337357);
	}
	
	private void gameOfThrees(int n)
	{
		System.out.println("Staring number = " + n);
		
		while (n > 1)
		{
			if (n % 3 == 0)
			{
				System.out.println(n + " 0");
			}
			else if (n % 3 == 1)
			{
				System.out.println(n + " -1");
				n -= 1;
			}
			else if (n % 3 == 2)
			{
				System.out.println(n + " 1");
				n += 1;
			}
			n = n / 3;
		}
		
		System.out.println(n);
	}
}
