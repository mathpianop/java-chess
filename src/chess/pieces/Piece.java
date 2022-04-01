package chess.pieces;

import chess.movement.*;

public abstract class Piece {
  protected Position currentPosition;
  protected boolean captured;
  public final Color color;

  public Piece(Color color, Position initialPosition) {
    this.currentPosition = initialPosition;
    this.color = color;
  }

  public abstract boolean isMoveLegal(Move move);
  
  

}
