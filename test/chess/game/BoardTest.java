package chess.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import chess.movement.Position;
import chess.pieces.Piece;

public class BoardTest {
  @Test
  public void getPieceAt_returnsNonEmptyOptionalForOccupiedPosition() {
    Board board = new Board();

    Optional<Piece> pieceBox = board.getPieceAt(new Position(1,1));

    assertEquals(pieceBox.get().toString(), "White Rook");
  }

  @Test
  public void getPieceAt_returnsEmptyOptionalForUnoccupiedPosition() {
    Board board = new Board();

    Optional<Piece> pieceBox = board.getPieceAt(new Position(1,4));

    assertFalse(pieceBox.isPresent());
  }

  @Test
  public void getPieceAt_returnsEmptyOptionalForSiteOfCapture() {
    Board board = new Board();

    Optional<Piece> pieceBox = board.getPieceAt(new Position(1,1));
    assertTrue(pieceBox.isPresent());

    pieceBox.get().setCaptured(true);

    pieceBox = board.getPieceAt(new Position(1,1));
    assertFalse(pieceBox.isPresent());
  }

  @Test
  public void getPieceAt_returnsTrueForOccupied() {
    Board board = new Board();    
    assertTrue(board.isOccupiedAt(new Position(1,1)));
  }

  @Test
  public void getPieceAt_returnsFalseForUnoccupied() {
    Board board = new Board();    
    assertFalse(board.isOccupiedAt(new Position(1,4)));
  }

  @Test
  public void getPieceAt_returnsFalseForUnoccupiedSiteOfCapture() {
    Board board = new Board();  
    Position pos = new Position(1,1);  
    assertTrue(board.isOccupiedAt(pos));
    board.getPieceAt(pos).get().setCaptured(true);
    assertFalse(board.isOccupiedAt(pos));
  }

  
}
