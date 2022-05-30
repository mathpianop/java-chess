package chess.pieces;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import chess.movement.Move;
import chess.movement.Position;

public class PieceTest {

  class GenericPiece extends Piece {
    GenericPiece() {
      super(Color.RED, new Position(1,1));
    }
    public boolean isLegalMove(Move move) {
      return true;
    }

    public char getSymbol() {
      return 'a';
    }
  }

  @Test
  public void makeMove_ShiftsPiecePosition() {
    Piece piece = new GenericPiece();
    Move move = new Move(new Position(1,1), new Position(1,4));
    piece.makeMove(move);
    assertTrue(piece.getCurrentPosition().equals(new Position(1,4)));
  }

  @Test
  public void makeMove_ThrowsIllegalArgumentExceptionIfMoveNotFromCurrentPosition() {
    Piece piece = new GenericPiece();
    Move move = new Move(new Position(1,2), new Position(1,4));
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      piece.makeMove(move);
    });

    assertSame(exception.getMessage(), "Move must begin from piece's current position");
  }

  @Test
  public void makeMove_ThrowsIllegalArgumentExceptionIfMoveNotLegal() {
    Piece noLegalMovesPiece = new GenericPiece() {
      @Override
      public boolean isLegalMove(Move move) {
        return false;
      }
    };

    Move move = new Move(new Position(1,1), new Position(1,4));
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      noLegalMovesPiece.makeMove(move);
    });

    assertSame(exception.getMessage(), "Move must be legal");
  }

  @Test
  public void undoMove_RevertsToPreviousPosition() {
    Piece piece = new GenericPiece();
    Move move = new Move(new Position(1,1), new Position(1,4));
    piece.makeMove(move);
    assertTrue(piece.getCurrentPosition().equals(new Position(1,4)));
    piece.undoMove();
    assertTrue(piece.getCurrentPosition().equals(new Position(1,1)));
  }



  
}
