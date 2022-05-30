package chess.pieces;

import chess.movement.*;

public abstract class Piece {
  protected Position currentPosition;
  protected boolean captured;
  public final Color color;
  private Move lastMove;
  public String rank = "Piece";

  public Piece(Color color, Position initialPosition) {
    this.currentPosition = initialPosition;
    this.color = color;
  }

  
  public void makeMove(Move move) {
    if (!currentPosition.equals(move.startPos)) {
      throw new IllegalArgumentException("Move must begin from piece's current position");
    }

    if (!isLegalMove(move)) {
      throw new IllegalArgumentException("Move must be legal");
    }
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
    return this.color + " " + this.getClass().getSimpleName();
  }

  public abstract boolean isLegalMove(Move move);
  public abstract char getSymbol();
}
