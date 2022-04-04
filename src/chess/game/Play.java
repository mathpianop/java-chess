package chess.game;

import java.util.Optional;

import chess.movement.*;
import chess.pieces.Piece;

public class Play {
  Board board;
  Piece piece;
  Optional<Piece> targetPiece;
  Position endPos;
  Move move;
  String reason;
  
  

  Play(Board board, Piece piece, Position endPos) {
    this.board = board;
    this.piece = piece;
    this.endPos = endPos;
    this.targetPiece = findTargetPiece();

    try {
      this.move = new Move(piece.getCurrentPosition(), endPos);
      targetPiece.ifPresent(p -> move.setCapture());
    } catch(NonExistantMoveException e) {
      this.reason = e.getMessage();
    }
  }

  private Optional<Piece> findTargetPiece() {
    return this.board.getPieceAt(endPos)
              .filter(endPiece -> endPiece.color == piece.color.opposite());
  }

  private boolean isValidMove() {
    return (move != null);
  }

  private boolean midpointsClear() {
    return move.getMidpoints().stream().anyMatch(board::isOccupiedAt);
  }

  private boolean friendlyPieceOnEndpoint() {
    if (targetPiece.isPresent()) {
      return targetPiece.get().color == piece.color;
    } else {
      return false;
    }
  }

  private boolean isClear() {
    return midpointsClear() && !friendlyPieceOnEndpoint();
  }

  private boolean isSafe() {
    boolean safe;
    makePlay();
    safe = !Check.isInCheck(board, piece.color);
    reset();
    return safe;
  }

  boolean isPermissible() {
    return isValidMove() && piece.isLegalMove(move) && isClear() && isSafe();
  }

  public void makePlay() {
    piece.makeMove(move);
    targetPiece.ifPresent(p -> {
      p.setCaptured(true);
    });
  }

  public void reset() {
    piece.makeMove(move.reverse());
    targetPiece.ifPresent(p -> {
      p.setCaptured(false);
    });
  }

  public Optional<Move> getMove() {
    return Optional.ofNullable(move);
  }

  String getReason() {
    return reason;
  }
}
