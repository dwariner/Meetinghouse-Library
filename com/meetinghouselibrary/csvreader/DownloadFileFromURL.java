package com.meetinghouselibrary.csvreader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
 
public class DownloadFileFromURL {
 
    public static void main(String[] args) {
        String url = "http://meetinghouselibrary.com/?wpdmdl=10346";
        
        try {
        		String workingDir = System.getProperty("user.dir");
        		System.out.println("Current working directory : " + workingDir);
				//downloadUsingNIO(url, "/Users/dwariner/Downloads/Meetinghouse Library/Library Manager.csv");
				downloadUsingNIO(url, workingDir+"/Library Manager.csv");
        	
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