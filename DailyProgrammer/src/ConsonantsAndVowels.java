import java.util.Random;


/**
 * ====================================================================
 * 
 * Consonants and Vowels
 * 
 * ====================================================================
 * 
 * Description:
 * 
 * You were hired to create words for a new language. However, your boss wants these words to follow a 
 * strict pattern of consonants and vowels. You are bad at creating words by yourself, so you decide it 
 * would be best to randomly generate them. Your task is to create a program that generates a random 
 * word given a pattern of consonants (c) and vowels (v).
 * 
 * Example input:
 * 
 * CvVcVC
 * 
 * Example output:
 * 
 * JaItUP
 * 
 * @author Jake Holden - jholden88@live.co.uk
 *
 */
public class ConsonantsAndVowels extends DailyProgrammer
{
	private static final String CONSONANTS = "bcdfghjklmnpqrstvwxyz";
	private static final String VOWELS = "aeiou";
	
	protected void solve() 
	{
		generateWord("cv");
		generateWord("CccvvV");
		generateWord("CvvCccVVVvCCcVvCvcv");
		generateWord("FAIL");
	}
	
	/**
	 * Generate a word based on the pattern.
	 * 
	 * @param pattern The pattern used to generate a word.
	 * @return A randomly generated String based on the pattern.
	 */
	private String generateWord(String pattern)
	{
		System.out.println("Input = " + pattern);
		String generatedWord = "";
		
		// Ensure the pattern only contains C and V (lower and upper case).
		if (pattern.matches("[cvCV]*"))
		{			
			for (int i = 0; i < pattern.length(); i++)
			{
				if (pattern.charAt(i) == 'c' || pattern.charAt(i) == 'C')
				{
					char consonant = generateConsonant();
					if (pattern.charAt(i) == 'C')
					{
						consonant = Character.toUpperCase(consonant);
					}
					generatedWord += consonant;
				}
				else if (pattern.charAt(i) == 'v' || pattern.charAt(i) == 'V')
				{
					char vowel = generateVowel();
					if (pattern.charAt(i) == 'V')
					{
						vowel = Character.toUpperCase(vowel);
					}
					generatedWord += vowel;
				}
			}
			
		}
		else
		{
			generatedWord = "Please use a string that contains only c or v (lower or upper case).";
		}
		
		System.out.println("Output = " + generatedWord);
		return generatedWord;
	}
	
	/**
	 * Generate a random consonant.
	 * 
	 * @return A random consonant char.
	 */
	private char generateConsonant()
	{
		Random random = new Random();
		int index = random.nextInt(20) + 1;
		return CONSONANTS.charAt(index);
	}
	
	/**
	 * Generate a random vowel.
	 * 
	 * @return A random vowel char.
	 */
	private char generateVowel()
	{
		Random random = new Random();
		int index = random.nextInt(4) + 1;
		return VOWELS.charAt(index);
	}
}
