package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LibraryBookModel {
    Connection dbConnection;
    PreparedStatement PS;

    public String Id, BId, SId, Name, EntryDate, BorrowDate, SubmitDate,
            Author, Edition, Publication, ISBN, DayCount, Class, Section,
            Major, SearchId;
    
    public LibraryBookModel() throws SQLException {
        dbConnection = Classes.DatabaseConnection.invokeJDBC();
    }
    
    public String BookAddValidityCheck() {
        if ("".equals(Id) || "".equals(Name) || "".equals(EntryDate)
                || "".equals(Author) || "".equals(Edition)
                || "".equals(Publication) || "".equals(ISBN)) {
            return "?!? problems have been found during validation ?!?".toUpperCase();
        } else {
            return "Checked!!!";
        }
    }
    
    public String StoreBookDetails(String dmlSave) {
        String returnStatement = null;
        try {
            PS = dbConnection.prepareStatement(dmlSave);
            PS.setString(1, Id);
            PS.setString(2, Name);
            PS.setString(3, EntryDate);
            PS.setString(4, Author);
            PS.setString(5, Edition);
            PS.setString(6, Publication);
            PS.setString(7, ISBN);
            
            int executeNotification = PS.executeUpdate();
            if (executeNotification != 0) {
                returnStatement = "CONGRATULATION! GIVEN BOOK DETAILS ARE IN DATABASE.";
            } else {
                returnStatement = "FAILED? SOMEHOW DIDN'T REACH IN DATABASE.";
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnStatement;
    }
    
    public String SearchBookDetails(String querySerach, String whomFor) {
        try {
            PS = dbConnection.prepareStatement(querySerach);
            PS.setString(1, SearchId);
            ResultSet getDbData = PS.executeQuery();
            
            if (SearchId.substring(0, 3).equals("BOK") && whomFor.equals("For Borrow List")) {
                int i = 0;
                while (getDbData.next()) {
                    BookId[i] = getDbData.getString("BOKID");
                    BookTitle[i] = getDbData.getString("BOKNAME");
                    AuthorName[i] = getDbData.getString("BOKAUTHOR");
                    BookEdition[i] = getDbData.getString("BOKEDITION");
                    AccessDate[i]= getDbData.getString("BOKDATE");
                    i++;
                }
            } else {
                while (getDbData.next()) {
                    Id = getDbData.getString("BOKID");
                    Name = getDbData.getString("BOKNAME");
                    EntryDate = getDbData.getString("BOKDATE");
                    Author = getDbData.getString("BOKAUTHOR");
                    Edition = getDbData.getString("BOKEDITION");
                    Publication = getDbData.getString("BOKPUBNAME");
                    ISBN = getDbData.getString("BOKISBN");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "All Retrive";
    }
    
    public String RestoreBookDetails(String dmlUpdate) {
        String returnStatement = null;
        try {
            PS = dbConnection.prepareStatement(dmlUpdate);
            PS.setString(1, Id);
            PS.setString(2, Name);
            PS.setString(3, EntryDate);
            PS.setString(4, Author);
            PS.setString(5, Edition);
            PS.setString(6, Publication);
            PS.setString(7, ISBN);
            
            PS.setString(8, SearchId);
            
            int executeNotification = PS.executeUpdate();
            if (executeNotification != 0) {
                returnStatement = "CONGRATULATION! EDITED BOOK DETAILS UPDATED TO DATABASE.";
            } else {
                returnStatement = "FAILED? PROBLEM IN UPDATING PROCESS.";
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnStatement;
    }
    
    public String WithdrawBookDetails(String dmlDelete) {
        String returnStatement = null;
        try {
            PS = dbConnection.prepareStatement(dmlDelete);
            PS.setString(1, SearchId);
            
            int executeNotification = PS.executeUpdate();
            if (executeNotification != 0) {
                returnStatement = "CONGRATULATION! SELECTED BOOK INFORMATION VANISHED FROM DATABASE.";
            } else {
                returnStatement = "FAILED? IT'S STILL IN DATABASE.";
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnStatement;
    }
    
    public String BookBorrowValidityCheck() {
        if ("".equals(Id) || "".equals(BId) || "".equals(SId)
                || "".equals(BorrowDate) || "".equals(SubmitDate) || "".equals(DayCount)) {
            return "?!? problems have been found during validation ?!?".toUpperCase();
        } else {
            return "Checked!!!";
        }
    }
    
    public String SaveBorrowDetails(String dmlSave) {
        String returnStatement = null;
        try {
            PS = dbConnection.prepareStatement(dmlSave);
            PS.setString(1, Id);
            PS.setString(2, BId);
            PS.setString(3, SId);
            PS.setString(4, BorrowDate);
            PS.setString(5, SubmitDate);
            PS.setString(6, DayCount);
            
            int executeNotification = PS.executeUpdate();
            if (executeNotification != 0) {
                returnStatement = "CONGRATULATION! BORROWED BOOK INFORMATION RECORD SAVED.";
            } else {
                returnStatement = "FAILED? SOMEHOW DIDN'T REACH IN DATABASE.";
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnStatement;
    }
    
    public String[] BrwId = new String[100];
    public String[] BrwDate = new String[100];
    public String[] SubDate = new String[100];
    public String[] BookId  = new String[100];
    public String[] BookTitle  = new String[100];
    public String[] AuthorName  = new String[100];
    public String[] BookEdition  = new String[100];
    public String[] AccessDate  = new String[100];
    
    public int CountSearchQuery(String countQuery) {
        int count =0;
        try {
            PS = dbConnection.prepareStatement(countQuery);
            PS.setString(1, SearchId);
            ResultSet getDbData = PS.executeQuery();
            while (getDbData.next()) {
                count = Integer.parseInt(getDbData.getString("count(*)"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LibraryBookModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public String SearchBorrowDetails(String querySerach) {
        try {
            PS = dbConnection.prepareStatement(querySerach);
            PS.setString(1, SearchId);
            ResultSet getDbData = PS.executeQuery();

            if (SearchId.substring(0, 3).equals("BOK")) {
                int i = 0;
                while (getDbData.next()) {
                    BrwId[i] = getDbData.getString("BRWID");
                    BrwDate[i] = getDbData.getString("BRWDATE");
                    SubDate[i] = getDbData.getString("BRWSUBDATE");
                    i++;
                }
            }
            while (getDbData.next()) {
                Id = getDbData.getString("BRWID");
                BId = getDbData.getString("BRWBOKID");
                SId = getDbData.getString("BRWSTUID");
                BorrowDate = getDbData.getString("BRWDATE");
                SubmitDate = getDbData.getString("BRWSUBDATE");
                DayCount = getDbData.getString("BRWDAYS");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "All Retrive";
    }
    
    public String GetBookDataFromDb(String querySerach) {
        try {
            PS = dbConnection.prepareStatement(querySerach);
            PS.setString(1, SearchId);
            ResultSet getDbData = PS.executeQuery();
            
            while (getDbData.next()) {
                BId = getDbData.getString("BOKID");
                Name = getDbData.getString("BOKNAME");
                Author = getDbData.getString("BOKAUTHOR");
                Edition = getDbData.getString("BOKEDITION");
                Publication = getDbData.getString("BOKPUBNAME");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LibraryBookModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "All Retrive";
    }
    
    public String GetStudentDataFromDb(String querySerach){
        try {
            PS = dbConnection.prepareStatement(querySerach);
            PS.setString(1, SearchId);
            ResultSet getDbData = PS.executeQuery();
            
            while (getDbData.next()) {
                SId = getDbData.getString("STUID");
                Name = getDbData.getString("STUNAME");
                Class = getDbData.getString("STUCLASS");
                Section = getDbData.getString("STUSECTION");
                Major = getDbData.getString("STUMAJOR");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LibraryBookModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "All Retrive";
    }
    
    public String UpgradeBorrowDetails(String dmlUpdate) {
        String returnStatement = null;
        try {
            PS = dbConnection.prepareStatement(dmlUpdate);
            PS.setString(1, Id);
            PS.setString(2, BId);
            PS.setString(3, SId);
            PS.setString(4, BorrowDate);
            PS.setString(5, SubmitDate);
            PS.setString(6, DayCount);
            
            PS.setString(7, SearchId);
            
            int executeNotification = PS.executeUpdate();
            if (executeNotification != 0) {
                returnStatement = "CONGRATULATION! BORROWED BOOK INFORMATION RECORD UPDATED.";
            } else {
                returnStatement = "FAILED? PROBLEM IN UPDATING PROCESS.";
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnStatement;
    }
    
    public String DeleteBorrowDetails(String dmlDelete) {
        String returnStatement = null;
        try {
            PS = dbConnection.prepareStatement(dmlDelete);
            PS.setString(1, SearchId);
            
            int executeNotification = PS.executeUpdate();
            if (executeNotification != 0) {
                returnStatement = "CONGRATULATION! BORROWED BOOK INFORMATION RECORD DELETED.";
            } else {
                returnStatement = "FAILED? IT'S STILL IN DATABASE.";
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnStatement;
    }
    
    public String GetBorrowIdByBookId(String bookId) {
        try {
            PS = dbConnection.prepareStatement("SELECT BRWID FROM BOOKBORROWINFO WHERE BRWBOKID = ?");
            PS.setString(1, bookId);
            ResultSet getDbData = PS.executeQuery();
            
            while (getDbData.next()) {
                Id = getDbData.getString("BRWID");
                BorrowDate = getDbData.getString("BRWDATE");
                SubmitDate = getDbData.getString("BRWSUBDATE");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LibraryBookModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "All Retrive";
    }
}
