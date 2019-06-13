
/*
 * Cell Class: A custom JButton that keeps track of it's x, y position and contain an ArrayList of
 * candidates. The candidates are the potential values that the cell can hold. The cell also has a
 * isFixed value that determines if the cell can be modified.
 * 
 * Aaron Guevarra 
 * U. of Illinois, Chicago 
 * CS342, Fall 2017 
 * Project #03
 */

import java.util.ArrayList;
import javax.swing.JButton;

public class Cell extends JButton {
 private ArrayList<Integer> candidateList;

 private String value;
 private boolean isFixed;
 private int row;
 private int col;
 private int index;

 
 public Cell(int newIndex, int xPos, int yPos, String text, boolean isCellFixed) {
   
   super(text);

   candidateList = new ArrayList<Integer>();
   index = newIndex;
   value = text;
   isFixed = isCellFixed;
   row = xPos;
   col = yPos;
   calculateIndex();
 }
 
 
 /**
  * Constructor:
  * 
  * @param text  value of the cell
  * @param xPos  x position of the cell
  * @param yPos   y position of the cell
  */
 public Cell(int xPos, int yPos, String text, boolean isCellFixed) {
  super(text);

  candidateList = new ArrayList<Integer>();
  value = text;
  isFixed = isCellFixed;
  row = xPos;
  col = yPos;
  calculateIndex();
 }

 
 /**
  * Constructor: Cell constructor that takes an index for the location
  * instead of using row and column
  * 
  * @param pIndex     index of the cell in the grid
  * @param text   value of the cell
  * @param isCellFixed   determines if the cell can be modified
  */
 public Cell(int pIndex, String text, boolean isCellFixed) {
  candidateList = new ArrayList<Integer>();
  value = text;
  isFixed = isCellFixed;
  row = -1;
  col = -1;
  index = pIndex;
 }

 
 /**
  * calculateIndex:
  * 
  * Calculates the index based on the entered row and col
  */
 public void calculateIndex() {
  Map converter = new Map(row, col);
  index = converter.mapRowAndCol();
 }

 
 /**
  * getValue:
  * 
  * Returns the value of the cell
  * 
  * @return the value of the cell
  */
 public String getValue() {
  return value;
 }

 
 /**
  * addCandidate:
  * 
  * Adds a possible value in the candidate list
  * 
  * @param candidate
  *            an potential value for the cell
  */
 public void addCandidate(int candidate) {
  candidateList.add(candidate);
 }

 
 /**
  * removeCandidate:
  * 
  * Removes a possible value in the candidate list
  * 
  * @param candidate
  *            the value to be removed
  */
 public void removeCandidate(int candidate) {
  candidateList.remove(Integer.valueOf(candidate));
 }

 
 /**
  * setCandidateList:
  * 
  * Sets a new list of candidates to the candidateList
  * 
  * @param list
  *            the new candidate list
  */
 public void setCandidateList(ArrayList<Integer> list) {
  candidateList = list;
 }

 
 /**
  * getCandidateList:
  * 
  * Returns the list of candidates as an ArrayList of integers
  * 
  * @return the list of candidate values
  */
 public ArrayList<Integer> getCandidateList() {
  return candidateList;
 }
 
 /**
  * getRow:
  * 
  * Returns the x position of the cell
  * 
  * @return the x position of the cell
  */
 public int getRow() {
  return row;
 }

 
 /**
  * getCol:
  * 
  * Returns the y position of the cell
  * 
  * @return the y position of the cell
  */
 public int getCol() {
  return col;
 }

 
 /**
  * Returns the index of the cell
  * 
  * @return the index of the cell
  */
 public int getIndex() {
  return index;
 }

 
 public void setRow(int newRow) {
	 row = newRow;
 }
 
 
 public void setCol(int newCol) {
	 col = newCol;	 
 }
 
 
 public void setIndex(int newIndex) {
	 index = newIndex;
 }
 
 public void setValue(String val) {
	 value = val;
 }
 
 
 /**
  * isFixed:
  * 
  * Determines if the cell can be modified
  * 
  * @return true if the cell can be modified
  */
 public boolean isFixed() {
  return isFixed;
 }

 
 /**
  * setFixed:
  * 
  * Makes the cell 
  */
 public void setFixed() {
  isFixed = true;
  }
}
