package csci.proj.lcs;

public class LCSMemoization implements ILCS {
	private final String DEFAULT = null;
	public int RECURSIONCOUNT = 0;
	/**
	 * 
	 * @param s1
	 * @param s2
	 * @return lcs
	 * 
	 * This is memoized version of Naive algorithm to find LCS
	 * this works better than Naive as it involves less computations 
	 * as compared to the naive 
	 */
	public String findLCS(String s1, String s2){
		RECURSIONCOUNT = 0;
		int i = s1.length() -1;
		int j = s2.length() -1;
		String[][] memo = new String[i+1][j+1];
		for (int k = 0; k < memo.length; k++) {
			for (int k2 = 0; k2 < memo.length; k2++) {
				memo[k][k2] = DEFAULT;
			}
		}
		String result =  this.getLCS(s1, s2, i, j, memo);
		return new StringBuilder(result).reverse().toString();
	}
	
	private String getLCS(String s1, String s2, int i, int j, String[][] memo){
		if (i == -1 || j == -1)
			return "";
		if( memo[i][j] == DEFAULT){
			RECURSIONCOUNT++;
			if (s1.charAt(i) == s2.charAt(j)){
				memo[i][j] = s1.charAt(i)+"";
				return s1.charAt(i) + getLCS(s1, s2, i-1, j-1, memo);
			}
			String tmp1 = getLCS(s1, s2, i-1, j, memo);
			String tmp2 = getLCS(s1, s2, i, j-1, memo);
			if (tmp1.length() > tmp2.length()){
				return tmp1;
			}
			return tmp2;
		}else{
			return memo[i][j];
		}
		
	}
	
	public static void main(String[] args){
		LCSMemoization lcsDP = new LCSMemoization();
		String s1 = "ABCBDA";
		String s2 = "BDCABA";
		System.out.println("String 1: " + s1);
		System.out.println("String 2: " + s2);
		String result = (lcsDP.findLCS(s1, s2));
		System.out.println("Recursion Count " +lcsDP.RECURSIONCOUNT);
		System.out.println("LCS "+ result);
		if(result.equals("BCBA") || result.equals("BACB"))
			System.out.println("Test Passed");
		else
			System.err.println("Test Failed");
	}
}
