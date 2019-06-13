/* Author: Jason Guo
 * CS 342 Fall 2017
 * Project 3: Sudoku Solver
 * University of Illinois at Chicago
 * 
 * This class stores the data read in from a text file chosen by the user from the computer. 
 * It allows the user to choose what kind of puzzle they want to solve from the data within
 * the selected text file. (Easy, Medium, Hard).
 */

import java.io.File;


import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.*;

public class fileLoader {
	private ArrayList<Cell> list;
	
	public fileLoader(){
		list = new ArrayList<Cell>();
	}

	public void loadFile(){
		
		JFileChooser file = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue = file.showOpenDialog(null); 
		File selectedFile = null;
		int row, col, value;
		
		//determines if the file is opened/accepted
		if (returnValue == JFileChooser.APPROVE_OPTION){
			selectedFile = file.getSelectedFile();	
			System.out.println(selectedFile.getAbsolutePath()); // check the file route		
		}
		else {
			System.err.println("File not found ... exiting...");
			System.exit(-1);
		}
		
		try{
			Scanner sc = new Scanner(selectedFile);
			while(sc.hasNext()){
				row = sc.nextInt();
				col = sc.nextInt();
				value = sc.nextInt();
				
				//returns the index of the row col, according to our grid
				Map map = new Map(row, col);
				int index = map.mapRowAndCol();
				
				list.add( new Cell(index, row, col, Integer.toString(value), true));
			}
			
		}
		catch(Exception e){
			System.err.println("Error: " + e.getMessage());
		}
		
		
	}
	
	public ArrayList<Cell> getList(){
		return list;
	}
	
}