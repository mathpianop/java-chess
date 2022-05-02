package chess.movement;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class PositionTest {

  static Position pos1 = new Position(2,4);
  static Position pos2 = new Position(6,8);
  static Position pos3 = new Position(6,2);
  static Position pos4 = new Position(2,6);


  //static areDiagonal
  @Test
  public void areDiagonal_WorksRightUp() {
    assertTrue(Position.areDiagonal(pos1, pos2));
  }

  @Test
  public void areDiagonal_WorksRightDown() {
    assertTrue(Position.areDiagonal(pos4, pos3));
  }

  @Test
  public void areDiagonal_WorksLeftUp() {
    assertTrue(Position.areDiagonal(pos2, pos1));
  }

  @Test
  public void areDiagonal_WorksLeftDown() {
    assertTrue(Position.areDiagonal(pos3, pos4));
  }

  @Test
  public void areDiagonal_FailsForVertical() {
    assertFalse(Position.areDiagonal(pos2, pos3));
  }

  @Test
  public void areDiagonal_FailsForHorizontal() {
    assertFalse(Position.areDiagonal(pos4, pos1));
  }

  @Test
  public void areDiagonal_FailsForNot45Degree() {
    assertFalse(Position.areDiagonal(pos4, pos2));
  }



  //static areInline
  @Test
  public void areInline_WorksForVertical() {
    assertTrue(Position.areInline(pos2, pos3));
  }

  @Test
  public void areInline_WorksForHorizontal() {
    assertTrue(Position.areInline(pos4, pos1));
  }

  @Test
  public void areInline_FailsForDiagonal() {
    assertFalse(Position.areInline(pos1, pos2));
  }

  @Test
  public void areInline_FailsForNot45Degree() {
    assertFalse(Position.areInline(pos4, pos2));
  }


  //static areKnight

  static Position startPos = new Position(4,4);
  static Position rightUpTPos = new Position(5,6);
  static Position rightUpLPos = new Position(6,5);
  static Position rightDownTPos = new Position(5,2);
  static Position rightDownLPos = new Position(6,3);
  static Position leftUpTPos = new Position(3,6);
  static Position leftUpLPos = new Position(2,5);
  static Position leftDownTPos = new Position(3,2);
  static Position leftDownLPos = new Position(2,3);

  @Test
  public void areKnight_FailsForVertical() {
    assertFalse(Position.areKnight(pos2, pos3));
  }

  @Test
  public void areKnight_FailsForHorizontal() {
    assertFalse(Position.areKnight(pos4, pos1));
  }

  @Test
  public void areKnight_FailsForDiagonal() {
    assertFalse(Position.areKnight(pos1, pos2));
  }

  @Test
  public void areKnight_WorksForRightUpT() {
    assertTrue(Position.areKnight(startPos, rightUpTPos));
  }

  @Test
  public void areKnight_WorksForRightUpL() {
    assertTrue(Position.areKnight(startPos, rightUpLPos));
  }

  @Test
  public void areKnight_WorksForRightDownT() {
    assertTrue(Position.areKnight(startPos, rightDownTPos));
  }

  @Test
  public void areKnight_WorksForRightDownL() {
    assertTrue(Position.areKnight(startPos, leftDownLPos));
  }

  @Test
  public void areKnight_WorksForLeftUpT() {
    assertTrue(Position.areKnight(startPos, leftUpTPos));
  }

  @Test
  public void areKnight_WorksForLeftUpL() {
    assertTrue(Position.areKnight(startPos, leftUpLPos));
  }

  @Test
  public void areKnight_WorksForLeftDownT() {
    assertTrue(Position.areKnight(startPos, leftDownTPos));
  }

  @Test
  public void areKnight_WorksForLeftDownL() {
    assertTrue(Position.areKnight(startPos, leftDownLPos));
  }


  //static horizontalDirection

  @Test
  public void horizontalDirection_ReturnsINLINEForInline() {
    
  }

  

}
