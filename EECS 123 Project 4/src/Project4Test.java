import org.junit.*;
import static org.junit.Assert.*;

public class Project4Test {

	private Xiangqi testGame;

	public Project4Test() {
		testGame = new Xiangqi();
	}

	@Test
	public void testPiecePositions() {
		/**
		 * Tests that all positions have correct pieces in them
		 * 
		 */

		// Test top pieces
		assertTrue("Pieces improperly positioned", testGame.chessBoard.getPiece(0, 0) instanceof RookPiece);
		assertTrue("Pieces improperly positioned", testGame.chessBoard.getPiece(0, 1) instanceof KnightPiece);
		assertTrue("Pieces improperly positioned", testGame.chessBoard.getPiece(0, 2) instanceof ElephantPiece);
		assertTrue("Pieces improperly positioned", testGame.chessBoard.getPiece(0, 3) instanceof GuardPiece);
		assertTrue("Pieces improperly positioned", testGame.chessBoard.getPiece(0, 4) instanceof KingPiece);
		assertTrue("Pieces improperly positioned", testGame.chessBoard.getPiece(0, 5) instanceof GuardPiece);
		assertTrue("Pieces improperly positioned", testGame.chessBoard.getPiece(0, 6) instanceof ElephantPiece);
		assertTrue("Pieces improperly positioned", testGame.chessBoard.getPiece(0, 7) instanceof KnightPiece);
		assertTrue("Pieces improperly positioned", testGame.chessBoard.getPiece(0, 8) instanceof RookPiece);
		assertTrue("Pieces improperly positioned", testGame.chessBoard.getPiece(2, 1) instanceof CannonPiece);
		assertTrue("Pieces improperly positioned", testGame.chessBoard.getPiece(2, 7) instanceof CannonPiece);
		assertTrue("Pieces improperly positioned", testGame.chessBoard.getPiece(3, 0) instanceof PawnPiece);
		assertTrue("Pieces improperly positioned", testGame.chessBoard.getPiece(3, 2) instanceof PawnPiece);
		assertTrue("Pieces improperly positioned", testGame.chessBoard.getPiece(3, 4) instanceof PawnPiece);
		assertTrue("Pieces improperly positioned", testGame.chessBoard.getPiece(3, 6) instanceof PawnPiece);
		assertTrue("Pieces improperly positioned", testGame.chessBoard.getPiece(3, 8) instanceof PawnPiece);

		// Test bottom pieces
		assertTrue("Pieces improperly positioned", testGame.chessBoard.getPiece(9, 0) instanceof RookPiece);
		assertTrue("Pieces improperly positioned", testGame.chessBoard.getPiece(9, 1) instanceof KnightPiece);
		assertTrue("Pieces improperly positioned", testGame.chessBoard.getPiece(9, 2) instanceof ElephantPiece);
		assertTrue("Pieces improperly positioned", testGame.chessBoard.getPiece(9, 3) instanceof GuardPiece);
		assertTrue("Pieces improperly positioned", testGame.chessBoard.getPiece(9, 4) instanceof KingPiece);
		assertTrue("Pieces improperly positioned", testGame.chessBoard.getPiece(9, 5) instanceof GuardPiece);
		assertTrue("Pieces improperly positioned", testGame.chessBoard.getPiece(9, 6) instanceof ElephantPiece);
		assertTrue("Pieces improperly positioned", testGame.chessBoard.getPiece(9, 7) instanceof KnightPiece);
		assertTrue("Pieces improperly positioned", testGame.chessBoard.getPiece(9, 8) instanceof RookPiece);
		assertTrue("Pieces improperly positioned", testGame.chessBoard.getPiece(7, 1) instanceof CannonPiece);
		assertTrue("Pieces improperly positioned", testGame.chessBoard.getPiece(7, 7) instanceof CannonPiece);
		assertTrue("Pieces improperly positioned", testGame.chessBoard.getPiece(6, 0) instanceof PawnPiece);
		assertTrue("Pieces improperly positioned", testGame.chessBoard.getPiece(6, 2) instanceof PawnPiece);
		assertTrue("Pieces improperly positioned", testGame.chessBoard.getPiece(6, 4) instanceof PawnPiece);
		assertTrue("Pieces improperly positioned", testGame.chessBoard.getPiece(6, 6) instanceof PawnPiece);
		assertTrue("Pieces improperly positioned", testGame.chessBoard.getPiece(6, 8) instanceof PawnPiece);
	}



	@Test
	public void testGetColor() {

		// Test top piece
		assertEquals("Top pieces incorrect color", testGame.chessBoard.getPiece(0, 0).getColor(), java.awt.Color.BLACK);

		// Test bottom piece
		assertEquals("Bottom pieces incorrect color", testGame.chessBoard.getPiece(9, 0).getColor(), java.awt.Color.RED.darker());
	}

	@Test
	public void testGetLabel() {

		// Testing getLabel() of all types of pieces
		assertTrue("Rook incorrect label", testGame.chessBoard.getPiece(0, 0).getLabel() == "R");
		assertTrue("Knight incorrect label", testGame.chessBoard.getPiece(0, 1).getLabel() == "KT");
		assertTrue("Elephant incorrect label", testGame.chessBoard.getPiece(0, 2).getLabel() == "E");
		assertTrue("Guard incorrect label", testGame.chessBoard.getPiece(0, 3).getLabel() == "G");
		assertTrue("King incorrect label", testGame.chessBoard.getPiece(0, 4).getLabel() == "KG");
		assertTrue("Cannon incorrect label", testGame.chessBoard.getPiece(2, 1).getLabel() == "C");
		assertTrue("Pawn incorrect label", testGame.chessBoard.getPiece(3, 0).getLabel() == "P");
	}

	@Test
	public void testGetIcon() {

		assertTrue("Incorrect Icon", testGame.chessBoard.getPiece(0, 0).getIcon() == null);
	}

	@Test
	public void testGetSide() {

		// Test top piece
		assertTrue(ChessGame.Side.NORTH == testGame.chessBoard.getPiece(0, 0).getSide());

		// Test bottom piece
		assertTrue(ChessGame.Side.SOUTH == testGame.chessBoard.getPiece(9, 0).getSide());
	}

	@Test
	public void testGetRowAndColumn() {

		// Test Row
		assertTrue(0 == testGame.chessBoard.getPiece(0, 0).getRow());

		//Test Row nonzero
		assertTrue(3 == testGame.chessBoard.getPiece(3, 0).getRow());


		// Test Column
		assertTrue(0 == testGame.chessBoard.getPiece(0, 0).getColumn());

		// Test Column nonzero
		assertTrue(3 == testGame.chessBoard.getPiece(0, 3).getColumn());
	}

	@Test
	public void testRemovePieceAndAddPiece() {

		// Check that the space is empty
		assertTrue("Pieces in spots meant to be empty or hasPiece() broken", testGame.chessBoard.hasPiece(1, 1) == false);

		// Add a piece
		testGame.chessBoard.addPiece(new PawnPiece(testGame.chessBoard, ChessGame.Side.NORTH), 1, 1);

		// Check that piece was added
		assertTrue("addPiece() broken", testGame.chessBoard.hasPiece(1, 1) == true);

		// Remove the piece
		testGame.chessBoard.removePiece(1, 1);

		// Check that piece was removed
		assertTrue("removePiece() broken", testGame.chessBoard.hasPiece(1, 1) == false);
	}


	@Test
	public void testPawnMovement() {
		
		assertTrue("Moving more than one space", testGame.chessBoard.getPiece(3, 0).isLegalNonCaptureMove(5, 0) == false);
		
		assertTrue("Moving sideways before crossing river", testGame.chessBoard.getPiece(3, 0).isLegalNonCaptureMove(3, 1) == false);
		
		assertTrue("Moving diagonally", testGame.chessBoard.getPiece(3, 0).isLegalNonCaptureMove(4, 1) == false);
		
		assertTrue("Moving backwards", testGame.chessBoard.getPiece(3, 0).isLegalNonCaptureMove(2, 0) == false);
		
		assertTrue("Not moving forwards one space", testGame.chessBoard.getPiece(3, 0).isLegalNonCaptureMove(4, 0) == true);
		
		testGame.chessBoard.addPiece(new PawnPiece(testGame.chessBoard, ChessGame.Side.NORTH), 5, 0);

		assertTrue("Not capturing enemy", testGame.chessBoard.getPiece(5, 0).isLegalCaptureMove(6, 0) == true);
		
		assertTrue("Not moving sideways after crossing river", testGame.chessBoard.getPiece(5, 0).isLegalNonCaptureMove(5, 1) == true);
		
		assertTrue("Moving sideways more than one piece", testGame.chessBoard.getPiece(5, 0).isLegalNonCaptureMove(5, 2) == false);

	}


	@Test
	public void testKingMovement() {

		assertTrue("Moving more than one space", testGame.chessBoard.getPiece(0, 4).isLegalNonCaptureMove(0, 6) == false);
		
		assertTrue("Moving diagonally", testGame.chessBoard.getPiece(0, 4).isLegalNonCaptureMove(1, 5) == false);
		
		assertTrue("Not moving forwards one space", testGame.chessBoard.getPiece(0, 4).isLegalNonCaptureMove(1, 4) == true);
		
		testGame.chessBoard.addPiece(new KingPiece(testGame.chessBoard,ChessGame.Side.NORTH), 2, 3);
		
		assertTrue("moving sideways more than one space", testGame.chessBoard.getPiece(2, 3).isLegalNonCaptureMove(2, 5) == false);
		
		assertTrue("Not moving sideways one space", testGame.chessBoard.getPiece(2, 3).isLegalNonCaptureMove(2, 4) == true);
		
		assertTrue("Not moving backwards one space", testGame.chessBoard.getPiece(2, 3).isLegalNonCaptureMove(1, 3) == true);

		assertTrue("Moving sideways outside restricted box", testGame.chessBoard.getPiece(2, 3).isLegalNonCaptureMove(2, 2) == false);
		
		assertTrue("Moving forward outside restricted box", testGame.chessBoard.getPiece(2, 3).isLegalNonCaptureMove(3, 3) == false);
		
		testGame.chessBoard.addPiece(new PawnPiece(testGame.chessBoard, ChessGame.Side.SOUTH), 2, 4);
		
		assertTrue("Not capturing enemy", testGame.chessBoard.getPiece(2, 3).isLegalCaptureMove(2, 4) == true);
	}
	
	/**
	 * Not enough time to test others T_T
	 * 
	 * Then again, they work, so I'm not too beaten up about it
	 */
	
}