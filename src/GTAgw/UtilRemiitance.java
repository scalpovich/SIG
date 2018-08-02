/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GTAgw;


import java.io.IOException;
import java.io.StringWriter;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.json.JSONException;
import org.json.simple.JSONObject;


/**
 *
 * @author wanda.priatna
 */
public class UtilRemiitance {
    
    public static String pathKey = ApplicationProperties.getSignaturePath().toString();
    public static String passwordKey = ApplicationProperties.getSignaturePass();
    

    public String createStanGenerator(String num) {
        String formatted = String.format("%06d", Integer.parseInt(num));
        return formatted;
    }

    public String transDateTime() {
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmss");
         String folderName = formatter.format(today);
         return folderName;
    }
    
    public String createXmlRemmitanceAccont(String STAN, String TransDateTime,
            String InstID, String AccountID, String Name, String Address, 
            String CountryCode, String BirthDate,String PhoneNumber, String Occupation,
            String Citizenship, String IDNumber, String FundResource, String RegencyCode,
            String benefAccountId, String BenefInstId,String Data
            ){
        String xml = "<?xml version=\"1.0\"?>\n"
                    + "<MethodCall>\n"
                    + "<MethodID>\n"
                        + "<Name>Transfer.Artajasa.CreateRemittanceAccount</Name>\n"
                    + "</MethodID>\n"
                    + "<TransactionID>\n"
                        + "<STAN>"+STAN+"</STAN>\n"
                        + "<TransDateTime>"+TransDateTime+"</TransDateTime>\n"
                        + "<InstID>"+InstID+"</InstID>\n"
                    + "</TransactionID>\n"
                    + "<SenderData>\n"
                        + "<AccountID>"+AccountID+"</AccountID>\n"
                        + "<Name>"+Name+"</Name>\n"
                        + "<Address>"+Address +"</Address>\n"
                        + "<CountryCode>"+CountryCode+"</CountryCode>\n"
                        + "<BirthDate>"+BirthDate+"</BirthDate>\n"
                        + "<BirthPlace></BirthPlace>\n"
                        + "<PhoneNumber>"+PhoneNumber+"</PhoneNumber>\n"
                        + "<Email>email </Email>\n"
                        + "<Occupation>"+Occupation+"</Occupation>\n"
                        + "<Citizenship>"+Citizenship+"</Citizenship>\n"
                        + "<IDNumber>"+IDNumber+"</IDNumber>\n"
                        + "<FundResource>"+FundResource+"</FundResource>\n"
                    + "</SenderData>\n"
                    + "<BeneficiaryData>\n"
                        + "<Acc01>\n"
                        + "<AccountID>"+benefAccountId+"</AccountID>\n"
                        + "<InstID>"+BenefInstId+"</InstID>\n"
                        + "<Name></Name>\n"
                        + "<Relationship></Relationship>\n"
                        + "<RegencyCode>"+RegencyCode+"</RegencyCode>\n"
                        + "<Address></Address>\n"
                        + "<ProvCode></ProvCode>\n"
                        + "<IDNumber></IDNumber>\n"
                    + "</Acc01>\n"
                    + "<Acc02>\n"
                        + "<AccountID></AccountID>\n"
                        + "<InstID></InstID>\n"
                        + "<Name></Name>\n"
                        + "<Relationship></Relationship>\n"
                        + "<RegencyCode></RegencyCode>\n"
                        + "<Address></Address>\n"
                        + "<ProvCode></ProvCode>\n"
                        + "<IDNumber></IDNumber>\n"
                    + "</Acc02>\n"
                    + "<Acc03>\n"
                        + "<AccountID></AccountID>\n"
                        + "<InstID></InstID>\n"
                        + "<Name></Name>\n"
                        + "<Relationship></Relationship>\n"
                        + "<RegencyCode></RegencyCode>\n"
                        + "<Address></Address>\n"
                        + "<ProvCode></ProvCode>\n"
                        + "<IDNumber></IDNumber>\n"
                    + "</Acc03>\n"
                    + "<Acc04>\n"
                        + "<AccountID></AccountID>\n"
                        + "<InstID></InstID>\n"
                        + "<Name></Name>\n"
                        + "<Relationship></Relationship>\n"
                        + "<RegencyCode></RegencyCode>\n"
                        + "<Address></Address>\n"
                        + "<ProvCode></ProvCode>\n"
                        + "<IDNumber></IDNumber>\n"
                    + "</Acc04>\n"
                    + "<Acc05>\n"
                        + "<AccountID></AccountID>\n"
                        + "<InstID></InstID>\n"
                        + "<Name></Name>\n"
                        + "<Relationship></Relationship>\n"
                        + "<RegencyCode></RegencyCode>\n"
                        + "<Address></Address>\n"
                        + "<ProvCode></ProvCode>\n"
                        + "<IDNumber></IDNumber>\n"
                    + "</Acc05>\n"
                    + "</BeneficiaryData>\n"
                    + "<Signature>\n"
                        + "<Data>"+Data+"</Data>\n"
                    + "</Signature>\n"
                    + "</MethodCall>";
        return xml;
    }
    
    public static String responseError(String message) throws IOException{
        JSONObject obj = new JSONObject();
        obj.put("error", message);
        
        StringWriter out = new StringWriter();
        obj.writeJSONString(out);

        String jsonText = out.toString();
        return jsonText;
        
    }
 
    public static String responseCreateAccount(String trxSTAN, String trxTransDateTime, String trxInstID,
            String senderAccountID, String senderName,String senderAddress,String senderCountryCode ,String senderBirthDate, 
            String senderPhoneNumber,String senderEmail ,String senderOccupation, String senderCitizenship, 
            String senderIDNumber , String senderFundResource ,String benefInstID , String benefAccountID,
            String benefName, String benefRelationship, String benefRegencyCode ,String benefAddresss1,String benefProvCode,
            String benefIDNumberr1,String ErrCode, 
            String ErrDescription, String Data) throws JSONException, IOException{
        
        JSONObject obj = new JSONObject();
        obj.put("trxSTAN", trxSTAN);
        obj.put("trxTransDateTime", trxTransDateTime);
        obj.put("trxInstID", trxInstID);
        obj.put("senderAccountID",senderAccountID);
        obj.put("senderName", senderName);
        obj.put("senderAddress", senderAddress);
        obj.put("senderCountryCode", senderCountryCode);
        obj.put("senderBirthDate", senderBirthDate);
        obj.put("senderPhoneNumber",senderPhoneNumber);
        obj.put("senderEmail",senderEmail);
        obj.put("senderOccupation", senderOccupation);
        obj.put("senderCitizenship", senderCitizenship);
        obj.put("senderIDNumber", senderIDNumber);
        obj.put("senderFundResource",senderFundResource);
        obj.put("benefAccountID", benefAccountID);
        obj.put("benefInstID", benefInstID);
        obj.put("benefName", benefName);
        obj.put("benefRelationship",benefRelationship);
        obj.put("benefRegencyCode", benefRegencyCode);
        obj.put("benefAddresss1", benefAddresss1);
        obj.put("benefProvCode", benefProvCode);
        obj.put("benefIDNumberr1", benefIDNumberr1);
        obj.put("ErrCode", ErrCode);
        obj.put("ErrDescription",ErrDescription);
        obj.put("Data", Data);
       

        StringWriter out = new StringWriter();
        obj.writeJSONString(out);

        String jsonText = out.toString();
        //System.out.print(jsonText);
        return jsonText;

    }
    
       public String createXmlTransferInquiry(String STAN, String TransDateTime,String InstID,
            String ProcCode, String ChannelType, String RefNumber, String TerminalID,
            String CountryCode, String LocalDateTime, String senderAccountID,String senderName,
            String senderCurrCode, String senderAmount, String senderRate, String senderAreaCode, 
            String benefInstID, String benefAccountID, String benefCurrCode, String benefAmount,
            String benefCustRefNumber, String benefRegencyCode, String benefPurposeCode,
            String benefPurposeDesc, String Data
            
    ){
        String xml = "<?xml version=\"1.0\"?>\n"
                + "<MethodCall>\n"
                + "<MethodID>\n"
                    + "<Name>Inquiry.Artajasa.ATMBTransfer</Name>\n"
                + "</MethodID>\n"
                + "<TransactionID>\n"
                    + "<STAN>"+STAN+"</STAN>\n"
                    + "<TransDateTime>"+TransDateTime+"</TransDateTime>\n"
                    + "<InstID>"+InstID+"</InstID>\n"
                    + "</TransactionID>\n"
                + "<TransactionInfo>\n"
                    + "<ProcCode>"+ProcCode+"</ProcCode>\n"
                    + "<ChannelType>"+ChannelType+"</ChannelType>\n"
                    + "<RefNumber>"+RefNumber+"</RefNumber>\n"
                    + "<TerminalID>"+TerminalID+"</TerminalID>\n"
                    + "<CountryCode>"+CountryCode+"</CountryCode>\n"
                    + "<LocalDateTime>"+LocalDateTime+"</LocalDateTime>\n"
                + "</TransactionInfo>\n"
                + "<SenderData>\n"
                    + "<AccountID>"+senderAccountID+"</AccountID>\n"
                    + "<Name>"+senderName+"</Name>\n"
                    + "<CurrCode>"+senderCurrCode+"</CurrCode>\n"
                    + "<Amount>"+senderAmount+"</Amount>\n"
                    + "<Rate>"+senderRate+"</Rate>\n"
                    + "<AreaCode>"+senderAreaCode+"</AreaCode>\n"
                + "</SenderData>\n"
                + "<BeneficiaryData>\n"
                    + "<InstID>"+benefInstID+"</InstID>\n"
                    + "<AccountID>"+benefAccountID+"</AccountID>\n"
                    + "<CurrCode>"+benefCurrCode+"</CurrCode>\n"
                    + "<Amount>"+benefAmount+"</Amount>\n"
                    + "<CustRefNumber>"+benefCustRefNumber+"</CustRefNumber>\n"
                    + "<RegencyCode>"+benefRegencyCode+"</RegencyCode>\n"
                    + "<PurposeCode>"+benefPurposeCode+"</PurposeCode>\n"
                    + "<PurposeDesc>"+benefPurposeDesc+"</PurposeDesc>\n"
                + "</BeneficiaryData>\n"
                + "<Signature>\n"
                    + "<Data>"+Data+"</Data>\n"
                + "</Signature>\n"
                + "</MethodCall>";
        return xml;
    }
       
         public static String responseTransferInquiry(String STAN, String trxTransDateTime, String trxInstID,
            String senderAccountID, String senderName,String CurrCode,String senderAmount ,String senderRate, 
            String senderAreaCode,String benefInstID ,String benefAccountID, String benefCurrCode, 
            String benefAmount , String benefCustRefNumber ,String benefName , String benefRegencyCode,
            String benefPurposeCode, String benefPurposeDesc, String Code ,String Description,String Data
            ) throws JSONException, IOException{
        
        JSONObject obj = new JSONObject();
        obj.put("trxSTAN", STAN);
        obj.put("trxTransDateTime", trxTransDateTime);
        obj.put("trxInstID", trxInstID);
        obj.put("senderAccountID",senderAccountID);
        obj.put("senderName", senderName);
        obj.put("CurrCode", CurrCode);
        obj.put("senderAmount", senderAmount);
        obj.put("senderRate", senderRate);
        obj.put("senderAreaCode",senderAreaCode);
        obj.put("benefInstID",benefInstID);
        obj.put("benefAccountID", benefAccountID);
        obj.put("benefCurrCode", benefCurrCode);
        obj.put("benefAmount", benefAmount);
        obj.put("benefCustRefNumber",benefCustRefNumber);
        obj.put("benefName", benefName);
        obj.put("benefRegencyCode", benefRegencyCode);
        obj.put("benefPurposeCode", benefPurposeCode);
        obj.put("benefPurposeDesc",benefPurposeDesc);
        obj.put("Code", Code);
        obj.put("Description", Description);
        obj.put("Data", Data);

        StringWriter out = new StringWriter();
        obj.writeJSONString(out);

        String jsonText = out.toString();
        //System.out.print(jsonText);
        return jsonText;

    }
       
       
       
       
       public String createXmlTransfer(String stan, String trxTransDateTime,
       String trxInstID, String trxProcCode, String trxChannelType, String trxRefNumber,
       String trxTerminalID, String trxCountryCode, String trxLocalDateTime,String senderAccountID,
       String senderName,String senderCurrCode,String senderAmount,String senderRate,
       String senderAreaCode,String benefInstID,String benefAccountID,String benefCurrCode,
       String benefAmount,String benefCustRefNumber,String benefName,String benefRegencyCode,
       String benefPurposeCode,String benefPurposeDesc,String Data
       ){
           String xml = "<?xml version=\"1.0\"?>\n"
                   + "<MethodCall>\n"
                   + "<MethodID>\n"
                   + "<Name>Transfer.Artajasa.ATMBTransfer</Name>\n"
                   + "</MethodID>\n"
                   + "<TransactionID>\n"
                        + "<STAN>"+stan+"</STAN>\n"
                        + "<TransDateTime>"+trxTransDateTime+"</TransDateTime>\n"
                        + "<InstID>"+trxInstID+"</InstID>\n"
                   + "</TransactionID>\n"
                   + "<TransactionInfo>\n"
                        + "<ProcCode>"+trxProcCode+"</ProcCode>\n"
                        + "<ChannelType>"+trxChannelType+"</ChannelType>\n"
                        + "<RefNumber>"+trxRefNumber+"</RefNumber>\n"
                        + "<TerminalID>"+trxTerminalID+"</TerminalID>\n"
                        + "<CountryCode>"+trxCountryCode+"</CountryCode>\n"
                        + "<LocalDateTime>"+trxLocalDateTime+"</LocalDateTime>\n"
                   + "</TransactionInfo>\n"
                   + "<SenderData>\n"
                        + "<AccountID>"+senderAccountID+"</AccountID>\n"
                        + "<Name>"+senderName+"</Name>\n"
                        + "<CurrCode>"+senderCurrCode+"</CurrCode>\n"
                        + "<Amount>"+senderAmount+"</Amount>\n"
                        + "<Rate>"+senderRate+"</Rate>\n"
                        + "<AreaCode>"+senderAreaCode+"</AreaCode>\n"
                   + "</SenderData>\n"
                   + "<BeneficiaryData>\n"
                        + "<InstID>"+benefInstID+"</InstID>\n"
                        + "<AccountID>"+benefAccountID+"</AccountID>\n"
                        + "<CurrCode>"+benefCurrCode+"</CurrCode>\n"
                        + "<Amount>"+benefAmount+"</Amount>\n"
                        + "<CustRefNumber>"+benefCustRefNumber+"</CustRefNumber>\n"
                        + "<Name>"+benefName+"</Name>\n"
                        + "<RegencyCode>"+benefRegencyCode+"</RegencyCode>\n"
                        + "<PurposeCode>"+benefPurposeCode+"</PurposeCode>\n"
                        + "<PurposeDesc>"+benefPurposeDesc+"</PurposeDesc>\n"
                   + "</BeneficiaryData>\n"
                   + "<Signature>\n"
                         + "<Data>"+Data+"</Data>\n"
                   + "</Signature>\n"
                   + "</MethodCall>";
           return xml;
       }
       
       public static String responseTransfer(String STAN, String trxTransDateTime, String trxInstID,
            String senderAccountID, String senderName,String CurrCode,String senderAmount ,String senderRate, 
            String senderAreaCode,String benefInstID ,String benefAccountID, String benefCurrCode, 
            String benefAmount , String benefCustRefNumber ,String benefName , String benefRegencyCode,
            String benefPurposeCode, String benefPurposeDesc, String Code ,String Description,String Data
            ) throws JSONException, IOException{
        
        JSONObject obj = new JSONObject();
        obj.put("trxSTAN", STAN);
        obj.put("trxTransDateTime", trxTransDateTime);
        obj.put("trxInstID", trxInstID);
        obj.put("senderAccountID",senderAccountID);
        obj.put("senderName", senderName);
        obj.put("CurrCode", CurrCode);
        obj.put("senderAmount", senderAmount);
        obj.put("senderRate", senderRate);
        obj.put("senderAreaCode",senderAreaCode);
        obj.put("benefInstID",benefInstID);
        obj.put("benefAccountID", benefAccountID);
        obj.put("benefCurrCode", benefCurrCode);
        obj.put("benefAmount", benefAmount);
        obj.put("benefCustRefNumber",benefCustRefNumber);
        obj.put("benefName", benefName);
        obj.put("benefRegencyCode", benefRegencyCode);
        obj.put("benefPurposeCode", benefPurposeCode);
        obj.put("benefPurposeDesc",benefPurposeDesc);
        obj.put("Code", Code);
        obj.put("Description", Description);
        obj.put("Data", Data);
       
       

        StringWriter out = new StringWriter();
        obj.writeJSONString(out);

        String jsonText = out.toString();
        //System.out.print(jsonText);
        return jsonText;

    }
       
       public String createXmlInquiryStatus(String trxstan,String trxTransDateTime, String trxInstID,
       String trxCountryCode, String trxLocalDateTime, String querySTAN, String queryTransDateTime,String Data
       
       ){
           String xml = "<?xml version=\"1.0\" ?>\n" 
                   + "<MethodCall>\n"
                   + "<MethodID>\n"
                        + "<Name>Status.Artajasa.ATMBTransfer</Name>\n"
                   + "</MethodID>\n"
                   + "<TransactionID>\n"
                        + "<STAN>"+trxstan+"</STAN>\n"
                        + "<TransDateTime>"+trxTransDateTime+"</TransDateTime>\n"
                        + "<InstID>"+trxInstID+"</InstID>\n"
                   + "</TransactionID>\n"
                   + "<TransactionInfo>\n"
                        + "<CountryCode>"+trxCountryCode+"</CountryCode>\n"
                        + "<LocalDateTime>"+trxLocalDateTime+"</LocalDateTime>\n"
                   + "</TransactionInfo>\n"
                   + "<QueryTransactionID>\n"
                        + "<STAN>"+querySTAN+"</STAN>\n"
                        + "<TransDateTime>"+queryTransDateTime+"</TransDateTime>\n"
                   + "</QueryTransactionID>\n"
                   + "<Signature>\n"
                        + "<Data>"+Data+"</Data>\n"
                   + "</Signature>\n"
                   + "</MethodCall>";
           
           return xml;
       }
       
       
        public static String responseInquiryStatus(String STAN, String trxTransDateTime, String trxInstID,
            String querySTAN, String queryTransDateTime,String trfAccountID,String trfName ,String trfCurrCode, 
            String trfAmount,String trfRate ,String benefInstID, String benefAccountID, 
            String benefCurrCode , String benefAmount ,String benefCustRefNumber , String benefName,
            String benefRegencyCode, String respCode, String respDescription ,String trfCode,String trfDescription,String signData
            ) throws JSONException, IOException{
        
        JSONObject obj = new JSONObject();
        obj.put("trxSTAN", STAN);
        obj.put("trxTransDateTime", trxTransDateTime);
        obj.put("trxInstID", trxInstID);
        obj.put("querySTAN",querySTAN);
        obj.put("queryTransDateTime", queryTransDateTime);
        obj.put("trfAccountID", trfAccountID);
        obj.put("trfName", trfName);
        obj.put("trfCurrCode", trfCurrCode);
        obj.put("trfAmount",trfAmount);
        obj.put("trfRate",trfRate);
        obj.put("benefAccoutID", benefInstID);
        obj.put("benefAccountID", benefAccountID);
        obj.put("benefCurrCode", benefCurrCode);
        obj.put("benefAmount",benefAmount);
        obj.put("benefCustRefNumber", benefCustRefNumber);
        obj.put("benefRegencyCode", benefRegencyCode);
        obj.put("benefName", benefName);
        obj.put("benefRegencyCode",benefRegencyCode);
        obj.put("respCode", respCode);
        obj.put("respDescription", respDescription);
        obj.put("trfCode", trfCode);
        obj.put("trfDescription", trfDescription);
        obj.put("respDescription", respDescription);
        obj.put("signData", signData);
        StringWriter out = new StringWriter();
        obj.writeJSONString(out);

        String jsonText = out.toString();
        //System.out.print(jsonText);
        return jsonText;

    }
       
    public static String signatureTransferInquiry(String trxStan, String trxTransDateTime,
            String trxinstid, String trxRefNumber, String trxTerminalID, String trxLocalDateTime,
            String senderAccountID, String senderAmount, String benefInstID, String benefAccountID,
            String benefAmount, String benefCustRefNumber, String trxCountryCode
    ) {
        
        StringBuffer sb = new StringBuffer(); 
        sb.append(trxStan);
        sb.append(trxTransDateTime); 
        sb.append(trxinstid); 
        sb.append(trxRefNumber); 
        sb.append(trxTerminalID); 
        sb.append(trxLocalDateTime); 
        sb.append(senderAccountID); 
        sb.append(senderAmount); 
        sb.append(benefInstID); 
        sb.append(benefAccountID); 
        sb.append(benefAmount); 
        sb.append(benefCustRefNumber); 
        sb.append(trxCountryCode);
        
        String concat = sb.toString(); 
        Ads ads = new Ads(pathKey, passwordKey);
        try {
           ads.loadKeys("artajasa");
           System.out.println("Encrypted Result :" + ads.getDigitalSignatureAsString(concat));
           return ads.getDigitalSignatureAsString(concat);
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        return concat;
    }
    
      public static String signatureTransfer(String trxStan, String trxTransDateTime,
            String trxinstid, String trxRefNumber, String trxTerminalID, String trxLocalDateTime,
            String senderAccountID, String senderAmount, String benefInstID, String benefAccountID,
            String benefAmount, String benefCustRefNumber, String trxCountryCode
    ) {
          
          StringBuffer sb = new StringBuffer(); 
          sb.append(trxStan); 
          sb.append(trxTransDateTime); 
          sb.append(trxinstid); 
          sb.append(trxRefNumber); 
          sb.append(trxTerminalID); 
          sb.append(trxLocalDateTime); 
          sb.append(senderAccountID); 
          sb.append(senderAmount); 
          sb.append(benefInstID); 
          sb.append(benefAccountID); 
          sb.append(benefAmount); 
          sb.append(benefCustRefNumber); 
          sb.append(trxCountryCode);
        String concat = sb.toString();
        Ads ads = new Ads(pathKey, passwordKey);
        try {
           ads.loadKeys("artajasa");
           System.out.println("Encrypted Result :" + ads.getDigitalSignatureAsString(concat));
            return ads.getDigitalSignatureAsString(concat);
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        return concat;
    }
      
      public static String signatureInquiryStatus(String trxStan, String trxTransDateTime,
            String trxinstid, String trxLocalDateTime, String queryStan, String queryTransDateTime) 
      {
          
          StringBuffer sb = new StringBuffer();
          sb.append(trxStan); 
          sb.append(trxTransDateTime);
          sb.append(trxinstid);
          sb.append(trxLocalDateTime);
          sb.append(queryStan);
          sb.append(queryTransDateTime);
        String concat = sb.toString();
        Ads ads = new Ads(pathKey, passwordKey);
        try {
           ads.loadKeys("artajasa");
           System.out.println("Encrypted Result :" + ads.getDigitalSignatureAsString(concat));
            return ads.getDigitalSignatureAsString(concat);
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        return concat;
    }
      
        public static String signatureCreateAccount(String trxStan, String trxTransDateTime,
            String trxinstid,String senderAccountId ,String senderName, String senderAddress, String senderPhoneNumber,
            String senderIdNumber
    ) {
            StringBuffer sb = new StringBuffer(); 
            sb.append(trxStan); 
            sb.append(trxTransDateTime); 
            sb.append(trxinstid); 
            sb.append(senderAccountId); 
            sb.append(senderName); 
            sb.append(senderAddress); 
            sb.append(senderPhoneNumber); 
            sb.append(senderIdNumber); 
             
            String concat = sb.toString(); 
            
        //String concat = trxStan+trxTransDateTime+trxinstid+senderAccountId+semderName+senderAddress+senderPhoneNumber+senderIdNumber;
        Ads ads = new Ads(pathKey, passwordKey);
        try {
           ads.loadKeys("artajasa");
          // System.out.println("Encrypted Result :" + ads.getDigitalSignatureAsString(concat));
           return ads.getDigitalSignatureAsString(concat);
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        return concat;
    }
        
    public String xmlInquiryStatus(String STAN,String TransDateTime,
            String InstID, String CountryCode,String LocalDateTime,
            String queryStan,String queryTransDateTime,String data
            ){
        String xml = "<?xml version=\"1.0\" ?>\n"
                + "<MethodCall>\n"
                + "<MethodID>\n"
                + "<Name>Status.Artajasa.ATMBTransfer</Name>\n"
                + "</MethodID>\n"
                + "<TransactionID>\n"
                + "<STAN>"+STAN+"</STAN>\n"
                + "<TransDateTime>"+TransDateTime+"</TransDateTime>\n"
                + "<InstID>"+InstID+"</InstID>\n"
                + "</TransactionID>\n"
                + "<TransactionInfo>\n"
                + "<CountryCode>"+CountryCode+"</CountryCode>\n"
                + "<LocalDateTime>"+LocalDateTime+"</LocalDateTime>\n"
                + "</TransactionInfo>\n"
                + "<QueryTransactionID>\n"
                + "<STAN>"+queryStan+"</STAN>\n"
                + "<TransDateTime>"+queryTransDateTime+"</TransDateTime>\n"
                + "</QueryTransactionID>\n"
                + "<Signature>\n"
                + "<Data>"+data+"</Data>\n"
                + "</Signature>\n"
                + "</MethodCall>";
        return xml;
    }
    
    
}
