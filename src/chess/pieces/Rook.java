package chess.pieces;

import chess.movement.*;

public class Rook extends Piece {
  char symbol;

  public Rook(Color color, int column) {
    super(color, getInitialPosition(color, column));
    this.symbol = (color == Color.RED ?  '♜' : '♖');
  }

  public char getSymbol() {
    return this.symbol;
  }

  static Position getInitialPosition(Color color, int column) {
    if (column == 1) {
      return (color == Color.RED ? new Position(1,8) : new Position(1,1));
    } else if (column == 8) {
      return (color == Color.RED ? new Position(8,8) : new Position(8,1));
    } else {
      throw new IllegalArgumentException("Rook must be placed on columns 1 or 8");
    }
  }

  public boolean isMoveLegal(Move move) {
    return (move.orientation == Move.Orientation.INLINE);
  }
}
