package chess.pieces;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import chess.movement.Move;
import chess.movement.Position;

public class KnightTest {

  @Test
  public void constructorThrowsExceptionIfNotAValidPlacement() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Knight(Color.RED, 5);
    });
  }

  
  @Test
  public void isLegalMove_FailsForDiagonal() {
    Knight knight = new Knight(Color.RED, 2);
  
      Move move = new Move(knight.getCurrentPosition(), new Position(1,7));
  
      assertFalse(knight.isLegalMove(move));
      
  }

  @Test
  public void isLegalMove_FailsForInline() {
    Knight knight = new Knight(Color.RED, 2);
  
      Move move = new Move(knight.getCurrentPosition(), new Position(2,7));
  
      assertFalse(knight.isLegalMove(move));
      
  }

  @Test
  public void isLegalMove_WorksForKnightRightUpTall() {
    Knight knight = new Knight(Color.WHITE, 2);
  
      Move move = new Move(knight.getCurrentPosition(), new Position(3,3));
  
      assertTrue(knight.isLegalMove(move));
      
  }

  @Test
  public void isLegalMove_WorksForKnightRightUpWide() {
    Knight knight = new Knight(Color.WHITE, 2);
  
      Move move = new Move(knight.getCurrentPosition(), new Position(4,2));
  
      assertTrue(knight.isLegalMove(move));
      
  }

  @Test
  public void isLegalMove_WorksForKnightLeftUpTall() {
    Knight knight = new Knight(Color.WHITE, 7);
  
      Move move = new Move(knight.getCurrentPosition(), new Position(6,3));
  
      assertTrue(knight.isLegalMove(move));
      
  }

  @Test
  public void isLegalMove_WorksForKnightLeftUpWide() {
    Knight knight = new Knight(Color.WHITE, 7);
  
      Move move = new Move(knight.getCurrentPosition(), new Position(5,2));
  
      assertTrue(knight.isLegalMove(move));
      
  }

  @Test
  public void isLegalMove_WorksForKnightRightDownTall() {
    Knight knight = new Knight(Color.RED, 2);
  
      Move move = new Move(knight.getCurrentPosition(), new Position(3,6));
  
      assertTrue(knight.isLegalMove(move));
      
  }

  @Test
  public void isLegalMove_WorksForKnightRightDownWide() {
    Knight knight = new Knight(Color.RED, 2);
  
      Move move = new Move(knight.getCurrentPosition(), new Position(4,7));
  
      assertTrue(knight.isLegalMove(move));
      
  }

  @Test
  public void isLegalMove_WorksForKnightLeftDownTall() {
    Knight knight = new Knight(Color.RED, 7);
  
      Move move = new Move(knight.getCurrentPosition(), new Position(6,6));
  
      assertTrue(knight.isLegalMove(move));
      
  }

  @Test
  public void isLegalMove_WorksForKnightLeftDownWide() {
    Knight knight = new Knight(Color.RED, 7);
  
      Move move = new Move(knight.getCurrentPosition(), new Position(5,7));
  
      assertTrue(knight.isLegalMove(move));
      
  }

  
}
