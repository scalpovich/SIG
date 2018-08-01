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
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.logging.Level;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.util.logging.Level;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author fjuniadi
 */
public class InqReqHandler implements HttpHandler{
    private static Util util = new Util();
    private POJO.ReqInqPayment InqReqObj ;
    private String Ack="05";
    private String RespXML="";
    private InputStreamReader isr;
    private BufferedReader br;
    private String customer_name;
    private String max_amount;
    private String min_amount;
//        String amount="0";
    private String paytype;
    private String refnumber;
    private String productid;
    private String paidstatus;
    private String signature;
 
    MyLogger log = new MyLogger(); 
        
    
    public void handle(HttpExchange t) throws IOException {
        String RespSignature;
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        String SysReffId = Util.MD5sign(timeStamp)+Util.getrandom();
        RespXML = "";
        String bookingid="";
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
        log.sendtoLog(Level.INFO,"Incoming PayInq message from handler",input);
         //Cnnvert XML to Obj 
        
        try {
            JAXBContext context = JAXBContext.newInstance(POJO.ReqInqPayment.class);
            Unmarshaller un = context.createUnmarshaller();
            StreamSource streamSource = new StreamSource(new StringReader(input));
            JAXBElement<POJO.ReqInqPayment> je = un.unmarshal(streamSource, POJO.ReqInqPayment.class);
            InqReqObj = (POJO.ReqInqPayment)je.getValue();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        
        //Verify Signature Request
            String Username =InqReqObj.getusername();
            String Password =Util.getpass(Username);
            RespSignature = Util.MD5sign(Username + Password );

        String vaid1 = InqReqObj.getvaid();
        String vaid = TrimVaid.trim(vaid1);
        
        Boolean SignMatched = false;
                        
        if (RespSignature.equals(InqReqObj.getsignature())){
             SignMatched=true;
        } else{
            SignMatched=false;
            Ack = "01";
        }
         customer_name="";
         max_amount="0";
         min_amount="0";
         
//        String amount="0";
      
        if (SignMatched) {
            // LOGIC PROCESS
            String CACode= ApplicationProperties.getCACode("collectingAgentCode");
            POJO.GrabTopUpInqReq GrabTopUpInqReqObj = new POJO.GrabTopUpInqReq();
            String collectingAgentCode =  GrabTopUpInqReqObj.collectingAgentCode=CACode;
            String countryCode= GrabTopUpInqReqObj.countryCode=ApplicationProperties.getCountryCode();
            String deliveryChannelCode = GrabTopUpInqReqObj.deliveryChannelCode=ApplicationProperties.getDeliveryChannelCode();
            GrabTopUpInqReqObj.switchingReferenceCode=InqReqObj.getreference_number();
            refnumber = GrabTopUpInqReqObj.switchingReferenceCode=InqReqObj.getreference_number();

            Gson gson = new Gson();
            String Jsonstring ="{\"switchingReferenceCode\": \""+SysReffId+"\", \"collectingAgentCode\":\""+collectingAgentCode+"\", \"countryCode\":\""+countryCode+"\", \"deliveryChannelCode\":\""+deliveryChannelCode+"\"}";
            util.verbose("Waiting for Grab GW response from "+Jsonstring);
             log.sendtoLog(Level.INFO,"Waiting for Grab GW response from ",Jsonstring);
          //  System.out.println("Json :"  + Jsonstring);
            String[] JSONresult = new String [2];
            String GrabTopUpInqURL =ApplicationProperties.getGrabTopUpInqURL()+vaid;
         //   System.out.println(GrabTopUpInqURL);
            
            if(SignMatched == true){
                Util.LogtoDB_GrabInqReq(SysReffId, countryCode, collectingAgentCode, deliveryChannelCode, collectingAgentCode);
               
            }
            
            JSONresult =MyPostMethodhttps.getResponse( GrabTopUpInqURL,Jsonstring, vaid);
            String HttpRC = JSONresult[0];
            String JsonRespstring = JSONresult[1];

            log.sendtoLog(Level.INFO,"Response from Grab GW  ",JsonRespstring);
          
            
            POJO.GrabTopUpInqResp GrabTopUpInqRespObj = new POJO.GrabTopUpInqResp();
            GrabTopUpInqRespObj = gson.fromJson(JsonRespstring, GTAgw.POJO.GrabTopUpInqResp.class);
            customer_name=GrabTopUpInqRespObj.customerName.trim();
            if (HttpRC.equals("200")){
                if (!customer_name.equals("")) {
                    Ack ="00";
                    for(int i=0; i<GrabTopUpInqRespObj.topupRestrictions.size(); i++){
                        if (GrabTopUpInqRespObj.topupRestrictions.get(i).currencyCode.equals("IDR")){
                            if (!GrabTopUpInqRespObj.topupRestrictions.get(0).maxTopup.isEmpty()){
                                max_amount=GrabTopUpInqRespObj.topupRestrictions.get(0).maxTopup;
                            } else max_amount="0";
                            if (!GrabTopUpInqRespObj.topupRestrictions.get(0).minTopup.isEmpty()){
                                min_amount=GrabTopUpInqRespObj.topupRestrictions.get(0).minTopup;
                            } else min_amount="0";
//                            if(Integer.parseInt(max_amount) <= Integer.parseInt(min_amount)){
//                                Ack = "13";
//                            }
                            GrabTopUpInqRespObj.LogtoDB(InqReqObj.getvaid(), SysReffId, i);
                        } else Ack ="13"; // Currency not IDR
                    }
                } else Ack ="76"; //!customer_name.equals("")
            } else if (HttpRC.equals("404")){
                Ack ="76";
            }else if (HttpRC.equals("400")){
                Ack ="91";
            } 
            //else  Ack="05";
            else if(HttpRC.equals("401")){
                Ack = "01";
            }
        String hhmmss = new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());
        //String bookingid = hhmmss+Util.getrandom();
        bookingid = GrabTopUpInqRespObj.grabpayReferenceCode;
     //   String bookingid = GrabTopUpInqRespObj.grabpayReferenceCode.substring(0, (GrabTopUpInqRespObj.grabpayReferenceCode.length()-7));
       
        //Load  to trx key table
        if (Ack.equals("00")){
            try{
            if (!Util.insertTrxKeyData(SysReffId, GrabTopUpInqRespObj.grabpayReferenceCode, InqReqObj.getvaid(), bookingid,  GrabTopUpInqRespObj.customerName)){
               log.sendtoLog(Level.SEVERE,"Error on inserting to trx key table",""); 
               Ack="91";
            }
            } catch (Exception e){
                Ack="91";
                e.printStackTrace();
                log.sendtoLog(Level.SEVERE,"Exception on inserting to trx key table",e.getMessage());
            }
        }
            
           
        }
        
        // Construct Response
        POJO.ResInqPayment RespObj = new POJO.ResInqPayment();
        RespObj.setack(Ack);
        RespObj.setbookingid(bookingid);
        RespObj.setcustomer_name(customer_name);
       // Integer int_min_amount = Integer.parseInt(min_amount);
//        min_amount = min_amount.substring(0, (min_amount.length()-3));
           if(min_amount.length() > 3){
               RespObj.setmin_amount(min_amount.substring(0, (min_amount.length()-3)));
           }
           else RespObj.setmin_amount("0");
           
           if(max_amount.length() > 3){
               RespObj.setmax_amount(max_amount.substring(0, (max_amount.length()-3)));
           }
           else RespObj.setmax_amount("0");
        
      //  RespObj.setmax_amount(max_amount.substring(0, (max_amount.length()-3)));
        RespObj.setproductid(InqReqObj.getvaid());
        RespObj.setsignature(RespSignature);
        RespObj.settype("resinqpayment");
         System.out.println(RespObj.toString());
        String SQLstmt=RespObj.toSQLString();
        
//       if (!Util.executeSQL(SQLstmt)){
         // log.sendtoLog(Level.SEVERE,"Error on inserting to database Pay Inq XML Response",SQLstmt); 
//        }

        try {
            JAXBContext context = JAXBContext.newInstance(POJO.ResInqPayment.class);
            Marshaller m = context.createMarshaller();
            //for pretty-print XML in JAXB
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Write to System.out for debugging
            // Write to File
            StringWriter sw = new StringWriter();
            m.marshal(RespObj, sw);
            RespXML = sw.toString();

           // System.out.println("xmlString " +  RespXML);
          //  log.sendtoLog(Level.INFO,"XML Pay Inq response message generated",RespXML);
        } catch (JAXBException e) {
            e.printStackTrace();
           // log.sendtoLog(Level.SEVERE,"Exception on generating Pay Inq XML Response",e.getMessage());
        }
     // System.out.println("RespXML ="+RespXML); 
       log.sendtoLog(Level.INFO,"RespXML ",RespXML);
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
           // System.out.println(response); 
            log.sendtoLog(Level.INFO,"Send response ack = "+Ack,t.getResponseHeaders().toString()+" "+response);
            os.close();
            br.close();
            isr.close();
        }
        
    }

}
