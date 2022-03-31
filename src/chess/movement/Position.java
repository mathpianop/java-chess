package chess.movement;

public class Position {
  private final int xCoor;
  private final int yCoor;

  
  

  Position(int xCoor, int yCoor) {
    this.xCoor = xCoor;
    this.yCoor = yCoor;
  }

  static boolean areDiagonal(Position pos1, Position pos2) {
    return Math.abs(pos1.xCoor - pos2.xCoor) == Math.abs(pos1.yCoor - pos2.yCoor);
  }

  static boolean areInline(Position pos1, Position pos2) {
    return pos1.xCoor == pos2.xCoor || pos1.yCoor == pos2.yCoor;
  }

  static boolean areKnight(Position pos1, Position pos2) {
    return Math.abs(pos1.xCoor - pos2.xCoor) == 1 && 
            Math.abs(pos1.yCoor - pos2.yCoor) == 2 || 
            Math.abs(pos1.xCoor - pos2.xCoor) == 2 && 
            Math.abs(pos1.yCoor - pos2.yCoor) == 1;
  }

  static Horizontals horizontalDirection(Position pos1, Position pos2) {
    if (pos1.xCoor < pos2.xCoor) {
      return Horizontals.RIGHT;
    } else if (pos1.xCoor > pos2.xCoor) {
      return Horizontals.LEFT;
    } else {
      return Horizontals.INLINE;
    }
  }

  static Verticals verticalDirection(Position pos1, Position pos2) {
    if (pos1.yCoor < pos2.yCoor) {
      return Verticals.UP;
    } else if (pos1.yCoor > pos2.yCoor) {
      return Verticals.DOWN;
    } else {
      return Verticals.INLINE;
    }
  }

  public boolean isOnBoard() {
    return xCoor <= 8 && xCoor >= 1 && yCoor <= 8 && yCoor >= 1;
  }

  public Position getDiagonalShift(Horizontals hd, Verticals vd) {
    //Return new Position shifted diagonally
    if (hd == Horizontals.RIGHT) {
      if (vd == Verticals.UP) {
        //RIGHT-UP
        return new Position(xCoor + 1, yCoor + 1);
      } else if (vd == Verticals.DOWN) {
        //RIGHT-DOWN
        return new Position(xCoor + 1, yCoor - 1);
      } else {
        throw new IllegalArgumentException("Directions not diagonal");
      }
    } else if (hd == Horizontals.LEFT) {
      if (vd == Verticals.UP) {
        //LEFT-UP
        return new Position(xCoor - 1, yCoor + 1);
      } else if (vd == Verticals.DOWN) {
        //LEFT-DOWN
        return new Position(xCoor - 1, yCoor - 1);
      } else {
        throw new IllegalArgumentException("Directions not diagonal");
      }
    } else {
      throw new IllegalArgumentException("Directions not diagonal");
    }
  }

  public Position getInlineShift(Horizontals hd, Verticals vd) {
    //Return new Position shifted inline
    if (hd == Horizontals.INLINE) {
      if (vd == Verticals.UP) {
        //UP
        return new Position(xCoor, yCoor + 1);
      } else if (vd == Verticals.DOWN) {
        //DOWN
        return new Position(xCoor, yCoor - 1);
      } else {
        throw new IllegalArgumentException("Directions not inline");
      }
    } else if (vd == Verticals.INLINE) {
      if (hd == Horizontals.RIGHT) {
        //RIGHT
        return new Position(xCoor + 1, yCoor);
      } else if (hd == Horizontals.LEFT) {
        //LEFT
        return new Position(xCoor - 1, yCoor);
      } else {
        throw new IllegalArgumentException("Directions not inline");
      }
    } else {
      throw new IllegalArgumentException("Directions not inline");
    }
  }

  public boolean equals(Object o) {
    if (o instanceof Position ) {
      Position p = (Position) o;
      return (p.xCoor == xCoor && p.yCoor == yCoor) ? true : false;
    } else {
      return false;
    }
  }
}
