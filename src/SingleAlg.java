/**
 * SingleAlg Class: Uses the single algorithm for the given cell.
 * 
 * A single is when a cell, X, only has one possible value, V, because the other values are
 * already resolved in a cell that share a row, column or box with X. In the puzzle at the top
 * of the page, the cell in the vary center of the puzzle (row 5, column 5) must have the
 * value of 5 since the values 1,2,3,4,6,7,8 and 9 are already known in cells that share the
 * row, column or box with the cell at row 5, column 5.
 * 
 * When an unresolved cell only has a single value in its candidate list, you have a single.
 * 
 * Aaron Guevarra 
 * U. of Illinois, Chicago 
 * CS342, Fall 2017 
 * Project #03
 */
import java.util.ArrayList;

public class SingleAlg {
  private ArrayList<Integer> candidateList;

  /**
   * Constructor:
   * 
   * @param cell the cell to evaluate
   */
  public SingleAlg(Cell cell) {
    candidateList = cell.getCandidateList();
  }

  /**
   * Determines if the single algorithm can be implemented
   * @return
   */
  public boolean isSingle() {
    return candidateList.size() == 1 ? true:false;
  }
}
