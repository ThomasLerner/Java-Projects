/**
 * @author Tom
 *
 */
public interface ChessGame {
	
	/**
	 * @param piece
	 * @return
	 */
	public boolean legalPieceToPlay(ChessPiece piece);

	/**
	 * @param piece
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean makeMove(ChessPiece piece, int row, int column);
	
	/**
	 * 
	 * @author Tom
	 *
	 */
	public enum Side {NORTH, SOUTH};
}