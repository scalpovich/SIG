/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GTAgw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Arrays;
import java.nio.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;
import java.util.logging.*;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
//import org.apache.commons.codec.binary.Base64;
import java.util.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

import org.jpos.iso.packager.GenericPackager;
/**
 *
 * @author funiadi
 */
public class Util {
    private static Connection con;
    private static ResultSet data;
    private static PreparedStatement stmt; 
    static String dbUsed = "MySQL"; 
/*    PostgresHelper client = new PostgresHelper(
	                DbContract.HOST,
	                DbContract.DB_NAME,
	                DbContract.USERNAME,
	                DbContract.PASSWORD);
 */   
     //DBPool.
    static String TFP13sign(String message)
        {
        String sha25body = DigestUtils.sha256Hex(message);			
        return sha25body; //this is last thing!
        } 
    static String MD5sign(String message)
        {
        String MD5body = DigestUtils.md5Hex(message);			
        return MD5body; //this is last thing!
        } 
    



public void  verbose (String msg){
    System.out.println(Util.getDate(0, "YYYY/MM/dd HH:mm:ss.SSS")+ " - "+msg);
}
    
    
    static String getrandom(){
        Random rand = new Random();
        long randomNum ;
        randomNum = rand.nextInt(999999);
        //long l = Long.parseLong("500888000000");
        return String.valueOf(randomNum);
    }
    
    static String getHttpFormatDate(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        //System.out.println(dateFormat.format(calendar.getTime()));
        String value=dateFormat.format(calendar.getTime());
        return value;
    }
    
    static String getDate(int addMinutes, String format){
        Calendar todaysDate = Calendar.getInstance();    
        todaysDate.add(Calendar.MINUTE, addMinutes);
        DateFormat tgl = new SimpleDateFormat(format);
        String value=tgl.format(todaysDate.getTime());
        return value;
    }
    static String getGMT(int addMinutes, String format){
        Calendar GMT = Calendar.getInstance();    
        GMT.add(Calendar.MINUTE, addMinutes);
        SimpleDateFormat dateFormatGmt = new SimpleDateFormat(format);
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormatGmt.format(GMT.getTime());
    }
    
    static boolean isBit2(String bit2) throws InstantiationException, IllegalAccessException {
        try {
            if (dbUsed.equals("MySQL")) con =MySQLHelper.getConnection();
            else con =PostgresHelper.getConnection();
            
            stmt = con.prepareStatement("select count(*) as reccnt from t_enrollment where cardnumber ='"+bit2+"'");
            data = stmt.executeQuery();
            if (data.next()) {
                if(data.getInt(1)<1){
                    return false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try{
                data.close();
                stmt.close();
                con.close();
            }catch (SQLException ex) {
                Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        return true;

    }
    
    static String[] getBill(String BillID) throws InstantiationException, IllegalAccessException {
    String[] result = new String[9];
        try {
            if (dbUsed.equals("MySQL")) con =MySQLHelper.getConnection();
            else con =PostgresHelper.getConnection();
            
            stmt = con.prepareStatement("select cust_name, amount,  min_amount,max_amount,status, pay_type,accumulative_amount,paid_status, balance from TBill  where vaid='"+BillID+"'");
            data = stmt.executeQuery();
            if (data.next()) {
                result[0] = data.getString(1);//cust_name
                result[1] = data.getString(2);//amount
                result[2] = data.getString(3);//min_amount
                result[3] = data.getString(4);//max_amount
                result[4] = data.getString(5);//status (Bill Status)
                result[5] = data.getString(6);//pay_type
                result[6] = data.getString(7);//accumulative_amount
                result[7] = data.getString(8);//paidstatus
                result[8] = data.getString(9);//Balance
             }
            
        } catch (SQLException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try{
                data.close();
                stmt.close();
                con.close();
            }catch (SQLException ex) {
                Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        return result;

    }
    static String getpass(String username) throws InstantiationException, IllegalAccessException {
    String result = null;
        
        try {
            if (dbUsed.equals("MySQL")) con =MySQLHelper.getConnection();
            else con =PostgresHelper.getConnection();
            
            stmt = con.prepareStatement("select password from Tpartner  where username='"+username+"'");
            data = stmt.executeQuery();
            if (data.next()) {
                result = data.getString(1);
             }
            
        } catch (SQLException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try{
                data.close();
                stmt.close();
                con.close();
            }catch (SQLException ex) {
                Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        return result;

    }


    static boolean isBit3(String bit3) throws InstantiationException, IllegalAccessException {
        try {
            if (dbUsed.equals("MySQL")) con =MySQLHelper.getConnection();
            else con =PostgresHelper.getConnection();
            
            stmt = con.prepareStatement("select count(*) as reccnt from t_proc_code where processing_code="+bit3+"'");
            data = stmt.executeQuery();
            if (data.next()) {
                if(data.getInt(1)<1){
                    return false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try{
                data.close();
                stmt.close();
                con.close();
            }catch (SQLException ex) {
                Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        return true;
    }

static String rightPaddedSpace(String data, int len)
{
    
    for ( int i=data.length();i<=len;i++)
    {
        data+=" ";
    }
    return data;
}

static String leftPaddedZero(String data, int len)
{
    
    for ( int i=data.length();i<len;i++)
    {
        data="0"+data;
    }
    return data;
}

    
    static boolean isBit32(String bit32) throws InstantiationException, IllegalAccessException {
        try {
            if (dbUsed.equals("MySQL")) con =MySQLHelper.getConnection();
            else con =PostgresHelper.getConnection();
            
            stmt = con.prepareStatement("select count(*) as reccnt from t_acquirer where acquire_code='"+bit32+"'");
            data = stmt.executeQuery();
            if (data.next()) {
                if(data.getInt(1)<1){
                    return false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try{
                data.close();
                stmt.close();
                con.close();
            }catch (SQLException ex) {
                Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        return true;

    
    }
    
    static boolean isBit49(String bit49) throws InstantiationException, IllegalAccessException {
        try {
            if (dbUsed.equals("MySQL")) con =MySQLHelper.getConnection();
            else con =PostgresHelper.getConnection();
            
            stmt = con.prepareStatement("select count(*) as reccnt from t_currency where currency_code='"+bit49+"'");
            data = stmt.executeQuery();
            if (data.next()) {
                if(data.getInt(1)<1){
                    return false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try{
                data.close();
                stmt.close();
                con.close();
            }catch (SQLException ex) {
                Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        return true;

    }

static String verifyOTP(String cardno, String OTP) throws InstantiationException, IllegalAccessException {
    String result = "05";
        try {
            if (dbUsed.equals("MySQL")) con =MySQLHelper.getConnection();
            else con =PostgresHelper.getConnection();
            
            stmt = con.prepareStatement("select count(*) as reccnt from transaksi where cardnumber='"+cardno+"' and otp ='"+OTP+"'");
            data = stmt.executeQuery();
            if (data.next()) {
                if(data.getInt(1)<1){
                    result = "55";
                } else{
                    stmt = con.prepareStatement("select count(*) as reccnt from transaksi where cardnumber='"+cardno+"' and otp ='"+OTP+"' and otpused='0'");
                    data = stmt.executeQuery();
                    if (data.next()) {
                        if(data.getInt(1)<1){
                            result = "59";
                        } else result = "00";
            }
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try{
                data.close();
                stmt.close();
                con.close();
            }catch (SQLException ex) {
                Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        return result;

    }

static String getRCDesc(String RC) throws InstantiationException, IllegalAccessException {
    String result = "";
        
        try {
            if (dbUsed.equals("MySQL")) con =MySQLHelper.getConnection();
            else con =PostgresHelper.getConnection();
            
            stmt = con.prepareStatement("select \"desc\"  from t_rcmapping  where \"RC\"='"+RC+"'");
            data = stmt.executeQuery();
            if (data.next()) {
                result = data.getString(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try{
                data.close();
                stmt.close();
                con.close();
            }catch (SQLException ex) {
                Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        return result;

    }

static boolean setOTPUsed(String cardno, String OTP) throws InstantiationException, IllegalAccessException {
    boolean result=false;
        
        try {
            if (dbUsed.equals("MySQL")) con =MySQLHelper.getConnection();
            else con =PostgresHelper.getConnection();
            
            stmt = con.prepareStatement("UPDATE transaksi SET  otpused='1' WHERE  cardnumber='"+cardno+"' and otp ='"+OTP+"' and otpused='0'");
            result = stmt.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try{
                data.close();
                stmt.close();
                con.close();
            }catch (SQLException ex) {
                Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        return result;

    }

static boolean setPaidStatus(String PaidStatus, String vaid) throws InstantiationException, IllegalAccessException {
    boolean result=false;
        try {
            if (dbUsed.equals("MySQL")) con =MySQLHelper.getConnection();
            else con =PostgresHelper.getConnection();
            
            stmt = con.prepareStatement("UPDATE `Tbill` SET  paid_status='"+PaidStatus+"' WHERE  vaid='"+vaid+"';");
            result = stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try{
                data.close();stmt.close();con.close();
            }catch (SQLException ex) {
                Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        return result;

    }

static boolean setAmount(String AccAmount, String Balance,String vaid) throws InstantiationException, IllegalAccessException {
    boolean result=false;
        try {
            if (dbUsed.equals("MySQL")) con =MySQLHelper.getConnection();
            else con =PostgresHelper.getConnection();
            
            stmt = con.prepareStatement("UPDATE Tbill SET  accumulative_amount='"+AccAmount+"', balance='"+Balance+"' WHERE  vaid='"+vaid+"';");
            result = stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try{
                data.close();stmt.close();con.close();
            }catch (SQLException ex) {
                Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        return result;
    }


static boolean setAccAmount(String AccAmount, String vaid) throws InstantiationException, IllegalAccessException {
    boolean result=false;
        try {
            if (dbUsed.equals("MySQL")) con =MySQLHelper.getConnection();
            else con =PostgresHelper.getConnection();
            
            stmt = con.prepareStatement("UPDATE Tbill SET  accumulative_amount='"+AccAmount+"' WHERE  vaid='"+vaid+"';");
            result = stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try{
                data.close();stmt.close();con.close();
            }catch (SQLException ex) {
                Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        return result;
    }


static boolean executeSQL(String SQLStmt) throws InstantiationException, IllegalAccessException {
    boolean result=false;
        
        try {
            if (dbUsed.equals("MySQL")) con =MySQLHelper.getConnection();
            else con =PostgresHelper.getConnection();
            
            stmt = con.prepareStatement(SQLStmt);
            result = stmt.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try{
                data.close();
                stmt.close();
                con.close();
            }catch (SQLException ex) {
                Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        return result;

    }


    static boolean inserttrx(String cardnumber, String OTP, String TrxID, String otpExpiryDateTime) throws InstantiationException, IllegalAccessException {
        boolean result= true;
        
        try {
            if (dbUsed.equals("MySQL")) con =MySQLHelper.getConnection();
            else con =PostgresHelper.getConnection();
            
            stmt = con.prepareStatement("INSERT INTO transaksi(cardnumber,  otp, trxid, otpexpirydatetime) "+
                    "VALUES ('"+cardnumber+"','"+ OTP+"','" +TrxID+"','"+otpExpiryDateTime+"');");
            result = stmt.execute();//.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            result=false;
        } finally {
            try{
                data.close();
                stmt.close();
                con.close();
            }catch (SQLException ex) {
                Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        return result;
   }
    
    static boolean LogtoDB_GrabInqReq(String SysReffId, String countryCode, String collectingAgentCode, String deliveryChannelCode, String switchingReferenceCode) throws InstantiationException, IllegalAccessException {
        boolean result= true;
        
        try {
            if (dbUsed.equals("MySQL")) con =MySQLHelper.getConnection();
            else con =PostgresHelper.getConnection();
            
            stmt = con.prepareStatement("INSERT INTO `grab_tfp`.`TGrabInqReq`\n"+
                    "(`SysReffId`,`countryCode`,`collectingAgentCode`,\n" +
                    "`deliveryChannelCode`,`switchingReferenceCode`)\n" +
                    "VALUES ('"+SysReffId+"','"+countryCode+"','" +collectingAgentCode+
                    "','"+deliveryChannelCode+"','"+switchingReferenceCode+"');");
            result = stmt.execute();//.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            result=false;
        } finally {
            try{
                data.close();
                stmt.close();
                con.close();
            }catch (SQLException ex) {
                Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        return result;
   }
    
    static boolean LogtoDB_GrabInqResp(String SysReffId,String PhoneNumber, String customerName, String grabpayReferenceCode, String currencyCode, String minTopup, String maxTopup) throws InstantiationException, IllegalAccessException {
        boolean result= true;
        
        try {
            if (dbUsed.equals("MySQL")) con =MySQLHelper.getConnection();
            else con =PostgresHelper.getConnection();
            
            stmt = con.prepareStatement("INSERT INTO `grab_tfp`.TGrabInqResp\n" +
                    "(`SysReffId`,`PhoneNumber`,`customerName`,`grabpayReferenceCode`,\n" +
                    "`currencyCode`,`minTopup`,`maxTopup`)\n" +
                    "VALUES ('"+SysReffId+"','"+PhoneNumber+"','"+ customerName+"','" +grabpayReferenceCode+
                    "','"+currencyCode+"','"+minTopup+"','"+maxTopup+"');");
            result = stmt.execute();//.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            result=false;
        } finally {
            try{
                data.close();
                stmt.close();
                con.close();
            }catch (SQLException ex) {
                Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        return result;
   }
   
    static boolean LogtoDB_GrabTopUpReq(String SysReffId,String grabpayReferenceCode, String currencyCode, String amount, String approverReferenceCode, String rewardPoints) throws InstantiationException, IllegalAccessException {
        boolean result= true;        
        try {
            if (dbUsed.equals("MySQL")) con =MySQLHelper.getConnection();
            else con =PostgresHelper.getConnection();
            
            stmt = con.prepareStatement("INSERT INTO `grab_tfp`.TGrabTopUpReq\n" +
                    "(`SysReffId`,`grabpayReferenceCode`,`currencyCode`,`amount`,\n" +
                    "`approverReferenceCode`,`rewardPoints`)\n" +
                    "VALUES ('"+SysReffId+"','"+grabpayReferenceCode+"','"+ currencyCode+"','" +amount+
                    "','"+approverReferenceCode+"','"+rewardPoints+"');");
            result = stmt.execute();//.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            result=false;
        } finally {
            try{
                data.close();
                stmt.close();
                con.close();
            }catch (SQLException ex) {
                Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        return result;
   }
    

   
    
    static boolean LogtoDB_GrabTopUpResp(String SysReffId,String grabpayReferenceCode,String state) throws InstantiationException, IllegalAccessException {
        boolean result= true;        
        try {
            if (dbUsed.equals("MySQL")) con =MySQLHelper.getConnection();
            else con =PostgresHelper.getConnection();
            
            stmt = con.prepareStatement("INSERT INTO `grab_tfp`.TGrabTopUpResp\n" +
                    "(`SysReffId`,`grabpayReferenceCode`,`state`)\n" +
                    "VALUES ('"+SysReffId+"','"+grabpayReferenceCode+"','"+state+"');");
            result = stmt.execute();//.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            result=false;
        } finally {
            try{
                data.close();
                stmt.close();
                con.close();
            }catch (SQLException ex) {
                Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        return result;
   }
    
    static boolean insertTrxKeyData(String InqSysReffId,String grabpayReferenceCode, String TFP_vaid, String TFP_BookingID, String TFP_customer_name) throws InstantiationException, IllegalAccessException {
        boolean result= true;        
        try {
            if (dbUsed.equals("MySQL")) con =MySQLHelper.getConnection();
            else con =PostgresHelper.getConnection();
            stmt = con.prepareStatement("INSERT INTO `grab_tfp`.TKeyTransaksi\n" +
                    "(`InqSysReffId`,`grabpayReferenceCode`,`TFP_vaid`,`TFP_BookingID`,\n" +
                    "`TFP_customer_name`)\n" +
                    "VALUES ('"+InqSysReffId+"','"+grabpayReferenceCode+"','"+ TFP_vaid+"','" +TFP_BookingID+
                    "','"+TFP_customer_name+"');");
            stmt.execute();//.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            result=false;
        } finally {
            try{
                data.close();
                stmt.close();
                con.close();
            }catch (SQLException ex) {
                Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        return result;
   }
    
    static String GetgrabpayReferenceCode (String TFP_vaid, String TFP_BookingID,  String TFP_customer_name) throws InstantiationException, IllegalAccessException {
        String grabpayReffCode = "-1";
        try {
            if (dbUsed.equals("MySQL")) con =MySQLHelper.getConnection();
            else con =PostgresHelper.getConnection();
            
            stmt = con.prepareStatement("select grabpayReferenceCode from TKeyTransaksi where "+
                    "TFP_vaid='"+TFP_vaid+"' and "+
                    "TFP_BookingID='"+TFP_BookingID+"' and "+
                //    "TFP_productid='"+TFP_productid+"' and "+
                    "TFP_customer_name='"+TFP_customer_name+"';");
            data = stmt.executeQuery();
            
            if (data.next()) {
                grabpayReffCode = data.getString("grabpayReferenceCode");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try{
                data.close();
                stmt.close();
                con.close();
            }catch (SQLException ex) {
                Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        return grabpayReffCode;
    }
    
    
static String GetCardData (String cardno,String expmonth,String expyear) throws InstantiationException, IllegalAccessException ,ParseException {
        String RC = "05";
        try {
            if (dbUsed.equals("MySQL")) con =MySQLHelper.getConnection();
            else con =PostgresHelper.getConnection();
            
           // stmt = con.prepareStatement("select msisdn, expmonth, expyear,cardstatus  from customer where cardnumber='"+cardno+"'");
            //data = stmt.executeQuery();
            
            String query = "select msisdn, expmonth, expyear,cardstatus  from customer where cardnumber='"+cardno+"'";
            Statement st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
            ResultSet data = st.executeQuery(query);
            //int rowCount = data.last() ? data.getRow() : 0; 
            
            int rowCount = 0;
            data.last();
            rowCount = data.getRow();
           
            
            if (rowCount>0){
                data.beforeFirst();
                if (data.next()) {
                    //CEK EXPIRED
                    String cardexpyear =data.getString("expyear");
                    String cardexpmonth =data.getString("expmonth");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date cardexpdate = sdf.parse(cardexpyear+"-"+cardexpmonth+"-31");
                    if (expyear == null){
                        expyear="2000";
                    }
                    if (expmonth == null){
                        expmonth="01";
                    }
                    Date inputedexpdate = sdf.parse(expyear+"-"+expmonth+"-1");
                    Date date = new Date();
                    if (cardexpdate.compareTo(inputedexpdate) < 0) {
                        RC = "Q1";
                    } else if (cardexpdate.compareTo(date) < 0) {
                        RC = "54";
                    } else if (data.getString("cardstatus").equals("Blocked")) {
                        RC = "62";
                    } else if ((data.getString("cardstatus").equals("Fraud"))) {
                        RC = "59";
                    } else if ((data.getString("cardstatus").equals("Closed"))) {
                        RC = "78";
                    } else if ((data.getString("cardstatus").equals("Active"))) {
                        RC = "00";
                    }
                    
              /*  CardData[0] = data.getString("msisdn");
                CardData[1] = data.getString("expmonth");
                CardData[2] = data.getString("expyear");
                CardData[3] = data.getString("cardstatus");
                */
                    /*if(data.getInt(1)<1){
                    return "";
                }
                */
            }
            }
                data.close();
                st.close();
                con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }// finally {
          //  try{
                //data.close();
             //   stmt.close();
             //   con.close();
           // }catch (SQLException ex) {
           //     System.out.println("Exception : "+ex);
           //     Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
           //     }
           // }
        return RC;
    }
        
    
    static String GetMSISDN (String cardno) throws InstantiationException, IllegalAccessException {
        String msisdn = "";
        try {
            if (dbUsed.equals("MySQL")) con =MySQLHelper.getConnection();
            else con =PostgresHelper.getConnection();
            
            stmt = con.prepareStatement("select msisdn from customer where cardnumber='"+cardno+"'");
            data = stmt.executeQuery();
            
            if (data.next()) {
                msisdn = data.getString("msisdn");
                /*if(data.getInt(1)<1){
                    return "";
                }
                */
            }
        } catch (SQLException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try{
                data.close();
                stmt.close();
                con.close();
            }catch (SQLException ex) {
                Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        return msisdn;
    }
    
    static boolean isCommerce(String bit2, String bit4, String bit7, String bit22,String bit32, String bit48) {
        try {
            if(bit22 == "081") {
                
                String BCV =null;
                String EcommData = bit48.substring(8, 101);
                String Remainbit48 = bit48;
                String AuthReffID = null;
                String VerificationID =null;
                String Resultcode = null;
                String tag = bit48.substring(0,2);
                int taglen = Integer.getInteger(bit48.substring(3,5));
                if (tag=="30"){
                    BCV=Remainbit48.substring(5, taglen+6);
                    System.out.println(BCV);
                }
                
                try {
                    con =PostgresHelper.getConnection();
                    stmt = con.prepareStatement("select * from t_submission_ias where cardnumber='"+bit2+
                        "' and amount='"+bit4+"' and acquire_id='"+bit32+"' and validitydatetimeexpiregmt >'"+bit7+"'"+
                        " and iasauthenticationrequestreferenceid ='" +AuthReffID+"' and verificationid ='"+VerificationID+
                        "' and authenticationresponsecode ='" +Resultcode+"'");
                    data = stmt.executeQuery();
                    if (data.next()) {
                        if(data.getInt(1)<1){
                            return false;
                        } 
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try{
                        data.close();
                        stmt.close();
                        con.close();
                    }catch (SQLException ex) {
                        Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
            
            
            }
        } catch (Exception ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    static boolean isBlockedMCC(String bit18) throws InstantiationException, IllegalAccessException {
        try {
            if (dbUsed.equals("MySQL")) con =MySQLHelper.getConnection();
            else con =PostgresHelper.getConnection();
            
            stmt = con.prepareStatement("select count(*) as reccnt from t_mcc where mcc_is_block = true and mcc'="+bit18+"'");
            data = stmt.executeQuery();
            if (data.next()) {
                if(data.getInt(1)<1){
                    return false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try{
                data.close();
                stmt.close();
                con.close();
            }catch (SQLException ex) {
                Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        return true;
    }

    static boolean isPrefix(String prefix) throws InstantiationException, IllegalAccessException {
        try {
            if (dbUsed.equals("MySQL")) con =MySQLHelper.getConnection();
            else con =PostgresHelper.getConnection();
            
            stmt = con.prepareStatement("select count(*) as reccnt from t_prefix where card_prefix_code like  '"+prefix+"%'");
            data = stmt.executeQuery();
            if (data.next()) {
                if(data.getInt(1)<1){
                    return false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try{
                data.close();
                stmt.close();
                con.close();
            }catch (SQLException ex) {
                Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        return true;
        
    }

    static String CommerceCheck(String bit2, String bit4, String bit7, String bit22,String bit32, String bit48,String MTI) throws InstantiationException, IllegalAccessException {
        try {
                String BCV =null;
                String BCVResult =null;
                String EcommData =null;
                String Remainbit48 = bit48;
                String AuthReffID = null;
                String VerificationID =null;
                String Resultcode = null;
                while (Remainbit48.length()>3) {
                    int tag = Integer.parseInt(Remainbit48.substring(0,2));
                    // perlu di check available tag di array
                    int taglen = Integer.parseInt(Remainbit48.substring(2,4));
                    if (tag==30){
                        BCV=Remainbit48.substring(4, taglen+4);
                        Remainbit48=Remainbit48.substring(taglen+4,Remainbit48.length());
                        System.out.println(BCV);
                        System.out.println(Remainbit48);
                    } else   
                    if (tag==31){
                        EcommData=Remainbit48.substring(4, taglen+4);
                        Remainbit48=Remainbit48.substring(taglen+4,Remainbit48.length());
                        AuthReffID=EcommData.substring(0,40);
                        VerificationID=EcommData.substring(40,80);
                        Resultcode=EcommData.substring(80,82);
                        System.out.println(EcommData);
                        System.out.println(Remainbit48);
                        System.out.println(AuthReffID);
                        System.out.println(VerificationID);
                        System.out.println(Resultcode);
                    }
                    else   
                    if (tag==40){
                        if (MTI=="0210"){
                            BCVResult=Remainbit48.substring(4, taglen+4);
                            Remainbit48=Remainbit48.substring(taglen+4,Remainbit48.length());
                            System.out.println(BCVResult);
                            System.out.println(Remainbit48);
                        }else {
                            Remainbit48=Remainbit48.substring(taglen+4,Remainbit48.length());
                        } 
                        
                    }
                }
                
                
               
                try {
                    if (dbUsed.equals("MySQL")) con =MySQLHelper.getConnection();
                    else con =PostgresHelper.getConnection();
            
                    stmt = con.prepareStatement("select * from t_submission_ias where cardnumber='"+bit2+
                        "' and amount='"+bit4+"' and acquire_id='"+bit32+"' and validitydatetimeexpiregmt >'"+bit7+"'"+
                        " and iasauthenticationrequestreferenceid ='" +AuthReffID+"' and verificationid ='"+VerificationID+
                        "' and authenticationresponsecode ='" +Resultcode+"'");
                    data = stmt.executeQuery();
                    if (data.next()) {
                        if(data.getInt(1)<1){
                            return "";
                        } 
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try{
                        data.close();
                        stmt.close();
                        con.close();
                    }catch (SQLException ex) {
                        Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
            
            
            
        } catch (Exception ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "00";
    }
    
    
    
    static String insertstr(String table, Map<String,Object> values) throws SQLException {
		
		StringBuilder columns = new StringBuilder();
		StringBuilder vals = new StringBuilder();
		int i = 0;
		for (String col : values.keySet()) {
			columns.append(col).append(",");
			i++;
                       
                        
			if (values.get(col) instanceof String) {
                            if (values.size()!= i) {
                            	vals.append("'").append(values.get(col)).append("', ");
                            } else {
                                vals.append("'").append(values.get(col)).append("' ");; 
                            }
			}
			else 
                            if (values.size()!= i) {
                                vals.append(values.get(col)).append(",");
                    //            System.out.println(Integer.toString(i) +" "+  vals.toString()); 
                            } else {
                                vals.append(values.get(col)).append(" ");
                   //              System.out.println(Integer.toString(i) +" "+  vals.toString()); 
                            }
                                    
		}
		
		columns.setLength(columns.length()-1);
		vals.setLength(vals.length()-1);

		String query = String.format("INSERT INTO %s (%s) VALUES (%s)", table,
				columns.toString(), vals.toString());
		
		return query;
	}
    
static byte[] MsgPrep(byte[] messageBody ) {
    // ADD HEADER LENGTH TO SEND
  //  byte[] headerlen = new byte[2];
    //headerlen = String.valueOf(messageBody.length).getBytes();
    
    byte[] headerlen = ByteBuffer.allocate(2).putShort((short)messageBody.length).array();
    ByteBuffer bb = ByteBuffer.allocate(headerlen.length + messageBody.length );
    bb.put(headerlen);
    bb.put(messageBody);
    byte[] result = bb.array();
    return result;
}
static String processISO(ISOMsg iso ) {
    MyLogger log = new MyLogger(); 
    try{
        
	/*
        inisialisasi packager ISO BAse24
        */
    /*    GenericPackager packager = null;
        try {
            packager = new GenericPackager("packager/base24.xml");
        } catch (ISOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        //Setting packager
        ISOMsg iso = new ISOMsg();
        iso.setPackager(packager);
    */ 
        try {
            log.LogToDB(iso);
            if (iso.getMTI().equals("0200")) {
                if(!isPrefix(iso.getString(35).substring(0, 6))) {
                    return "15";
                }else if(!isBit3(iso.getString(3))) {
                    return "12";
                }else if(!isBit32(iso.getString(32))) {
                    return "31";
                }else if(!isBlockedMCC(iso.getString(18))) {
                    return "58";
                }else if(!isBit49(iso.getString(49))) {
                    return "58";
                }
    //    static boolean isCommerce(String bit2, String bit4, String bit7, String bit22,String bit32, String bit48) {
                String bit22 = iso.getString(22).substring(0, 2);
                if (bit22 != "81"){
                    String commerceResult ;
                    commerceResult = Util.CommerceCheck(iso.getString(2), iso.getString(4), iso.getString(7), iso.getString(22), iso.getString(32), iso.getString(48),iso.getMTI());
                    return commerceResult;
                }
                
            }

            try {
                TripleDes td = new TripleDes();
//                            log.trxlog("EncPAN : "+td.encrypt(bit2)+"\n");
            } catch (Exception e) {
               // TODO Auto-generated catch block
                  e.printStackTrace();
            }
            
        } catch (Exception ex) {
            log.trxlog("ISOException : "+ex+"\n");
           // Logger.getLGeneogger(CommandHandler.class.getName()).log(Level.INFO, null, ex);
        }	
	}catch (Exception ex) {
            log.trxlog("ProcessISOGeneralException : "+ex+"\n");
           
        }	
    return "00";
}  

static String ProcessISO(byte[] isomsg ) {
    MyLogger log = new MyLogger(); 
    try{
        
//       inisialisasi packager ISO BAse24
  
        GenericPackager packager = null;
        try {
            packager = new GenericPackager("packager/base24.xml");
        } catch (ISOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        //Setting packager
        ISOMsg iso = new ISOMsg();
        iso.setPackager(packager);
        
        try {
            iso.unpack(isomsg);
            log.LogToDB(iso);
            
            if(!isPrefix(iso.getString(35).substring(0, 6))) {
                return "15";
            }else if(!isBit3(iso.getString(3))) {
                return "12";
            }else if(!isBit32(iso.getString(32))) {
                return "31";
            }else if(!isBlockedMCC(iso.getString(18))) {
                return "58";
            }else if(!isBit49(iso.getString(49))) {
                return "12";
            }
            
//                        String bit2 = iso.getString(35);
//                        String bit11 = iso.getString(11);
//                        String bit18 = iso.getString(18);
//                        log.trxlog("PAN : "+bit2+bit11+bit18+"\n");
            try {
                TripleDes td = new TripleDes();
//                            log.trxlog("EncPAN : "+td.encrypt(bit2)+"\n");
            } catch (Exception e) {
               // TODO Auto-generated catch block
                  e.printStackTrace();
            }
            
        } catch (Exception ex) {
            log.trxlog("ISOException : "+ex+"\n");
           // Logger.getLGeneogger(CommandHandler.class.getName()).log(Level.INFO, null, ex);
        }	
	}catch (Exception ex) {
            log.trxlog("ProcessISOGeneralException : "+ex+"\n");
           
        }	
    return "00";
}    
    

/*    void LogToDB(ISOMsg iso) {
        
//        iso.setPackager(packager);
        try {
            try {
                String SQLStmt;
                TripleDes td = new TripleDes();
                //log.trxlog("EncPAN : "+td.encrypt(bit2)+"\n");
                SQLStmt = "INSERT INTO public.t_log_transaction("+
                " MTI, pan, proc_code, amount, trans_date, "+
                "stan, local_time, local_date, expiredate, settlement_date, capture_date, "+
                "mcc, point_of_service, acquire_code, forwarding_inst, rrn, terminal_id, "+
                "resp_code, Additional_Data, currency_code,"+ 
                "card_iss_resp_data, invoice_data, batch_settlement_data, original_data, "+
                " EncPAN,EncTrack)"+
                "VALUES ("+iso.getMTI()+","+ iso.getString(2)+","+  iso.getString(3)+","+  iso.getString(4)+","+  
                        iso.getString(12)+","+iso.getString(11)+","+  iso.getString(12)+","+  iso.getString(13)+","+  
                        iso.getString(14)+","+iso.getString(15)+","+  iso.getString(17)+","+ iso.getString(18)+","+  
                        iso.getString(22)+","+  iso.getString(32)+","+  iso.getString(33)+","+iso.getString(37)+","+  
                        iso.getString(41)+","+iso.getString(39)+","+  iso.getString(48)+","+  iso.getString(49)+","+  
                        iso.getString(61)+","+iso.getString(123)+","+  iso.getString(124)+","+  iso.getString(90)+","+  
                        td.encrypt(iso.getString(2))+","+  td.encrypt(iso.getString(35));
                con =PostgresHelper.getConnection();
                data = con.createStatement().executeQuery(SQLStmt);
            
            } catch (SQLException ex) {
               // TODO Auto-generated catch block
               Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ISOException ex) {
  //          log.trxlog("ISOException : "+ex+"\n");
           // Logger.getLogger(CommandHandler.class.getName()).log(Level.INFO, null, ex);
        }
    }
*/
  

static String signature(String header,String body, String secret)
{
header=	header+"##" + secret+ "##";
String sha256header = DigestUtils.sha256Hex(header);	
String str2 = "##" + body + "##" + sha256header + "##"; 
String sha25body = DigestUtils.sha256Hex(str2);			
System.out.println("signature : " + sha25body); 			
return sha25body; //this is last thing!
}    

static String EncHmacSHA256(String message,String date, String secret)
{
    String auth ="";
    String content_digest ="";
    try {
        String clientId = "f51fb676-13fe-4bac-8ede-88e27c061c42";
        String appSecret = "TGVBNii0TABdG0fp";
        String GrabAppID = "f51fb676-13fe-4bac-8ede-88e27c061c42";

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(message.getBytes());
        byte[] digest = md.digest(); // Missing charset
        //String hex = Hex.encodeHexString(digest);
        //content_digest = Base64.encodeBase64String(digest); 
        content_digest = Base64.getEncoder().encodeToString(digest); 
        System.out.println("base64 content_digest :" +content_digest);
        System.out.println("Date : " + date);
        String strVal = "POST\n"+"application/json\n"+date+"\n"+"/v1/switchingpartner/inquiry/phone/87775277588"+"\n"+content_digest+"\n";
        System.out.println(strVal);
        auth =  encode(secret, strVal);
        auth = clientId+":"+auth;
        System.out.println("auth:"+auth);
        }
        catch (Exception e){
         System.out.println("Error");
        }
    return auth;
    
}


public static String encode(String key, String data) {
    try {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        return new String(Base64.getEncoder().encode(sha256_HMAC.doFinal(data.getBytes("UTF-8"))));
    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
}

static String GetDeliveryChannel (String DCCode) throws InstantiationException, IllegalAccessException {
        String DCName = "";
        try {
            if (dbUsed.equals("MySQL")) con =MySQLHelper.getConnection();
            else con =PostgresHelper.getConnection();
            
            stmt = con.prepareStatement("select DCName from DeliveryChannelTable where DCCode='"+DCCode+"'");
            data = stmt.executeQuery();
            
            if (data.next()) {
                DCName = data.getString("DCName");
                /*if(data.getInt(1)<1){
                    return "";
                }
                */
            }
        } catch (SQLException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try{
                data.close();
                stmt.close();
                con.close();
            }catch (SQLException ex) {
                Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        return DCName;
    }

static String grabSigning_old(String message, String secret, String url)
{
    String content_digest = EncHmacSHA256(message,secret,"");
    
    String stringToSign = "POST\n"+
            "application/json\n"+
            getHttpFormatDate()+"\n"+
            url+"\n"+
            content_digest+"\n";
   // System.out.println("stringToSign value is " + stringToSign);
    String hash = EncHmacSHA256(stringToSign,secret,"");
    return hash; 
}   
static String grabSigning(String message, String secret, String url)
{
    String content_digest = EncHmacSHA256(message,secret,"");
    
    String date = "Tue, 29 Aug 2017 12:14:54 GMT";//getHttpFormatDate();
      //   System.out.println("Date : " + date);
           
        String stringToSign = "POST\n"+"application/json\n"
                +date+"\n"
                +"/v1/switchingpartner/inquiry/phone/87775277588"
                +"\n"+content_digest+"\n";
      
/*    
    String stringToSign = "POST\n"+
            "application/json\n"+
            getHttpFormatDate()+"\n"+
            url+"\n"+
            content_digest+"\n";
*/
  //  System.out.println("stringToSign value is " + stringToSign);
    String hash = EncHmacSHA256(stringToSign,secret,"");
    return hash; 
}   


    public static String Grabsign(String date, String requestMessage , String vaid) throws NoSuchAlgorithmException  {
  
        String clientId = ApplicationProperties.getGrabAppID();
        String appSecret = ApplicationProperties.getGrabAppSecret();
        String appName = ApplicationProperties.getGrabAppName();
        String auth;
        String authorization;
         //String requestMessage = "{\"switchingReferenceCode\": \"12492839ab43\", \"collectingAgentCode\": \"doku_permata_lite_atm\", \"countryCode\": \"ID\", \"deliveryChannelCode\": \"cash\"}";
        // System.out.println("Request Message : " + requestMessage);

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(requestMessage.getBytes());
        byte[] digest = md.digest(); // Missing charset

        String content_digest = Base64.getEncoder().encodeToString(digest);
      //  System.out.println("base64 content_digest :" +content_digest);

        // String date = "Tue, 29 Aug 2017 12:14:54 GMT";//getHttpFormatDate();
         //date = getHttpFormatDate();
        // System.out.println("Date : " + date);
           
        String strVal = "POST\n"+"application/json\n"+date+"\n"+"/v1/switchingpartner/inquiry/phone/"+vaid+"\n"+content_digest+"\n";
       // System.out.println(strVal);
        auth =  HMACSHA256encode(appSecret, strVal);
        authorization = clientId+":"+auth;
        //System.out.println("auth:"+authorization);
        return authorization;

    }
    
      public static String GrabsignTopUp(String date, String requestMessage , String grabRef) throws NoSuchAlgorithmException  {
  
        String clientId = ApplicationProperties.getGrabAppID();
        String appSecret = ApplicationProperties.getGrabAppSecret();
        String appName = ApplicationProperties.getGrabAppName();
        String auth;
        String authorization;
         //String requestMessage = "{\"switchingReferenceCode\": \"12492839ab43\", \"collectingAgentCode\": \"doku_permata_lite_atm\", \"countryCode\": \"ID\", \"deliveryChannelCode\": \"cash\"}";
        // System.out.println("Request Message : " + requestMessage);


        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(requestMessage.getBytes());
        byte[] digest = md.digest(); // Missing charset

        String content_digest = Base64.getEncoder().encodeToString(digest);
       // System.out.println("base64 content_digest :" +content_digest);

        // String date = "Tue, 29 Aug 2017 12:14:54 GMT";//getHttpFormatDate();
         //date = getHttpFormatDate();
       //  System.out.println("Date : " + date);
           
        String strVal = "POST\n"+"application/json\n"+date+"\n"+"/v1/switchingpartner/topup/"+grabRef+"\n"+content_digest+"\n";
      //  System.out.println(strVal);
        auth =  HMACSHA256encode(appSecret, strVal);
        authorization = clientId+":"+auth;
        //System.out.println("auth:"+authorization);
        return authorization;
        
    }
    
     public static String HMACSHA256encode(String key, String data) {
    try {

        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);

        return new String(Base64.getEncoder().encode(sha256_HMAC.doFinal(data.getBytes("UTF-8"))));

    } catch (Exception e) {
        e.printStackTrace();
    } 

    return null;
}
     
     /*
     public  String generateSignatureRemmitance() throws NoSuchAlgorithmException, SignatureException{
     String message = null;
     Signature sign = Signature.getInstance("MD5withRSA");
     sign.initSign("public.crt");
     byte[] byteMessage = message.getBytes();
     sign.update(byteMessage, 0, byteMessage.length);

        byte[] result = sign.sign();
     }

*/
     
    public String createStan(int stan) {
        String formatted = String.format("%06d", stan);
        return formatted;
    }

}


