/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GTAgw;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author fjuniadi
 */
public class POJO {
    
    public static class MethodCall{
        private MethodID MethodID;

        public MethodID getMethodID() {
            return MethodID;
        }

        public void setMethodID(MethodID MethodID) {
            this.MethodID = MethodID;
        }
        
    }
    public static class MethodID{
        private String Name;

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }
        
    }
   
    

    @XmlRootElement(name = "Emp")
    @XmlType(propOrder = {"name", "age", "role", "gender"})
    public static class Employee {

        private int id;
        private String gender;
        private int age;
        private String name;
        private String role;
        private String password;

        @XmlTransient
        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @XmlAttribute
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @XmlElement(name = "gen")
        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        @Override
        public String toString() {
            return "ID = " + id + " NAME=" + name + " AGE=" + age + " GENDER=" + gender + " ROLE="
                    + role + " PASSWORD=" + password;
        }

    }

    @XmlRootElement(name = "data")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Reqdata {

        private String type;
        private String bookingid;
        private String clientid;
        private String customer_name;
        private String customer_data;
        private String amount;
        private String productid;
        private int interval;
        private String username;
        private String booking_datetime;
        private String aggrmerchant;
        private String merchant_name;
        private String currency_code;
        private String signature;

        //@XmlAttribute
        public String getbookingid() {
            return bookingid;
        }

        public String getcurrency() {
            return currency_code;
        }

        public String getamount() {
            return amount;
        }

        public String getsignature() {
            return signature;
        }

        @Override
        public String toString() {
            return "type = " + type + " bookingid=" + bookingid + " clientid=" + clientid + " customer_name=" + customer_name + " customer_data="
                    + customer_data + " interval=" + interval;
        }

    }

    @XmlRootElement(name = "Return")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Respdata {

        private String type;
        private String ack;
        private String bookingid;
        private String vaid;
        private String bankcode;
        private String amount;
        private String signature;

        // @XmlAttribute
        public void setsignature(String signature) {
            this.signature = signature;
        }

        public void settype(String type) {
            this.type = type;
        }

        public void setbookingid(String Bookingid) {
            this.bookingid = Bookingid;
        }

        public void setack(String ack) {
            this.ack = ack;
        }

        public void setbankcode(String bankcode) {
            this.bankcode = bankcode;
        }

        public void setamount(String amount) {
            this.amount = amount;
        }

        public String setvaid(String vaid) {
            String start = StringUtils.substringAfter(vaid, "5005");
            return this.vaid = start;
        }

        @Override
        public String toString() {
            return "type = " + type + " bookingid=" + bookingid + " ack=" + ack + " vaid=" + vaid + " bankcode="
                    + bankcode + " amount=" + amount;
        }
    }

    @XmlRootElement(name = "data")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class ReqInqPayment {

        private String type;
        private String vaid;
        private String booking_datetime;
        private String reference_number;
        private String username;
        private String signature;

        //@XmlAttribute
        public String getvaid() {
            return vaid;
        }
        
        public String getbooking_datetime() {
            return booking_datetime;
        }

        public String getreference_number() {
            return reference_number;
        }

        public String getusername() {
            return username;
        }

        public String getsignature() {
            return signature;
        }

//        @Override
//        public String toString() {
//            return "type = " + type + " vaid=" + vaid + " booking_datetime=" + booking_datetime + " reference_number=" + reference_number + " username="
//                    + username + " signature=" + signature;
//        }

    }

    @XmlRootElement(name = "data")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class ResInqPayment {

        private String type;
        private String ack;
        private String bookingid;
        private String customer_name;
        private String max_amount;
        private String min_amount;
        private String productid;
        private String signature;

        // @XmlAttribute
        public void setsignature(String signature) {
            this.signature = signature;
        }

        public void settype(String type) {
            this.type = type;
        }

        public void setbookingid(String Bookingid) {
            this.bookingid = Bookingid;
        }

        public void setack(String ack) {
            this.ack = ack;
        }

        public void setcustomer_name(String customer_name) {
            this.customer_name = customer_name;
        }

        public void setmin_amount(String min_amount) {
            this.min_amount = min_amount;
        }

        public void setmax_amount(String max_amount) {
            this.max_amount = max_amount;
        }

        public void setproductid(String productid) {
            this.productid = productid;
        }

        @Override
        public String toString() {
            return "type = " + type + " bookingid=" + bookingid + " ack=" + ack + " bookingid=" + bookingid + " customer_name="
                    + customer_name + " max_amount=" + max_amount;
        }

        public String toSQLString() {
            return "INSERT INTO `ebill`.`Ttransaction` (`trans_type`,`msg_type`,`vaid`,`cust_name`,`product_id`)\n"
                    + "VALUES('InqPayment','" + type + "','" + bookingid + "','" + customer_name + "','" + productid + "');";
        }
    }

    @XmlRootElement(name = "notification")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class NotifyReq {

        private String type;
        private String bookingid;
        private String customer_name;
        private String issuer_bank;
        private String issuer_name;
        private String amount;
        private String productid;
        private String reference_number;
        private String trxid;
        private String trx_date;
        private String username;
        private String notification_datetime;
        private String signature;
        //@XmlAttribute

        public String getbookingid() {
            return bookingid;
        }

        public String getcustomer_name() {
            return customer_name;
        }

        public String getissuer_bank() {
            return issuer_bank;
        }

        public String getissuer_name() {
            return issuer_name;
        }

        public String getsignature() {
            return signature;
        }

        public String getamount() {
            return amount;
        }

        public String getproductid() {
            return productid;
        }

        public String getreference_number() {
            return reference_number;
        }

        public String gettrxid() {
            return trxid;
        }

        public String gettrx_date() {
            return trx_date;
        }

        public String getusername() {
            return username;
        }

        public String getnotification_datetime() {
            return notification_datetime;
        }
//
//        @Override
//        public String toString() {
//            return "";//"type = " + type + " vaid=" + vaid + " booking_datetime=" + booking_datetime + " reference_number=" + reference_number + " username=" +
//            // username + " signature=" + signature;
//        }

        @Override
        public String toString() {
            return "type=" + type + ", bookingid=" + bookingid + ", customer_name=" + customer_name + ", issuer_bank=" + issuer_bank + ", issuer_name=" + issuer_name + ", amount=" + amount + ", productid=" + productid + ", reference_number=" + reference_number + ", trxid=" + trxid + ", trx_date=" + trx_date + ", username=" + username + ", notification_datetime=" + notification_datetime + ", signature=" + signature;
        }
        
        

        public String toSQLString() {
            return "INSERT INTO `ebill`.`Ttransaction` (`trans_type`,`msg_type`,`amount`,`vaid`,`cust_name`,`Issuer`,`Issuername`,`product_id`,`reff_no`,`trxid`,`trx_date`,`username`,`notification_datetime`)\n"
                    + "VALUES('Notification','" + type + "','" + amount + "','" + bookingid + "','" + customer_name + "','" + issuer_bank + "','" + issuer_name + "','"
                    + productid + "','" + reference_number + "','" + trxid + "','" + trx_date + "','" + username + "','" + notification_datetime + "');";
        }
    }

    @XmlRootElement(name = "return")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class NotifyResp {

        private String type;
        private String ack;
        private String bookingid;
        private String signature;

        // @XmlAttribute
        public void setsignature(String signature) {
            this.signature = signature;
        }

        public void settype(String type) {
            this.type = type;
        }

        public void setbookingid(String Bookingid) {
            this.bookingid = Bookingid;
        }

        public void setack(String ack) {
            this.ack = ack;
        }

        
//    @Override
//    public String toString() {
//        return "";//type = " + type + " bookingid=" + bookingid + " ack=" + ack + " bookingid=" + bookingid + " customer_name=" +
//                //customer_name + " max_amount=" + max_amount;
//    }

        @Override
        public String toString() {
            return "type=" + type + ", ack=" + ack + ", bookingid=" + bookingid + ", signature=" + signature;
        }
    }

    public static class GrabTopUpInqReq {

        public String countryCode;
        public String collectingAgentCode;
        public String deliveryChannelCode;
        public String switchingReferenceCode;

        public void showln() {
            System.out.println(countryCode);
            System.out.println(collectingAgentCode);
            System.out.println(deliveryChannelCode);
            System.out.println(switchingReferenceCode);
        }

        public void LogtoDB(String PhoneNumber, String SysReffId) {
            try {
                Util.LogtoDB_GrabInqReq(SysReffId, countryCode, collectingAgentCode, deliveryChannelCode, switchingReferenceCode);
            } catch (InstantiationException ex) {
                Logger.getLogger(POJO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(POJO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static class GrabTopUpInqResp {

        public String customerName;
        public String grabpayReferenceCode;
        public List<topupRestrictionsList> topupRestrictions;

        public void showln() {
            System.out.println(customerName);
            System.out.println(grabpayReferenceCode);
            System.out.println(topupRestrictions);
        }

        public void LogtoDB(String PhoneNumber, String SysReffId, int i) {
            int j = i;
            try {
                Util.LogtoDB_GrabInqResp(SysReffId, PhoneNumber, customerName, grabpayReferenceCode, topupRestrictions.get(i).currencyCode, topupRestrictions.get(i).minTopup, topupRestrictions.get(i).maxTopup);
            } catch (InstantiationException ex) {
                Logger.getLogger(POJO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(POJO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static class topupRestrictionsList {

        public String currencyCode;
        public String minTopup;
        public String maxTopup;

        public void showln() {
            System.out.println(currencyCode);
            System.out.println(minTopup);
            System.out.println(maxTopup);
        }
    }

    public static class GrabTopUpReq {
        public String  amount;
        public String currencyCode;
        public String approverReferenceCode;
        public String rewardPoints;
        public transactionInfoList transactionInfo;
        
        public void showIn(){
            System.out.println(amount);
            System.out.println(currencyCode);
            System.out.println(approverReferenceCode);
            System.out.println(rewardPoints);
        }
        

        public void LogtoDB(String grabpayReferenceCode, String SysReffId) {
            try {
                Util.LogtoDB_GrabTopUpReq(SysReffId, grabpayReferenceCode, transactionInfo.currencyCode, transactionInfo.amount, transactionInfo.approverReferenceCode, transactionInfo.rewardPoints);
            } catch (InstantiationException ex) {
                Logger.getLogger(POJO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(POJO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static class transactionInfoList {

        public String currencyCode;
        public String amount;
        public String approverReferenceCode;
        public String rewardPoints;

        public void showln() {
            System.out.println(currencyCode);
            System.out.println(amount);
            System.out.println(approverReferenceCode);
            System.out.println(rewardPoints);
        }
    }

    public static class GrabTopUpResp {

        public String Status;
        
        public void showIn(){
            System.out.println(Status);
        }

        public void LogtoDB(String grabpayReferenceCode, String SysReffId) {
            try {
                Util.LogtoDB_GrabTopUpResp(SysReffId, grabpayReferenceCode, Status);
            } catch (InstantiationException ex) {
                Logger.getLogger(POJO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(POJO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static class VerReqBody {

        public String transactionID;
        public String merchantID;
        public String acquirerId;
        public String merchantName;
        public String merchantURL;
        public String currencyCodeISO;
        public Float amount;
        public String cardNumber;
        public String issuerName;
        public String cardholderIP;
        public String authenticationType;
    }

    public static class Header {

        public String appId;
        public String apiVersion;
        public String pgUrl;
        public String transactionDateTime;
        public String transactionDateTimeGMT;
        public String signature;
    }

    public static class VerReqHeader {

        public String appId;
        public String apiVersion;
        public String pgUrl;
        public String transactionDateTime;
        public String transactionDateTimeGMT;
        public String signature;
    }

    public static class VerificationReq {
        //   Header header ;//= new Header();

        Header header;
        VerReqBody body;

        public void showln() {
            System.out.println(header.apiVersion);
            System.out.println(header.appId);
            System.out.println(header.pgUrl);
            System.out.println(header.transactionDateTime);
            System.out.println(header.transactionDateTimeGMT);
            System.out.println(header.signature);
            System.out.println(body.acquirerId);
            System.out.println(body.authenticationType);
            System.out.println(body.amount);
            System.out.println(body.cardNumber);
            System.out.println(body.merchantID);
            System.out.println(body.cardholderIP);
            System.out.println(body.currencyCodeISO);
            System.out.println(body.issuerName);
            System.out.println(body.merchantName);
            System.out.println(body.transactionID);
            System.out.println(body.merchantURL);

        }
    }

    public static class VerRespHeader {

        public String appId;
        public String apiVersion;
        public String pgUrl;
        public String transactionDateTime;
        public String transactionDateTimeGMT;
        public String signature;
    }

    public static class VerificationResp {

        Header header;//= new Header();
        VerRespBody body;

        public void showln() {
            System.out.println(header.apiVersion);
            System.out.println(header.appId);
            System.out.println(header.pgUrl);
            System.out.println(header.transactionDateTime);
            System.out.println(header.transactionDateTimeGMT);
            System.out.println(header.signature);
            System.out.println(body.acquirerId);
            System.out.println(body.authenticationType);
            System.out.println(body.bSecureLogoUrl);
            System.out.println(body.issuerBankLogoUrl);
            System.out.println(body.merchantID);
            System.out.println(body.otpExpiryDateTime);
            System.out.println(body.otpExpiryDateTimeGmt);
            System.out.println(body.otpRefId);
            System.out.println(body.phoneLastNumber4);
            System.out.println(body.transactionID);
            System.out.println(body.verificationDescription);
            System.out.println(body.verificationResponseCode);
        }
    }

    public static class VerRespBody {

        public String transactionID;
        public String merchantID;
        public String acquirerId;
        public String verificationResponseCode;
        public String verificationDescription;
        public String authenticationType;
        public String issuerBankLogoUrl;
        public String bSecureLogoUrl;
        public String phoneLastNumber4;
        public String otpRefId;
        public String otpExpiryDateTimeGmt;
        public String otpExpiryDateTime;
    }

    public static class AuthenticationReq {

        Header header;
        AuthenticationReqBody body;

        public void showln() {
            System.out.println(header.apiVersion);
            System.out.println(header.appId);
            System.out.println(header.pgUrl);
            System.out.println(header.transactionDateTime);
            System.out.println(header.transactionDateTimeGMT);
            System.out.println(header.signature);
            System.out.println(body.acquirerId);
            System.out.println(body.requestType);
            System.out.println(body.amount);
            System.out.println(body.cardNumber);
            System.out.println(body.merchantID);
            System.out.println(body.currencyCodeISO);
            System.out.println(body.transactionID);

        }
    }

    public static class AuthenticationReqBody {

        public String transactionID;
        public String merchantID;
        public String acquirerId;
        public String currencyCodeISO;
        public Float amount;
        public String cardNumber;
        public String otpHashed;
        public String requestType;
    }

    public static class AuthenticationResp {

        Header dasDirectAuthheader;
        AuthenticationRespBody dasDirectAuthBody;

        public void showln() {
            System.out.println(dasDirectAuthheader.apiVersion);
            System.out.println(dasDirectAuthheader.appId);
            System.out.println(dasDirectAuthheader.pgUrl);
            System.out.println(dasDirectAuthheader.transactionDateTime);
            System.out.println(dasDirectAuthheader.transactionDateTimeGMT);
            System.out.println(dasDirectAuthheader.signature);
            System.out.println(dasDirectAuthBody.acquirerId);
            System.out.println(dasDirectAuthBody.authenticationDateTime);
            System.out.println(dasDirectAuthBody.authenticationDateTimeGmt);
            System.out.println(dasDirectAuthBody.authenticationRequestReferenceId);
            System.out.println(dasDirectAuthBody.merchantID);
            System.out.println(dasDirectAuthBody.authenticationResponseDetail);
            System.out.println(dasDirectAuthBody.authenticationResponseCode);
            System.out.println(dasDirectAuthBody.merchantName);
            System.out.println(dasDirectAuthBody.requestType);
            System.out.println(dasDirectAuthBody.transactionID);
            System.out.println(dasDirectAuthBody.verficationId);

        }
    }

    public static class AuthenticationRespBody {

        public String transactionID;
        public String merchantID;
        public String acquirerId;
        public String merchantName;
        public String verficationId;
        public String authenticationDateTime;
        public String authenticationDateTimeGmt;
        public String authenticationRequestReferenceId;
        public String authenticationResponseCode;
        public String authenticationResponseDetail;
        public String requestType;
    }

}
