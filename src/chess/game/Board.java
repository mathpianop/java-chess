package chess.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import chess.movement.Position;
import chess.pieces.*;

public class Board {

  private final List<Piece> pieces;

  Board() {
    pieces = new ArrayList<>();
    pieces.addAll(Side.generate(Color.RED));
    pieces.addAll(Side.generate(Color.WHITE));
  }
  
  void printBoard() {
    Messenger.printBoard(this);
  }

  public List<Piece> getPieces() {
    List<Piece> copyOfPieces = new ArrayList<>(pieces);
    return copyOfPieces;
  }


  Optional<Piece> getPieceAt(Position pos) {
    //Return whether any piece on either side 
   return pieces.stream()
                .filter(piece -> !piece.isCaptured())
                .filter(piece -> piece.getCurrentPosition().equals(pos))
                .findAny();
  }

  boolean isOccupiedAt(Position pos) {
    //Return whether any piece on either side 
   return pieces.stream()
                .filter(piece -> !piece.isCaptured())
                .anyMatch(piece -> piece.getCurrentPosition().equals(pos));
  }
}
