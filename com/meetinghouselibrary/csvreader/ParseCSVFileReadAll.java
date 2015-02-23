package com.meetinghouselibrary.csvreader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.opencsv.CSVReader;

	

	public class ParseCSVFileReadAll 
	{

	public static void main(String[] args) 
	{
	       
			  //String[] filenames = new String[]{"http://meetinghouselibrary.com/?wpdmdl=10334"
			//		  							, "http://meetinghouselibrary.com/?wpdmdl=10340"};// instantiate String array with file names
			  DownloadFileFromURL.main(null);
			  DownloadLibraries.main(null);
			  //String filename = "/Users/dwariner/Downloads/all-videos-downloaded.csv";
			  File aDirectory = new File(System.getProperty("user.dir")+"/Media Index/");
			  String workingDir = System.getProperty("user.dir")+"/Media Index/";
			  //File aDirectory = new File(workingDir);
			  System.out.println(aDirectory);
			  String[] filesInDir = aDirectory.list();
			  for ( int i=0; i<filesInDir.length; i++ )
			    {
			      System.out.println( "file: " + filesInDir[i] );
			    }
						
			  for (String filenameArg : filesInDir)
			  {
					ParseCSVFileReadAll parseCSVFile = new ParseCSVFileReadAll();
	
			        System.out.println("Starting to parse CSV Library files using opencsv");
			        parseCSVFile.parseUsingOpenCSV(workingDir+filenameArg);				
			  }
//			ParseCSVFileReadAll parseCSVFile = new ParseCSVFileReadAll();
//
//	        System.out.println("Starting to parse CSV file using opencsv");
//	        parseCSVFile.parseUsingOpenCSV(filename);
	}

	

	private void logInfo(String info) {
		// open file
		// append to file. with a date and time stamp.
		// close file.
		System.out.println(info);
	}
	private void parseUsingOpenCSV(String filename) 
	 {
	 CSVReader reader;
	  try 
	  {
		  //String localdirectory = "/Users/dwariner/Downloads/Meetinghouse Library/Media/";
		  String localDirectory = System.getProperty("user.dir")+"/Media Library/";
		  //String localDirectory = "Media/";
		  
		  System.out.println(filename);
		  
		  reader = new CSVReader(new FileReader(filename));
	        String[] row;
	        List<?> content = reader.readAll();
	        ExecutorService threadPool = Executors.newFixedThreadPool(3);

	        boolean firstRow = true;
	        
	        for (Object object : content) 
	        {
                row = (String[]) object;
                //for (int i = 0; i < row.length; i++) 
                //{
                        // display CSV values
                //System.out.println("Cell column index: " + i);
                //System.out.println("Cell Value: " + row[i]);
                //logInfo("Cell Value: " + row[0] + "," + row[1] + "," + row[2] + "," + row[3]);
                logInfo("File Name:   " + localDirectory+row[8]+"/"+row[13]);
                logInfo("Server Name: " + localDirectory+row[8]+"/"+row[3]+"."+row[9].substring(row[9].length()-3));
                logInfo("URL:         " + row[9]);
                logInfo("Directory:   " + localDirectory+row[8]);
                if (row[9] != null && !row[9].isEmpty()) {
                logInfo("Media Type:  " + row[9].substring(row[9].length()-3));
                }
                logInfo("-------------");
	                //}
	          
                if (firstRow) {
                	firstRow = false;
                	continue;
                }
                //if (row[8] != null && !row[8].isEmpty())
	            //DownloadFile dload = new DownloadFile("Cell Value: " + row[0] + "," + row[1] + "," + row[2] + "," + row[3]);
	            URL link = new URL(row[9]);
	            //String fulllocalpath = localDirectory+row[3];
	            File localDir = new File(localDirectory+row[8]); //The file that will be saved on your computer
	        	//File localDir = new File(fulllocalpath); //The file that will be saved on your computer
	        	File localFile = new File(localDirectory+row[8]+"/"+row[3]+"."+row[9].substring(row[9].length()-3));
	        	File localFileName = new File(localDirectory+row[8]+"/"+row[13]);
	        	//File localFile = new File(row[3]+row[1]+"."+row[2].substring(row[2].length()-3)); //The file that will be saved on your computer
	        	 

	        	
	        	//File file = new File("C:\\user\\Desktop\\dir1\\dir2\\filename.txt");
	        	//localFile.getParentFile().mkdir();
//	        	FileWriter writer = new FileWriter(file);
	        	
//	        	File dir = new File("C:\\user\\Desktop\\dir1\\dir2");
	        	//localFile.mkdirs();
//	        	File file = new File(dir, "filename.txt");
//	        	FileWriter newJsp = new FileWriter(file);
	        	//if (row[2] != null && !row[2].isEmpty()) {
	        	if(localFileName.renameTo(localFile)){
    	            logInfo("File Name found and renamed success");
	        	}
	        		if (localFile.exists()) {
		        		 logInfo("Local File already exists. Skipping...");
		        		// File (or directory) with old name
//		        		    File file = new File("oldname");
//
//		        		    // File (or directory) with new name
//		        		    File file2 = new File("newname");
//		        		    if(file2.exists()) throw new java.io.IOException("file exists");
//
//		        		    // Rename file (or directory)
//		        		    boolean success = file.renameTo(file2);
//		        		    if (!success) {
//		        		        // File was not successfully renamed
//		        		    }
		        		 
//		        		 if(localFileName.renameTo(localFile)){
//		        	            System.out.println("File rename success");;
//		        	        }else{
//		        	            System.out.println("File rename already exists");
//		        	       
//		        	            continue;
//		        	        }
		        		 
		        		 
		        		 continue;
		        	} else {      		
		        		if (!localDir.exists()) {
			        		if (localDir.mkdirs()) {
			    				logInfo("Directory is created!");
			    			} else {
			    				logInfo("Local Directory already exists. Skipping...");
			    			}
		        		}
		        		logInfo("Downloading new file...");
		        	}
		           	DownloadFile download = new DownloadFile(localFile, link);
		           	threadPool.execute(download);
		        }
		        
		        threadPool.shutdown();
		  }
	  catch (FileNotFoundException e) 
	  {
		  logInfo(e.getMessage());
	  }
	  catch (IOException e) 
	  {
		  logInfo(e.getMessage());
	  }
	  }
	}