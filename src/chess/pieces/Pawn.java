package chess.pieces;

import chess.movement.*;

public class Pawn extends Piece {
  char symbol;

  public Pawn(Color color, int column) {
    super(color, getInitialPosition(color, column));
    this.symbol = (color == Color.RED ?  '♟' : '♙');
  }

  public char getSymbol() {
    return this.symbol;
  }

  static Position getInitialPosition(Color color, int column) {
    if (column >= 1 && column <= 8) {
      return (color == Color.RED ? new Position(column,7) : new Position(column,2));
    } else {
      throw new IllegalArgumentException("Column outside of 1-8");
    }
  }

  private boolean isNonCaptureMove(Move move) {
    return move.hd == Horizontals.INLINE && move.vd == Verticals.UP;
  }

  private boolean isCaptureMove(Move move) {
    return (move.hd == Horizontals.RIGHT || move.hd == Horizontals.LEFT) &&
            move.vd == Verticals.UP &&
            move.isCapture();
  }

  public boolean isLegalMove(Move move) {
   return isCaptureMove(move) || isNonCaptureMove(move);
  }
}
