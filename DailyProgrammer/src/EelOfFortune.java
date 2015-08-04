import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
 *  Optional:
 *  Define the problem count of an offensive word to be the number of words in the enable1 word list
 *  (https://code.google.com/p/dotnetperls-controls/downloads/detail?name=enable1.txt)
 *  that return true when paired with that offensive word as secret words. For instance, the problem 
 *  count of "snond" is 6. What is the problem count of "rrizi" (Zarthan offensive slang for the air 
 *  in potato chip bags)?
 *  
 *  What are the 10 largest problem counts of any sequence of 5 letters ("aaaaa", "aaaab", " aaaac", 
 *  through "zzzzz")? A solution to this problem needs to finish in less than a year. Aim for a few 
 *  minutes, or an hour at most.
 *  
 *  @author Jake Holden - jholden88@live.co.uk
 */
public class EelOfFortune 
{
	private static final String TEXT_FILE = "txt/enable1.txt";
	private static List<String> cachedTextFile = null;
	private static Map<String, Integer> problemWords = new HashMap<String, Integer>();
	private static ProblemCountComparator comparator = new ProblemCountComparator(problemWords);
	private static Map<String, Integer> sortedProblemWords = new TreeMap<String, Integer>(comparator);
	
	public static void main(String[] args) throws InterruptedException
	{
		// Get the start time so we can keep track how long each problem takes.
		long startTime = System.currentTimeMillis();
		
		// Turn the text file into a list of strings.
		cacheTextFile(TEXT_FILE);
		
		long endTime = System.currentTimeMillis();
		System.out.println("Cached text file in " + (endTime - startTime) + "ms");
		
		System.out.println("\nMain problem:");
		System.out.println("containsOffensiveWord(\"synchronized\", \"snond\") = " + containsOffensiveWord("synchronized", "snond"));
		System.out.println("containsOffensiveWord(\"misfunctioned\", \"snond\") = " + containsOffensiveWord("misfunctioned", "snond"));
		System.out.println("containsOffensiveWord(\"mispronounced\", \"snond\") = " + containsOffensiveWord("mispronounced", "snond"));
		System.out.println("containsOffensiveWord(\"shotgunned\", \"snond\") = " + containsOffensiveWord("shotgunned", "snond"));
		System.out.println("containsOffensiveWord(\"snond\", \"snond\") = " + containsOffensiveWord("snond", "snond"));

		startTime = endTime;
		endTime = System.currentTimeMillis();
		System.out.println("Completed main problem in " + (endTime - startTime) + "ms");
		
		System.out.println("\nOptional problem 1:");
		System.out.println("Problem count for rrizi = " + getProblemCount("rrizi"));
		
		startTime = endTime;
		endTime = System.currentTimeMillis();
		System.out.println("Completed optional problem 1 in " + (endTime - startTime) + "ms");
		
		System.out.println("\nOption problem 2:");
		generateProblemWords();
		System.out.println("Problem words generated, size = " + problemWords.size());
		System.out.println("Sorting problem words to get top 10");
		sortedProblemWords.putAll(problemWords);
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
	 * Covert the text file into a list of strings.
	 * @param file  The file path.
	 */
	public static void cacheTextFile(String file)
	{
		cachedTextFile = new ArrayList<String>();
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
	 * @param word  
	 * @return  The problem count of the word.
	 */
	public static int getProblemCount(String word)
	{
		System.out.println("Getting problem count for " + word);
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
	
	public static void generateProblemWords()
	{
		for (String string : cachedTextFile)
		{
			List<String> temp = new ArrayList<String>();
			
			if (string.length() == 5)
			{
				temp.add(string);
			}
			else if (string.length() > 5)
			{
				for (int v = 0; v < string.length() - 4; v++)
                {
                    if (!string.substring(0, v).contains("" + string.charAt(v)) || v == 0)
                    {
	                    for (int w = v + 1; w < string.length() - 3; w++)
	                    {
	                        if (!string.substring(v + 1, w).contains("" + string.charAt(w)) || w == v + 1)
	                        {
		                        for (int x = w + 1; x < string.length() - 2; x++)
		                        {
		                            if (!string.substring(w + 1, x).contains("" + string.charAt(x)) || x == w + 1)
		                            {
			                            for (int y = x + 1; y < string.length() -1; y++)
			                            {
			                                if (!string.substring(x + 1, y).contains("" + string.charAt(y)) || y == x + 1)
			                                {
				                                for (int z = y + 1; z < string.length(); z++)
				                                {
				                                    String phrase = "";
				                                    phrase += (string.charAt(v));
				                                    phrase += (string.charAt(w));
				                                    phrase += (string.charAt(x));
				                                    phrase += (string.charAt(y));
				                                    phrase += (string.charAt(z));
				                                    
				                                    if (containsOffensiveWord(string, phrase))
				                                    {
				                                        temp.add(phrase);
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
			
			for (int i = 0; i < temp.size(); i++)
            {
                boolean anotherFound = false;
                
                if (i != 0)
                { 
                    for (int j = i - 1; j >= 0; j--)
                    {
                        if (temp.get(j).equals(temp.get(i)))
                        {
                            anotherFound = true;
                        }
                    }
                }
                
                if (!anotherFound)
                {
                    if (problemWords.containsKey(temp.get(i)))
                    {
                        int newVal = problemWords.get(temp.get(i)) + 1;
                        problemWords.put(temp.get(i), newVal);
                    }
                    else
                    {
                        problemWords.put(temp.get(i), 1);
                    }
                }
            }
		}
	}
}
