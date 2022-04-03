package chess.game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import chess.pieces.*;

public class Side {
  static List<Piece> generate(Color color) {
    List<Piece> pieces = new ArrayList<>();

    List<Pawn> pawns = IntStream.rangeClosed(1,8)
                                .mapToObj(i -> new Pawn(color, i))
                                .collect(Collectors.toList());
    List<Piece> bigPieces = List.of(
      new Rook(color, 1),
      new Knight(color, 2),
      new Bishop(color, 3),
      new Queen(color),
      new King(color),
      new Bishop(color, 6),
      new Knight(color, 7),
      new Rook(color, 8)
    );

    pieces.addAll(pawns);
    pieces.addAll(bigPieces);

    return pieces;
  }
}
