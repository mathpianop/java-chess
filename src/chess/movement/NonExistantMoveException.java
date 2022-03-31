package chess.movement;

public class NonExistantMoveException extends RuntimeException {
  NonExistantMoveException(String s) {
    super(s);
  }

  NonExistantMoveException() {
    super();
  }
}
