package chess.game;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Printer {
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
}
