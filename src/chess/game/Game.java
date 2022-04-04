package chess.game;

import chess.pieces.Color;

public class Game {
  Board board;

  Game() {
    this.board = new Board();
  }

  void play() {
    Messenger.printWelcome();
    Messenger.printBoard(board);
    Color color = Color.WHITE;
    boolean inCheck = false;
    do {
      //Take turn
      Turn.takeTurn(board, color);
      //Check for check
      inCheck = Check.isInCheck(board, color);
      //Switch which color's turn it is
      color = color.opposite();
    } while (inCheck && Check.checkmate(board, color));

    Messenger.declareVictory(color.opposite());

  }

  public static void main(String[] args) {
    new Game().play();
  }
}
