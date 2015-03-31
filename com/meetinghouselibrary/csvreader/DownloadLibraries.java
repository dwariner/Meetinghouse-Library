package com.meetinghouselibrary.csvreader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.opencsv.CSVReader;


	

public class DownloadLibraries {



	public static void main(String[] args) 
	{
		
			//String filename = "/Users/dwariner/Downloads/Meetinghouse Library/Library Manager.csv";
			StringBuilder sb = new StringBuilder();
	        sb.append(System.getProperty("user.dir"))
	                .append(File.separator)
	                .append("Library Manager.csv");
	        String FileName = sb.toString();
	        
	        StringBuilder mysb = new StringBuilder();
	        sb.append(System.getProperty("user.dir"))
	                .append(File.separator)
	                .append("My Library Manager.csv");
	        String myFileName = mysb.toString();
		
			String workingDir = System.getProperty("user.dir");
			System.out.println("Current working directory : " + workingDir);
			String f1 = null;
			File file = new File(myFileName);
			if (file.exists()) {
					f1 = myFileName;
    				logInfo("Using My Library Manager");
    			} else {
    				f1 = FileName;
    				logInfo("Using Master Library Manager");
    		}
			
			DownloadLibraries parseCSVFile = new DownloadLibraries();

	        System.out.println("Starting to parse CSV file using opencsv");
	        
			String filename = f1;
			parseCSVFile.parseUsingOpenCSV(filename);
	}

	

	private static void logInfo(String info) {
		System.out.println(info);
	}

	void parseUsingOpenCSV(String filename) 
	 {
	 CSVReader reader;
	  try 
	  {
		  //String localdirectory = "/Users/dwariner/Downloads/Meetinghouse Library/Libraries/";
		  //String localDirectory = "Libraries/";
		  
		  StringBuilder sb = new StringBuilder();
	        sb.append(System.getProperty("user.dir"))
	                .append(File.separator)
	                .append("Libraries")
	                .append(File.separator);
	        String localDirectory = sb.toString();
		  
		  //String localDirectory = System.getProperty("user.dir")+"/Media Index/";
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
                //logInfo("Download:    " + row[0]);
                logInfo("File Name:   " + row[0]+".csv");
                logInfo("Directory:   " + localDirectory+row[0]+".csv");
                //logInfo("Server Name: " + localDirectory+row[8]+"/"+row[3]+"."+row[9].substring(row[9].length()-3));
                logInfo("URL:         " + row[1]);
                //logInfo("Directory:   " + localDirectory+row[8]);
                //if (row[9] != null && !row[9].isEmpty()) {
                //logInfo("Media Type:  " + row[9].substring(row[9].length()-3));             
                //}
                
                StringBuilder sbLF = new StringBuilder();
	        	sbLF.append(localDirectory)
	                .append(row[0])
	                .append(".csv");
	        	String builtLocalFile = sbLF.toString();
	        	logInfo("CSV Name:    " + builtLocalFile);
                
                
                logInfo("-------------");
	                //}
	          
                
                //if (row[8] != null && !row[8].isEmpty())
	            //DownloadFile dload = new DownloadFile("Cell Value: " + row[0] + "," + row[1] + "," + row[2] + "," + row[3]);
                //String downloadFlag = new String(row[0]);
                URL link = new URL(row[1]);
	            //String fulllocalpath = localDirectory+row[3];
	            File localDir = new File(localDirectory); //The file that will be saved on your computer
	        	//File localDir = new File(fulllocalpath); //The file that will be saved on your computer
	        	//File localFile = new File(localDirectory+row[8]+"/"+row[3]+"."+row[9].substring(row[9].length()-3));
	        	File localFile = new File(builtLocalFile);
	        	
	        	//File localFileName = new File(localdirectory+row[1]+".csv");
	        	//File localFile = new File(row[3]+row[1]+"."+row[2].substring(row[2].length()-3)); //The file that will be saved on your computer
	        	 
//	        	if(localFileName.renameTo(localFile)){
//    	            logInfo("File Name found and renamed success");
//
//	        	}
//	        		if (localFile.exists()) {
//		        		 logInfo("Local File already exists. Skipping...");
//		        		 
//		        		 continue;
//		        	} else {      		
//		        		if (!localDir.exists()) {
			        		if (localDir.mkdirs()) {
			    				logInfo("Directory is created!");
			    			} else {
			    				logInfo("Local Directory already exists. Skipping...");
			    			}
//		        		}
//		        		logInfo("Downloading new file...");
//		        	}
		           	
	        		DownloadFile download = new DownloadFile(localFile, link);
		           	threadPool.execute(download);
		        }
		        
		      //Now I would like to wait until the threadPool is done working
		      threadPool.shutdown();
		      while (!threadPool.isTerminated()) {
		            try {
		                threadPool.awaitTermination(10, TimeUnit.MILLISECONDS);
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            }
		      }
		        
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