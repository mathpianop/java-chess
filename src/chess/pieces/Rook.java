package chess.pieces;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import chess.movement.*;

public class Rook extends Piece {
  Rook(Position intialPosition, Color color) {
    super(intialPosition, color);
  }

  public boolean isMoveLegal(Move move) {
    return (move.orientation == Move.Orientation.INLINE);
  }

  public List<Move> getLegalMoves() {
    return Stream.of(getInlineEndpointsStream(Horizontals.INLINE, Verticals.UP),
                      getInlineEndpointsStream(Horizontals.INLINE, Verticals.DOWN),
                      getInlineEndpointsStream(Horizontals.LEFT, Verticals.INLINE),
                      getInlineEndpointsStream(Horizontals.RIGHT, Verticals.INLINE))
                .flatMap(s -> s)
                .map(possiblePosition -> new Move(currentPosition, possiblePosition))
                .collect(Collectors.toList());
  }
}
