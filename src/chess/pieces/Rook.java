package chess.pieces;

import chess.movement.*;

public class Rook extends Piece {
  public Rook(Position intialPosition, Color color) {
    super(intialPosition, color);
  }

  public boolean isMoveLegal(Move move) {
    return (move.orientation == Move.Orientation.INLINE);
  }
}
