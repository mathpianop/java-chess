package chess.game;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import chess.movement.Move;
import chess.movement.Position;
import chess.pieces.Color;
import chess.pieces.King;
import chess.pieces.Piece;

public class Check {

  Board board;
  Color color;

  public Check(Board board, Color threatenedColor) {
    this.board = board;
    this.color = threatenedColor;
  }
  
  

  boolean isInCheck() {
    List<Piece> pieces = board.getPieces();
    Piece king = pieces.stream()
                      .filter(piece -> piece.color == color)
                      .filter(King.class::isInstance)
                      .findAny()
                      .get();

    return pieces.stream()
                  .filter(piece -> piece.color == color.opposite())
                  .filter(piece -> !piece.isCaptured())
                  .map(piece -> new Play(board, piece, king.getCurrentPosition()))
                  .anyMatch(Play::isPermissible);
  }

  King getKing(List<Piece> pieces) {
    return pieces.stream()
                .filter(piece -> piece.color == color)
                .filter(King.class::isInstance)
                .map(piece -> (King) piece)
                .findAny()
                .get();
  }

  List<Piece> getCheckingPieces() {
    List<Piece> pieces = board.getPieces();
    Piece king = getKing(pieces);

    return pieces.stream()
                  .filter(piece -> piece.color == color.opposite())
                  .filter(piece -> !piece.isCaptured())
                  .filter(piece -> new Play(board, piece, king.getCurrentPosition()).isPermissible())
                  .collect(Collectors.toList());
  }

  

  private Stream<Play> getCounterAttacks(List<Piece> checkingPieces, Piece defendingPiece) {
    return checkingPieces.stream()
                .map(Piece::getCurrentPosition)
                .map(checkingPos -> new Play(board, defendingPiece, checkingPos));
  }

  private Stream<Play> getBlockingPlays(List<Piece> pieces, Position midpoint) {
    return pieces.stream()
                    .filter(piece -> piece.color == color)
                    .map(defendingPiece -> new Play(board, defendingPiece, midpoint));
  }



  boolean checkmate(List<Piece> checkingPieces) {
    List<Piece> pieces = board.getPieces();
    King king = getKing(pieces);

    boolean canEscape = king.getLegalEndPositions()
                            .stream()
                            .map(endPos -> new Play(board, king, endPos))
                            .anyMatch(Play::isPermissible);

    if (canEscape) return false;


    boolean canCaptureCheckingPiece = pieces.stream()
          .filter(piece -> piece.color == color)
          .flatMap(defendingPiece -> getCounterAttacks(checkingPieces, defendingPiece))
          .anyMatch(Play::isPermissible);

     
    if (canCaptureCheckingPiece) return false;

    boolean canBlockCheckingPiece = checkingPieces.stream()
                  .map(Piece::getCurrentPosition)
                  .map(checkingPos -> new Move(checkingPos, king.getCurrentPosition()))
                  .flatMap(move -> move.getMidpoints().stream())
                  .flatMap(midpoint -> getBlockingPlays(pieces, midpoint))
                  .anyMatch(Play::isPermissible);
    
    if (canBlockCheckingPiece) return false;

    return true;

  }
}
