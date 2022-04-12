package chess.game;

import java.util.Optional;

import chess.movement.*;
import chess.pieces.Piece;

public class Play {
  final Board board;
  final Piece piece;
  final Optional<Piece> targetPiece;
  final Position endPos;
  private Move move;
  private String reason;
  
  

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
    //Reason already supplied by exception catch in constructor
    return (move != null);
  }

  private boolean isLegalMove() {
    boolean isLegalMove = piece.isLegalMove(move);
    if (!isLegalMove) reason = "That is not a valid move for that piece";
    return isLegalMove;
  }

  private boolean midpointsClear() {
    boolean midpointsClear = move.getMidpoints().stream().noneMatch(board::isOccupiedAt);
    if (!midpointsClear) reason = "There is a piece blocking that move";
    return midpointsClear;
  }

  private boolean friendlyPieceOnEndpoint() {
    if (targetPiece.isPresent()) {
      boolean friendlyPieceOnEndpoint = targetPiece.get().color == piece.color;
      if (friendlyPieceOnEndpoint) reason = "There is a friendly piece on the end position";
      return friendlyPieceOnEndpoint;
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
    if (!safe) reason = "That move puts you king in check";
    return safe;
  }

  boolean isPermissible() {
    return isValidMove() && isLegalMove() && isClear() && isSafe();
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
