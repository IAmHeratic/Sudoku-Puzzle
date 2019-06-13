/* Author: Jose Rodriguez
 * CS 342 Fall 2017
 * Project 3: Sudoku Solver
 * University of Illinois at Chicago
 * 
 * This will map the row, col coordinates to the proper index of the grid.
 * First we determine which subgrid to look at, then which Cell in the
 * subgrid, and thus we get an index linked with a (row, col) pair.
 */

import java.io.*;
import java.util.*;

public class Map {
  private int rows;
  private int columns;

  public Map(int newRows, int newColumns) {
    rows = newRows;
    columns = newColumns;
  }

  // main function, returns result
  public int mapRowAndCol() {
	  
	// TODO:
    // check that row and col are valid?

    // perform calculation
    int gridNumber = getRowStartIndex(rows) + getColumnOffset(columns);
    int finalIndex = getSudokuGridStartIndex(gridNumber) + getSubGridOffset(rows, columns);

    return (finalIndex - 1); // subtract 1 because we start indexes at 0 zero!
  }
  
  
  public Pair convertIndex() {
    return new Pair(-1,-1);
  }

  /* --------- HELPER FUNCTIONS BELOW --------- */

  private int getRowStartIndex(int row) {
    if (row < 4) {
      return 1;
    } else if (row < 7) {
      return 4;
    } else { // row < 9
      return 7;
    }
  }


  private int getColumnOffset(int col) {
    if (col < 4) {
      return 0;
    } else if (col < 7) {
      return 1;
    } else { // col < 9
      return 2;
    }
  }


  private int getSudokuGridStartIndex(int gridNumber) {
    return (((gridNumber - 1) * (9)) + 1);
  }


  private int getSubGridOffset(int row, int col) {
    return (getRowSubGridOffset(row) + getColSubGridOffset(col));
  }


  private int getRowSubGridOffset(int row) {
    if ((row % 3) == 1) {
      return 0;
    } else if ((row % 3) == 2) {
      return 3;
    } else { // row%3 == 0
      return 6;
    }
  }


  private int getColSubGridOffset(int col) {
    if ((col % 3) == 1) {
      return 0;
    } else if ((col % 3) == 2) {
      return 1;
    } else { // col%3 == 0
      return 2;
    }
  }

} // end map class
