package com.comp301.a09akari.model;

import javafx.scene.control.Cell;

public class PuzzleImpl implements Puzzle {
  private int[][] board;

  public PuzzleImpl(int[][] board) {
    this.board = board.clone();
  }

  public int getWidth() {
    return this.board[0].length;
  }

  public int getHeight() {
    return this.board.length;
  }

  public CellType getCellType(int r, int c) {
    if (r >= this.board.length || c >= this.board[0].length || r<0 || c<0){
      throw new IndexOutOfBoundsException("Out of bound!");
    }
    if (this.board[r][c] == 0
        || this.board[r][c] == 1
        || this.board[r][c] == 2
        || this.board[r][c] == 3
        || this.board[r][c] == 4) {
      return CellType.CLUE;
    } else if (this.board[r][c] == 5) {
      return CellType.WALL;
    } else {
      return CellType.CORRIDOR;
    }
  }

  public int getClue(int r, int c) {
    if (r >= this.board.length || c >= this.board[0].length || r<0 || c<0){
      throw new IndexOutOfBoundsException("Out of bound!");
    }
    if (this.board[r][c]>4){
      throw new IllegalArgumentException();
    }
    return this.board[r][c];
  }
}
