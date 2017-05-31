/**
 * 
 * @author Tom
 *
 */
public class CannonPiece extends RookPiece{

	/**
	 * @param chessBoard
	 * @param side
	 */
	public CannonPiece(ChessBoard chessBoard, ChessGame.Side side) {
		super(chessBoard, side);
	}
	
	/** 
	 * @see RookPiece#getLabel()
	 */
	public String getLabel() {
		return "C";
	}
	
	/** 
	 * @see RookPiece#allowedSkips()
	 */
	public int allowedSkips() {
		return 1;
	}
}
