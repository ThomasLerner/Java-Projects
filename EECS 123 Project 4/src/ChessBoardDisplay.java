import javax.swing.JButton;

/**
 * @author Tom
 *
 */
public interface ChessBoardDisplay {

	/**
	 * @param square
	 * @param row
	 * @param column
	 */
	public void displayEmptySquare(JButton square, int row, int column);

	/**
	 * @param square
	 * @param row
	 * @param column
	 * @param piece
	 */
	public void displayFilledSquare(JButton square, int row, int column, ChessPiece piece);

	/**
	 * @param isHighlited
	 * @param square
	 * @param row
	 * @param column
	 * @param piece
	 */
	public void highlightSquare(boolean isHighlited, JButton square, int row, int column, ChessPiece piece);
}
