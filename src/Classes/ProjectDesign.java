package Classes;

import UIs.UserFrontPage;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ProjectDesign {

    public javax.swing.JTextField phTextField;
    public javax.swing.JTextArea phTextArea;
    public javax.swing.JComboBox<String> phComboBox;
    public javax.swing.JCheckBox phCheckBox;
    public com.toedter.calendar.JDateChooser phJDateChooser;
    public javax.swing.JPanel phJPanel;
    public javax.swing.JFrame lFJFrame;

    public void placeHolder(String compoType, String indicator, String compoTitle) {
        if ("jTextField".equals(compoType)) {
            if ("FG".equals(indicator) && phTextField.getText().equals(compoTitle)) {
                phTextField.setText("");
                phTextField.setBackground(Color.YELLOW);
            }

            if ("FL".equals(indicator) && phTextField.getText().equals("")) {
                phTextField.setText(compoTitle);
                phTextField.setBackground(Color.WHITE);
            }
        }

        if ("jTextArea".equals(compoType)) {
            if ("FG".equals(indicator) && phTextArea.getText().equals(compoTitle)) {
                phTextArea.setText("");
                phTextArea.setBackground(Color.YELLOW);
            }

            if ("FL".equals(indicator) && phTextArea.getText().equals("")) {
                phTextArea.setText(compoTitle);
                phTextArea.setBackground(Color.WHITE);
            }
        }

        if ("jComboBox".equals(compoType)) {
            if ("FG".equals(indicator)) {
                phComboBox.setBackground(Color.YELLOW);
            }

            if ("FL".equals(indicator) && phComboBox.getSelectedItem().equals(compoTitle)) {
                phComboBox.setBackground(Color.WHITE);
            }
        }

        if ("jCheckBox".equals(compoType)) {
            if ("FG".equals(indicator)) {
                phCheckBox.setBackground(Color.YELLOW);
                phCheckBox.setOpaque(true);
            }

            if ("FL".equals(indicator)) {
                phCheckBox.setBackground(Color.WHITE);
                phCheckBox.setOpaque(true);
            }
        }

        if ("jDateChooser".equals(compoType)) {
            if ("FG".equals(indicator) && (phJDateChooser.getDate() != null)) {
                phJPanel.setBackground(Color.YELLOW);
            }

            if ("FL".equals(indicator) && (phJDateChooser.getDate() == null)) {
                phJPanel.setBackground(Color.WHITE);
            }
        }
    }

    public void lookAndFeel(String themeType) {
        if (null == themeType) {
        } else switch (themeType) {
            case "Windows":
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(UserFrontPage.class.getName()).log(Level.SEVERE, null, ex);
                }   break;
            case "Classic":
                 try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(UserFrontPage.class.getName()).log(Level.SEVERE, null, ex);
                }
break;
            case "Motif":
                 try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(ProjectDesign.class.getName()).log(Level.SEVERE, null, ex);
                }
break;
            case "Nimbus":
                 try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(ProjectDesign.class.getName()).log(Level.SEVERE, null, ex);
                }
break;
            case "Tattoo":
                 try {
                    UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(ProjectDesign.class.getName()).log(Level.SEVERE, null, ex);
                }
break;
            case "BlackStar":
                 try {
                    UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaBlackStarLookAndFeel");
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(ProjectDesign.class.getName()).log(Level.SEVERE, null, ex);
                }
break;
            case "OrangeMetallic":
                 try {
                    UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaOrangeMetallicLookAndFeel");
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(ProjectDesign.class.getName()).log(Level.SEVERE, null, ex);
                }
break;
            default:
                break;
        }
        SwingUtilities.updateComponentTreeUI(this.lFJFrame);
    }
}
