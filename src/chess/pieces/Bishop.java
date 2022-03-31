package chess.pieces;
import java.util.List;
import java.util.stream.Stream;
import chess.movement.*;

public class Bishop extends Piece {
  public Bishop(Position intialPosition, Color color) {
    super(intialPosition, color);
  }

  public boolean isMoveLegal(Move move) {
    return (move.orientation == Move.Orientation.DIAGONAL);
  }

  public List<Move> getLegalMoves() {
    Stream<Stream<Position>> streamOfStreams = Stream.of(getDiagonalEndpointsStream(Horizontals.RIGHT, Verticals.UP),
                          getDiagonalEndpointsStream(Horizontals.RIGHT, Verticals.DOWN),
                          getDiagonalEndpointsStream(Horizontals.LEFT, Verticals.UP),
                          getDiagonalEndpointsStream(Horizontals.LEFT, Verticals.DOWN));
    
    return getMovesFromEndpointsStream(streamOfStreams);
  }
  
}
