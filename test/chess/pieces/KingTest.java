package chess.pieces;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import chess.movement.Move;
import chess.movement.Position;

public class KingTest {
  @Test
    public void isLegalMove_WorksForRightward() {
      King king = new King(Color.RED);
  
      Move move = new Move(king.getCurrentPosition(), new Position(6,8));
  
      assertTrue(king.isLegalMove(move));
      

    }

    @Test
    public void isLegalMove_WorksForLeftward() {
      King king = new King(Color.RED);
  
      Move move = new Move(king.getCurrentPosition(), new Position(4,8));
  
      assertTrue(king.isLegalMove(move));
    }

    @Test
    public void isLegalMove_WorksForDownward() {
      King king = new King(Color.RED);
  
      Move move = new Move(king.getCurrentPosition(), new Position(5,7));
  
      assertTrue(king.isLegalMove(move));
    }

    @Test
    public void isLegalMove_WorksForUpward() {
      King king = new King(Color.WHITE);
  
      Move move = new Move(king.getCurrentPosition(), new Position(5,2));
  
      assertTrue(king.isLegalMove(move));
    }

    @Test
    public void isLegalMove_WorksForDiagonalRightUp() {
      King king = new King(Color.WHITE);
  
      Move move = new Move(king.getCurrentPosition(), new Position(6,2));
  
      assertTrue(king.isLegalMove(move));
    }

    @Test
    public void isLegalMove_WorksForDiagonalLeftUp() {
      King king = new King(Color.WHITE);
  
      Move move = new Move(king.getCurrentPosition(), new Position(4,2));
  
      assertTrue(king.isLegalMove(move));
    }

    @Test
    public void isLegalMove_WorksForDiagonalRightDown() {
      King king = new King(Color.RED);
  
      Move move = new Move(king.getCurrentPosition(), new Position(6,7));
  
      assertTrue(king.isLegalMove(move));
    }

    @Test
    public void isLegalMove_WorksForDiagonalLeftDown() {
      King king = new King(Color.RED);
  
      Move move = new Move(king.getCurrentPosition(), new Position(4,7));
  
      assertTrue(king.isLegalMove(move));
    }

    @Test
    public void isLegalMove_FailsForDiagonalMoreThanOne() {
      King king = new King(Color.RED);
  
      Move move = new Move(king.getCurrentPosition(), new Position(3,6));
  
      assertFalse(king.isLegalMove(move));
    }

    @Test
    public void isLegalMove_FailsForInlineMoreThanOne() {
      King king = new King(Color.RED);
  
      Move move = new Move(king.getCurrentPosition(), new Position(5,6));
  
      assertFalse(king.isLegalMove(move));
    }

    @Test
    public void isLegalMove_FailsForKnightward() {
      King king = new King(Color.RED);
  
      Move move = new Move(king.getCurrentPosition(), new Position(4,6));
  
      assertFalse(king.isLegalMove(move));
    }
}
