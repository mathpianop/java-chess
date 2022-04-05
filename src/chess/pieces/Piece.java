package chess.pieces;

import chess.movement.*;

public abstract class Piece {
  protected Position currentPosition;
  protected boolean captured;
  public final Color color;
  private Move lastMove;

  public Piece(Color color, Position initialPosition) {
    this.currentPosition = initialPosition;
    this.color = color;
  }

  
  public void makeMove(Move move) {
    currentPosition = move.endPos;
    lastMove = move;
  }

  public void undoMove() {
    makeMove(new Move(lastMove.endPos, lastMove.startPos));
    lastMove = null;
  }

  public boolean isCaptured() {
    return captured;
  }

  public void setCaptured(boolean bool) {
    captured = bool;
  }

  public Position getCurrentPosition() {
    return this.currentPosition;
  }

  public String toString() {
    return this.color + " " + this.getClass().getName();
  }

  public abstract boolean isLegalMove(Move move);
  public abstract char getSymbol();
}
