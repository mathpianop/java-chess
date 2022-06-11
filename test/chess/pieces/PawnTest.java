package chess.pieces;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import chess.movement.Move;
import chess.movement.Position;

public class PawnTest {
    @Test
    public void isLegalMove_WorksForForwardOneWhite() {
      Pawn pawn = new Pawn(Color.WHITE, 1);
  
      Move move = new Move(pawn.getCurrentPosition(), new Position(1,3));
  
      assertTrue(pawn.isLegalMove(move));
    }

    @Test
    public void isLegalMove_WorksForForwardOneRed() {
      Pawn pawn = new Pawn(Color.RED, 1);
  
      Move move = new Move(pawn.getCurrentPosition(), new Position(1,6));
  
      assertTrue(pawn.isLegalMove(move));
    }

    

    @Test
    public void isLegalMove_WorksForForwardTwoIfFirstMove() {
      Pawn pawn = new Pawn(Color.WHITE, 1);
  
      Move move = new Move(pawn.getCurrentPosition(), new Position(1,4));
  
      assertTrue(pawn.isLegalMove(move));
    }

    @Test
    public void isLegalMove_FailsForForwardTwoIfNotFirstMove() {
      Pawn pawn = new Pawn(Color.WHITE, 1);
  
      Move firstMove = new Move(pawn.getCurrentPosition(), new Position(1,4));
      pawn.makeMove(firstMove);

      Move nextMove = new Move(pawn.getCurrentPosition(), new Position(1,6));
  
      assertFalse(pawn.isLegalMove(nextMove));
    }

    @Test
    public void isLegalMove_FailsForDiagonalOneIfNotCapture() {
      Pawn pawn = new Pawn(Color.WHITE, 1);
      Move move = new Move(pawn.getCurrentPosition(), new Position(2,3));
  
      assertFalse(pawn.isLegalMove(move));
    }

    @Test
    public void isLegalMove_WorksForDiagonalOneIfCaptureWhite() {
      Pawn pawn = new Pawn(Color.WHITE, 1);
      Move move = new Move(pawn.getCurrentPosition(), new Position(2,3));
      move.setCapture();

      assertTrue(pawn.isLegalMove(move));
    }

    @Test
    public void isLegalMove_WorksForDiagonalOneIfCaptureRed() {
      Pawn pawn = new Pawn(Color.RED, 1);
      Move move = new Move(pawn.getCurrentPosition(), new Position(2,6));
      move.setCapture();

      assertTrue(pawn.isLegalMove(move));
    }

    @Test
    public void isLegalMove_FailsForDiagonalTwoIfEvenIfCapture() {
      Pawn pawn = new Pawn(Color.WHITE, 1);
      Move move = new Move(pawn.getCurrentPosition(), new Position(2,4));
      move.setCapture();
      assertFalse(pawn.isLegalMove(move));
    }
}
