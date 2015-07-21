import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *  You work on the popular game show Eel of Fortune, where contestants take turns fishing live eels 
 *  out of an aquarium for the opportunity to solve a word puzzle. The word puzzle works like the 
 *  game Hangman. A secret word is obscured on the board. A player guesses a letter of the alphabet, 
 *  and if that letter appears anywhere in the secret word, all of the times it appears in the secret 
 *  word are revealed.
 *  
 *  An unfortunate incident occurred on yesterday's show. The secret word was SYNCHRONIZED, and at one 
 *  point the board was showing:
 *  
 *  S _ N _ _ _ O N _ _ _ D
 *  
 *  As you can see, the letters on the board spelled "snond", which is of course an extremely offensive
 *  word for telemarketer in the Doldunian language. This incident caused ratings to immediately plummet 
 *  in East Doldunia. The Eel of Fortune producers want the ability to identify "problem words" for any
 *  given offensive word.
 *  
 *  Write a function that, given a secret word and an offensive word, returns true if the board could 
 *  theoretically display the offensive word (with no additional letters) during the course of solving 
 *  the secret word.
 *  
 *  problem("synchronized", "snond") -> true
 *  problem("misfunctioned", "snond") -> true
 *  problem("mispronounced", "snond") -> false
 *  problem("shotgunned", "snond") -> false
 *  problem("snond", "snond") -> true
 *  
 *  Optional:
 *  Define the problem count of an offensive word to be the number of words in the enable1 word list
 *  (https://code.google.com/p/dotnetperls-controls/downloads/detail?name=enable1.txt)
 *  that return true when paired with that offensive word as secret words. For instance, the problem 
 *  count of "snond" is 6. What is the problem count of "rrizi" (Zarthan offensive slang for the air 
 *  in potato chip bags)?
 *  
 *  What are the 10 largest problem counts of any sequence of 5 letters ("aaaaa", "aaaab", " aaaac", 
 *  through "zzzzz")? A solution to this problem needs to finish in less than a year. Aim for a few 
 *  minutes, or an hour at most. Post your output along with your code.
 *  
 *  @author Jake Holden - jholden88@live.co.uk
 */
public class EelOfFortune 
{
	private static final String TEXT_FILE = "C:\\enable1.txt";
	
	public static void main(String[] args)
	{
		System.out.println(containsWord("synchronized", "snond"));
		System.out.println(containsWord("misfunctioned", "snond"));
		System.out.println(containsWord("mispronounced", "snond"));
		System.out.println(containsWord("shotgunned", "snond"));
		System.out.println(containsWord("snond", "snond"));
		System.out.println(getProblemCount(TEXT_FILE, "snond"));
		System.out.println(getProblemCount(TEXT_FILE, "rrizi"));
	}
	
	/**
	 * 
	 * @param file
	 * @param word
	 * @return
	 */
	public static int getProblemCount(String file, String word)
	{
		int problemCount = 0;
		BufferedReader reader = null;
		
		try 
		{
			reader = new BufferedReader(new FileReader(file));
			String line;
			
			while ((line = reader.readLine()) != null)
			{
				if (line.length() >= word.length())
				{
					if (containsWord(line, word))
					{
						problemCount++;
					}
				}
			}
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			if (reader != null)
			{
				try 
				{
					reader.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
		
		return problemCount;
	}
	
	/**
	 * Check if a string contains another string regardless of the index of the characters.
	 * This method checks for exact matches only, for example if the string "barred" is being 
	 * checked for "bar" this method will return false because after filtering out all the
	 * characters in "barred" that do not contain the characters in "bar" you are left with 
	 * "barr". 
	 * @param stringToCheck - The string being checked.
	 * @param word - The string being checked for.
	 * @return True if the string to check contains the word, false if otherwise.
	 */
	public static boolean containsWord(String stringToCheck, String word)
	{		
		// Convert the word to a char array.
		char[] chars = word.toCharArray();
				
		// Iterate over the char array and create a regex.
		StringBuilder regex = new StringBuilder("[^");
		for (char c : chars)
		{
			regex.append(c);
		}
		regex.append("]");
		
		// Replace the characters in the string that are not part of the regex.
		stringToCheck = stringToCheck.replaceAll(regex.toString(), "");
		
		// Return true if the string to check is equal to the word, false if otherwise.
		return stringToCheck.equals(word);
	}
}
