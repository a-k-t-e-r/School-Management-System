package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutomaticIdGenerator {

    Connection dbConnection;
    PreparedStatement PS;

    public AutomaticIdGenerator() throws SQLException {
        dbConnection = Classes.DatabaseConnection.invokeJDBC();
    }

    public String IdGenerator(String formName) {
        String sqlString = "", IdColumnName = "";
        int IdInNumber = 0;
        try {
            if ("ProfileStudentAdd".equals(formName)) {
                IdColumnName = "STUID";
                sqlString = "SELECT MAX(CONVERT(SUBSTRING(STUID, 5, LENGTH(STUID)), SIGNED)) AS STUID FROM STUDENTDATASET";
            } else if ("ProfileTeacherAdd".equals(formName)) {
                IdColumnName = "TEAID";
                sqlString = "SELECT MAX(CONVERT(SUBSTRING(TEAID, 5, LENGTH(TEAID)), SIGNED)) AS TEAID FROM TEACHERDATASET";
            } else if ("ProfileEmployeeAdd".equals(formName)) {
                IdColumnName = "EMPID";
                sqlString = "SELECT MAX(CONVERT(SUBSTRING(EMPID, 5, LENGTH(EMPID)), SIGNED)) AS EMPID FROM EMPLOYEEDATASET";
            } else if ("LibraryBookAdd".equals(formName)) {
                IdColumnName = "BOKID";
                sqlString = "SELECT MAX(CONVERT(SUBSTRING(BOKID, 5, LENGTH(BOKID)), SIGNED)) AS BOKID FROM BOOKDATASET";
            } else if ("LibraryBookBorrow".equals(formName)) {
                IdColumnName = "BRWID";
                sqlString = "SELECT MAX(CONVERT(SUBSTRING(BRWID, 5, LENGTH(BRWID)), SIGNED)) AS BRWID FROM BOOKBORROWINFO";
            } else if ("UserCreate".equals(formName)) {
                IdColumnName = "USRID";
                sqlString = "SELECT MAX(CONVERT(SUBSTRING(USRID, 5, LENGTH(USRID)), SIGNED)) AS USRID FROM USERDATASET";
            } else if ("AccountStuFees".equals(formName)) {
                IdColumnName = "PAYID";
                sqlString = "SELECT MAX(CONVERT(SUBSTRING(PAYID, 5, LENGTH(PAYID)), SIGNED)) AS PAYID FROM PAYMENTACCOUNT";
            } else if ("ResultsCreate".equals(formName)) {
                IdColumnName = "RLTID";
                sqlString = "SELECT MAX(CONVERT(SUBSTRING(RLTID, 5, LENGTH(RLTID)), SIGNED)) AS RLTID FROM RESULTDATASET";
            } else if ("AccountAccessoryBill".equals(formName)) {
                IdColumnName = "BILID";
                sqlString = "SELECT MAX(CONVERT(SUBSTRING(BILID, 5, LENGTH(BILID)), SIGNED)) AS BILID FROM BILLACCOUNT";
            } else if ("AccountSalaryVoucher".equals(formName)) {
                IdColumnName = "SALID";
                sqlString = "SELECT MAX(CONVERT(SUBSTRING(SALID, 5, LENGTH(SALID)), SIGNED)) AS SALID FROM SALARYACCOUNT";
            } else {
                System.out.println("Check IdGenerator() Method.");
            }
            PS = dbConnection.prepareStatement(sqlString);
            ResultSet getIdFromDB = PS.executeQuery();
            while (getIdFromDB.next()) {
                String IdInString = getIdFromDB.getString(IdColumnName);
                if (IdInString == null) {
                    IdInNumber = 1;
                } else {
                    IdInNumber = Integer.parseInt(IdInString);
                    IdInNumber = IdInNumber + 1;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AutomaticIdGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Integer.toString(IdInNumber);
    }
}
