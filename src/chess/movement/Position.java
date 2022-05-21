package chess.movement;

public class Position {
  public final int xCoor;
  public final int yCoor;

  
  public Position(int xCoor, int yCoor) {
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

  static int distanceBetween(Position pos1, Position pos2) {
    return Math.max(
      Math.abs(pos1.xCoor - pos2.xCoor), 
      Math.abs(pos1.yCoor - pos2.yCoor)
    );
  }

  public boolean isOnBoard() {
    return xCoor <= 8 && xCoor >= 1 && yCoor <= 8 && yCoor >= 1;
  }

  public Position getDiagonalShift(Horizontals hd, Verticals vd) {
     //If either direction is inline, throw exception
     if ((hd == Horizontals.INLINE) || (vd == Verticals.INLINE)) {
      throw new IllegalArgumentException("Directions must not be inline");
    }

    //Return new Position shifted diagonally
    if (hd == Horizontals.RIGHT) {
      if (vd == Verticals.UP) {
        //RIGHT-UP
        return new Position(xCoor + 1, yCoor + 1);
      } else {
        //RIGHT-DOWN
        return new Position(xCoor + 1, yCoor - 1);
      }
    } else {
      if (vd == Verticals.UP) {
        //LEFT-UP
        return new Position(xCoor - 1, yCoor + 1);
      } else {
        //LEFT-DOWN
        return new Position(xCoor - 1, yCoor - 1);
      }
    }
  }

  public Position getInlineShift(Horizontals hd, Verticals vd) {

    //If neither or both directions is inline, throw exception
    if (!(hd == Horizontals.INLINE) && !(vd == Verticals.INLINE) || 
        (hd == Horizontals.INLINE) && (vd == Verticals.INLINE)) {
      throw new IllegalArgumentException("Exactly one direction must be inline");
    }

    //Return new Position shifted inline
    if (hd == Horizontals.INLINE) {
      if (vd == Verticals.UP) {
        //UP
        return new Position(xCoor, yCoor + 1);
      } else {
        //DOWN
        return new Position(xCoor, yCoor - 1);
      }
    } else {
      if (hd == Horizontals.RIGHT) {
        //RIGHT
        return new Position(xCoor + 1, yCoor);
      } else {
        //LEFT
        return new Position(xCoor - 1, yCoor);
      }
    }
  }

  //Wide version
  public Position getKnightShift(Horizontals twoSpace, Verticals oneSpace) {
    if (twoSpace == Horizontals.INLINE || oneSpace == Verticals.INLINE) {
      throw new IllegalArgumentException("Neither direction can be inline for a knight shift");
    }

    if (twoSpace == Horizontals.RIGHT) {
      if (oneSpace == Verticals.UP) {
        return new Position(xCoor + 2, yCoor + 1);
      } else {
        return new Position(xCoor + 2, yCoor - 1);
      }
    } else {
      if (oneSpace == Verticals.UP) {
        return new Position(xCoor - 2, yCoor + 1);
      } else {
        return new Position(xCoor - 2, yCoor - 1);
      }
    }
  }

  //Tall version
  public Position getKnightShift(Verticals twoSpace, Horizontals oneSpace) {
    if (twoSpace == Verticals.INLINE || oneSpace == Horizontals.INLINE) {
      throw new IllegalArgumentException("Neither direction can be inline for a knight shift");
    }

    if (twoSpace == Verticals.UP) {
      if (oneSpace == Horizontals.RIGHT) {
        return new Position(xCoor + 1, yCoor + 2);
      } else {
        return new Position(xCoor - 1, yCoor + 2);
      }
    } else {
      if (oneSpace == Horizontals.RIGHT) {
        return new Position(xCoor + 1, yCoor - 2);
      } else {
        return new Position(xCoor - 1, yCoor - 2);
      }
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