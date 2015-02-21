package com.meetinghouselibrary.csvreader;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class DownloadFile implements Runnable {

	File localFile;
	URL url;
	
	public DownloadFile(File _localFile, URL _url) {
		localFile = _localFile;
		url = _url;
	}

	@Override
	public void run() {
		try {
		
	//Code to download
		 InputStream in = new BufferedInputStream(url.openStream());
		 ByteArrayOutputStream out = new ByteArrayOutputStream();
		 byte[] buf = new byte[1024];
		 int n = 0;
		 while (-1!=(n=in.read(buf)))
		 {
		    out.write(buf, 0, n);
		 }
		 out.close();
		 in.close();
		 byte[] response = out.toByteArray();
	
		 FileOutputStream fos = new FileOutputStream(localFile);
		 fos.write(response);
		 fos.close();
	//End download code
		} catch (Throwable t) {
			t.printStackTrace();
		}
		 
		 System.out.println("Finished");
		
	}

public static void main(String[] args) throws IOException {
	URL url = new URL("http://media2.ldscdn.org/assets/scripture-and-lesson-support/the-life-of-jesus-christ-bible-videos-2014/2014-01-010-the-risen-lord-appears-to-the-apostles-720p-eng.mp4");
	 File localFile = new File("C:\\Users\\dwariner\\Dropbox\\2014-01-010-the-risen-lord-appears-to-the-apostles-720p-eng.mp4"); //The file that will be saved on your computer

	 DownloadFile download = new DownloadFile(localFile, url);
	 new Thread(download).start();
	 
}

}