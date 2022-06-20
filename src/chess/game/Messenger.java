package chess.game;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import chess.movement.Position;
import chess.pieces.Color;
import chess.pieces.Piece;

public class Messenger {
  private final static char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
  static Scanner scanner = new Scanner(System.in);

  static String getInput() {
    String inputString = scanner.nextLine();
    if (inputString.equals("exit")) {
      System.out.println("Goodbye...");
      System.exit(0);
    }
    return inputString;
  }

  static void printBoard(Board board) {
    Character[][] printableBoard = new Character[8][8];

    //Fill 2d array printableBoard with '-'
    Arrays.stream(printableBoard).forEach(row -> Arrays.fill(row, '-'));
    
    //Add the pieces to the 2d array
    board.getPieces().forEach(piece -> {
      if (!piece.isCaptured()) {
        printableBoard[8 - piece.getCurrentPosition().yCoor][(piece.getCurrentPosition().xCoor - 1) ] =
          piece.getSymbol();
      }
    });

    //Convert each row array to a single string and then print it
    System.out.println();
    Arrays.stream(printableBoard)
          .map(row -> Arrays.stream(row)
                            .map(c -> c.toString())
                            .collect(Collectors.joining(" ")))
          .forEach(System.out::println);
  }

  private static boolean inputIsValid(String inputString) {
    if (inputString.length() != 2) return false;
  
    //inputString must be two characters: a-h, 1-8
    
    return inputString.charAt(0) >= 'a' && 
          inputString.charAt(0) <= 'h' &&
          Integer.parseInt(inputString.substring(1)) >= 1 &&
          Integer.parseInt(inputString.substring(1)) <= 8;
  
  }

  private static Optional<Piece> getPieceFromUserInput(Board board, String inputString) {
    Position position = getPositionFromUserInput(board, inputString);
    return board.getPieceAt(position);
  }

  private static Position getPositionFromUserInput(Board board, String inputString) {
    int xCoor = Arrays.binarySearch(letters, inputString.charAt(0)) + 1;
    int yCoor = Integer.parseInt(inputString.substring(1));
    return new Position(xCoor, yCoor);
  }

  

  static Piece getPiece(Board board, Color color) {
    String inputString;

    System.out.println();
    System.out.println("Enter starting position, " + color);
      while (true) {
        inputString = getInput();

        if (!inputIsValid(inputString)) {
          System.out.println("That is not a valid position. Please try again");
          continue;
        }
         
        Optional<Piece> piece = getPieceFromUserInput(board, inputString);
        if (!piece.isPresent()) {
          System.out.println("There is no piece at that position. Please try again");
          continue;
        }

        return piece.get();
      }
  }

  static Position getEndPos(Board board, Piece piece) {
    String inputString;

    System.out.println(piece + " " + piece.getCurrentPosition().gameNotation() + " to what position?");
    
      while (true) {
        inputString = getInput();

        if (!inputIsValid(inputString)) {
          System.out.println("That is not a valid position. Please try again");
          continue;
        }
         
        return getPositionFromUserInput(board, inputString);
      }
  }

  static void printProblem(String problem) {
    System.out.println("Not a valid play: " + problem);
  }

  static void summarizePlay(Play play) {
    Piece piece = play.piece;
   play.getMove().ifPresent(move -> {
    System.out.println(piece + " " + move.startPos.gameNotation() + " to " + move.endPos.gameNotation());
   });

   play.targetPiece.ifPresent(targetPiece -> {
    System.out.println("Capture" + targetPiece);
   });
  }

  static void printWelcome() {
    System.out.println("Welcome to Java chess!");
    System.out.println("For each turn, enter a starting position for your piece");
    System.out.println("Enter your position using a1, b4, e6, etc");
    System.out.println("a-h refer to the columns, 1-8 to the rows");
    System.out.println("a1 corresponds to the lower left hand corner, while h8 corresponds to the upper right");
    System.out.println("Then, enter the position to which you wish to move");
  }

  static void declareVictory(Color color) {
    System.out.println(color + "wins!");
  }

  static String askForOldOrNew() {
    System.out.println("Start a new game or resume an old one? Enter 'new' or 'old'");
    String response = getInput();
    while (!response.equals("new") && !response.equals("old")) {
      System.out.println("I do not understand");
      System.out.println("Please enter 'old' or 'new'");
      response = getInput();
    }
    return response;
  }

  static Path getGamePath() throws IOException {
    try (Stream<Path> s = Files.list(Path.of("saved-games"))) {
      List<Path> games = s.peek(System.out::println).collect(Collectors.toList());
      System.out.println("Enter the name of one of these saved games");
      String response = getInput();
      while (!games.contains(Path.of(response))) {
        System.out.println("No such game");
        System.out.println("Enter the name of one of these saved games");
        response = getInput();
      }
      return Path.of("saved-games", response);
    }
  }
}
