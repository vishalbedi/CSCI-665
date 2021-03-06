package csci.proj.lcs;

import java.util.Random;

public class ExperimentFramework {
	/**
	 *  Values to represent experiment Type
	 */
	//Defaults for Algorithms
	private final int NAIVE = 0;
	private final int MEMOIZATION = 1;
	private final int DYNAMICPROG = 2;
	private final int HIRSCHBERG = 3;
	
	private final int BINARY = 0;
	private final int LETTERS = 1;
	//Seed value to random generators so the results are reproducible
	private final long SEED1 = 1234;
	private final long SEED2 = 888;
	
	private int startLength = 0;
	private int maxLength = 0;
	private int interval = 0;
	private String filename;
	// Algo object
	private ILCS algo;
	
	private char[] alphabet;
	
	private StringGenerator strGen = new StringGenerator();
	
	private Random generator = new Random(SEED1);
	// Helper class to save data to file
	private FileIO io = new FileIO();
	
	private String[] HEADERS = new String[]{"INPUT LENGTH",
			"LCS LENGTH", "RECURSIVE CALLS", "TIME"};
	
	// Defaults for algos
	private void setDefaults(int opt){
		switch (opt) {
		case NAIVE:
			startLength = 4;
			maxLength = 22;
			interval = 2;
			filename = "naive.txt";
			break;
		case MEMOIZATION:
			startLength = 10;
			maxLength = 7800;
			interval = 300;
			filename = "memoization.txt";
			break;
		case DYNAMICPROG:
			startLength = 10;
			maxLength = 15000;
			interval = 500;
			filename = "dynamicprog.txt";
			break;
		case HIRSCHBERG:
			startLength = 10;
			maxLength = 100000;
			interval = 3000;
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
	// Factory pattern to get desired algo
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
	// Alphabet for the experiment
	private void setAlphabet(int opt){
		if (opt == BINARY){
			alphabet = new char[]{'0','1'};
		}else{
			alphabet = new char[]{'A', 'C', 'G','T'};
		}
	}
	
	//Test suite to run time profile of all the algorithms
	private void testRun(){
		for (int i = startLength; i < maxLength; i += interval) {
			int len1 =  i + generator.nextInt(interval);
			int len2 =  i + generator.nextInt(interval);
			String s1 = strGen.createString(alphabet, len1, SEED1);
			String s2 = strGen.createString(alphabet, len2, SEED2);
			int maxLen = len1 > len2 ? len1 : len2;
			long startTime = System.currentTimeMillis();
			String result = algo.findLCS(s1, s2);
			long endTime = System.currentTimeMillis();
			int recursionCount = algo.getRecursionCount();
			io.write(maxLen+"",result.length()+"",recursionCount+"", (endTime-startTime)+"");
		}
		io.save();
	}
	// Executes all algos and saves data to a file
	public void executeAll(int alphabetOption){
		this.setAlphabet(alphabetOption);
			for (int i = 0; i <= HIRSCHBERG; i++) {
				execute(i);
		}
	}
	
	public void executeNaive(int alphabetOption){
		this.setAlphabet(alphabetOption);
		execute(NAIVE);
	}
	
	public void executeMemoization(int alphabetOption){
		this.setAlphabet(alphabetOption);
		execute(MEMOIZATION);
	}
	
	public void executeHirchberg(int alphabetOption){
		this.setAlphabet(alphabetOption);
		execute(HIRSCHBERG);
	}
	
	public void executeDP(int alphabetOption){
		this.setAlphabet(alphabetOption);
		execute(DYNAMICPROG);
	}
	public void execute(int Algo){
		this.setDefaults(Algo);
		this.setUpAlgo(Algo);
		this.testRun();
	}
}
