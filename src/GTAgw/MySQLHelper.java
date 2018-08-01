/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GTAgw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author fjuniadi
 */
public class MySQLHelper {
   // private Connection conn;
    private static String host=ApplicationProperties.getHost();
        //private static String host="jdbc:mysql://localhost:3306";
	private static String user=ApplicationProperties.getUserDb();
       // private static String pass=ApplicationProperties.getPasswordDb();
//        private static String user="ebilluser";
	private static String pass=ApplicationProperties.getPasswordDb();
	//private static String pass="fjuniadi";
	
        private static Connection con;

	
	protected MySQLHelper() {}
        
        public static Connection getConnection() throws InstantiationException, IllegalAccessException {
        try {
            // Load the Connector/J driver
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // Establish connection to MySQL
            
           // Class.forName("org.postgresql.Driver");
            try {
                con = DriverManager.getConnection(host, user, pass);
                
                //con = DriverManager.getConnection(host, user, pass);
            } catch (SQLException ex) {                
                System.out.println("Failed to create the database connection."); 
            }
        } catch (ClassNotFoundException ex) {
            // log an exception. for example:
            System.out.println("Driver not found."); 
        }
        return con;
    }
        
//        public static void main(String[] args ) throws InstantiationException, IllegalAccessException{
//            System.out.println("Connection " + getConnection());
//        }
        
        
        
}
