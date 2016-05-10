package csci.proj.lcs;

public class LCSNaive implements ILCS {
	
	public int RECURSIVECALLS = 0;
	
	/**
	 * @param s1
	 * @param s2
	 * @return lcs
	 * 
	 * This is Naive algorithm to find LCS of two Strings s1 and s2
	 */	
	public String findLCS(String s1, String s2){
		int i = s1.length() -1;
		int j = s2.length() -1;
		RECURSIVECALLS = 0;
		String result =  this.bruteForce(s1, s2, i, j);
		return new StringBuilder(result).reverse().toString();
	}
	
	private String bruteForce(String s1, String s2, int i, int j){
		if (i == -1 || j == -1)
			return "";
		RECURSIVECALLS++;
		if (s1.charAt(i) == s2.charAt(j)){
			return s1.charAt(i) + bruteForce(s1, s2, i-1, j-1);
		}
		String tmp1 = bruteForce(s1, s2, i-1, j);
		String tmp2 = bruteForce(s1, s2, i, j-1);
		if (tmp1.length() > tmp2.length()){
			return tmp1;
		}
		return tmp2;
	}
}
