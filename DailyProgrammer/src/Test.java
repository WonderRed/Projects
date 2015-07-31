import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Test 
{
    public static boolean checkCharPart(char c, String letters)
    {
        boolean returnable = false;
        
        for(int k = 0; k < letters.length(); ++k)
        {
            if (letters.charAt(k) == c)
            {
                returnable = true;
            }
        }
        
        return returnable;
    }

    public static boolean offensive(String word, String phrase)
    {
        boolean returnable = false;        
        String phraseTested = "";
        
        for (int i = 0; i < word.length(); i++)
        {
            if (checkCharPart(word.charAt(i), phrase))
            {
                phraseTested += word.charAt(i);
            }
        }

        if (phrase.equals(phraseTested))
        {
            returnable = true;
        }
        
        return returnable;
    }

    public static int problemCount(ArrayList<String> str, String phrase)
    {
        int problemCount = 0;
        
        for (int a = 0; a < str.size(); ++a)
        {
            if (offensive(str.get(a), phrase))
            {
                problemCount++;
            }
        }
        
        return problemCount;
    }


    public static void problemCountAll(ArrayList<String> str)
    {
        Map <String, Integer> allProblemCount = new HashMap<String, Integer>();
        for (int u = 0; u < str.size(); ++u)
        {
            ArrayList<String> temp = new ArrayList<String>();
            String phrase = "";
            if (str.get(u).length() == 5)
            {
                temp.add(str.get(u));
            }
            else if (str.get(u).length() > 5)
            {
                for (int v = 0; v < str.get(u).length() - 4; ++v)
                {
                    if (!checkCharPart(str.get(u).charAt(v), str.get(u).substring(0,v)) || v == 0)
                    {
	                    for (int w = v + 1; w < str.get(u).length() - 3; ++w)
	                    {
	                        if (!checkCharPart(str.get(u).charAt(w), str.get(u).substring(v+1,w)) || w == v+1)
	                        {
		                        for (int x = w + 1; x < str.get(u).length() - 2; ++x)
		                        {
		                            if (!checkCharPart(str.get(u).charAt(x), str.get(u).substring(w+1,x)) || x == w+1)
		                            {
			                            for (int y = x + 1; y < str.get(u).length() -1; ++y)
			                            {
			                                if (!checkCharPart(str.get(u).charAt(y), str.get(u).substring(x+1,y)) || y == x+1)
			                                {
				                                for (int z = y + 1; z < str.get(u).length(); ++z)
				                                {
				                                    String stringU = str.get(u);
				                                    phrase = "";
				                                    phrase += (stringU.charAt(v));
				                                    phrase += (stringU.charAt(w));
				                                    phrase += (stringU.charAt(x));
				                                    phrase += (stringU.charAt(y));
				                                    phrase += (stringU.charAt(z));
				                                    if (offensive(str.get(u), phrase))
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
            
            for (int r = 0; r < temp.size(); ++r)
            {
                boolean anotherFound = false;
                if (r != 0)
                { 
                    for (int s = r-1; s >= 0; --s)
                    {
                        if (temp.get(s).equals(temp.get(r)))
                        {
                            anotherFound = true;
                        }
                    }
                }
                
                if (!anotherFound)
                {
                    if (allProblemCount.containsKey(temp.get(r)))
                    {
                        int newVal = allProblemCount.get(temp.get(r)) + 1;
                        allProblemCount.put(temp.get(r), newVal);
                    }
                    else
                    {
                        allProblemCount.put(temp.get(r), 1);
                    }
                }
            }       
        }

        String name[] = new String[10];
        int val[] = new int[10];
        int count = 0;
        for (String g : allProblemCount.keySet())
        {
            int value = allProblemCount.get(g);

            if (count < 10)
            {
                name[count] =g;
                val[count] = value;
            }
            else
            {
                boolean valDidntHappen = true;
                for (int h = 0; h < val.length; ++h)
                {
                    if(val[h] < value && valDidntHappen)
                    {
                        valDidntHappen = false;         
                        for (int q = val.length -1; q > h; --q)
                        {
                            name[q] = name[q-1];
                            val[q] = val[q-1];
                        }

                        name[h] = g;
                        val[h] = value;
                    }
                }
            }
            count++;
        }

        for (int t = 0; t < name.length; ++t)
        {
            System.out.println(t + ". " + name[t] + " has " + val[t] + " problem values.");
        }
    }

    public static void main(String[] args) throws IOException{
        double startTime = System.currentTimeMillis();
        ArrayList<String> words = new ArrayList<String>();
        String filename = "txt/enable1.txt";
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String word = "lskdjaf";
        String line;
        while (word != null) {
            line = reader.readLine();
            if (line == null) {
                break;
            }
            // Ensures it just stores the word and not the spaces.
            word = "";
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) != ' ') {
                    word += line.charAt(i);
                }

            }
            words.add(word);
        }
        reader.close();
        double endTime = System.currentTimeMillis();
        double allTime = (endTime-startTime);
        System.out.println("Spent " + (allTime) + " milliseconds storing words to an arraylist.\n");

        System.out.println("Main Problem");
		System.out.println("=========================================================================");
        System.out.println("offensive(\"synchronized\", \"snond\") -> " + offensive("synchronized", "snond"));
        System.out.println("offensive(\"misfunctioned\", \"snond\") -> " + offensive("misfunctioned", "snond"));
        System.out.println("offensive(\"mispronounced\", \"snond\") -> " + offensive("mispronounced", "snond"));
        System.out.println("offensive(\"shotgunned\", \"snond\") -> " + offensive("shotgunned", "snond"));
        System.out.println("offensive(\"snond\", \"snond\") -> " + offensive("snond", "snond"));

        startTime = endTime;
        endTime = System.currentTimeMillis();
        allTime += (endTime-startTime);
        System.out.println("Spent " + (endTime-startTime) + " milliseconds on the main problem.\n");

        System.out.println("Challenge Problem #1");
        System.out.println("=========================================================================");
        System.out.println("Problem Count for rrizi is " + problemCount(words, "rrizi"));

        startTime = endTime;
        endTime = System.currentTimeMillis();
        allTime += (endTime-startTime);
        System.out.println("Spent " + (endTime-startTime) + " milliseconds finding the problem count for rrizi for challenge #1.\n");


        System.out.println("Challenge Problem #2");
        System.out.println("=========================================================================");
        startTime = endTime;
        problemCountAll(words);
        endTime = System.currentTimeMillis();
        allTime += (endTime-startTime);
        System.out.println("Spent " + (endTime-startTime) + " milliseconds finding all the problem counts for challenge #2.\n");
        System.out.println("Spent " + allTime + " milliseconds throughout the entire program.");
    }

}