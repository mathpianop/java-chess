package chess.game;

import java.util.List;

import chess.movement.Move;
import chess.movement.NonExistantMoveException;
import chess.movement.Position;
import chess.pieces.Color;
import chess.pieces.King;
import chess.pieces.Piece;

public class Check {
  private static boolean isLegalMove(Piece piece, Position endPos) {
    Move move;
    try {
      move = new Move(piece.getCurrentPosition(), endPos);
    } catch (NonExistantMoveException e) {
      return false;
    }

    move.setCapture();
    return piece.isLegalMove(move);
  } 

  static boolean isInCheck(Board board, Color color) {
    List<Piece> pieces = board.getPieces();
    Piece king = pieces.stream()
                      .filter(piece -> piece.color == color)
                      .filter(King.class::isInstance)
                      .findAny()
                      .get();

    return pieces.stream()
                  .filter(piece -> piece.color == color.opposite())
                  .anyMatch(piece -> isLegalMove(piece, king.getCurrentPosition()));
  }

  static boolean checkmate(Board board, Color threatenedColor) {

  }
}
