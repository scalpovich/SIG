/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GTAgw;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

/**
 *
 * @author wanda.priatna
 */
public class signature {
    
    public static String clientId = "f51fb676-13fe-4bac-8ede-88e27c061c42";
    public static String appSecret = "2LkLqu2ngxfWxeRgt8x0h8XGqbDkq2gy6vYCVz3g";
    public String appName = "Artajasa";
    public static String strVal = null;
    
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        
      //  String requestMessage = "{\"switchingReferenceCode\": \"12492839ab43\", \"collectingAgentCode\": \"doku_permata_lite_atm\", \"countryCode\": \"ID\", \"deliveryChannelCode\": \"cash\"}";
        String requestMessage ="{\"key\" : \"2LkLqu2ngxfWxeRgt8x0h8XGqbDkq2gy6vYCVz3g\",\"function\" : \"company.login\",\"data\" : {\"username\" : \"cs@net2software.com\",\"password\": \"net2software\"}}";
        System.out.println("Request Message : " + requestMessage);
        
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(requestMessage.getBytes());
        byte[] digest = md.digest(); // Missing charset
        //String hex = Hex.encodeHexString(digest);
        String content_digest = Base64.encodeBase64String(digest);   
        System.out.println("base64 content_digest :" +content_digest);

         String date = Util.getHttpFormatDate();
         System.out.println("Date : " + date);
           
        //String strVal = "POST\n"+"application/json\n"+date+"\n"+"/v1/switchingpartner/inquiry/phone/87775277588"+"\n"+content_digest+"\n";
        strVal = "POST\n"+date+"\n"+"application/json\n"+"/testingaj/aj/middle"+"\n"+content_digest+"\n";
        System.out.println(strVal);

       String auth =  encode(appSecret, strVal);
        System.out.println("auth:"+clientId+":"+auth);
    }
    
     public static String encode(String key, String data) {
    try {

        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);
       

        return new String(Base64.encodeBase64String(sha256_HMAC.doFinal(data.getBytes("UTF-8"))));

    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
    } catch (InvalidKeyException e) {
        e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    }

    return null;
}  
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
// */
//
//import static GTAgw.Util.getHttpFormatDate;
//import java.io.UnsupportedEncodingException;
//import java.security.InvalidKeyException;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import javax.crypto.Mac;
//import javax.crypto.spec.SecretKeySpec;
//import java.util.Base64;
//
//import java.io.BufferedReader;
//import java.io.DataOutputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//import javax.net.ssl.HttpsURLConnection;
//import static org.apache.http.HttpHeaders.USER_AGENT;

//
///**
// *
// * @author wanda.priatna
// */
//public class signature {
//    
//    public static String clientId = "f51fb676-13fe-4bac-8ede-88e27c061c42";
//    public static String appSecret = "TGVBNii0TABdG0fp";
//    public String appName = "Artajasa";
//    public static String auth;
//    public static String date;
//    public static String authorization;
//    
//    public static String sign(String date, String requestMessage) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, Exception {
//     public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, Exception {
//        
//         String requestMessage = "{\"switchingReferenceCode\": \"12492839ab45576575\", \"collectingAgentCode\": \"doku_permata_lite_atm\", \"countryCode\": \"ID\", \"deliveryChannelCode\": \"cash\"}";
//        String requestMessage = "{\"switchingReferenceCode\": \"12492865757565739\", \"collectingAgentCode\": \"doku_permata_lite_atm\", \"countryCode\": \"ID\", \"deliveryChannelCode\": \"cash\"}";
//         System.out.println("Request Message : " + requestMessage);
//
//
//        MessageDigest md = MessageDigest.getInstance("SHA-256");
//        md.update(requestMessage.getBytes());
//        byte[] digest = md.digest(); // Missing charset
//
//        System.out.println("Digest string"+new String(digest));
//        String content_digest = Base64.getEncoder().encodeToString(digest);
//        System.out.println("base64 content_digest :" +content_digest);
//
//         String date = "Tue, 29 Aug 2017 12:14:54 GMT";//getHttpFormatDate();
//         date = getHttpFormatDate();
//        date = "Sat, 02 Sep 2017 01:58:00 GMT";
//         System.out.println("Date :"+date);
//           
//        String strVal = "POST\n"+"application/json\n"+date+"\n"+"/v1/switchingpartner/inquiry/phone/87775277588"+"\n"+content_digest+"\n";
//        System.out.println(strVal);
//
//        auth =  encode(appSecret, strVal);
//        authorization = clientId+":"+auth;
//        System.out.println("auth:"+authorization);
//        return authorization;
//        
//        
//        
//        
//    }
//    
//    public static void header(){
//        
//    }
//    
//     public static String encode(String key, String data) {
//    try {
//
//        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
//        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
//        sha256_HMAC.init(secret_key);
//
//        return new String(Base64.getEncoder().encode(sha256_HMAC.doFinal(data.getBytes("UTF-8"))));
//
//    } catch (NoSuchAlgorithmException e) {
//        e.printStackTrace();
//    } catch (InvalidKeyException e) {
//        e.printStackTrace();
//    } catch (UnsupportedEncodingException e) {
//        e.printStackTrace();
//    }
//
//    return null;
//}
//     
//    
//
//
//}
