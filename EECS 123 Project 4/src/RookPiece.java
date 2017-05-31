/**
 * @author Tom
 *
 */
public class RookPiece extends ChessPiece{

	/**
	 * @param chessBoard
	 * @param side
	 */
	public RookPiece(ChessBoard chessBoard, ChessGame.Side side) {
		super(chessBoard, side);
	}

	/** 
	 * @see ChessPiece#getLabel()
	 */
	public String getLabel() {
		return "R";
	}

	/**
	 * @return
	 */
	public int allowedSkips() {
		return 0;
	}

	/** 
	 * @see ChessPiece#isLegalNonCaptureMove(int, int)
	 */
	public boolean isLegalNonCaptureMove(int row, int column) {
		if(row != this.getRow() && column != this.getColumn()) {
			return false;
		}
		if(row > this.getRow()) {
			int numskipped = 0;

			/**
			 * 
			 */
			for(int i = this.getRow() + 1; i <= row; i++) {
				if(chessBoard.hasPiece(i, column) == true) {
					if(chessBoard.getPiece(i, column).getSide() != this.getSide()) {
						if(i == row && numskipped == this.allowedSkips()) {
							return true;
						}
						else if(i < row) {
							if(numskipped < this.allowedSkips()){
								numskipped += 1;
							}
							else {
								return false;
							}
						}
					}
					else {
						return false;
					}
				}
				else if(numskipped == 0 && i == row) {
					return true;
				}
			}
			return false;
		}
		else if(row < this.getRow()) {
			int numskipped = 0;

			/**
			 * 
			 */
			for(int i = this.getRow() - 1; i >= row; i--) {
				if(chessBoard.hasPiece(i, column) == true) {
					if(chessBoard.getPiece(i, column).getSide() != this.getSide()) {
						if(i == row && numskipped == this.allowedSkips()) {
							return true;
						}
						else if(i > row) {
							if(numskipped < this.allowedSkips()){
								numskipped += 1;
							}
							else {
								return false;
							}
						}
					}
					else {
						return false;
					}
				}
				else if(numskipped == 0 && i == row) {
					return true;
				}
			}
			return false;
		}
		else if(column > this.getColumn()) {
			int numskipped = 0;

			/**
			 * 
			 */
			for(int i = this.getColumn() + 1; i <= column; i++) {
				if(chessBoard.hasPiece(row, i) == true) {
					if(chessBoard.getPiece(row, i).getSide() != this.getSide()) {
						if(i == column && numskipped == this.allowedSkips()) {
							return true;
						}
						else if(i < column) {
							if(numskipped < this.allowedSkips()){
								numskipped += 1;
							}
							else {
								return false;
							}
						}
					}
					else {
						return false;
					}
				}
				else if(numskipped == 0 && i == column) {
					return true;
				}
			}
			return false;
		}
		else if(column < this.getColumn()) {
			int numskipped = 0;

			/**
			 * 
			 */
			for(int i = this.getColumn() - 1; i >= column; i--) {
				if(chessBoard.hasPiece(row, i) == true) {
					if(chessBoard.getPiece(row, i).getSide() != this.getSide()) {
						if(i == column && numskipped == this.allowedSkips()) {
							return true;
						}
						else if(i > column) {
							if(numskipped < this.allowedSkips()){
								numskipped += 1;
							}
							else {
								return false;
							}
						}
					}
					else {
						return false;
					}
				}
				else if(numskipped == 0 && i == column) {
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
		if(this.isLegalNonCaptureMove(row, column) == true) {
			return true;
		}
		else {
			return false;
		}
	}
}
