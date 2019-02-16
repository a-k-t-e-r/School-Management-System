package Classes;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResultsModel {
    Connection dbConnection;
    PreparedStatement PS;
    
    public String ResultId, StudentId, StudentName, StudentClass, StudentSection, StudentMajor;
    
    public String[] SubjectName = new String[20];
    public String[] FullMark = new String[20];
    public String[] SuccessRate = new String[20];
    public String[] PreTestAchMcq = new String[20];
    public String[] PreTestAchDiction = new String[20];
    public String[] PreTestAchApplied = new String[20];
    public String[] PreTestAchSba = new String[20];
    public String[] PreTestAchTotal = new String[20];
    public String[] PreTestLatterGrade = new String[20];
    public String[] PreTestGradePoint = new String[20];
    public String[] PreTestFullMark = new String[20];
    public String[] TestAchMcq = new String[20];
    public String[] TestAchDiction = new String[20];
    public String[] TestAchApplied = new String[20];
    public String[] TestAchSba = new String[20];
    public String[] TestAchTotal = new String[20];
    public String[] TestLatterGrade = new String[20];
    public String[] TestGradePoint = new String[20];
    public String[] TestFullMark = new String[20];
    public String[] AverageMark = new String[20];
    public String[] LatterGrade = new String[20];
    public String[] GradePoint = new String[20];
    public String[] Comment = new String[20];
    
    public ResultsModel() throws SQLException {
        dbConnection = Classes.DatabaseConnection.invokeJDBC();
    }
    
    public String GetSpecificStudentData(String querySerach, String searchId){
        try {
            PS = dbConnection.prepareStatement(querySerach);
            PS.setString(1, searchId);
            
            ResultSet getDbData = PS.executeQuery();
            while (getDbData.next()) {
                StudentId = getDbData.getString("STUID");
                StudentName = getDbData.getString("STUNAME");
                StudentClass = getDbData.getString("STUCLASS");
                StudentSection = getDbData.getString("STUSECTION");
                StudentMajor = getDbData.getString("STUMAJOR");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ResultsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "All Retrive";
    }
    
    public int GetColumnsAmount(){
        int columnNumber = 0;
        try {
            PS = dbConnection.prepareStatement("SELECT COUNT(*) AS NoOfColumn FROM information_schema.columns WHERE table_name = 'RESULTDATASET'");
            ResultSet getDbData = PS.executeQuery();
            while (getDbData.next()) {
                columnNumber = Integer.parseInt(getDbData.getString("NoOfColumn"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ResultsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return columnNumber;
    }
    
    public String GetLastColumnName() {
        String columnName = "", columnPosition;
        try {
            PS = dbConnection.prepareStatement("SELECT COLUMN_NAME, ORDINAL_POSITION FROM information_schema.COLUMNS WHERE TABLE_SCHEMA = 'SMS-PS' AND TABLE_NAME = 'RESULTDATASET' ORDER BY ORDINAL_POSITION DESC LIMIT 1");
            ResultSet getDbData = PS.executeQuery();
            while (getDbData.next()) {
                columnName = getDbData.getString("COLUMN_NAME");
                columnPosition = getDbData.getString("ORDINAL_POSITION");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ResultsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return columnName;
    }
    
    public void AddColumns(String sqlCommand) {
        String notificationAlert = "";
        try {
            PS = dbConnection.prepareStatement(sqlCommand);
            int executeNotification = PS.executeUpdate();
            if (executeNotification != 0) {
                notificationAlert = "COLUMNS ADDED";
            } else {
                notificationAlert = "FAILED? SOMEHOW DIDN'T WORK ON.";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ResultsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String CheckClassExistence(String className) {
        String classNameFromDb = "";
        try {
            PS = dbConnection.prepareStatement("SELECT RLTSTUCLASS FROM RESULTDATASET WHERE RLTSTUCLASS = ?");
            PS.setString(1, className);
            ResultSet getDbData = PS.executeQuery();
            while (getDbData.next()) {
                classNameFromDb = getDbData.getString("RLTSTUCLASS");
            }
        } catch (SQLException ex) {
            classNameFromDb = "Not Found";
            Logger.getLogger(ResultsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return classNameFromDb;
    }
    
    public String[][] GetClassSubjectDetails(String searchBy, int subjectAmount, String frameName) {
        String[][] columnValues = new String[subjectAmount][23];
        String queryString = "SELECT ";
        try {
            if("ResultsSearch".equals(frameName)) {
                queryString += "RLTSTUID, ";
            }
            queryString += "RLTSUBNAME1, RLTFULLMARK1, RLTSUCCESSRATE1, RLTPRETESTMCQ1, RLTPRETESTDICTION1, RLTPRETESTAPPLIED1,"
                    + "RLTPRETESTSBA1, RLTPRETESTTOTAL1, RLTPRETESTLGRADE1, RLTPRETESTGPOINT1, RLTPRETESTFULLMARK1, RLTTESTMCQ1, RLTTESTDICTION1,"
                    + "RLTTESTAPPLIED1, RLTTESTSBA1, RLTTESTTOTAL1, RLTTESTLGRADE1, RLTTESTGPOINT1, RLTTESTFULLMARK1, RLTAVGMARK1, RLTLGRADE1,"
                    + "RLTGPOINT1, RLTCOMMENT1";
            for (int i = 2; i <= subjectAmount; i++) {
                queryString += ", RLTSUBNAME" + i + ", RLTFULLMARK" + i + ", RLTSUCCESSRATE" + i + ", RLTPRETESTMCQ" + i + ", RLTPRETESTDICTION" + i
                        + ", RLTPRETESTAPPLIED" + i + ", RLTPRETESTSBA" + i + ", RLTPRETESTTOTAL" + i + ", RLTPRETESTLGRADE" + i + ", RLTPRETESTGPOINT" + i
                        + ", RLTPRETESTFULLMARK" + i + ", RLTTESTMCQ" + i + ", RLTTESTDICTION" + i + ", RLTTESTAPPLIED" + i + ", RLTTESTSBA" + i
                        + ", RLTTESTTOTAL" + i + ", RLTTESTLGRADE" + i + ", RLTTESTGPOINT" + i + ", RLTTESTFULLMARK" + i + ", RLTAVGMARK" + i + ", RLTLGRADE" + i
                        + ", RLTGPOINT" + i + ", RLTCOMMENT" + i;
            }
            queryString += " FROM RESULTDATASET WHERE ";
            if("ResultsCreate".equals(frameName)) {
                queryString += "RLTSTUCLASS = ?";
            } else if("ResultsSearch".equals(frameName)) {
                queryString += "RLTID = ? OR RLTSTUID = ? OR RLTSTUCLASS = ?";
            }
            
            PS = dbConnection.prepareStatement(queryString);
            PS.setString(1, searchBy);
            if("ResultsSearch".equals(frameName)) {
                PS.setString(2, searchBy);
                PS.setString(3, searchBy);
            }
            
            ResultSet getDbData = PS.executeQuery();
            while (getDbData.next()) {
                if("ResultsSearch".equals(frameName)) {
                    StudentId = getDbData.getString("RLTSTUID");
                }
                for (int row = 0; row < subjectAmount; row++) {
                    for (int col = 0; col < 23; col++) {
                        switch (col) {
                            case 0:
                                columnValues[row][col] = getDbData.getString("RLTSUBNAME" + (row + 1));
                                break;
                            case 1:
                                columnValues[row][col] = getDbData.getString("RLTFULLMARK" + (row + 1));
                                break;
                            case 2:
                                columnValues[row][col] = getDbData.getString("RLTSUCCESSRATE" + (row + 1));
                                break;
                            case 3:
                                columnValues[row][col] = getDbData.getString("RLTPRETESTMCQ" + (row + 1));
                                break;
                            case 4:
                                columnValues[row][col] = getDbData.getString("RLTPRETESTDICTION" + (row + 1));
                                break;
                            case 5:
                                columnValues[row][col] = getDbData.getString("RLTPRETESTAPPLIED" + (row + 1));
                                break;
                            case 6:
                                columnValues[row][col] = getDbData.getString("RLTPRETESTSBA" + (row + 1));
                                break;
                            case 7:
                                columnValues[row][col] = getDbData.getString("RLTPRETESTTOTAL" + (row + 1));
                                break;
                            case 8:
                                columnValues[row][col] = getDbData.getString("RLTPRETESTLGRADE" + (row + 1));
                                break;
                            case 9:
                                columnValues[row][col] = getDbData.getString("RLTPRETESTGPOINT" + (row + 1));
                                break;
                            case 10:
                                columnValues[row][col] = getDbData.getString("RLTPRETESTFULLMARK" + (row + 1));
                                break;
                            case 11:
                                columnValues[row][col] = getDbData.getString("RLTTESTMCQ" + (row + 1));
                                break;
                            case 12:
                                columnValues[row][col] = getDbData.getString("RLTTESTDICTION" + (row + 1));
                                break;
                            case 13:
                                columnValues[row][col] = getDbData.getString("RLTTESTAPPLIED" + (row + 1));
                                break;
                            case 14:
                                columnValues[row][col] = getDbData.getString("RLTTESTSBA" + (row + 1));
                                break;
                            case 15:
                                columnValues[row][col] = getDbData.getString("RLTTESTTOTAL" + (row + 1));
                                break;
                            case 16:
                                columnValues[row][col] = getDbData.getString("RLTTESTLGRADE" + (row + 1));
                                break;
                            case 17:
                                columnValues[row][col] = getDbData.getString("RLTTESTGPOINT" + (row + 1));
                                break;
                            case 18:
                                columnValues[row][col] = getDbData.getString("RLTTESTFULLMARK" + (row + 1));
                                break;
                            case 19:
                                columnValues[row][col] = getDbData.getString("RLTAVGMARK" + (row + 1));
                                break;
                            case 20:
                                columnValues[row][col] = getDbData.getString("RLTLGRADE" + (row + 1));
                                break;
                            case 21:
                                columnValues[row][col] = getDbData.getString("RLTGPOINT" + (row + 1));
                                break;
                            case 22:
                                columnValues[row][col] = getDbData.getString("RLTCOMMENT" + (row + 1));
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ResultsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return columnValues;
    }
    
    private String FromValidation(int SubjectNumber) {
        String notifyMsg = null;
        for (int e = 0; e < SubjectNumber; e++) {
            if ("".equals(SubjectName[e]) || "".equals(FullMark[e]) || "".equals(SuccessRate[e])
                    || "".equals(PreTestAchMcq[e]) || "".equals(PreTestAchDiction[e]) || "".equals(PreTestAchApplied[e])
                    || "".equals(PreTestAchSba[e]) || "".equals(PreTestAchTotal[e]) || "".equals(PreTestLatterGrade[e])
                    || "".equals(PreTestGradePoint[e]) || "".equals(PreTestFullMark[e]) || "".equals(TestAchMcq[e])
                    || "".equals(TestAchDiction[e]) || "".equals(TestAchApplied[e]) || "".equals(TestAchSba[e])
                    || "".equals(TestAchTotal[e]) || "".equals(TestLatterGrade[e]) || "".equals(TestGradePoint[e])
                    || "".equals(TestFullMark[e]) || "".equals(AverageMark[e]) || "".equals(LatterGrade[e])
                    || "".equals(GradePoint[e]) || "".equals(Comment[e])) {
                notifyMsg = "";
                break;
            } else {
                notifyMsg = "Checked!!!";
            }
        }
        return notifyMsg;
    }
    
    public String ValidityCheck(int SubjectNumber) {
        if ("".equals(ResultId) || "".equals(StudentId) || "".equals(StudentClass) || "".equals(FromValidation(SubjectNumber))) {
            return "You did something miss to FILL up.".toUpperCase();
        } else {
            return "Checked!!!";
        }
    }
    
    public String SaveStudentResults(String dmlSave, int totalColumn) {
        String returnStatement = "";
        try {
            PS = dbConnection.prepareStatement(dmlSave);
            PS.setString(1, ResultId);
            PS.setString(2, StudentId);
            PS.setString(3, StudentClass);
            int num = 0;
            for (int i = 4; i < totalColumn; i++) {
                if (num < 20) {
                    PS.setString(i, SubjectName[num]);
                    PS.setString(++i, FullMark[num]);
                    PS.setString(++i, SuccessRate[num]);
                    PS.setString(++i, PreTestAchMcq[num]);
                    PS.setString(++i, PreTestAchDiction[num]);
                    PS.setString(++i, PreTestAchApplied[num]);
                    PS.setString(++i, PreTestAchSba[num]);
                    PS.setString(++i, PreTestAchTotal[num]);
                    PS.setString(++i, PreTestLatterGrade[num]);
                    PS.setString(++i, PreTestGradePoint[num]);
                    PS.setString(++i, PreTestFullMark[num]);
                    PS.setString(++i, TestAchMcq[num]);
                    PS.setString(++i, TestAchDiction[num]);
                    PS.setString(++i, TestAchApplied[num]);
                    PS.setString(++i, TestAchSba[num]);
                    PS.setString(++i, TestAchTotal[num]);
                    PS.setString(++i, TestLatterGrade[num]);
                    PS.setString(++i, TestGradePoint[num]);
                    PS.setString(++i, TestFullMark[num]);
                    PS.setString(++i, AverageMark[num]);
                    PS.setString(++i, LatterGrade[num]);
                    PS.setString(++i, GradePoint[num]);
                    PS.setString(++i, Comment[num]);
                }
                ++num;
            }
            int executeNotification = PS.executeUpdate();
            if (executeNotification != 0) {
                returnStatement = "CONGRATULATION! STUDENT RESULTS HAVE SAVED IN DATABASE.";
            } else {
                returnStatement = "FAILED? SOME HOW DIDN'T REACH IN DATABASE.";
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnStatement;
    }
    
    public String GetStudentIdByResultId(String searchId) {
        String stuId = "";
        try {
            PS = dbConnection.prepareStatement("SELECT RLTSTUID FROM RESULTDATASET WHERE RLTID = ?");
            PS.setString(1, searchId);
            
            ResultSet getDbData = PS.executeQuery();
            while (getDbData.next()) {
                stuId = getDbData.getString("RLTSTUID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ResultsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stuId;
    }

    public String UpdateStudentResults(String dmlUpdate, int totalColumn, String searchId) {
        String returnStatement = "";
        int i = 0, num = 0;
        try {
            PS = dbConnection.prepareStatement(dmlUpdate);
            PS.setString(1, ResultId);
            PS.setString(2, StudentId);
            PS.setString(3, StudentClass);
            for (i = 4; i < totalColumn; i++) {
                if (num < 20) {
                    PS.setString(i, SubjectName[num]);
                    PS.setString(++i, FullMark[num]);
                    PS.setString(++i, SuccessRate[num]);
                    PS.setString(++i, PreTestAchMcq[num]);
                    PS.setString(++i, PreTestAchDiction[num]);
                    PS.setString(++i, PreTestAchApplied[num]);
                    PS.setString(++i, PreTestAchSba[num]);
                    PS.setString(++i, PreTestAchTotal[num]);
                    PS.setString(++i, PreTestLatterGrade[num]);
                    PS.setString(++i, PreTestGradePoint[num]);
                    PS.setString(++i, PreTestFullMark[num]);
                    PS.setString(++i, TestAchMcq[num]);
                    PS.setString(++i, TestAchDiction[num]);
                    PS.setString(++i, TestAchApplied[num]);
                    PS.setString(++i, TestAchSba[num]);
                    PS.setString(++i, TestAchTotal[num]);
                    PS.setString(++i, TestLatterGrade[num]);
                    PS.setString(++i, TestGradePoint[num]);
                    PS.setString(++i, TestFullMark[num]);
                    PS.setString(++i, AverageMark[num]);
                    PS.setString(++i, LatterGrade[num]);
                    PS.setString(++i, GradePoint[num]);
                    PS.setString(++i, Comment[num]);
                }
                ++num;
            }
            PS.setString(i, searchId);
            
            int executeNotification = PS.executeUpdate();
            if (executeNotification != 0) {
                returnStatement = "CONGRATULATION! STUDENT RESULTS HAVE SAVED IN DATABASE.";
            } else {
                returnStatement = "FAILED? SOME HOW DIDN'T REACH IN DATABASE.";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ResultsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnStatement;
    }
    
    public String DocumentOfResultSearchReport(String StudentId, String[] subjectName, String[] preTestTotalMark, String[] testTotalMark, String[] averageMark, String[] latterGrade, String[] GradePoint) {
        String fileName = "";
        try {
            Document ResultReport = new Document(PageSize.A4);
            fileName = "reportFiles\\_resultReport.pdf";
            PdfWriter paymentSlipWriter = PdfWriter.getInstance(ResultReport, new FileOutputStream(fileName));
            ResultReport.open();
            String CopyOf = "Student Results Report\n";
            
            Image img = Image.getInstance("src\\Images\\School Logo Small.png");
            img.setAlignment(Image.UNDERLYING);
            Paragraph paymentId = new Paragraph(StudentId, new Font(Font.COURIER, 10, Font.BOLD, new Color(255, 0, 0)));
            paymentId.setAlignment(Paragraph.ALIGN_RIGHT);
            
            String separator = "-------------------------------------------------------------------------------------------------------"
                    + "-----------------------------------------------------\n";
            String paragraphPart_1 = "NAME OF THE SCHOOL\nSchool address goes here\nCity, State, Zip code\n"
                    + "Tel: 122-455-7788 | Fax: 444-555-6666\nEmail: example@email.com\n";
            Paragraph schoolNameAndAddress = new Paragraph(paragraphPart_1, new Font(Font.TIMES_ROMAN, 9, Font.BOLD, new Color(0, 0, 0)));
            schoolNameAndAddress.setAlignment(Paragraph.ALIGN_RIGHT);
            Paragraph copyName = new Paragraph(CopyOf, new Font(Font.COURIER, 8, Font.BOLD, new Color(0, 0, 255)));
            copyName.setAlignment(Paragraph.ALIGN_LEFT);
            String paragraphPart_2 = "STUDENT RESULTS QUERIES\n";
            Paragraph paymentTitle = new Paragraph(separator + separator + paragraphPart_2 + separator, new Font(Font.TIMES_ROMAN, 10, Font.BOLD, new Color(0, 0, 0)));
            paymentTitle.setAlignment(Paragraph.ALIGN_CENTER);
            
            /* ----------------------------Table------------------------------*/
            PdfPTable dataTable = new PdfPTable(7);
            dataTable.setWidthPercentage(100f);
            float[] columnWidths = {0.4f, 0.4f, 0.4f, 0.4f, 0.4f, 0.4f, 0.4f};
            dataTable.setWidths(columnWidths);
            /* -------------------------Table Header--------------------------*/
            String[] hearderNames = new String[]{"Student Id", "Subject Name", "Total Mark (PreTest)", "Total Mark (Test)", "Average", "Latter Grade", "Grade Point"};
            PdfPTable headerTable = new PdfPTable(hearderNames.length);
            headerTable.setWidthPercentage(100f);
            headerTable.setWidths(columnWidths);
            
            PdfPCell headerCell = new PdfPCell();
            headerCell.setBorderColor(Color.BLACK);
            for (String header : hearderNames) {
                headerCell.setPhrase(new Phrase(header, new Font(Font.NORMAL, 11, Font.BOLD, new Color(255, 255, 255))));
                headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                headerCell.setGrayFill(0.3f);
                headerTable.addCell(headerCell);
            }
            headerTable.completeRow();
            /* ---------------------------------------------------------------*/
            /* --------------------------Table Data---------------------------*/
            PdfPCell dataCell = new PdfPCell();
            dataCell.setBorderColor(Color.BLACK);
            
            int serialNum = 0;
            for (int i = 0; i < 20; i++) {
                    dataCell = new PdfPCell(new Phrase(StudentId, new Font(Font.NORMAL, 10)));
                    dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    dataTable.addCell(dataCell);
                    
                    dataCell = new PdfPCell(new Phrase(subjectName[i], new Font(Font.NORMAL, 10)));
                    dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    dataTable.addCell(dataCell);
                    
                    dataCell = new PdfPCell(new Phrase(preTestTotalMark[i], new Font(Font.NORMAL, 10)));
                    dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    dataTable.addCell(dataCell);
                    
                    dataCell = new PdfPCell(new Phrase(testTotalMark[i], new Font(Font.NORMAL, 10)));
                    dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    dataTable.addCell(dataCell);
                    
                    dataCell = new PdfPCell(new Phrase(averageMark[i], new Font(Font.NORMAL, 10)));
                    dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    dataTable.addCell(dataCell);
                    
                    dataCell = new PdfPCell(new Phrase(latterGrade[i], new Font(Font.NORMAL, 10)));
                    dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    dataTable.addCell(dataCell);
                    
                    dataCell = new PdfPCell(new Phrase(GradePoint[i], new Font(Font.NORMAL, 10)));
                    dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    dataTable.addCell(dataCell);
            }
            /* ---------------------------------------------------------------*/
            ResultReport.add(img);
            ResultReport.add(paymentId);
            ResultReport.add(schoolNameAndAddress);
            ResultReport.add(copyName);
            ResultReport.add(paymentTitle);
            ResultReport.add(headerTable);
            ResultReport.add(dataTable);
            ResultReport.close();
        } catch (DocumentException | FileNotFoundException ex) {
            Logger.getLogger(ResultsModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ResultsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fileName;
    }
    
        public static String GetGradeData(String gradeType, double pointMark) {
        String latterGrade = "", gradePoint = "";
        if (pointMark >= 80 && pointMark <= 100) {
            gradePoint = "5.0";
            latterGrade = "A+";
        } else if (pointMark >= 70 && pointMark <= 79) {
            gradePoint = "4.0";
            latterGrade = "A";
        } else if (pointMark >= 60 && pointMark <= 69) {
            gradePoint = "3.5";
            latterGrade = "A-";
        } else if (pointMark >= 50 && pointMark <= 59) {
            gradePoint = "3.0";
            latterGrade = "B";
        } else if (pointMark >= 40 && pointMark <= 49) {
            gradePoint = "2.0";
            latterGrade = "C";
        } else if (pointMark >= 33 && pointMark <= 39) {
            gradePoint = "1.0";
            latterGrade = "D";
        } else {
            gradePoint = "0.0";
            latterGrade = "F";
        }
        if (gradeType.equals("Grade Point")) {
            return gradePoint;
        } else {
            return latterGrade;
        }
    }
}
