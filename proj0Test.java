import staffp0.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class proj0Test {

	private void testPiece(A staffP, Piece studentP) {
		assertEquals(staffP.C(), studentP.isFire());
		assertEquals(staffP.F(), studentP.side());
		assertEquals(staffP.A(), studentP.isBomb());
		assertEquals(staffP.I(), studentP.isShield());
		assertEquals(staffP.G(), studentP.isKing());
		assertEquals(staffP.E(), studentP.hasCaptured());
	}

	private void testBoard(B staffB, Board studentB) {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 9; y++) {
				A staffP = staffB.C(x, y);
				Piece studentP = studentB.pieceAt(x, y);
				if (staffP == null) assertEquals(null, studentP);
				else testPiece(staffP, studentP);
				assertEquals(staffB.A(x, y), studentB.canSelect(x, y));
			}
		}
	}

	/* START OF RELEASED TESTS */

	@Test
	public void release1() {
		
		B staffB = new B(false);
		Board studentB = new Board(false);

		staffB.D(0, 2);
		studentB.select(0, 2);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(0, 2, 1, 3), studentB.validMove(0, 2, 1, 3));

		staffB.D(1, 3);
		studentB.select(1, 3);
		testBoard(staffB, studentB);

		staffB.A();
		studentB.endTurn();
		testBoard(staffB, studentB);
	}

	@Test
	public void release2() {
		B staffB = new B(false);
		Board studentB = new Board(false);
		testBoard(staffB, studentB);	
	}

	@Test
	public void release3() {
		B staffB = new B(true);
		staffB.A(new A(false, staffB, 3, 5, "bomb"), 3, 5);
		staffB.A(new A(false, staffB, 7, 5, "bomb"), 7, 5);
		staffB.A(new A(true, staffB, 4, 4, "bomb"), 4, 4);
		staffB.A(new A(true, staffB, 6, 4, "bomb"), 6, 4);
		staffB.A(new A(true, staffB, 6, 2, "bomb"), 6, 2);
		staffB.A(new A(true, staffB, 4, 2, "shield"), 4, 2);
		staffB.A(new A(true, staffB, 0, 0, "shield"), 0, 0);

		Board studentB = new Board(true);
		studentB.place(new Piece(false, studentB, 3, 5, "bomb"), 3, 5);
		studentB.place(new Piece(false, studentB, 7, 5, "bomb"), 7, 5);
		studentB.place(new Piece(true, studentB, 4, 4, "bomb"), 4, 4);
		studentB.place(new Piece(true, studentB, 6, 4, "bomb"), 6, 4);
		studentB.place(new Piece(true, studentB, 6, 2, "bomb"), 6, 2);
		studentB.place(new Piece(true, studentB, 4, 2, "shield"), 4, 2);
		studentB.place(new Piece(true, studentB, 0, 0, "shield"), 0, 0);

		testBoard(staffB, studentB);

		staffB.D(0, 0);
		studentB.select(0, 0);
		testBoard(staffB, studentB);

		staffB.D(1, 1);
		studentB.select(1, 1);
		testBoard(staffB, studentB);

		staffB.A();
		studentB.endTurn();
		testBoard(staffB, studentB);

		staffB.D(3, 5);
		studentB.select(3, 5);
		testBoard(staffB, studentB);

		staffB.D(5, 3);
		studentB.select(5, 3);
		testBoard(staffB, studentB);
	}

	@Test
	public void release4() {
		B staffB = new B(true);
		Board studentB = new Board(true);

		assertEquals(null, studentB.winner());

		staffB.A(new A(true, staffB, 3, 5, "pawn"), 3, 5);
		staffB.A(new A(false, staffB, 2, 6, "pawn"), 2, 6);

		studentB.place(new Piece(true, studentB, 3, 5, "pawn"), 3, 5);
		studentB.place(new Piece(false, studentB, 2, 6, "pawn"), 2, 6);

		assertEquals(null, studentB.winner());

		staffB.D(3, 5);
		studentB.select(3, 5);
		testBoard(staffB, studentB);

		staffB.D(1, 7);
		studentB.select(1, 7);
		testBoard(staffB, studentB);

		staffB.A();
		studentB.endTurn();
		testBoard(staffB, studentB);

		assertEquals("Fire", studentB.winner());

		// --------------------------

		staffB = new B(true);
		staffB.A(new A(true, staffB, 2, 2, "pawn"), 2, 2);
		staffB.A(new A(false, staffB, 3, 3, "pawn"), 3, 3);

		studentB = new Board(true);
		studentB.place(new Piece(true, studentB, 2, 2, "pawn"), 2, 2);
		studentB.place(new Piece(false, studentB, 3, 3, "pawn"), 3, 3);

		staffB.D(3, 3);
		studentB.select(3, 3);
		testBoard(staffB, studentB);

		staffB.D(1, 1);
		studentB.select(1, 1);
		testBoard(staffB, studentB);

		staffB.A();
		studentB.endTurn();
		testBoard(staffB, studentB);

		assertEquals("Water", studentB.winner());

		// --------------------------

		staffB = new B(true);
		staffB.A(new A(true, staffB, 2, 2, "bomb"), 2, 2);
		staffB.A(new A(false, staffB, 3, 3, "bomb"), 3, 3);

		studentB = new Board(true);
		studentB.place(new Piece(true, studentB, 2, 2, "bomb"), 2, 2);
		studentB.place(new Piece(false, studentB, 3, 3, "bomb"), 3, 3);

		staffB.D(3, 3);
		studentB.select(3, 3);
		testBoard(staffB, studentB);

		staffB.D(1, 1);
		studentB.select(1, 1);
		testBoard(staffB, studentB);

		staffB.A();
		studentB.endTurn();
		testBoard(staffB, studentB);

		assertEquals("No one", studentB.winner());
	}

	@Test
	public void release5() {
		B staffB = new B(false);
		Board studentB = new Board(false);	
		
		staffB.D(0, 2);
		studentB.select(0, 2);
		testBoard(staffB, studentB);

		staffB.D(1, 3);
		studentB.select(1, 3);
		testBoard(staffB, studentB);

		staffB.A();
		studentB.endTurn();
		testBoard(staffB, studentB);

		staffB.D(1, 7);
		studentB.select(1, 7);
		testBoard(staffB, studentB);

		staffB.D(1, 5);
		studentB.select(1, 5);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(1, 5, 2, 4), studentB.validMove(1, 5, 2, 4));

		staffB.D(2, 4);
		studentB.select(2, 4);
		testBoard(staffB, studentB);

		staffB.A();
		studentB.endTurn();
		testBoard(staffB, studentB);

		staffB.D(1, 1);
		studentB.select(1, 1);
		testBoard(staffB, studentB);
	}

	@Test
	public void release6() {
		B staffB = new B(true);
		staffB.A(new A(true, staffB, 0, 6, "bomb"), 0, 6);

		Board studentB = new Board(true);
		studentB.place(new Piece(true, studentB, 0, 6, "bomb"), 0, 6);

		testBoard(staffB, studentB);
	}

	@Test
	public void release7() {
		B staffB = new B(false);
		Board studentB = new Board(false);

		staffB.D(0, 2);
		studentB.select(0, 2);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(0, 2, 1, 3), studentB.validMove(0, 2, 1, 3));

		staffB.D(1, 3);
		studentB.select(1, 3);
		testBoard(staffB, studentB);

		staffB.A();
		studentB.endTurn();
		testBoard(staffB, studentB);
	}

	@Test
	public void release8() {
		B staffB = new B(true);
		staffB.A(new A(true, staffB, 0, 6, "bomb"), 0, 6);

		Board studentB = new Board(true);
		studentB.place(new Piece(true, studentB, 0, 6, "bomb"), 0, 6);
	}

	@Test
	public void release9() {
		B staffB = new B(true);
		staffB.A(new A(true, staffB, 0, 2, "shield"), 0, 2);
		staffB.A(new A(false, staffB, 1, 3, "shield"), 1, 3);

		Board studentB = new Board(true);
		studentB.place(new Piece(true, studentB, 0, 2, "shield"), 0, 2);
		studentB.place(new Piece(false, studentB, 1, 3, "shield"), 1, 3);

		testBoard(staffB, studentB);

		staffB.D(0, 2);
		studentB.select(0, 2);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(0, 2, 2, 4), studentB.validMove(0, 2, 2, 4));

		staffB.D(2, 4);
		studentB.select(2, 4);
		testBoard(staffB, studentB);

		staffB.A();
		studentB.endTurn();
		testBoard(staffB, studentB);
	}

	@Test
	public void release10() {
		release2();
	}

	@Test
	public void release11() {
		B staffB = new B(true);
		staffB.A(new A(true, staffB, 0, 6, "bomb"), 0, 6);
		staffB.A(new A(false, staffB, 1, 1, "bomb"), 1, 1);

		Board studentB = new Board(true);
		studentB.place(new Piece(true, studentB, 0, 6, "bomb"), 0, 6);
		studentB.place(new Piece(false, studentB, 1, 1, "bomb"), 1, 1);

		testBoard(staffB, studentB);

		staffB.D(0, 6);
		studentB.select(0, 6);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(0, 6, 1, 7), studentB.validMove(0, 6, 1, 7));

		staffB.D(1, 7);
		studentB.select(1, 7);
		testBoard(staffB, studentB);

		staffB.A();
		studentB.endTurn();
		testBoard(staffB, studentB);

		staffB.D(1, 1);
		studentB.select(1, 1);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(1, 1, 0, 0), studentB.validMove(1, 1, 0, 0));

		staffB.D(0, 0);
		studentB.select(0, 0);
		testBoard(staffB, studentB);
	}

	@Test
	public void release12() {
		B staffB = new B(true);
		staffB.A(new A(true, staffB, 0, 2, "shield"), 0, 2);
		staffB.A(new A(false, staffB, 1, 3, "bomb"), 1, 3);

		Board studentB = new Board(true);
		studentB.place(new Piece(true, studentB, 0, 2, "shield"), 0, 2);
		studentB.place(new Piece(false, studentB, 1, 3, "bomb"), 1, 3);

		testBoard(staffB, studentB);

		staffB.D(0, 2);
		studentB.select(0, 2);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(0, 2, 2, 4), studentB.validMove(0, 2, 2, 4));

		staffB.D(2, 4);
		studentB.select(2, 4);
		testBoard(staffB, studentB);
	}

	@Test
	public void release13() {
		B staffB = new B(true);
		Board studentB = new Board(true);
		testBoard(staffB, studentB);
	}

	@Test
	public void release14() {
		B staffB = new B(true);
		staffB.A(new A(true, staffB, 0, 6, "shield"), 0, 6);
		staffB.A(new A(true, staffB, 4, 6, "shield"), 4, 6);
		staffB.A(new A(false, staffB, 1, 1, "shield"), 1, 1);
		staffB.A(new A(false, staffB, 3, 1, "shield"), 3, 1);

		Board studentB = new Board(true);
		studentB.place(new Piece(true, studentB, 0, 6, "shield"), 0, 6);
		studentB.place(new Piece(true, studentB, 4, 6, "shield"), 4, 6);
		studentB.place(new Piece(false, studentB, 1, 1, "shield"), 1, 1);
		studentB.place(new Piece(false, studentB, 3, 1, "shield"), 3, 1);

		staffB.D(0, 6);
		studentB.select(0, 6);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(0, 6, 1, 7), studentB.validMove(0, 6, 1, 7));

		staffB.D(1, 7);
		studentB.select(1, 7);
		testBoard(staffB, studentB);

		staffB.A();
		studentB.endTurn();
		testBoard(staffB, studentB);

		staffB.D(1, 1);
		studentB.select(1, 1);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(1, 1, 0, 0), studentB.validMove(1, 1, 0, 0));

		staffB.D(0, 0);
		studentB.select(0, 0);
		testBoard(staffB, studentB);

		staffB.A();
		studentB.endTurn();
		testBoard(staffB, studentB);	

		staffB.D(4, 6);
		studentB.select(4, 6);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(4, 6, 3, 7), studentB.validMove(4, 6, 3, 7));

		staffB.D(3, 7);
		studentB.select(3, 7);
		testBoard(staffB, studentB);

		staffB.A();
		studentB.endTurn();
		testBoard(staffB, studentB);

		staffB.D(3, 1);
		studentB.select(3, 1);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(3, 1, 4, 0), studentB.validMove(3, 1, 4, 0));

		staffB.D(4, 0);
		studentB.select(4, 0);
		testBoard(staffB, studentB);

		staffB.A();
		studentB.endTurn();
		testBoard(staffB, studentB);

		staffB.A(new A(false, staffB, 2, 6, "shield"), 2, 6);
		studentB.place(new Piece(false, studentB, 2, 6, "shield"), 2, 6);

		staffB.D(1, 7);
		studentB.select(1, 7);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(1, 7, 3, 5), studentB.validMove(1, 7, 3, 5));

		staffB.D(3, 5);
		studentB.select(3, 5);
		testBoard(staffB, studentB);
	}

	@Test
	public void release15() {
		B staffB = new B(true);
		staffB.A(new A(true, staffB, 0, 2, "shield"), 0, 2);
		staffB.A(new A(false, staffB, 1, 3, "shield"), 1, 3);
		staffB.A(new A(false, staffB, 3, 5, "shield"), 3, 5);

		Board studentB = new Board(true);
		studentB.place(new Piece(true, studentB, 0, 2, "shield"), 0, 2);
		studentB.place(new Piece(false, studentB, 1, 3, "shield"), 1, 3);
		studentB.place(new Piece(false, studentB, 3, 5, "shield"), 3, 5);

		testBoard(staffB, studentB);

		staffB.D(0, 2);
		studentB.select(0, 2);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(0, 2, 2, 4), studentB.validMove(0, 2, 2, 4));	

		staffB.D(2, 4);
		studentB.select(2, 4);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(2, 4, 4, 6), studentB.validMove(2, 4, 4, 6));	

		staffB.D(4, 6);
		studentB.select(4, 6);
		testBoard(staffB, studentB);
	}

	@Test
	public void release16() {
		B staffB = new B(false);
		Board studentB = new Board(false);

		staffB.D(0, 0);
		studentB.select(0, 0);
		testBoard(staffB, studentB);

		staffB.D(0, 2);
		studentB.select(0, 2);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(0, 2, 1, 3), studentB.validMove(0, 2, 1, 3));	

		staffB.D(1, 3);
		studentB.select(1, 3);
		testBoard(staffB, studentB);
	}

	/* START OF SECRET TESTS */

	@Test
	public void secret1() {
		B staffB = new B(true);
		staffB.A(new A(true, staffB, 0, 2, "shield"), 0, 2);
		staffB.A(new A(false, staffB, 1, 3, "shield"), 1, 3);
		staffB.A(new A(false, staffB, 3, 5, "shield"), 3, 5);

		Board studentB = new Board(true);
		studentB.place(new Piece(true, studentB, 0, 2, "shield"), 0, 2);
		studentB.place(new Piece(false, studentB, 1, 3, "shield"), 1, 3);
		studentB.place(new Piece(false, studentB, 3, 5, "shield"), 3, 5);

		staffB.D(0, 2);
		studentB.select(0, 2);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(0, 2, 2, 4), studentB.validMove(0, 2, 2, 4));	

		staffB.D(2, 4);
		studentB.select(2, 4);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(2, 4, 4, 6), studentB.validMove(2, 4, 4, 6));	

		staffB.D(4, 6);
		studentB.select(4, 6);
		testBoard(staffB, studentB);
	}

	@Test
	public void secret2() {
		B staffB = new B(true);
		staffB.A(new A(true, staffB, 5, 5, "bomb"), 5, 5);
		staffB.A(new A(false, staffB, 6, 6, "bomb"), 6, 6);
		staffB.A(new A(false, staffB, 0, 0, "bomb"), 0, 0);

		Board studentB = new Board(true);
		studentB.place(new Piece(true, studentB, 5, 5, "bomb"), 5, 5);
		studentB.place(new Piece(false, studentB, 6, 6, "bomb"), 6, 6);
		studentB.place(new Piece(false, studentB, 0, 0, "bomb"), 0, 0);

		staffB.D(5, 5);
		studentB.select(5, 5);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(5, 5, 7, 7), studentB.validMove(5, 5, 7, 7));	

		staffB.D(7, 7);
		studentB.select(7, 7);
		testBoard(staffB, studentB);
	}

	@Test
	public void secret3() {
		B staffB = new B(true);
		staffB.A(new A(true, staffB, 0, 6, "shield"), 0, 6);
		Board studentB = new Board(true);
		studentB.place(new Piece(true, studentB, 0, 6, "shield"), 0, 6);

		staffB.D(0, 6);
		studentB.select(0, 6);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(0, 6, 1, 7), studentB.validMove(0, 6, 1, 7));	

		staffB.A();
		studentB.endTurn();
		testBoard(staffB, studentB);

		staffB.A(new A(false, staffB, 2, 6, "shield"), 2, 6);
		staffB.A(new A(false, staffB, 4, 6, "shield"), 4, 6);
		staffB.A(new A(false, staffB, 0, 0, "bomb"), 0, 0);

		studentB.place(new Piece(false, studentB, 2, 6, "shield"), 2, 6);
		studentB.place(new Piece(false, studentB, 4, 6, "shield"), 4, 6);
		studentB.place(new Piece(false, studentB, 0, 0, "bomb"), 0, 0);	

		staffB.D(0, 0);
		studentB.select(0, 0);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(0, 0, 1, 1), studentB.validMove(0, 0, 1, 1));	

		staffB.D(1, 1);
		studentB.select(1, 1);
		testBoard(staffB, studentB);

		staffB.D(1, 7);
		studentB.select(1, 7);
		testBoard(staffB, studentB);
	}

	@Test
	public void secret4() {
		B staffB = new B(false);
		Board studentB = new Board(false);

		staffB.D(0, 0);
		studentB.select(0, 0);
		testBoard(staffB, studentB);

		staffB.D(0, 2);
		studentB.select(0, 2);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(0, 2, 1, 3), studentB.validMove(0, 2, 1, 3));

		staffB.D(1, 3);
		studentB.select(1, 3);
		testBoard(staffB, studentB);
	}

	@Test
	public void secret5() {
		secret3();
	}

	@Test
	public void secret6() {
		B staffB = new B(true);
		Board studentB = new Board(true);

		staffB.A();
		studentB.endTurn();
		testBoard(staffB, studentB);
	}

	@Test
	public void secret7() {
		B staffB = new B(true);
		staffB.A(new A(true, staffB, 0, 2, "shield"), 0, 2);
		staffB.A(new A(false, staffB, 1, 3, "bomb"), 1, 3);

		Board studentB = new Board(true);
		studentB.place(new Piece(true, studentB, 0, 2, "shield"), 0, 2);
		studentB.place(new Piece(false, studentB, 1, 3, "bomb"), 1, 3);

		staffB.D(0, 2);
		studentB.select(0, 2);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(0, 2, 2, 4), studentB.validMove(0, 2, 2, 4));

		staffB.D(2, 4);
		studentB.select(2, 4);
		testBoard(staffB, studentB);
	}

	@Test
	public void secret8() {
		B staffB = new B(true);
		staffB.A(new A(true, staffB, 0, 6, "bomb"), 0, 6);
		staffB.A(new A(false, staffB, 1, 1, "bomb"), 1, 1);

		Board studentB = new Board(true);
		studentB.place(new Piece(true, studentB, 0, 6, "bomb"), 0, 6);
		studentB.place(new Piece(false, studentB, 1, 1, "bomb"), 1, 1);

		testBoard(staffB, studentB);

		staffB.D(0, 6);
		studentB.select(0, 6);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(0, 6, 1, 7), studentB.validMove(0, 6, 1, 7));	

		staffB.D(1, 7);
		studentB.select(1, 7);
		testBoard(staffB, studentB);

		staffB.A();
		studentB.endTurn();
		testBoard(staffB, studentB);

		staffB.D(1, 1);
		studentB.select(1, 1);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(1, 1, 0, 0), studentB.validMove(1, 1, 0, 0));	

		staffB.D(0, 0);
		studentB.select(0, 0);
		testBoard(staffB, studentB);

		staffB.A();
		studentB.endTurn();
		testBoard(staffB, studentB);

		staffB.D(1, 7);
		studentB.select(1, 7);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(1, 7, 0, 6), studentB.validMove(1, 7, 0, 6));	
		assertEquals(staffB.A(1, 7, 2, 6), studentB.validMove(1, 7, 2, 6));	

		staffB.D(2, 6);
		studentB.select(2, 6);
		testBoard(staffB, studentB);

		staffB.A();
		studentB.endTurn();
		testBoard(staffB, studentB);

		staffB.D(0, 0);
		studentB.select(0, 0);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(0, 0, 1, 1), studentB.validMove(0, 0, 1, 1));	

		staffB.D(1, 1);
		studentB.select(1, 1);
		testBoard(staffB, studentB);

		staffB.A();
		studentB.endTurn();
		testBoard(staffB, studentB);
	}

	@Test
	public void secret9() {
		B staffB = new B(true);
		staffB.A(new A(true, staffB, 0, 0, "bomb"), 0, 0);
		staffB.A(new A(false, staffB, 2, 0, "bomb"), 2, 0);
		staffB.A(new A(false, staffB, 1, 1, "bomb"), 1, 1);
		staffB.A(new A(true, staffB, 0, 2, "bomb"), 0, 2);
		staffB.A(new A(false, staffB, 4, 2, "bomb"), 4, 2);
		staffB.A(new A(true, staffB, 1, 3, "pawn"), 1, 3);
		staffB.A(new A(false, staffB, 1, 1, "bomb"), 3, 3);
		staffB.A(new A(true, staffB, 2, 4, "bomb"), 2, 4);
		staffB.A(new A(true, staffB, 4, 4, "bomb"), 4, 4);

		Board studentB = new Board(true);
		studentB.place(new Piece(true, studentB, 0, 0, "bomb"), 0, 0);
		studentB.place(new Piece(false, studentB, 2, 0, "bomb"), 2, 0);
		studentB.place(new Piece(false, studentB, 1, 1, "bomb"), 1, 1);
		studentB.place(new Piece(true, studentB, 0, 2, "bomb"), 0, 2);
		studentB.place(new Piece(false, studentB, 4, 2, "bomb"), 4, 2);
		studentB.place(new Piece(true, studentB, 1, 3, "pawn"), 1, 3);
		studentB.place(new Piece(false, studentB, 1, 1, "bomb"), 3, 3);
		studentB.place(new Piece(true, studentB, 2, 4, "bomb"), 2, 4);
		studentB.place(new Piece(true, studentB, 4, 4, "bomb"), 4, 4);

		testBoard(staffB, studentB);

		staffB.D(0, 0);
		studentB.select(0, 0);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(0, 0, 2, 2), studentB.validMove(0, 0, 2, 2));	

		staffB.D(2, 2);
		studentB.select(2, 2);
		testBoard(staffB, studentB);	
	}

	@Test
	public void secret10() {
		B staffB = new B(true);
		staffB.A(new A(true, staffB, 2, 2, "bomb"), 2, 2);
		staffB.A(new A(false, staffB, 3, 3, "bomb"), 3, 3);

		Board studentB = new Board(true);
		studentB.place(new Piece(true, studentB, 2, 2, "bomb"), 2, 2);
		studentB.place(new Piece(false, studentB, 3, 3, "bomb"), 3, 3);

		staffB.D(3, 3);
		studentB.select(3, 3);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(3, 3, 1, 1), studentB.validMove(3, 3, 1, 1));

		staffB.D(1, 1);
		studentB.select(1, 1);
		testBoard(staffB, studentB);
	}

	@Test
	public void secret11() {
		B staffB = new B(true);
		staffB.A(new A(true, staffB, 0, 0, "pawn"), 0, 0);
		staffB.A(new A(false, staffB, 1, 1, "pawn"), 1, 1);
		staffB.A(new A(false, staffB, 3, 3, "pawn"), 3, 3);
		staffB.A(new A(false, staffB, 5, 5, "pawn"), 5, 5);
		staffB.A(new A(false, staffB, 7, 7, "pawn"), 7, 7);

		Board studentB = new Board(true);
		studentB.place(new Piece(true, studentB, 0, 0, "pawn"), 0, 0);
		studentB.place(new Piece(false, studentB, 1, 1, "pawn"), 1, 1);
		studentB.place(new Piece(false, studentB, 3, 3, "pawn"), 3, 3);
		studentB.place(new Piece(false, studentB, 5, 5, "pawn"), 5, 5);
		studentB.place(new Piece(false, studentB, 7, 7, "pawn"), 7, 7);

		staffB.D(0, 0);
		studentB.select(0, 0);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(0, 0, 2, 2), studentB.validMove(0, 0, 2, 2));	

		staffB.D(2, 2);
		studentB.select(2, 2);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(2, 2, 4, 4), studentB.validMove(2, 2, 4, 4));	
		assertEquals(staffB.A(2, 2, 0, 4), studentB.validMove(2, 2, 0, 4));
	}

	@Test
	public void secret12() {
		B staffB = new B(false);
		Board studentB = new Board(false);
		testBoard(staffB, studentB);
	}

	@Test
	public void secret13() {
		B staffB = new B(true);
		staffB.A(new A(true, staffB, 0, 2, "shield"), 0, 2);
		staffB.A(new A(false, staffB, 1, 3, "shield"), 1, 3);

		Board studentB = new Board(true);
		studentB.place(new Piece(true, studentB, 0, 2, "shield"), 0, 2);
		studentB.place(new Piece(false, studentB, 1, 3, "shield"), 1, 3);

		staffB.D(0, 2);
		studentB.select(0, 2);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(0, 2, 2, 4), studentB.validMove(0, 2, 2, 4));

		staffB.D(2, 4);
		studentB.select(2, 4);
		testBoard(staffB, studentB);
	}

	@Test
	public void secret14() {
		B staffB = new B(true);
		staffB.A(new A(true, staffB, 0, 0, "pawn"), 0, 0);
		staffB.A(new A(false, staffB, 1, 1, "pawn"), 1, 1);
		staffB.A(new A(false, staffB, 3, 3, "pawn"), 3, 3);
		staffB.A(new A(false, staffB, 3, 5, "pawn"), 3, 5);
		staffB.A(new A(false, staffB, 7, 7, "pawn"), 7, 7);
		staffB.A(new A(true, staffB, 1, 3, "pawn"), 1, 3);

		Board studentB = new Board(true);
		studentB.place(new Piece(true, studentB, 0, 0, "pawn"), 0, 0);
		studentB.place(new Piece(false, studentB, 1, 1, "pawn"), 1, 1);
		studentB.place(new Piece(false, studentB, 3, 3, "pawn"), 3, 3);
		studentB.place(new Piece(false, studentB, 3, 5, "pawn"), 3, 5);
		studentB.place(new Piece(false, studentB, 7, 7, "pawn"), 7, 7);
		studentB.place(new Piece(true, studentB, 1, 3, "pawn"), 1, 3);

		staffB.D(0, 0);
		studentB.select(0, 0);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(0, 0, 2, 2), studentB.validMove(0, 0, 2, 2));	

		staffB.D(2, 2);
		studentB.select(2, 2);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(2, 2, 4, 4), studentB.validMove(2, 2, 4, 4));	
		assertEquals(staffB.A(2, 2, 0, 4), studentB.validMove(2, 2, 0, 4));
	}

	@Test
	public void secret15() {
		B staffB = new B(false);
		Board studentB = new Board(false);

		staffB.D(0, 2);
		studentB.select(0, 2);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(0, 2, 1, 3), studentB.validMove(0, 2, 1, 3));	

		staffB.D(1, 3);
		studentB.select(1, 3);
		testBoard(staffB, studentB);

		staffB.A();
		studentB.endTurn();
		testBoard(staffB, studentB);
	}

	@Test
	public void secret16() {
		B staffB = new B(true);
		staffB.A(new A(true, staffB, 0, 0, "pawn"), 0, 0);
		staffB.A(new A(false, staffB, 1, 1, "pawn"), 1, 1);
		staffB.A(new A(false, staffB, 3, 3, "pawn"), 3, 3);
		staffB.A(new A(false, staffB, 3, 5, "pawn"), 3, 5);
		staffB.A(new A(false, staffB, 7, 7, "pawn"), 7, 7);

		Board studentB = new Board(true);
		studentB.place(new Piece(true, studentB, 0, 0, "pawn"), 0, 0);
		studentB.place(new Piece(false, studentB, 1, 1, "pawn"), 1, 1);
		studentB.place(new Piece(false, studentB, 3, 3, "pawn"), 3, 3);
		studentB.place(new Piece(false, studentB, 3, 5, "pawn"), 3, 5);
		studentB.place(new Piece(false, studentB, 7, 7, "pawn"), 7, 7);

		staffB.D(0, 0);
		studentB.select(0, 0);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(0, 0, 2, 2), studentB.validMove(0, 0, 2, 2));	

		staffB.D(2, 2);
		studentB.select(2, 2);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(2, 2, 4, 4), studentB.validMove(2, 2, 4, 4));	

		staffB.D(4, 4);
		studentB.select(4, 4);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(4, 4, 2, 6), studentB.validMove(4, 4, 2, 6));

		staffB.D(2, 6);
		studentB.select(2, 6);
		testBoard(staffB, studentB);
	}

	@Test
	public void secret17() {
		B staffB = new B(false);
		Board studentB = new Board(false);

		staffB.D(0, 2);
		studentB.select(0, 2);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(0, 2, 1, 3), studentB.validMove(0, 2, 1, 3));

		staffB.D(1, 3);
		studentB.select(1, 3);
		testBoard(staffB, studentB);

		staffB.A();
		studentB.endTurn();
		testBoard(staffB, studentB);

		staffB.D(1, 7);
		studentB.select(1, 7);
		testBoard(staffB, studentB);

		staffB.D(1, 5);
		studentB.select(1, 5);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(1, 5, 2, 4), studentB.validMove(1, 5, 2, 4));

		staffB.D(2, 4);
		studentB.select(2, 4);
		testBoard(staffB, studentB);

		staffB.A();
		studentB.endTurn();
		testBoard(staffB, studentB);

		staffB.D(1, 1);
		studentB.select(1, 1);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(1, 1, 0, 2), studentB.validMove(1, 1, 0, 2));

		staffB.D(0, 2);
		studentB.select(0, 2);
		testBoard(staffB, studentB);

		staffB.A();
		studentB.endTurn();
		testBoard(staffB, studentB);
	}

	@Test
	public void secret18() {
		B staffB = new B(true);
		staffB.A(new A(true, staffB, 0, 0, "bomb"), 0, 0);
		staffB.A(new A(true, staffB, 4, 4, "shield"), 4, 4);
		staffB.A(new A(false, staffB, 4, 6, "bomb"), 4, 6);
		staffB.A(new A(false, staffB, 6, 4, "shield"), 6, 4);
		staffB.A(new A(true, staffB, 6, 6, "shield"), 6, 6);
		staffB.A(new A(false, staffB, 7, 7, "bomb"), 7, 7);

		Board studentB = new Board(true);
		studentB.place(new Piece(true, studentB, 0, 0, "bomb"), 0, 0);
		studentB.place(new Piece(true, studentB, 4, 4, "shield"), 4, 4);
		studentB.place(new Piece(false, studentB, 4, 6, "bomb"), 4, 6);
		studentB.place(new Piece(false, studentB, 6, 4, "shield"), 6, 4);
		studentB.place(new Piece(true, studentB, 6, 6, "shield"), 6, 6);
		studentB.place(new Piece(false, studentB, 7, 7, "bomb"), 7, 7);

		staffB.D(0, 0);
		studentB.select(0, 0);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(0, 0, 1, 1), studentB.validMove(0, 0, 1, 1));	

		staffB.D(1, 1);
		studentB.select(1, 1);
		testBoard(staffB, studentB);

		staffB.A();
		studentB.endTurn();
		testBoard(staffB, studentB);

		staffB.D(7, 7);
		studentB.select(7, 7);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(7, 7, 5, 5), studentB.validMove(7, 7, 5, 5));	

		staffB.D(5, 5);
		studentB.select(5, 5);
		testBoard(staffB, studentB);

		staffB.A();
		studentB.endTurn();
		testBoard(staffB, studentB);
	}

	@Test
	public void secret19() {
		B staffB = new B(false);
		Board studentB = new Board(false);

		staffB.D(0, 2);
		studentB.select(0, 2);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(0, 2, 1, 3), studentB.validMove(0, 2, 1, 3));

		staffB.D(1, 3);
		studentB.select(1, 3);
		testBoard(staffB, studentB);

		staffB.A();
		studentB.endTurn();
		testBoard(staffB, studentB);

		staffB.D(1, 5);
		studentB.select(1, 5);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(1, 5, 2, 4), studentB.validMove(1, 5, 2, 4));

		staffB.D(2, 4);
		studentB.select(2, 4);
		testBoard(staffB, studentB);

		staffB.A();
		studentB.endTurn();
		testBoard(staffB, studentB);

		staffB.D(1, 3);
		studentB.select(1, 3);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(1, 3, 0, 4), studentB.validMove(1, 3, 0, 4));
		assertEquals(staffB.A(1, 3, 0, 2), studentB.validMove(1, 3, 0, 2));

		staffB.D(0, 4);
		studentB.select(0, 4);
		testBoard(staffB, studentB);

		staffB.A();
		studentB.endTurn();
		testBoard(staffB, studentB);

		staffB.D(2, 4);
		studentB.select(2, 4);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(2, 4, 1, 3), studentB.validMove(2, 4, 1, 3));

		staffB.D(1, 3);
		studentB.select(1, 3);
		testBoard(staffB, studentB);
	}

	@Test
	public void secret20() {
		B staffB = new B(true);
		Board studentB = new Board(true);
		assertEquals(null, studentB.winner());

		staffB.A(new A(true, staffB, 3, 5, "pawn"), 3, 5);
		studentB.place(new Piece(true, studentB, 3, 5, "pawn"), 3, 5);

		staffB.A(new A(false, staffB, 2, 6, "pawn"), 2, 6);
		studentB.place(new Piece(false, studentB, 2, 6, "pawn"), 2, 6);

		staffB.D(3, 5);
		studentB.select(3, 5);
		testBoard(staffB, studentB);

		staffB.D(1, 7);
		studentB.select(1, 7);
		testBoard(staffB, studentB);

		staffB.A();
		studentB.endTurn();
		testBoard(staffB, studentB);

		assertEquals("Fire", studentB.winner());

		staffB = new B(true);
		studentB = new Board(true);

		staffB.A(new A(true, staffB, 2, 2, "pawn"), 2, 2);
		studentB.place(new Piece(true, studentB, 2, 2, "pawn"), 2, 2);

		staffB.A(new A(false, staffB, 3, 3, "pawn"), 3, 3);
		studentB.place(new Piece(false, studentB, 3, 3, "pawn"), 3, 3);

		staffB.D(3, 3);
		studentB.select(3, 3);
		testBoard(staffB, studentB);

		assertEquals(staffB.A(3, 3, 1, 1), studentB.validMove(3, 3, 1, 1));

		staffB.D(1, 1);
		studentB.select(1, 1);
		testBoard(staffB, studentB);

		assertEquals("Water", studentB.winner());
 	}

	public static void main(String[] args) {
		jh61b.junit.textui.runClasses(proj0Test.class);
	}
}