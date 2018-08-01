/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GTAgw;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author fjuniadi
 */
public class NotifyHandler implements HttpHandler{
    private static Util util = new Util();
    private POJO.NotifyReq NotifyReqObj ;
    private String Ack="05";
    private String RespXML="";
    private InputStreamReader isr;
    private BufferedReader br;
    MyLogger log = new MyLogger(); 
        
    
    public void handle(HttpExchange t) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        String SysReffId = Util.MD5sign(timeStamp)+Util.getrandom();
        
        try{
        isr =  new InputStreamReader(t.getRequestBody(),"utf-8");
        br = new BufferedReader(isr);
        
        int b;
        StringBuilder buf = new StringBuilder(512);
        while ((b = br.read()) != -1) {
            buf.append((char) b);
        }
        
        String input = buf.toString();
        System.out.println(input); 
        //log.sendtoLog(Level.INFO,"Incoming Notify message from handler",input);
        //POJO.ReqInqPayment reqinqPaymentObj = XMLHandler.jaxbXMLToObject(input);
        // Cnnvert XML to Obj 
        try {
            JAXBContext context = JAXBContext.newInstance(POJO.NotifyReq.class);
            Unmarshaller un = context.createUnmarshaller();
            StreamSource streamSource = new StreamSource(new StringReader(input));
            JAXBElement<POJO.NotifyReq> je = un.unmarshal(streamSource, POJO.NotifyReq.class);
            NotifyReqObj = (POJO.NotifyReq)je.getValue();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        
        //Verify Signature Request
        String Username =NotifyReqObj.getusername();
        String Password =Util.getpass(Username);
        String RespSignature = Util.MD5sign(Username + Password );
        
        Boolean SignMatched;              
        if (RespSignature.equals(NotifyReqObj.getsignature())){
             SignMatched=true;
        } else{
            SignMatched=false;
            Ack = "101";
        }
        String customer_name;
        String max_amount;
        String min_amount;
        String amount;
        String status;
        String productid;
        String signature;
        String paytype;
        String paidstatus;
        String balance;
        String refnumber;
        String trxid;
        String AccumulativeAmount;
        
        String approverReferenceCode1 = Util.MD5sign(timeStamp)+Util.getrandom();
        
        if (SignMatched) {
            try {
                 // LOGIC PROCESS
            POJO.GrabTopUpReq GrabTopUpReqObj = new POJO.GrabTopUpReq();
            POJO.transactionInfoList transactionInfoListObj = new POJO.transactionInfoList();
            GrabTopUpReqObj.transactionInfo=transactionInfoListObj;
            
            amount = NotifyReqObj.getamount();
           // refnumber = NotifyReqObj.getreference_number();
           trxid = NotifyReqObj.gettrxid();
            
            String a =  GrabTopUpReqObj.transactionInfo.amount=amount;
            GrabTopUpReqObj.transactionInfo.approverReferenceCode=Util.getrandom();
            String currCode= GrabTopUpReqObj.transactionInfo.currencyCode=ApplicationProperties.getCurrencyCode();
            GrabTopUpReqObj.transactionInfo.rewardPoints="0";

            /* Cari dari database berdasarkan booking ID   */
            /* TFP Product ID untuk grap diisi dengan VAid */
            String grabpayReferenceCode=Util.GetgrabpayReferenceCode(NotifyReqObj.getproductid(), NotifyReqObj.getbookingid(), NotifyReqObj.getcustomer_name());
            
            if (!grabpayReferenceCode.equals("-1")){
                GrabTopUpReqObj.LogtoDB(grabpayReferenceCode, SysReffId);

                Gson gson = new Gson();
               // String Jsonstring = gson.toJson(GrabTopUpReqObj);
               String Jsonstring = "{\"transactionInfo\":{\"amount\":\""+a+"\",\"currencyCode\":\""+currCode+"\",\"approverReferenceCode\":\""+trxid+"\"}}";
                System.out.println(Jsonstring);
                log.sendtoLog(Level.INFO,"info ", Jsonstring);
                log.sendtoLog(Level.INFO,"Waiting for Grab response from  ", Jsonstring);
                String[] JSONresult = new String [2];
//                System.out.println("GrabpayReferenceCode" + grabpayReferenceCode);
//                System.out.println();
                // URL Prod nantinya
                String GrabTopUpURL = ApplicationProperties.getGrabTopUpURL()+grabpayReferenceCode;
               // System.out.println("GrabUrl"+ GrabTopUpURL);
                JSONresult = MyPostMethodhttps.httpsPost(GrabTopUpURL,Jsonstring,grabpayReferenceCode );
                String HttpRC = JSONresult[0];
                String JsonRespstring = JSONresult[1];
                log.sendtoLog(Level.INFO,"Response from Grab GW   ", JsonRespstring);
                POJO.GrabTopUpResp GrabTopUpRespObj = new POJO.GrabTopUpResp();
                GrabTopUpRespObj = gson.fromJson(JsonRespstring, GTAgw.POJO.GrabTopUpResp.class);

                GrabTopUpRespObj.LogtoDB(grabpayReferenceCode, SysReffId);

                String RCState = GrabTopUpRespObj.Status.toUpperCase();
                if (RCState.equals("SUCCESS")){
                    Ack="00";
                } else if (HttpRC.equals("404")){
                    Ack="12";
                } else if (HttpRC.equals("409")){
                    Ack="78";
                } else Ack="05";

                } else { // Grab Reff Code tidak valid/tidak ditemukan
                Ack="76";
            }
            } catch (Exception e) {
                log.sendtoLog(Level.SEVERE,"Exception logic Proses",e.getMessage());
            }
        } 
        
        // Construct Response
        POJO.NotifyResp RespObj = new POJO.NotifyResp();
        RespObj.setack(Ack);
        RespObj.setbookingid(NotifyReqObj.getbookingid());
        RespObj.setsignature(RespSignature);
        
        RespObj.settype("resnotification");

        try {
            JAXBContext context = JAXBContext.newInstance(POJO.NotifyResp.class);
            Marshaller m = context.createMarshaller();
            //for pretty-print XML in JAXB
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Write to System.out for debugging
            // Write to File
            StringWriter sw = new StringWriter();
            m.marshal(RespObj, sw);
            RespXML = sw.toString();

          //  System.out.println("xmlString " +  RespXML);
            log.sendtoLog(Level.INFO,"Notify response XML  message generated",RespXML);
        } catch (JAXBException e) {
            e.printStackTrace();
            log.sendtoLog(Level.SEVERE,"Exception on generating Notify response XML Response",e.getMessage());
        }
       System.out.println("RespXML ="+RespXML); 
       log.sendtoLog(Level.INFO,"Notify response XML  message generated",RespXML);
       /*END OF RESPONSE CONSTRUCTION*/
         
        }
        
        catch (Exception e) 
        {
           // System.out.println("Exception : "+e);
            log.sendtoLog(Level.SEVERE,"Exception on void Handler",e.getMessage());
        }   
        finally{
            String response = RespXML; 
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            //System.out.println(response); 
            log.sendtoLog(Level.INFO,"Send response ack = "+Ack,t.getResponseHeaders().toString()+" "+response);
            os.close();
            br.close();
            isr.close();
        }
        
    }

        
    
    
}
