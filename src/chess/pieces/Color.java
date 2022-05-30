package chess.pieces;

public enum Color { 
  RED {
    public String toString() {
      return "Red";
    }
  }, WHITE {
    public String toString() {
      return "White";
    }
  };

  public Color opposite() {
    return (this == RED ? WHITE : RED);
  }
 }
