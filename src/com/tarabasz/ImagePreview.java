package com.tarabasz;

import javax.swing.*;

import java.net.URISyntaxException;


public class ImagePreview {
    public JPanel imagePreviewPanel;
    public JLabel greenLockButton;


    public ImagePreview() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, URISyntaxException {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    }

    public JPanel getImagePreviewPanel(){
        return this.imagePreviewPanel;
    }

}
