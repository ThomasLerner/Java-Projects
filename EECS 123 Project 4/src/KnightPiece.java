/**
 * @author Tom
 *
 */
public class KnightPiece extends ChessPiece{

	/**
	 * @param chessBoard
	 * @param side
	 */
	public KnightPiece(ChessBoard chessBoard, ChessGame.Side side) {
		super(chessBoard, side);
	}

	/** 
	 * @see ChessPiece#getLabel()
	 */
	public String getLabel() {
		return "KT";
	}

	/**
	 * @return
	 */
	public boolean isRestricted() {
		return false;
	}

	/**
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean leavingBounds(int row, int column) {
		return false;
	}

	/** 
	 * @see ChessPiece#isLegalNonCaptureMove(int, int)
	 */
	public boolean isLegalNonCaptureMove(int row, int column) {
		if((Math.abs(this.getColumn() - column) == 1 && Math.abs(this.getRow() - row) == 2) || (Math.abs(this.getColumn() - column) == 2 && Math.abs(this.getRow() - row) == 1)) {
			if(Math.abs(this.getColumn() - column) == 1 && this.getRow() > row) {
				if(chessBoard.hasPiece(this.getRow() - 1, this.getColumn()) == true) {
					return false;
				}
				else {
					return true;
				}
			}
			else if(Math.abs(this.getColumn() - column) == 1 && this.getRow() < row) {
				if(chessBoard.hasPiece(this.getRow() + 1, this.getColumn()) == true) {
					return false;
				}
				else {
					return true;
				}
			}
			else if(Math.abs(this.getRow() - row) == 1 && this.getColumn() < column) {
				if(chessBoard.hasPiece(this.getRow(), this.getColumn() + 1) == true) {
					return false;
				}
				else {
					return true;
				}
			}
			else if(Math.abs(this.getRow() - row) == 1 && this.getColumn() > column) {
				if(chessBoard.hasPiece(this.getRow(), this.getColumn() - 1) == true) {
					return false;
				}
				else {
					return true;
				}
			}
			return false;
		}
		return false;
	}

	/** 
	 * @see ChessPiece#isLegalCaptureMove(int, int)
	 */
	public boolean isLegalCaptureMove(int row, int column) {
		return isLegalNonCaptureMove(row, column);
	}
}
