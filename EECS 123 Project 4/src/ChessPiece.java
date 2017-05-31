import javax.swing.Icon;
import java.awt.Color;

/**
 * @author Tom
 *
 */
public class ChessPiece {

	/**
	 * 
	 */
	ChessBoard chessBoard;
	
	/**
	 * 
	 */
	ChessGame.Side side;

	/**
	 * @param chessBoard
	 * @param side
	 */
	public ChessPiece(ChessBoard chessBoard, ChessGame.Side side) {
		this.chessBoard = chessBoard;
		this.side = side;
	}

	/**
	 * @return
	 */
	public ChessBoard getChessBoard() {
		return chessBoard;
	}

	/**
	 * @return
	 */
	public Color getColor() {
		if(side == ChessGame.Side.NORTH) {
			return Color.BLACK;
		}
		else {
			return Color.RED.darker();
		}
	}

	/**
	 * @return
	 */
	public String getLabel() {
		return "";
	}

	/**
	 * @return
	 */
	public Icon getIcon() {
		return null;
	}

	/**
	 * @return
	 */
	public ChessGame.Side getSide() {
		return side;
	}

	/**
	 * @return
	 */
	public int getRow() {
		return chessBoard.getPosition(this)[0];
	}

	/**
	 * @return
	 */
	public int getColumn() {
		return chessBoard.getPosition(this)[1];
	}

	/**
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean isLegalMove(int row, int column) {
		if(chessBoard.hasPiece(row, column)) {
			if(chessBoard.getPiece(row, column).getSide() != side) {
				return this.isLegalCaptureMove(row, column);
			}
			else {
				return false;
			}
		}
		else {
			return this.isLegalNonCaptureMove(row, column);
		}
	}

	/**
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean isLegalNonCaptureMove(int row, int column) {
		return false;
	}

	/**
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean isLegalCaptureMove(int row, int column) {
		return false;
	}

	/**
	 * 
	 */
	public void moveDone() {
	}
	
}
