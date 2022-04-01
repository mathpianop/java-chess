package chess.pieces;

import chess.movement.*;

public class Bishop extends Piece {
  public Bishop(Position intialPosition, Color color) {
    super(intialPosition, color);
  }

  public boolean isMoveLegal(Move move) {
    return (move.orientation == Move.Orientation.DIAGONAL);
  }

  
}
