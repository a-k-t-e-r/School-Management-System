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

public class EmployeeModel {
    Connection dbConnection;
    PreparedStatement PS;

    public String Id, Name, Position, ParmanentAddr, TemporaryAddr, DateOfBirth,
            Gender, BloodGroup, Religion, Nationality, EduQualification,
            OtherQualification, ContactNo, EmergencyContactNo, SearchId;
    public byte[] PhotographInByte;
    public Icon PhotographInIcon;
    
    public DefaultTableModel searchTableModel;
    private Document employeeReport;
    
    public EmployeeModel() throws SQLException {
        dbConnection = Classes.DatabaseConnection.invokeJDBC();
    }
    
    public String ValidityCheck() {
        if ("".equals(Id) || "".equals(Name) || PhotographInByte == null
                || "".equals(Position) || "".equals(ParmanentAddr)
                || "".equals(TemporaryAddr) || "".equals(DateOfBirth)
                || "".equals(Gender) || "".equals(BloodGroup) || "".equals(Religion)
                || "".equals(Nationality) || "".equals(EduQualification)
                || "".equals(OtherQualification) || "".equals(ContactNo) || "".equals(EmergencyContactNo)) {
            return "?!? problems have been found during validation ?!?".toUpperCase();
        } else {
            return "Checked!!!";
        }
    }
    
    public String SaveEmpProfile(String dmlSave) {
        String returnStatement = null;
        try {
            PS = dbConnection.prepareStatement(dmlSave);
            PS.setString(1, Id);
            PS.setString(2, Name);
            PS.setBytes(3, PhotographInByte);
            PS.setString(4, Position);
            PS.setString(5, ParmanentAddr);
            PS.setString(6, TemporaryAddr);
            PS.setString(7, DateOfBirth);
            PS.setString(8, Gender);
            PS.setString(9, BloodGroup);
            PS.setString(10, Religion);
            PS.setString(11, Nationality);
            PS.setString(12, EduQualification);
            PS.setString(13, OtherQualification);
            PS.setString(14, ContactNo);
            PS.setString(15, EmergencyContactNo);
            
            int executeNotification = PS.executeUpdate();
            if (executeNotification != 0) {
                returnStatement = "CONGRATULATION! GIVEN EMPLOYEE PROFILE DETAILS ARE IN DATABASE.";
            } else {
                returnStatement = "FAILED? SOMEHOW DIDN'T REACH IN DATABASE.";
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnStatement;
    }
    
    public String SearchEmpProfile(String querySerach) {
        try {
            PS = dbConnection.prepareStatement(querySerach);
            PS.setString(1, SearchId);
            ResultSet getDbData = PS.executeQuery();
            
            while (getDbData.next()) {
                Id = getDbData.getString("EMPID");
                Name = getDbData.getString("EMPNAME");
                /* -------------------I M A G E------------------- */
                PhotographInByte = getDbData.getBytes("EMPPHOTO");
                PhotographInIcon = new ImageIcon(PhotographInByte);
                /* ----------------------------------------------- */
                Position = getDbData.getString("EMPPOSITION");
                ParmanentAddr = getDbData.getString("EMPPADDRESS");
                TemporaryAddr = getDbData.getString("EMPTADDRESS");
                DateOfBirth = getDbData.getString("EMPDATEOFBIRTH");
                Gender = getDbData.getString("EMPGENDER");
                BloodGroup = getDbData.getString("EMPBLOODGROUP");
                Religion = getDbData.getString("EMPRELIGION");
                Nationality = getDbData.getString("EMPNATIONALITY");
                EduQualification = getDbData.getString("EMPEDUQUALIFICATION");
                OtherQualification = getDbData.getString("EMPOTHERQUALIFICATION");
                ContactNo = getDbData.getString("EMPCONTACTNO");
                EmergencyContactNo = getDbData.getString("EMPECONTACTNO");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "All Retrive";
    }
    
    public String UpdateEmpProfile(String dmlUpdate) {
        String returnStatement = null;
        try {
            PS = dbConnection.prepareStatement(dmlUpdate);
            PS.setString(1, Id);
            PS.setString(2, Name);
            PS.setBytes(3, PhotographInByte);
            PS.setString(4, Position);
            PS.setString(5, ParmanentAddr);
            PS.setString(6, TemporaryAddr);
            PS.setString(7, DateOfBirth);
            PS.setString(8, Gender);
            PS.setString(9, BloodGroup);
            PS.setString(10, Religion);
            PS.setString(11, Nationality);
            PS.setString(12, EduQualification);
            PS.setString(13, OtherQualification);
            PS.setString(14, ContactNo);
            PS.setString(15, EmergencyContactNo);
            
            PS.setString(16, SearchId);
            
            int executeNotification = PS.executeUpdate();
            if (executeNotification != 0) {
                returnStatement = "CONGRATULATION! EDITED EMPLOYEE PROFILE UPDATED TO DATABASE.";
            } else {
                returnStatement = "FAILED? PROBLEM IN UPDATING PROCESS.";
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnStatement;
    }
    
    public String DeleteEmpProfile(String dmlDelete) {
        String returnStatement = null;
        try {
            PS = dbConnection.prepareStatement(dmlDelete);
            PS.setString(1, SearchId);
            
            int executeNotification = PS.executeUpdate();
            if (executeNotification != 0) {
                returnStatement = "CONGRATULATION! SELECTED EMPLOYEE PROFILE VANISHED FROM DATABASE.";
            } else {
                returnStatement = "FAILED? IT'S STILL IN DATABASE.";
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnStatement;
    }
    
    public String[][] SearchForEmployees(String sqlQuery, String stuId, String stuClass) {
        String[][] searchResultData = new String[100][6];
        try {
            PS = dbConnection.prepareStatement(sqlQuery);
            PS.setString(1, stuId);
            PS.setString(2, stuClass);
            ResultSet getDbData = PS.executeQuery();

            int a = 0;
            while (getDbData.next()) {
                searchResultData[a][0] = getDbData.getString("EMPID");
                searchResultData[a][1] = getDbData.getString("EMPNAME");
                searchResultData[a][2] = getDbData.getString("EMPPOSITION");
                searchResultData[a][3] = getDbData.getString("EMPEDUQUALIFICATION");
                searchResultData[a][4] = getDbData.getString("EMPCONTACTNO");
                searchResultData[a][5] = getDbData.getString("EMPCONTACTNO");

                searchTableModel.addRow(new Object[]{searchResultData[a][0], searchResultData[a][1],
                    searchResultData[a][2], searchResultData[a][3], searchResultData[a][4], searchResultData[a][5]});
                a++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return searchResultData;
    }
    
    public String SearchReportGenerates(String[][] searchOutcomes) {
        String fileLocation = "";
        for (int copy = 1; copy <= 2; copy++) {
            if (copy == 1) {
                try {
                    employeeReport = new Document(PageSize.A4);
                    fileLocation = "reportFiles\\employeeReport.pdf";
                    PdfWriter paymentSlipWriter = PdfWriter.getInstance(employeeReport, new FileOutputStream(fileLocation));
                    employeeReport.open();
                    WritingSearchReport(searchOutcomes);
                } catch (DocumentException | FileNotFoundException ex) {
                    Logger.getLogger(EmployeeModel.class.getName()).log(Level.SEVERE, null, ex);
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
            String paragraphPart_2 = "EMPLOYEE SEARCH REPORT\n";
            Paragraph reportTitle = new Paragraph(separator + separator + paragraphPart_2 + separator, new Font(Font.TIMES_ROMAN, 10, Font.BOLD, new Color(0, 0, 0)));
            reportTitle.setAlignment(Paragraph.ALIGN_CENTER);

            /* ----------------------------Table------------------------------*/
            PdfPTable dataTable = new PdfPTable(7);
            dataTable.setWidthPercentage(100f);
            float[] columnWidths = {0.3f, 0.7f, 0.8f, 0.8f, 0.8f, 0.7f, 0.8f};
            dataTable.setWidths(columnWidths);
            /* -------------------------Table Header--------------------------*/
            String[] hearderNames = new String[]{"#Sl", "Employee Id", "Employee Name", "Position", "Educational Qualification", "Contact No.", "Emergency Contact No."};
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

            employeeReport.add(img);
            employeeReport.add(schoolNameAndAddress);
            employeeReport.add(reportTitle);
            employeeReport.add(headerTable);
            employeeReport.add(dataTable);
            employeeReport.add(endTag);
            employeeReport.close();
        } catch (BadElementException | IOException ex) {
            Logger.getLogger(TeachersModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(TeachersModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
