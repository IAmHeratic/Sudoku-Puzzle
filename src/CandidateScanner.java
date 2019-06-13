/*
 * CandidateScannder Class: Scans a grid of cells to get candidate values for the given cell. 
 * This is done by scanning each group that the cell belongs to. A group consists of 
 * 1) Being in the same row 
 * 2) Being in the same column 
 * 3) Being in the same 3x3 sub-grid.
 * 
 * Aaron Guevarra
 * U. of Illinois, Chicago 
 * CS342, Fall 2017 
 * Project #03
 */
import java.util.ArrayList;

public class CandidateScanner {
  private ArrayList<Integer> candidateList;
  private Cell cell;
  private Cell[] grid;


  /**
   * Constructor:
   * 
   * @param cellToCheck     cell to be populated
   * @param cellArray       array of cells to scan for candidates
   */
  public CandidateScanner(Cell cellToCheck, Cell[] cellArray) {
    cell = cellToCheck;
    grid = cellArray;
    candidateList = new ArrayList<Integer>();
    fillCandidates();
  }


  /**
   * fillCandidates:
   * 
   * Numbers 1 - 9 are the potential values initially
   */
  private void fillCandidates() {
    for (int i = 1; i < 10; ++i) {
      candidateList.add(Integer.valueOf(i));
    }
  }
  
  
  /**
   * checkCandidate: 
   * 
   * Determines if the value exists in the candidate list. If the value exists 
   * removes in from the list
   * 
   * @param value   the value to be determined
   * @return true   if the value was found and removed
   */
  private boolean checkCandidate(String value) {
    if (value != "" && candidateList.contains(Integer.valueOf(value))) {
      candidateList.remove(Integer.valueOf(value));

      return true;
    }
    
    // not found:
    return false;
  }
  
  
  /**
   * getPosition:
   * 
   * Determines the sub-grid that the cell is located at.
   * Sections in the SubGrid:
   * 1 2 3
   * 4 5 6
   * 7 8 9
   * Section 1 starts at (0,0)
   * Section 2 starts at (0,3)
   * Section 3 ...
   * Section N starts at (row, col)
   * 
   * @param row     row of the cell being evaluated
   * @param col     column of the cell being evaluated
   * @return        the position of the starting row/col
   */
  private int getPosition(int row, int col) {
    // position of each section:
    boolean topRow = row < 4;
    boolean midRow = row >= 3 && row < 6;
    boolean botRow = row >= 6 && row < 9;
    
    boolean leftCol = col < 4;
    boolean centerCol = col >= 3 && col < 6;
    boolean rightCol = col >= 6 && col < 9;
    
    // Determine section:
    if (topRow && leftCol)        // Section 1
      return 0;
    else if (topRow && centerCol) // Section 2
      return 9;     
    else if (topRow && rightCol)  // Section 3
      return 18;     
    else if (midRow && leftCol)   // Section 4
      return 27;
    else if (midRow && centerCol) // Section 5
      return 36;
    else if (midRow && rightCol)  // Section 6
      return 45;
    else if (botRow && leftCol)   // Section 7
      return 54;
    else if (botRow && centerCol) // Section 8
      return 63;
    else if (botRow && rightCol)  // Section 9
      return 72;
    else
      return -1;       // section not found:
  }
  
  
  /**
   * scanGroup3:
   * 
   * Determines the possible candidates for group 3
   * 
   * @param position    the row and column pair
   */
  private void scanGroup3(int index) {
    for (int i = index; i < (index + 9); ++i) {
		if (index > -1) {
			Cell temp = grid[i];
			this.checkCandidate(temp.getText());
		}
    }
  }
  
  
  /**
   * Determines the cell's value to see if it is in the list
   * 
   * @param row 	row of the cell to be evaluated
   * @param col		column of the cell to be evaluated
   */
  private void evalCell(int row, int col) {
    int index = (new Map(row, col)).mapRowAndCol();
	Cell curr = grid[index];
    String value = curr.getText();
    checkCandidate(value);
  }
  
  
  /**
   * evalGroups:
   * 
   * Populates the candidate list for the given cell. Evaluates each 
   * group for the given cell. A group consists of: 
   * Group 1) Cells in the same row
   * Group 2) Cells in the same column 
   * Group 3) Cells in the same 3x3 sub-grid
   */
  private void evalGroups() {
    // position of cell to evaluate
    int gridRow = cell.getRow();
    int gridCol = cell.getCol();
    int gridHeight = 10;
    
    
    // Group 1:
    for (int i = 1; i < gridHeight; ++i) {
    	evalCell(gridRow, i);
    }
    
    // Group 2:
    for (int i = 1; i < gridHeight; ++i) {
    	evalCell(i, gridCol);
    }
    
    // Group 3:
    int index = getPosition(gridRow, gridCol);
    if (index > -1);
    	scanGroup3(index);
  }
  
  
  /**
   * Scans the entire grid to populate a list for possible values the cell
   * can contain.
   * 
   * @return    the list of candidates for the given cell
   */
  public ArrayList<Integer> scanForCandidates() {
    evalGroups();
    return candidateList;
  }
  
  public String getCandidatesAsString() {
	  String list = "";
	  for (int i = 0; i < candidateList.size(); ++i) {
		  list += ", " + candidateList.get(i); 
	  }
	  
	  return list;
  }
}