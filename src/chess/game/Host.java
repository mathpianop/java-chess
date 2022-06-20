package chess.game;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Path;

public class Host {

  static private Game deserializeGame(Path gamePath) throws IOException, ClassNotFoundException {
    
    try(var ois = new ObjectInputStream(
                    new BufferedInputStream(
                      new FileInputStream(gamePath.toFile())))) {
      return (Game) ois.readObject();
    }
  }

  static private Game determineGame(){
    try {
      String response = Messenger.askForOldOrNew();
      if (response.equals("new")) {
        return new Game();
      } else {
        Path gamePath = Messenger.getOldGamePath();
        return deserializeGame(gamePath);
      }
    } catch (Exception e) {
      System.out.println(e);
      return determineGame();
    }
    
  }
  public static void main(String[] args) {
    Game game = determineGame();
    game.play();
    
  }
}
