package chess.game;

import java.util.Arrays;
import java.util.stream.Collectors;

import chess.movement.Position;
import chess.pieces.Color;
import chess.pieces.Piece;

public class Messenger {
  static void printBoard(Board board) {
    Character[][] printableBoard = new Character[8][8];

    //Fill 2d array printableBoard with '-'
    Arrays.stream(printableBoard).forEach(row -> Arrays.fill(row, '-'));
    
    //Add the pieces to the 2d array
    board.getPieces().forEach(piece -> {
      printableBoard[piece.getCurrentPosition().yCoor - 1][piece.getCurrentPosition().xCoor - 1] =
        piece.getSymbol();
    });

    //Convert each row array to a single string and then print it
    Arrays.stream(printableBoard)
          .map(row -> Arrays.stream(row)
                            .map(c -> c.toString())
                            .collect(Collectors.joining(" ")))
          .forEach(System.out::println);
  }

  static Piece getPiece(Color color) {
    
  }

  static Position getEndPos(Color color) {
    
  }

  static void printProblem(String string);
  static void summarizePlay(Play play);
}
