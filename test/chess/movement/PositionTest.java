package chess.movement;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class PositionTest {

  static Position pos1 = new Position(2,4);
  static Position pos2 = new Position(6,8);
  static Position pos3 = new Position(6,2);
  static Position pos4 = new Position(2,6);
  static Position pos5 = new Position(3,6);
  static Position pos6 = new Position(8, 5);


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
    assertTrue(
      Position.horizontalDirection(pos3, pos2) == Horizontals.INLINE
    );
  }

  @Test
  public void horizontalDirection_ReturnsRIGHTForRight() {
    assertTrue(
      Position.horizontalDirection(pos1, pos2) == Horizontals.RIGHT
    );
  }

  @Test
  public void horizontalDirection_ReturnsLEFTForLeft() {
    assertTrue(
      Position.horizontalDirection(pos2, pos1) == Horizontals.LEFT
    );
  }

  //static verticalDirection

  @Test
  public void verticalDirection_ReturnsINLINEForInline() {
    assertTrue(
      Position.verticalDirection(pos4, pos5) == Verticals.INLINE
    );
  }

  @Test
  public void verticalDirection_ReturnsUPForUp() {
    assertTrue(
      Position.verticalDirection(pos1, pos2) == Verticals.UP
    );
  }

  @Test
  public void verticalDirection_ReturnsDOWNForUp() {
    assertTrue(
      Position.verticalDirection(pos2, pos3) == Verticals.DOWN
    );
  }

  //static distanceBetween

  @Test
  public void distanceBetween_ReturnsXDifferenceIfBigger() {
    assertTrue(Position.distanceBetween(pos1, pos6) == 6);
  }

  @Test
  public void distanceBetween_ReturnsYDifferenceIfBigger() {
    assertTrue(Position.distanceBetween(pos1, pos5) == 2);
  }

  @Test
  public void distanceBetween_WorksForHorizontal() {
    assertTrue(Position.distanceBetween(pos4, pos5) == 1);
  }

  @Test
  public void distanceBetween_WorksForVertical() {
    assertTrue(Position.distanceBetween(pos2, pos3) == 6);
  }

  //isOnBoard

  @Test
  public void isOnBoard_ReturnsTrueIfOnBoard() {
    assertTrue(pos1.isOnBoard());
  }

  @Test
  public void isOnBoard_ReturnsFalseIfToFarToTheRight() {
    assertFalse(new Position(9,5).isOnBoard());
  }

  @Test
  public void isOnBoard_ReturnsFalseIfToFarToTheLeft() {
    assertFalse(new Position(0,8).isOnBoard());
  }
  
  @Test
  public void isOnBoard_ReturnsFalseIfToFarUp() {
    assertFalse(new Position(1,9).isOnBoard());
  }

  @Test
  public void isOnBoard_ReturnsFalseIfToFarDown() {
    assertFalse(new Position(1,0).isOnBoard());
  }


  //getDiagonalShift

  @Test
  public void getDiagonalShift_WorksForRightUp() {
    assertTrue(pos1.getDiagonalShift(Horizontals.RIGHT, Verticals.UP).equals(
      new Position(3,5)
    ));
  }

  @Test
  public void getDiagonalShift_WorksForRightDown() {
    assertTrue(pos1.getDiagonalShift(Horizontals.RIGHT, Verticals.DOWN).equals(
      new Position(3,3)
    ));
  }

  @Test
  public void getDiagonalShift_WorksForLeftDown() {
    assertTrue(pos1.getDiagonalShift(Horizontals.LEFT, Verticals.DOWN).equals(
      new Position(1,3)
    ));
  }

  @Test
  public void getDiagonalShift_WorksForLeftUp() {
    assertTrue(pos1.getDiagonalShift(Horizontals.LEFT, Verticals.UP).equals(
      new Position(1,5)
    ));
  }

  @Test
  public void getDiagonalShift_ThrowsExceptionIfEitherDirectionHorizontal() {
    Exception exception1 = assertThrows(IllegalArgumentException.class, () -> {
      pos1.getDiagonalShift(Horizontals.INLINE, Verticals.UP);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      pos1.getDiagonalShift(Horizontals.RIGHT, Verticals.INLINE);
    });

    assertTrue(("Directions must not be inline".equals(exception1.getMessage())));
  }

  @Test
  public void getInlineShift_WorksForRight() {
    assertTrue(pos1.getInlineShift(Horizontals.RIGHT, Verticals.INLINE).equals(
      new Position(3,4)
    ));
  }

  @Test
  public void getInlineShift_WorksForLeft() {
    assertTrue(pos1.getInlineShift(Horizontals.LEFT, Verticals.INLINE).equals(
      new Position(1,4)
    ));
  }

  @Test
  public void getInlineShift_WorksForDown() {
    assertTrue(pos1.getInlineShift(Horizontals.INLINE, Verticals.DOWN).equals(
      new Position(2,3)
    ));
  }

  @Test
  public void getInlineShift_WorksForUp() {
    assertTrue(pos1.getInlineShift(Horizontals.INLINE, Verticals.UP).equals(
      new Position(2,5)
    ));
  }

  @Test
  public void getInlineShift_ThrowsExceptionIfNeitherDirectionInline() {
    assertThrows(IllegalArgumentException.class, () -> {
      pos1.getInlineShift(Horizontals.RIGHT, Verticals.UP);
    });
  }

  @Test
  public void getInlineShift_ThrowsExceptionIfBothDirectionsInline() {
    assertThrows(IllegalArgumentException.class, () -> {
      pos1.getInlineShift(Horizontals.INLINE, Verticals.INLINE);
    });
  }

  @Test
  public void getKnightShift_WorksForWideVersions() {
    assertTrue(pos5.getKnightShift(Horizontals.RIGHT, Verticals.UP).equals(
      new Position(5,7)
    ));

    assertTrue(pos5.getKnightShift(Horizontals.LEFT, Verticals.UP).equals(
      new Position(1,7)
    ));

    assertTrue(pos5.getKnightShift(Horizontals.RIGHT, Verticals.DOWN).equals(
      new Position(5,5)
    ));

    assertTrue(pos5.getKnightShift(Horizontals.LEFT, Verticals.DOWN).equals(
      new Position(1,5)
    ));
  }

  @Test
  public void getKnightShift_WorksForTallVersions() {
    assertTrue(pos5.getKnightShift(Verticals.UP, Horizontals.RIGHT).equals(
      new Position(4,8)
    ));

    assertTrue(pos5.getKnightShift(Verticals.DOWN, Horizontals.RIGHT).equals(
      new Position(4,4)
    ));

    assertTrue(pos5.getKnightShift(Verticals.UP, Horizontals.LEFT).equals(
      new Position(2,8)
    ));

    assertTrue(pos5.getKnightShift(Verticals.DOWN, Horizontals.LEFT).equals(
      new Position(2,4)
    ));
  }

  @Test
  public void getInlineShift_ThrowsExceptionIfEitherDirectionsInline() {
    assertThrows(IllegalArgumentException.class, () -> {
      pos1.getKnightShift(Horizontals.INLINE, Verticals.UP);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      pos1.getKnightShift(Horizontals.RIGHT, Verticals.INLINE);
    });
  }



  //equals

  @Test
  public void equals_ReturnsTrueForSameObject() {
    assertTrue(pos1.equals(pos1));
  }

  @Test
  public void equals_ReturnsTrueForDifferentObjectSameCoors() {
    assertTrue(pos1.equals(new Position(2,4)));
  }

  @Test
  public void equals_ReturnsFalseForDifferentObjectDifferentCoors() {
    assertFalse(pos1.equals(new Position(1,4)));
  }



}
