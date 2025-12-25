=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 1200 Game Project README
PennKey: 27324895
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D Arrays - My game uses a 4×4 2D int array to store the values of each tile on the 2048 board.
  Each move (left, right, up, down) reads and writes into this 2D array.
  This is appropriate since 2048 is a grid based game.

  2. Collections - I use a Java Collection (ArrayList) to keep track of previous board states or the cells that changed after a move.
  This helps store "snapshots" for the undo feature.
  This is appropriate because while the 2D array is a fixed size, the history changes (grows/shrinks) which makes ArrayLists perfect.

  3. Complex Game Logic - My game implements the full movement algorithm of 2048: compressing tiles, merging equal tiles, reversing rows for rightward moves, and checking if any moves remain.
  This is appropriate because 2048 requires multiple sequential transformations on each row/column, and breaking these rules into smaller methods and by using helper functions makes the game easier to follow.

  4. JUnit Testable Component - I created a separate BoardManager class that contains all of the logic for tile movement and board evaluation.
  These components were not involved in GUI, but rather served to help with the logic of the game.
  This is appropriate because this gave me one fully testable model component.

===============================
=: File Structure Screenshot :=
===============================
- Include a screenshot of your project's file structure. This should include
  all of the files in your project, and the folders they are in. You can
  upload this screenshot in your homework submission to gradescope, named 
  "file_structure.png".

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.

TwentyFortyEight (Model) -

This class stores and updates the core game state, including the 4×4 board, tile values, move handling, adding new tiles, and tracking when the game ends.
It delegates all movement-related logic to BoardManager, but it remains responsible for coordinating moves, updating the board, and triggering resets.

BoardManager (Testable Logic Component) -

This class contains all logic for shifting tiles: compressing non-zero values, merging adjacent equal tiles, reversing rows, handling left/right movement for a single row, and detecting when the board has no moves left.
It is fully JUnit-testable.

GameBoard (View + Controller) -

This class draws the board on the screen using Swing and handles user input through arrow keys.
It listens for key events, updates the status message, and repaints the grid.

RunTwentyFortyEight -

This class creates the main game window, places the board and reset button, and starts the game.
It is only responsible for setting up the GUI and not for any game logic.

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?

I had a few issues with implementing the logic of the game.
Originally, a new tile was added once for every changed cell, which caused multiple tiles to spawn at once.
To fix this, I checked whether any cell changed and added only one tile per move.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?

My design has a clear separation of functionality that follows MVC.
BoardManager handles all tile-movement logic, TwentyFortyEight maintains the game state and coordinates moves, GameBoard is responsible for rendering and handling keyboard input, and RunTwentyFortyEight simply sets up the GUI.
Encapsulation is strong because the board array is private, moves occur only through public methods, and the UI never manipulates the model directly.
If I had more time, I would refactor the movement code since right now the UP, DOWN, LEFT, and RIGHT moves each contain similar logic, and introducing rotation helpers could reduce repetition and make the code cleaner.


========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used 
  while implementing your game.
