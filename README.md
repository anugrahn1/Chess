# Chess
#### Description: 

## Introduction

This project is a Java-based chess game that provides a graphical user interface (GUI) for players to engage in a traditional game of chess. The game incorporates all standard chess pieces and rules, including pawn promotion, check, and checkmate detection. The classes and their interactions model the chess game’s logic and visual representation.

## Table of Contents

- [Introduction](#introduction)
- [Classes Overview](#classes-overview)
  - [Square](#square)
  - [Piece](#piece)
  - [Rook](#rook)
  - [Queen](#queen)
  - [Pawn](#pawn)
  - [Knight](#knight)
  - [King](#king)
  - [GUI](#gui)
  - [Game](#game)
  - [Board](#board)
  - [Bishop](#bishop)
- [Installation](#installation)
- [Usage](#usage)
- [Features](#features)
- [Dependencies](#dependencies)
- [Configuration](#configuration)
- [Documentation](#documentation)
- [Examples](#examples)
- [Troubleshooting](#troubleshooting)


## Classes Overview

### Square

The `Square` class extends `JButton` and represents a single square on the chessboard. Each `Square` object holds its position (row and column) and sets its size and color (red or white) based on its position to create a checkered pattern. It can display a piece symbol using the `setPieceSymbol` method and clear the piece symbol with the `clearPiece` method.

### Piece

The `Piece` class is the base class for all chess pieces. It includes properties for the piece's position (row and column), color, and type. It provides methods to get and set these properties and to determine if a move is valid, though the latter is typically overridden by subclasses.

### Rook

The `Rook` class extends `Piece` and represents a rook in the game of chess. The `isValidMove` method checks whether a move is valid by ensuring it moves in a straight line (either row or column) and that no pieces block its path.

### Queen

The `Queen` class extends `Piece` and represents a queen in chess. The `isValidMove` method checks if the move is in a straight line (like a rook) or diagonal (like a bishop) and verifies that no pieces block its path.

### Pawn

The `Pawn` class extends `Piece` and represents a pawn. It includes logic for pawn-specific movements such as moving forward one square, two squares from the starting position, and diagonal captures. It also handles promotion when a pawn reaches the opposite side of the board.

### Knight

The `Knight` class extends `Piece` and represents a knight. Its `isValidMove` method checks for the characteristic L-shaped moves and ensures it can move to either an empty square or one occupied by an opponent’s piece.

### King

The `King` class extends `Piece` and represents the king. The `isValidMove` method ensures that the king only moves one square in any direction and checks for both check and checkmate situations.

### GUI

The `GUI` class extends `JFrame` and provides the visual interface for the chess game. It initializes the board with `Square` objects, handles piece selection and movement, and updates the display accordingly. The class also handles check, checkmate, and pawn promotion dialogs.

### Game

The `Game` class manages the overall state of the game, including the board, the current player's turn, and checkmate status. It provides methods to move pieces, check for check and checkmate conditions, and handle pawn promotion. The `handleSquareSelection` method processes user input for selecting and moving pieces.

### Board

The `Board` class represents the chessboard and manages the placement of pieces. It initializes the board with the standard chess setup, provides methods to get and set pieces at specific positions, and moves pieces on the board.

### Bishop

The `Bishop` class extends `Piece` and represents a bishop. The `isValidMove` method checks if the move is along a diagonal and ensures no pieces block the path.

## Installation

To install and run the chess game, follow these steps:

1. Ensure you have Java installed on your system.
2. Download or clone the project repository.
3. Compile the Java files:
   ```bash
   javac GUI.java
   ```
4. Run the `GUI` class to start the game:
   ```bash
   java GUI
   ```

## Usage

- Launch the game using the instructions above.
- Click on a piece to select it, then click on a destination square to move it.
- The game will handle moves, detect checks and checkmates, and allow pawn promotion.

## Features

- Full implementation of chess rules, including piece movements and captures.
- Graphical user interface for intuitive play.
- Check and checkmate detection.
- Pawn promotion with a selection dialog.

## Dependencies

- Java Development Kit (JDK)
- Swing library for the graphical user interface

## Configuration

The game does not require specific configuration beyond ensuring Java is installed and available in your system's PATH.

## Documentation

For detailed documentation, refer to the comments within the source code files. Each class and method is documented to explain its purpose and functionality.

## Examples

Example of starting a new game:
```bash
java GUI
```

## Troubleshooting

If you encounter issues running the game, ensure you have the correct version of Java installed and that all source files are compiled correctly.


## Credits:
    ChatGPT for this readme

Video Demo: https://www.youtube.com/watch?v=W3JcGyABt54