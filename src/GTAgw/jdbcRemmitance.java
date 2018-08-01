/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GTAgw;

import static GTAgw.Util.dbUsed;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wanda.priatna
 */
public class jdbcRemmitance {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  //  static final String DB_URL = "jdbc:mysql://localhost:3306/appusers";
    static final String DB_URL = ApplicationProperties.getHost();

    //  Database credentials
   // static final String USER = "root";
    static final String USER = ApplicationProperties.getUserDb();
    static final String PASS = ApplicationProperties.getPasswordDb();

    private static Connection con;
    private static ResultSet data;
    private static PreparedStatement stmt;

    public static String selectStan(String id) throws SQLException {
        String stan1 ="";
        try {
            con = MySQLHelper.getConnection();
            stmt = con.prepareStatement("SELECT stan_generator FROM stan_gen WHERE id = " + id);
            data = stmt.executeQuery();
            while (data.next()) {
                String stan = data.getString("stan_generator");
                return stan;
            }
            data.close();
        } catch (InstantiationException ex) {
            Logger.getLogger(jdbcRemmitance.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(jdbcRemmitance.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stan1;
    }


    static void insertCreateRemmitance(String id,String stan, String trans_date_time, String inst_id,
            String account_id, String name, String address, String birth_date, String phone_number, String occupation,
            String citizenship, String id_number, String fund_resource, String regency_code, String signature
    ) throws InstantiationException, IllegalAccessException {
        try {
            con = MySQLHelper.getConnection();
            String sql = "INSERT INTO create_remmitance_account (id,stan, trans_date_time,inst_id,account_id,name,address,birth_date,phone_number,occupation,citizenship,"
                    + "id_number,fund_resource,regency_code,signature) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, id);
            statement.setString(2, stan);
            statement.setString(3, trans_date_time);
            statement.setString(4, inst_id);
            statement.setString(5, account_id);
            statement.setString(6, name);
            statement.setString(7, address);
            statement.setString(8, birth_date);
            statement.setString(9, phone_number);
            statement.setString(10, occupation);
            statement.setString(11, citizenship);
            statement.setString(12, id_number);
            statement.setString(13, fund_resource);
            statement.setString(14, regency_code);
            statement.setString(15, signature);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }

        } catch (SQLException ex) {
            //Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
           // result = false;
        }
        // return result;
    }
    
     static void insertCreateRemmitanceResp(String id,String trxstan, String trx_trans_date_time, String trx_inst_id,
            String sender_account_id, String sender_name, String sender_address, String sender_birth_date, String sender_phone_number, 
            String sender_occupation,String sender_citizenship, String sender_id_number, String sender_fund_resource, String benef_inst_id, String benefAccount_id,
            String benef_name,String benef_relationship,String benef_regency_code,String err_code,String err_description,String data
    ) throws InstantiationException, IllegalAccessException {
        try {
            con = MySQLHelper.getConnection();
            String sql = "INSERT INTO create_remmitance_account_resp(id,trxstan, trx_trans_date_time,trx_inst_id,"
                    + "sender_account_id,sender_name,sender_address,sender_birth_date,sender_phone_number,sender_occupation,"
                    + "sender_citizenship,sender_id_number,sender_fund_resource,benef_inst_id,benefAccount_id,"
                    + "benef_name,benef_relationship,benef_regency_code,err_code,err_description,data"
                    + ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, id);
            statement.setString(2, trxstan);
            statement.setString(3, trx_trans_date_time);
            statement.setString(4, trx_inst_id);
            statement.setString(5, sender_account_id);
            statement.setString(6, sender_name);
            statement.setString(7, sender_address);
            statement.setString(8, sender_birth_date);
            statement.setString(9, sender_phone_number);
            statement.setString(10, sender_occupation);
            statement.setString(11, sender_citizenship);
            statement.setString(12, sender_id_number);
            statement.setString(13, sender_fund_resource);
            statement.setString(14, benef_inst_id);
            statement.setString(15, benefAccount_id);
            statement.setString(16, benef_name);
            statement.setString(17, benef_relationship);
            statement.setString(18, benef_regency_code);
            statement.setString(19, err_code);
            statement.setString(20, err_description);
            statement.setString(21, data);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }

        } catch (SQLException ex) {
            //Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
           // result = false;
        }
        // return result;
    }
    
    
    static void insertCreateTransferInquiry(String id,String stan , String trans_date_time, String inst_id,
            String proc_code, String channel_type, String ref_number, String terminal_id, String country_code, String local_date_time,
            String sender_account_id, String sender_name, String curr_code, String sender_amount, String sender_rate,
            String area_code,String benef_inst_id,String benef_account_id,String benef_curr_code, String benef_amount,
            String benef_cust_ref_number,String benef_regency_code,String benef_purpose_code,String benef_purpose_desc,String data
    ) throws InstantiationException, IllegalAccessException {
        try {
            con = MySQLHelper.getConnection();
            String sql = "INSERT INTO create_transfer_inquiry (id,stan,trans_date_time,inst_id,	proc_code,channel_type,"
                    + "ref_number,terminal_id,country_code,local_date_time,sender_account_id,sender_name,curr_code,"
                    + "sender_amount,sender_rate,area_code,benef_inst_id,benef_account_id,benef_curr_code,benef_amount,"
                    + "benef_cust_ref_number,benef_regency_code,benef_purpose_code,benef_purpose_desc,data) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, id);
            statement.setString(2, stan);
            statement.setString(3, trans_date_time);
            statement.setString(4, inst_id);
            statement.setString(5, proc_code);
            statement.setString(6, channel_type);
            statement.setString(7, ref_number);
            statement.setString(8, terminal_id);
            statement.setString(9, country_code);
            statement.setString(10, local_date_time);
            statement.setString(11, sender_account_id);
            statement.setString(12, sender_name);
            statement.setString(13, curr_code);
            statement.setString(14, sender_amount);
            statement.setString(15, sender_rate);
            statement.setString(16, area_code);
            statement.setString(17, benef_inst_id);
            statement.setString(18, benef_account_id);
            statement.setString(19, benef_curr_code);
            statement.setString(20, benef_amount);
            statement.setString(21, benef_cust_ref_number);
            statement.setString(22, benef_regency_code);
            statement.setString(23, benef_purpose_code);
            statement.setString(24, benef_purpose_desc);
            statement.setString(25, data);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A Transfer inquiry inserted successfully!");
                
            }

        } catch (SQLException ex) {
            //Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
           // result = false;
        }
        // return result;
    }
    
     static void insertCreateTransferInquiryResp(String id,String stan , String trans_date_time, String inst_id,
            String sender_account_id, String sender_name, String sender_curr_code, String sender_amount, String sender_rate,
            String sender_area_code,String benef_inst_id,String benef_account_id,String benef_curr_code, String benef_amount,
            String benef_cust_ref_number,String benef_name,String benef_regency_code,String benef_purpose_code,String benef_purpose_desc,
            String code,String description,String data
    ) throws InstantiationException, IllegalAccessException {
        try {
            con = MySQLHelper.getConnection();
            String sql = "INSERT INTO create_transfer_inquiry_resp (id,stan,trans_date_time,inst_id,sender_account_id,sender_name,"
                    + "sender_curr_code,sender_amount,sender_rate,sender_area_code,benef_inst_id,benef_account_id,benef_curr_code,"
                    + "benef_amount,benef_cust_ref_number,benef_name,benef_regency_code,benef_purpose_code,benef_purpose_desc,"
                    + "code,description,data) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, id);
            statement.setString(2, stan);
            statement.setString(3, trans_date_time);
            statement.setString(4, inst_id);
            statement.setString(5, sender_account_id);
            statement.setString(6, sender_name);
            statement.setString(7, sender_curr_code);
            statement.setString(8, sender_amount);
            statement.setString(9, sender_rate);
            statement.setString(10, sender_area_code);
            statement.setString(11, benef_inst_id);
            statement.setString(12, benef_account_id);
            statement.setString(13, benef_curr_code);
            statement.setString(14, benef_amount);
            statement.setString(15, benef_cust_ref_number);
            statement.setString(16, benef_name);
            statement.setString(17, benef_regency_code);
            statement.setString(18, benef_purpose_code);
            statement.setString(19, benef_purpose_desc);
            statement.setString(20, code);
            statement.setString(21, description);
            statement.setString(22, data);
         

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A Transfer inquiry inserted successfully!");
                
            }

        } catch (SQLException ex) {
            //Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
           // result = false;
        }
        // return result;
    }
    
      static void createTransfer(String id,String stan , String trx_trans_date_time, String trx_inst_id,
            String trx_proc_code, String trx_channel_type, String trx_ref_number, String trx_terminal_id, String trx_country_code, String trx_local_date_time,
            String sender_account_id, String sender_name, String sender_curr_code, String sender_amount, String sender_rate,
            String area_code,String benef_inst_id,String benef_account_id,String benef_curr_code, String benef_amount,
            String benef_cust_ref_number,String benef_name,String benef_regency_code,String benef_purpose_code,String benef_purpose_description,
              String data
    ) throws InstantiationException, IllegalAccessException {
        try {
            con = MySQLHelper.getConnection();
            String sql = "INSERT INTO create_remmitance_transfer (id,stan,trx_trans_date_time,trx_inst_id,trx_proc_code,trx_channel_type,"
                    + "trx_ref_number,trx_terminal_id,trx_country_code,trx_local_date_time,sender_account_id,sender_name,sender_curr_code,"
                    + "sender_amount,sender_rate,sender_area_code,benef_inst_id,benef_account_id,benef_curr_code,benef_amount,"
                    + "benef_cust_ref_number,benef_name,benef_regency_code,benef_purpose_code,benef_purpose_description,data) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, id);
            statement.setString(2, stan);
            statement.setString(3, trx_trans_date_time);
            statement.setString(4, trx_inst_id);
            statement.setString(5, trx_proc_code);
            statement.setString(6, trx_channel_type);
            statement.setString(7, trx_ref_number);
            statement.setString(8, trx_terminal_id);
            statement.setString(9, trx_country_code);
            statement.setString(10, trx_local_date_time);
            statement.setString(11, sender_account_id);
            statement.setString(12, sender_name);
            statement.setString(13, sender_curr_code);
            statement.setString(14, sender_amount);
            statement.setString(15, sender_rate);
            statement.setString(16, area_code);
            statement.setString(17, benef_inst_id);
            statement.setString(18, benef_account_id);
            statement.setString(19, benef_curr_code);
            statement.setString(20, benef_amount);
            statement.setString(21, benef_cust_ref_number);
            statement.setString(22, benef_name);
            statement.setString(23, benef_regency_code);
            statement.setString(24, benef_purpose_code);
            statement.setString(25, benef_purpose_code);
            statement.setString(26, data);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A Transfer inserted successfully!");
                
            }

        } catch (SQLException ex) {
            //Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
           // result = false;
        }
        // return result;
    }
      
       static void createTransferResp(String id,String trx_stan , String trx_trans_date_time, String trx_inst_id,
            String sender_account_id, String sender_name, String sender_curr_code, String sender_amount, String sender_rate, String sender_area_code,
            String benef_inst_id, String benef_account_id, String benef_curr_code, String benef_amount, String 	benef_cust_ref_number,
            String benef_name,String benef_regency_code,String 	benef_purpose_code,String benef_purpose_desc, String code,
            String description,String Data) throws InstantiationException, IllegalAccessException {
        try {
            con = MySQLHelper.getConnection();
            String sql = "INSERT INTO create_remmitance_transfer_resp (id,trx_stan,trx_trans_date_time,trx_inst_id,sender_account_id,"
                    + "sender_name,sender_curr_code,sender_amount,sender_rate,sender_area_code,benef_inst_id,benef_account_id,"
                    + "benef_curr_code,benef_amount,benef_cust_ref_number,benef_name,benef_regency_code,benef_purpose_code,benef_purpose_desc,"
                    + "code,description,Data)"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, id);
            statement.setString(2, trx_stan);
            statement.setString(3, trx_trans_date_time);
            statement.setString(4, trx_inst_id);
            statement.setString(5, sender_account_id);
            statement.setString(6, sender_name);
            statement.setString(7, sender_curr_code);
            statement.setString(8, sender_amount);
            statement.setString(9, sender_rate);
            statement.setString(10, sender_area_code);
            statement.setString(11, benef_inst_id);
            statement.setString(12, benef_account_id);
            statement.setString(13, benef_curr_code);
            statement.setString(14, benef_amount);
            statement.setString(15, benef_cust_ref_number);
            statement.setString(16, benef_name);
            statement.setString(17, benef_regency_code);
            statement.setString(18, benef_purpose_code);
            statement.setString(19, benef_purpose_desc);
            statement.setString(20, code);
            statement.setString(21, description);
            statement.setString(22, Data);
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A Transfer inserted successfully!");
            }
        } catch (SQLException ex) {
            //Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
           // result = false;
        }
        // return result;
    }
    
    static void stanGen(String id
    ) throws InstantiationException, IllegalAccessException {
       // boolean result = true;
        try {
            con = MySQLHelper.getConnection();
            String sql = "INSERT INTO stan_gen (id) VALUES (?)";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, id);
    
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                //System.out.println("A new user was inserted successfully!");
              
            }

        } catch (SQLException ex) {
            //Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
           // result = false;
        }
        // return result;
    }
    
      static void inquiryStatus(String id,String trx_stan , String trx_trans_date_time, String 	trx_inst_id,
            String trx_country_code, String trx_local_date_time, String query_stan, String query_trans_date_time, String data 
    ) throws InstantiationException, IllegalAccessException {
        try {
            con = MySQLHelper.getConnection();
            String sql = "INSERT INTO  inquiry_status (id,trx_stan,trx_trans_date_time,trx_inst_id,trx_country_code,trx_local_date_time,"
                    + "query_stan,query_trans_date_time,data)"
                    + "VALUES (?,?,?,?,?,?,?,?,?)";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, id);
            statement.setString(2, trx_stan);
            statement.setString(3, trx_trans_date_time);
            statement.setString(4, trx_inst_id);
            statement.setString(5, trx_country_code);
            statement.setString(6, trx_local_date_time);
            statement.setString(7, query_stan);
            statement.setString(8, query_trans_date_time);
            statement.setString(9, data);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A inquiry status inserted successfully!");
            }
        } catch (SQLException ex) {
            //Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    
        static void inquiryStatusResp(String id,String trx_stan , String trx_trans_date_time, String trx_inst_id,
            String query_stan, String query_trans_date_time, String sender_trans_date_time, String sender_name, String 	sender_curr_code,
            String sender_amount,String sender_rate,String benef_inst_id,String benef_account_id,String benef_curr_code,
            String benef_amount,String benef_cust_ref_number,String benef_name,String benef_regency_code,String trx_code,
            String trx_description,String inq_code,String inq_description,String data
    ) throws InstantiationException, IllegalAccessException {
        try {
            con = MySQLHelper.getConnection();
            String sql = "INSERT INTO  inquiry_status_resp(id,trx_stan,trx_trans_date_time,trx_inst_id,query_stan,query_trans_date_time,"
                    + "sender_trans_date_time,sender_name,sender_curr_code,sender_amount,sender_rate,benef_inst_id,benef_account_id,"
                    + "benef_curr_code,benef_amount,benef_cust_ref_number,benef_name,benef_regency_code,trx_code,trx_description,"
                    + "inq_code,inq_description,data)"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, id);
            statement.setString(2, trx_stan);
            statement.setString(3, trx_trans_date_time);
            statement.setString(4, trx_inst_id);
            statement.setString(5, query_stan);
            statement.setString(6, query_trans_date_time);
            statement.setString(7, sender_trans_date_time);
            statement.setString(8, sender_name);
            statement.setString(9, sender_curr_code);
            statement.setString(10, sender_amount);
            statement.setString(11, sender_rate);
            statement.setString(12, benef_inst_id);
            statement.setString(13, benef_account_id);
            statement.setString(14, benef_curr_code);
            statement.setString(15, benef_amount);
            statement.setString(16, benef_cust_ref_number);
            statement.setString(17, benef_name);
            statement.setString(18, benef_regency_code);
            statement.setString(19, trx_code);
            statement.setString(20, trx_description);
            statement.setString(21, inq_code);
            statement.setString(22, inq_description);
            statement.setString(23, data);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A inquiry status inserted successfully!");
            }
        } catch (SQLException ex) {
            //Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }


}
