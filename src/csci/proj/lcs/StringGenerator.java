package csci.proj.lcs;

import java.util.Random;

/**
 * 
 * @author Vishal
 *
 */
public class StringGenerator {
	
	/**
	 * 
	 * @param alphabet
	 * @param size
	 * @param seed
	 * @return randomString
	 * 
	 * The Method generates random Strings based on the alphabet 
	 * provided and the size.
	 * The randomness of the string is gaussian 
	 * 
	 */
	public String createString(char[] alphabet, int size, long seed){
		Random generator = new Random(seed);
		int alphabetSize = alphabet.length;
		StringBuilder result = new StringBuilder();
		for (int i = 0; i< size; i++){
			int j = generator.nextInt(alphabetSize);
			result.append(alphabet[j]);
		}
		return result.toString();
	}
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		StringGenerator sg = new StringGenerator();
		char[] s = {'A', 'C', 'G','T'};
		String st = sg.createString(s, 10, 10);
		System.out.println(st);
	}
	
	
}
