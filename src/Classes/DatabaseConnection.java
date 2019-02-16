package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    static Connection dbConnection;
    
    public static Connection invokeJDBC() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sms-ps", "root", "");
            System.out.println("CONNECTION ESTABLISH SUCCESSFULLY...");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("CONNECTION PROBLEM ! \n" + ex.toString());
        }
        return dbConnection;
    }
}
