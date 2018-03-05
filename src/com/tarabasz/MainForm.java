package com.tarabasz;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
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
    DecodeForm decodeForm;

    public MainForm() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, URISyntaxException {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        fileChooser.setPreferredSize(new Dimension(700,500));
        fileChooser.setAcceptAllFileFilterUsed(false);
        imagePreview = new ImagePreview();
        decodeForm = new DecodeForm();
        setLanguage("en");
        setElementsIcons();
        decryptPanel.setOpaque(false);
        encryptPanel.setOpaque(true);
        this.frame = new JFrame("Steganography - Michal Tarabasz");
        frame.setIconImage(Utils.getImageFromRsources("appIco"));
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.getContentPane().add(imagePreview.getImagePreviewPanel());
        frame.getContentPane().add(decodeForm.getDecodePanel());
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
                oneBitArrowUp.setIcon(Utils.getScaledImageIconFromRsources("arrowUp", 30, 30));
                twoBitArrowUp.setIcon(null);
                fourBitArrowUp.setIcon(null);
                steganography.setBitEncryptionCount(1);
                super.mouseClicked(e);
            }
        });
        twoBitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                oneBitArrowUp.setIcon(null);
                twoBitArrowUp.setIcon(Utils.getScaledImageIconFromRsources("arrowUp", 30, 30));
                fourBitArrowUp.setIcon(null);
                steganography.setBitEncryptionCount(2);
                super.mouseClicked(e);
            }
        });
        fourBitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                oneBitArrowUp.setIcon(null);
                twoBitArrowUp.setIcon(null);
                fourBitArrowUp.setIcon(Utils.getScaledImageIconFromRsources("arrowUp", 30, 30));
                steganography.setBitEncryptionCount(4);
                super.mouseClicked(e);
            }
        });
        decodeForm.getOneBitButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                decodeForm.getOneBitArrowUp().setIcon(Utils.getScaledImageIconFromRsources("arrowUp", 30, 30));
                decodeForm.getTwoBitArrowUp().setIcon(null);
                decodeForm.getFourBitArrowUp().setIcon(null);
                steganography.setBitDecryptionCount(1);
                super.mouseClicked(e);
            }
        });
        decodeForm.getTwoBitButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                decodeForm.getOneBitArrowUp().setIcon(null);
                decodeForm.getTwoBitArrowUp().setIcon(Utils.getScaledImageIconFromRsources("arrowUp", 30, 30));
                decodeForm.getFourBitArrowUp().setIcon(null);
                steganography.setBitDecryptionCount(2);
                super.mouseClicked(e);
            }
        });
        decodeForm.getFourBitButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                decodeForm.getOneBitArrowUp().setIcon(null);
                decodeForm.getTwoBitArrowUp().setIcon(null);
                decodeForm.getFourBitArrowUp().setIcon(Utils.getScaledImageIconFromRsources("arrowUp", 30, 30));
                steganography.setBitDecryptionCount(4);
                super.mouseClicked(e);
            }
        });
        redLockButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                bottomPanel.setVisible(false);
                imagePreview.getImagePreviewPanel().setVisible(false);
                decodeForm.getDecodePanel().setVisible(true);
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
                decodeForm.getDecodePanel().setVisible(false);
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
                super.mouseClicked(e);
                fileChooser.setSelectedFile(null);
                fileChooser.resetChoosableFileFilters();
                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PNG files", "png","PNG"));
                fileChooser.updateUI();
                int returnVal = fileChooser.showOpenDialog(MainForm.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File sourceImageFile = fileChooser.getSelectedFile();
                    steganography.setSourceImage(sourceImageFile);
                    sourceImage.setIcon(Utils.getScaledImageIconFromFile(sourceImageFile, 348, 478));
                }
            }
        });

        decodeForm.getAddIDecodeImageButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                fileChooser.setSelectedFile(null);
                fileChooser.resetChoosableFileFilters();
                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PNG files", "png","PNG"));
                fileChooser.updateUI();
                int returnVal = fileChooser.showOpenDialog(MainForm.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File sourceImageFile = fileChooser.getSelectedFile();
                    try {
                        steganography.setTargetDecryptImage(Utils.getBufferedImageFromFile(sourceImageFile));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    decodeForm.getDecodeSourceImage().setIcon(Utils.getScaledImageIconFromFile(sourceImageFile, 348, 478));
                }
            }
        });
        removeImageButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sourceImage.setIcon(Utils.getScaledImageIconFromRsources("photoMock", 350, 350));
                steganography.setSourceImage(null);
                super.mouseClicked(e);
            }
        });
        decodeForm.getRemoveDecodeImageButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                decodeForm.getDecodeSourceImage().setIcon(Utils.getScaledImageIconFromRsources("photoMock", 350, 350));
                steganography.setTargetDecryptImage(null);
                super.mouseClicked(e);
            }
        });
        addTextButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                fileChooser.setSelectedFile(null);
                fileChooser.resetChoosableFileFilters();
                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text files", "txt"));
                fileChooser.updateUI();
                int returnVal = fileChooser.showOpenDialog(MainForm.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File sourceTextFile = fileChooser.getSelectedFile();
                    String text = Utils.readTextFromFile(sourceTextFile);
                    sourceTextField.setText(text);
                }
            }
        });
        encryptButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                steganography.setErrors(new ArrayList<>());
                errorMessageLabel.setText("");
                super.mouseClicked(e);
                if(sourceTextField.getText().isEmpty()){
                    errorMessageLabel.setText(labelsBundle.getString("emptyTextError"));
                }
                steganography.setTextToHide(sourceTextField.getText());
                if(steganography.encode()){
                    imagePreview.getOriginalImagePreview().setIcon(Utils.getScaledImageIconFromFile(steganography.getSourceImage(), 298, 377));
                    imagePreview.getZoomedOriginalImagePreview().setIcon(Utils.getScaledImageIconPreviewFromFile(steganography.getSourceImage(), 158, 157,20));
                    imagePreview.getTargetImagePreview().setIcon(Utils.getScaledImageIconFromImage(steganography.getTargetImage(),298,377));
                    imagePreview.getZoomedTargetImagePreview().setIcon(Utils.getScaledImageIconPreviewFromImage(steganography.getTargetImage(),158,157,20));
                    bottomPanel.setVisible(false);
                    decodeForm.getDecodePanel().setVisible(false);
                    imagePreview.getImagePreviewPanel().setVisible(true);
                    super.mouseClicked(e);
                }
                else{
                    for(String error : steganography.getErrors()){
                        StringBuilder builder = new StringBuilder();
                        builder.append(error);
                        builder.append("\n");
                        errorMessageLabel.setText(builder.toString());
                    }
                }
            }
        });

        decodeForm.getDecodeButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                steganography.setErrors(new ArrayList<>());
                decodeForm.getDecodeErrorMessageLabel().setText("");
                if (steganography.getTargetImage() == null) {
                    decodeForm.getDecodeErrorMessageLabel().setText("");
                }
                String decodedText = steganography.decode();
                if (!decodedText.isEmpty()) {
                    decodeForm.getDecryptedText().setText(decodedText);
                } else {
                    for (String error : steganography.getErrors()) {
                        StringBuilder builder = new StringBuilder();
                        builder.append(error);
                        builder.append("\n");
                        decodeForm.getDecodeErrorMessageLabel().setText(builder.toString());
                        decodeForm.getDecryptedText().setText("No text found in image");
                    }
                }
            }
        });
        imagePreview.getSaveButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fileChooser.setSelectedFile(null);
                fileChooser.resetChoosableFileFilters();
                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PNG files", "png","PNG"));
                fileChooser.updateUI();
                int returnVal = fileChooser.showSaveDialog(MainForm.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File targetImageFile = fileChooser.getSelectedFile();
                if(Utils.saveImage(steganography.getTargetImage(),targetImageFile)){
                    JOptionPane.showMessageDialog(null,"File saved");
                }
                }
                super.mouseClicked(e);
            }
        });
    }

    public void setElementsIcons() {
        this.greenLockButton.setIcon(Utils.getScaledImageIconFromRsources("lock", 90, 90));
        this.greenLockButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.redLockButton.setIcon(Utils.getScaledImageIconFromRsources("unlock", 90, 90));
        this.redLockButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.sourceImage.setIcon(Utils.getScaledImageIconFromRsources("photoMock", 350, 350));
        decodeForm.getDecodeSourceImage().setIcon(Utils.getScaledImageIconFromRsources("photoMock", 350, 350));
        this.addTextButton.setIcon(Utils.getScaledImageIconFromRsources("textFileButton", 25, 25));
        this.addTextButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.addImageButton.setIcon(Utils.getScaledImageIconFromRsources("addButton", 40, 40));
        decodeForm.getAddIDecodeImageButton().setIcon(Utils.getScaledImageIconFromRsources("addButton", 40, 40));
        this.addImageButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        decodeForm.getAddIDecodeImageButton().setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.removeImageButton.setIcon(Utils.getScaledImageIconFromRsources("removeButton", 40, 40));
        decodeForm.getRemoveDecodeImageButton().setIcon(Utils.getScaledImageIconFromRsources("removeButton", 40, 40));
        this.removeImageButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        decodeForm.getRemoveDecodeImageButton().setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.oneBitButton.setIcon(Utils.getScaledImageIconFromRsources("1bit", 60, 60));
        decodeForm.getOneBitButton().setIcon(Utils.getScaledImageIconFromRsources("1bit", 60, 60));
        this.oneBitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        decodeForm.getOneBitButton().setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.twoBitButton.setIcon(Utils.getScaledImageIconFromRsources("2bit", 60, 60));
        decodeForm.getTwoBitButton().setIcon(Utils.getScaledImageIconFromRsources("2bit", 60, 60));
        this.twoBitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        decodeForm.getTwoBitButton().setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.fourBitButton.setIcon(Utils.getScaledImageIconFromRsources("4bits", 60, 60));
        decodeForm.getFourBitButton().setIcon(Utils.getScaledImageIconFromRsources("4bits", 60, 60));
        this.fourBitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        decodeForm.getFourBitButton().setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.oneBitArrowUp.setIcon(Utils.getScaledImageIconFromRsources("arrowUp", 30, 30));
        decodeForm.getOneBitArrowUp().setIcon(Utils.getScaledImageIconFromRsources("arrowUp", 30, 30));
        this.encryptButton.setIcon(Utils.getScaledImageIconFromRsources("encryptButton", 70, 70));
        decodeForm.getDecodeButton().setIcon(Utils.getScaledImageIconFromRsources("encryptButton", 70, 70));
        this.encryptButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        decodeForm.getDecodeButton().setCursor(new Cursor(Cursor.HAND_CURSOR));
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
        decodeForm.getEncryptionByteConLabel().setText(labelsBundle.getString("encryptionByteConLabel"));
    }

}
