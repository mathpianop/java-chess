package chess.pieces;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import chess.movement.Move;
import chess.movement.Position;
public class BishopTest {

    @Test
    public void constructorThrowsExceptionIfNotAValidPlacement() {
      assertThrows(IllegalArgumentException.class, () -> {
        new Bishop(Color.RED, 2);
      });
    }

    @Test
    public void isLegalMove_FailsForRightward() {
      Bishop bishop = new Bishop(Color.RED, 3);
  
      Move move = new Move(bishop.getCurrentPosition(), new Position(8,8));
  
      assertFalse(bishop.isLegalMove(move));
      

    }

    @Test
    public void isLegalMove_FailsForLeftward() {
      Bishop bishop = new Bishop(Color.RED, 3);
  
      Move move = new Move(bishop.getCurrentPosition(), new Position(1,8));
  
      assertFalse(bishop.isLegalMove(move));
    }

    @Test
    public void isLegalMove_FailsForDownward() {
      Bishop bishop = new Bishop(Color.RED, 3);
  
      Move move = new Move(bishop.getCurrentPosition(), new Position(3,3));
  
      assertFalse(bishop.isLegalMove(move));
    }

    @Test
    public void isLegalMove_FailsForUpward() {
      Bishop bishop = new Bishop(Color.WHITE, 3);
  
      Move move = new Move(bishop.getCurrentPosition(), new Position(3,4));
  
      assertFalse(bishop.isLegalMove(move));
    }

    @Test
    public void isLegalMove_WorksForDiagonalRightUp() {
      Bishop bishop = new Bishop(Color.WHITE, 3);
  
      Move move = new Move(bishop.getCurrentPosition(), new Position(6,4));
  
      assertTrue(bishop.isLegalMove(move));
    }

    @Test
    public void isLegalMove_WorksForDiagonalLeftUp() {
      Bishop bishop = new Bishop(Color.WHITE, 3);
  
      Move move = new Move(bishop.getCurrentPosition(), new Position(1,3));
  
      assertTrue(bishop.isLegalMove(move));
    }

    @Test
    public void isLegalMove_WorksForDiagonalRightDown() {
      Bishop bishop = new Bishop(Color.RED, 3);
  
      Move move = new Move(bishop.getCurrentPosition(), new Position(7,4));
  
      assertTrue(bishop.isLegalMove(move));
    }

    @Test
    public void isLegalMove_WorksForDiagonalLeftDown() {
      Bishop bishop = new Bishop(Color.RED, 3);
  
      Move move = new Move(bishop.getCurrentPosition(), new Position(1,6));
  
      assertTrue(bishop.isLegalMove(move));
    }

    @Test
    public void isLegalMove_FailsForKnightward() {
      Bishop bishop = new Bishop(Color.WHITE, 3);
  
      Move move = new Move(bishop.getCurrentPosition(), new Position(4,3));
  
      assertFalse(bishop.isLegalMove(move));
    }
}
