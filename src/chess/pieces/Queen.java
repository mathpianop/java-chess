package chess.pieces;



import chess.movement.*;

public class Queen extends Piece {
  char symbol; 
  public Queen(Color color) {
    super(color, getInitialPosition(color));
    this.symbol = (color == Color.RED ?  '♛' : '♕');
  }

  public char getSymbol() {
    return this.symbol;
  }

  static Position getInitialPosition(Color color) {
    return (color == Color.RED ? new Position(4,8) : new Position(4,1));
  }

  public boolean isLegalMove(Move move) {
    return (move.orientation == Move.Orientation.DIAGONAL ||
            move.orientation == Move.Orientation.INLINE);
  }
}
