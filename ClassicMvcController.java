package com.comp301.a09akari.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public interface ClassicMvcController {
  /** Handles the click action to go to the next puzzle */
  void clickNextPuzzle();

  /** Handles the click action to go to the previous puzzle */
  void clickPrevPuzzle();

  /** Handles the click action to go to a random puzzle
   * @return*/
  void clickRandPuzzle();

  /** Handles the click action to reset the currently active puzzle
   * @return*/
  void clickResetPuzzle();

  /** Handles the click event on the cell at row r, column c */
  void clickCell(int r, int c);
}
