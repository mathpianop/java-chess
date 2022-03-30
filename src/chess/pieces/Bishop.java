package chess.pieces;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import chess.movement.*;
import static chess.movement.Position.*;

public class Bishop extends Piece {
  Bishop(Position intialPosition, Color color) {
    super(intialPosition, color);
  }

  private Stream<Position> getEndpointsStream(Horizontals hd, Verticals vd) {
    return Stream.iterate(currentPosition, 
                    pos -> currentPosition.isOnBoard(), 
                    pos -> pos.getDiagonalShift(hd, vd))
                  .filter(pos -> pos.isOnBoard());
  }

  public List<Move> getLegalMoves() {
        return Stream.of(getEndpointsStream(Horizontals.RIGHT, Verticals.UP),
                  getEndpointsStream(Horizontals.RIGHT, Verticals.DOWN),
                  getEndpointsStream(Horizontals.LEFT, Verticals.UP),
                  getEndpointsStream(Horizontals.LEFT, Verticals.DOWN))
              .flatMap(s -> s)
              .map(possiblePosition -> new Move(currentPosition, possiblePosition))
              .collect(Collectors.toList());
  }
  
}
