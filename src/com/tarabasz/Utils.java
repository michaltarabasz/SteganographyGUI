package com.tarabasz;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;

public class Utils {


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

    static ImageIcon getScaledImageIconFromFile(File file, int width, int height){
        ImageIcon img = new ImageIcon(file.getAbsolutePath());
        Image image = img.getImage();
        Image newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        img = new ImageIcon(newimg);
        return img;
    }

    static ImageIcon getScaledImageIconFromImage(BufferedImage file, int width, int height){
        ImageIcon img = new ImageIcon(file);
        Image image = img.getImage();
        Image newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        img = new ImageIcon(newimg);
        return img;
    }

    static ImageIcon getScaledImageIconPreviewFromImage(BufferedImage fileSource, int width, int height,int previewSize){
        BufferedImage file = fileSource.getSubimage(0,0,previewSize,previewSize);
        ImageIcon img = new ImageIcon(file);
        Image image = img.getImage();
        Image newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        img = new ImageIcon(newimg);
        return img;
    }

    static ImageIcon getScaledImageIconPreviewFromFile(File fileSource, int width, int height,int previewSize) {
        try {
            BufferedImage imageSource = getBufferedImageFromFile(fileSource);
            BufferedImage file = imageSource.getSubimage(0, 0, previewSize, previewSize);
            ImageIcon img = new ImageIcon(file);
            Image image = img.getImage();
            Image newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
            img = new ImageIcon(newimg);
            return img;
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return null;
    }


    static String readTextFromPath(String path) {
        File file = new File(path);
        return readTextFromFile(file);
    }

    static String readTextFromFile(File file) {
        try {
            return new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static BufferedImage getBufferedImageFromFile(File file) throws IOException {
        BufferedImage image = null;
        image = ImageIO.read(file);
        return image;
    }

    static BufferedImage getImage(String f) throws IOException {
        BufferedImage image = null;
        File file = new File(f);
        image = ImageIO.read(file);
        return image;
    }

    static boolean saveImage(BufferedImage image, File file) {
        String extensionN = file.getName();
        String[] extensionT = extensionN.split("\\.");
        int len = extensionT.length;
        String extension = file.getName().split("\\.")[1];
        try {
            file.delete(); //delete resources used by the File
            ImageIO.write(image, extension, file);
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
}
