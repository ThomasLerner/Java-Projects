import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * @author Tom
 *
 */
public class Xiangqi implements ChessGame{

	/**
	 * 
	 */
	ChessBoard chessBoard;

	/**
	 * 
	 */
	ChessGame.Side side;

	/**
	 * 
	 */
	public Xiangqi() {

		/**
		 * 
		 */
		chessBoard = new ChessBoard(10, 9, new XiangqiDisplay(), this);
		
		/**
		 * 
		 */
		chessBoard.addPiece(new RookPiece(chessBoard, ChessGame.Side.NORTH), 0, 0);
		chessBoard.addPiece(new KnightPiece(chessBoard, ChessGame.Side.NORTH), 0, 1);
		chessBoard.addPiece(new ElephantPiece(chessBoard, ChessGame.Side.NORTH), 0, 2);
		chessBoard.addPiece(new GuardPiece(chessBoard, ChessGame.Side.NORTH), 0, 3);
		chessBoard.addPiece(new KingPiece(chessBoard, ChessGame.Side.NORTH), 0, 4);
		chessBoard.addPiece(new GuardPiece(chessBoard, ChessGame.Side.NORTH), 0, 5);
		chessBoard.addPiece(new ElephantPiece(chessBoard, ChessGame.Side.NORTH), 0, 6);
		chessBoard.addPiece(new KnightPiece(chessBoard, ChessGame.Side.NORTH), 0, 7);
		chessBoard.addPiece(new RookPiece(chessBoard, ChessGame.Side.NORTH), 0, 8);
		chessBoard.addPiece(new CannonPiece(chessBoard, ChessGame.Side.NORTH), 2, 1);
		chessBoard.addPiece(new CannonPiece(chessBoard, ChessGame.Side.NORTH), 2, 7);
		chessBoard.addPiece(new PawnPiece(chessBoard, ChessGame.Side.NORTH), 3, 0);
		chessBoard.addPiece(new PawnPiece(chessBoard, ChessGame.Side.NORTH), 3, 2);
		chessBoard.addPiece(new PawnPiece(chessBoard, ChessGame.Side.NORTH), 3, 4);
		chessBoard.addPiece(new PawnPiece(chessBoard, ChessGame.Side.NORTH), 3, 6);
		chessBoard.addPiece(new PawnPiece(chessBoard, ChessGame.Side.NORTH), 3, 8);

		/**
		 * 
		 */
		chessBoard.addPiece(new RookPiece(chessBoard, ChessGame.Side.SOUTH), 9, 0);
		chessBoard.addPiece(new KnightPiece(chessBoard, ChessGame.Side.SOUTH), 9, 1);
		chessBoard.addPiece(new ElephantPiece(chessBoard, ChessGame.Side.SOUTH), 9, 2);
		chessBoard.addPiece(new GuardPiece(chessBoard, ChessGame.Side.SOUTH), 9, 3);
		chessBoard.addPiece(new KingPiece(chessBoard, ChessGame.Side.SOUTH), 9, 4);
		chessBoard.addPiece(new GuardPiece(chessBoard, ChessGame.Side.SOUTH), 9, 5);
		chessBoard.addPiece(new ElephantPiece(chessBoard, ChessGame.Side.SOUTH), 9, 6);
		chessBoard.addPiece(new KnightPiece(chessBoard, ChessGame.Side.SOUTH), 9, 7);
		chessBoard.addPiece(new RookPiece(chessBoard, ChessGame.Side.SOUTH), 9, 8);
		chessBoard.addPiece(new CannonPiece(chessBoard, ChessGame.Side.SOUTH), 7, 1);
		chessBoard.addPiece(new CannonPiece(chessBoard, ChessGame.Side.SOUTH), 7, 7);
		chessBoard.addPiece(new PawnPiece(chessBoard, ChessGame.Side.SOUTH), 6, 0);
		chessBoard.addPiece(new PawnPiece(chessBoard, ChessGame.Side.SOUTH), 6, 2);
		chessBoard.addPiece(new PawnPiece(chessBoard, ChessGame.Side.SOUTH), 6, 4);
		chessBoard.addPiece(new PawnPiece(chessBoard, ChessGame.Side.SOUTH), 6, 6);
		chessBoard.addPiece(new PawnPiece(chessBoard, ChessGame.Side.SOUTH), 6, 8);
		
		/**
		 * 
		 */
		side = ChessGame.Side.SOUTH;
	}

	/** 
	 * @see ChessGame#legalPieceToPlay(ChessPiece)
	 */
	@Override
	public boolean legalPieceToPlay(ChessPiece piece) {
		if(side == ChessGame.Side.NORTH && piece.getSide() == ChessGame.Side.NORTH) {
			return true;
		}
		else if(side == ChessGame.Side.SOUTH && piece.getSide() == ChessGame.Side.SOUTH) {
			return true;
		}
		else {
			return false;
		}
	}

	/** 
	 * @see ChessGame#makeMove(ChessPiece, int, int)
	 */
	@Override
	public boolean makeMove(ChessPiece piece, int row, int column) {
		if(piece.isLegalMove(row, column)) {
			if(chessBoard.hasPiece(row, column) && chessBoard.getPiece(row, column).getLabel() == "KG") {
				if(side == ChessGame.Side.NORTH) {
					JFrame winFrame = new JFrame("BLACK WINS!");
					JTextArea winText = new JTextArea("BLACK WINS!");
					winFrame.add(winText);
					winFrame.setVisible(true);
					winFrame.setSize(500, 500);
				}
				else {
					JFrame winFrame = new JFrame("RED WINS!");
					JTextArea winText = new JTextArea("RED WINS!");
					winFrame.add(winText);
					winFrame.setVisible(true);
					winFrame.setSize(500, 500);
				}
			}
			chessBoard.removePiece(piece.getRow(), piece.getColumn());
			chessBoard.addPiece(piece, row, column);
			piece.moveDone();
			if(piece.getSide() == ChessGame.Side.NORTH){
				side = ChessGame.Side.SOUTH;
				return true;
			}
			else {
				side = ChessGame.Side.NORTH;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Xiangqi();
	}
}
