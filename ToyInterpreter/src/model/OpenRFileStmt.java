package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import exceptions.FileAlreadyOpenedException;
import exceptions.FileNotFoundException;

public class OpenRFileStmt implements IStmt{
	private static int fd = 1;
	private String id, fileName;
	
	public OpenRFileStmt(String id, String fileName){
		this.id = id;
		this.fileName = fileName;
	}

	@Override
	public PrgState execute(PrgState state)
			throws FileNotFoundException, FileAlreadyOpenedException {
		
		/// check if filename is not already in the file table
		for (Tuple<String, BufferedReader> a : state.getFileTable().values())
			if (a.getFirst() == this.fileName)
				throw new FileAlreadyOpenedException("FileAlreadyOpenedException at : " + this.toString() + "\nThe file " + this.fileName + " is already open.");
		
		/// create a file with the provided name
		File f = new File(this.fileName);
		
		/// if such file does not exist throw exception
		if ( !f.exists() )
			throw new FileNotFoundException("FileNotFoundException at : " + this.toString() + "\n" + "The file " + this.fileName + " does not exist.");
		
		/// increment file descriptor
		int actualFd = ++OpenRFileStmt.fd; /// static
		
		/// map the corresponding fileReader to the new file descriptor
		try {
			state.getFileTable().put(actualFd, new Tuple<String, BufferedReader>(this.fileName, new BufferedReader(new FileReader(this.fileName))));
		} catch (java.io.FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		/// store in the symbol table the file descriptor to the specific id
		state.getSymTable().put(this.id, actualFd);
		
		return state;
	}
	
	@Override
	public String toString(){
		return "openRFile (" + this.id + "," + this.fileName + ")";
	}
}
