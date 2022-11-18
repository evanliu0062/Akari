package com.comp301.a09akari.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ModelImpl implements Model {
  private PuzzleLibrary library;
  private Puzzle activePuzzle;
  private int activePuzzleIndex = 0;
  private List<ModelObserver> observers;
  // lampBoard contains: 0 = Not Lamp, 1 = Lamp
  private int[][] lampBoard;

  public ModelImpl(PuzzleLibrary library) {
    if (library == null) {
      throw new IllegalArgumentException();
    }

    this.library = library;
    this.activePuzzle = library.getPuzzle(activePuzzleIndex);
    this.observers = new ArrayList<>();
    this.lampBoard = new int[activePuzzle.getHeight()][activePuzzle.getWidth()];
  }

  @Override
  public void addLamp(int r, int c) {
    if (r < 0 || c < 0 || c >= activePuzzle.getWidth() || r >= activePuzzle.getHeight()) {
      throw new IndexOutOfBoundsException();
    }

    if (library.getPuzzle(activePuzzleIndex).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    } else {
      lampBoard[r][c] = 1;
    }
    notifyObserver();
  }

  @Override
  public void removeLamp(int r, int c) {
    if (r < 0 || c < 0 || c >= activePuzzle.getWidth() || r >= activePuzzle.getHeight()) {
      throw new IndexOutOfBoundsException();
    }

    if (library.getPuzzle(activePuzzleIndex).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    } else {
      lampBoard[r][c] = 0;
    }
    notifyObserver();
  }

  @Override
  public boolean isLit(int r, int c) {
    if (r < 0 || c < 0 || c >= activePuzzle.getWidth() || r >= activePuzzle.getHeight()) {
      throw new IndexOutOfBoundsException();
    }

    if (library.getPuzzle(activePuzzleIndex).getCellType(r, c) == CellType.CORRIDOR) {
      if (lampBoard[r][c] == 1) {
        return true;
      }

      for (int x = r; x < lampBoard.length; x++) {
        if (library.getPuzzle(activePuzzleIndex).getCellType(x, c) != CellType.CORRIDOR) {
          break;
        }
        if (lampBoard[x][c] == 1) {
          return true;
        }
      }
      for (int x = r; x >= 0; x--) {
        if (library.getPuzzle(activePuzzleIndex).getCellType(x, c) != CellType.CORRIDOR) {
          break;
        }
        if (lampBoard[x][c] == 1) {
          return true;
        }
      }

      for (int y = c; y < lampBoard.length; y++) {
        if (library.getPuzzle(activePuzzleIndex).getCellType(r, y) != CellType.CORRIDOR) {
          break;
        }
        if (lampBoard[r][y] == 1) {
          return true;
        }
      }
      for (int y = c; y >= 0; y--) {
        if (library.getPuzzle(activePuzzleIndex).getCellType(r, y) != CellType.CORRIDOR) {
          break;
        }
        if (lampBoard[r][y] == 1) {
          return true;
        }
      }
    } else {
      throw new IllegalArgumentException();
    }
    return false;
  }

  @Override
  public boolean isLamp(int r, int c) {
    if (r < 0 || c < 0 || c >= activePuzzle.getWidth() || r >= activePuzzle.getHeight()) {
      throw new IndexOutOfBoundsException();
    }

    if (library.getPuzzle(activePuzzleIndex).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    return lampBoard[r][c] == 1;
  }

  @Override
  public boolean isLampIllegal(int r, int c) {
    if (r < 0 || c < 0 || c >= activePuzzle.getWidth() || r >= activePuzzle.getHeight()) {
      throw new IndexOutOfBoundsException();
    }
    if (library.getPuzzle(activePuzzleIndex).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }

    if (!isLamp(r, c)) {
      throw new IllegalArgumentException();
    }

    if (isLit(r, c)) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public Puzzle getActivePuzzle() {
    return library.getPuzzle(activePuzzleIndex);
  }

  @Override
  public int getActivePuzzleIndex() {
    return activePuzzleIndex;
  }

  @Override
  public void setActivePuzzleIndex(int index) {
    if (index < 0 || index >= library.size()) {
      throw new IndexOutOfBoundsException();
    } else {
      activePuzzleIndex = index;
      activePuzzle = library.getPuzzle(index);
    }
    notifyObserver();
  }

  @Override
  public int getPuzzleLibrarySize() {
    return library.size();
  }

  @Override
  public void resetPuzzle() {
    for (int x = 0; x < lampBoard.length; x++) {
      for (int y = 0; y < lampBoard[0].length; y++) {
        if (lampBoard[x][y] == 1) {
          lampBoard[x][y] = 0;
        }
      }
    }
    notifyObserver();
  }

  @Override
  public boolean isSolved() {
    return false;
  }

  @Override
  public boolean isClueSatisfied(int r, int c) {
    if (r < 0 || c < 0 || c >= activePuzzle.getWidth() || r >= activePuzzle.getHeight()) {
      throw new IndexOutOfBoundsException();
    }

    int clue = library.getPuzzle(activePuzzleIndex).getClue(r, c);
    int count = 0;
    if (isLamp(r + 1, c)) {
      count++;
    }
    if (isLamp(r - 1, c)) {
      count++;
    }
    if (isLamp(r, c + 1)) {
      count++;
    }
    if (isLamp(r, c - 1)) {
      count++;
    }
    return clue == count;
  }

  @Override
  public void addObserver(ModelObserver observer) {
    observers.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    observers.remove(observer);
  }

  public void notifyObserver() {
    for (ModelObserver o : observers) {
      o.update(this);
    }
  }
}
