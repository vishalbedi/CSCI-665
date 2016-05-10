package csci.proj.lcs;

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
	
	private int startLength = 0;
	private int maxLength = 0;
	private int interval = 0;
	private String filename;
	
	private ILCS algo;
	
	private char[] alphabet;
	
	
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
			maxLength = 10000;
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
	
	
	
	
	
	
}
