package com.comp301.a09akari.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ModelImpl implements Model {
  private PuzzleLibrary library;
  private List<Integer> lampList;
  private int activePuzzleIndex;
  private List<ModelObserver> observers;
  // lampBoard contains: 0 = Not Lamp, 1 = Lamp
  private int[][] lampBoard;

  public ModelImpl(PuzzleLibrary library) {
    if (library == null) {
      throw new IllegalArgumentException();
    }

    this.library = library;
    this.lampList = new ArrayList<>();
    this.observers = new ArrayList<>();
    // this.lampBoard =
  }

  @Override
  public void addLamp(int r, int c) {
    if (library.getPuzzle(activePuzzleIndex).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IndexOutOfBoundsException();
    } else {
      lampBoard[r][c] = 1;
    }
    notifyObserver();
  }

  @Override
  public void removeLamp(int r, int c) {
    if (library.getPuzzle(activePuzzleIndex).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IndexOutOfBoundsException();
    } else {
      lampBoard[r][c] = 0;
    }
    notifyObserver();
  }

  @Override
  public boolean isLit(int r, int c) {
    if (r < 0
        || c < 0
        || c >= library.getPuzzle(getActivePuzzleIndex()).getWidth()
        || r >= library.getPuzzle(getActivePuzzleIndex()).getHeight()) {
      throw new IndexOutOfBoundsException();
    }
    return false;
  }

  @Override
  public boolean isLamp(int r, int c) {
    if (library.getPuzzle(activePuzzleIndex).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IndexOutOfBoundsException();
    }
    return lampBoard[r][c] == 1;
  }

  @Override
  public boolean isLampIllegal(int r, int c) {
    if (isLit(r, c)) {
      return false;
    } else {
      return true;
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
      throw new IllegalArgumentException();
    } else {
      activePuzzleIndex = index;
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
