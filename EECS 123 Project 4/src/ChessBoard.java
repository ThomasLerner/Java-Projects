import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Tom
 *
 */
public class ChessBoard implements ActionListener{

	/**
	 * 
	 */
	private JFrame frame;
	
	/**
	 * 
	 */
	private JPanel board;
	
	/**
	 * 
	 */
	private JButton[][] squareGrid;
	
	/**
	 * 
	 */
	private ChessPiece[][] pieceGrid;
	
	/**
	 * 
	 */
	private boolean isClicked = false;
	
	/**
	 * 
	 */
	private int[] clickedPosition = new int[2];
	
	/**
	 * 
	 */
	private ChessBoardDisplay chessDisplay;
	
	/**
	 * 
	 */
	private ChessGame chessVersion;

	/**
	 * @param rows
	 * @param columns
	 * @param chessDisplay
	 * @param chessVersion
	 */
	public ChessBoard(int rows, int columns , ChessBoardDisplay chessDisplay, ChessGame chessVersion) {
		
		this.chessVersion = chessVersion;
		this.chessDisplay = chessDisplay;
		squareGrid = new JButton[rows][columns];
		pieceGrid = new ChessPiece[rows][columns];
		frame = new JFrame();
		board = new JPanel(new GridLayout(rows, columns));
		
		/**
		 * 
		 */
		frame.getContentPane().add(board, "Center");
		
		/**
		 * 
		 */
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				squareGrid[i][j] = new JButton();
				squareGrid[i][j].addActionListener(this);
				chessDisplay.displayEmptySquare(squareGrid[i][j], i, j);
				board.add(squareGrid[i][j]);
				squareGrid[i][j].setPreferredSize(new Dimension(50, 50));
			}
		}
		
		/**
		 * 
		 */
		frame.setVisible(true);
		
		/**
		 * 
		 */
		frame.pack();
	}
		
	/**
	 * @param piece
	 * @param row
	 * @param column
	 */
	public void addPiece(ChessPiece piece, int row, int column) {
		pieceGrid[row][column] = piece;
		chessDisplay.displayFilledSquare(squareGrid[row][column], row, column, piece);
	}

	/**
	 * @param row
	 * @param column
	 * @return
	 */
	public ChessPiece removePiece(int row, int column) {
		ChessPiece removedPiece = pieceGrid[row][column];
		pieceGrid[row][column] = null;
		chessDisplay.displayEmptySquare(squareGrid[row][column], row, column);
		return removedPiece;
	}

	/**
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean hasPiece(int row, int column) {
		if (pieceGrid[row][column] == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * @param row
	 * @param column
	 * @return
	 */
	public ChessPiece getPiece(int row, int column) {
		if(this.hasPiece(row, column) == true) {
			return pieceGrid[row][column];
		}
		else {
			throw new NullPointerException("piece expected");
		}
	}

	/** 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clickedButton = (JButton)e.getSource();
		for(int i = 0; i < squareGrid.length; i++) {
			for(int j = 0; j < squareGrid[i].length; j++) {
				if(squareGrid[i][j] == clickedButton) {
					if(this.hasPiece(i, j) == true && isClicked == false) {
						if(chessVersion.legalPieceToPlay(pieceGrid[i][j]) == true) {
							chessDisplay.highlightSquare(true, squareGrid[i][j], i, j, this.getPiece(i, j));
							clickedPosition[0] = i;
							clickedPosition[1] = j;
							isClicked = true;
						}
					}
					else if(isClicked == true) {
						chessDisplay.highlightSquare(false, squareGrid[clickedPosition[0]][clickedPosition[1]], clickedPosition[0], clickedPosition[1], this.getPiece(clickedPosition[0], clickedPosition[1]));
						chessVersion.makeMove(this.getPiece(clickedPosition[0], clickedPosition[1]), i, j);
						isClicked = false;
					}
				}
			}
		}
	}
	
	/**
	 * @param piece
	 * @return
	 */
	public int[] getPosition(ChessPiece piece) {
		for(int i = 0; i < pieceGrid.length; i++) {
			for(int j = 0; j < pieceGrid[i].length; j++) {
				if(pieceGrid[i][j] == piece) {
					return new int[]{i, j};
				}
			}
		}
		return null;
	}
}