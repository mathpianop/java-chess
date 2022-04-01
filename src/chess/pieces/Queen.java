package chess.pieces;



import chess.movement.*;

public class Queen extends Piece {
  public Queen(Position intialPosition, Color color) {
    super(intialPosition, color);
  }

  public boolean isMoveLegal(Move move) {
    return (move.orientation == Move.Orientation.DIAGONAL ||
            move.orientation == Move.Orientation.INLINE);
  }
}
