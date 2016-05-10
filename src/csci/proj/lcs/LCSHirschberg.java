package csci.proj.lcs;

public class LCSHirschberg implements ILCS{
	public int RECURSIONCOUNT = 0;
	
	private int[] AlgoB(String s1, String s2){
		s1 = padNullString(s1);
		s2 = padNullString(s2);
		int[][] dp = new int[2][s2.length()];		
		for (int i = 1; i<s1.length();i++){
			dp[0] = dp[1].clone();
			for(int j = 1; j<s2.length(); j++){
				if(s1.charAt(i) == s2.charAt(j)){
					dp[1][j] = dp[0][j-1]+ 1;
				}else{
					dp[1][j]= Math.max(dp[1][j-1], dp[0][j]);
				}
			}
		}
		return dp[1];
	}
	
	private String padNullString(String str){
		return " "+str;
	}
	
	private String AlgoC(String s1, String s2){
		int m = s1.length();
		int n = s2.length();
		if (m ==0 || n == 0)
			return "";
		else if (m ==1 && s2.indexOf(s1) != -1)
			return s2.charAt(s2.indexOf(s1))+"";
		else if (n ==1 && s1.indexOf(s2) != -1)
			return s1.charAt(s1.indexOf(s2))+"";
		else{
			RECURSIONCOUNT++;
			int i = m/2;
			String[] splitStrings = splitString(i, s1);
			String a = splitStrings[0];
			String b = splitStrings[1];
			String _b = new StringBuffer(b).reverse().toString();
			String c = new StringBuffer(s2).reverse().toString();
			int[] L1 = AlgoB(a,s2);
			int[] L2 = AlgoB(_b,c);
			int max = 0;
			int k = 0;
			for(int j=0;j<L1.length;j++){
				if(max < L1[j]+L2[n-j]){
					max = L1[j]+L2[n-j];
					k = j;
				}
			}
			String[] splitS2 = splitString(k,s2);
			String d = splitS2[0];
			String e = splitS2[1];
			return AlgoC(a,d) + AlgoC(b,e);
		}
			
	}
	
	private String[] splitString(int i, String str){
		String a = str.substring(0,i);
		String b = str.substring(i);
		return new String[]{a,b};
	}
	
	public String findLCS(String s1, String s2){
		return this.AlgoC(s1, s2);
	}
	
	public static void main(String[] args){
		LCSHirschberg lcsDP = new LCSHirschberg();
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
