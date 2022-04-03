package chess.pieces;

import chess.movement.*;

public class Bishop extends Piece {
  char symbol;

  public Bishop(Color color, int column) {
    super(color, getInitialPosition(color, column));
    this.symbol = (color == Color.RED ?  '♝' : '♗');
  }

  static Position getInitialPosition(Color color, int column) {
    if (column == 3) {
      return (color == Color.RED ? new Position(3,8) : new Position(3,1));
    } else if (column == 6) {
      return (color == Color.RED ? new Position(6,8) : new Position(6,1));
    } else {
      throw new IllegalArgumentException("Bishop must be placed on columns 3 or 6");
    }
  }

  public char getSymbol() {
    return this.symbol;
  }

  public boolean isLegalMove(Move move) {
    return (move.orientation == Move.Orientation.DIAGONAL);
  }

  
}
