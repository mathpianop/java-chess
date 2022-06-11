package chess.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import chess.movement.Move;
import chess.movement.Position;
import chess.pieces.Bishop;
import chess.pieces.Color;
import chess.pieces.King;
import chess.pieces.Piece;
import chess.pieces.Queen;

class CheckScenarios {
  static Position redPawn1 = new Position(4,7);
  static Position redPawn2 = new Position(5,7);
  static Position redBishop1 = new Position(3,8);
  static Position redBishop2 = new Position(6,8);
  static Position redKnight2 = new Position(7,8);
  static Position redQueen = new Position(4,8);

  static Position whitePawn1 = new Position(5,2);
  static Position whiteQueen = new Position(4,1);

  static Position whiteKnight1 = new Position(2,1);
  static Position knightTransit1 = new Position(3,3);
  static Position knightTransit2 = new Position(5,4);
  static Position knightTransit3 = new Position(4,6);
  static Move checkingMove1 = new Move(whiteQueen, whitePawn1);

  private static void killPiecesOnPositions(Board board, List<Position> positionsToKill) {
    positionsToKill.forEach((Position pos) -> {
      board.getPieceAt(pos).get().setCaptured(true);;
    });
  }

  public static Board escape() {
    Board board = new Board();

    List<Position> positionsToKill = List.of(
                                  redPawn1,
                                  redPawn2,
                                  redBishop1,
                                  redBishop2,
                                  redKnight2,
                                  redQueen,
                                  whitePawn1
                                  );
    killPiecesOnPositions(board, positionsToKill);


    board.getPieceAt(whiteQueen).get().makeMove(checkingMove1);
    return board;
  }

  public static Board block() {
    Board board = new Board();

    List<Position> positionsToKill = List.of(redPawn2, whitePawn1);
    killPiecesOnPositions(board, positionsToKill);
    Piece queen = board.getPieceAt(whiteQueen).get();
    queen.makeMove(checkingMove1);

    return board;
  }

  public static Board capture() {
    Board board = new Board();

    
    Piece knight = board.getPieceAt(whiteKnight1).get();
    knight.makeMove(new Move(whiteKnight1, knightTransit1));
    knight.makeMove(new Move(knightTransit1, knightTransit2));
    knight.makeMove(new Move(knightTransit2, knightTransit3));

    return board;
  }
}
public class CheckTest {

  @Test 
  public void isInCheck_ReturnsFalseIfNotInCheck() {
    Board board = new Board();
    assertFalse(Check.isInCheck(board, Color.WHITE));
  }

  @Test 
  public void isInCheck_ReturnsTrueIfInCheck() {
    Board board = new Board();

    Position pawnPos1 = new Position(5,2);
    Position pawnPos2 = new Position(5,7);
    Position queenPos = new Position(4,1);

    //Clear pawns in front of Kings out of the way
    board.getPieceAt(pawnPos1).get().setCaptured(true);
    board.getPieceAt(pawnPos2).get().setCaptured(true);

    //Move White Queen up-right one space
    Move move = new Move(queenPos, pawnPos1);
    board.getPieceAt(queenPos).get().makeMove(move);

    assertTrue(Check.isInCheck(board, Color.RED));
  }

  @Test
  public void getKing_ReturnsKing() {
    Board board = new Board();

    King king = (King) board.getPieceAt(new Position(5,1)).get();

    assertSame(king, Check.getKing(board.getPieces(), Color.WHITE));
  }

  @Test 
  public void getCheckingPieces_ReturnsListWithSingleCheckingPiece() {
    Board board = new Board();

    Position pawnPos1 = new Position(5,2);
    Position pawnPos2 = new Position(5,7);
    Position queenPos = new Position(4,1);

    //Clear pawns in front of Kings out of the way
    board.getPieceAt(pawnPos1).get().setCaptured(true);
    board.getPieceAt(pawnPos2).get().setCaptured(true);

    //Move White Queen up-right one space
    Move move = new Move(queenPos, pawnPos1);
    Queen queen = (Queen) board.getPieceAt(queenPos).get();

    queen.makeMove(move);

    List<Piece> checkingPieces = Check.getCheckingPieces(board, Color.RED);

    assertSame(queen, checkingPieces.get(0));
    assertEquals(checkingPieces.size(), 1);
  }

  @Test 
  public void getCheckingPieces_ReturnsListWithMultipleCheckingPieces() {
    Board board = new Board();

    Position pawnPos1 = new Position(5,2);
    Position pawnPos2 = new Position(5,7);
    Position pawnPos3 = new Position(6,7);
    Position queenPos = new Position(4,1);
    Position bishopPosition = new Position(6,1);
    Position bishopAttackPosition = new Position(8,5);

    //Clear pawns in front of Kings out of the way
    board.getPieceAt(pawnPos1).get().setCaptured(true);
    board.getPieceAt(pawnPos2).get().setCaptured(true);
    board.getPieceAt(pawnPos3).get().setCaptured(true);

    Queen queen = (Queen) board.getPieceAt(queenPos).get();
    Bishop bishop = (Bishop) board.getPieceAt(bishopPosition).get();

    //Move Bishop into checking position
    Move move1 = new Move(bishopPosition, pawnPos1);
    Move move2 = new Move(pawnPos1, bishopAttackPosition);
    bishop.makeMove(move1);
    bishop.makeMove(move2);


    //Move White Queen up-right one space
    Move move3 = new Move(queenPos, pawnPos1);
    queen.makeMove(move3);

    List<Piece> checkingPieces = Check.getCheckingPieces(board, Color.RED);

   
    assertTrue(checkingPieces.contains(queen));
    assertTrue(checkingPieces.contains(bishop));
    assertEquals(checkingPieces.size(), 2);
  }

  @Test
  public void getCheckingPieces_DoesNotIncludePiecesWhoseWayIsBlocked() {
    Board board = new Board();

    Position pawnPos1 = new Position(5,2);

    board.getPieceAt(pawnPos1).get().setCaptured(true);

    //Move White Queen up-right one space
    Position queenPos = new Position(4,1);
    Queen queen = (Queen) board.getPieceAt(queenPos).get();
    Move move3 = new Move(queenPos, pawnPos1);
    queen.makeMove(move3);

    List<Piece> checkingPieces = Check.getCheckingPieces(board, Color.RED);


    assertFalse(checkingPieces.contains(queen));
  }

  @Test
  public void checkmate_ReturnsFalseIfKingCanEscape() {
    Board board = CheckScenarios.escape();
    Piece queen = board.getPieceAt(CheckScenarios.whitePawn1).get();
    assertFalse(Check.checkmate(board, Color.RED, List.of(queen)));
  }

  @Test
  public void checkmate_ReturnsFalseIfFriendlyPieceCanBlock() {
    Board board = CheckScenarios.block();
    Piece queen = board.getPieceAt(CheckScenarios.whitePawn1).get();
    assertFalse(Check.checkmate(board, Color.RED, List.of(queen)));
  }

  @Test
  public void checkmate_ReturnsFalseIfFriendlyPieceCanCapture() {
    Board board = CheckScenarios.capture();
    Piece knight = board.getPieceAt(CheckScenarios.knightTransit3).get();
    assertFalse(Check.checkmate(board, Color.RED, List.of(knight)));
  }
}
