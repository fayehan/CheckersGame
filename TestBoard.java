import static org.junit.Assert.*;

import org.junit.Test;

public class TestBoard{
	public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestBoard.class);
    }

    @Test
    public void testRemove() {
        Board b = new Board(true);
        Piece shield = new Piece(true, b, 1, 1, "shield");
        Piece bomb= new Piece(false, b, 0, 0, "bomb");
        shield.move(2,2);
        bomb.move(1,1);
        assertEquals(null, b.pieceAt(0,0));
    }

    @Test
    public void testWinner(){
    	Board b = new Board(true);
    	
    }




}