
/**
 * @author Thomas Lerner
 *
 */
public class HuffmanNode {
	
	public Character inChar;
	
	public int frequency;
	
	public HuffmanNode left;
	
	public HuffmanNode right;
	
	public String encoding;
	
	public HuffmanNode(Character inChar, int frequency) {
		this.inChar = inChar;
		this.frequency = frequency;
		this.left = null;
		this.right = null;
		this.encoding = "";
	}
}
