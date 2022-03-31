package chess.pieces;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import chess.movement.*;

public class Bishop extends Piece {
  Bishop(Position intialPosition, Color color) {
    super(intialPosition, color);
  }

  public boolean isMoveLegal(Move move) {
    return (move.orientation == Move.Orientation.DIAGONAL);
  }

  public List<Move> getLegalMoves() {
        return Stream.of(getDiagonalEndpointsStream(Horizontals.RIGHT, Verticals.UP),
                          getDiagonalEndpointsStream(Horizontals.RIGHT, Verticals.DOWN),
                          getDiagonalEndpointsStream(Horizontals.LEFT, Verticals.UP),
                          getDiagonalEndpointsStream(Horizontals.LEFT, Verticals.DOWN))
                    .flatMap(s -> s)
                    .map(possiblePosition -> new Move(currentPosition, possiblePosition))
                    .collect(Collectors.toList());
  }
  
}
