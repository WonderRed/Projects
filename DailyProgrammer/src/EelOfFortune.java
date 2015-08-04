import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

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
 *  Optional Problem 1:
 *  Define the problem count of an offensive word to be the number of words in the enable1 word list
 *  (https://code.google.com/p/dotnetperls-controls/downloads/detail?name=enable1.txt)
 *  that return true when paired with that offensive word as secret words. For instance, the problem 
 *  count of "snond" is 6. What is the problem count of "rrizi" (Zarthan offensive slang for the air 
 *  in potato chip bags)?
 *  
 *  Optional Problem 2:
 *  What are the 10 largest problem counts of any sequence of 5 letters ("aaaaa", "aaaab", " aaaac", 
 *  through "zzzzz")? A solution to this problem needs to finish in less than a year. Aim for a few 
 *  minutes, or an hour at most.
 *  
 *  @author Jake Holden - jholden88@live.co.uk
 */
public class EelOfFortune 
{
	private static final String TEXT_FILE = "txt/enable1.txt";
	private static List<String> cachedTextFile = new ArrayList<String>();
	private static Map<String, Integer> problemWords = new HashMap<String, Integer>();
	private static ProblemCountComparator comparator = new ProblemCountComparator(problemWords);
	private static Map<String, Integer> sortedProblemWords = new TreeMap<String, Integer>(comparator);
	
	public static void main(String[] args)
	{
		// Keep track how long each problem takes by using start time and end time.
		long startTime = System.currentTimeMillis();		
		// Turn the text file into a list of strings.
		cacheTextFile(TEXT_FILE);		
				
		// Solve the main problem.
		System.out.println("Main problem:");
		System.out.println("containsOffensiveWord(\"synchronized\", \"snond\") = " + containsOffensiveWord("synchronized", "snond"));
		System.out.println("containsOffensiveWord(\"misfunctioned\", \"snond\") = " + containsOffensiveWord("misfunctioned", "snond"));
		System.out.println("containsOffensiveWord(\"mispronounced\", \"snond\") = " + containsOffensiveWord("mispronounced", "snond"));
		System.out.println("containsOffensiveWord(\"shotgunned\", \"snond\") = " + containsOffensiveWord("shotgunned", "snond"));
		System.out.println("containsOffensiveWord(\"snond\", \"snond\") = " + containsOffensiveWord("snond", "snond"));
		long endTime = System.currentTimeMillis();
		System.out.println("Completed main problem in " + (endTime - startTime) + "ms");
		
		// Solve optional problem 1.
		System.out.println("\nOptional problem 1:");
		System.out.println("Problem count for rrizi = " + getProblemCount("rrizi"));		
		startTime = endTime;
		endTime = System.currentTimeMillis();
		System.out.println("Completed optional problem 1 in " + (endTime - startTime) + "ms");
		
		// Solve optional problem 2.
		System.out.println("\nOption problem 2:");
		// Generate all the possible 5 letter problem words from the text file.
		generateProblemWords();
		// Sort the problem words map by value highest to lowest.
		sortedProblemWords.putAll(problemWords);		
		// Print out the first 10 entries in the map.
		Iterator<Entry<String, Integer>> iterator = sortedProblemWords.entrySet().iterator();		
		for (int i = 0; i < 10; i++)
		{
			Entry<String, Integer> entry = iterator.next();
			System.out.println("#" + (i + 1) + " " + entry.getKey() + " problem count = " + entry.getValue());
		}
		
		startTime = endTime;
		endTime = System.currentTimeMillis();
		System.out.println("Completed optional problem 2 in " + (endTime - startTime) + "ms");
		
	}
	
	/**
	 * Convert the text file into a list of strings.
	 * @param file  The file path.
	 */
	public static void cacheTextFile(String file)
	{
		BufferedReader reader = null;
		
		try 
		{
			reader = new BufferedReader(new FileReader(file));
			String line;
			
			while ((line = reader.readLine()) != null)
			{
				if (line.length() >= 5)
				{
					cachedTextFile.add(line);
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
	public static boolean containsOffensiveWord(String stringToCheck, String word)
	{	
		// If the string to check is smaller than the word being checked for there
		// is no need to continue so return false.
		if (stringToCheck.length() < word.length())
		{
			return false;
		}
		
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
	
	/**
	 * Get the problem count of the word by iterating through the cached text file. 
	 * @param word  The word used to get the problem count.
	 * @return  The problem count.
	 */
	public static int getProblemCount(String word)
	{
		int problemCount = 0;
		
		for (String string : cachedTextFile)
		{
			if (containsOffensiveWord(string, word))
			{
				problemCount++;
			}
		}		
		
		return problemCount;
	}
	
	/**
	 * Generate all the possible problem words and problem counts from the cached text file.
	 */
	public static void generateProblemWords()
	{
		// Loop through all the strings in the cached text file.
		for (String string : cachedTextFile)
		{
			// Create a set which will hold all the possible problem problem words of the 
			// current string.
			Set<String> temp = new HashSet<String>();
			
			// If the string is 5 characters long add it to the set.
			if (string.length() == 5)
			{
				temp.add(string);
			}
			else if (string.length() > 5)
			{
				// i starts at the first character of the string.
				for (int i = 0; i < string.length() - 4; i++)
				{
					if (!string.substring(0, i).contains("" + string.charAt(i)) || i == 0)
					{
						// j starts at the second character of the string.
						for (int j = i + 1; j < string.length() - 3; j++)
						{
							if (!string.substring(i + 1, j).contains("" + string.charAt(j)) || j == i + 1)
							{
								// k starts at the third character of the string.
								for (int k = j + 1; k < string.length() - 2; k++)
								{
									if (!string.substring(j + 1, k).contains("" + string.charAt(k)) || k == j + 1)
									{
										// l starts at the fourth character of the string.
										for (int l = k + 1; l < string.length() -1; l++)
										{
											if (!string.substring(k + 1, l).contains("" + string.charAt(l)) || l == k + 1)
											{
												// m starts at the fifth character of the string.
												for (int m = l + 1; m < string.length(); m++)
												{
													String word = "";
													word += (string.charAt(i));
													word += (string.charAt(j));
													word += (string.charAt(k));
													word += (string.charAt(l));
													word += (string.charAt(m));
													
													// Ensure the word created is offensive.
													if (containsOffensiveWord(string, word))
													{
													    temp.add(word);
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			
			// Loop through the set of problem words, if the problem words map already contains
			// the word add one on to the value, if it does not add it with a value of 1.
			for (String str : temp)
			{
				if (problemWords.containsKey(str))
				{
					int newVal = problemWords.get(str) + 1;
					problemWords.put(str, newVal);
				}
				else
				{
					problemWords.put(str, 1);
				}
			}
		}
	}
	
	/**
	 * Comparator to sort the problem words map by value highest to lowest.
	 */
	static class ProblemCountComparator implements Comparator<String>
	{
		Map<String, Integer> map;
		
		public ProblemCountComparator(Map<String, Integer> map)
		{
			this.map = map;
		}
		
		public int compare(String string1, String string2) 
		{
			if (map.get(string1) <= map.get(string2))
			{
				return 1;
			}
			else
			{
				return -1;
			}
			// Returning 0 would merge keys.
		}		
	}
}
