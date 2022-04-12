package chess.pieces;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import chess.movement.Move;
import chess.movement.Position;

public class RookTest {
    @Test
    public void isLegalMove_WorksForRightward() {
      Rook rook = new Rook(Color.RED, 1);
  
      Move move = new Move(rook.getCurrentPosition(), new Position(4,8));
  
      assertTrue(rook.isLegalMove(move));
      

    }

    @Test
    public void isLegalMove_WorksForLeftward() {
      Rook rook = new Rook(Color.RED, 8);
  
      Move move = new Move(rook.getCurrentPosition(), new Position(4,8));
  
      assertTrue(rook.isLegalMove(move));
    }

    @Test
    public void isLegalMove_WorksForDownward() {
      Rook rook = new Rook(Color.RED, 8);
  
      Move move = new Move(rook.getCurrentPosition(), new Position(8,4));
  
      assertTrue(rook.isLegalMove(move));
    }

    @Test
    public void isLegalMove_WorksForUpward() {
      Rook rook = new Rook(Color.WHITE, 1);
  
      Move move = new Move(rook.getCurrentPosition(), new Position(1,4));
  
      assertTrue(rook.isLegalMove(move));
    }

    @Test
    public void isLegalMove_FailsForDiagonal() {
      Rook rook = new Rook(Color.WHITE, 1);
  
      Move move = new Move(rook.getCurrentPosition(), new Position(4,4));
  
      assertFalse(rook.isLegalMove(move));
    }

    @Test
    public void isLegalMove_FailsForKnightward() {
      Rook rook = new Rook(Color.WHITE, 1);
  
      Move move = new Move(rook.getCurrentPosition(), new Position(2,3));
  
      assertFalse(rook.isLegalMove(move));
    }
}
