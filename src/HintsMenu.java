/* Author: Jose Rodriguez
 * CS 342 Fall 2017
 * Project 3: Sudoku Solver
 * University of Illinois at Chicago
 * 
 * This class creates a hints menu which aids the player in solving
 * the Sudoku puzzle. The following are menu items in the Help Menu:
 * 
 *  -> The Error Check check-box, when checked, only allows the
 *     player to input possible numbers in any cell until unchecked.
 *     
 *  -> Single Algorithm: Finds a cell that can be solved with this
 *     algorithm. This is the case when a cell has only one possible
 *     value. 
 *  
 *  -> Hidden Single Algorithm: Finds a cell that can be solved with
 *     this algorithm. A hidden single is when a cell must have the
 *     value because no other cell in a row, column or box could
 *     have that value.
 *     
 *  -> Locked Candidates Algorithm: Finds a cell that can be solved
 *     with this algorithm. When it is determined that a value must
 *     be contained in the cells in the first group and these cells
 *     are also part of a second group, this value can be removed
 *     from the candidate lists for the other cells in the second group.
 *     
 *  -> Naked Pairs Algorithm: Finds a cell that can be solved
 *     with this algorithm. When a group (row, column or box) has
 *     two cells that have the same two value candidate list those
 *     two values must exist in those two cells. Those two values
 *     can be removed from any other candidate lists in the group.
 *     
 *  -> Solve Cells: repeatedly process all of the blank cells until
 *     none of the algorithms can resolve any blank cell.
*/


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JOptionPane;


public class HintsMenu extends JMenu
{
	private JCheckBoxMenuItem errorCheck;
	
	private JMenuItem singleAlg;
	private JMenuItem hSingleAlg;
	private JMenuItem lockCandAlg;
	private JMenuItem nakedPairAlg;
	private JMenuItem solveCells;
	
	public HintsMenu(String name, Cell gridButtons[])
	{
		super(name);

	    // set up error check (Hints) menu item
		errorCheck = new JCheckBoxMenuItem( "Error Check" );
	    this.add( errorCheck );
	    errorCheck.addActionListener(
	                                
	       new ActionListener() {  // anonymous inner class
	          public void actionPerformed( ActionEvent event )
	          {
	        	  
	        	  // TODO
	        	  
	        	  
	          }
	       }  // end anonymous inner class
	                                
	    ); // end call to addActionListener
	    
		
		// Set up single algorithm (Hints) menu item
	    singleAlg = new JMenuItem( "Single Alg" );
	    this.add( singleAlg );
	    singleAlg.addActionListener(
	                                
	       new ActionListener() {  // anonymous inner class
	          public void actionPerformed( ActionEvent event )
	          {
	        	  // TODO
              for (int i = 0; i < gridButtons.length; i++){
                SingleAlg check = new SingleAlg(gridButtons[i]);
                if(check.isSingle()){
                  String text = Integer.toString(gridButtons[i].getCandidateList().get(0));
                  gridButtons[i].setText(text);
                  gridButtons[i].setValue(text);
                }
              }
              
	             JOptionPane.showMessageDialog( HintsMenu.this,
	                                       "This menu item solves a single cell with Single Algorithm.",
	                                      "Single Algorithm", JOptionPane.PLAIN_MESSAGE );
	          }
	       }  // end anonymous inner class
	                             
	    ); // end call to addActionListener
	    
	    
	    // Set up Hidden Singles algorithm (Hints) menu item
	    hSingleAlg = new JMenuItem( "Hidden Singles" );
	    this.add( hSingleAlg );
	    hSingleAlg.addActionListener(
	                                
	       new ActionListener() {  // anonymous inner class
	          public void actionPerformed( ActionEvent event )
	          {
	        	  // TODO
	        	  JOptionPane.showMessageDialog( HintsMenu.this,
                          "This menu item solves a single cell with Hidden Singles Algorithm.",
                         "Hidden Singles Algorithm", JOptionPane.PLAIN_MESSAGE );
	          }
	          
	       }  // end anonymous inner class
	    ); // end call to addActionListener
	    
	    
	    // Set up Locked Candidates algorithm (Hints) menu item
	    lockCandAlg = new JMenuItem( "Locked Candidates" );
	    this.add( lockCandAlg );
	    lockCandAlg.addActionListener(
	                                
	       new ActionListener() {  // anonymous inner class
	          public void actionPerformed( ActionEvent event )
	          {
	        	  // TODO
	        	  JOptionPane.showMessageDialog( HintsMenu.this,
                          "This menu item solves a single cell with Locked Candidates Algorithm.",
                         "Locked Candidates Algorithm", JOptionPane.PLAIN_MESSAGE );
	          }
	          
	       }  // end anonymous inner class
	    ); // end call to addActionListener
	    
	    
	    // Set up Naked Candidates algorithm (Hints) menu item
	    nakedPairAlg = new JMenuItem( "Naked Pairs" );
	    this.add( nakedPairAlg );
	    nakedPairAlg.addActionListener(
	                                
	       new ActionListener() {  // anonymous inner class
	          public void actionPerformed( ActionEvent event )
	          {
	        	  // TODO
	        	  JOptionPane.showMessageDialog( HintsMenu.this,
                          "This menu item solves a single cell with Naked Pairs Algorithm.",
                         "Naked Pairs Algorithm", JOptionPane.PLAIN_MESSAGE );
	          }
	          
	       }  // end anonymous inner class
	    ); // end call to addActionListener
	    
	    
	 // Set up solve cells (Hints) menu item
	    solveCells = new JMenuItem( "Solve Cells" );
	    this.add( solveCells );
	    solveCells.addActionListener(
	                                
	       new ActionListener() {  // anonymous inner class
	          public void actionPerformed( ActionEvent event )
	          {
	        	  // TODO
	        	  JOptionPane.showMessageDialog( HintsMenu.this,
                          "This menu item solves as many cells as possible with all four algorithms.",
                         "Solve Cells", JOptionPane.PLAIN_MESSAGE );
	          }
	          
	       }  // end anonymous inner class
	    ); // end call to addActionListener
	}
	
	
	// Check if error check is checked, return true/false
	public boolean errorCheckSelected()
	{
		return errorCheck.isSelected();
	}
	
	
	
}  // End HintsMenu Class