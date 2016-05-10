package csci.proj.lcs;

import java.util.Random;

public class ExperimentFramework {
	/**
	 *  Values to represent experiment Type
	 */
	private final int NAIVE = 0;
	private final int MEMOIZATION = 1;
	private final int DYNAMICPROG = 2;
	private final int HIRSCHBERG = 3;
	
	private final int BINARY = 0;
	private final int LETTERS = 1;
	
	private final long SEED = 1234;
	
	private int startLength = 0;
	private int maxLength = 0;
	private int interval = 0;
	private String filename;
	
	private ILCS algo;
	
	private char[] alphabet;
	
	private StringGenerator strGen = new StringGenerator();
	
	private Random generator = new Random(SEED);
	
	private FileIO io = new FileIO();
	
	private String[] HEADERS = new String[]{"INPUT LENGTH",
			"LCS LENGTH", "RECURSIVE CALLS", "TIME"};
	
	
	private void setDefaults(int opt){
		switch (opt) {
		case NAIVE:
			startLength = 8;
			maxLength = 22;
			interval = 2;
			filename = "naive.txt";
			break;
		case MEMOIZATION:
			startLength = 10;
			maxLength = 3700;
			interval = 100;
			filename = "memoization.txt";
			break;
		case DYNAMICPROG:
			startLength = 10;
			maxLength = 10000;
			interval = 100;
			filename = "dynamicprog.txt";
			break;
		case HIRSCHBERG:
			startLength = 10;
			maxLength = 40000;
			interval = 100;
			filename = "hirschberg.txt";
			break;
		default:
			startLength = 8;
			maxLength = 22;
			interval = 2;
			filename = "naive.txt";
			break;
		}
		io.init(filename, HEADERS);
	}
	
	private void setUpAlgo(int opt){
		switch (opt) {
		case NAIVE:
			algo = new LCSNaive();
			break;
		case MEMOIZATION:
			algo = new LCSMemoization();
			break;
		case DYNAMICPROG:
			algo = new LCSDynamicP();
			break;
		case HIRSCHBERG:
			algo = new LCSHirschberg();
			break;
		default:
			algo = new LCSNaive();
			break;
		}
	}
	
	private void setAlphabet(int opt){
		if (opt == BINARY){
			alphabet = new char[]{'0','1'};
		}else{
			alphabet = new char[]{'A', 'C', 'G','T'};
		}
	}
	
	private void testRun(){
		for (int i = startLength; i < maxLength; i += interval) {
			int len1 =  i + generator.nextInt(interval);
			int len2 =  i + generator.nextInt(interval);
			String s1 = strGen.createString(alphabet, len1, SEED);
			String s2 = strGen.createString(alphabet, len2, SEED);
			int maxLen = len1 > len2 ? len1 : len2;
			long startTime = System.currentTimeMillis();
			String result = algo.findLCS(s1, s2);
			long endTime = System.currentTimeMillis();
			int recursionCount = algo.getRecursionCount();
			io.write(maxLen+"",result.length()+"",recursionCount+"", (endTime-startTime)+"");
		}
		io.save();
	}
	
	public void executeAll(int alphabetOption){
		this.setAlphabet(alphabetOption);
		for (int i = 0; i <= HIRSCHBERG; i++) {
			this.setDefaults(i);
			this.setUpAlgo(i);
			this.testRun();
		}
	}
}
