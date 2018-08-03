///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package GTAgw;
//
//import static GTAgw.coba.getCharacterDataFromElement;
//import com.google.gson.Gson;
//import com.sun.net.httpserver.HttpExchange;
//import com.sun.net.httpserver.HttpHandler;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.io.StringReader;
//import java.io.StringWriter;
//import java.io.UnsupportedEncodingException;
//import java.io.Writer;
//import static java.lang.System.out;
//import java.net.URI;
//import java.net.URLDecoder;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBElement;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Marshaller;
//import javax.xml.bind.Unmarshaller;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.transform.stream.StreamSource;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.NodeList;
//import org.xml.sax.InputSource;
//
///**
// *
// * @author wanda.priatna
// */
//public class CreateAccount implements HttpHandler {
//
//    private InputStreamReader isr;
//    private BufferedReader br;
//    private String response;
//    private final String DATE_FORMAT = "yyyyMMddhhmmss";
//    MyLogger log = new MyLogger();
//    UtilRemiitance utilRem = new UtilRemiitance();
//    private String RespXML = "";
//
//    public void handle(HttpExchange t) throws IOException {
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
//        String url = ApplicationProperties.getUrlRemmitance();
//        UtilRemiitance ur = new UtilRemiitance();
//        try {
//            isr = new InputStreamReader(t.getRequestBody(), "utf-8");
//            br = new BufferedReader(isr);
//
//            int b;
//            StringBuilder buf = new StringBuilder(512);
//            while ((b = br.read()) != -1) {
//                buf.append((char) b);
//            }
//
//            String input = buf.toString();
//            //System.out.println(input);
//            JSONObject obj = new JSONObject(input);
//            String TransDateTime = utilRem.transDateTime();
//
//            log.sendtoLog(Level.INFO, "Incoming Notify message from handler", input);
//
//            String id = obj.getString("id");
//            //String STAN = "3456";  // menunggu kode unique yang di pakai untuk referensi generate kode 
//            String InstID = obj.getString("InstID"); // code yang di pakai uniqe untuk inquiry dan transfer 
//            String AccountID = obj.getString("AccountID");
//            String CountryCode = obj.getString("CountryCode");
//            String Name = obj.getString("Name");
//            String Address = obj.getString("Address");
//            String BirthDate = obj.getString("BirthDate");
//            String PhoneNumber = obj.getString("PhoneNumber");
//            // String Email = obj.getString("Email");
//            String Occupation = obj.getString("Occupation");
//            String Citizenship = obj.getString("Citizenship");
//            String IDNumber = obj.getString("IDNumber");
//            String FundResource = obj.getString("FundResource");
//            String RegencyCode = obj.getString("RegencyCode");
//            String BenefAccountId = obj.getString("BenefAccountId");
//            String BenefInstId =  obj.getString("BenefInstId");
//
//         
//            // logic to create stan generator
//            jdbcRemmitance.stanGen(id);
//            String stan1 = jdbcRemmitance.selectStan(id);
//            String stan = ur.createStanGenerator(stan1);
//            
//               String Data = UtilRemiitance.signatureCreateAccount(stan,TransDateTime,InstID,AccountID,Name,Address,PhoneNumber,IDNumber);
//
//            jdbcRemmitance.insertCreateRemmitance(id, stan, TransDateTime, InstID, AccountID, Name,
//                    Address, BirthDate, PhoneNumber, Occupation, Citizenship, IDNumber, FundResource, RegencyCode, Data);
//            
//            String createRemmitanceAccount = ur.createXmlRemmitanceAccont(stan, TransDateTime, InstID, AccountID,
//                    Name, Address, CountryCode, BirthDate, PhoneNumber, Occupation, Citizenship, IDNumber, FundResource,
//                    RegencyCode,BenefAccountId , BenefInstId , Data);
//            
//            log.sendtoLog(Level.INFO, "construct Messaging request ", createRemmitanceAccount);
//
//            String[] xml = MyPostMethodhttps.sendPostCa(createRemmitanceAccount, url);
//            String xml1 = xml[1];
//            String xmlCreateAccount = xml1;
//
////        StringBuilder builder = new StringBuilder();
////        for(String s : xml) {
////            builder.append(s);
////        }
////        String xmlCreateAccount = builder.toString();
//            try {
//                DocumentBuilderFactory dbf
//                        = DocumentBuilderFactory.newInstance();
//                DocumentBuilder db = dbf.newDocumentBuilder();
//                InputSource is = new InputSource();
//                is.setCharacterStream(new StringReader(xmlCreateAccount));
//
//                Document doc = db.parse(is);
//                NodeList nodes = doc.getElementsByTagName("MethodResponse");
//
//                // iterate the employees
//                for (int i = 0; i < nodes.getLength(); i++) {
//                    Element element = (Element) nodes.item(i);
//                    NodeList name = element.getElementsByTagName("STAN");
//                    Element line = (Element) name.item(0);
//                    String stanresp = getCharacterDataFromElement(line);
//
//                    NodeList title = element.getElementsByTagName("TransDateTime");
//                    line = (Element) title.item(0);
//                    String transdatetimeresp = getCharacterDataFromElement(line);
//
//                    NodeList instid = element.getElementsByTagName("InstID");
//                    line = (Element) instid.item(0);
//                    String instidresp = getCharacterDataFromElement(line);
//
//                    NodeList senderaccid = element.getElementsByTagName("AccountID");
//                    line = (Element) senderaccid.item(0);
//                    String senderaccountid = getCharacterDataFromElement(line);
//
//                    NodeList sendername1 = element.getElementsByTagName("Name");
//                    line = (Element) sendername1.item(0);
//                    String sendername = getCharacterDataFromElement(line);
//
//                    NodeList Address1 = element.getElementsByTagName("Address");
//                    line = (Element) Address1.item(0);
//                    String senderAddress = getCharacterDataFromElement(line);
//
//                    NodeList CountryCode1 = element.getElementsByTagName("CountryCode");
//                    line = (Element) CountryCode1.item(0);
//                    String senderCountryCode = getCharacterDataFromElement(line);
//
//                    NodeList BirthDate1 = element.getElementsByTagName("BirthDate");
//                    line = (Element) BirthDate1.item(0);
//                    String senderBirthDate = getCharacterDataFromElement(line);
//
//                    NodeList BirthPlace1 = element.getElementsByTagName("BirthPlace");
//                    line = (Element) BirthPlace1.item(0);
//                    String senderBirthPlace = getCharacterDataFromElement(line);
//
//                    NodeList PhoneNumber1 = element.getElementsByTagName("PhoneNumber");
//                    line = (Element) PhoneNumber1.item(0);
//                    String senderPhoneNumber = getCharacterDataFromElement(line);
//
//                    NodeList Email1 = element.getElementsByTagName("Email");
//                    line = (Element) Email1.item(0);
//                    String senderEmail = getCharacterDataFromElement(line);
//
//                    NodeList Occupation1 = element.getElementsByTagName("Occupation");
//                    line = (Element) Occupation1.item(0);
//                    String senderOccupation = getCharacterDataFromElement(line);
//
//                    NodeList Citizenship1 = element.getElementsByTagName("Citizenship");
//                    line = (Element) Citizenship1.item(0);
//                    String senderCitizenship = getCharacterDataFromElement(line);
//
//                    NodeList IDNumber1 = element.getElementsByTagName("IDNumber");
//                    line = (Element) IDNumber1.item(0);
//                    String senderIDNumber = getCharacterDataFromElement(line);
//
//                    NodeList FundResource1 = element.getElementsByTagName("FundResource");
//                    line = (Element) FundResource1.item(0);
//                    String senderFundResource = getCharacterDataFromElement(line);
//
//                    // parsing account no 1 
//                    NodeList AccountID1 = element.getElementsByTagName("AccountID");
//                    line = (Element) AccountID1.item(1);
//                    String benefAccountID = getCharacterDataFromElement(line);
//
//                    NodeList InstID1 = element.getElementsByTagName("InstID");
//                    line = (Element) InstID1.item(1);
//                    String benefInstID1 = getCharacterDataFromElement(line);
//
//                    NodeList Name1 = element.getElementsByTagName("Name");
//                    line = (Element) Name1.item(1);
//                    String benefName1 = getCharacterDataFromElement(line);
//
//                    NodeList Relationship1 = element.getElementsByTagName("Relationship");
//                    line = (Element) Relationship1.item(1);
//                    String benefRelationship1 = getCharacterDataFromElement(line);
//
//                    NodeList RegencyCode1 = element.getElementsByTagName("RegencyCode");
//                    line = (Element) RegencyCode1.item(0);
//                    String benefRegencyCode = getCharacterDataFromElement(line);
//
//                    NodeList benefAddress1 = element.getElementsByTagName("Address");
//                    line = (Element) benefAddress1.item(1);
//                    String benefAddresss1 = getCharacterDataFromElement(line);
//
//                    NodeList ProvCode1 = element.getElementsByTagName("ProvCode");
//                    line = (Element) ProvCode1.item(0);
//                    String benefProvCode = getCharacterDataFromElement(line);
//
//                    NodeList benefIDNumber1 = element.getElementsByTagName("IDNumber");
//                    line = (Element) ProvCode1.item(1);
//                    String benefIDNumberr1 = getCharacterDataFromElement(line);
//
////                    // parsing account no 2
//                    NodeList code = element.getElementsByTagName("Code");
//                    line = (Element) code.item(0);
//                    String responseCode = getCharacterDataFromElement(line);
//
//                    NodeList Description = element.getElementsByTagName("Description");
//                    line = (Element) Description.item(0);
//                    String responseDescription = getCharacterDataFromElement(line);
//
//                    NodeList signatureData1 = element.getElementsByTagName("Data");
//                    line = (Element) signatureData1.item(0);
//                    String signatureData = getCharacterDataFromElement(line);
//
//                    try {
//                        // send response
//                        response = UtilRemiitance.responseCreateAccount(stanresp, transdatetimeresp,
//                                instidresp, senderaccountid, sendername, senderAddress, senderCountryCode, senderBirthDate,
//                                senderPhoneNumber, senderEmail, senderOccupation, senderCitizenship, senderIDNumber, senderFundResource, benefInstID1, benefAccountID, benefName1,
//                                benefRelationship1, benefRegencyCode, benefAddresss1, benefProvCode, benefIDNumberr1, responseCode, responseDescription, signatureData);
//                        log.sendtoLog(Level.INFO, "json Response", response);
//
//                        jdbcRemmitance.insertCreateRemmitanceResp(id, stanresp, transdatetimeresp, instidresp, senderaccountid,
//                                sendername, senderAddress, senderBirthDate, senderPhoneNumber, senderOccupation,
//                                senderCitizenship, senderIDNumber, senderFundResource, benefInstID1, benefAccountID,
//                                benefName1, benefRelationship1, benefRegencyCode, responseCode, responseDescription, signatureData);
//
//                    } catch (JSONException ex) {
//                        Logger.getLogger(CreateAccount.class.getName()).log(Level.SEVERE, null, ex);
//                        System.out.println("cannot create response there is null parameter");
//                        t.sendResponseHeaders(200, response.length());
//                        OutputStream os = t.getResponseBody();
//                        os.write(response.toString().getBytes());
//                        os.close();
//                       
//                    }
//
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                t.sendResponseHeaders(200, response.length());
//                OutputStream os = t.getResponseBody();
//                os.write(response.toString().getBytes());
//                os.close();
//                
//            }
//
//        } catch (Exception e) {
//            //System.out.println("One of the field cannot be blank ");
//            e.printStackTrace();
//            t.sendResponseHeaders(200, response.length());
//            OutputStream os = t.getResponseBody();
//            os.write(response.toString().getBytes());
//            os.close();
//            
//        }
//        // String response = "";
//        t.sendResponseHeaders(200, response.length());
//        OutputStream os = t.getResponseBody();
//        os.write(response.toString().getBytes());
//        os.close();
//    }
//}
