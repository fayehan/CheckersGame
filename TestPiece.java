import static org.junit.Assert.*;

import org.junit.Test;

public class TestPiece{
	public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestPiece.class);
    }
    public Board b= new Board(false);
    public Piece bomb= new Piece(true, b, 1,7,"bomb");
    public Piece shield= new Piece(false, b, 2, 3, "shield");
    public Piece pawn= new Piece(true,b,2,2,"pawn");



    @Test
    public void testIsFire(){
    	assertEquals(bomb.isFire(), true);
    	assertEquals(shield.isFire(),false);
    	assertEquals(pawn.isFire(),true);
    }

    @Test
    public void testSide(){
    	assertEquals(bomb.side(),0);
    	assertEquals(shield.side(),1);
    }

    @Test
    public void testIsking(){
    	assertEquals(bomb.isKing(),true);
    	assertEquals(shield.isKing(),false);
    }

    @Test
    public void testBombShield(){
    	assertEquals(bomb.isBomb(),true);
    	assertEquals(shield.isShield(),true);
    	assertEquals(pawn.isBomb(),false);
    }


}