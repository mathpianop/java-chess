package chess.pieces;

import java.util.List;
import java.util.stream.Collectors;

import chess.movement.*;

public class Knight extends Piece {
  public Knight(Position intialPosition, Color color) {
    super(intialPosition, color);
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
