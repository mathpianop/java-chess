package chess.game;

import chess.movement.Position;
import chess.pieces.Color;
import chess.pieces.Piece;

public class Turn {

  static void takeTurn(Board board, Color color) {
    Piece piece;
    Position endPos;
    Play play;

    //Keep asking for plays from user until a legal one is found
    while (true) {
      piece  = Messenger.getPiece(board, color);
      endPos = Messenger.getEndPos(board, color);
      play = new Play(board, piece, endPos);
      if (play.isPermissible()) {
        break;
      } else {
        Messenger.printProblem(play.getReason());
      }
    }

    //Make the play
    play.makePlay();

    //Print summary of play and newBoard
    Messenger.summarizePlay(play);
    Messenger.printBoard(board);
    
  }

}
