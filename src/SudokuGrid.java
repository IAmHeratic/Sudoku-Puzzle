/* Author: Jose Rodriguez\

 * CS 342 Fall 2017
 * Project 3: Sudoku Solver
 * University of Illinois at Chicago
 * 
 * This program is a GUI implementation of the popular game Sudoku.
 * Features include 4 different algorithms that attempt to solve individual cells,
 * a button that displays a single cell's candidate list (possible numbers),
 * and an eraser.
 * 
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class SudokuGrid extends JFrame implements ActionListener {
   
   private GridLayout grid1;     // 10 buttons on right
   private GridLayout grid2;     // 3x3 grid (overall and sub grids)
   
   private JButton    actionButtons[];
   private Cell       gridButtons[];
   
   private Container  container;
   private JMenuBar   menuBar;
   private JPanel     sudokuGrid;
   private JPanel     numberChoices;
   private JLabel     candidateList;
   
   private FileMenu   fileMenu;
   private HelpMenu   helpMenu;
   private HintsMenu  hintsMenu;
   
   private int        currentDigit;
   private int        currentlyClicked;
   private boolean    clearIsClicked;
   private boolean    helpIsClicked;
   
   
   // Set up GUI
   public SudokuGrid()
   {
     super( "Sudoku" );

     // Set up grid layout for grid buttons and action buttons
     grid1 = new GridLayout(11, 1);
     grid2 = new GridLayout(3, 3);
     
     // Mark as unused
     currentDigit     = -1;
     currentlyClicked = 0;    // internal -> index of button
     clearIsClicked   = false;
     helpIsClicked    = false;
     
     // Declare arrays of buttons
     gridButtons   = new Cell[81];
     actionButtons = new JButton[11];
     
     // Get content pane
     container = getContentPane();
     
     
     // Create the panel that will hold the buttons the user
     // clicks to put a number in the grid, and an "eraser"
     numberChoices = new JPanel();
     numberChoices.setLayout(grid1);
     container.add(BorderLayout.EAST, numberChoices);
     
     
     // Create and add action buttons
     for ( int count = 0; count < 9; count++ ) {
             
       actionButtons[ count ] = new JButton( String.valueOf(9 - count) );
       actionButtons[ count ].setBackground(Color.LIGHT_GRAY);
       actionButtons[ count ].addActionListener( 
               
         new ActionListener() {  // anonymous inner class
          public void actionPerformed( ActionEvent event )
          {
           
           // Activate the action button that was clicked
           actionButtonHandler(event);
           
          }
         }  // end anonymous inner class
               
       ); // end call to addActionListener
       
       numberChoices.add( actionButtons[ count ] );
     }
     
     
     // Create and add the clear button
     actionButtons[ 9 ] = new JButton("Clear");
     actionButtons[ 9 ].addActionListener( 
             
       new ActionListener() {  // anonymous inner class
        public void actionPerformed( ActionEvent event )
        {
         
         // Activate the Clear button functionality
         clearButtonHandler(event);
         
        }
       }  // end anonymous inner class
     ); // end call to addActionListener

     actionButtons[ 9 ].setBackground(Color.WHITE);
     numberChoices.add( actionButtons[ 9 ] );
     
     
     
     // Create a 12th button for candidate list button
     // When clicked, displays a candidate list up top
     actionButtons[ 10 ] = new JButton("?");
     actionButtons[ 10 ].addActionListener( 
             
       new ActionListener() {  // anonymous inner class
        public void actionPerformed( ActionEvent event )
        {
         
         // Activate the ? (Candidate List) button functionality
         helpButtonHandler(event);
         
        }
       }  // end anonymous inner class
     ); // end call to addActionListener
     
     actionButtons[ 10 ].setBackground(Color.WHITE);
     numberChoices.add( actionButtons[ 10 ] );
     
     
     // Create and add the label that displays Candidate List info
     candidateList = new JLabel();
     candidateList.setText("");
     
     
     // Create the grid of nine 3x3 grids
     // Create and add all 81 buttons
     sudokuGrid = new JPanel();
     sudokuGrid.setLayout(grid2);
     container.add(BorderLayout.CENTER, sudokuGrid);
     
     int buttonIndex = 0;
     JPanel subGrid;
     
     for(int grids = 0; grids < 9; ++grids) {
      
         subGrid = new JPanel();
         subGrid.setLayout(grid2);
         
         // Fill sub grid with buttons
         for(int innerGrids = 0; innerGrids < 9; ++innerGrids) {
       
             buttonIndex = 9*grids + innerGrids;
          
             // creates a blank grid
             gridButtons[ buttonIndex ] = new Cell( grids, innerGrids, "", false );
             gridButtons[ buttonIndex ].addActionListener(
                     
               new ActionListener() {  // anonymous inner class
                public void actionPerformed( ActionEvent event )
                {
                 
                 // Perform the Grid button action
                 gridButtonHandler(event);
                 
                }
               }  // end anonymous inner class
                     
             ); // end call to addActionListener
             
             gridButtons[ buttonIndex ].setBackground(Color.WHITE);
             subGrid.add( gridButtons[ buttonIndex ] );
      }
         
         //subGrid.setBackground(Color.BLUE);
         subGrid.setBorder( BorderFactory.createLineBorder(Color.BLACK) );
         sudokuGrid.add(subGrid);
     }
     
     
     // Initialize row, column, and index values in Cell buttons
     insertRowAndColumnValues();
     
     
     // Create menu bar and attach it to MenuTest window
     // and add the File, Undo, and Game menu
     menuBar = new JMenuBar();
     setJMenuBar( menuBar );
     
     fileMenu   = new FileMenu("File", gridButtons);
     helpMenu   = new HelpMenu("Help");
     hintsMenu = new HintsMenu("Hints", gridButtons);
     
     menuBar.add(fileMenu);
     menuBar.add(helpMenu);
     menuBar.add(hintsMenu);
     menuBar.add(candidateList);
     
     setSize( 800, 500);
     setVisible( true );
   } // end constructor SudokuGrid
   
   
   // Sudoku Grid actionPerformed
   public void actionPerformed( ActionEvent event )
   {
     container.validate();
   }
   
   
   // Help button: Sets up grid button handler to display
   // candidate list. Independent of action and clear buttons.
   private void helpButtonHandler(ActionEvent event)
   {
	   if(!helpIsClicked) {
		   
		   helpIsClicked = true;
		   actionButtons[ 10 ].setBackground(Color.CYAN);
	   } else {
		   
		   //TODO: Get the candidate list and display it!
		   helpIsClicked = false;
		   candidateList.setText("");
		   actionButtons[ 10 ].setBackground(Color.WHITE);
	   }   
   }
   
   
   
   // Grid button: Clears or places a digit in a grid cell
   // If Error Check is on, ignore digits not in candidate list
   // If Help button clicked, display/update candidate list
   private void gridButtonHandler(ActionEvent event)
   {
    Cell temp = (Cell) event.getSource();
    
    // Check if the clear button is selected
    if(clearIsClicked && !temp.isFixed()) {
  
     temp.setText("");
     gridButtons[temp.getIndex()].setValue("");
     
    // Check if there's currently selected digit
    } else if(currentDigit != -1 && !temp.isFixed()) {
     
     // Change the digit in the cell clicked on
     temp.setText( String.valueOf(currentDigit) ); 
     gridButtons[temp.getIndex()].setValue(String.valueOf(currentDigit));
    }
    
    
    // TODO : Check if the error check box is checked
    //        Check that input digit is part of candidate list
    if( hintsMenu.errorCheckSelected() ) {
    	
    	
    	
    	
    	JOptionPane.showMessageDialog( this,
                   "This is the Error Check dialog box.",
                  "Error Check", JOptionPane.PLAIN_MESSAGE );
    	
    } else {
    
      
    }
    
    
    // TODO : Check if candidate list help button was clicked
    //        Get and Display Candidate List
    if(helpIsClicked) {
     
    	CandidateScanner csc = new CandidateScanner(temp, gridButtons);
    	temp.setCandidateList(csc.scanForCandidates());
    	if (temp.getCandidateList().isEmpty() && !temp.isFixed())
    		candidateList.setText("                 Candidate List: <empty>");
    	
    	if (!temp.isFixed())
    		candidateList.setText(" 				Candidate List: <" + csc.getCandidatesAsString() + " >");
    	
    	        
        
    }
    
   }  // End grid button handler
   
   
   
   // Action button: Selects a digit from the buttons
   // If the user selects a cell, the digit is placed in the cell
   private void actionButtonHandler(ActionEvent event)
   {
    // Change currently selected digit to Light Gray
    actionButtons[currentlyClicked].setBackground(Color.LIGHT_GRAY);
   
    // Unclick the Clear button
    actionButtons[9].setBackground(Color.WHITE);
    clearIsClicked = false;
   
    // Find index of button that was clicked
    // Change clicked button to Green
    JButton temp = (JButton) event.getSource();
    
    for(int count = 0; count < 9; ++count) {
     
     if( temp.equals(actionButtons[count]) ) {
      
      temp.setBackground(Color.GREEN);
      currentDigit     = (9-count);
      currentlyClicked = count;
      break;
     }
    }
   }
   
   
   
   // Clear button: Activates/De-Activates Clear button
   // Active: If the user clicks a cell, clear the cell
   private void clearButtonHandler(ActionEvent event)
   {
    // Get button
    JButton temp = (JButton) event.getSource();
    
    // Check if button was already clicked
    if(clearIsClicked) {
     
     // Change color to White -> not "clicked"
     temp.setBackground(Color.WHITE);
     clearIsClicked = false;
    } else {
     
     // Change color to Orange -> currently "clicked"
     temp.setBackground(Color.ORANGE);
     clearIsClicked = true;
     
     // Set digit buttons to not "clicked"
     for(int count = 0; count < 9; ++count) {
      
      currentDigit = -1;
      actionButtons[ count ].setBackground(Color.LIGHT_GRAY);
     }
    }
   }
   
   
   // Give each Cell button a Row, Column, and "Index" value
   private void insertRowAndColumnValues()
   {
	   int row, col, index;
	   
	   for(row = 1; row < 10; ++row) {
		   for(col = 1; col < 10; ++col) {
			   
			   Map temp = new Map(row, col);
			   index = temp.mapRowAndCol();
			   gridButtons[index].setRow(row);
			   gridButtons[index].setCol(col);
			   gridButtons[index].setIndex(index);
			   gridButtons[index].setValue("");
		   }
	   } // end for loops
   }
   
   
   // Main method
   public static void main( String args[] ) {
     
     SudokuGrid application = new SudokuGrid();
     application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

   }  // End main
   
   
}  // End SudokuGrid