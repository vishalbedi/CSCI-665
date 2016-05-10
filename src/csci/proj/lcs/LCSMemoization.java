package csci.proj.lcs;

public class LCSMemoization implements ILCS {
	private final int DEFAULT = -100;
	private int RECURSIONCOUNT = 0;
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
		int i = s1.length();
		int j = s2.length();
		int[][] memo = new int[i][j];
		for (int k = 0; k < i; k++) {
			for (int k2 = 0; k2 <j; k2++) {
				memo[k][k2] = DEFAULT;
			}
		}
		int lcsLength = this.getLCS(s1, s2, i-1, j-1, memo);
		if (memo.length == 0)
			return "";
		String result =  getLCS(memo, s1,s2, i-1, j-1);
		return new StringBuilder(result).reverse().toString();
	}
	
	private int getLCS(String s1, String s2, int i, int j, int[][] memo){
		if (i == -1 || j == -1)
			return 0;
		if( memo[i][j] == DEFAULT){
			memo[i][j] = 0;
			RECURSIONCOUNT++;
			if (s1.charAt(i) == s2.charAt(j)){
				memo[i][j] = 1 + getLCS(s1, s2, i-1, j-1, memo);
			}else{
				int tmp1 = getLCS(s1, s2, i-1, j, memo);
				int tmp2 = getLCS(s1, s2, i, j-1, memo);
				memo[i][j] = Math.max(tmp1, tmp2);
			}
		}
		return memo[i][j];
		
	}
	
	private String getLCS (int[][] dp, String s1, String s2, int i, int j){
		if (i <0 || j < 0)
			return "";
		if (s1.charAt(i) == s2.charAt(j))
			return s1.charAt(i) + getLCS(dp, s1, s2, i-1, j-1);
		else if(i>0 && j>0){
			if (dp[i-1][j]>= dp[i][j-1])
				return getLCS(dp, s1, s2, i-1, j);
			else 
				return getLCS(dp, s1, s2, i, j-1);
		}
		return "";
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

	@Override
	public int getRecursionCount() {
		return RECURSIONCOUNT;
	}
}
