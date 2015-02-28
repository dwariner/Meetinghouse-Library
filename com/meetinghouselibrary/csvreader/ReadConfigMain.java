package com.meetinghouselibrary.csvreader;

import java.io.IOException;

/**
 * @author Crunchify.com
 * 
 */

public class ReadConfigMain {
	
	public static void main(String[] args) throws IOException {
		GetPropertyValues properties = new GetPropertyValues();
		properties.getPropValues();		
		
		//Object mediaDirectory;
		//properties.getPropValues(mediaDirectory)
		//System.out.println( ": " + properties.getPropValues());
	}

}
