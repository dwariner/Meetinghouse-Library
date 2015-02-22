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


	

public class DownloadLibraries {



	public static void main(String[] args) 
	{
		
			//String filename = "/Users/dwariner/Downloads/Meetinghouse Library/Library Manager.csv";
			String workingDir = System.getProperty("user.dir");
			System.out.println("Current working directory : " + workingDir);
			String filename = workingDir+"/Library Manager.csv";
			DownloadLibraries parseCSVFile = new DownloadLibraries();

	        System.out.println("Starting to parse CSV file using opencsv");
	        parseCSVFile.parseUsingOpenCSV(filename);
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
		  //String localdirectory = "/Users/dwariner/Downloads/Meetinghouse Library/Libraries/";
		  //String localDirectory = "Libraries/";
		  String localDirectory = System.getProperty("user.dir")+"/Media Libraries/";
		  reader = new CSVReader(new FileReader(filename));
	        String[] row;
	        List<?> content = reader.readAll();
	        ExecutorService threadPool = Executors.newFixedThreadPool(3);

	        boolean firstRow = true;
	        
	        for (Object object : content) 
	        {
                row = (String[]) object;
                
                if (firstRow) {
                	firstRow = false;
                	continue;
                }
                
                //for (int i = 0; i < row.length; i++) 
                //{
                        // display CSV values
                //System.out.println("Cell column index: " + i);
                //System.out.println("Cell Value: " + row[i]);
                //logInfo("Cell Value: " + row[0] + "," + row[1] + "," + row[2] + "," + row[3]);
                //logInfo("File Name:   " + localDirectory+row[8]+"/"+row[13]);
                logInfo("Download:    " + row[0]);
                logInfo("File Name:   " + row[1]+".csv");
                logInfo("Directory:   " + localDirectory);
                //logInfo("Server Name: " + localDirectory+row[8]+"/"+row[3]+"."+row[9].substring(row[9].length()-3));
                logInfo("URL:         " + row[2]);
                //logInfo("Directory:   " + localDirectory+row[8]);
                //if (row[9] != null && !row[9].isEmpty()) {
                //logInfo("Media Type:  " + row[9].substring(row[9].length()-3));
                //}
                logInfo("-------------");
	                //}
	          
                
                //if (row[8] != null && !row[8].isEmpty())
	            //DownloadFile dload = new DownloadFile("Cell Value: " + row[0] + "," + row[1] + "," + row[2] + "," + row[3]);
                String downloadFlag = new String(row[0]);
                URL link = new URL(row[2]);
	            //String fulllocalpath = localDirectory+row[3];
	            File localDir = new File(localDirectory); //The file that will be saved on your computer
	        	//File localDir = new File(fulllocalpath); //The file that will be saved on your computer
	        	//File localFile = new File(localDirectory+row[8]+"/"+row[3]+"."+row[9].substring(row[9].length()-3));
	        	File localFile = new File(localDirectory+row[1]+".csv");
	        	//File localFileName = new File(localdirectory+row[1]+".csv");
	        	//File localFile = new File(row[3]+row[1]+"."+row[2].substring(row[2].length()-3)); //The file that will be saved on your computer
	        	 
//	        	if(localFileName.renameTo(localFile)){
//    	            logInfo("File Name found and renamed success");
//	        	}
	        	if(downloadFlag == "Yes"){
    	            logInfo("File was downloaded success");
	        	//}
//	        		if (localFile.exists()) {
//		        		 logInfo("Local File already exists. Skipping...");
//		        		 
//		        		 continue;
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