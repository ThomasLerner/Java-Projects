/**
 * @author Tom
 *
 */
public class GuardPiece extends KingPiece{
	
	/**
	 * @param chessBoard
	 * @param side
	 */
	public GuardPiece(ChessBoard chessBoard, ChessGame.Side side) {
		super(chessBoard, side);
	}
	
	/** 
	 * @see KingPiece#getLabel()
	 */
	public String getLabel() {
		return "G";
	}

	/** 
	 * @see KingPiece#breaksRestrictedRules(int, int)
	 */
	public boolean breaksRestrictedRules(int row, int column) {
		if((Math.abs(this.getRow() - row) == numJumps() && Math.abs(this.getColumn() - column) == numJumps()) && this.leavingBounds(row, column) == false) {
			return false;
		}
		else {
			return true;
		}
	}

	/**
	 * @return
	 */
	public int numJumps() {
		return 1;
	}
}