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
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 *
 * @author wanda.priatna
 */
public class Transfer implements HttpHandler {

    private InputStreamReader isr;
    private BufferedReader br;
    private String response;
    private final String DATE_FORMAT = "yyyyMMddhhmmss";
    MyLogger log = new MyLogger();
    UtilRemiitance utilRem = new UtilRemiitance();
    private String RespXML = "";

    @Override
    public void handle(HttpExchange t) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        // url for production 
         String url = ApplicationProperties.getUrlRemmitanceTrx();
       // String url = "http://localhost:1000/transfer";
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
            System.out.println(input);
            JSONObject obj = new JSONObject(input);

            log.sendtoLog(Level.INFO, "Incoming transfer message ", input);

            String id = obj.getString("id");
            String InstID = obj.getString("InstID");
            String ProcCode = obj.getString("ProcCode");
            String ChannelType = obj.getString("ChannelType");
            String RefNumber = obj.getString("RefNumber");
            String TerminalID = obj.getString("TerminalID");
            String CountryCode = obj.getString("CountryCode");
            String senderAccountID = obj.getString("senderAccountID");
            String senderName = obj.getString("senderName");
            String senderCurrCode = obj.getString("senderCurrCode");
            String senderAmount = obj.getString("senderAmount");
            String senderRate = obj.getString("senderRate");
            String senderAreaCode = obj.getString("senderAreaCode");
            String benefInstID = obj.getString("benefInstID");
            String benefAccountID = obj.getString("benefAccountID");
            String benefCurrCode = obj.getString("benefCurrCode");
            String benefAmount = obj.getString("benefAmount");
            String benefCustRefNumber = obj.getString("benefCustRefNumber");
            String benefName = obj.getString("benefName");
            String benefRegencyCode = obj.getString("benefRegencyCode");
            String benefPurposeCode = obj.getString("benefPurposeCode");
            String benefPurposeDesc = obj.getString("benefPurposeDesc");
            String TransDateTime = utilRem.transDateTime();
            String LocalDateTime = utilRem.transDateTime();

            // logic to create stan generator
            jdbcRemmitance.stanGen(id);
            String stan1 = jdbcRemmitance.selectStan(id);
            String stan = ur.createStanGenerator(stan1);

            String Data = UtilRemiitance.signatureTransfer(stan, TransDateTime, InstID,
                    RefNumber, TerminalID, LocalDateTime, senderAccountID, senderAmount,
                    benefInstID, benefAccountID, benefAmount, benefCustRefNumber, CountryCode);

            jdbcRemmitance.createTransfer(id, stan, TransDateTime, InstID, ProcCode, ChannelType, RefNumber, TerminalID,
                    CountryCode, LocalDateTime, senderAccountID, senderName, senderCurrCode, senderAmount, senderRate,
                    senderAreaCode, benefInstID, benefAccountID, benefCurrCode, benefAmount, benefCustRefNumber, benefName,
                    benefRegencyCode, benefPurposeCode, benefPurposeDesc, Data);

            String xmlTransfer = ur.createXmlTransfer(stan, TransDateTime, InstID, ProcCode, ChannelType,
                    RefNumber, TerminalID, CountryCode, LocalDateTime, senderAccountID, senderName,
                    senderCurrCode, senderAmount, senderRate, senderAreaCode, benefInstID, benefAccountID,
                    benefCurrCode, benefAmount, benefCustRefNumber, benefName, benefRegencyCode,
                    benefPurposeCode, benefPurposeDesc, Data);

            System.out.println(xmlTransfer);
            log.sendtoLog(Level.INFO, "construct Messaging request transfer ", xmlTransfer);

            String[] xml = MyPostMethodhttps.sendPostCa(xmlTransfer, url);
            String xml1 = xml[1];
            String xmlCreateAccount = xml1;

//        StringBuilder builder = new StringBuilder();
//        for(String s : xml) {
//            builder.append(s);
//        }
//        String xmlCreateAccount = builder.toString();
            try {
                DocumentBuilderFactory dbf
                        = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                InputSource is = new InputSource();
                is.setCharacterStream(new StringReader(xmlCreateAccount));

                Document doc = db.parse(is);
                NodeList nodes = doc.getElementsByTagName("MethodResponse");

                // iterate the employees
                for (int i = 0; i < nodes.getLength(); i++) {

                    Element element = (Element) nodes.item(i);

                    NodeList stan2 = element.getElementsByTagName("STAN");
                    Element line = (Element) stan2.item(0);
                    String stan3 = getCharacterDataFromElement(line);

                    NodeList TransDateTime1 = element.getElementsByTagName("TransDateTime");
                    line = (Element) TransDateTime1.item(0);
                    String TransDateTime2 = getCharacterDataFromElement(line);

                    NodeList InstID1 = element.getElementsByTagName("InstID");
                    line = (Element) InstID1.item(0);
                    String InstID2 = getCharacterDataFromElement(line);

                    NodeList senderAccountID1 = element.getElementsByTagName("AccountID");
                    line = (Element) senderAccountID1.item(0);
                    String senderAccountID2 = getCharacterDataFromElement(line);

                    NodeList senderName1 = element.getElementsByTagName("Name");
                    line = (Element) senderName1.item(0);
                    String senderName2 = getCharacterDataFromElement(line);

                    NodeList CurrCode = element.getElementsByTagName("CurrCode");
                    line = (Element) CurrCode.item(0);
                    String senderCurrCode2 = getCharacterDataFromElement(line);

                    NodeList senderAmount1 = element.getElementsByTagName("Amount");
                    line = (Element) senderAmount1.item(0);
                    String senderAmount2 = getCharacterDataFromElement(line);

                    NodeList Rate = element.getElementsByTagName("Rate");
                    line = (Element) Rate.item(0);
                    String senderRate2 = getCharacterDataFromElement(line);

                    NodeList AreaCode = element.getElementsByTagName("AreaCode");
                    line = (Element) AreaCode.item(0);
                    String senderAreaCode2 = getCharacterDataFromElement(line);

                    NodeList benInstID = element.getElementsByTagName("InstID");
                    line = (Element) benInstID.item(1);
                    String benfInstID2 = getCharacterDataFromElement(line);

                    NodeList AccountID = element.getElementsByTagName("AccountID");
                    line = (Element) AccountID.item(1);
                    String benefAccountID2 = getCharacterDataFromElement(line);

                    NodeList benCurrCode = element.getElementsByTagName("CurrCode");
                    line = (Element) benCurrCode.item(1);
                    String benefCurrCode2 = getCharacterDataFromElement(line);

                    NodeList Amount = element.getElementsByTagName("Amount");
                    line = (Element) Amount.item(1);
                    String benefAmount2 = getCharacterDataFromElement(line);

                    NodeList CustRefNumber = element.getElementsByTagName("CustRefNumber");
                    line = (Element) CustRefNumber.item(0);
                    String benefCustRefNumber2 = getCharacterDataFromElement(line);

                    NodeList Name = element.getElementsByTagName("Name");
                    line = (Element) Name.item(1);
                    String benefName2 = getCharacterDataFromElement(line);

                    NodeList RegencyCode = element.getElementsByTagName("RegencyCode");
                    line = (Element) RegencyCode.item(0);
                    String benefRegencyCode2 = getCharacterDataFromElement(line);

                    NodeList PurposeCode = element.getElementsByTagName("PurposeCode");
                    line = (Element) PurposeCode.item(0);
                    String benefPurposeCode2 = getCharacterDataFromElement(line);

                    NodeList PurposeDesc = element.getElementsByTagName("PurposeDesc");
                    line = (Element) PurposeDesc.item(0);
                    String benefPurposeDesc2 = getCharacterDataFromElement(line);

                    NodeList Code = element.getElementsByTagName("Code");
                    line = (Element) Code.item(0);
                    String respCode = getCharacterDataFromElement(line);

                    NodeList Description = element.getElementsByTagName("Description");
                    line = (Element) Description.item(0);
                    String respDescription = getCharacterDataFromElement(line);

                    NodeList respData = element.getElementsByTagName("Data");
                    line = (Element) respData.item(0);
                    String respData2 = getCharacterDataFromElement(line);
                    
             try {
            // send response
            response =  UtilRemiitance.responseTransfer(stan3, TransDateTime2, InstID2, senderAccountID2, 
                    senderName2, senderCurrCode2, senderAmount2, senderRate2, senderAreaCode2, benfInstID2, benefAccountID2, 
                    benefCurrCode2,
                    benefAmount2, benefCustRefNumber2, benefName2, benefRegencyCode2, benefPurposeCode2, benefPurposeDesc2, respCode,
                    respDescription, respData2);
            
            jdbcRemmitance.createTransferResp(id, stan3, TransDateTime2, InstID2, 
                    senderAccountID2, senderName2, senderCurrCode2, senderAmount2, 
                    senderRate2, senderAreaCode2, benfInstID2, benefAccountID2, 
                    benefCurrCode2, benefAmount2, benefCustRefNumber2, benefName2,
                    benefRegencyCode2, benefPurposeCode2, benefPurposeDesc2, respCode, 
                    respDescription, respData2);
            
            
             log.sendtoLog(Level.INFO, "json Response transfer", response);
             
            } catch (JSONException ex) {
                Logger.getLogger(CreateAccount.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("cannot create response there is null parameter");
            }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            System.out.println("One of the field cannot be blank ");
            e.printStackTrace();
        }
        
         t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.toString().getBytes());
        os.close();

    }

}
