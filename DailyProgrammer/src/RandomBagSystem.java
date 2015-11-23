import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;


/**
 * Description:
 * 
 * Contrary to popular belief, the tetromino pieces you are given in a game of Tetris are 
 * not randomly selected. Instead, all seven pieces are placed into a "bag." A piece is 
 * randomly removed from the bag and presented to the player until the bag is empty. When 
 * the bag is empty, it is refilled and the process is repeated for any additional pieces 
 * that are needed. In this way, it is assured that the player will never go too long 
 * without seeing a particular piece. It is possible for the player to receive two identical 
 * pieces in a row, but never three or more. Your task for today is to implement this system.
 * 
 * Output:
 * 
 * Output a string signifying 50 tetromino pieces given to the player using the random bag system. 
 * This will be on a single line. 
 * 
 * The pieces are as follows: 
 * 
 * -O
 * -I
 * -S
 * -Z
 * -L
 * -J
 * -T
 * 
 * Bonus:
 * 
 * Write a function that takes your output as input and verifies that it is a valid sequence of pieces.
 * 
 * @author Jake Holden - jholden88@live.co.uk
 *
 */
public class RandomBagSystem extends DailyProgrammer
{
	public static char[] pieces = new char[] { 'O', 'I', 'S', 'Z', 'L', 'J', 'T' };
	
	protected void solve() 
	{
		generatePieces(50);
	}
	
	private void generatePieces(int numberOfPieces)
	{
		List<Character> tempPieces = getPiecesAsList();		
		Random rand = new Random();
		String output = "";
		String tempOutput = "";
		
		while (numberOfPieces > 0)
		{
			if (tempPieces.size() == 0)
			{
				tempPieces = getPiecesAsList();
				tempOutput = "";
				output += tempOutput;
			}
			
			int n = rand.nextInt(tempPieces.size());
			char c = tempPieces.remove(n);
			tempOutput += c;
			output += c;
			numberOfPieces -= 1;
		}
		
		output += tempOutput;		
		System.out.println("Output: " + output);
	}
	
	private List<Character> getPiecesAsList()
	{
		List<Character> tempPieces = new ArrayList<Character>();
		for (char c : pieces)
		{
			tempPieces.add(c);
		}
		return tempPieces;
	}
	
	private boolean validatePieces(String pieces)
	{
		Set<Character> set = new HashSet<Character>();
		for (char c : pieces.toCharArray())
		{
			set.add(c);
		}
		return set.size() == 7;
	}
}
