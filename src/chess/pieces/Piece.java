package chess.pieces;
import java.util.List;

import chess.movement.*;

public abstract class Piece {
  protected Position currentPosition;
  protected boolean captured;
  public final Color color;

  Piece(Position intialPosition, Color color) {
    this.currentPosition = intialPosition;
    this.color = color;
  }

  public abstract List<Move> getLegalMoves();

}
