package csci.proj.lcs;

public class LCSDynamicP implements ILCS{
	
	
	private String padNullString(String str){
		return " "+str;
	}
	
	private int[][] makeDp (String s1, String s2){
		int[][] dp = new int[s1.length()][s2.length()];		
		for (int i = 1; i<s1.length();i++){
			for(int j = 1; j<s2.length(); j++){
				if(s1.charAt(i) == s2.charAt(j)){
					dp[i][j] = dp[i-1][j-1]+ 1;
				}else{
					dp[i][j]= Math.max(dp[i][j-1], dp[i-1][j]);
				}
			}
		}
		return dp;
	}
	private String getLCS (int[][] dp, String s1, String s2, int i, int j){
		if (i ==0 || j == 0)
			return "";
		if (s1.charAt(i) == s2.charAt(j))
			return s1.charAt(i) + getLCS(dp, s1, s2, i-1, j-1);
		else if (dp[i-1][j]>= dp[i][j-1])
			return getLCS(dp, s1, s2, i-1, j);
		else 
			return getLCS(dp, s1, s2, i, j-1);
	}
	
	public String findLCS(String s1, String s2){
		s1 = padNullString(s1);
		s2 = padNullString(s2);
		int[][] dp = makeDp(s1,s2);
		if (dp.length == 0)
			return "";
		String result =  getLCS(dp, s1,s2, dp.length-1, dp[0].length-1);
		return new StringBuilder(result).reverse().toString();
	}
	
	public static void main(String[] args){
		LCSDynamicP lcsDP = new LCSDynamicP();
		String s1 = "ABCBDA";
		String s2 = "BDCABA";
		System.out.println("String 1: " + s1);
		System.out.println("String 2: " + s2);
		String result = (lcsDP.findLCS(s1, s2));
		System.out.println("LCS "+ result);
		if(result.equals("BCBA") || result.equals("BACB"))
			System.out.println("Test Passed");
		else
			System.err.println("Test Failed");
	}
}
