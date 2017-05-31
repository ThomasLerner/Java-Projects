/**
 * @author Tom
 *
 */
public class PawnPiece extends ChessPiece {
		
	/**
	 * @param chessBoard
	 * @param side
	 */
	public PawnPiece(ChessBoard chessBoard, ChessGame.Side side) {
		super(chessBoard, side);
	}
	
	/** 
	 * @see ChessPiece#getLabel()
	 */
	public String getLabel() {
		return "P";
	}
	
	/**
	 * @return
	 */
	public boolean isRestricted() {
		if((side == ChessGame.Side.NORTH && this.getRow() > 4) || (side == ChessGame.Side.SOUTH && this.getRow() < 5)) {
			return false;
		}
		else {
			return true;
		}
	}
	
	/**
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean leavingBounds(int row, int column) {
		if(side == ChessGame.Side.SOUTH && this.getRow() - 1 == row) {
			return false;
		}
		else if(side == ChessGame.Side.NORTH && this.getRow() + 1 == row) {
			return false;
		}
		else {
			return true;
		}
	}
	
	/**
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean breaksRestrictedRules(int row, int column) {
		if(this.leavingBounds(row, column) == false && this.getColumn() == column) {
			return false;
		}
		else {
			return true;
		}
	}
	
	/**
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean breaksUnrestrictedRules(int row, int column) {
		if(this.breaksRestrictedRules(row, column) == false || (Math.abs(this.getColumn() - column) == 1 && this.getRow() == row)) {
			return false;
		}
		else {
			return true;
		}
	}

	/** 
	 * @see ChessPiece#isLegalNonCaptureMove(int, int)
	 */
	public boolean isLegalNonCaptureMove(int row, int column) {
		if(this.isRestricted() == false && this.breaksUnrestrictedRules(row, column) == false) {
			return true;
		}
		else if(this.isRestricted() == true && this.breaksRestrictedRules(row, column) == false) {
			return true;
		}
		else {
			return false;
		}
	}

	/** 
	 * @see ChessPiece#isLegalCaptureMove(int, int)
	 */
	public boolean isLegalCaptureMove(int row, int column) {
		return this.isLegalNonCaptureMove(row, column);
	}
	
	/** 
	 * @see ChessPiece#moveDone()
	 */
	public void moveDone() {
	}

}