package GTAgw;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;


class MyLogger {
    private static Connection con;
    private static ResultSet data;
    PostgresHelper  db ;   

    void trxlog(String msg) {
	    
	    Logger logger = Logger.getLogger("MyLog");
	    FileHandler fh =null;
	    try {
	         
	        // This block configure the logger with handler and formatter %h/log%g.out
	        fh = new FileHandler("trxlog.%g.%u.txt",0,10,true);
	       
	        logger.addHandler(fh);
	        //logger.setLevel(Level.ALL);
	        SimpleFormatter formatter = new SimpleFormatter();
	        fh.setFormatter(formatter);
	        
	         
	        // the following statement is used to log any messages
	        logger.log(Level.INFO,msg);
	        fh.flush();
	        fh.close();
	         
	    } catch (SecurityException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	  }
	    
	  void fileout(byte[] rawbyte){
	    	String filename= "test.txt";
	    	BufferedOutputStream bos = null;
	    	try {
	    	//create an object of FileOutputStream
	    	FileOutputStream fos = null;
			try {
				fos = new  FileOutputStream(new File(filename),true);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	//create an object of BufferedOutputStream
	    	try{
	    	bos = new BufferedOutputStream(fos);
	    	byte[] encoded = rawbyte;
	    	bos.write(encoded); 
	    	}
	    	catch(IOException e) {}
	    	} 
	    	finally {
	    	    // ... cleanup that will execute whether or not an error occurred ...
	    	}
	    }
          
          void LogToFile(String msg) {
	    
	    Logger logger = Logger.getLogger("MyLog");
	    FileHandler fh =null;
	    try {
	         
	        // This block configure the logger with handler and formatter %h/log%g.out
	        fh = new FileHandler("trxlog.%g.%u.txt",0,10,true);
	       
	        logger.addHandler(fh);
	        //logger.setLevel(Level.ALL);
	        SimpleFormatter formatter = new SimpleFormatter();
	        fh.setFormatter(formatter);
	        
	         
	        // the following statement is used to log any messages
	        logger.log(Level.INFO,msg);
	        fh.flush();
	        fh.close();
	         
	    } catch (SecurityException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	  }
void sendtoLog(Level level,String msg, String mydata) {
	    
	    Logger logger = Logger.getLogger("MyLog");
	    FileHandler fh =null;
	    try {
	         
	        // This block configure the logger with handler and formatter %h/log%g.out
	        fh = new FileHandler("trxlog.%g.%u.txt",0,10,true);
	       
	        logger.addHandler(fh);
	        //logger.setLevel(Level.ALL);
	        SimpleFormatter formatter = new SimpleFormatter();
	        fh.setFormatter(formatter);
	        
	         
	        // the following statement is used to log any messages
	        logger.log(level,msg+ " - "+mydata);
	        fh.flush();
	        fh.close();
	         
	    } catch (SecurityException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	  }
    void LogToDB(ISOMsg iso) {
              
//        iso.setPackager(packager);
        
            try {
                Connection c = null;
            //    Statement stmt = null;
            //    c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testdb","manisha", "123");

                //log.trxlog("EncPAN : "+td.encrypt(bit2)+"\n");
               // if (db.Myconnect()) {
                    
               // }
                    

                
                
                TripleDes td = new TripleDes();
 /*
                String SQLStmt = "INSERT INTO public.t_log_transaction("+
                " MTI, pan, proc_code, amount, trans_date, "+
                "stan, local_time, local_date, expiredate, settlement_date, capture_date, "+
                "mcc, point_of_service, acquire_code, forwarding_inst, rrn, terminal_id, "+
                "resp_code, Additional_Data, currency_code,"+ 
                "card_iss_resp_data, invoice_data, batch_settlement_data, original_data, "+
                " EncPAN,EncTrack)"  +
                "VALUES ("+iso.getMTI()+","+ iso.getString(2)+","+  iso.getString(3)+","+  iso.getString(4)+","+  
                        iso.getString(12)+","+iso.getString(11)+","+  iso.getString(12)+","+  iso.getString(13)+","+  
                        iso.getString(14)+","+iso.getString(15)+","+  iso.getString(17)+","+ iso.getString(18)+","+  
                        iso.getString(22)+","+  iso.getString(32)+","+  iso.getString(33)+","+iso.getString(37)+","+  
                        iso.getString(41)+","+iso.getString(39)+","+  iso.getString(48)+","+  iso.getString(49)+",'"+  
                        iso.getString(61)+"',"+iso.getString(123)+","+  iso.getString(124)+","+  iso.getString(90)+",'"+  
                        td.encrypt(iso.getString(2))+"','"+ td.encrypt(iso.getString(35))+"')" ;
                */
                
                
                
                
                Statement stmt = null;
                
                try {
                    Class.forName("org.postgresql.Driver");
                    try {
                        con = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/DB_BIP","postgres", "fjuniadi");
                        con.setAutoCommit(false);
                        
                        Map<String,Object> vals = new HashMap<>();
                            vals.put("MTI", iso.getMTI());
                            vals.put("pan", iso.getString(2));
                            vals.put("proc_code", iso.getString(3));
                            vals.put("amount", iso.getString(4));
                            vals.put("trans_date",iso.getString(7) );
                            vals.put("stan",iso.getString(11) );
                            vals.put("local_time", iso.getString(12));
                            vals.put("local_date",iso.getString(13) );
                            vals.put("expiredate",iso.getString(14) );
                            vals.put("settlement_date",iso.getString(15) );
                            vals.put("capture_date", iso.getString(17));
                            vals.put("mcc", iso.getString(18));
                            vals.put("point_of_service", iso.getString(22));
                            vals.put("acquire_code", iso.getString(32));
                            vals.put("forwarding_inst", iso.getString(33));
                            vals.put("rrn", iso.getString(37));
                            vals.put("terminal_id", iso.getString(41));
                            vals.put("resp_code", iso.getString(39));
                            vals.put("Additional_Data", iso.getString(48));
                            vals.put("currency_code", iso.getString(49));
                            vals.put("card_iss_resp_data", iso.getString(61));
                            vals.put("invoice_data", iso.getString(123));
                            vals.put("batch_settlement_data", iso.getString(124));
                            vals.put("original_data", iso.getString(90));
                            vals.put("EncPAN", td.encrypt(iso.getString(2)));
                            vals.put("EncTrack", td.encrypt(iso.getString(35)));
                            
                        stmt = con.createStatement();
                        String SQLStmt =Util.insertstr("public.t_log_transaction", vals);
                        stmt.executeUpdate(Util.insertstr("public.t_log_transaction", vals));
                        
                        //con =PostgresHelper.getConnection();
                        //data = con.createStatement().executeQuery(SQLStmt);
                        //    Statement stmt = con.createStatement();
                        //stmt.executeUpdate(SQLStmt);
                        stmt.close();
                        con.commit();
                        con.close();
                    } catch (SQLException ex) {                
                        System.out.println("Failed to create the database connection."); 
                    }
                } catch (ClassNotFoundException ex) {
                    
                    System.out.println("Driver not found."); 
                }
                
                
                //Class.forName("org.postgresql.Driver");
                //c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DB_BIP","postgres", "fjuniadi");
              
            
        } catch (Exception ex) {
            trxlog("ISOException : "+ex+"\n");
           // Logger.getLogger(CommandHandler.class.getName()).log(Level.INFO, null, ex);
        }
    }	   
}

	

