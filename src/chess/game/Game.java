package chess.game;

import java.util.ArrayList;
import java.util.List;

import chess.pieces.Color;
import chess.pieces.Piece;

public class Game {
  Board board;

  Game() {
    this.board = new Board();
  }

  void play() {
    Messenger.printWelcome();
    Messenger.printBoard(board);
    Color color = Color.WHITE;
    List<Piece> checkingPieces = new ArrayList<>();
    do {
      //Take turn
      Turn.takeTurn(board, color);
      //Switch which color's turn it is
      color = color.opposite();
      //Check for check
      checkingPieces = Check.getCheckingPieces(board, color);
    } while (checkingPieces.size() == 0 || !Check.checkmate(board, color, checkingPieces));

    Messenger.declareVictory(color.opposite());

  }
}
