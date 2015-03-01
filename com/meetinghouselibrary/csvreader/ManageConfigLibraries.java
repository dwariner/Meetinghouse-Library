package com.meetinghouselibrary.csvreader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author james.murtha
 */
public class ManageConfigProperties {
    static Properties getConfigProperties(String path, String fileName) {
        Properties prop = new Properties();
        FileInputStream fis;
        StringBuilder sb = new StringBuilder();
        sb.append(System.getProperty("user.home"))
                .append(File.separator)
                .append(path)
                .append(File.separator)
                .append(fileName);
        String myFileName = sb.toString();
        try {
            fis = new FileInputStream(myFileName);
            if (fis != null) {
                try {
                    prop.load(fis);
                } catch (IOException ex) {
                    Logger.getLogger(ManageConfigProperties.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ManageConfigProperties.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prop;
    }
    
    static void saveConfigProperties(Properties props, String path, String fileName) {
        FileOutputStream fos;
        StringBuilder sb = new StringBuilder();
        sb.append(System.getProperty("user.home"))
                .append(File.separator)
                .append(path)
                .append(File.separator)
                .append(fileName);
        String myFileName = sb.toString();
        try {
            fos = new FileOutputStream(myFileName);
            props.store(fos, null);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ManageConfigProperties.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ManageConfigProperties.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
