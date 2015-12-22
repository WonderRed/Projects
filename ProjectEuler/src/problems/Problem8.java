package problems;


/**
 * The four adjacent digits in the 1000-digit number that have the greatest product are 9 × 9 × 8 × 9 = 5832. 
 * 
 * 73167176531330624919225119674426574742355349194934 
 * 96983520312774506326239578318016984801869478851843 
 * 85861560789112949495459501737958331952853208805511 
 * 12540698747158523863050715693290963295227443043557 
 * 66896648950445244523161731856403098711121722383113 
 * 62229893423380308135336276614282806444486645238749 
 * 30358907296290491560440772390713810515859307960866 
 * 70172427121883998797908792274921901699720888093776 
 * 65727333001053367881220235421809751254540594752243 
 * 52584907711670556013604839586446706324415722155397 
 * 53697817977846174064955149290862569321978468622482 
 * 83972241375657056057490261407972968652414535100474 
 * 82166370484403199890008895243450658541227588666881 
 * 16427171479924442928230863465674813919123162824586 
 * 17866458359124566529476545682848912883142607690042 
 * 24219022671055626321111109370544217506941658960408 
 * 07198403850962455444362981230987879927244284909188 
 * 84580156166097919133875499200524063689912560717606 
 * 05886116467109405077541002256983155200055935729725 
 * 71636269561882670428252483600823257530420752963450 
 * 
 * Find the thirteen adjacent digits in the 1000-digit number that have the greatest product. What is the value of this product?
 * 
 * @author Jake Holden - jholden88@live.co.uk
 *
 */
public class Problem8 extends Problem
{
	private String number = "73167176531330624919225119674426574742355349194934" +
							"96983520312774506326239578318016984801869478851843" +
							"85861560789112949495459501737958331952853208805511" +
							"12540698747158523863050715693290963295227443043557" +
							"66896648950445244523161731856403098711121722383113" +
							"62229893423380308135336276614282806444486645238749" +
							"30358907296290491560440772390713810515859307960866" +
							"70172427121883998797908792274921901699720888093776" +
							"65727333001053367881220235421809751254540594752243" +
							"52584907711670556013604839586446706324415722155397" +
							"53697817977846174064955149290862569321978468622482" +
							"83972241375657056057490261407972968652414535100474" +
							"82166370484403199890008895243450658541227588666881" +
							"16427171479924442928230863465674813919123162824586" +
							"17866458359124566529476545682848912883142607690042" +
							"24219022671055626321111109370544217506941658960408" +
							"07198403850962455444362981230987879927244284909188" +
							"84580156166097919133875499200524063689912560717606" +
							"05886116467109405077541002256983155200055935729725" +
							"71636269561882670428252483600823257530420752963450";
	
	protected void solve() 
	{
		int[] numbers = new int[number.length()];
		
		for (int i = 0; i < numbers.length; i++)
		{
			numbers[i] = Integer.parseInt(Character.toString(number.charAt(i)));
		}
		
		long greatestProduct = 0;
		
		for (int i = 0; i < numbers.length - 12; i++)
		{
			long tempProduct = 1;
			
			for (int j = i; j < i + 13; j++)
			{
				if (numbers[j] == 0)
				{
					break;
				}
				
				tempProduct *= numbers[j];
			}
			
			if (tempProduct > greatestProduct)
			{
				greatestProduct = tempProduct;
			}
		}
		
		System.out.println("Greatest product is " + greatestProduct);
		
		/*
		int[][] numbers = new int[20][50];
		int count = 0;
		
		// Convert the string into a 2D int array.
		for (int i = 0; i < 20; i++)
		{
			for (int j = 0; j < 50; j++)
			{
				numbers[i][j] = Integer.parseInt(Character.toString(number.charAt(count)));
				count++;
			}
		}

		System.out.println("Highest horizontal product is " + calculateHighestHorizontalProduct(numbers));
		long greatestProduct = calculateHighestHorizontalProduct(numbers);
		System.out.println("Highest vertical product is " + calculateHighestVerticalProduct(numbers));
		if (calculateHighestVerticalProduct(numbers) > greatestProduct)
		{
			greatestProduct = calculateHighestVerticalProduct(numbers);
		}
		System.out.println("Highest diagonal forward product is " + calculateHighestDiagonalForwardProduct(numbers));
		if (calculateHighestDiagonalForwardProduct(numbers) > greatestProduct)
		{
			greatestProduct = calculateHighestDiagonalForwardProduct(numbers);
		}
		System.out.println("Highest diagonal backward product is " + calculateHighestDiagonalBackwardProduct(numbers));
		if (calculateHighestDiagonalBackwardProduct(numbers) > greatestProduct)
		{
			greatestProduct =  calculateHighestDiagonalBackwardProduct(numbers);
		}
		System.out.println("Greatest product is " + greatestProduct);*/
	}
	
	private long calculateHighestHorizontalProduct(int[][] numbers)
	{
		long product = 0;
		
		for (int i = 0; i < numbers.length; i++)
		{
			for (int j = 0; j <= 37; j++)
			{
				long tempProduct = 1;
				
				for (int k = j; k < j + 13; k++)
				{
					tempProduct *= numbers[i][k];
				}
				
				if (tempProduct > product)
				{
					product = tempProduct;
				}
			}
		}
		
		return product;
	}
	
	private long calculateHighestVerticalProduct(int[][] numbers)
	{
		long product = 0;
		
		for (int i = 0; i < 50; i++)
		{			
			for (int j = 0; j <= 7; j++)
			{
				long tempProduct = 1;
				
				for (int k = j; k < j + 13; k++)
				{				
					tempProduct *= numbers[k][i];
				}
				
				if (tempProduct > product)
				{
					product = tempProduct;
				}
			}			
		}
		
		return product;
	}
	
	private long calculateHighestDiagonalForwardProduct(int[][] numbers)
	{
		long product = 0;
		
		for (int i = 0; i <= 7; i++)
		{
			for (int j = 0; j <= 37; j++)
			{
				long tempProduct = 1;				
				tempProduct *= numbers[i][j];
				tempProduct *= numbers[i + 1][j + 1];
				tempProduct *= numbers[i + 2][j + 2];
				tempProduct *= numbers[i + 3][j + 3];
				tempProduct *= numbers[i + 4][j + 4];
				tempProduct *= numbers[i + 5][j + 5];
				tempProduct *= numbers[i + 6][j + 6];
				tempProduct *= numbers[i + 7][j + 7];
				tempProduct *= numbers[i + 8][j + 8];
				tempProduct *= numbers[i + 9][j + 9];
				tempProduct *= numbers[i + 10][j + 10];
				tempProduct *= numbers[i + 11][j + 11];
				tempProduct *= numbers[i + 12][j + 12];
				
				if (tempProduct > product)
				{
					product = tempProduct;
				}
			}
		}
		
		return product;
	}
	
	private long calculateHighestDiagonalBackwardProduct(int[][] numbers)
	{
		long product = 0;
		
		for (int i = 0; i <= 7; i++)
		{
			for (int j = 12; j <= 49; j++)
			{
				long tempProduct = 1;				
				tempProduct *= numbers[i][j];
				tempProduct *= numbers[i + 1][j - 1];
				tempProduct *= numbers[i + 2][j - 2];
				tempProduct *= numbers[i + 3][j - 3];
				tempProduct *= numbers[i + 4][j - 4];
				tempProduct *= numbers[i + 5][j - 5];
				tempProduct *= numbers[i + 6][j - 6];
				tempProduct *= numbers[i + 7][j - 7];
				tempProduct *= numbers[i + 8][j - 8];
				tempProduct *= numbers[i + 9][j - 9];
				tempProduct *= numbers[i + 10][j - 10];
				tempProduct *= numbers[i + 11][j - 11];
				tempProduct *= numbers[i + 12][j - 12];
				
				if (tempProduct > product)
				{
					product = tempProduct;
				}
			}
		}
		
		return product;
	}
}
