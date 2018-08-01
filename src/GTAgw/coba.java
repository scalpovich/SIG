/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GTAgw;

import static GTAgw.Util.getHttpFormatDate;
import static GTAgw.UtilRemiitance.passwordKey;
import static GTAgw.UtilRemiitance.pathKey;
import java.util.Random;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;
import javax.xml.parsers.*;
import org.xml.sax.InputSource;
import org.w3c.dom.*;
import java.io.*;
import org.json.simple.JSONObject;

/**
 *
 * z @author fjuniadi
 */
public class coba {

//        
//        String req = ""
//                + "{\n" +
//"        \"id\": \"springBotadd78\",\n" +
//"        \"name\": \"Spring Boot update 12356\",\n" +
//"        \"description\": \"description Boot update78 \"\n" +
//"    }";
//        
//        String url = "http://localhost:1000/topics";
//        
//      MyPostMethodhttps.sendPostCa(req,url);
    public static void main(String arg[]) throws IOException {
//        String xmlRecords
//                = "<?xml version=\"1.0\"?>\n"
//                + "<MethodResponse>\n"
//                + "    <TransactionID>\n"
//                + "        <STAN>1234</STAN>\n"
//                + "        <TransDateTime>123456</TransDateTime>\n"
//                + "        <InstID>InstID12</InstID>\n"
//                + "    </TransactionID>\n"
//                + "    <SenderData>\n"
//                + "        <AccountID>AccountID</AccountID>\n"
//                + "        <Name>name sender </Name>\n"
//                + "        <Address>Address sender</Address>\n"
//                + "        <CountryCode>CountryCode sender</CountryCode>\n"
//                + "        <BirthDate>BirthDate</BirthDate>\n"
//                + "        <BirthPlace>BirthPlace</BirthPlace>\n"
//                + "        <PhoneNumber>PhoneNumber</PhoneNumber>\n"
//                + "        <Email>Email</Email>\n"
//                + "        <Occupation>Occupation</Occupation>\n"
//                + "        <Citizenship>Citizenship</Citizenship>\n"
//                + "        <IDNumber>IDNumber</IDNumber>\n"
//                + "        <FundResource>FundResource</FundResource>\n"
//                + "    </SenderData>\n"
//                + "    <BeneficiaryData>\n"
//                + "        <Acc01>\n"
//                + "            <AccountID>AccountID</AccountID>\n"
//                + "            <InstID>InstID</InstID>\n"
//                + "            <Name>name benef</Name>\n"
//                + "            <Relationship>Relationship</Relationship>\n"
//                + "            <RegencyCode>RegencyCode</RegencyCode>\n"
//                + "            <Address>Address</Address>\n"
//                + "            <ProvCode>ProvCode</ProvCode>\n"
//                + "            <IDNumber>IDNumber</IDNumber>\n"
//                + "        </Acc01>\n"
//                + "        <Acc02>\n"
//                + "            <AccountID>AccountID</AccountID>\n"
//                + "            <InstID>InstID</InstID>\n"
//                + "            <Name>Name</Name>\n"
//                + "            <Relationship>Relationship</Relationship>\n"
//                + "            <RegencyCode>RegencyCode</RegencyCode>\n"
//                + "            <Address>Address</Address>\n"
//                + "            <ProvCode>ProvCode</ProvCode>\n"
//                + "            <IDNumber>IDNumber</IDNumber>\n"
//                + "        </Acc02>\n"
//                + "        <Acc03>\n"
//                + "            <AccountID>AccountID</AccountID>\n"
//                + "            <InstID>InstID</InstID>\n"
//                + "            <Name>Name</Name>\n"
//                + "            <Relationship>Relationship</Relationship>\n"
//                + "            <RegencyCode>RegencyCode</RegencyCode>\n"
//                + "            <Address>Address</Address>\n"
//                + "            <ProvCode>ProvCode</ProvCode>\n"
//                + "            <IDNumber>IDNumber</IDNumber>\n"
//                + "        </Acc03>\n"
//                + "        <Acc04>\n"
//                + "            <AccountID>AccountID</AccountID>\n"
//                + "            <InstID>InstID</InstID>\n"
//                + "            <Name>Name</Name>\n"
//                + "            <Relationship>Relationship</Relationship>\n"
//                + "            <RegencyCode>RegencyCode</RegencyCode>\n"
//                + "            <Address>Address</Address>\n"
//                + "            <ProvCode>ProvCode</ProvCode>\n"
//                + "            <IDNumber>IDNumber</IDNumber>\n"
//                + "        </Acc04>\n"
//                + "        <Acc05>\n"
//                + "            <AccountID>AccountID</AccountID>\n"
//                + "            <InstID>InstID</InstID>\n"
//                + "            <Name>Name</Name>\n"
//                + "            <Relationship>Relationship</Relationship>\n"
//                + "            <RegencyCode>RegencyCode</RegencyCode>\n"
//                + "            <Address>Address</Address>\n"
//                + "            <ProvCode>ProvCode</ProvCode>\n"
//                + "            <IDNumber>IDNumber</IDNumber>\n"
//                + "        </Acc05>\n"
//                + "    </BeneficiaryData>\n"
//                + "    <Response>\n"
//                + "        <Code>Code</Code>\n"
//                + "        <Description>Description</Description>\n"
//                + "    </Response>\n"
//                + "    <Signature>\n"
//                + "        <Data>Data</Data>\n"
//                + "    </Signature>\n"
//                + "</MethodResponse>";
//
//        try {
//            DocumentBuilderFactory dbf
//                    = DocumentBuilderFactory.newInstance();
//            DocumentBuilder db = dbf.newDocumentBuilder();
//            InputSource is = new InputSource();
//            is.setCharacterStream(new StringReader(xmlRecords));
//
//            Document doc = db.parse(is);
//            NodeList nodes = doc.getElementsByTagName("MethodResponse");
//
//            // iterate the employees
//            for (int i = 0; i < nodes.getLength(); i++) {
//                Element element = (Element) nodes.item(i);
//
//                NodeList name = element.getElementsByTagName("Name");
//                Element line = (Element) name.item(1);
//                System.out.println("Name: " + getCharacterDataFromElement(line));
//                
//                   NodeList namesender = element.getElementsByTagName("Name");
//                 line = (Element) namesender.item(0);
//                System.out.println("namesender: " + getCharacterDataFromElement(line));
//
//                NodeList title = element.getElementsByTagName("TransDateTime");
//                line = (Element) title.item(0);
//                System.out.println("TransDateTime: " + getCharacterDataFromElement(line));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//JSONObject obj = new JSONObject();
//        String test = "this is test";
//        obj.put("name","foo");
//      obj.put("num",test);
//      obj.put("balance",new Double(1000.21));
//      obj.put("is_vip",new Boolean(true));
//
//      StringWriter out = new StringWriter();
//      obj.writeJSONString(out);
//      
//      String jsonText = out.toString();
     // System.out.print(jsonText);
        
        String concat ="000057201807191210200000211230402012TESTTBANDUNG 0856787576001001001";
        Ads ads = new Ads(pathKey, passwordKey);
        try {
           ads.loadKeys("artajasa");
           System.out.println("Encrypted Result :" + ads.getDigitalSignatureAsString(concat));
            
        } catch (Exception ex) {
           ex.printStackTrace();
        }
       
    }
    
  

    public static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "?";
    }
}
