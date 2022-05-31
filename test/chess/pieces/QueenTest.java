package chess.pieces;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import chess.movement.Move;
import chess.movement.Position;

public class QueenTest {
  @Test
    public void isLegalMove_WorksForRightward() {
      Queen queen = new Queen(Color.RED);
  
      Move move = new Move(queen.getCurrentPosition(), new Position(8,8));
  
      assertTrue(queen.isLegalMove(move));
      

    }

    @Test
    public void isLegalMove_WorksForLeftward() {
      Queen queen = new Queen(Color.RED);
  
      Move move = new Move(queen.getCurrentPosition(), new Position(1,8));
  
      assertTrue(queen.isLegalMove(move));
    }

    @Test
    public void isLegalMove_WorksForDownward() {
      Queen queen = new Queen(Color.RED);
  
      Move move = new Move(queen.getCurrentPosition(), new Position(4,4));
  
      assertTrue(queen.isLegalMove(move));
    }

    @Test
    public void isLegalMove_WorksForUpward() {
      Queen queen = new Queen(Color.WHITE);
  
      Move move = new Move(queen.getCurrentPosition(), new Position(4,4));
  
      assertTrue(queen.isLegalMove(move));
    }

    @Test
    public void isLegalMove_WorksForDiagonalRightUp() {
      Queen queen = new Queen(Color.WHITE);
  
      Move move = new Move(queen.getCurrentPosition(), new Position(7,4));
  
      assertTrue(queen.isLegalMove(move));
    }

    @Test
    public void isLegalMove_WorksForDiagonalLeftUp() {
      Queen queen = new Queen(Color.WHITE);
  
      Move move = new Move(queen.getCurrentPosition(), new Position(1,4));
  
      assertTrue(queen.isLegalMove(move));
    }

    @Test
    public void isLegalMove_WorksForDiagonalRightDown() {
      Queen queen = new Queen(Color.RED);
  
      Move move = new Move(queen.getCurrentPosition(), new Position(7,5));
  
      assertTrue(queen.isLegalMove(move));
    }

    @Test
    public void isLegalMove_WorksForDiagonalLeftDown() {
      Queen queen = new Queen(Color.RED);
  
      Move move = new Move(queen.getCurrentPosition(), new Position(1,5));
  
      assertTrue(queen.isLegalMove(move));
    }

    @Test
    public void isLegalMove_FailsForKnightward() {
      Queen queen = new Queen(Color.WHITE);
  
      Move move = new Move(queen.getCurrentPosition(), new Position(3,3));
  
      assertFalse(queen.isLegalMove(move));
    }
}
