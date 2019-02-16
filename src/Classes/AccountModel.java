package Classes;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class AccountModel {
    private Connection dbConnection;
    private PreparedStatement PS;
    public javax.swing.JCheckBox demoCheckBox;
    public javax.swing.JTextField demoTextFieldOfCheck;
    public javax.swing.JTextField demoTextFieldOfTotal;
    private Document paymentVoucher;
    
    public String Id, TeaEmpId, Date, Name, Position_Designation, StudentId, AdmissionFormFee, ProfessionTax,
            AdmissionFee, MonthlyFee, SemesterFee, YearlyFee, IdCardFee, UniformFee, MonthName,
            ScoutFee, SportsFee, PenaltyFee, PicnicFee, StudyTourFee, CeremonyFee, YearName, TSDIT, TotalDeduction,
            TransferFee, ExamFee, ExamCenterFee, BoardFee, CharacterCertificateFee, BasicDa, HRA, NetSalary,
            TransferCertificateFee, TranscriptsFee, MainCertificateFee, TotalAmount, ConveyanceAllowance,
            ExpenseType, PaymentMode, StaffId, StaffName, StaffAddress, StaffContactNo, SalaryAmount,
            BillOf, BillTo, UnitAmount, Quantity, ItemLists, PaymentDate, TotalEarning, ProvidentFund, ESI, Loan,
            DueAmount, AccountNumber, AccountName, BankName, BranchName, SwiftCode, City, Country, SearchBoxId, CopyOf;
    
    public byte[] PhotographInByte;
    public Icon PhotographInIcon;
    
    public AccountModel() throws SQLException {
        dbConnection = Classes.DatabaseConnection.invokeJDBC();
    }
    
    public void InitiateCheckBoxCommand() {
        if(demoCheckBox.isSelected()) {
            demoTextFieldOfCheck.setEnabled(true);
            demoTextFieldOfCheck.setEditable(true);
        } else {
            if(demoTextFieldOfCheck.getText().equals("")) {
                demoTextFieldOfCheck.setEnabled(false);
            } else {
                if(demoTextFieldOfTotal.getText().equals("")) {
                    demoTextFieldOfCheck.setEnabled(false);
                } else {
                    if(Double.parseDouble(demoTextFieldOfTotal.getText()) > Double.parseDouble(demoTextFieldOfCheck.getText())) {
                        double totalAmount = Double.parseDouble(demoTextFieldOfTotal.getText());
                        totalAmount -= Double.parseDouble(demoTextFieldOfCheck.getText());
                        demoTextFieldOfTotal.setText(String.valueOf(totalAmount));
                    } else {
                        double admissionFormFee = Double.parseDouble(demoTextFieldOfCheck.getText());
                        admissionFormFee -= Double.parseDouble(demoTextFieldOfTotal.getText());
                        demoTextFieldOfTotal.setText(String.valueOf(admissionFormFee));
                    }
                    demoTextFieldOfCheck.setEnabled(false);
                }
            }
            demoTextFieldOfCheck.setText("");
        }
    }
    
    public void InitiateTextFieldCommand() {
        if(!demoTextFieldOfCheck.getText().equals("")) {
            String feeAmount = demoTextFieldOfCheck.getText();
            if(demoTextFieldOfTotal.getText().equals("")) {
                demoTextFieldOfTotal.setText(feeAmount);
            } else {
                double totalAmount = Double.parseDouble(demoTextFieldOfTotal.getText());
                totalAmount += Double.parseDouble(feeAmount);
                demoTextFieldOfTotal.setText(String.valueOf(totalAmount));
            }
            demoTextFieldOfCheck.setEditable(false);
        } else {
            JOptionPane.showMessageDialog(null, "NO VALUE FOUND !!!");
        }
    }
    
    public ArrayList<EmployeeModel> GetAllDetailsOfEmployees() {
        ArrayList<EmployeeModel> employeeLists = new ArrayList<>();
        try {
            PS = dbConnection.prepareStatement("SELECT * FROM EMPLOYEEDATASET");
            ResultSet getDbData = PS.executeQuery();
            
            while (getDbData.next()) {
                EmployeeModel empMod = new EmployeeModel();
                
                empMod.Id = getDbData.getString("EMPID");
                empMod.Name = getDbData.getString("EMPNAME");
                /* -------------------I M A G E------------------- */
                empMod.PhotographInByte = getDbData.getBytes("EMPPHOTO");
                empMod.PhotographInIcon = new ImageIcon(empMod.PhotographInByte);
                /* ----------------------------------------------- */
                empMod.Position = getDbData.getString("EMPPOSITION");
                empMod.ParmanentAddr = getDbData.getString("EMPPADDRESS");
                empMod.TemporaryAddr = getDbData.getString("EMPTADDRESS");
                empMod.DateOfBirth = getDbData.getString("EMPDATEOFBIRTH");
                empMod.Gender = getDbData.getString("EMPGENDER");
                empMod.BloodGroup = getDbData.getString("EMPBLOODGROUP");
                empMod.Religion = getDbData.getString("EMPRELIGION");
                empMod.Nationality = getDbData.getString("EMPNATIONALITY");
                empMod.EduQualification = getDbData.getString("EMPEDUQUALIFICATION");
                empMod.OtherQualification = getDbData.getString("EMPOTHERQUALIFICATION");
                empMod.ContactNo = getDbData.getString("EMPCONTACTNO");
                empMod.EmergencyContactNo = getDbData.getString("EMPECONTACTNO");
                
                employeeLists.add(empMod);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employeeLists;
    }
    
    public String StudentFeesValidityCheck() {
        if ("".equals(Id) || "".equals(Date) || "<Select Employee>".equals(Name) || "".equals(StudentId)
                || "".equals(AdmissionFormFee) || "".equals(AdmissionFee) || "".equals(MonthlyFee)
                || "".equals(SemesterFee) || "".equals(YearlyFee) || "".equals(IdCardFee) || "".equals(UniformFee)
                || "".equals(ScoutFee) || "".equals(SportsFee) || "".equals(PenaltyFee) || "".equals(PicnicFee)
                || "".equals(StudyTourFee) || "".equals(CeremonyFee) || "".equals(TransferFee) || "".equals(ExamFee)
                || "".equals(ExamCenterFee) || "".equals(BoardFee) || "".equals(CharacterCertificateFee)
                || "".equals(TransferCertificateFee) || "".equals(TranscriptsFee) || "".equals(MainCertificateFee)
                || "".equals(TotalAmount)) {
            return "?!? problems have been found during validation ?!?".toUpperCase();
        } else {
            return "Checked!!!";
        }
    }
    
    public String SaveStudentPaymentAccount(String dmlSave) {
        String returnStatement = null;
        try {
            PS = dbConnection.prepareStatement(dmlSave);
            PS.setString(1, Id);
            PS.setString(2, Date);
            PS.setString(3, Name);
            PS.setString(4, StudentId);
            PS.setString(5, AdmissionFormFee);
            PS.setString(6, AdmissionFee);
            PS.setString(7, MonthlyFee);
            PS.setString(8, SemesterFee);
            PS.setString(9, YearlyFee);
            PS.setString(10, IdCardFee);
            PS.setString(11, UniformFee);
            PS.setString(12, ScoutFee);
            PS.setString(13, SportsFee);
            PS.setString(14, PenaltyFee);
            PS.setString(15, PicnicFee);
            PS.setString(16, StudyTourFee);
            PS.setString(17, CeremonyFee);
            PS.setString(18, TransferFee);
            PS.setString(19, ExamFee);
            PS.setString(20, ExamCenterFee);
            PS.setString(21, BoardFee);
            PS.setString(22, CharacterCertificateFee);
            PS.setString(23, TransferCertificateFee);
            PS.setString(24, TranscriptsFee);
            PS.setString(25, MainCertificateFee);
            PS.setString(26, TotalAmount);
            
            int executeNotification = PS.executeUpdate();
            if (executeNotification != 0) {
                returnStatement = "CONGRATULATION! GIVEN PAYMENT DETAILS ARE IN DATABASE.";
            } else {
                returnStatement = "FAILED? SOMEHOW DIDN'T REACH IN DATABASE.";
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnStatement;
    }
    
    public String SearchStudentPaymentAccount(String querySerach) {
        try {
            PS = dbConnection.prepareStatement(querySerach);
            PS.setString(1, SearchBoxId);
            ResultSet getDbData = PS.executeQuery();
            
            while (getDbData.next()) {
                Id = getDbData.getString("PAYID");
                Date = getDbData.getString("PAYDATE");
                Name = getDbData.getString("PAYAUTHORIZE");
                StudentId = getDbData.getString("PAYSTUID");
                AdmissionFormFee = getDbData.getString("PAYADMISSIONFORMFEE");
                AdmissionFee = getDbData.getString("PAYADMISSIONFEE");
                MonthlyFee = getDbData.getString("PAYMONTHFEE");
                SemesterFee = getDbData.getString("PAYSEMESTERFEE");
                YearlyFee = getDbData.getString("PAYYEARFEE");
                IdCardFee = getDbData.getString("PAYIDCARDFEE");
                UniformFee = getDbData.getString("PAYUNIFORMFEE");
                ScoutFee = getDbData.getString("PAYSCOUTFEE");
                SportsFee = getDbData.getString("PAYSPORTSFEE");
                PenaltyFee = getDbData.getString("PAYPENALTYFEE");
                PicnicFee = getDbData.getString("PAYPICNICFEE");
                StudyTourFee = getDbData.getString("PAYSTUDYTOUR");
                CeremonyFee = getDbData.getString("PAYCEREMONY");
                TransferFee = getDbData.getString("PAYTRANSFERFEE");
                ExamFee = getDbData.getString("PAYEXAMFEE");
                ExamCenterFee = getDbData.getString("PAYEXAMCENTERFEE");
                BoardFee = getDbData.getString("PAYBOARDFEE");
                CharacterCertificateFee = getDbData.getString("PAYCCFEE");
                TransferCertificateFee = getDbData.getString("PAYTCFEE");
                TranscriptsFee = getDbData.getString("PAYTRANSCRIPTSFEE");
                MainCertificateFee = getDbData.getString("PAYMCFEE");
                TotalAmount = getDbData.getString("PAYTOTALAMOUNT");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "All Retrive";
    }
    
    public String UpdateStudentPaymentAccount(String dmlUpdate) {
        String returnStatement = null;
        try {
            PS = dbConnection.prepareStatement(dmlUpdate);
            PS.setString(1, Id);
            PS.setString(2, Date);
            PS.setString(3, Name);
            PS.setString(4, StudentId);
            PS.setString(5, AdmissionFormFee);
            PS.setString(6, AdmissionFee);
            PS.setString(7, MonthlyFee);
            PS.setString(8, SemesterFee);
            PS.setString(9, YearlyFee);
            PS.setString(10, IdCardFee);
            PS.setString(11, UniformFee);
            PS.setString(12, ScoutFee);
            PS.setString(13, SportsFee);
            PS.setString(14, PenaltyFee);
            PS.setString(15, PicnicFee);
            PS.setString(16, StudyTourFee);
            PS.setString(17, CeremonyFee);
            PS.setString(18, TransferFee);
            PS.setString(19, ExamFee);
            PS.setString(20, ExamCenterFee);
            PS.setString(21, BoardFee);
            PS.setString(22, CharacterCertificateFee);
            PS.setString(23, TransferCertificateFee);
            PS.setString(24, TranscriptsFee);
            PS.setString(25, MainCertificateFee);
            PS.setString(26, TotalAmount);
            
            PS.setString(27, SearchBoxId);
            
            int executeNotification = PS.executeUpdate();
            if (executeNotification != 0) {
                returnStatement = "CONGRATULATION! EDITED PAYMENT DETAILS UPDATED TO DATABASE.";
            } else {
                returnStatement = "FAILED? PROBLEM IN UPDATING PROCESS.";
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnStatement;
    }
    
    public String DeleteStudentPaymentAccount(String dmlDelete) {
        String returnStatement = null;
        try {
            PS = dbConnection.prepareStatement(dmlDelete);
            PS.setString(1, SearchBoxId);
            
            int executeNotification = PS.executeUpdate();
            if (executeNotification != 0) {
                returnStatement = "CONGRATULATION! SELECTED PAYMENT DETAILS VANISHED FROM DATABASE.";
            } else {
                returnStatement = "FAILED? IT'S STILL IN DATABASE.";
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnStatement;
    }
    
    private void WritingPaymentSlip(String[] studentData, String[][] paymentData) {
        try {
            Image img = Image.getInstance("src\\Images\\School Logo Small.png");
            img.setAlignment(Image.UNDERLYING);
            Paragraph paymentId = new Paragraph(Id, new Font(Font.COURIER, 10, Font.BOLD, new Color(255, 0, 0)));
            paymentId.setAlignment(Paragraph.ALIGN_RIGHT);
            
            String separator = "-----------------------------------------------"
                    + "--------------------------------------------------------"
                    + "-----------------------------------------------------\n";
            String paragraphPart_1 = "NAME OF THE SCHOOL\nSchool address goes here\nCity, State, Zip code\n"
                    + "Tel: 122-455-7788 | Fax: 444-555-6666\nEmail: example@email.com\n";
            Paragraph schoolNameAndAddress = new Paragraph(paragraphPart_1, new Font(Font.TIMES_ROMAN, 9, Font.BOLD, new Color(0, 0, 0)));
            schoolNameAndAddress.setAlignment(Paragraph.ALIGN_RIGHT);
            Paragraph copyName = new Paragraph(CopyOf, new Font(Font.COURIER, 8, Font.BOLD, new Color(0, 0, 255)));
            copyName.setAlignment(Paragraph.ALIGN_LEFT);
            String paragraphPart_2 = "STUDENT PAYMENT VOUCHER\n";
            Paragraph paymentTitle = new Paragraph(separator + separator + paragraphPart_2 + separator, new Font(Font.TIMES_ROMAN, 10, Font.BOLD, new Color(0, 0, 0)));
            paymentTitle.setAlignment(Paragraph.ALIGN_CENTER);
            String paragraphPart_3 = "STUDENT INFORMATION\n";
            Paragraph studentInformationTitle = new Paragraph(paragraphPart_3, new Font(Font.TIMES_ROMAN, 10, Font.BOLD, new Color(0, 0, 0)));
            studentInformationTitle.setAlignment(Paragraph.ALIGN_CENTER);
            String paragraphPart_4 = "Id: " + studentData[0] + " | Name: " + studentData[1] + " | Class: "
                    + studentData[2] + " | Section: " + studentData[3] + " | Major: " + studentData[4];
            Paragraph studentInformation = new Paragraph(paragraphPart_4 + "\n", new Font(Font.COURIER, 10, Font.BOLD, new Color(0, 0, 0)));
            studentInformation.setAlignment(Paragraph.ALIGN_CENTER);
            String paragraphPart_5 = "PAYMENT INFORMATION\n\n";
            Paragraph paymentInformationTitle = new Paragraph(separator + paragraphPart_5, new Font(Font.TIMES_ROMAN, 10, Font.BOLD, new Color(0, 0, 0)));
            paymentInformationTitle.setAlignment(Paragraph.ALIGN_CENTER);
            
            /* ----------------------------Table------------------------------*/
            PdfPTable dataTable = new PdfPTable(3);
            dataTable.setWidthPercentage(100f);
            float[] columnWidths = {0.4f, 2f, 2f};
            dataTable.setWidths(columnWidths);
            /* -------------------------Table Header--------------------------*/
            String[] hearderNames = new String[]{"#SL", "Payment Of", "Payment Amount"};
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
            for (int i = 0; i < 21; i++) {
                if (paymentData[0][i] != null) {
                    dataCell = new PdfPCell(new Phrase(++serialNum + "", new Font(Font.NORMAL, 10)));
                    dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    dataTable.addCell(dataCell);
                    
                    dataCell = new PdfPCell(new Phrase(paymentData[0][i], new Font(Font.NORMAL, 10)));
                    dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    dataTable.addCell(dataCell);
                    
                    dataCell = new PdfPCell(new Phrase(paymentData[1][i], new Font(Font.NORMAL, 10)));
                    dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    dataTable.addCell(dataCell);
                }
            }
            /* ---------------------------------------------------------------*/
            Paragraph totalAmountInformation = new Paragraph(paymentData[0][21] + " = " + paymentData[1][21] + " taka.\n\n\n",
                    new Font(Font.COURIER, 11, Font.BOLD, new Color(255, 0, 0)));
            totalAmountInformation.setAlignment(Paragraph.ALIGN_RIGHT);
            String tabSpace = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"
                    + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"
                    + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"
                    + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"
                    + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"
                    + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
            Paragraph authorizeBySignature = new Paragraph("(" + Name + ")" + tabSpace + "(" + studentData[1] + ")",
                    new Font(Font.TIMES_ROMAN, 9, Font.COURIER, new Color(0, 0, 0)));
            authorizeBySignature.setAlignment(Paragraph.ALIGN_MIDDLE);
            
            paymentVoucher.add(img);
            paymentVoucher.add(paymentId);
            paymentVoucher.add(schoolNameAndAddress);
            paymentVoucher.add(copyName);
            paymentVoucher.add(paymentTitle);
            paymentVoucher.add(studentInformationTitle);
            paymentVoucher.add(studentInformation);
            paymentVoucher.add(paymentInformationTitle);
            paymentVoucher.add(headerTable);
            paymentVoucher.add(dataTable);
            paymentVoucher.add(totalAmountInformation);
            paymentVoucher.add(authorizeBySignature);
        } catch (BadElementException ex) {
            Logger.getLogger(AccountModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException | IOException ex) {
            Logger.getLogger(AccountModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String DocumentForStudentPaymentSlip(String[] studentData, String[][] paymentData) {
        String fileName = "";
        for (int copy = 1; copy <= 2; copy++) {
            if (copy == 1) {
                try {
                    Rectangle customPageSize = new Rectangle((float) 595.44, (float) 1310);
                    paymentVoucher = new Document(customPageSize);
                    fileName = "reportFiles\\" + Id + "_PaySlip.pdf";
                    PdfWriter paymentSlipWriter = PdfWriter.getInstance(paymentVoucher, new FileOutputStream(fileName));
                    paymentVoucher.open();
                    CopyOf = "Office_Copy\n";
                    WritingPaymentSlip(studentData, paymentData);
                } catch (DocumentException | FileNotFoundException ex) {
                    Logger.getLogger(AccountModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    String separator = "---------------------------------------"
                            + "------------------------------------------------";
                    Paragraph devidor = new Paragraph(separator + "\n", new Font(Font.COURIER, 10, Font.BOLD, new Color(0, 0, 0)));
                    paymentVoucher.add(devidor);
                    CopyOf = "Student_Copy\n";
                    WritingPaymentSlip(studentData, paymentData);
                    paymentVoucher.close();
                } catch (DocumentException ex) {
                    Logger.getLogger(AccountModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return fileName;
    }
    
    public String StaffAccessoryBillValidityCheck() {
        String notification = "";
        if ("".equals(Id) || "".equals(Date) || "<Select Employee>".equals(Name) || "".equals(ExpenseType) || "".equals(PaymentMode)) {
            if(!"".equals(StaffId)) {
                if("".equals(StaffName) || "".equals(StaffAddress) || "".equals(StaffContactNo) || PhotographInByte == null) {
                    notification = "?!? YOU HAVE MISSED SOMETIME ON \"SALARY BILL DETAILS\" ?!?";
                }
            } else if(!"".equals(BillOf)) {
                if("".equals(BillTo) || "".equals(UnitAmount) || "".equals(Quantity) || "".equals(ItemLists) || "".equals(TotalAmount)) {
                    notification = "?!? YOU HAVE MISSED SOMETIME ON \"ACCESSORY BILL DETAILS\" ?!?";
                }
            } else if (!"".equals(PaymentDate)) {
                if("".equals(DueAmount)) {
                    notification = "?!? YOU HAVE MISSED SOMETIME ON \"Case Payment Details\" ?!?";
                }
            } else if (!"".equals(PaymentDate)) {
                if("".equals(AccountNumber) || "".equals(AccountName) || "".equals(BankName) || "".equals(BranchName)) {
                    notification = "?!? YOU HAVE MISSED SOMETIME ON \"Cheque Payment Details\" ?!?";
                }
            } else if (!"".equals(PaymentDate)) {
                if("".equals(AccountNumber) || "".equals(AccountName) || "".equals(BankName) || "".equals(BranchName) || "".equals(SwiftCode) || "".equals(City) || "".equals(Country)) {
                    notification = "?!? YOU HAVE MISSED SOMETIME ON \"Online Payment Details\" ?!?";
                }
            } else {
                notification = "?!? PLEASE CHECK BELOW FILEDS AGIAN -\n1: STAFF ID\n2: BILL OF\n3: CASE PAY DATE\n4: CHEQUE PAY DATE\n5: ONLINE PAY DAT";
            }
        } else {
            notification = "Checked!!!";
        }
        return notification;
    }
    
    public String SaveStaffAccessoryBill(String sqlString) {
        String returnStatement = null;
        try {
            PS = dbConnection.prepareStatement(sqlString);
            PS.setString(1, Id);
            PS.setString(2, Date);
            PS.setString(3, Name);
            PS.setString(4, ExpenseType);
            PS.setString(5, PaymentMode);
            PS.setString(6, StaffId);
            PS.setString(7, StaffName);
            PS.setBytes(8, PhotographInByte);
            PS.setString(9, StaffAddress);
            PS.setString(10, StaffContactNo);
            PS.setString(11, SalaryAmount);
            PS.setString(12, BillOf);
            PS.setString(13, BillTo);
            PS.setString(14, UnitAmount);
            PS.setString(15, Quantity);
            PS.setString(16, ItemLists);
            PS.setString(17, TotalAmount);
            PS.setString(18, PaymentDate);
            PS.setString(19, DueAmount);
            PS.setString(20, AccountNumber);
            PS.setString(21, AccountName);
            PS.setString(22, BankName);
            PS.setString(23, BranchName);
            PS.setString(24, SwiftCode);
            PS.setString(25, City);
            PS.setString(26, Country);
            
            int executeNotification = PS.executeUpdate();
            if (executeNotification != 0) {
                returnStatement = "CONGRATULATION! GIVEN PAYMENT DETAILS ARE IN DATABASE.";
            } else {
                returnStatement = "FAILED? SOMEHOW DIDN'T REACH IN DATABASE.";
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnStatement;
    }
    
    public String SearchStaffAccessoryBill(String querySerach) {
        try {
            PS = dbConnection.prepareStatement(querySerach);
            PS.setString(1, SearchBoxId);
            ResultSet getDbData = PS.executeQuery();
            
            while (getDbData.next()) {
                Id = getDbData.getString("BILID");
                Date = getDbData.getString("BILDATE");
                Name = getDbData.getString("BILAUTHORIZED");
                ExpenseType = getDbData.getString("BILEXPENSE");
                PaymentMode = getDbData.getString("BILPAYMODE");
                StaffId = getDbData.getString("BILSTAFFID");
                StaffName = getDbData.getString("BILSTAFFNAME");
                /* -------------------I M A G E------------------- */
                PhotographInByte = getDbData.getBytes("BILSTAFFPHOTO");
                if(PhotographInByte == null) {
                    PhotographInByte = "204.29.207.217".getBytes();
                } else {
                    PhotographInIcon = new ImageIcon(PhotographInByte);
                }
                /* ----------------------------------------------- */
                StaffAddress = getDbData.getString("BILSTAFFADDR");
                StaffContactNo = getDbData.getString("BILSTAFFCONTACT");
                SalaryAmount = getDbData.getString("BILSTAFFSALARY");
                BillOf = getDbData.getString("BILBILLOF");
                BillTo = getDbData.getString("BILBILLTO");
                UnitAmount = getDbData.getString("BILUNTAMOUNT");
                Quantity = getDbData.getString("BILQUANTITY");
                ItemLists = getDbData.getString("BILITEMS");
                TotalAmount = getDbData.getString("BILTOTALAMOUNT");
                PaymentDate = getDbData.getString("BILPAYDATE");
                DueAmount = getDbData.getString("BILPAYDUE");
                AccountNumber = getDbData.getString("BILACCNUMBER");
                AccountName = getDbData.getString("BILACCNAME");
                BankName = getDbData.getString("BILBNKNAME");
                BranchName = getDbData.getString("BILBRNCNAME");
                SwiftCode = getDbData.getString("BILSWIFTCODE");
                City = getDbData.getString("BILCITY");
                Country = getDbData.getString("BILCOUNTRY");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "All Retrive";
    }
    
    public String UpdateStaffAccessoryBill(String sqlString) {
        String returnStatement = null;
        try {
            PS = dbConnection.prepareStatement(sqlString);
            PS.setString(1, Id);
            PS.setString(2, Date);
            PS.setString(3, Name);
            PS.setString(4, ExpenseType);
            PS.setString(5, PaymentMode);
            PS.setString(6, StaffId);
            PS.setString(7, StaffName);
            PS.setBytes(8, PhotographInByte);
            PS.setString(9, StaffAddress);
            PS.setString(10, StaffContactNo);
            PS.setString(11, SalaryAmount);
            PS.setString(12, BillOf);
            PS.setString(13, BillTo);
            PS.setString(14, UnitAmount);
            PS.setString(15, Quantity);
            PS.setString(16, ItemLists);
            PS.setString(17, TotalAmount);
            PS.setString(18, PaymentDate);
            PS.setString(19, DueAmount);
            PS.setString(20, AccountNumber);
            PS.setString(21, AccountName);
            PS.setString(22, BankName);
            PS.setString(23, BranchName);
            PS.setString(24, SwiftCode);
            PS.setString(25, City);
            PS.setString(26, Country);
            
            PS.setString(27, SearchBoxId);
            
            int executeNotification = PS.executeUpdate();
            if (executeNotification != 0) {
                returnStatement = "CONGRATULATION! EDITED EMPLOYEE PROFILE UPDATED TO DATABASE.";
            } else {
                returnStatement = "FAILED? SOMEHOW DIDN'T REACH IN DATABASE.";
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnStatement;
    }
    
    public String DeleteStaffAccessoryBill(String dmlDelete) {
        String returnStatement = null;
        try {
            PS = dbConnection.prepareStatement(dmlDelete);
            PS.setString(1, SearchBoxId);
            
            int executeNotification = PS.executeUpdate();
            if (executeNotification != 0) {
                returnStatement = "CONGRATULATION! SELECTED PAYMENT DETAILS VANISHED FROM DATABASE.";
            } else {
                returnStatement = "FAILED? IT'S STILL IN DATABASE.";
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnStatement;
    }
    
    public String SearchTeacherOrEmployee(String querySerach) {
        try {
            PS = dbConnection.prepareStatement(querySerach);
            PS.setString(1, SearchBoxId);
            ResultSet getDbData = PS.executeQuery();
            
            if(SearchBoxId.substring(0, 3).equals("EMP")) {
                while (getDbData.next()) {
                    Id = getDbData.getString("EMPID");
                    Name = getDbData.getString("EMPNAME");
                    Position_Designation = getDbData.getString("EMPPOSITION");
                }
            } else if (SearchBoxId.substring(0, 3).equals("TEA")) {
                while (getDbData.next()) {
                    Id = getDbData.getString("TEAID");
                    Name = getDbData.getString("TEANAME");
                    Position_Designation = getDbData.getString("TEADESIGNATION");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Information Retrive";
    }
    
    public String SalaryVoucherValidityCheck() {
        String notification = "";
        if ("".equals(Id) || "".equals(Date) || "<Select Employee>".equals(Name) || "".equals(TeaEmpId) || "".equals(MonthName)
                || "".equals(YearName) || "".equals(BasicDa) || "".equals(HRA) || "".equals(ConveyanceAllowance) || "".equals(TotalEarning)
                || "".equals(ProvidentFund) || "".equals(ESI) || "".equals(Loan) || "".equals(ProfessionTax) || "".equals(TSDIT)
                || "".equals(TotalDeduction) || "".equals(NetSalary)) {
            notification = "?!? YOU HAVE MISSED SOMETIME ON \"Salary Voucher Form\" ?!?";
        } else {
            notification = "Checked!!!";
        }
        return notification;
    }
    
    public String SaveUpdateSalaryVoucher(String sqlString, String cMDNotify) {
        String returnStatement = null;
        try {
            PS = dbConnection.prepareStatement(sqlString);
            PS.setString(1, Id);
            PS.setString(2, Date);
            PS.setString(3, TeaEmpId);
            PS.setString(4, MonthName);
            PS.setString(5, YearName);
            PS.setString(6, BasicDa);
            PS.setString(7, HRA);
            PS.setString(8, ConveyanceAllowance);
            PS.setString(9, TotalEarning);
            PS.setString(10, ProvidentFund);
            PS.setString(11, ESI);
            PS.setString(12, Loan);
            PS.setString(13, ProfessionTax);
            PS.setString(14, TSDIT);
            PS.setString(15, TotalDeduction);
            PS.setString(16, NetSalary);
            if("Update".equals(cMDNotify)) {
                PS.setString(17, SearchBoxId);
            }
            
            int executeNotification = PS.executeUpdate();
            if (executeNotification != 0) {
                if("Update".equals(cMDNotify)) {
                    returnStatement = "CONGRATULATION! SALARY VOUCHER DETAILS UPDATED ON DATABASE.";
                } else {
                    returnStatement = "CONGRATULATION! SALARY VOUCHER DETAILS ARE IN DATABASE.";
                }
            } else {
                returnStatement = "FAILED? SOMEHOW DIDN'T REACH IN DATABASE.";
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnStatement;
    }
    
    public String DeleteSalaryVoucher(String dmlDelete) {
        String returnStatement = null;
        try {
            PS = dbConnection.prepareStatement(dmlDelete);
            PS.setString(1, SearchBoxId);
            
            int executeNotification = PS.executeUpdate();
            if (executeNotification != 0) {
                returnStatement = "CONGRATULATION! SELECTED SALARY DETAILS VANISHED FROM DATABASE.";
            } else {
                returnStatement = "FAILED? IT'S STILL IN DATABASE.";
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnStatement;
    }
    
    public String SearchSalaryVoucher(String querySerach) {
        try {
            PS = dbConnection.prepareStatement(querySerach);
            PS.setString(1, SearchBoxId);
            ResultSet getDbData = PS.executeQuery();
            
            while (getDbData.next()) {
                Id = getDbData.getString("SALID");
                Date = getDbData.getString("SALDATE");
                TeaEmpId = getDbData.getString("SALTEAEMPID");
                MonthName = getDbData.getString("SALMONTH");
                YearName = getDbData.getString("SALYEAR");
                BasicDa = getDbData.getString("SALBASICDA");
                HRA = getDbData.getString("SALHRA");
                ConveyanceAllowance = getDbData.getString("SALCA");
                TotalEarning = getDbData.getString("SALTOTALEARN");
                ProvidentFund = getDbData.getString("SALFUND");
                ESI = getDbData.getString("SALESI");
                Loan = getDbData.getString("SALLOAN");
                ProfessionTax = getDbData.getString("SALPROFTAX");
                TSDIT = getDbData.getString("SALTSDIT");
                TotalDeduction = getDbData.getString("SALTOTALDEDUCT");
                NetSalary = getDbData.getString("SALNETSALARY");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "All Retrive";
    }
}
