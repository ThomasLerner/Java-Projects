/**
 * @author Tom
 *
 */
public class ElephantPiece extends GuardPiece{

	/**
	 * @param chessBoard
	 * @param side
	 */
	public ElephantPiece(ChessBoard chessBoard, ChessGame.Side side) {
		super(chessBoard, side);
	}

	/** 
	 * @see GuardPiece#getLabel()
	 */
	public String getLabel() {
		return "E";
	}

	/** 
	 * @see KingPiece#isRestricted()
	 */
	public boolean isRestricted() {
		return false;
	}

	/** 
	 * @see GuardPiece#numJumps()
	 */
	public int numJumps() {
		return 2;
	}

	/**
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean leavingBounds(int row, int column) {
		if((side == ChessGame.Side.NORTH && row < 5) || (side == ChessGame.Side.SOUTH && row > 4)) {
			return false;
		}
		else {
			return true;
		}
	}

	/** 
	 * @see PawnPiece#breaksUnrestrictedRules(int, int)
	 */
	@Override
	public boolean breaksUnrestrictedRules(int row, int column) {
		if(this.breaksRestrictedRules(row, column) == false && this.leavingBounds(row, column) == false) {
			if(row - this.getRow() == 2) {
				System.out.println("first if");
				if(column - this.getColumn() == 2 && chessBoard.hasPiece(row - 1, column - 1) == false) {
					return false;
				}
				else if(column - this.getColumn() == -2 && chessBoard.hasPiece(row - 1, column + 1) == false) {
					return false;
				}
				else {
					return true;
				}
			}
			else if(row - this.getRow() == -2) {
				System.out.println("second if");
				if(column - this.getColumn() == 2 && chessBoard.hasPiece(row + 1, column - 1) == false) {
					return false;
				}
				else if(column - this.getColumn() == -2 && chessBoard.hasPiece(row + 1, column + 1) == false) {
					return false;
				}
				else {
					return true;
				}
			}
			else {
				return true;
			}
		}
		else {
			return true;
		}
	}
}