package Classes;

import UIs.UserFrontPage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class UserModel {
    Connection dbConnection;
    PreparedStatement PS;
    UserFrontPage uFP = new UserFrontPage();
    
    public String Id, Name, Password, Role, SearchId;
    
    public javax.swing.JMenuItem jmi_FileCreateUser, jmi_ProfileStuAdd, jmi_ProfileTeaAdd, jmi_ProfileTeaSearch,
            jmi_ProfileEmpAdd, jmi_ProfileEmpSearch, jmi_ResultCreate, jmi_ResultSearch, jmi_LibraryAddBook,
            jmi_LibraryBorrowBook, jmi_LibraryReport, jmi_AccountsStuFees, jmi_AccountsSalaryPayment, jmi_AccountsAccessoryBills;
    
    public UserModel() throws SQLException {
        dbConnection = Classes.DatabaseConnection.invokeJDBC();
    }
    
    public String ValidityCheck() {
        if ("".equals(Name) || "".equals(Password) || "".equals(Role)) {
            return "?!? problems have been found during validation ?!?".toUpperCase();
        } else {
            return "Checked!!!";
        }
    }
    
    public String SaveUsrProfile(String dmlSave) {
        String returnStatement = null;
        try {
            PS = dbConnection.prepareStatement(dmlSave);
            PS.setString(1, Id);
            PS.setString(2, Name);
            PS.setString(3, Password);
            PS.setString(4, Role);
            
            int executeNotification = PS.executeUpdate();
            if (executeNotification != 0) {
                returnStatement = "CONGRATULATION! GIVEN USER PROFILE DETAILS ARE IN DATABASE.";
            } else {
                returnStatement = "FAILED? SOMEHOW DIDN'T REACH IN DATABASE.";
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnStatement;
    }
    
    public String SearchUsrProfile(String querySerach) {
        String feedBack = "";
        try {
            PS = dbConnection.prepareStatement(querySerach);
            PS.setString(1, SearchId);
            ResultSet getDbData = PS.executeQuery();
            
            while (getDbData.next()) {
                Id = getDbData.getString("USRID");
                Name = getDbData.getString("USRNAME");
                Password = getDbData.getString("USRPASSWD");
                Role = getDbData.getString("USRROLE");
            }
            feedBack = "All Retrive";
        } catch (SQLException ex) {
            Logger.getLogger(StudentsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return feedBack;
    }
    
    public String UpdateUsrProfile(String dmlUpdate) {
        String returnStatement = null;
        try {
            PS = dbConnection.prepareStatement(dmlUpdate);
            PS.setString(1, Id);
            PS.setString(2, Name);
            PS.setString(3, Password);
            PS.setString(4, Role);
            
            PS.setString(5, SearchId);
            
            int executeNotification = PS.executeUpdate();
            if (executeNotification != 0) {
                returnStatement = "CONGRATULATION! EDITED USER PROFILE UPDATED TO DATABASE.";
            } else {
                returnStatement = "FAILED? PROBLEM IN UPDATING PROCESS.";
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnStatement;
    }
    
    public String DeleteUsrProfile(String dmlDelete) {
        String returnStatement = null;
        try {
            PS = dbConnection.prepareStatement(dmlDelete);
            PS.setString(1, SearchId);
            
            int executeNotification = PS.executeUpdate();
            if (executeNotification != 0) {
                returnStatement = "CONGRATULATION! SELECTED USER PROFILE DELETED FROM DATABASE.";
            } else {
                returnStatement = "FAILED? IT'S STILL IN DATABASE.";
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnStatement;
    }
    
    public String SearchLogInsData(String querySerach) {
        String returnStatement = null;
        try {
            PS = dbConnection.prepareStatement(querySerach);
            PS.setString(1, Name);
            PS.setString(2, Password);
            PS.setString(3, Role);
            ResultSet rSet = PS.executeQuery();
            
            if (rSet.next()) {
                returnStatement = "Information Exist";
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnStatement;
    }
}
