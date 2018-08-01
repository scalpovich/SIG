/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GTAgw;

import java.io.*;
import java.net.*;
import com.sun.net.ssl.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fjuniadi
 */
public class Sendmsgtograb {

    public static void main(String[] args) {
        String cuki = new String();
        try {
            System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            URL url = new URL("https://api.stg-myteksi.com/v1/switchingpartner/inquiry/phone/87775277588");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
//String cookieHeader = connection.getHeaderField("set-cookie"); 
//if(cookieHeader != null) { 
//int index = cookieHeader.indexOf(";"); 
//if(index >= 0) 
//cuki = cookieHeader.substring(0, index); 
//connection.setRequestProperty("Cookie", cuki); 
//}

            connection.setRequestMethod("POST");
            connection.setFollowRedirects(true);
            String query = "{\"switchingReferenceCode\": \"12492839ab5400\", \"collectingAgentCode\": \"doku_permata_lite_atm\", \"countryCode\": \"ID\", \"deliveryChannelCode\": \"cash\"}";

            /*
String query = "UserID=" + URLEncoder.encode("williamalex@hotmail.com"); 
query += "&"; 
query += "password=" + URLEncoder.encode("password"); 
query += "&"; 
query += "UserChk=" + URLEncoder.encode("Bidder");
// This particular website I was working with, required that the referrel URL should be from this URL
// as specified the previousURL. If you do not have such requirement you may omit it.
query += "&"; 
query += "PreviousURL=" + URLEncoder.encode("https://www.anysecuresite.com.sg/auct.cfm"); 
             */
//connection.setRequestProperty("Accept-Language","it"); 
//connection.setRequestProperty("Accept", "application/cfm, image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, image/png, //*/*"); 
//connection.setRequestProperty("Accept-Encoding","gzip"); 
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException ex) {
                Logger.getLogger(MyPostMethodhttps.class.getName()).log(Level.SEVERE, null, ex);
            }
            String date = Util.getHttpFormatDate();
//            String authorization = Util.Grabsign(date, query, vaid);
//            connection.setRequestProperty("Content-length", String.valueOf(query.length()));
//            connection.setRequestProperty("Authorization", authorization);
//            System.out.println("Authorization :" + authorization);
//            connection.setRequestProperty("Content-Type", "application/json");

            String Dateheader = date;// Util.getHttpFormatDate();
            connection.setRequestProperty("Date", Dateheader);
//System.out.println("Date :"+Dateheader);
// open up the output stream of the connection 
            DataOutputStream output = new DataOutputStream(connection.getOutputStream());

// write out the data 
            int queryLength = query.length();
            output.writeBytes(query);
//output.close();

            System.out.println("Resp Code:" + connection.getResponseCode());
            System.out.println("Resp Message:" + connection.getResponseMessage());

// get ready to read the response from the cgi script 
            DataInputStream input = new DataInputStream(connection.getInputStream());

// read in each character until end-of-stream is detected 
            for (int c = input.read(); c != -1; c = input.read()) {
                System.out.print((char) c);
            }
            input.close();
        } catch (Exception e) {
            System.out.println("Something bad just happened.");
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
