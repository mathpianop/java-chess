package chess.game;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

  static King getKing(List<Piece> pieces, Color color) {
    return pieces.stream()
                .filter(piece -> piece.color == color)
                .filter(King.class::isInstance)
                .map(piece -> (King) piece)
                .findAny()
                .get();
  }

  static List<Piece> getCheckingPieces(Board board, Color color) {
    List<Piece> pieces = board.getPieces();
    Piece king = getKing(pieces, color);

    return pieces.stream()
                  .filter(piece -> piece.color == color.opposite())
                  .filter(piece -> isLegalMove(piece, king.getCurrentPosition()))
                  .collect(Collectors.toList());
  }

  

  static Stream<Play> getCounterAttacks(Board board, List<Piece> checkingPieces, Piece defendingPiece) {
    return checkingPieces.stream()
                .map(Piece::getCurrentPosition)
                .map(checkingPos -> new Play(board, defendingPiece, checkingPos));
  }

  static Stream<Play> getBlockingPlays(Board board, List<Piece> pieces, Color threatenedColor, Position midpoint) {
    return pieces.stream()
                    .filter(piece -> piece.color == threatenedColor)
                    .map(defendingPiece -> new Play(board, defendingPiece, midpoint));
  }



  static boolean checkmate(Board board, Color threatenedColor, List<Piece> checkingPieces) {
    List<Piece> pieces = board.getPieces();
    King king = getKing(pieces, threatenedColor);
    
    boolean canEscape = king.getLegalEndPositions()
                            .stream()
                            .map(endPos -> new Play(board, king, endPos))
                            .anyMatch(Play::isPermissible);

    if (canEscape) return false;


    boolean canCaptureCheckingPiece = pieces.stream()
          .filter(piece -> piece.color == threatenedColor)
          .flatMap(defendingPiece -> getCounterAttacks(board, checkingPieces, defendingPiece))
          .anyMatch(Play::isPermissible);

    if (canCaptureCheckingPiece) return false;

    boolean canBlockCheckingPiece = checkingPieces.stream()
                  .map(Piece::getCurrentPosition)
                  .map(checkingPos -> new Move(checkingPos, king.getCurrentPosition()))
                  .flatMap(move -> move.getMidpoints().stream())
                  .flatMap(midpoint -> getBlockingPlays(board, pieces, threatenedColor, midpoint))
                  .anyMatch(Play::isPermissible);
    
    if (canBlockCheckingPiece) return false;

    return true;

  }
}
