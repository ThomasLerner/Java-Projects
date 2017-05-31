import javax.swing.JButton;

/**
 * @author Tom
 *
 */
public class XiangqiDisplay implements ChessBoardDisplay {
	
	/** 
	 * @see ChessBoardDisplay#displayEmptySquare(javax.swing.JButton, int, int)
	 */
	@Override
	public void displayEmptySquare(JButton square, int row, int column) {
		square.setBackground(java.awt.Color.gray);
		square.setText(null);
		square.setIcon(null);
		
	}

	/** 
	 * @see ChessBoardDisplay#displayFilledSquare(javax.swing.JButton, int, int, ChessPiece)
	 */
	@Override
	public void displayFilledSquare(JButton square, int row, int column, ChessPiece piece) {
		square.setBackground(piece.getColor());
		square.setText(piece.getLabel());
		square.setIcon(piece.getIcon());
	}

	/** 
	 * @see ChessBoardDisplay#highlightSquare(boolean, javax.swing.JButton, int, int, ChessPiece)
	 */
	@Override
	public void highlightSquare(boolean isHighlighted, JButton square, int row, int column, ChessPiece piece) {
		if(isHighlighted == true) {
			square.setBackground(java.awt.Color.yellow);
		}
		else if(piece == null){
			this.displayEmptySquare(square, row, column);
		}
		else {
			this.displayFilledSquare(square, row, column, piece);
		}
	}
	
}
