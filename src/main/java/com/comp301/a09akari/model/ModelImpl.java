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
    if (library.getPuzzle(activePuzzleIndex).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    if (isLamp(r, c)) {
      return true;
    }

    boolean right = false;
    boolean left = false;
    boolean down = false;
    boolean up = false;

    for (int x = c; x < activePuzzle.getWidth(); x++) {
      if ((activePuzzle.getCellType(r, x) == CellType.CORRIDOR) == false) {
        right = false;
        break;
      } else if (isLamp(r, x) != false) {
        right = true;
        break;
      }
    }
    for (int x = c; x >= 0; x--) {
      if ((activePuzzle.getCellType(r, x) == CellType.CORRIDOR) == false) {
        left = false;
        break;
      } else if (isLamp(r, x) != false) {
        left = true;
        break;
      }
    }

    for (int y = r; y < activePuzzle.getHeight(); y++) {
      if ((activePuzzle.getCellType(y, c) == CellType.CORRIDOR) == false) {
        down = false;
        break;
      } else if (isLamp(y, c) != false) {
        down = true;
        break;
      }
    }
    for (int y = r; y >= 0; y--) {
      if ((activePuzzle.getCellType(y, c) != CellType.CORRIDOR) == false) {
        up = false;
        break;
      } else if (isLamp(y, c) != false) {
        up = true;
        break;
      }
    }

    return right || left || up || down;
  }

  @Override
  public boolean isLamp(int r, int c) {
    /*if (r < 0 || c < 0 || c >= activePuzzle.getWidth() || r >= activePuzzle.getHeight()) {
      throw new IndexOutOfBoundsException();
    }*/

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

    int x1 = c + 1;
    int x2 = c - 1;
    int y1 = r + 1;
    int y2 = r - 1;

    boolean right = false;
    boolean left = false;
    boolean down = false;
    boolean up = false;

    for (int x = x1; x < activePuzzle.getWidth(); x++) {
      if (activePuzzle.getCellType(r, x) != CellType.CORRIDOR) {
        right = false;
        break;
      } else if (isLamp(r, c)) {
        if (isLamp(r, x)) {
          right = true;
          break;
        }
      }
    }
    for (int x = x2; x >= 0; x--) {
      if (activePuzzle.getCellType(r, x) != CellType.CORRIDOR) {
        left = false;
        break;
      } else if ((isLamp(r, c))) {
        if (isLamp(r, x)) {
          left = true;
          break;
        }
      }
    }

    for (int y = y1; y < activePuzzle.getHeight(); y++) {
      if (activePuzzle.getCellType(y, c) != CellType.CORRIDOR) {
        down = false;
        break;
      } else if (isLamp(r, c)) {
        if (isLamp(y, c)) {
          down = true;
          break;
        }
      }
    }
    for (int y = y2; y >= 0; y--) {
      if (activePuzzle.getCellType(y, c) != CellType.CORRIDOR) {
        up = false;
        break;
      } else if (isLamp(r, c)) {
        if (isLamp(y, c)) {
          up = true;
          break;
        }
      }
    }

    return left || right || up || down;
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
      this.activePuzzleIndex = index;
      this.activePuzzle = library.getPuzzle(index);
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
          removeLamp(x, y);
        }
      }
    }
    notifyObserver();
  }

  @Override
  public boolean isSolved() {
    for (int x = 0; x < activePuzzle.getWidth(); x++) {
      for (int y = 0; y < activePuzzle.getHeight(); y++) {
        if (activePuzzle.getCellType(x, y) == CellType.CORRIDOR) {
          if (isLit(x, y)) {}
        }
      }
    }
    return false;
  }

  @Override
  public boolean isClueSatisfied(int r, int c) {
    /*if (r < 0 || c < 0 || c >= activePuzzle.getWidth() || r >= activePuzzle.getHeight()) {
      throw new IndexOutOfBoundsException();
    }*/

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
    if (observer == null) {
      throw new IllegalArgumentException();
    }
    observers.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    if (observer == null) {
      throw new IllegalArgumentException();
    }
    observers.remove(observer);
  }

  public void notifyObserver() {
    for (ModelObserver o : observers) {
      o.update(this);
    }
  }
}
