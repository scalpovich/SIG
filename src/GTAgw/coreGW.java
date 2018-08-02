/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GTAgw;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.*; 
import java.net.*;
import java.util.Arrays;
import java.util.Properties;
import GTAgw.POJO;
import GTAgw.POJO.Respdata;
import static GTAgw.coreGW.prop;

/**
 *
 * @author funiadi
 */
public class coreGW { 
    
    public static Properties prop = null;
    public static InputStream input = null; 
    public static Socket clientSocket ; 
    public  static    NetworkDataListener NDL; 
    
    public  static  NetworkData ND;  

    
    public static void main(String[] args) throws IOException {  
        
        prop = new Properties();
        input = new FileInputStream("config.properties");
        prop.load(input);
        String HttpPort= ApplicationProperties.getHTTPPort("HTTP_PORT");

        HttpServer server = HttpServer.create(new InetSocketAddress(Integer.parseInt(HttpPort)), 0); 
        server.createContext("/createAccount", new CreateAccountEnhance());  
         server.createContext("/transferInquiry", new TransferInquiryEnhance()); 
         server.createContext("/transfer", new TransferEnhance()); 
         server.createContext("/InquiryStatus", new InquiryStatus());
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Application Is Starting ");
        
           
    } 
} 

