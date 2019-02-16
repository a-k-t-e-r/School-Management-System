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

public class TeachersModel {
    Connection dbConnection;
    PreparedStatement PS;

    public String Id, Name, Designation, Department, ParmanentAddr, TemporaryAddr,
            DateOfBirth, Gender, BloodGroup, Religion, Nationality, EduQualification,
            OtherQualification, ContactNo, EmergencyContactNo, SearchId;
    public byte[] photographInByte;
    public Icon photographInIcon;
    
    public DefaultTableModel searchTableModel;
    private Document teacherReport;
    
    public TeachersModel() throws SQLException {
        dbConnection = Classes.DatabaseConnection.invokeJDBC();
    }
    
    public String ValidityCheck() {
        if ("".equals(Id) || "".equals(Name) || photographInByte == null
                || "".equals(Designation) || "".equals(Department)
                || "".equals(ParmanentAddr) || "".equals(TemporaryAddr)
                || "".equals(DateOfBirth) || "".equals(Gender) || "".equals(BloodGroup)
                || "".equals(Religion) || "".equals(Nationality) || "".equals(EduQualification)
                || "".equals(OtherQualification) || "".equals(ContactNo) || "".equals(EmergencyContactNo)) {
            return "?!? problems have been found during validation ?!?".toUpperCase();
        } else {
            return "Checked!!!";
        }
    }
    
    public String SaveTeaProfile(String dmlSave) {
        String returnStatement = null;
        try {
            PS = dbConnection.prepareStatement(dmlSave);
            PS.setString(1, Id);
            PS.setString(2, Name);
            PS.setBytes(3, photographInByte);
            PS.setString(4, Designation);
            PS.setString(5, Department);
            PS.setString(6, ParmanentAddr);
            PS.setString(7, TemporaryAddr);
            PS.setString(8, DateOfBirth);
            PS.setString(9, Gender);
            PS.setString(10, BloodGroup);
            PS.setString(11, Religion);
            PS.setString(12, Nationality);
            PS.setString(13, EduQualification);
            PS.setString(14, OtherQualification);
            PS.setString(15, ContactNo);
            PS.setString(16, EmergencyContactNo);
            
            int executeNotification = PS.executeUpdate();
            if (executeNotification != 0) {
                returnStatement = "CONGRATULATION! GIVEN TEACHER PROFILE DETAILS ARE IN DATABASE.";
            } else {
                returnStatement = "FAILED? SOMEHOW DIDN'T REACH IN DATABASE.";
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnStatement;
    }
    
    public String SearchTeaProfile(String querySerach) {
        try {
            PS = dbConnection.prepareStatement(querySerach);
            PS.setString(1, SearchId);
            ResultSet getDbData = PS.executeQuery();
            
            while (getDbData.next()) {
                Id = getDbData.getString("TEAID");
                Name = getDbData.getString("TEANAME");
                /* -------------------I M A G E------------------- */
                photographInByte = getDbData.getBytes("TEAPHOTO");
                photographInIcon = new ImageIcon(photographInByte);
                /* ----------------------------------------------- */
                Designation = getDbData.getString("TEADESIGNATION");
                Department = getDbData.getString("TEADEPARTMENT");
                ParmanentAddr = getDbData.getString("TEAPADDRESS");
                TemporaryAddr = getDbData.getString("TEATADDRESS");
                DateOfBirth = getDbData.getString("TEADATEOFBIRTH");
                Gender = getDbData.getString("TEAGENDER");
                BloodGroup = getDbData.getString("TEABLOODGROUP");
                Religion = getDbData.getString("TEARELIGION");
                Nationality = getDbData.getString("TEANATIONALITY");
                EduQualification = getDbData.getString("TEAEDUQUALIFICATION");
                OtherQualification = getDbData.getString("TEAOTHERQUALIFICATION");
                ContactNo = getDbData.getString("TEACONTACTNO");
                EmergencyContactNo = getDbData.getString("TEAECONTACTNO");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "All Retrive";
    }
    
    public String UpdateTeaProfile(String dmlUpdate) {
        String returnStatement = null;
        try {
            PS = dbConnection.prepareStatement(dmlUpdate);
            PS.setString(1, Id);
            PS.setString(2, Name);
            PS.setBytes(3, photographInByte);
            PS.setString(4, Designation);
            PS.setString(5, Department);
            PS.setString(6, ParmanentAddr);
            PS.setString(7, TemporaryAddr);
            PS.setString(8, DateOfBirth);
            PS.setString(9, Gender);
            PS.setString(10, BloodGroup);
            PS.setString(11, Religion);
            PS.setString(12, Nationality);
            PS.setString(13, EduQualification);
            PS.setString(14, OtherQualification);
            PS.setString(15, ContactNo);
            PS.setString(16, EmergencyContactNo);
            
            PS.setString(17, SearchId);
            
            int executeNotification = PS.executeUpdate();
            if (executeNotification != 0) {
                returnStatement = "CONGRATULATION! EDITED TEACHER PROFILE UPDATED TO DATABASE.";
            } else {
                returnStatement = "FAILED? PROBLEM IN UPDATING PROCESS.";
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnStatement;
    }
    
    public String DeleteTeaProfile(String dmlDelete) {
        String returnStatement = null;
        try {
            PS = dbConnection.prepareStatement(dmlDelete);
            PS.setString(1, SearchId);
            
            int executeNotification = PS.executeUpdate();
            if (executeNotification != 0) {
                returnStatement = "CONGRATULATION! SELECTED TEACHER PROFILE VANISHED FROM DATABASE.";
            } else {
                returnStatement = "FAILED? IT'S STILL IN DATABASE.";
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnStatement;
    }
    
    public String[][] SearchForTeachers(String sqlQuery, String stuId, String stuClass) {
        String[][] searchResultData = new String[100][6];
        try {
            PS = dbConnection.prepareStatement(sqlQuery);
            PS.setString(1, stuId);
            PS.setString(2, stuClass);
            ResultSet getDbData = PS.executeQuery();

            int a = 0;
            while (getDbData.next()) {
                searchResultData[a][0] = getDbData.getString("TEAID");
                searchResultData[a][1] = getDbData.getString("TEANAME");
                searchResultData[a][2] = getDbData.getString("TEADESIGNATION");
                searchResultData[a][3] = getDbData.getString("TEADEPARTMENT");
                searchResultData[a][4] = getDbData.getString("TEAEDUQUALIFICATION");
                searchResultData[a][5] = getDbData.getString("TEACONTACTNO");

                searchTableModel.addRow(new Object[]{searchResultData[a][0], searchResultData[a][1],
                    searchResultData[a][2], searchResultData[a][3], searchResultData[a][4], searchResultData[a][5]});
                a++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TeachersModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return searchResultData;
    }
    
    public String SearchReportGenerates(String[][] searchOutcomes) {
        String fileLocation = "";
        for (int copy = 1; copy <= 2; copy++) {
            if (copy == 1) {
                try {
                    teacherReport = new Document(PageSize.A4);
                    fileLocation = "reportFiles\\teacherReport.pdf";
                    PdfWriter paymentSlipWriter = PdfWriter.getInstance(teacherReport, new FileOutputStream(fileLocation));
                    teacherReport.open();
                    WritingSearchReport(searchOutcomes);
                } catch (DocumentException | FileNotFoundException ex) {
                    Logger.getLogger(TeachersModel.class.getName()).log(Level.SEVERE, null, ex);
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
            String paragraphPart_2 = "TEACHER SEARCH REPORT\n";
            Paragraph reportTitle = new Paragraph(separator + separator + paragraphPart_2 + separator, new Font(Font.TIMES_ROMAN, 10, Font.BOLD, new Color(0, 0, 0)));
            reportTitle.setAlignment(Paragraph.ALIGN_CENTER);

            /* ----------------------------Table------------------------------*/
            PdfPTable dataTable = new PdfPTable(7);
            dataTable.setWidthPercentage(100f);
            float[] columnWidths = {0.3f, 0.5f, 0.8f, 0.8f, 0.8f, 0.6f, 0.8f};
            dataTable.setWidths(columnWidths);
            /* -------------------------Table Header--------------------------*/
            String[] hearderNames = new String[]{"#Sl", "Teacher Id", "Teacher Name", "Designation", "Department", "Educational Qualification", "Contact No."};
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

            teacherReport.add(img);
            teacherReport.add(schoolNameAndAddress);
            teacherReport.add(reportTitle);
            teacherReport.add(headerTable);
            teacherReport.add(dataTable);
            teacherReport.add(endTag);
            teacherReport.close();
        } catch (BadElementException | IOException ex) {
            Logger.getLogger(TeachersModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(TeachersModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
