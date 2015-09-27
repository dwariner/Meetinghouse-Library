package com.meetinghouselibrary.csvreader;

public class ProcessDownloads {
	
	public static void main(String[] args) 
	{
		//process this
		DownloadFileFromURL.main(null);
		//when downloadfilefromURL is done run next class
		DownloadLibraries.main(null);
		//when downloadlibraries is completed then run next class
		ParseCSVFileReadAll.main(null);
	}
}
