package com.comp301.a09akari.model;

import static com.comp301.a09akari.model.CellType.*;

public class PuzzleImpl implements Puzzle {
  protected int[][] board;

  public PuzzleImpl(int[][] board) {
    this.board = board;
  }

  @Override
  public int getWidth() {
    return board[0].length;
  }

  @Override
  public int getHeight() {
    return board.length;
  }

  @Override
  public CellType getCellType(int r, int c) {
    if (r < 0 || c < 0 || c >= getWidth() || r >= getHeight()) {
      throw new IndexOutOfBoundsException();
    }

    switch (board[r][c]) {
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
        return CLUE;
      case 5:
        return WALL;
      case 6:
        return CORRIDOR;
      default:
        return null;
    }
  }

  @Override
  public int getClue(int r, int c) {
    if (r < 0 || c < 0 || c >= getWidth() || r >= getHeight()) {
      throw new IndexOutOfBoundsException();
    }

    if (getCellType(r, c) == CLUE) {
      return board[r][c];
    } else {
      throw new IllegalArgumentException();
    }
  }
}
