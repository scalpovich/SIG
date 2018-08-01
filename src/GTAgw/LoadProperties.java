/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GTAgw;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author wanda.priatna
 */
public class LoadProperties {
    
      public static Properties prop = null;
    public static InputStream input = null; 
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
         prop = new Properties();
         input = new FileInputStream("config.properties");
        
        prop.load(input);
        
        int sourcePort2 = Integer.parseInt(prop.getProperty("SOURCE_PORT2"));
        int sourcePort = Integer.parseInt(prop.getProperty("SOURCE_PORT"));
       // int tes1 = Integer.parseInt(prop.getProperty("database"));
        
        System.out.println("Source Port : " + sourcePort);
        System.out.println("Source Port 2 : " + sourcePort2);
        System.out.println(prop.getProperty("DESTINATION_HOST"));
        System.out.println(prop.getProperty("MigratePrefix"));
        
    
        tes();
        tes1();
  }
    
    public static void tes(){
        System.out.println("");
        System.out.println("Section 1 ");
        System.out.println("--------------------------------------------------------------------");
          int sourcePort2 = Integer.parseInt(prop.getProperty("SOURCE_PORT2"));
        int sourcePort = Integer.parseInt(prop.getProperty("SOURCE_PORT"));
        
        System.out.println("Source Port : " + sourcePort);
        System.out.println("Source Port 2 : " + sourcePort2);
        System.out.println(prop.getProperty("DESTINATION_HOST"));
        System.out.println(prop.getProperty("MigratePrefix"));
        
      
        
    }
    
      public static void tes1(){
          System.out.println("");
           System.out.println("Section 2");
        System.out.println("--------------------------------------------------------------------");
            int sourcePort2 = Integer.parseInt(prop.getProperty("SOURCE_PORT2"));
        int sourcePort = Integer.parseInt(prop.getProperty("SOURCE_PORT"));
        
       System.out.println("Source Port : " + sourcePort);
        System.out.println("Source Port 2 : " + sourcePort2);
        System.out.println(prop.getProperty("DESTINATION_HOST"));
        System.out.println(prop.getProperty("MigratePrefix"));
        
        
    }
    
}
