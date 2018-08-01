/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GTAgw;

import org.apache.http.HttpResponse; 
import org.apache.http.client.HttpClient; 
import org.apache.http.client.methods.HttpDelete; 
import org.apache.http.client.methods.HttpGet; 
import org.apache.http.client.methods.HttpPost; 
import org.apache.http.client.methods.HttpPut; 
import org.apache.http.entity.StringEntity; 
import org.apache.http.impl.client.DefaultHttpClient; 
import org.apache.log4j.Logger; 
import org.apache.http.util.EntityUtils;
import org.json.JSONArray; 

import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader; 
import java.net.HttpURLConnection;  
import javax.net.ssl.HttpsURLConnection;
import java.net.InetSocketAddress; 
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author fjuniadi
 */
public class HttpSend {
//    protected static Logger logger = Logger.getLogger("HttpProcessLogger"); 
    /*
    public httpprocess(){ 
        super(httpprocess.class); 
    } 
    @Override 
    */
    static String[] sendPost(String data, String url) { 
        // dataoutput="{\"info\": {\"username\": \" drippingmind\", \"userid\": \"449077875\", \"n_answers\": 2, \"date\": \" Wed Dec 05 11:13:18 CET 2012\", \"text\": \" Google provides support in the #Philippines w/ crisis response map 4 typhoon #Pablo #Bopha http://t.co/mJCNBHAJ #DRM #ICT #EmergencyResponse\", \"question\": \"Please tag the following tweet or SMS based on the category or categories that best describes the link(s) included in the tweet/Sms\", \"tweetid\": \"2.76E17\"}, \"state\": 0, \"n_answers\": 30, \"quorum\": 0, \"calibration\": 0, \"app_id\": 4, \"priority_0\": 0}"; 
        int responseCode = -1; 
        String respStr="";
        String[] result = new String[2];
        HttpClient httpClient = new DefaultHttpClient(); 
 
        try { 
            HttpPost request = new HttpPost(url); 
            StringEntity params =new StringEntity(data, "UTF-8"); 
            params.setContentType("application/json"); 
            request.addHeader("content-type", "application/json"); 
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
                            new InputStreamReader((response.getEntity().getContent()))); 
 
                    String output; 
                    //logger.info("Output from Server ...." + response.getStatusLine().getStatusCode() + "\n"); 
                    System.out.println("Output from Server ...." + response.getStatusLine().getStatusCode() + "\n"); 
  
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

 static String sendHttpsPost(String data, String url) throws NoSuchAlgorithmException, KeyManagementException, IOException, KeyStoreException  { 
        // dataoutput="{\"info\": {\"username\": \" drippingmind\", \"userid\": \"449077875\", \"n_answers\": 2, \"date\": \" Wed Dec 05 11:13:18 CET 2012\", \"text\": \" Google provides support in the #Philippines w/ crisis response map 4 typhoon #Pablo #Bopha http://t.co/mJCNBHAJ #DRM #ICT #EmergencyResponse\", \"question\": \"Please tag the following tweet or SMS based on the category or categories that best describes the link(s) included in the tweet/Sms\", \"tweetid\": \"2.76E17\"}, \"state\": 0, \"n_answers\": 30, \"quorum\": 0, \"calibration\": 0, \"app_id\": 4, \"priority_0\": 0}"; 
        int responseCode = -1; 
        String respStr="";
        
        SSLContextBuilder builder = new SSLContextBuilder();
            builder.loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
             });

            SSLConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(builder.build(),
            SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

        
        //CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslSF).build();

        //HttpClient httpClient = new DefaultHttpClient(); 
        HttpClient httpClient =  HttpClients.custom().setSSLSocketFactory(sslSF).build();
 
        try { 
            
            HttpPost request = new HttpPost(url); 
            StringEntity params =new StringEntity(data, "UTF-8"); 
            params.setContentType("application/json"); 
            request.addHeader("content-type", "application/json"); 
            request.setEntity(params);
            
            HttpResponse response = httpClient.execute(request); 
            responseCode = response.getStatusLine().getStatusCode(); 
 
            if ( responseCode == 200 || responseCode == 204) { 
                if(response.getEntity()!=null){ 
                  /*  String json_string = EntityUtils.toString(response.getEntity());
                    JSONArray jsonrespStr = new JSONArray(json_string);
                    
                    System.out.println(jsonrespStr);
                    */
                    BufferedReader br = new BufferedReader( 
                            new InputStreamReader((response.getEntity().getContent()))); 
 
                    String output; 
                    //logger.info("Output from Server ...." + response.getStatusLine().getStatusCode() + "\n"); 
                    System.out.println("Output from Server ...." + response.getStatusLine().getStatusCode() + "\n"); 
  
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
 
        return respStr; 
    } 
    
    static String sendHtpsPost(String data, String url) { 
        // dataoutput="{\"info\": {\"username\": \" drippingmind\", \"userid\": \"449077875\", \"n_answers\": 2, \"date\": \" Wed Dec 05 11:13:18 CET 2012\", \"text\": \" Google provides support in the #Philippines w/ crisis response map 4 typhoon #Pablo #Bopha http://t.co/mJCNBHAJ #DRM #ICT #EmergencyResponse\", \"question\": \"Please tag the following tweet or SMS based on the category or categories that best describes the link(s) included in the tweet/Sms\", \"tweetid\": \"2.76E17\"}, \"state\": 0, \"n_answers\": 30, \"quorum\": 0, \"calibration\": 0, \"app_id\": 4, \"priority_0\": 0}"; 
        int responseCode = -1; 
        String respStr="";
        HttpClient httpClient = new DefaultHttpClient(); 
 
        try { 
            HttpPost request = new HttpPost(url); 
            StringEntity params =new StringEntity(data, "UTF-8"); 
            params.setContentType("application/json"); 
            request.addHeader("content-type", "application/json"); 
            request.setEntity(params); 
/*            
    String httpsURL = "https://your.https.url.here/";
    URL myurl = new URL(httpsURL);
    HttpsURLConnection con = (HttpsURLConnection)myurl.openConnection();
    
    InputStream ins = con.getInputStream();
    InputStreamReader isr = new InputStreamReader(ins);
    BufferedReader in = new BufferedReader(isr);

    String inputLine;
 */           
            
            
            HttpResponse response = httpClient.execute(request); 
            responseCode = response.getStatusLine().getStatusCode(); 
 
            if ( responseCode == 200 || responseCode == 204) { 
                if(response.getEntity()!=null){ 
                  /*  String json_string = EntityUtils.toString(response.getEntity());
                    JSONArray jsonrespStr = new JSONArray(json_string);
                    
                    System.out.println(jsonrespStr);
                    */
                    BufferedReader br = new BufferedReader( 
                            new InputStreamReader((response.getEntity().getContent()))); 
 
                    String output; 
                    //logger.info("Output from Server ...." + response.getStatusLine().getStatusCode() + "\n"); 
                    System.out.println("Output from Server ...." + response.getStatusLine().getStatusCode() + "\n"); 
  
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
 
        return respStr; 
    } 
    
    
    
    static int sendPostInt(String data, String url) { 
        // dataoutput="{\"info\": {\"username\": \" drippingmind\", \"userid\": \"449077875\", \"n_answers\": 2, \"date\": \" Wed Dec 05 11:13:18 CET 2012\", \"text\": \" Google provides support in the #Philippines w/ crisis response map 4 typhoon #Pablo #Bopha http://t.co/mJCNBHAJ #DRM #ICT #EmergencyResponse\", \"question\": \"Please tag the following tweet or SMS based on the category or categories that best describes the link(s) included in the tweet/Sms\", \"tweetid\": \"2.76E17\"}, \"state\": 0, \"n_answers\": 30, \"quorum\": 0, \"calibration\": 0, \"app_id\": 4, \"priority_0\": 0}"; 
        int responseCode = -1; 
        String respStr="";
        HttpClient httpClient = new DefaultHttpClient(); 
 
        try { 
            HttpPost request = new HttpPost(url); 
            StringEntity params =new StringEntity(data, "UTF-8"); 
            params.setContentType("application/json"); 
            request.addHeader("content-type", "application/json"); 
            request.setEntity(params); 
            HttpResponse response = httpClient.execute(request); 
            responseCode = response.getStatusLine().getStatusCode(); 
 
            if ( responseCode == 200 || responseCode == 204) { 
                if(response.getEntity()!=null){ 
                  /*  String json_string = EntityUtils.toString(response.getEntity());
                    JSONArray jsonrespStr = new JSONArray(json_string);
                    
                    System.out.println(jsonrespStr);
                    */
                    BufferedReader br = new BufferedReader( 
                            new InputStreamReader((response.getEntity().getContent()))); 
 
                    String output; 
                    //logger.info("Output from Server ...." + response.getStatusLine().getStatusCode() + "\n"); 
                    System.out.println("Output from Server ...." + response.getStatusLine().getStatusCode() + "\n"); 
  
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
 
        return responseCode; 
    }
    
}
