/**
 * HiddenSingle Class: Uses the hidden single algorithm to find the
 * value for the cell. A hidden single is when a cell, X, must have 
 * the value V because no other cell in a row, column or box could 
 * have that value
 * 
 * Aaron Guevarra
 * U. of Illinois, Chicago 
 * CS342, Fall 2017 
 * Project #03
 *
 */
import java.util.ArrayList;

public class HiddenSingle {
	private Cell cell;
	private int row, col;
	
	private Cell[] grid;
	private int[] valueCount; 
	
	/**
	 * Constructor:
	 * 
	 * @param cell	Cell to be analyzed
	 */
	public HiddenSingle(Cell cell, Cell[] grid) {
		this.cell = cell;
		this.grid = grid;
		row = cell.getCol();
		col = cell.getRow();
		valueCount = new int[9];
		
		// init valueCount to 0
		this.resetValueCount();
	}
	
	/**
	 * Runs the Hidden Single Algorithm
	 * 
	 * @return	the hidden value of the cell, otherwise 0
	 */
	public int getHiddenVal() {
		
		// search row
		int rowResult = scanRow();
		if (rowResult != 0)
			return rowResult;
		
		// search column
		int colResult = scanCol();
		if (colResult != 0)
			return colResult;
		
		// search box
		int boxResult = scanBox();
		if (boxResult != 0)
			return boxResult;
		
		return 0;
	}
	  
	  
	/**
	 * Searches for an index with a count of 1. This represents
	 * that there was only one candidate list in the entire row
	 * with that value.
	 * @return	 the hidden value, otherwise 0
	 */
	private int findHiddenVal() {
		for (int i = 0; i < valueCount.length; ++i) {
			boolean isInList = cell.getCandidateList().contains(valueCount[i]);
			if (valueCount[i] == 1 && isInList)
				return i + 1;
		}
		
		return 0;
	}
	
	
	/**
	 * Scans the row that the cell is located at to
	 * determine a hidden value.
	 * 
	 * @return	 the hidden value, otherwise 0
	 */
	private int scanRow() {
		this.resetValueCount();
		
		int gridHeight = 9;
		for (int i = 0; i < gridHeight; ++i) {
			int index = (new Map(row, i)).mapRowAndCol();
			Cell curr = grid[index];
			
			this.incrementValues(curr);
		}
		
		return this.findHiddenVal();
	}
	
	
	/**
	 * Scans the row that the cell is located at to determine a 
	 * hidden value.
	 * 
 	 * @return	the hidden value, otherwise 0
	 */
	private int scanCol() {
		this.resetValueCount();
		
		int gridHeight = 9;
		for (int i = 0; i < gridHeight; ++i) {
			int index = (new Map(i, col)).mapRowAndCol();
			Cell curr = grid[index];
			this.incrementValues(curr);
		}
		return this.findHiddenVal();
	}
	
	
	/**
	 * Scans the box that the cell is located at to determine 
	 * a hidden value.
	 * 
	 * @return	the hidden value, otherwise 0
	 */
	private int scanBox() {
		this.resetValueCount();
		
		int index = getStartIndex(row, col);
		int subGridLength = 9;
		for (int i = index; i < subGridLength; ++i) {
			Cell curr = grid[i];
			this.incrementValues(curr);
		}
		return this.findHiddenVal();	
	}
	
	
	/**
	 * getStartIndex:
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
	 private int getStartIndex(int row, int col) {
	    // position of each section:
	    boolean topRow = row < 3;
	    boolean midRow = row >= 3 && row < 6;
	    boolean botRow = row >= 6 && row < 9;
	    
	    boolean leftCol = col < 3;
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
	    
	    return -1;       // section not found:
	}
	  
	  
	/**
	 * Compares if the value is in the Cell being analyzed
	 * 
	 * @param comp	the cell being compared
	 * @return		value that is not in the cell, otherwise 0    
	 */
	public void incrementValues(Cell cell) {
		ArrayList<Integer> list = cell.getCandidateList();
		for (int i = 0; i < list.size(); ++i) {
			int valueIndex = list.get(i) - 1;
			valueCount[valueIndex]++;
		}
	}
	
	
	/**
	 * Reset valueCount to 0
	 * 
	 */
	public void resetValueCount() {
		for (int i = 0; i < valueCount.length; ++i) {
			valueCount[i] = 0;
		}
	}
}
