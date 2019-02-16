package Classes;

import com.lowagie.text.BadElementException;
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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

public class StudentsModel {

    Connection dbConnection;
    PreparedStatement PS;

    public String Id, Name, Class, Section, Major, ParmanentAddr, TemporaryAddr,
            DateOfBirth, Gender, BloodGroup, Religion, Nationality, FatherName,
            MotherName, Guardian, GuardianContact, SearchId;
    public byte[] photographInByte;
    public Icon photographInIcon;

    public StudentsModel() throws SQLException {
        dbConnection = Classes.DatabaseConnection.invokeJDBC();
    }

    public String ValidityCheck() {
        if ("".equals(Id) || "".equals(Name) || photographInByte == null
                || "".equals(Class) || "".equals(Section) || "".equals(Major)
                || "".equals(ParmanentAddr) || "".equals(TemporaryAddr)
                || "".equals(DateOfBirth) || "".equals(Gender) || "".equals(BloodGroup)
                || "".equals(Religion) || "".equals(Nationality) || "".equals(FatherName)
                || "".equals(MotherName) || "".equals(Guardian)) {
            return "?!? problems have been found during validation ?!?".toUpperCase();
        } else {
            return "Checked!!!";
        }
    }

    public String SaveStuProfile(String dmlSave) {
        String returnStatement = "";
        try {
            PS = dbConnection.prepareStatement(dmlSave);
            PS.setString(1, Id);
            PS.setString(2, Name);
            PS.setBytes(3, photographInByte);
            PS.setString(4, Class);
            PS.setString(5, Section);
            PS.setString(6, Major);
            PS.setString(7, ParmanentAddr);
            PS.setString(8, TemporaryAddr);
            PS.setString(9, DateOfBirth);
            PS.setString(10, Gender);
            PS.setString(11, BloodGroup);
            PS.setString(12, Religion);
            PS.setString(13, Nationality);
            PS.setString(14, FatherName);
            PS.setString(15, MotherName);
            PS.setString(16, Guardian);
            PS.setString(17, GuardianContact);

            int executeNotification = PS.executeUpdate();
            if (executeNotification != 0) {
                returnStatement = "CONGRATULATION! GIVEN STUDENT PROFILE DETAILS ARE IN DATABASE.";
            } else {
                returnStatement = "FAILED? SOME HOW DIDN'T REACH IN DATABASE.";
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnStatement;
    }

    public String UpdateStuProfile(String dmlUpdate) {
        String returnStatement = null;
        try {
            PS = dbConnection.prepareStatement(dmlUpdate);
            PS.setString(1, Id);
            PS.setString(2, Name);
            PS.setBytes(3, photographInByte);
            PS.setString(4, Class);
            PS.setString(5, Section);
            PS.setString(6, Major);
            PS.setString(7, ParmanentAddr);
            PS.setString(8, TemporaryAddr);
            PS.setString(9, DateOfBirth);
            PS.setString(10, Gender);
            PS.setString(11, BloodGroup);
            PS.setString(12, Religion);
            PS.setString(13, Nationality);
            PS.setString(14, FatherName);
            PS.setString(15, MotherName);
            PS.setString(16, Guardian);
            PS.setString(17, GuardianContact);

            PS.setString(18, SearchId);

            int executeNotification = PS.executeUpdate();
            if (executeNotification != 0) {
                returnStatement = "CONGRATULATION! EDITED STUDENT PROFILE UPDATED TO DATABASE.";
            } else {
                returnStatement = "FAILED? PROBLEM IN UPDATING PROCESS.";
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnStatement;
    }

    public String DeleteStuProfile(String dmlDelete) {
        String returnStatement = null;
        try {
            PS = dbConnection.prepareStatement(dmlDelete);
            PS.setString(1, SearchId);

            int executeNotification = PS.executeUpdate();
            if (executeNotification != 0) {
                returnStatement = "CONGRATULATION! SELECTED STUDENT PROFILE VANISHED FROM DATABASE.";
            } else {
                returnStatement = "FAILED? IT'S STILL IN DATABASE.";
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnStatement;
    }

    public String SearchStuProfile(String querySerach) {
        try {
            PS = dbConnection.prepareStatement(querySerach);
            PS.setString(1, SearchId);
            ResultSet getDbData = PS.executeQuery();

            while (getDbData.next()) {
                Id = getDbData.getString("STUID");
                Name = getDbData.getString("STUNAME");
                /* -------------------I M A G E------------------- */
                photographInByte = getDbData.getBytes("STUPHOTO");
                photographInIcon = new ImageIcon(photographInByte);
                /* ----------------------------------------------- */
                Class = getDbData.getString("STUCLASS");
                Section = getDbData.getString("STUSECTION");
                Major = getDbData.getString("STUMAJOR");
                ParmanentAddr = getDbData.getString("STUPADDRESS");
                TemporaryAddr = getDbData.getString("STUTADDRESS");
                DateOfBirth = getDbData.getString("STUDATEOFBIRTH");
                Gender = getDbData.getString("STUGENDER");
                BloodGroup = getDbData.getString("STUBLOODGROUP");
                Religion = getDbData.getString("STURELIGION");
                Nationality = getDbData.getString("STUNATIONALITY");
                FatherName = getDbData.getString("STUFATHERNAME");
                MotherName = getDbData.getString("STUMOTHERNAME");
                Guardian = getDbData.getString("STUGUARDIAN");
                GuardianContact = getDbData.getString("STUGUARDIANCONTACT");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "All Retrive";
    }

    public DefaultTableModel searchTableModel;
    private Document studentReport;

    public String[][] SearchForStudents(String sqlQuery, String stuId, String stuClass) {
        String[][] searchResultData = new String[100][6];
        try {
            PS = dbConnection.prepareStatement(sqlQuery);
            PS.setString(1, stuId);
            PS.setString(2, stuClass);
            ResultSet getDbData = PS.executeQuery();
            
            int a = 0;
            while (getDbData.next()) {
                searchResultData[a][0] = getDbData.getString("STUID");
                searchResultData[a][1] = getDbData.getString("STUNAME");
                searchResultData[a][2] = getDbData.getString("STUCLASS");
                searchResultData[a][3] = getDbData.getString("STUSECTION");
                searchResultData[a][4] = getDbData.getString("STUMAJOR");
                searchResultData[a][5] = getDbData.getString("STUGUARDIANCONTACT");

                searchTableModel.addRow(new Object[]{searchResultData[a][0], searchResultData[a][1],
                    searchResultData[a][2], searchResultData[a][3], searchResultData[a][4], searchResultData[a][5]});
                a++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return searchResultData;
    }

    public String SearchReportGenerates(String[][] searchOutcomes) {
        
        String fileLocation = "";
        for (int copy = 1; copy <= 2; copy++) {
            if (copy == 1) {
                try {
                    studentReport = new Document(PageSize.A4);
                    fileLocation = "reportFiles\\studentReport.pdf";
                    PdfWriter paymentSlipWriter = PdfWriter.getInstance(studentReport, new FileOutputStream(fileLocation));
                    studentReport.open();
                    WritingSearchReport(searchOutcomes);
                } catch (DocumentException | FileNotFoundException ex) {
                    Logger.getLogger(StudentsModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return fileLocation;
    }

    private void WritingSearchReport(String[][] searchOutcomes) {
        try {
            Image img = Image.getInstance("src\\Images\\School Logo Small.png");
            img.setAlignment(Image.UNDERLYING);

            String separator = "-----------------------------------------------"
                    + "--------------------------------------------------------"
                    + "-----------------------------------------------------\n";
            String paragraphPart_1 = "NAME OF THE SCHOOL\nSchool address goes here\nCity, State, Zip code\n"
                    + "Tel: 122-455-7788 | Fax: 444-555-6666\nEmail: example@email.com\n";
            Paragraph schoolNameAndAddress = new Paragraph(paragraphPart_1, new Font(Font.TIMES_ROMAN, 9, Font.BOLD, new Color(0, 0, 0)));
            schoolNameAndAddress.setAlignment(Paragraph.ALIGN_RIGHT);
            String paragraphPart_2 = "STUDENT SEARCH REPORT\n";
            Paragraph reportTitle = new Paragraph(separator + separator + paragraphPart_2 + separator, new Font(Font.TIMES_ROMAN, 10, Font.BOLD, new Color(0, 0, 0)));
            reportTitle.setAlignment(Paragraph.ALIGN_CENTER);

            /* ----------------------------Table------------------------------*/
            PdfPTable dataTable = new PdfPTable(7);
            dataTable.setWidthPercentage(100f);
            float[] columnWidths = {0.3f, 0.5f, 1f, 0.5f, 0.5f, 1f, 1f};
            dataTable.setWidths(columnWidths);
            /* -------------------------Table Header--------------------------*/
            String[] hearderNames = new String[]{"#Sl", "Student Id", "Student Name", "Class", "Section", "Major", "Guardian Contact"};
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
            for (int i = 0; i < searchTableModel.getRowCount(); i++) {
                for (int j = 0;;) {
                    dataCell = new PdfPCell(new Phrase(++serialNum + "", new Font(Font.NORMAL, 10)));
                    dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    dataTable.addCell(dataCell);

                    dataCell = new PdfPCell(new Phrase(searchOutcomes[i][j], new Font(Font.NORMAL, 10)));
                    dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    dataTable.addCell(dataCell);

                    dataCell = new PdfPCell(new Phrase(searchOutcomes[i][j+1], new Font(Font.NORMAL, 10)));
                    dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    dataTable.addCell(dataCell);

                    dataCell = new PdfPCell(new Phrase(searchOutcomes[i][j+2], new Font(Font.NORMAL, 10)));
                    dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    dataTable.addCell(dataCell);

                    dataCell = new PdfPCell(new Phrase(searchOutcomes[i][j+3], new Font(Font.NORMAL, 10)));
                    dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    dataTable.addCell(dataCell);

                    dataCell = new PdfPCell(new Phrase(searchOutcomes[i][j+4], new Font(Font.NORMAL, 10)));
                    dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    dataTable.addCell(dataCell);
                    
                    dataCell = new PdfPCell(new Phrase(searchOutcomes[i][j+5], new Font(Font.NORMAL, 10)));
                    dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    dataTable.addCell(dataCell);
                    break;
                }
            }
            /* ---------------------------------------------------------------*/
            
            String paragraphPart_3 = "\n\n- THIS REPORT IS GENERATES ON BASIS OF A CONDITION -";
            Paragraph endTag = new Paragraph(paragraphPart_3, new Font(Font.TIMES_ROMAN, 6, Font.BOLD, new Color(0, 0, 255)));
            endTag.setAlignment(Element.ALIGN_RIGHT);

            studentReport.add(img);
            studentReport.add(schoolNameAndAddress);
            studentReport.add(reportTitle);
            studentReport.add(headerTable);
            studentReport.add(dataTable);
            studentReport.add(endTag);
            studentReport.close();
        } catch (BadElementException | IOException ex) {
            Logger.getLogger(StudentsModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(StudentsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
