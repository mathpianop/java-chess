package chess.movement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;

import java.util.List;

import org.junit.Test;

public class MoveTest {

  //Constructor

  @Test
  public void constructorThrowsExceptionIfStartOffBoard() {
    assertThrows(NonExistantMoveException.class, () -> {
      new Move(new Position(0,2), new Position(1,3));
    });
  }

  @Test
  public void constructorThrowsExceptionIfEndOffBoard() {
    assertThrows(NonExistantMoveException.class, () -> {
      new Move(new Position(1,2), new Position(1,9));
    });
  }

  @Test
  public void constructorThrowsExceptionIfInvalidOrientation() {
    assertThrows(NonExistantMoveException.class, () -> {
      new Move(new Position(1,2), new Position(2,7));
    });
  }

  //getMidpoints  

  @Test
  public void getMidpoints_WorksForDiagonalUpRight() {
    Move move = new Move(new Position(2,3), new Position(5,6));
    List<Position> expectedMidpoints = List.of(
      new Position(3,4),
      new Position(4,5)
    );

    assertEquals(expectedMidpoints, move.getMidpoints());
  }

  @Test
  public void getMidpoints_WorksForDiagonalUpLeft() {
    Move move = new Move(new Position(5,3), new Position(2,6));
    List<Position> expectedMidpoints = List.of(
      new Position(4,4),
      new Position(3,5)
    );

    assertEquals(expectedMidpoints, move.getMidpoints());
  }

  @Test
  public void getMidpoints_WorksForDiagonalDownLeft() {
    Move move = new Move(new Position(5,6), new Position(2,3));
    List<Position> expectedMidpoints = List.of(
      new Position(4,5),
      new Position(3,4)
    );

    assertEquals(expectedMidpoints, move.getMidpoints());
  }

  @Test
  public void getMidpoints_WorksForDiagonalDownRight() {
    Move move = new Move(new Position(2,6), new Position(5,3));
    List<Position> expectedMidpoints = List.of(
      new Position(3,5),
      new Position(4,4)
    );

    assertEquals(expectedMidpoints, move.getMidpoints());
  }

  @Test
  public void getMidpoints_WorksForInlineRight() {
    Move move = new Move(new Position(2,6), new Position(5,6));
    List<Position> expectedMidpoints = List.of(
      new Position(3,6),
      new Position(4,6)
    );

    assertEquals(expectedMidpoints, move.getMidpoints());
  }

  @Test
  public void getMidpoints_WorksForInlineLeft() {
    Move move = new Move(new Position(5,6), new Position(2,6));
    List<Position> expectedMidpoints = List.of(
      new Position(4,6),
      new Position(3,6)
    );

    assertEquals(expectedMidpoints, move.getMidpoints());
  }

  @Test
  public void getMidpoints_WorksForInlineUp() {
    Move move = new Move(new Position(5,3), new Position(5,6));
    List<Position> expectedMidpoints = List.of(
      new Position(5,4),
      new Position(5,5)
    );

    assertEquals(expectedMidpoints, move.getMidpoints());
  }

  @Test
  public void getMidpoints_WorksForInlineDown() {
    Move move = new Move(new Position(5,6), new Position(5,3));
    List<Position> expectedMidpoints = List.of(
      new Position(5,5),
      new Position(5,4)
    );

    assertEquals(expectedMidpoints, move.getMidpoints());
  }

  @Test
  public void getMidpoints_WorksForKnight() {
    Move move = new Move(new Position(5,6), new Position(4,8));
    List<Position> expectedMidpoints = List.of();
    assertEquals(expectedMidpoints, move.getMidpoints());
  }

  
  
  
}
