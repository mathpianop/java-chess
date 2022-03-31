package chess.pieces;
import java.util.List;
import java.util.stream.Stream;

import chess.movement.*;

public abstract class Piece {
  protected Position currentPosition;
  protected boolean captured;
  public final Color color;

  Piece(Position intialPosition, Color color) {
    this.currentPosition = intialPosition;
    this.color = color;
  }

  public abstract List<Move> getLegalMoves();
  public abstract boolean isMoveLegal(Move move);
  
  protected Stream<Position> getDiagonalEndpointsStream(Horizontals hd, Verticals vd) {
    return Stream.iterate(currentPosition, 
                    pos -> currentPosition.isOnBoard(), 
                    pos -> pos.getDiagonalShift(hd, vd));
  }

  protected Stream<Position> getInlineEndpointsStream(Horizontals hd, Verticals vd) {
    return Stream.iterate(currentPosition, 
                    pos -> currentPosition.isOnBoard(), 
                    pos -> pos.getInlineShift(hd, vd));
  }

}
