package manageproperties;

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
        Properties myProps;
        myProps = ManageConfigProperties.getConfigProperties("MyLDSMediaLibrary", "config.properties");
//        myProps.put("name", "Jimbo");
        ManageConfigProperties.saveConfigProperties(myProps, "MyLDSMediaLibrary", "config.properties");
    }
    
}
