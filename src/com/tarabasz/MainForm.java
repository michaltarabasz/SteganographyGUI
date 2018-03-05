package com.tarabasz;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
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

    private JFrame frame;
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
    private JLabel twoBitArrowUp;
    private JLabel fourBitArrowUp;
    private JLabel oneBitArrowUp;
    private JPanel bottomPanel;
    private JLabel encryptButtonLabel;
    private JLabel encryptButton;
    private JLabel errorMessageLabel;
    private JPanel encryptPanel;
    private JPanel decryptPanel;
    private JPanel bottomPanelHidden;
    private JLabel arrowUp;
    private ResourceBundle labelsBundle;
    private JFileChooser fileChooser = new JFileChooser();
    private Steganography steganography = new Steganography();
    ImagePreview imagePreview;

    public MainForm() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, URISyntaxException {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        fileChooser.setPreferredSize(new Dimension(700,500));
        imagePreview = new ImagePreview();
        setLanguage("en");
        setElementsIcons();
        decryptPanel.setOpaque(false);
        encryptPanel.setOpaque(true);
        this.frame = new JFrame("Steganography - Michal Tarabasz");
        frame.setIconImage(ImageUtils.getImageFromRsources("appIco"));
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.getContentPane().add(imagePreview.getImagePreviewPanel());
        frame.getContentPane().revalidate();
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

        oneBitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                oneBitArrowUp.setIcon(ImageUtils.getScaledImageIconFromRsources("arrowUp", 30, 30));
                twoBitArrowUp.setIcon(null);
                fourBitArrowUp.setIcon(null);
                super.mouseClicked(e);
            }
        });
        twoBitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                oneBitArrowUp.setIcon(null);
                twoBitArrowUp.setIcon(ImageUtils.getScaledImageIconFromRsources("arrowUp", 30, 30));
                fourBitArrowUp.setIcon(null);
                super.mouseClicked(e);
            }
        });
        fourBitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                oneBitArrowUp.setIcon(null);
                twoBitArrowUp.setIcon(null);
                fourBitArrowUp.setIcon(ImageUtils.getScaledImageIconFromRsources("arrowUp", 30, 30));
                super.mouseClicked(e);
            }
        });
        redLockButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                bottomPanel.setVisible(false);
                imagePreview.getImagePreviewPanel().setVisible(true);
                decryptPanel.setOpaque(true);
                decryptPanel.repaint();
                encryptPanel.setOpaque(false);
                encryptPanel.repaint();
                super.mouseClicked(e);
            }
        });
        greenLockButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                bottomPanel.setVisible(true);
                imagePreview.getImagePreviewPanel().setVisible(false);
                decryptPanel.setOpaque(false);
                decryptPanel.repaint();
                encryptPanel.setOpaque(true);
                encryptPanel.repaint();
                super.mouseClicked(e);
            }
        });
        addImageButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int returnVal = fileChooser.showOpenDialog(MainForm.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File sourceImageFile = fileChooser.getSelectedFile();
                    steganography.setSourceImage(sourceImageFile);
                    sourceImage.setIcon(ImageUtils.getScaledImageFromFile(sourceImageFile, 348, 478));
                }
                super.mouseClicked(e);
            }
        });
        removeImageButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sourceImage.setIcon(ImageUtils.getScaledImageIconFromRsources("photoMock", 350, 350));
                steganography.setSourceImage(null);
                super.mouseClicked(e);
            }
        });
        addTextButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                super.mouseClicked(e);
            }
        });
    }

    public void setElementsIcons() {
        this.greenLockButton.setIcon(ImageUtils.getScaledImageIconFromRsources("lock", 90, 90));
        this.greenLockButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.redLockButton.setIcon(ImageUtils.getScaledImageIconFromRsources("unlock", 90, 90));
        this.redLockButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.sourceImage.setIcon(ImageUtils.getScaledImageIconFromRsources("photoMock", 350, 350));
        this.addTextButton.setIcon(ImageUtils.getScaledImageIconFromRsources("textFileButton", 25, 25));
        this.addTextButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.addImageButton.setIcon(ImageUtils.getScaledImageIconFromRsources("addButton", 40, 40));
        this.addImageButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.removeImageButton.setIcon(ImageUtils.getScaledImageIconFromRsources("removeButton", 40, 40));
        this.removeImageButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.oneBitButton.setIcon(ImageUtils.getScaledImageIconFromRsources("1bit", 60, 60));
        this.oneBitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.twoBitButton.setIcon(ImageUtils.getScaledImageIconFromRsources("2bit", 60, 60));
        this.twoBitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.fourBitButton.setIcon(ImageUtils.getScaledImageIconFromRsources("4bits", 60, 60));
        this.fourBitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.oneBitArrowUp.setIcon(ImageUtils.getScaledImageIconFromRsources("arrowUp", 30, 30));
        this.encryptButton.setIcon(ImageUtils.getScaledImageIconFromRsources("encryptButton", 70, 70));
        this.encryptButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, IOException, URISyntaxException {
        MainForm mainForm = new MainForm();
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
        encryptButtonLabel.setText(labelsBundle.getString("encryptButtonLabel"));
        imagePreview.getOriginalImageLabel().setText(labelsBundle.getString("originalImageLabel"));
        imagePreview.getZoomedOriginalImageLabel().setText(labelsBundle.getString("zoomedOriginalImageLabel"));
        imagePreview.getEncryptedImageLabel().setText(labelsBundle.getString("encryptedImageLabel"));
        imagePreview.getZoomedEncryptedImageLabel().setText(labelsBundle.getString("zoomedEncryptedImageLabel"));
        imagePreview.getSaveButton().setText(labelsBundle.getString("saveButton"));
    }

}
