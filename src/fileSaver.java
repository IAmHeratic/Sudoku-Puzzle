/* Author: Jason Guo
 * CS 342 Fall 2017
 * Project 3: Sudoku Solver
 * University of Illinois at Chicago
 * 
 * This class save the data from the current sudoku grid and saves it in a txt file to a directory
 * chosen by the user. It allows the user to continue from where they left off.
 */

import java.io.*;
import javax.swing.*;
import java.util.*;

public class fileSaver{
  
  private ArrayList<Cell> list;
  private Cell buttons[];
  
  public fileSaver(Cell gridButtons[]){
      buttons = gridButtons; 
  }
  
  public void saveFile(){
    
    try {
      JFileChooser jFileChooser = new JFileChooser();
      jFileChooser.setSelectedFile(new File("nameOfFile"));
      jFileChooser.showSaveDialog(null);
      jFileChooser.setCurrentDirectory(jFileChooser.getCurrentDirectory());
      FileWriter output = new FileWriter(jFileChooser.getSelectedFile() + ".txt");        
            
      
      for(int i = 0; i < buttons.length; ++i){
        
        if(!(buttons[i].getText().equals(""))){ // if it is not a blank button, read the button row/col/value
        output.write(Integer.toString(buttons[i].getRow()));
        output.write(" "); // add a space
        output.write(Integer.toString(buttons[i].getCol()));
        output.write(" "); // add a space
        output.write(buttons[i].getText());
        output.write("\n"); //new line every 3 values
        }
      }
      
      output.close();
      
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }


  }
}
