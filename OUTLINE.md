
Play requires:
-Board
-Piece
-End Position

Steps to determine Play permissibility (assuming endpoint/startpoint is on the board):
--------------- (Valid move in general / handled by Move constructor)
1. Check if end position is on board
2. Check if move is a possible one for any piece in any situation
-------------- (Move legal for specific piece / handled by isMoveLegal and getLegalMoves)
3. Check if move is a legal maneuver for the piece generally (specifying whether or not it is a capture for the sake of pawns )
--------------- (Play clear)
4. Check if pieces are occupying the midpoints
5. Check if friendly piece is on endpoint
------------------ (Play safe)
7. Check if move leaves friendly king in check

How to tell if a king is in check
1. Assemble all possible moves ending on king position
2. Pass 1 & 2 above


How to check for checkmate
1. Check if King has a legal move that can get him out of check
2. Check if any friendly piece can capture the checking piece (and whether that actually fixes the situation)
3. Check if a friendly piece can block the checking piece(s) (and whether that actually fixes the situation)


Steps for a turn
0. Specify whether King is in check in the constructor
1. Get a move from the user
2. Create play based on move
3. Determine permissibility of play
4. If not permissible, say so
5. Repeat 1-3 as necessary
6. Print summary of move
7. Print the board

Steps for a game
1. Print welcome and starting board
2. Let player take turn
3. Check for check
4. If someone is in check, check for checkmate
5. If yes, declare game over
6. If not, repeat steps 2-5