package chess.pieces;


import chess.movement.Move;
import chess.movement.Position;

public class King extends Piece {
  public King(Position intialPosition, Color color) {
    super(intialPosition, color);
  }

  private boolean isInlineOrDiagonal(Move move) {
    return (move.orientation == Move.Orientation.INLINE) ||
            (move.orientation == Move.Orientation.DIAGONAL);
  }

  public boolean isMoveLegal(Move move) {
    return isInlineOrDiagonal(move) && (move.distance() == 1);
  }


}
