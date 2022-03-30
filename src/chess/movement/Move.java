package chess.movement;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static chess.movement.Position.*;

public class Move {
  private Position startPos;
  private Position endPos;
  private Orientation orientation;

  enum Orientation {
    INLINE, DIAGONAL
  }

  public Move(Position startPos, Position endPos) {
    if (!startPos.isOnBoard() || !endPos.isOnBoard()) {
      throw new IllegalArgumentException("Positions must be on the board");
    }
    if(Position.areDiagonal(startPos, endPos)) {
      this.orientation = Orientation.DIAGONAL;
    } else if (Position.areInline(startPos, endPos)) {
      this.orientation = Orientation.INLINE;
    } else {
      throw new IllegalArgumentException("Postions must be diagonal or inline");
    }
  }

  public List<Position> getMidpoints() {
    if (orientation == Orientation.DIAGONAL) {
      return getDiagonalMidpoints();
    } else {
      return getInlineMidpoints();
    }
  }

  private List<Position> getDiagonalMidpoints() {
    Horizontals hd = 
            Position.horizontalDirection(startPos, endPos);
    Verticals vd = 
            Position.verticalDirection(startPos, endPos);
    
    return Stream.iterate(startPos, 
                    pos -> pos.equals(endPos), 
                    pos -> pos.getDiagonalShift(hd, vd))
            .collect(Collectors.toList());

  }

  private List<Position> getInlineMidpoints() {
    return List.of();
  }
  
}
