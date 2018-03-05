package com.tarabasz;

import javax.swing.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URISyntaxException;


public class ImagePreview {
    public JPanel imagePreviewPanel;
    public JLabel originalImageLabel;
    public JLabel encryptedImageLabel;
    private JLabel zoomedOriginalImageLabel;
    private JLabel zoomedEncryptedImageLabel;
    private JButton saveButton;
    private JLabel originalImagePreview;
    private JLabel targetImagePreview;
    private JLabel zoomedOriginalImagePreview;
    private JLabel zoomedTargetImagePreview;
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

    public JLabel getOriginalImagePreview() {
        return originalImagePreview;
    }

    public void setOriginalImagePreview(JLabel originalImagePreview) {
        this.originalImagePreview = originalImagePreview;
    }

    public JLabel getTargetImagePreview() {
        return targetImagePreview;
    }

    public void setTargetImagePreview(JLabel targetImagePreview) {
        this.targetImagePreview = targetImagePreview;
    }

    public JLabel getZoomedOriginalImagePreview() {
        return zoomedOriginalImagePreview;
    }

    public void setZoomedOriginalImagePreview(JLabel zoomedOriginalImagePreview) {
        this.zoomedOriginalImagePreview = zoomedOriginalImagePreview;
    }

    public JLabel getZoomedTargetImagePreview() {
        return zoomedTargetImagePreview;
    }

    public void setZoomedTargetImagePreview(JLabel zoomedTargetImagePreview) {
        this.zoomedTargetImagePreview = zoomedTargetImagePreview;
    }

}
