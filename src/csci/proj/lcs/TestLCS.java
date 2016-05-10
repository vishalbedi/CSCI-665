package csci.proj.lcs;

import java.util.Random;

public class TestLCS {
	public static void main(String[] args){
		TestLCS test = new TestLCS();
		System.out.println("Starting Tests");
		test.testEmpty();
		test.testOneEmpty();
		test.testSmallSize();
		test.testRandomString();
		test.testSameString();
		test.testForLargeData();
	}
	private boolean testEmpty(){
		System.out.println("Test : Empty String : Expected LCS empty String");
		String s1 = "";
		String s2 = "";
		LCSNaive lcs = new LCSNaive();
		System.out.println("String 1: " + s1);
		System.out.println("String 2: " + s2);
		String result = (lcs.findLCS(s1, s2));
		System.out.println("LCS "+ result);
		if(result.isEmpty())
			System.out.println("Test Passed");
		else
			System.err.println("Test Failed");
		return result.isEmpty();
	}
	
	private boolean testOneEmpty (){
		System.out.println("Test : One String Empty String : Expected LCS empty String");
		long seed = 555;
		StringGenerator sg = new StringGenerator();
		char[] alphabet = {'A', 'C', 'G','T'};
		String s1 =sg.createString(alphabet, 5, seed);
		String s2 = "";
		LCSNaive lcs = new LCSNaive();
		System.out.println("String 1: " + s1);
		System.out.println("String 2: " + s2);
		String result = (lcs.findLCS(s1, s2));
		System.out.println("LCS "+ result);
		if(result.isEmpty())
			System.out.println("Test Passed");
		else
			System.err.println("Test Failed");
		return result.isEmpty();
	}
	
	private boolean testSmallSize(){
		System.out.println("Test : Small String : Expected LCS BCBA or BACB");
		String s1 = "ABCBDA";
		String s2 = "BDCABA";
		LCSNaive lcs = new LCSNaive();
		System.out.println("String 1: " + s1);
		System.out.println("String 2: " + s2);
		String result = (lcs.findLCS(s1, s2));
		System.out.println("LCS "+ result);
		if(result.equals("BCBA") || result.equals("BACB"))
			System.out.println("Test Passed");
		else
			System.err.println("Test Failed");
		return result.equals("BCBA") || result.equals("BACB");
	}
	
	private void testRandomString(){
		System.out.println("Test : Random String : Expected LCS not known");
		StringGenerator sg = new StringGenerator();
		long seed = 666;
		char[] alphabet = {'A', 'C', 'G','T'};
		String s1 = sg.createString(alphabet, 5, seed);
		String s2 = sg.createString(alphabet, 6, seed);
		LCSNaive lcs = new LCSNaive();
		System.out.println("String 1: " + s1);
		System.out.println("String 2: " + s2);
		String result = (lcs.findLCS(s1, s2));
		System.out.println("LCS "+ result);
	}
	private boolean testSameString(){
		System.out.println("Test: S1 == S2: Expected LCS S1");
		StringGenerator sg = new StringGenerator();
		char[] alphabet = {'A', 'C', 'G','T'};
		long seed = 123;
		String s1 = sg.createString(alphabet, 5, seed);
		String s2 = s1;
		LCSNaive lcs = new LCSNaive();
		System.out.println("String 1: " + s1);
		System.out.println("String 2: " + s2);
		String result = (lcs.findLCS(s1, s2));
		System.out.println("LCS "+ result);
		if(result.equals(s1))
			System.out.println("Test Passed");
		else
			System.err.println("Test Failed");
		return result.equals(s1);
	}
	
	private void testForLargeData(){
		System.out.println("Random Test Cases for String Length 10-25 for 1000 times");
		
		int[] averages = new int[10];
	
		for (int j = 0; j< 10; j++){
			long startTime = System.currentTimeMillis();
			int[] lc = testBatch(j);
	 		long endTime = System.currentTimeMillis();
	 		int sum = 0;
	 		for (int i : lc) {
				sum += i;
			}
	 		averages[j] = sum/100;
	 		System.out.println("Time for "+j+"th batch " + (endTime-startTime) + " ms");
		}
		
	}
	
	private int[] testBatch(int minLenFactor){
		long seed = 12015;
		LCSNaive lcs = new LCSNaive();
		StringGenerator sg = new StringGenerator();
		char[] alphabet = {'A', 'C', 'G','T'};
		Random generator = new Random(seed);
		int[] lc = new int[100];
 		for (int i = 0; i < 100; i++){
			int len1 = 4 + minLenFactor + generator.nextInt(10);
			int len2 = 4 + minLenFactor + generator.nextInt(10);
			String s1 = sg.createString(alphabet, len1, seed);
			String s2 = sg.createString(alphabet, len2, seed);
			lc[i] =  lcs.findLCS(s1, s2).length();			
		}
 		return lc;
	}
}
