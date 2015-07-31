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
	private static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	private static List<String> words = new ArrayList<String>();
	private static Map<String, Integer> problemCountMap = new HashMap<String, Integer>();
	private static ProblemCountComparator comparator = new ProblemCountComparator(problemCountMap);
	private static Map<String, Integer> sortedProblemCountMap = new TreeMap<String, Integer>(comparator);
	private static Set<String> textFilePermutations = new HashSet<String>();
	
	public static void main(String[] args) throws InterruptedException
	{
		System.out.println(containsWord("synchronized", "snond"));
		System.out.println(containsWord("misfunctioned", "snond"));
		System.out.println(containsWord("mispronounced", "snond"));
		System.out.println(containsWord("shotgunned", "snond"));
		System.out.println(containsWord("snond", "snond"));
		System.out.println(getProblemCountFromFile(TEXT_FILE, "snond"));
		System.out.println(getProblemCountFromFile(TEXT_FILE, "rrizi"));
		
		generateProblemWords(cachedTextFile);
		
		System.out.println("Generated problem words");
		
		getProblemCount();
		
		sortedProblemCountMap.putAll(problemCountMap);
		
		Iterator<Entry<String, Integer>> iterator = sortedProblemCountMap.entrySet().iterator();
		
		for (int i = 0; i < 10; i++)
		{
			Entry<String, Integer> entry = iterator.next();
			
			System.out.println("Word = " + entry.getKey() + " Count = " + entry.getValue());
		}
	}
	
	public static void getProblemCount()
	{
		int count = textFilePermutations.size();
		System.out.println("Count = " + count);
		for (String string : textFilePermutations)
		{
			int problemCount = getProblemCountFromFile(TEXT_FILE, string);
			problemCountMap.put(string, problemCount);
			count--;
			System.out.println("Count = " + count);
		}
	}
	
	/**
	 * 
	 * @param file
	 * @param word
	 * @return
	 */
	public static int getProblemCountFromFile(String file, String word)
	{
		int problemCount = 0;
		BufferedReader reader = null;
		System.out.println("Checking " + word);
		
		if (cachedTextFile == null)
		{
			cachedTextFile = new ArrayList<String>();
			
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
		
		for (String string : cachedTextFile)
		{
			if (string.contains("" + word.charAt(0)) && 
					string.contains("" + word.charAt(1)) && 
					string.contains("" + word.charAt(2)) && 
					string.contains("" + word.charAt(3)) && 
					string.contains("" + word.charAt(4)))
			{
				if (containsWord(string, word))
				{
					problemCount++;
				}
			}
		}		
		
		System.out.println("Problem count = " + problemCount);
		return problemCount;
	}
	
	public static Map<String, Integer> getProblemCountMap()
	{
		Map<String, Integer> hashMap = new HashMap<String, Integer>();
		ProblemCountComparator comparator = new ProblemCountComparator(hashMap);
		Map<String, Integer> treeMap = new TreeMap<String, Integer>(comparator);
		
		for (String word : words)
		{
			int problemCount = getProblemCountFromFile(TEXT_FILE, word);
			hashMap.put(word, problemCount);
		}
		
		treeMap.putAll(hashMap);
		return treeMap;
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
	
	public static void generateProblemWords(List<String> strings)
	{
		for (String string : strings)
		{	
			if (string.length() == 5)
			{
				textFilePermutations.add(string);
			}
			if (string.length() > 5)
			{
				generate(string);
			}
		}
	}
	
	public static void generate(String string)
	{		
		System.out.println("Generating permutations for " + string);
		
		for (int i = 0; i < string.length() - 4; i++)
		{
			if (!containsSameCharacter(string.charAt(i), string.substring(0, i)) || i == 0)
			{
				for (int j = i + 1; j < string.length() - 3; j++)
				{
					if (!containsSameCharacter(string.charAt(j), string.substring(i + 1, j)) || j == i + 1)
					{
						for (int k = j + 1; k < string.length() - 2; k++)
						{
							if (!containsSameCharacter(string.charAt(k), string.substring(j + 1, k)) || k == j + 1)
							{
								for (int l = k + 1; l < string.length() - 1; l++)
								{
									if (!containsSameCharacter(string.charAt(l), string.substring(k + 1, l)) || l == k + 1)
									{
										for (int m = l + 1; m < string.length(); m++)
										{
											String problemWord = "";
											problemWord += string.charAt(i);
											problemWord += string.charAt(j);
											problemWord += string.charAt(k);
											problemWord += string.charAt(l);
											problemWord += string.charAt(m);
											
											if (containsWord(string, problemWord))
											{
												textFilePermutations.add(problemWord);
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
	
	public static boolean containsSameCharacter(char character, String string)
	{
		for (int i = 0; i < string.length(); i++)
		{
			if (character == string.charAt(i))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public static void generatePermutations(String string)
	{
		generatePermutations("", string, string.length(), 5);
	}
	
	public static void generatePermutations(String prefix, String string, int n, int k)
	{
		// Base case: k is 0, print prefix
        if (k == 0) 
        {
        	System.out.println(prefix);
        	textFilePermutations.add(prefix);
            return;
        }
 
        // One by one add all characters from set and recursively 
        // call for k equals to k-1
        for (int i = 0; i < n; ++i) 
        {             
            // Next character of input added
            String newPrefix = prefix + string.charAt(i); 
             
            // k is decreased, because we have added a new character
            generatePermutations(newPrefix, string, n, k - 1); 
        }
	}
	
	public static void generate(StringBuilder stringBuilder, int length)
	{
		if (length == stringBuilder.length())
		{
			words.add(stringBuilder.toString());
			String string = stringBuilder.toString();
			int problemCount = getProblemCountFromFile(TEXT_FILE, string);
			problemCountMap.put(string, problemCount);
			return;
		}
		
		for (char letter : alphabet)
		{
			stringBuilder.setCharAt(length, letter);
			generate(stringBuilder, length + 1);
		}
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
			if (map.get(string1) >= map.get(string2))
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
