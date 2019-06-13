/* Author: Jose Rodriguez
 * CS 342 Fall 2017
 * Project 3: Sudoku Solver
 * University of Illinois at Chicago
 * 
 * This class creates a file menu with load, save, and quit menu items
 * The Load menu item loads a puzzle from a file selected by the user
 * Save menu item stores the current puzzle into a text file
 * Quit: Exits the program.
*/


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


public class FileMenu extends JMenu
{
	private JMenuItem loadPuzzle;
	private JMenuItem savePuzzle;
	private JMenuItem quit;
	
	public FileMenu(String name, Cell gridButtons[])
	{
		super(name);
		
		this.setMnemonic('F');

	    // set up Load Puzzle (File) menu item
	    loadPuzzle = new JMenuItem( "Load" );
	    loadPuzzle.setMnemonic( 'L' );
	    this.add( loadPuzzle );
	    loadPuzzle.addActionListener(
	                                
	       new ActionListener() {  // anonymous inner class
	          public void actionPerformed( ActionEvent event )
	          {
	        	  
	        	  // User selects the file containing the Sudoku puzzle data
	        	  fileLoader temp = new fileLoader();
	        	  temp.loadFile();
	        	  
	        	  // Grab values to be inserted
	        	  ArrayList<Cell> temp2 = temp.getList();
	        	  
	        	  // Insert the values into the Cells!
	        	  int buttonIndex;
	        	  String buttonValue;
	        	  
	        	  for(Cell currentCell: temp2) {
	        		  
	        		  buttonValue = currentCell.getValue();
	        		  buttonIndex = currentCell.getIndex();
	        		  gridButtons[buttonIndex].setText(buttonValue);
					  gridButtons[buttonIndex].setFixed();
	        	  }
	              
	          }
	       }  // end anonymous inner class
	                                
	    ); // end call to addActionListener
		
		
		// Set up Save Puzzle (File) menu item
	    savePuzzle = new JMenuItem( "Save" );
	    savePuzzle.setMnemonic( 'S' );
	    this.add( savePuzzle );
	    savePuzzle.addActionListener(
	                                
	       new ActionListener() {  // anonymous inner class
	          public void actionPerformed( ActionEvent event )
	          {
	        	  // Grab the values in the Cells at this point,
	        	  // and save them to a txt file in the format:
	        	  // Row Col Value
	        	  fileSaver temp = new fileSaver(gridButtons);
	        	  temp.saveFile();
	        	  
	          }
	       }  // end anonymous inner class
	                             
	    ); // end call to addActionListener
	    
	    
	    // Set up Quit (File) menu item
	    quit = new JMenuItem( "Quit" );
	    quit.setMnemonic( 'Q' );
	    this.add( quit );
	    quit.addActionListener(
	                                
	       new ActionListener() {  // anonymous inner class
	          public void actionPerformed( ActionEvent event )
	          {
	             System.exit(0);
	          }
	          
	       }  // end anonymous inner class
	    ); // end call to addActionListener
	}
	
	
}  // End FileMenu Class