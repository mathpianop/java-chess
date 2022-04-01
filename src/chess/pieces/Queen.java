package chess.pieces;



import chess.movement.*;

public class Queen extends Piece {
  public Queen(Color color) {
    super(color, getInitialPosition(color));
  }

  static Position getInitialPosition(Color color) {
    return (color == Color.RED ? new Position(4,8) : new Position(4,1));
  }

  public boolean isMoveLegal(Move move) {
    return (move.orientation == Move.Orientation.DIAGONAL ||
            move.orientation == Move.Orientation.INLINE);
  }
}
