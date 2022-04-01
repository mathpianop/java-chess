package chess.pieces;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import chess.movement.Horizontals;
import chess.movement.Move;
import chess.movement.Position;
import chess.movement.Verticals;

public class King extends Piece {
  public King(Color color) {
    super(color, getInitialPosition(color));
  }

  static Position getInitialPosition(Color color) {
    return (color == Color.RED ? new Position(5,8) : new Position(5,1));
  }

  private boolean isInlineOrDiagonal(Move move) {
    return (move.orientation == Move.Orientation.INLINE) ||
            (move.orientation == Move.Orientation.DIAGONAL);
  }

  public boolean isMoveLegal(Move move) {
    return isInlineOrDiagonal(move) && (move.distance() == 1);
  }

  public List<Move> getLegalMoves() {
    Stream<Position> stream = Stream.of(
      currentPosition.getInlineShift(Horizontals.INLINE, Verticals.UP),
      currentPosition.getInlineShift(Horizontals.INLINE, Verticals.DOWN),
      currentPosition.getInlineShift(Horizontals.RIGHT, Verticals.INLINE),
      currentPosition.getInlineShift(Horizontals.LEFT, Verticals.INLINE),
      currentPosition.getDiagonalShift(Horizontals.RIGHT, Verticals.UP),
      currentPosition.getDiagonalShift(Horizontals.RIGHT, Verticals.DOWN),
      currentPosition.getDiagonalShift(Horizontals.LEFT, Verticals.UP),
      currentPosition.getDiagonalShift(Horizontals.LEFT, Verticals.DOWN)
    );
    
    return stream.filter(Position::isOnBoard)
                  .map(pos -> new Move(currentPosition, pos))
                  .collect(Collectors.toList());
  }

}
