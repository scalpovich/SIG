/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GTAgw;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author wanda.priatna
 */
public class ConfigPropertiesArta {
    
     public static void main(String[] args) {

	Properties prop = new Properties();
	OutputStream output = null;

	try {

		output = new FileOutputStream("config.properties");

		// set the properties value
		prop.setProperty("SOURCE_PORT" ,"9993");
		prop.setProperty("SOURCE_PORT2" , "9994");
		prop.setProperty("DESTINATION_HOST" , "10.98.10.139");
                prop.setProperty("DESTINATION_PORT" , "7000");
                prop.setProperty("MigratePrefix","|500015|");

		// save properties to project root folder
		prop.store(output, null);

	} catch (IOException io) {
		io.printStackTrace();
	} finally {
		if (output != null) {
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
    
}
