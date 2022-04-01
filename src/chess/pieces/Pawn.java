package chess.pieces;

import chess.movement.*;

public class Pawn extends Piece {
  public Pawn(Position intialPosition, Color color) {
    super(intialPosition, color);
  }

  private boolean isNonCaptureMove(Move move) {
    return move.hd == Horizontals.INLINE && move.vd == Verticals.UP;
  }

  private boolean isCaptureMove(Move move) {
    return (move.hd == Horizontals.RIGHT || move.hd == Horizontals.LEFT) &&
            move.vd == Verticals.UP &&
            move.isCapture();
  }

  public boolean isMoveLegal(Move move) {
   return isCaptureMove(move) || isNonCaptureMove(move);
  }
}
