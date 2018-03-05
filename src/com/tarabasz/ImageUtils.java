package com.tarabasz;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URISyntaxException;

public class ImageUtils {


    static ImageIcon getScaledImageIconFromRsources(String resourceName)  {
        ClassLoader classLoader = MainForm.class.getClassLoader();
        File file = null;
        try {
            file = new File(classLoader.getResource("com/tarabasz/resources/" + resourceName + ".png").toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        ImageIcon img = new ImageIcon(file.getAbsolutePath());
        return img;
    }

    static Image getImageFromRsources(String resourceName) {
        return getScaledImageIconFromRsources(resourceName).getImage();
    }

    static ImageIcon getScaledImageIconFromRsources(String resourceName, int width, int height) {
        ImageIcon img = getScaledImageIconFromRsources(resourceName);
        Image image = img.getImage();
        Image newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        img = new ImageIcon(newimg);
        return img;
    }

     static ImageIcon getImageFromFile(File file, int width, int height){
         ImageIcon img = new ImageIcon(file.getAbsolutePath());
         return img;
    }

    static ImageIcon getScaledImageFromFile(File file, int width, int height){
        ImageIcon img = new ImageIcon(file.getAbsolutePath());
        Image image = img.getImage();
        Image newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        img = new ImageIcon(newimg);
        return img;
    }
}
