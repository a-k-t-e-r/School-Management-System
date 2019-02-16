package Classes;

import UIs.ProfileStudentAdd;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.coobird.thumbnailator.Thumbnails;

public class ImageModel {
    public String FileURL;
    
    public Icon AddImage() {
        ImageIcon actualImage = null;
        try {
            JFileChooser chooseImage = new JFileChooser();
            FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("jpg, jpeg, png", "jpg", "jpeg", "png");
            chooseImage.setFileFilter(extensionFilter);
            chooseImage.showOpenDialog(null);

            File collectFilePath = chooseImage.getSelectedFile();
            FileURL = collectFilePath.getAbsolutePath();

            BufferedImage describeImage = ImageIO.read(collectFilePath);
            BufferedImage modifyImage = Thumbnails.of(describeImage).size(100, 100).asBufferedImage();

            ByteArrayOutputStream retriveImageBytes = new ByteArrayOutputStream();
            ImageIO.write(modifyImage, "jpg", retriveImageBytes);

            actualImage = new ImageIcon(modifyImage);
        } catch (IOException ex) {
            Logger.getLogger(ProfileStudentAdd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return actualImage;
    }

    public byte[] ConvertImageToByteArray(Icon imageInIcon) {
        if (imageInIcon == null) {
            JOptionPane.showMessageDialog(null, "YOU FORGOT TO SELECT A IMAGE");
        } else {
            try {
                byte[] imageInByte = null;
                BufferedImage actualImage = null;
                /* Convert ImageIcon to Actual Image */
                if (imageInIcon instanceof ImageIcon) {
                    int w = imageInIcon.getIconWidth();
                    int h = imageInIcon.getIconHeight();
                    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                    GraphicsDevice gd = ge.getDefaultScreenDevice();
                    GraphicsConfiguration gc = gd.getDefaultConfiguration();
                    BufferedImage image = gc.createCompatibleImage(w, h);
                    Graphics2D g = image.createGraphics();
                    imageInIcon.paintIcon(null, g, 0, 0);
                    g.dispose();
                    actualImage = image;
                }
                BufferedImage modifyImage = Thumbnails.of(actualImage).size(100, 100).asBufferedImage();
                ByteArrayOutputStream retriveImageBytes = new ByteArrayOutputStream();
                ImageIO.write(modifyImage, "jpg", retriveImageBytes);
                ByteArrayInputStream readImageBytes = new ByteArrayInputStream(retriveImageBytes.toByteArray());
                byte[] byteImgData = new byte[1024];
                for (int readNum; (readNum = readImageBytes.read(byteImgData)) != -1;) {
                    retriveImageBytes.write(byteImgData, 0, readNum);
                    System.out.println("Reading byte No. " + readNum);
                }
                imageInByte = retriveImageBytes.toByteArray();
                return imageInByte;
            } catch (IOException ex) {
                Logger.getLogger(ImageModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
