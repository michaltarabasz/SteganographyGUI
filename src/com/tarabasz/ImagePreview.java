package com.tarabasz;

import javax.swing.*;

import java.net.URISyntaxException;


public class ImagePreview {
    public JPanel imagePreviewPanel;
    public JLabel originalImageLabel;
    public JLabel encryptedImageLabel;
    private JLabel zoomedOriginalImageLabel;
    private JLabel zoomedEncryptedImageLabel;
    private JButton saveButton;
    public JLabel greenLockButton;


    public ImagePreview() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, URISyntaxException {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    }

    public JPanel getImagePreviewPanel(){
        return this.imagePreviewPanel;
    }

    public JLabel getOriginalImageLabel() {
        return originalImageLabel;
    }

    public void setOriginalImageLabel(JLabel originalImageLabel) {
        this.originalImageLabel = originalImageLabel;
    }

    public JLabel getEncryptedImageLabel() {
        return encryptedImageLabel;
    }

    public void setEncryptedImageLabel(JLabel encryptedImageLabel) {
        this.encryptedImageLabel = encryptedImageLabel;
    }

    public JLabel getZoomedOriginalImageLabel() {
        return zoomedOriginalImageLabel;
    }

    public void setZoomedOriginalImageLabel(JLabel zoomedOriginalImageLabel) {
        this.zoomedOriginalImageLabel = zoomedOriginalImageLabel;
    }

    public JLabel getZoomedEncryptedImageLabel() {
        return zoomedEncryptedImageLabel;
    }

    public void setZoomedEncryptedImageLabel(JLabel zoomedEncryptedImageLabel) {
        this.zoomedEncryptedImageLabel = zoomedEncryptedImageLabel;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public void setSaveButton(JButton saveButton) {
        this.saveButton = saveButton;
    }
}
