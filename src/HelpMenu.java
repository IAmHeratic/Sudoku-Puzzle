/* Author: Jose Rodriguez
 * CS 342 Fall 2017
 * Project 3: Sudoku Solver
 * University of Illinois at Chicago
 * 
 * This class creates a help menu with about, play, sudoku menu items.
 * The Sudoku menu item displays instructions on how to play the game.
 * Play menu item displays info on how to use this GUI program with Sudoku.
 * About menu item displays developer info.
*/


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


public class HelpMenu extends JMenu
{
	private JMenuItem sudokuHelp;
	private JMenuItem playHelp;
	private JMenuItem aboutHelp;
	
	public HelpMenu(String name)
	{
		super(name);
		
		this.setMnemonic('H');

	    // set up Sudoku (Help) menu item
	    sudokuHelp = new JMenuItem( "How to Play" );
	    sudokuHelp.setMnemonic( 'S' );
	    this.add( sudokuHelp );
	    sudokuHelp.addActionListener(
	                                
	       new ActionListener() {  // anonymous inner class
	          public void actionPerformed( ActionEvent event )
	          {
	             JOptionPane.showMessageDialog( HelpMenu.this,
	                                      "How to play Sudoku:\n\nSudoku is one of the most popular puzzle\n" +
	                                      "games of all time. The goal of Sudoku is to fill\n" +
	                                      "a 9x9 grid with numbers so that each row, column\n" +
	                                      "and 3x3 section contain all of the digits between\n" +
	                                      "1 and 9 only once.",
	                                      "Sudoku", JOptionPane.PLAIN_MESSAGE );
	          }
	       }  // end anonymous inner class
	                                
	    ); // end call to addActionListener
		
		
		// Set up Play (Help) menu item
	    playHelp = new JMenuItem( "User Interface" );
	    playHelp.setMnemonic( 'P' );
	    this.add( playHelp );
	    playHelp.addActionListener(
	                                
	       new ActionListener() {  // anonymous inner class
	          public void actionPerformed( ActionEvent event )
	          {
	        	  // TODO
	             JOptionPane.showMessageDialog( HelpMenu.this,
	                                       "To insert a value in a Cell, first click on the corresponding button\n" +
	                                    	"on the right with the number you want to place. Then simply click\n" +
	                                    	"the Cell to place the number. The current digit button will become\n" +
	                                    	"green.\n\n" +
	                                    	"To clear a Cell, click the 'Clear' button first, then the Cell you\n" +
	                                    	"wish to clear. The 'Clear' button will turn Orange.\n\n" +
	                                    	"To get the list of possible values in a Cell, click the '?' button\n" +
	                                    	"on the right, followed by a Cell. The values wil be displayed on\n" +
	                                    	"the top.\n\n" +
	                                    	"To load a puzzle, go to File -> Load, you'll be prompted to select" +
	                                    	"a file. The puzzle in that file will then be loaded.\n\n" +
	                                    	"To save the current puzzle go to File -> Save, you'll be asked to\n" +
	                                    	"name the file and select a directory to save it to.\n\n" +
	                                    	"To exit, click the 'x' at the top right or File -> Quit.\n\n" +
	                                    	"Hints -> Error Check, this makes sure the values you enter in any\n" +
	                                    	"Cell is a possible value for that Cell.\n\n" +
	                                    	"To solve a Cell on the grid, you can go to Hints -> <algorithm> , " +
	                                    	"the algorithms are:\n\n" + 
	                                    	"       Single, Hidden Singles, Locked Candidates, Naked Pairs.\n\n" +
	                                    	"Hints -> Solve Cells will attempt to solve as many Cells as possible\n" +
	                                    	"using the four algorithms above.",
	                                      "Play", JOptionPane.PLAIN_MESSAGE );
	          }
	       }  // end anonymous inner class
	                             
	    ); // end call to addActionListener
	    
	    
	    // Set up About (Help) menu item
	    aboutHelp = new JMenuItem( "About" );
	    aboutHelp.setMnemonic( 'A' );
	    this.add( aboutHelp );
	    aboutHelp.addActionListener(
	                                
	       new ActionListener() {  // anonymous inner class
	          public void actionPerformed( ActionEvent event )
	          {
	        	  JOptionPane.showMessageDialog( HelpMenu.this,
	        			  "Authors:\n\n" +
                          "Jason Guo - jguo28\n" + 
                          "Aaron Guevarra - guevarr2\n" + 
                          "Jose Rodriguez -  jrodr41\n",
                         "About", JOptionPane.PLAIN_MESSAGE );
	          }
	          
	       }  // end anonymous inner class
	    ); // end call to addActionListener
	}
	
	
}  // End HelpMenu Class