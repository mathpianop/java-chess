package chess.game;

import java.util.Arrays;
import java.util.stream.Collectors;

import chess.pieces.*;

public class Board {
  Side red = new Side(Color.RED);
  Side white = new Side(Color.WHITE);
  
  void printBoard() {
    Character[][] board = new Character[8][8];

    //Fill 2d-array board with '-'
    Arrays.stream(board).forEach(row -> Arrays.fill(row, '-'));
    
    red.pieces.forEach(piece -> {
      board[piece.getCurrentPosition().yCoor - 1][piece.getCurrentPosition().xCoor - 1] =
        piece.getSymbol();
    });

    white.pieces.forEach(piece -> {
      board[piece.getCurrentPosition().yCoor - 1][piece.getCurrentPosition().xCoor - 1] =
        piece.getSymbol();
    });

    Arrays.stream(board)
          .map(row -> Arrays.stream(row)
                            .map(c -> c.toString())
                            .collect(Collectors.joining(" ")))
          .forEach(System.out::println);
  }

  public static void main(String[] args) {
    new Board().printBoard();
  }
}
