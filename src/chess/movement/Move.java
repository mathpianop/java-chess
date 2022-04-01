package chess.movement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Move {
  public final Position startPos;
  public final Position endPos;
  public final Horizontals hd;
  public final Verticals vd;
  public final Orientation orientation;
  private boolean isCapture = false;

  public enum Orientation {
    INLINE, DIAGONAL, KNIGHT
  }

  public Move(Position startPos, Position endPos) throws NonExistantMoveException {

    this.startPos = startPos;
    this.endPos = endPos;

    if (!startPos.isOnBoard() || !endPos.isOnBoard()) {
      throw new NonExistantMoveException("Positions must be on the board");
    }

    this.hd = Position.horizontalDirection(startPos, endPos);
    this.vd = Position.verticalDirection(startPos, endPos);
    
    if(Position.areDiagonal(startPos, endPos)) {
      this.orientation = Orientation.DIAGONAL;
    } else if(Position.areInline(startPos, endPos)) {
      this.orientation = Orientation.INLINE;
    } else if(Position.areKnight(startPos, endPos)) {
      this.orientation = Orientation.KNIGHT;
    }
    else {
      throw new NonExistantMoveException("Move orientation must be diagonal, inline or knight");
    }

  }

  public List<Position> getMidpoints() {
    if (orientation == Orientation.DIAGONAL) {
      return getDiagonalMidpoints();
    } else if (orientation == Orientation.INLINE) {
      return getInlineMidpoints();
    } else {
      //Knight version, no midpoints
      return new ArrayList<Position>();
    }
  }

  private List<Position> getDiagonalMidpoints() {
    return Stream.iterate(startPos, 
                    pos -> pos.equals(endPos), 
                    pos -> pos.getDiagonalShift(hd, vd))
            .collect(Collectors.toList());

  }

  private List<Position> getInlineMidpoints() {
    return Stream.iterate(startPos, 
            pos -> pos.equals(endPos), 
            pos -> pos.getInlineShift(hd, vd))
    .collect(Collectors.toList());
  }

  public int distance() {
    return Position.distanceBetween(startPos, endPos);
  }

  public void setCapture() {
    isCapture = true;
  }

  public boolean isCapture() {
    return isCapture;
  }
  
}
