
/**
 * @author Thomas Lerner
 *
 */
public class designator {
	public static void main(String[] args) {
		String s = args[0];
		s = s.toUpperCase();
		for(int x = 0; x < s.length(); x++) {
			System.out.print(s.charAt(x) + " ");
		}
		System.out.println("");
		for(int y = 1; y < s.length(); y++) {
			System.out.println(s.charAt(y));
		}
	}
}
