/**
 * @author Tom
 *
 */
public class KingPiece extends PawnPiece {

	/**
	 * @param chessBoard
	 * @param side
	 */
	public KingPiece(ChessBoard chessBoard, ChessGame.Side side) {
		super(chessBoard, side);
	}

	/** 
	 * @see PawnPiece#getLabel()
	 */
	public String getLabel() {
		return "KG";
	}

	/** 
	 * @see PawnPiece#isRestricted()
	 */
	public boolean isRestricted() {
		return true;
	}

	/** 
	 * @see PawnPiece#leavingBounds(int, int)
	 */
	public boolean leavingBounds(int row, int column) {
		if((column > 2 && column < 6) && (row < 3 || row > 6)) {
			return false;
		}
		else {
			return true;
		}
	}

	/** 
	 * @see PawnPiece#breaksRestrictedRules(int, int)
	 */
	public boolean breaksRestrictedRules(int row, int column) {
		if(((Math.abs(this.getRow() - row) == 1 && this.getColumn() == column) || (Math.abs(this.getColumn() - column) == 1 && this.getRow() == row)) && this.leavingBounds(row, column) == false) {
			return false;
		}
		else {
			return true;
		}
	}
}