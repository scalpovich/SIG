/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GTAgw;



import com.sun.net.ssl.HttpsURLConnection;
import java.security.KeyStoreException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.commons.codec.binary.Base64;
//import org.apache.commons.httpclient.StatusLine;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.net.HttpURLConnection;
import java.net.URL;
import static org.apache.http.HttpHeaders.USER_AGENT;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;



 

public class MyPostMethodhttps {
    
    static String[] getResponse(String url, String requestMessage, String vaid) throws NoSuchAlgorithmException, KeyManagementException, IOException, KeyStoreException  {
            String GrabAppID = ApplicationProperties.getGrabAppID();
            String GrabAppSecret =  ApplicationProperties.getGrabAppSecret();       
            //String credential = GrabAppID+":"+Util.GrabSign(content, GrabAppSecret, url);//"artajasa_user1:4rt4j454_User1";
//            System.out.println("credential Base64 value is " + credential);
            //System.out.println("content value is " + content);
            //System.out.println("URL value is " + url);
            
            String clientId = "f51fb676-13fe-4bac-8ede-88e27c061c42";
            String appSecret = "TGVBNii0TABdG0fp";
            
            //String requestMessage = //"{\"switchingReferenceCode\": \"12492839ab51\", \"collectingAgentCode\": \"doku_permata_lite_atm\", \"countryCode\": \"ID\", \"deliveryChannelCode\": \"cash\"}";
            //content = requestMessage;
            String authorization="";
//            try {
//               // TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(MyPostMethodhttps.class.getName()).log(Level.SEVERE, null, ex);
//            }
            String date = Util.getHttpFormatDate();//"Tue, 29 Aug 2017 12:14:54 GMT";
            
            try {
                authorization = Util.Grabsign(date,requestMessage, vaid);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(coba.class.getName()).log(Level.SEVERE, null, ex);
            }
          //  System.out.println("Date data :"+date);
         //   System.out.println("authorization :"+authorization);
            
            String[] result = new String[2];
            
            SSLContextBuilder builder = new SSLContextBuilder();
            builder.loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
             });

            SSLConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(builder.build(),
            SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslSF).build();
            HttpPost httpPost = new HttpPost(url);
            //sslsocket.setEnabledCipherSuites(new String[] {"TLS_RSA_WITH_AES_128_CBC_SHA"});
            StringEntity params =new StringEntity(requestMessage, "UTF-8"); 
           // System.out.println(requestMessage);

            //params.setContentType("application/json");
            httpPost.addHeader("Authorization", authorization); 
            httpPost.addHeader("Content-Type", "application/json"); 
            httpPost.addHeader("Date",date);
            
            params.setContentType("application/json");
            httpPost.setEntity(params); 
            
            /*
            ByteArrayEntity postDataEntity = new ByteArrayEntity(content.getBytes());
            httpPost.setEntity(postDataEntity);
            */
            
            /* DIREMARK KHSUS UNTUK PRODUCTION*/
            CloseableHttpResponse response = httpClient.execute(httpPost);
             
            StatusLine rcsl = response.getStatusLine();
            try {
                HttpEntity entity = response.getEntity();
                result[1] = EntityUtils.toString(entity);
                result[0] = Integer.toString(rcsl.getStatusCode());
                EntityUtils.consume(entity);
                System.out.println("HTTP response code : "+rcsl.getStatusCode());
                System.out.println("result json : "+result);
            } finally {
                response.close();
            }
            return result;
    }
    
    static String[] httpsPost(String url, String requestMessage, String grabRef) throws NoSuchAlgorithmException, KeyManagementException, IOException, KeyStoreException  {
            String GrabAppID = ApplicationProperties.getGrabAppID();
            String GrabAppSecret =  ApplicationProperties.getGrabAppSecret();       
            //String credential = GrabAppID+":"+Util.GrabSign(content, GrabAppSecret, url);//"artajasa_user1:4rt4j454_User1";
//            System.out.println("credential Base64 value is " + credential);
            //System.out.println("content value is " + content);
            //System.out.println("URL value is " + url);
            
            String clientId = "f51fb676-13fe-4bac-8ede-88e27c061c42";
            String appSecret = "TGVBNii0TABdG0fp";
            
            //String requestMessage = //"{\"switchingReferenceCode\": \"12492839ab51\", \"collectingAgentCode\": \"doku_permata_lite_atm\", \"countryCode\": \"ID\", \"deliveryChannelCode\": \"cash\"}";
            //content = requestMessage;
            String authorization="";
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(MyPostMethodhttps.class.getName()).log(Level.SEVERE, null, ex);
//            }
            String date = Util.getHttpFormatDate();//"Tue, 29 Aug 2017 12:14:54 GMT";
            
            try {
                authorization = Util.GrabsignTopUp(date,requestMessage,grabRef );
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(coba.class.getName()).log(Level.SEVERE, null, ex);
            }
           // System.out.println("Date data :"+date);
           // System.out.println("authorization :"+authorization);
            
            String[] result = new String[2];
            
            SSLContextBuilder builder = new SSLContextBuilder();
            builder.loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
             });

            SSLConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(builder.build(),
            SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslSF).build();
            HttpPost httpPost = new HttpPost(url);
            //sslsocket.setEnabledCipherSuites(new String[] {"TLS_RSA_WITH_AES_128_CBC_SHA"});
            StringEntity params =new StringEntity(requestMessage, "UTF-8"); 
           // System.out.println(requestMessage);

            //params.setContentType("application/json");
            httpPost.addHeader("Authorization", authorization); 
            httpPost.addHeader("Content-Type", "application/json"); 
            httpPost.addHeader("Date",date);
            
            params.setContentType("application/json");
            httpPost.setEntity(params); 
            
            /*
            ByteArrayEntity postDataEntity = new ByteArrayEntity(content.getBytes());
            httpPost.setEntity(postDataEntity);
            */
            
            /* DIREMARK KHSUS UNTUK PRODUCTION*/
            CloseableHttpResponse response = httpClient.execute(httpPost);
             
            StatusLine rcsl = response.getStatusLine();
            try {
                HttpEntity entity = response.getEntity();
                result[1] = EntityUtils.toString(entity);
                result[0] = Integer.toString(rcsl.getStatusCode());
                EntityUtils.consume(entity);
                System.out.println("HTTP response code : "+rcsl.getStatusCode());
                System.out.println("result json : "+result);
            } finally {
                response.close();
            }
            return result;
    }
    
    static String[] sendHttpPost(String url, String requestMessage) throws NoSuchAlgorithmException, KeyManagementException, IOException, KeyStoreException  {
            String[] result = new String[2];
            
            SSLContextBuilder builder = new SSLContextBuilder();
            builder.loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
             });

            SSLConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(builder.build(),
            SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslSF).build();
            HttpPost httpPost = new HttpPost(url);
            //sslsocket.setEnabledCipherSuites(new String[] {"TLS_RSA_WITH_AES_128_CBC_SHA"});
            StringEntity params =new StringEntity(requestMessage, "UTF-8"); 
            
            params.setContentType("application/json");
            httpPost.setEntity(params); 
            
            /*
            ByteArrayEntity postDataEntity = new ByteArrayEntity(content.getBytes());
            httpPost.setEntity(postDataEntity);
            */
            
            /* DIREMARK KHSUS UNTUK PRODUCTION*/
            CloseableHttpResponse response = httpClient.execute(httpPost);
             
            StatusLine rcsl = response.getStatusLine();
            
            System.out.println("toString " + rcsl.toString());
        
            try {
                HttpEntity entity = response.getEntity();
                entity.getContent();
                System.out.println("content" + entity.getContent().toString());
                
                System.out.println("getEntity" + entity);
                result[1] = EntityUtils.toString(entity);
                result[0] = Integer.toString(rcsl.getStatusCode());
                EntityUtils.consume(entity);
                System.out.println("HTTP response code : "+rcsl.getStatusCode());
               // System.out.println("result json : "+result);
            } finally {
                response.close();
            }
            return result;
    }
    
  
    
     static String[] sendPostCa(String data, String url) { 

        int responseCode = -1; 
        String respStr="";
        String[] result = new String[2];
        HttpClient httpClient = new DefaultHttpClient(); 

        try { 
            HttpPost request = new HttpPost(url); 
            StringEntity params =new StringEntity(data, "UTF-8"); 
            params.setContentType("application/xml"); 
            request.addHeader("content-type", "application/xml"); 
            request.setEntity(params); 
            HttpResponse response = httpClient.execute(request); 
            responseCode = response.getStatusLine().getStatusCode(); 
            result[0]=Integer.toString(responseCode);
            if ( responseCode == 200 || responseCode == 204) { 
                if(response.getEntity()!=null){ 
                  /*  String json_string = EntityUtils.toString(response.getEntity());
                    JSONArray jsonrespStr = new JSONArray(json_string);
                    System.out.println(jsonrespStr);
                    */
                    BufferedReader br = new BufferedReader( 
                          //  new InputStreamReader((response.getEntity().getContent()))); 
                            new InputStreamReader((response.getEntity().getContent()))); 
                    String output; 
                    //logger.info("Output from Server ...." + response.getStatusLine().getStatusCode() + "\n"); 
                 //   System.out.println("Output from Server ...." + response.getStatusLine().getStatusCode() + "\n"); 
                    while ((output = br.readLine()) != null) { 
                        //logger.info(output); 
                        System.out.println(output); 
                        respStr+=output;
                    } 
                } 
            } 
            else{ 
                throw new RuntimeException("Failed : HTTP error code : " 
                        + response.getStatusLine().getStatusCode()); 
            } 

        }catch (Exception ex) { 
            //logger.error("ex Code sendPost1: " + ex); 
            //logger.error("ex Code sendPost2: " + data); 
            //logger.error("ex Code sendPost3: " + url); 
            throw new RuntimeException(" sendPost Failed :" + ex.getMessage()); 
        } finally { 
            httpClient.getConnectionManager().shutdown(); 
        } 
        result[1]=respStr;
        return result; 
    } 


}

