package com.meetinghouselibrary.csvreader;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 *
 * @author james.murtha
 */
public class ManageProperties {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
        Date curDate = new Date();
        Properties myProps;
        
        //StringBuilder sb = new StringBuilder();
        //sb.append(System.getProperty("user.dir"));
        //String myDirectory = sb.toString();
        String myDirectory = System.getProperty("user.dir");
        
        
        myProps = ManageConfigProperties.getConfigProperties(myDirectory, "config.properties");
        String mediaLibrary = myProps.getProperty("mediaDirectory", "myDirectory");
        System.out.println(mediaLibrary);

        if (myProps.containsKey("mydatetime")) {
            // update property with new date & time
            myProps.setProperty("mydatetime", sdf.format(curDate));
        } else {
            // create new property key
            myProps.put("mydatetime", sdf.format(curDate));
        }
        ManageConfigProperties.saveConfigProperties(myProps, myDirectory, "config.properties");
    }
    
}
