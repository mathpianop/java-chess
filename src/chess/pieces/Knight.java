package chess.pieces;

import java.util.List;
import java.util.stream.Collectors;

import chess.movement.*;

public class Knight extends Piece {
  public Knight(Color color, int column) {
    super(color, getInitialPosition(color, column));
  }

  static Position getInitialPosition(Color color, int column) {
    if (column == 2) {
      return (color == Color.RED ? new Position(2,8) : new Position(2,1));
    } else if (column == 7) {
      return (color == Color.RED ? new Position(7,8) : new Position(7,1));
    } else {
      throw new IllegalArgumentException("Knight must be placed on columns 2 or 7");
    }
  }

  public boolean isMoveLegal(Move move) {
    return (move.orientation == Move.Orientation.KNIGHT);
  }

  public List<Move> getLegalMoves() {
    List<Position> allEightKnightEndPositions = List.of(
      currentPosition.getKnightShift(Horizontals.RIGHT, Verticals.UP),
      currentPosition.getKnightShift(Horizontals.RIGHT, Verticals.DOWN),
      currentPosition.getKnightShift(Horizontals.LEFT, Verticals.UP),
      currentPosition.getKnightShift(Horizontals.LEFT, Verticals.DOWN),
      currentPosition.getKnightShift(Verticals.UP, Horizontals.RIGHT),
      currentPosition.getKnightShift(Verticals.UP, Horizontals.LEFT),
      currentPosition.getKnightShift(Verticals.DOWN, Horizontals.RIGHT),
      currentPosition.getKnightShift(Verticals.DOWN, Horizontals.LEFT)
    );

    return allEightKnightEndPositions.stream()
                                    .filter(Position::isOnBoard)
                                    .map(pos -> new Move(currentPosition, pos))
                                    .collect(Collectors.toList());
    
  }
}
