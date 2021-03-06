package com.meetinghouselibrary.csvreader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.opencsv.CSVReader;

	

	public class ParseCSVFileReadAll 
	{

	public static void main(String[] args) 
	{
	       
			  //String[] filenames = new String[]{"http://meetinghouselibrary.com/?wpdmdl=10334"
			  //	  							, "http://meetinghouselibrary.com/?wpdmdl=10340"};// instantiate String array with file names
			  //DownloadFileFromURL.main(null);
			  //DownloadLibraries.main(null);
			  //String filename = "/Users/dwariner/Downloads/all-videos-downloaded.csv";
			  //"path"+File.Seperator+"to"+File.Seperator+"some"+File.Seperator+"file"
			  File aDirectory = new File(System.getProperty("user.dir")+File.separator+"Libraries"+File.separator);
			  String workingDir = System.getProperty("user.dir")+File.separator+"Libraries"+File.separator;
			  //File aDirectory = new File(workingDir);
			  System.out.println(aDirectory);
			  String[] filesInDir = aDirectory.list();
			  for ( int i=0; i<filesInDir.length; i++ )
			    {
			      //System.out.println( "file: " + filesInDir[i] );
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
		  
		  		  
		  //Calling to get the properties file to find out what directory to download the media in.
		  //StringBuilder sb = new StringBuilder();
	      //sb.append(System.getProperty("user.dir"));
		  String mediaLibrary;
	      String myDirectory = System.getProperty("user.dir");
		  Properties myProps = ManageConfigProperties.getConfigProperties(myDirectory, "config.properties");
	      String propLibrary = myProps.getProperty("mediaDirectory");
	      
	      if (propLibrary != null && !propLibrary.isEmpty()){
	    	  mediaLibrary = propLibrary;
	      } else {
	    	  mediaLibrary = myDirectory;
	      }
	      
	      System.out.println("Media Library:  " +propLibrary);
	     
		  
		  
		//String args = new String();
		//String mediaLibrary = "/Users/dwariner/Downloads/Meetinghouse Library/Media Library/";
		  //DownloadFile download = new DownloadFile(localFile, link);
		  //mediaDirectory11 = new ReadPropertiesFile();
			//getReadPropertiesFile();
		  //ReadPropertiesFile.main(null);
		  //logInfo("directoryName " + args);
//		  String workingDir = System.getProperty("user.dir");
//			System.out.println("Current working directory : " + workingDir);
//			ReadPropertiesFile mediaLibrary = null;
//			File file = new File(workingDir+"/My Properties.csv");
//			if (file.exists()) {
//				mediaLibrary = mediaDirectory11;
//  				logInfo("Using My Properties File");
//  			} else {
//  				mediaLibrary = workingDir+mediaDirectory11;
//  				logInfo("Using Default Media Library");
//  		}
			
		  //String localDirectory = System.getProperty("user.dir")+"/Media Library/";
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
                //logInfo("Cell Value: " + row[0] + "," + row[1] + "," + row[1] + "," + row[2]);
                //logInfo("File Name:   " + mediaLibrary+row[1]+"/"+row[5]);
                //logInfo("Server Name: " + mediaLibrary+row[1]+"/"+row[0]+"."+row[5].substring(row[5].length()-3));   
                //logInfo("URL:         " + row[3]);
                //logInfo("Directory:   " + mediaLibrary+row[1]);
//                if (row[3] != null && !row[3].isEmpty()) {
//                logInfo("Media Type:  " + row[3].substring(row[3].length()-3));
//                }
                logInfo("-------------");
	                //}
	          
                if (firstRow) {
                	firstRow = false;
                	continue;
                }
                //if (row[1] != null && !row[1].isEmpty())
	            //DownloadFile dload = new DownloadFile("Cell Value: " + row[0] + "," + row[1] + "," + row[1] + "," + row[2]);
	            //URL link = new URL(row[3]);
                
                String foo = row[1].trim();
        		String[] split = foo.split("/");
        		StringBuilder sbSplit = new StringBuilder();
        		for (int i = 0; i < split.length; i++) {
        			sbSplit.append(File.separator)
        			.append(split[i]);
        		    //if (i != split.length - 1) {
        		    //    sb.append("");
        		   // }
        		}
        		String dirSeparator = sbSplit.toString();
        		String cleanFileName;
        		
        		//cleanRow0 = row[0].replaceAll("[^sa-zA-Z0-9\\w+_\\-\\.]", "");
        		cleanFileName = row[0].trim();
        		cleanFileName = cleanFileName.replaceAll(":", " -");
        		cleanFileName = cleanFileName.replaceAll("—", " - ");
        		cleanFileName = cleanFileName.replaceAll("\\?", "");
        		cleanFileName = cleanFileName.replaceAll("\"", "");
        		cleanFileName = cleanFileName.replaceAll("\\*", "");
        		cleanFileName = cleanFileName.replaceAll("%", "");
        		cleanFileName = cleanFileName.replaceAll("|", "");
        		cleanFileName = cleanFileName.replaceAll("<", "");
        		cleanFileName = cleanFileName.replaceAll(">", "");
        		cleanFileName = cleanFileName.replaceAll(";", "");
        		cleanFileName = cleanFileName.replaceAll("!", "");
        		
        		//System.out.println(dirSeparator);
        		
        		//logInfo("dirSeparator:" + dirSeparator);
                
                //String dirSeparator = row[1].replaceAll("\\/", File.separator);
                //logInfo("dirSeparator:" + dirSeparator);
                
                StringBuilder sbDir = new StringBuilder();
                   sbDir.append(mediaLibrary)
		                .append(dirSeparator);
		        String builtDir = sbDir.toString();
		        logInfo("Directory:   " + builtDir);
		        
		        StringBuilder sbLF = new StringBuilder();
		        	sbLF.append(mediaLibrary)
		                .append(dirSeparator)
		                .append(File.separator)
		                .append(cleanFileName)
		                .append(".")
		                .append(row[5].trim().substring(row[5].trim().length()-3));
		        String builtLocalFile = sbLF.toString();
		        logInfo("Title Name:  " + builtLocalFile);
		        
		        StringBuilder sbFN = new StringBuilder();
		            sbFN.append(mediaLibrary)
		                .append(dirSeparator)
		                .append(File.separator)
		                .append(row[5].trim());
		        String builtLocalFileName = sbFN.toString();
		        logInfo("File Name:   " + builtLocalFileName);
                
                
                
                String findLink;
				//If 720p link is blank then move to 1080p then if blank then 360p video download
                if (row[3] !=null && row[3].length()>0){
                	//720p Download URL
                	findLink = row[3].trim();
                //logInfo("URL 720p:    " + row[3]);
                } else if (row[2] !=null && row[2].length()>0) {
                	//1080p Download URL
                	findLink = row[2].trim();
                logInfo("URL 1080p:   " + row[2]);
                } else {
                	//360p Download URL
                	findLink = row[4].trim();
                logInfo("URL 360p:    " + row[4]);
                }
				URL link = new URL(findLink);
				logInfo("URL link:    " + link);
                
				//String fulllocalpath = mediaLibrary+row[2];
	            File localDir = new File(builtDir); //The file that will be saved on your computer
	        	//File localDir = new File(fulllocalpath); //The file that will be saved on your computer
	        	File localFile = new File(builtLocalFile);
	        	File localFileName = new File(builtLocalFileName);
	        	//File localFile = new File(row[2]+row[1]+"."+row[1].substring(row[1].length()-3)); //The file that will be saved on your computer
	        	 

	        	
	        	//File file = new File("C:\\user\\Desktop\\dir1\\dir2\\filename.txt");
	        	//localFile.getParentFile().mkdir();
//	        	FileWriter writer = new FileWriter(file);
	        	
//	        	File dir = new File("C:\\user\\Desktop\\dir1\\dir2");
	        	//localFile.mkdirs();
//	        	File file = new File(dir, "filename.txt");
//	        	FileWriter newJsp = new FileWriter(file);
	        	//if (row[1] != null && !row[1].isEmpty()) {
	        	    	
	        
	        	if(localFileName.renameTo(localFile)){
    	            logInfo("File Name found and renamed success");
	        	}
	        		if (localFile.exists()) {
		        		 logInfo("Local File already exists. Skipping...");
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