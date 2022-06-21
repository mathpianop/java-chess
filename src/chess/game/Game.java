package chess.game;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import chess.pieces.Color;
import chess.pieces.Piece;

public class Game implements Serializable {
  Board board;
  private static Game currentGame;
  private Color currentColor = Color.WHITE;
  private String name;

  Game() {
    this.board = new Board();
  }

  void play() {
    currentGame = this;
    Messenger.printWelcome();
    Messenger.printBoard(board);
    List<Piece> checkingPieces = new ArrayList<>();
    Check check = new Check(board, currentColor);
    do {
      //Take turn
      Turn.takeTurn(board, currentColor);
      //Switch which color's turn it is
      currentColor = currentColor.opposite();
      //Check for check
      checkingPieces = check.getCheckingPieces();
    } while (checkingPieces.size() == 0 || !check.checkmate(checkingPieces));

    Messenger.declareVictory(currentColor.opposite());

  }

  public void save() throws IOException {
    Path gamePath = (this.name == null ? Messenger.getNewGamePath() : Path.of("saved-games", this.name));
    this.name = gamePath.getFileName().toString();
    try(var oos = new ObjectOutputStream(
                    new BufferedOutputStream(
                      new FileOutputStream(gamePath.toFile())))) {
                        
      oos.writeObject(this);
    } catch (IOException e) {
      System.out.println("Could not save game");
      System.out.println(e);
      throw e;
    }
  }

  public static void saveCurrent() throws IOException  {
    currentGame.save();
  }
}
