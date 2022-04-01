-Color enum

-Piece
  -Calculate legal moves on an empty board
  -Knows whether a move is legal or not
  -has location
  -Knows whether it is captured or not
  -Knows whether it is red or white
-Move
  -Has endpoint and startpoint
  -calculates midpoints
-Board
  -Contains Piece objects


Steps to determine move permissibility (assuming endpoint/startpoint is on the board):
--------------- (Valid move in general / handled by Move constructor)
1. Check if end position is on board
2. Check if move is a possible one for any piece in any situation
-------------- (Move legal for specific piece / handled by isMoveLegal and getLegalMoves)
3. Check if move is a legal maneuver for the piece generally (specifying whether or not it is a capture for the sake of pawns )
--------------- (Move clear)
4. Check if pieces are occupying the midpoints
5. Check if friendly piece is on endpoint
6. Check if enemy king is on endpoint
------------------ (Move safe)
7. Check if move leaves friendly king in check

How to tell if a king is in check
1. Assemble all possible moves ending on king position
2. Pass 1 & 2 above


How to check for checkmate
1. Check if King has a legal move that can get him out of check
2. Check if any friendly piece can capture the checking piece (and whether that actually fixes the situation)
3. Check if a friendly piece can block the checking piece(s) (and whether that actually fixes the situation)