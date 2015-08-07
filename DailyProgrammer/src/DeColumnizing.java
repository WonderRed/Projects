import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 * ====================================================================
 * 
 * De-columnizing
 * 
 * ====================================================================
 * 
 * Often, column-style writing will put images and features to the left or right of the body of text, for example:
 *
 * 24
 * This is an example piece of text. This is an exam-
 * ple piece of text. This is an example piece of
 * text. This is an example
 * piece of text. This is a +-----------------------+
 * sample for a challenge.  |                       |
 * Lorum ipsum dolor sit a- |       top class       |
 * met and other words. The |        feature        |
 * proper word for a layout |                       |
 * like this would be type- +-----------------------+
 * setting, or so I would
 * imagine, but for now let's carry on calling it an
 * example piece of text. Hold up - the end of the
 *                  paragraph is approaching - notice
 * +--------------+ the double line break for a para-
 * |              | graph.
 * |              |
 * |   feature    | And so begins the start of the
 * |   bonanza    | second paragraph but as you can
 * |              | see it's only marginally better
 * |              | than the other one so you've not
 * +--------------+ really gained much - sorry. I am
 *                  certainly not a budding author
 * as you can see from this example input. Perhaps I
 * need to work on my writing skills.
 * 
 * In order to fit into the column format, some words are hyphenated. For the purpose of the challenge, you may assume
 * that any hyphens at the end of a line join a single un-hyphenated word together (for example, the exam- and ple in
 * the above input form the word example and not exam-ple). However, hyphenated words that do not span multiple lines
 * should retain their hyphens. Side features will only appear at the far left or right of the input, and will always
 * be bordered by the +---+ style shown above. They will also never have 'holes' in them, like this:
 * 
 * +--------------------+
 * |                    |
 * | Inside the feature |
 * |                    |
 * | +----------------+ |
 * | |                | |
 * | |     Outside    | |
 * | |                | |
 * | +----------------+ |
 * |                    |
 * +--------------------+
 * 
 * Paragraphs in the input are separated by double line breaks. Your task today is to extract just the paragraph text from 
 * the input, removing the feature-boxes.
 * 
 * ====================================================================
 * 
 * Formal Inputs and Outputs
 * 
 * ====================================================================
 * 
 * Input Specification:
 * 
 * You'll be given a number N on one line, followed by N further lines of input like the example in the description above.
 * 
 * Output Description:
 * 
 * Output just the paragraph text, de-hyphenating words where appropriate (ie. a line of text ends with a hyphen).
 * 
 * ====================================================================
 * 
 * Sample Inputs and Outputs
 * 
 * ====================================================================
 * 
 * Example 1:
 * 
 * This corresponds to the input given in the Description.
 * 
 * Output
 * 
 * This is an example piece of text. This is an example piece of text. This is an example piece of text. This is an example 
 * piece of text. This is a sample for a challenge. Lorum ipsum dolor sit amet and other words. The proper word for a layout 
 * like this would be typesetting, or so I would imagine, but for now let's carry on calling it an example piece of text. Hold 
 * up - the end of the paragraph is approaching - notice the double line break for a paragraph.
 * 
 * And so begins the start of the second paragraph but as you can see it's only marginally better than the other one so you've 
 * not really gained much - sorry. I am certainly not a budding author as you can see from this example input. Perhaps I need 
 * to work on my writing skills.
 * 
 * 
 * Example 2:
 * 
 * Input
 * 
 * 22
 * +-------------+ One hundred and fifty quadrillion,
 * |             | seventy-two trillion, six hundred
 * | 150 072 626 | and twenty-six billion, eight hun-
 * | 840 312 999 | dred and fourty million, three
 * |             | hundred and thirteen thousand sub-
 * +-------------+ tract one is a rather large prime
 *                 number which equals one to five if
 * calculated modulo two to six respectively.
 *  
 * However, one other rather more in- +-------------+
 * teresting number is two hundred    |             |
 * and twenty-one quadrillion, eight  | 221 806 434 |
 * hundred and six trillion, four     | 537 978 679 |
 * hundred and thirty-four billion,   |             |
 * five hundred and thirty-seven mil- +-------------+
 * million, nine hundred and seven-
 *                                ty-eight thousand,
 * +-----------------------------+ six hundred and
 * |                             | seventy nine,
 * | Subscribe for more Useless  | which isn't prime
 * |      Number Facts(tm)!      | but is the 83rd
 * +-----------------------------+ Lucas number.
 * 
 * Output
 * 
 * One hundred and fifty quadrillion, seventy-two trillion, six hundred and twenty-six billion, eight hundred and fourty 
 * million, three hundred and thirteen thousand subtract one is a rather large prime number which equals one to five if 
 * calculated modulo two to six respectively.
 * However, one other rather more interesting number is two hundred and twenty-one quadrillion, eight hundred and six trillion, 
 * four hundred and thirty-four billion, five hundred and thirty-seven milmillion, nine hundred and seventy-eight thousand, 
 * six hundred and seventy nine, which isn't prime but is the 83rd Lucas number.
 * 
 * 
 * Example 3:
 * 
 * Input
 * 
 * 16
 * +----------------+ Lorem ipsum dolor sit amet,
 * |                | consectetur adipiscing elit,
 * |  Aha, now you  | sed do eiusmod tempor incid-
 * |  are stumped!! | idunt ut labore et dolore
 * |                | magna aliqua. Ut enim ad mi-
 * |       +--------+ nim veniam, quis nostrud ex-
 * |  top  |          ercitation ullamco laboris
 * |  kek  | nisi ut aliquip ex.
 * |       |                       +-------------+
 * +-------+ Duis aute irure dolor |             |
 * in repre-henderit in voluptate  | Nothing to  |
 * velit esse cillum dolore eu fu- |  see here.  |
 * giat nulla pariatur. Excepteur  |             |
 * sint occaecat cupidatat non     +-------------+
 * proident, sunt in culpa qui of-
 * ficia deserunt mollit anim id est laborum.
 * 
 * Output
 * 
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
 * Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex.
 * Duis aute irure dolor in repre-henderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint 
 * occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
 * 
 * ====================================================================
 * 
 * Extension (Intermediate)
 * 
 * ====================================================================
 * 
 * At the start of each paragraph in your output, list the text of each feature associated with that paragraph. A feature is 
 * "associated" with a paragraph if the top of the feature box (the +--------+) starts on or below the line that the paragraph 
 * starts on. For example, the outputs for the above three examples would be:
 * 
 * Example 1 Output:
 * 
 * (top class feature) (feature bonanza) This is an example piece of text. This is an example piece of text. This is an example 
 * piece of text. This is an example piece of text. This is a sample for a challenge. Lorum ipsum dolor sit amet and other words. 
 * The proper word for a layout like this would be typesetting, or so I would imagine, but for now let's carry on calling it an 
 * example piece of text. Hold up - the end of the paragraph is approaching - notice the double line break for a paragraph.
 * And so begins the start of the second paragraph but as you can see it's only marginally better than the other one so you've 
 * not really gained much - sorry. I am certainly not a budding author as you can see from this example input. Perhaps I need to 
 * work on my writing skills.
 * 
 * Example 2 Output:
 * 
 * (150 072 626 840 312 999) One hundred and fifty quadrillion, seventy-two trillion, six hundred and twenty-six billion, eight 
 * hundred and fourty million, three hundred and thirteen thousand subtract one is a rather large prime number which equals one to 
 * five if calculated modulo two to six respectively.
 * (221 806 434 537 978 679) (Subscribe for more Useless Number Facts(tm)!) However, one other rather more interesting number is 
 * two hundred and twenty-one quadrillion, eight hundred and six trillion, four hundred and thirty-four billion, five hundred and 
 * thirty-seven milmillion, nine hundred and seventy-eight thousand, six hundred and seventy nine, which isn't prime but is the 83rd 
 * Lucas number.
 * 
 * Example 3 Output:
 * 
 * (Aha, now you are stumped! top kek) (Nothing to see here.) Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod 
 * tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut 
 * aliquip ex.
 * Duis aute irure dolor in repre-henderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat 
 * cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. 
 * 
 * @author Jake Holden - jholden88@live.co.uk
 */
public class DeColumnizing extends DailyProgrammer
{
	private static final String EXAMPLE_1 = "txt/decolumnizing/Example1.txt";
	private static final String EXAMPLE_2 = "txt/decolumnizing/Example2.txt";
	private static final String EXAMPLE_3 = "txt/decolumnizing/Example3.txt";
	
	public void solve()
	{
		System.out.println("Output 1:\n");
		readFile(EXAMPLE_1);	
		System.out.println("\nOutput 2:\n");	
		readFile(EXAMPLE_2);
		System.out.println("\nOutput 3:\n");
		readFile(EXAMPLE_3);
	}
	
	private static void readFile(String file)
	{
		BufferedReader reader = null;
		
		try 
		{
			// Read the file.
			reader = new BufferedReader(new FileReader(file));
			// Read the first line, this is the total lines to read.
			String firstLine = reader.readLine();
			int totalLines = 0;
			
			if (firstLine != null)
			{
				try
				{
					totalLines = Integer.parseInt(firstLine);
				}
				catch (NumberFormatException e)
				{
					e.printStackTrace();
				}
			}
			
			if (totalLines > 0)
			{		
				List<String> output = new ArrayList<String>();
				Map<Integer, String> hyphenatedWords = new HashMap<Integer, String>();
				
				int count = 1;
				List<String> featuredText = new ArrayList<String>();
				
				while (count <= totalLines)
				{
					String line = reader.readLine();
					
					if (line != null)
					{
						if (!line.matches("^.*[^a-zA-Z0-9 ].*$"))
						{
							StringBuilder textToAdd = new StringBuilder();
							Iterator<String> it = featuredText.iterator();
							textToAdd.append("(");
							
							while (it.hasNext())
							{
								textToAdd.append(it.next());
								
								if (it.hasNext())
								{
									textToAdd.append(" ");
								}
							}
							
							textToAdd.append(")");

							String text = output.get(0);
							text = textToAdd.toString() + text;
							output.set(0, text);
						}
						
						if (line.endsWith("-"))
						{
							hyphenatedWords.put(count, line.substring(line.lastIndexOf(" "), line.length() - 1));
						}
						
						if (line.startsWith("|") || line.endsWith("|"))
						{
							String featuredLine = line.substring(line.indexOf("|"), line.lastIndexOf("|"));
							featuredLine = featuredLine.replaceAll("[^A-Za-z0-9() ]", "");
							featuredLine = featuredLine.trim();
							
							if (!featuredLine.equals(""))
							{
								featuredText.add(featuredLine);
							}
						}
						
						output.add(readLine(line));
					}
					count++;
				}
								
				for (Entry<Integer, String> entry : hyphenatedWords.entrySet())
				{
					int lineNumber = entry.getKey();
					String hyphenatedWordStart = entry.getValue();
					
					String line = output.get(lineNumber - 1);
					String line2 = output.get(lineNumber);

					int endIndex = line2.indexOf(" ") == -1 ? line2.length() : line2.indexOf(" ");
					String hyphenatedWordEnd = line2.substring(0, endIndex);
					String hyphenatedWord = hyphenatedWordStart + hyphenatedWordEnd;
					
					line = line.substring(0, line.lastIndexOf(" "));
					line = line + hyphenatedWord;
					
					line2 = line2.substring(endIndex);
					
					output.set(lineNumber - 1, line);
					if (line2.equals(""))
					{
						output.remove(lineNumber);
					}
					else
					{
						output.set(lineNumber, line2);
					}
				}
				
				for (String string : output)
				{
					System.out.println(string);
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
	
	private static String readLine(String line)
	{				
		if (line.contains("|"))
		{
			if (line.startsWith("|"))
			{
				line = line.substring(line.lastIndexOf("|") + 1);
			}
			
			if (line.endsWith("|"))
			{
				line = line.substring(0, line.indexOf("|"));
			}
		}
		
		line = line.replaceAll("[^A-Za-z0-9., ]", "");
		line = line.trim();
		
		return line;
	}
}

