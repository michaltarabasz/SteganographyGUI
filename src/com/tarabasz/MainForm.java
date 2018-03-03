package com.tarabasz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainForm extends Container {

    interface LANGUAGES {
        String POLISH = "pl";
        String ENGLISH = "en";
    }


    private JPanel panel;
    private JButton plButton;
    private JButton enButton;
    private JButton infoButton;
    private JLabel greenLockButton;
    private JLabel redLockButton;
    private JLabel enrcryptLabel;
    private JLabel decryptLabel;
    private JTextPane sourceTextField;
    private JLabel sourceImage;
    private JLabel addTextButton;
    private JLabel removeImageButton;
    private JLabel addImageButton;
    private JLabel encryptionByteConLabel;
    private JLabel oneBitButton;
    private JLabel twoBitButton;
    private JLabel fourBitButton;
    private JLabel arrowUp;
    private ResourceBundle labelsBundle;

    public MainForm() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, URISyntaxException {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        setLanguage("en");
        setElementsIcons();
        plButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLanguage(LANGUAGES.POLISH);
            }
        });
        enButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLanguage(LANGUAGES.ENGLISH);
            }
        });

        greenLockButton.addMouseListener(new MouseAdapter() {
        });
    }

    private void setElementsIcons() throws URISyntaxException {
        this.greenLockButton.setIcon(getImageIconFromRsources("lock", 90, 90));
        this.greenLockButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.redLockButton.setIcon(getImageIconFromRsources("unlock", 90, 90));
        this.redLockButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.sourceImage.setIcon(getImageIconFromRsources("photoMock", 300, 300));
        this.addTextButton.setIcon(getImageIconFromRsources("textFileButton", 25, 25));
        this.addTextButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.addImageButton.setIcon(getImageIconFromRsources("addButton", 40, 40));
        this.addImageButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.removeImageButton.setIcon(getImageIconFromRsources("removeButton", 40, 40));
        this.removeImageButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.oneBitButton.setIcon(getImageIconFromRsources("1bit", 60, 60));
        this.oneBitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.twoBitButton.setIcon(getImageIconFromRsources("2bit", 60, 60));
        this.twoBitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.fourBitButton.setIcon(getImageIconFromRsources("4bits", 60, 60));
        this.fourBitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.arrowUp.setIcon(getImageIconFromRsources("arrowUp", 60, 60));
    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, IOException, URISyntaxException {
        JFrame frame = new JFrame("Steganography - Michal Tarabasz");
        MainForm mainForm = new MainForm();
        frame.setIconImage(getImageFromRsources("appIco"));
        frame.setContentPane(mainForm.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void setLanguage(String language) {
        Locale locale = new Locale(language);
        ResourceBundle labelsBundle = ResourceBundle.getBundle("com.tarabasz.LanguageBundle", locale);
        this.labelsBundle = labelsBundle;
        refreshLabelsAfterLanguageChange();
    }

    public void refreshLabelsAfterLanguageChange() {
        infoButton.setText(labelsBundle.getString("infoButtonLabel"));
        enrcryptLabel.setText(labelsBundle.getString("enrcryptLabel"));
        decryptLabel.setText(labelsBundle.getString("decryptLabel"));
        sourceTextField.setText(labelsBundle.getString("sourceTextField"));
        encryptionByteConLabel.setText(labelsBundle.getString("encryptionByteConLabel"));
    }

    private static ImageIcon getImageIconFromRsources(String resourceName) throws URISyntaxException {
        ClassLoader classLoader = MainForm.class.getClassLoader();
        File file = new File(classLoader.getResource("com/tarabasz/resources/" + resourceName + ".png").toURI());
        ImageIcon img = new ImageIcon(file.getAbsolutePath());
        return img;
    }

    private static Image getImageFromRsources(String resourceName) throws URISyntaxException {
        return getImageIconFromRsources(resourceName).getImage();
    }

    private static ImageIcon getImageIconFromRsources(String resourceName, int width, int height) throws URISyntaxException {
        ImageIcon img = getImageIconFromRsources(resourceName);
        Image image = img.getImage();
        Image newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        img = new ImageIcon(newimg);
        return img;
    }
}
