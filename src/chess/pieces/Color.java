package chess.pieces;

public enum Color { 
  RED, WHITE;

  public Color opposite() {
    return (this == RED ? WHITE : RED);
  }
 }
