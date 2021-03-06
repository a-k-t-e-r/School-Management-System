package UIs;

import Classes.AutomaticIdGenerator;
import Classes.LibraryBookModel;
import Classes.ProjectDesign;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class LibraryBookAdd extends javax.swing.JFrame {
    ProjectDesign pD;
    AutomaticIdGenerator autoId;
    LibraryBookModel profileModel;
    
    public LibraryBookAdd() throws SQLException {
        initComponents();
        
        this.autoId = new AutomaticIdGenerator();
        jT_BookId.setText("BOK-" + (autoId.IdGenerator("LibraryBookAdd")));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jP_background = new javax.swing.JPanel();
        jL_schoolLogo = new javax.swing.JLabel();
        jB_Clear = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jT_BookPublicationName = new javax.swing.JTextField();
        jT_BookAuthor = new javax.swing.JTextField();
        jT_BookId = new javax.swing.JTextField();
        jT_BookISBN = new javax.swing.JTextField();
        jT_BookTitle = new javax.swing.JTextField();
        jC_Edition = new javax.swing.JComboBox<>();
        jP_bookEntryDate = new javax.swing.JPanel();
        jDC_BookEntryDate = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jB_Store = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jB_Withdraw = new javax.swing.JButton();
        jT_searchBox = new javax.swing.JTextField();
        jB_Restore = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jL_status = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jP_background.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jP_background.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jP_backgroundMouseClicked(evt);
            }
        });

        jL_schoolLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/School Logo Small.png"))); // NOI18N

        jB_Clear.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jB_Clear.setForeground(new java.awt.Color(192, 0, 0));
        jB_Clear.setText("C L E A R");
        jB_Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_ClearActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Details About Entry Book", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 12), new java.awt.Color(192, 0, 0))); // NOI18N

        jT_BookPublicationName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jT_BookPublicationName.setForeground(new java.awt.Color(192, 0, 0));
        jT_BookPublicationName.setText("Enter Publication Name...");
        jT_BookPublicationName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jT_BookPublicationNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jT_BookPublicationNameFocusLost(evt);
            }
        });

        jT_BookAuthor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jT_BookAuthor.setForeground(new java.awt.Color(192, 0, 0));
        jT_BookAuthor.setText("Enter Book Author...");
        jT_BookAuthor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jT_BookAuthorFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jT_BookAuthorFocusLost(evt);
            }
        });

        jT_BookId.setEditable(false);
        jT_BookId.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jT_BookId.setForeground(new java.awt.Color(192, 0, 0));
        jT_BookId.setText("Book Id");

        jT_BookISBN.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jT_BookISBN.setForeground(new java.awt.Color(192, 0, 0));
        jT_BookISBN.setText("Enter ISBN Number...");
        jT_BookISBN.setToolTipText("International Standard Book Number (ISBN)");
        jT_BookISBN.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jT_BookISBNFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jT_BookISBNFocusLost(evt);
            }
        });

        jT_BookTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jT_BookTitle.setForeground(new java.awt.Color(192, 0, 0));
        jT_BookTitle.setText("Enter Book Title...");
        jT_BookTitle.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jT_BookTitleFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jT_BookTitleFocusLost(evt);
            }
        });

        jC_Edition.setBackground(new java.awt.Color(192, 0, 0));
        jC_Edition.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jC_Edition.setForeground(new java.awt.Color(192, 0, 0));
        jC_Edition.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<select edition>", "1st Edition", "2nd Edition", "3rd Edition", "4th Edition", "5th Edition", "6th Edition", "7th Edition", "8th Edition", "9th Edition", "10th Edition", "11th Edition", "12th Edition", "13th Edition", "14th Edition", "15th Edition", "16th Edition", "17th Edition", "18th Edition", "19th Edition", "20th Edition" }));
        jC_Edition.setToolTipText("numbering of book editions is a special case of the wider field of revision control");
        jC_Edition.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jC_EditionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jC_EditionFocusLost(evt);
            }
        });

        jP_bookEntryDate.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Date of Entry Day", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 12), new java.awt.Color(192, 0, 0))); // NOI18N

        jDC_BookEntryDate.setForeground(new java.awt.Color(192, 0, 0));
        jDC_BookEntryDate.setToolTipText("Entry Date");
        jDC_BookEntryDate.setDateFormatString("yyyy-MM-dd");
        jDC_BookEntryDate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(192, 0, 0));
        jLabel1.setText("Book Entry Date");

        javax.swing.GroupLayout jP_bookEntryDateLayout = new javax.swing.GroupLayout(jP_bookEntryDate);
        jP_bookEntryDate.setLayout(jP_bookEntryDateLayout);
        jP_bookEntryDateLayout.setHorizontalGroup(
            jP_bookEntryDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_bookEntryDateLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jP_bookEntryDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jP_bookEntryDateLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 191, Short.MAX_VALUE))
                    .addComponent(jDC_BookEntryDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jP_bookEntryDateLayout.setVerticalGroup(
            jP_bookEntryDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_bookEntryDateLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDC_BookEntryDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jT_BookAuthor)
                    .addComponent(jT_BookId, javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jT_BookTitle, javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jP_bookEntryDate, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jC_Edition, javax.swing.GroupLayout.Alignment.CENTER, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jT_BookPublicationName, javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jT_BookISBN, javax.swing.GroupLayout.Alignment.CENTER))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jT_BookId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jT_BookTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jP_bookEntryDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jT_BookAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jC_Edition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jT_BookPublicationName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jT_BookISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jB_Store.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jB_Store.setForeground(new java.awt.Color(192, 0, 0));
        jB_Store.setText("STORE");
        jB_Store.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_StoreActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Search To Make Further Decision", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 12), new java.awt.Color(192, 0, 0))); // NOI18N

        jB_Withdraw.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jB_Withdraw.setForeground(new java.awt.Color(192, 0, 0));
        jB_Withdraw.setText("WITHDRAW");
        jB_Withdraw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_WithdrawActionPerformed(evt);
            }
        });

        jT_searchBox.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jT_searchBox.setForeground(new java.awt.Color(192, 0, 0));
        jT_searchBox.setText("Search by Book Id...");
        jT_searchBox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jT_searchBoxFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jT_searchBoxFocusLost(evt);
            }
        });
        jT_searchBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jT_searchBoxKeyReleased(evt);
            }
        });

        jB_Restore.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jB_Restore.setForeground(new java.awt.Color(192, 0, 0));
        jB_Restore.setText("RE-STORE");
        jB_Restore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_RestoreActionPerformed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jT_searchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jB_Restore, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jB_Withdraw)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jT_searchBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jB_Restore)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jB_Withdraw)
                .addGap(5, 5, 5))
        );

        jL_status.setBackground(new java.awt.Color(0, 0, 0));
        jL_status.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jL_status.setForeground(new java.awt.Color(255, 0, 0));
        jL_status.setText("Status...");

        javax.swing.GroupLayout jP_backgroundLayout = new javax.swing.GroupLayout(jP_background);
        jP_background.setLayout(jP_backgroundLayout);
        jP_backgroundLayout.setHorizontalGroup(
            jP_backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_backgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jP_backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jP_backgroundLayout.createSequentialGroup()
                        .addGroup(jP_backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jB_Store, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jP_backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jP_backgroundLayout.createSequentialGroup()
                                .addGap(152, 152, 152)
                                .addComponent(jB_Clear))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jP_backgroundLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jP_backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jL_schoolLogo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jL_status))
                .addContainerGap())
        );
        jP_backgroundLayout.setVerticalGroup(
            jP_backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_backgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jP_backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jP_backgroundLayout.createSequentialGroup()
                        .addComponent(jL_schoolLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83)
                        .addComponent(jB_Clear))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jB_Store)
                .addGap(10, 10, 10)
                .addComponent(jL_status)
                .addGap(8, 8, 8))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jP_background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jP_background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void refreshThisForm() {
        jT_BookId.setText("BOK-" + (autoId.IdGenerator("LibraryBookAdd")));
        pD = new ProjectDesign();
        pD.phTextField = jT_BookTitle;
        pD.phTextField.setText("");
        pD.placeHolder("jTextField", "FL", "Enter Book Title...");
        ((JTextField) jDC_BookEntryDate.getDateEditor().getUiComponent()).setText("");
        pD.phTextField = jT_BookAuthor;
        pD.phTextField.setText("");
        pD.placeHolder("jTextField", "FL", "Enter Book Author...");
        pD.phComboBox = jC_Edition;
        pD.phComboBox.setSelectedItem("<select edition>");
        pD.placeHolder("jComboBox", "FL", "<select edition>");
        pD.phTextField = jT_BookPublicationName;
        pD.phTextField.setText("");
        pD.placeHolder("jTextField", "FL", "Enter Publication Name...");
        pD.phTextField = jT_BookISBN;
        pD.phTextField.setText("");
        pD.placeHolder("jTextField", "FL", "Enter ISBN Number...");
        pD.phTextField = jT_searchBox;
        pD.phTextField.setText("");
        pD.placeHolder("jTextField", "FL", "Search by Book Id...");
    }
    
    private void jT_BookTitleFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jT_BookTitleFocusGained
        pD = new ProjectDesign();
        pD.phTextField = jT_BookTitle;
        pD.placeHolder("jTextField", "FG", "Enter Book Title...");
    }//GEN-LAST:event_jT_BookTitleFocusGained

    private void jT_BookTitleFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jT_BookTitleFocusLost
        if (jT_BookTitle.getText().equals("")) {
            pD = new ProjectDesign();
            pD.phTextField = jT_BookTitle;
            pD.placeHolder("jTextField", "FL", "Enter Book Title...");
        }
    }//GEN-LAST:event_jT_BookTitleFocusLost

    private void jT_BookAuthorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jT_BookAuthorFocusGained
        pD = new ProjectDesign();
        pD.phTextField = jT_BookAuthor;
        pD.placeHolder("jTextField", "FG", "Enter Book Author...");
    }//GEN-LAST:event_jT_BookAuthorFocusGained

    private void jT_BookAuthorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jT_BookAuthorFocusLost
        if (jT_BookAuthor.getText().equals("")) {
            pD = new ProjectDesign();
            pD.phTextField = jT_BookAuthor;
            pD.placeHolder("jTextField", "FL", "Enter Book Author...");
        }
    }//GEN-LAST:event_jT_BookAuthorFocusLost

    private void jT_BookPublicationNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jT_BookPublicationNameFocusGained
        pD = new ProjectDesign();
        pD.phTextField = jT_BookPublicationName;
        pD.placeHolder("jTextField", "FG", "Enter Publication Name...");
    }//GEN-LAST:event_jT_BookPublicationNameFocusGained

    private void jT_BookPublicationNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jT_BookPublicationNameFocusLost
        if (jT_BookPublicationName.getText().equals("")) {
            pD = new ProjectDesign();
            pD.phTextField = jT_BookPublicationName;
            pD.placeHolder("jTextField", "FL", "Enter Publication Name...");
        }
    }//GEN-LAST:event_jT_BookPublicationNameFocusLost

    private void jT_BookISBNFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jT_BookISBNFocusGained
        pD = new ProjectDesign();
        pD.phTextField = jT_BookISBN;
        pD.placeHolder("jTextField", "FG", "Enter ISBN Number...");
    }//GEN-LAST:event_jT_BookISBNFocusGained

    private void jT_BookISBNFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jT_BookISBNFocusLost
        if (jT_BookISBN.getText().equals("")) {
            pD = new ProjectDesign();
            pD.phTextField = jT_BookISBN;
            pD.placeHolder("jTextField", "FL", "Enter ISBN Number...");
        }
    }//GEN-LAST:event_jT_BookISBNFocusLost

    private void jT_searchBoxFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jT_searchBoxFocusGained
        pD = new ProjectDesign();
        pD.phTextField = jT_searchBox;
        pD.placeHolder("jTextField", "FG", "Search by Book Id...");
    }//GEN-LAST:event_jT_searchBoxFocusGained

    private void jT_searchBoxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jT_searchBoxFocusLost
        if (jT_searchBox.getText().equals("")) {
            pD = new ProjectDesign();
            pD.phTextField = jT_searchBox;
            pD.placeHolder("jTextField", "FL", "Search by Book Id...");
        }
    }//GEN-LAST:event_jT_searchBoxFocusLost

    private void jC_EditionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jC_EditionFocusGained
        pD = new ProjectDesign();
        pD.phComboBox = jC_Edition;
        pD.placeHolder("jComboBox", "FG", "<select edition>");
    }//GEN-LAST:event_jC_EditionFocusGained

    private void jC_EditionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jC_EditionFocusLost
        pD = new ProjectDesign();
        pD.phComboBox = jC_Edition;
        pD.placeHolder("jComboBox", "FL", "<select edition>");
    }//GEN-LAST:event_jC_EditionFocusLost

    private void jB_StoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_StoreActionPerformed
        try {
            String sqlString = "INSERT INTO BOOKDATASET(BOKID, BOKNAME, BOKDATE, BOKAUTHOR,"
                    + "BOKEDITION, BOKPUBNAME, BOKISBN) VALUES(?,?,?,?,?,?,?)";
            profileModel = new LibraryBookModel();
            profileModel.Id = jT_BookId.getText().toUpperCase();
            profileModel.Name = jT_BookTitle.getText().toUpperCase();
            profileModel.EntryDate = ((JTextField) jDC_BookEntryDate.getDateEditor().getUiComponent()).getText().toUpperCase();
            profileModel.Author = jT_BookAuthor.getText().toUpperCase();
            profileModel.Edition = jC_Edition.getSelectedItem().toString().toUpperCase();
            profileModel.Publication = jT_BookPublicationName.getText().toUpperCase();
            profileModel.ISBN = jT_BookISBN.getText().toUpperCase();
            String feedBack = profileModel.BookAddValidityCheck();
            if ("Checked!!!".equals(feedBack)) {
                feedBack = profileModel.StoreBookDetails(sqlString);
            }
            JOptionPane.showMessageDialog(this, feedBack);
            jL_status.setText(feedBack);
            refreshThisForm();
        } catch (SQLException ex) {
            Logger.getLogger(LibraryBookAdd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jB_StoreActionPerformed

    private void jP_backgroundMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jP_backgroundMouseClicked
        pD = new ProjectDesign();
        pD.phJDateChooser = jDC_BookEntryDate;
        pD.phJPanel = jP_bookEntryDate;
        pD.placeHolder("jDateChooser", "FG", "EntryDate");
        if (jDC_BookEntryDate.getDate() == null) {
            pD.placeHolder("jDateChooser", "FL", "EntryDate");
        }
    }//GEN-LAST:event_jP_backgroundMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.setLocationRelativeTo(null);
    }//GEN-LAST:event_formWindowOpened

    private void jB_ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_ClearActionPerformed
        refreshThisForm();
    }//GEN-LAST:event_jB_ClearActionPerformed

    private void jB_RestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_RestoreActionPerformed
        try {
            String sqlString = "UPDATE BOOKDATASET SET BOKID = ?, BOKNAME = ?, BOKDATE = ?, BOKAUTHOR = ?,"
                    + "BOKEDITION = ?, BOKPUBNAME = ?, BOKISBN = ? WHERE BOKID = ?";
            profileModel = new LibraryBookModel();
            profileModel.Id = jT_BookId.getText().toUpperCase();
            profileModel.Name = jT_BookTitle.getText().toUpperCase();
            profileModel.EntryDate = ((JTextField) jDC_BookEntryDate.getDateEditor().getUiComponent()).getText().toUpperCase();
            profileModel.Author = jT_BookAuthor.getText().toUpperCase();
            profileModel.Edition = jC_Edition.getSelectedItem().toString().toUpperCase();
            profileModel.Publication = jT_BookPublicationName.getText().toUpperCase();
            profileModel.ISBN = jT_BookISBN.getText().toUpperCase();
            
            profileModel.SearchId = jT_searchBox.getText().toUpperCase();
            
            String feedBack = profileModel.BookAddValidityCheck();
            if ("Checked!!!".equals(feedBack)) {
                feedBack = profileModel.RestoreBookDetails(sqlString);
            }
            JOptionPane.showMessageDialog(this, feedBack);
            jL_status.setText(feedBack);
            refreshThisForm();
        } catch (SQLException ex) {
            Logger.getLogger(LibraryBookAdd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jB_RestoreActionPerformed

    private void jT_searchBoxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jT_searchBoxKeyReleased
        try {
            String sqlString = "SELECT * FROM BOOKDATASET WHERE BOKID = ?";
            
            profileModel = new LibraryBookModel();
            profileModel.SearchId = jT_searchBox.getText().toUpperCase();
            String feedBack = profileModel.SearchBookDetails(sqlString, "For Book List");
            if ("All Retrive".equals(feedBack)) {
                jT_BookId.setText(profileModel.Id);
                jT_BookTitle.setText(profileModel.Name);
                jDC_BookEntryDate.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(profileModel.EntryDate));
                jT_BookAuthor.setText(profileModel.Author);
                for (int i = 0; i < jC_Edition.getModel().getSize(); i++) {
                    if (profileModel.Edition.equals(jC_Edition.getModel().getElementAt(i).toUpperCase())) {
                        jC_Edition.setSelectedIndex(i);
                        break;
                    }
                }
                jT_BookPublicationName.setText(profileModel.Publication);
                jT_BookISBN.setText(profileModel.ISBN);
            }
            jL_status.setText(feedBack);
        } catch (SQLException | ParseException ex) {
            jL_status.setText("SEARCH FOR ACTUAL TYPE OF ID");
            Logger.getLogger(LibraryBookAdd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jT_searchBoxKeyReleased

    private void jB_WithdrawActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_WithdrawActionPerformed
        try {
            String sqlString = "DELETE FROM BOOKDATASET WHERE BOKID = ?";
            profileModel = new LibraryBookModel();
            profileModel.SearchId = jT_searchBox.getText().toUpperCase();
            String feedBack = profileModel.WithdrawBookDetails(sqlString);
            JOptionPane.showMessageDialog(this, feedBack);
            jL_status.setText(feedBack);
            refreshThisForm();
        } catch (SQLException ex) {
            Logger.getLogger(LibraryBookAdd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jB_WithdrawActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LibraryBookAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LibraryBookAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LibraryBookAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LibraryBookAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new LibraryBookAdd().setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(LibraryBookAdd.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jB_Clear;
    private javax.swing.JButton jB_Restore;
    private javax.swing.JButton jB_Store;
    private javax.swing.JButton jB_Withdraw;
    private javax.swing.JComboBox<String> jC_Edition;
    private com.toedter.calendar.JDateChooser jDC_BookEntryDate;
    private javax.swing.JLabel jL_schoolLogo;
    private javax.swing.JLabel jL_status;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jP_background;
    private javax.swing.JPanel jP_bookEntryDate;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jT_BookAuthor;
    private javax.swing.JTextField jT_BookISBN;
    private javax.swing.JTextField jT_BookId;
    private javax.swing.JTextField jT_BookPublicationName;
    private javax.swing.JTextField jT_BookTitle;
    private javax.swing.JTextField jT_searchBox;
    // End of variables declaration//GEN-END:variables
}
