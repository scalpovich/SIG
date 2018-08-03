/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GTAgw;

import static GTAgw.coba.getCharacterDataFromElement;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 *
 * @author dev09
 */
class InquiryStatusEnhance implements HttpHandler {

    private InputStreamReader isr;
    private BufferedReader br;
    private final String DATE_FORMAT = "yyyyMMddhhmmss";
    MyLogger log = new MyLogger();
    UtilRemiitance utilRem = new UtilRemiitance();
    private String response;
    
    private String stan3;
    private String TransDateTime2;
    private String InstID2;
    private String querySTAN2;
    private String queryTransDateTime2;
    private String senderCurrCode2;
    private String senderAccountID2;
    private String senderName2;
    private String senderAreaCode2;
    private String senderAmount2;
    private String senderRate2;
    private String benefInstID23;
    private String benefAccountID2;
    private String benefCurrCode2;
    private String benefAmount2;
    private String benefCustRefNumber2;
    private String benefName2;
    private String RegencyCode2;
    private String resCode2;
    private String respDescription2;
    private String trfCode2;
    private String trfDescription2;
    private String signData2;

    
    @Override
    public void handle(HttpExchange t) throws IOException {
    
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        // url for prod
        String url = ApplicationProperties.getUrlRemmitanceTrx();
        //String url = "http://localhost:1000/inqstatus";
        UtilRemiitance ur = new UtilRemiitance();
        try {
            isr = new InputStreamReader(t.getRequestBody(), "utf-8");
            br = new BufferedReader(isr);
            int b;
            StringBuilder buf = new StringBuilder(512);
            while ((b = br.read()) != -1) {
                buf.append((char) b);
            }
            String input = buf.toString();

            JSONObject obj = new JSONObject(input);
            log.sendtoLog(Level.INFO, "Incoming inquiry status message", input);
            String id = obj.getString("id");
            String InstID = obj.getString("InstID"); // code yang di pakai uniqe untuk inquiry dan transfer 
            String CountryCode = obj.getString("CountryCode");
            String querySTAN = obj.getString("querySTAN");
            String queryTransDateTime = obj.getString("queryTransDateTime");
            String LocalDateTime = utilRem.transDateTime();
            String TransDateTime = utilRem.transDateTime();
            // logic to create stan generator
            jdbcRemmitance.stanGen(id);
            String stan1 = jdbcRemmitance.selectStan(id);
            String stan = ur.createStanGenerator(stan1);
            
            String Data = UtilRemiitance.signatureInquiryStatus(stan, TransDateTime, InstID, 
                     LocalDateTime, querySTAN, queryTransDateTime);

            jdbcRemmitance.inquiryStatus(id, stan, TransDateTime, InstID, CountryCode, LocalDateTime, querySTAN, queryTransDateTime, Data);
            // create xml 
            String xmlInquiryStatus = ur.xmlInquiryStatus(stan, TransDateTime, InstID, CountryCode, LocalDateTime, 
                    querySTAN, queryTransDateTime, Data);
            
            System.out.println(xmlInquiryStatus);
            log.sendtoLog(Level.INFO, "remmitance transfer inquiry message", xmlInquiryStatus);

            String[] xml = MyPostMethodhttps.sendPostCa(xmlInquiryStatus, url);
            String xml1 = xml[1];
            String xmlCreateAccount = xml1;
            
             DocumentBuilderFactory dbf
                        = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                InputSource is = new InputSource();
                is.setCharacterStream(new StringReader(xmlCreateAccount));

                Document doc = db.parse(is);
                NodeList nodes = doc.getElementsByTagName("MethodResponse");
                
                // iterate
                for (int i = 0; i < nodes.getLength(); i++) {
                    Element element = (Element) nodes.item(i);

                    NodeList stan2 = element.getElementsByTagName("STAN");
                    Element line = (Element) stan2.item(0);
                    stan3 = getCharacterDataFromElement(line);

                    NodeList TransDateTime1 = element.getElementsByTagName("TransDateTime");
                    line = (Element) TransDateTime1.item(0);
                    TransDateTime2 = getCharacterDataFromElement(line);

                    NodeList InstID1 = element.getElementsByTagName("InstID");
                    line = (Element) InstID1.item(0);
                    InstID2 = getCharacterDataFromElement(line);
                    
                    NodeList querySTAN1 = element.getElementsByTagName("STAN");
                    line = (Element) querySTAN1.item(1);
                    querySTAN2 = getCharacterDataFromElement(line);
                    
                    NodeList queryTransDateTime1 = element.getElementsByTagName("TransDateTime");
                    line = (Element) queryTransDateTime1.item(1);
                    queryTransDateTime2 = getCharacterDataFromElement(line);
                    
                    NodeList senderAccountID1 = element.getElementsByTagName("AccountID");
                    line = (Element) senderAccountID1.item(0);
                    senderAccountID2 = getCharacterDataFromElement(line);
                    
                    NodeList senderName1 = element.getElementsByTagName("Name");
                    line = (Element) senderName1.item(0);
                    senderName2 = getCharacterDataFromElement(line);
                    
                    NodeList senderCurrCode1 = element.getElementsByTagName("CurrCode");
                    line = (Element) senderCurrCode1.item(0);
                    senderCurrCode2 = getCharacterDataFromElement(line);
                    
                    NodeList senderAmount = element.getElementsByTagName("Amount");
                    line = (Element) senderAmount.item(0);
                    senderAmount2 = getCharacterDataFromElement(line);
                    
                    NodeList senderRate = element.getElementsByTagName("Rate");
                    line = (Element) senderRate.item(0);
                    senderRate2 = getCharacterDataFromElement(line);
                 
                    NodeList benefInstID12 = element.getElementsByTagName("InstID");
                    line = (Element) benefInstID12.item(1);
                    benefInstID23 = getCharacterDataFromElement(line);
                    
                    NodeList benefAccountID1 = element.getElementsByTagName("AccountID");
                    line = (Element) benefAccountID1.item(1);
                    benefAccountID2 = getCharacterDataFromElement(line);
                    
                     NodeList benefCurrCode1 = element.getElementsByTagName("CurrCode");
                    line = (Element) benefCurrCode1.item(1);
                    benefCurrCode2 = getCharacterDataFromElement(line);
                    
                    NodeList benefAmount1 = element.getElementsByTagName("Amount");
                    line = (Element) benefAmount1.item(1);
                    benefAmount2 = getCharacterDataFromElement(line);
                    
                    NodeList benefCustRefNumber = element.getElementsByTagName("CustRefNumber");
                    line = (Element) benefCustRefNumber.item(0);
                    benefCustRefNumber2 = getCharacterDataFromElement(line);
                    
                    NodeList benefName = element.getElementsByTagName("Name");
                    line = (Element) benefName.item(1);
                    benefName2 = getCharacterDataFromElement(line);
                    
                    NodeList RegencyCode = element.getElementsByTagName("RegencyCode");
                    line = (Element) RegencyCode.item(0);
                    RegencyCode2 = getCharacterDataFromElement(line);
                    
                     NodeList resCode = element.getElementsByTagName("Code");
                    line = (Element) resCode.item(0);
                    resCode2 = getCharacterDataFromElement(line);
                    
                    NodeList respDescription = element.getElementsByTagName("Description");
                    line = (Element) respDescription.item(0);
                    respDescription2 = getCharacterDataFromElement(line);
                    
                    NodeList trfCode = element.getElementsByTagName("Code");
                    line = (Element) trfCode.item(1);
                    trfCode2 = getCharacterDataFromElement(line);
                    
                    NodeList trfDescription = element.getElementsByTagName("Description");
                    line = (Element) trfDescription.item(1);
                    trfDescription2 = getCharacterDataFromElement(line);
                    
                    NodeList signData = element.getElementsByTagName("Data");
                    line = (Element) signData.item(0);
                    signData2 = getCharacterDataFromElement(line);
                }
                
            response = UtilRemiitance.responseInquiryStatus(stan3, TransDateTime2, InstID2, querySTAN2,
                    queryTransDateTime2, senderAccountID2, senderName2, senderCurrCode2, senderAmount2, senderRate2,
                    benefInstID23, benefAccountID2, benefCurrCode2, benefAmount2, benefCustRefNumber2, benefName2, RegencyCode2,
                    resCode2, respDescription2, trfCode2, trfDescription2, signData2);

            jdbcRemmitance.inquiryStatusResp(id, stan3, TransDateTime2, InstID2, querySTAN2,
                    queryTransDateTime2, senderAccountID2, senderName2, senderCurrCode2,
                    senderAmount2, senderRate2, benefInstID23, benefAccountID2, benefCurrCode2,
                    benefAmount2, benefCustRefNumber2, benefName2, RegencyCode2, resCode2,
                    respDescription2, trfCode2, trfDescription2, signData2);

            log.sendtoLog(Level.INFO, "json Response", response);
        } catch (Exception e) {
            e.printStackTrace();
            String errorResp = UtilRemiitance.responseError("upps something error happen check console");
            t.sendResponseHeaders(200, errorResp.length());
            OutputStream os = t.getResponseBody();
            os.write(errorResp.getBytes());
            os.close();
        }
    }
    
}
