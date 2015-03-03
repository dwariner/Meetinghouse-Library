package com.meetinghouselibrary.csvreader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

 
public class DownloadFileFromURL {
 
    public static void main(String[] args) {
        String url = "http://meetinghouselibrary.com/?wpdmdl=10346";
        
        try {
        		
	        	StringBuilder sb = new StringBuilder();
		        sb.append(System.getProperty("user.dir"))
		                .append(File.separator)
		                .append("Library Manager.csv");
		        String myFileName = sb.toString();
        	
        	
        		//String workingDir = System.getProperty("user.home");
        		System.out.println("Current working directory : " + myFileName);
				//downloadUsingNIO(url, "/Users/dwariner/Downloads/Meetinghouse Library/Library Manager.csv");
				downloadUsingNIO(url, myFileName);
				
				//Calling to get the properties file to find out what directory to download the media in.
				//Properties myProps = ManageConfigProperties.getConfigProperties("Meetinghouse Library", "config.properties");
			    //String homeFolderName = myProps.getProperty("homeFolderName", "");
			    //System.out.println(homeFolderName);
				
				
				
				
        	
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    private static void downloadUsingNIO(String urlStr, String file) throws IOException {
        URL url = new URL(urlStr);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(file);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
    }
 
}