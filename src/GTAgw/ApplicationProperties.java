/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GTAgw;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.configuration.ConfigurationException;


/**
 *
 * @author wanda.priatna
 */
public class ApplicationProperties
{
    private static PropertiesConfiguration configuration = null;
 
    static
    {
        try {
            configuration = new PropertiesConfiguration("config.properties");
            configuration.setReloadingStrategy(new FileChangedReloadingStrategy());
            
        } catch (ConfigurationException e) {
            // e.printStackTrace();
        }
    }

    public static synchronized String getCountryCode(final String countryCode)
    {
        return (String)configuration.getProperty(countryCode);
    }
    
    
    
    public static synchronized String getSourcePort2(final String SOURCE_PORT2)
    {
        return (String)configuration.getProperty(SOURCE_PORT2);
    }
    
    public static synchronized String getSourcePort(final String SOURCE_PORT)
    {
        return (String)configuration.getProperty(SOURCE_PORT);
    }
    
    public static synchronized String getHTTPPort(final String HTTP_PORT)
    {
        return (String)configuration.getProperty(HTTP_PORT);
    }

    public static synchronized String getCACode(final String collectingAgentCode)
    {
        return (String)configuration.getProperty(collectingAgentCode);
    }

    
    public static synchronized String getDestinationHost(final String DESTINATION_HOST)
    {
        return (String)configuration.getProperty(DESTINATION_HOST);
    }
    
    public static synchronized String getMigratePrefix(final String MigratePrefix)
    {
        return (String)configuration.getProperty(MigratePrefix);
    }
    
    public static synchronized String getGrabTopUpInqURL()
    {
        return (String)configuration.getProperty("GrabTopUpInqURL");
    }

    public static synchronized String getGrabTopUpURL()
    {
        return (String)configuration.getProperty("GrabTopUpURL");
    }

    public static synchronized String getGrabAppID()
    {
        return (String)configuration.getProperty("GrabAppID");
    }
    
    public static synchronized String getGrabAppSecret()
    {
        return (String)configuration.getProperty("GrabAppSecret");
    }
    
     public static synchronized String getGrabAppName()
    {
        return (String)configuration.getProperty("GrabAppName");
    }
    
     public static synchronized String getDestinationPort(final String DESTINATION_PORT)
    {
        return (String)configuration.getProperty(DESTINATION_PORT);
    }
     
     public static synchronized String getCountryCode()
    {
        return (String)configuration.getProperty("countryCode");
    }
     
      public static synchronized String getDeliveryChannelCode()
    {
        return (String)configuration.getProperty("deliveryChannelCode");
    }
      
        public static synchronized String getCurrencyCode()
    {
        return (String)configuration.getProperty("currencyCode");
    }
        
        public static synchronized String getPasswordDb()
    {
        return (String)configuration.getProperty("passwordDb");
    }
            
    public static synchronized String getHost()
    {
        return (String)configuration.getProperty("host");
    }
    
     public static synchronized String getUserDb()
    {
        return (String)configuration.getProperty("userDb");
    }
    
      public static synchronized String getUrlRemmitance()
    {
        return (String)configuration.getProperty("urlRemmitance");
    }
      
         public static synchronized String getUrlRemmitanceTrx()
    {
        return (String)configuration.getProperty("urlRemmitanceTrx");
    }
         
    public static synchronized String getSignaturePath() {
        return (String) configuration.getProperty("signaturepath");
    }
    
     public static synchronized String getSignaturePass() {
        return (String) configuration.getProperty("signaturepass");
    }
      
      
    
}

