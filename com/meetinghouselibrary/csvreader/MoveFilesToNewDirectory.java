package com.meetinghouselibrary.csvreader;

import java.io.File;
//This will read a CSV of any moved files to new directory
//http://www.mkyong.com/java/how-to-move-file-to-another-directory-in-java/
public class MoveFilesToNewDirectory {

	public static void main(String[] args)
    {	
    	try{
 
    	   File afile =new File("C:\\folderA\\Afile.txt");
 
    	   if(afile.renameTo(new File("C:\\folderB\\" + afile.getName()))){
    		System.out.println("File is moved successful!");
    	   }else{
    		System.out.println("File is failed to move!");
    	   }
 
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
	
}
