package com.tarabasz;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Steganography {

    static String bigText = " Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi mollis gravida tristique. Pellentesque dapibus urna vel interdum luctus. Proin egestas consectetur felis eget molestie. Aenean gravida blandit elit non ultricies. Nam eu dignissim arcu. Integer ut erat est. Nullam eleifend condimentum accumsan. Sed sodales rhoncus aliquam. Vivamus dapibus, dolor vitae placerat blandit, diam elit finibus augue, a commodo urna turpis at enim. In rutrum vulputate condimentum.Sed in luctus eros, nec sollicitudin lectus. Quisque auctor quam enim, sit amet euismod enim tristique quis. Nunc dignissim nisl in urna tempor dignissim. Vivamus eleifend nunc ac nibh viverra ultricies. Aliquam a nisl ut lectus faucibus faucibus tempus vel turpis. Ut tempor sem in libero vestibulum malesuada. Etiam sagittis rutrum ante eget porta. Pellentesque lobortis lacinia lacus, ac vestibulum nibh. Etiam pellentesque ultricies dolor, non sollicitudin augue rhoncus eget. Donec molestie lacus nec vestibulum tincidunt. Nullam tristique ullamcorper pharetra. Nulla orci lectus, dictum eget aliquam sed, tincidunt quis diam. Suspendisse quis vehicula tortor, in egestas nisi.Cras ornare ac turpis ac lobortis. Quisque tincidunt ante ac ligula placerat tristique. Duis et risus non tellus aliquam lacinia. Curabitur in arcu a justo laoreet varius. Phasellus id feugiat lectus. Duis id sapien vel urna tincidunt pulvinar in eu lacus. Nunc nec vulputate mi. Ut aliquam risus ut ligula dapibus, ac ultricies dui aliquam.Nulla elementum nisl vel pulvinar cursus. Suspendisse venenatis erat odio, vitae ultrices diam facilisis non. Fusce vitae nibh arcu. Suspendisse suscipit elementum nulla, sit amet lacinia sem congue volutpat. Curabitur in ante urna. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec lacinia neque nec dapibus sodales. Donec sollicitudin massa in arcu tristique ornare sed et ipsum. Pellentesque tempus magna lectus, sit amet consequat elit gravida a. Quisque elementum neque vulputate, posuere tellus et, condimentum enim. ";
    private File sourceImage = null;
    private BufferedImage targetImage = null;
    private int bitEncryptionCount = 1;
    private ArrayList<String> errors;
    private String textToHide = "";



/*    public static void main(String[] args) {
        Steganography steganography = new Steganography();
        //steganography.encode(args[0], args[1], Utils.readTextFromPath(args[2]));
        //String decoded = steganography.decode(null, args[0]);
        //System.out.println("Decoded: " + decoded);
    }*/

    public Steganography() {
    }

    public boolean encode() {
        BufferedImage imageOriginal = null;
        BufferedImage imageTarget = null;
        try {
            if (sourceImage != null) {
                imageOriginal = Utils.getBufferedImageFromFile(sourceImage);
                BufferedImage image = createWorkingCopy(imageOriginal);
                targetImage = hideMessageInImage(image, getTextToHide(),bitEncryptionCount);
            } else {
                errors.add("Error: empty source image");
                return false;
            }
        } catch (IOException ex) {
            errors.add(ex.getMessage());
            return false;
        }
        return targetImage != null ? true : false;
    }

    public boolean encode(String fileInput, String fileOutput, String message) throws IOException {
        BufferedImage imageOriginal = Utils.getImage(fileInput);
        BufferedImage image = createWorkingCopy(imageOriginal);
        targetImage = hideMessageInImage(image, message);
        return Utils.saveImage(image, new File(fileOutput));
    }

    public String decode(String path, String name) {
        byte[] decode;
        try {
            BufferedImage image = createWorkingCopy(Utils.getImage(name));
            //decode = decodeText(getImageDataBytes(image));
            decode = decodeTextLast4Bits(getImageDataBytes(image));
            return (new String(decode));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return "";
        }
    }

    private BufferedImage hideMessageInImage(BufferedImage image, String text, int lastBytesCount) {
        byte img[] = getImageDataBytes(image);
        byte msg[] = text.getBytes();
        byte len[] = getBytesFromInteger(msg.length);//message length encoded in 4 bytes
        byte length = (byte) msg.length;
        //32 bits from encoding message length in header (4 bytes)
        if (msg.length + 32 > img.length) {
            errors.add("File too small to encode!");
            return null;
        }
        try {
            encodeTextLength(img, len);
            switch (lastBytesCount) {
                case 1:
                    encodeText(img, msg);
                    break;
                case 2:
                    encodeTextLast2Bits(img, msg);
                    break;
                case 4:
                    encodeTextLast4Bits(img, msg);
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return image;
    }


    private BufferedImage hideMessageInImage(BufferedImage image, String text) {
        byte img[] = getImageDataBytes(image);
        byte msg[] = text.getBytes();
        byte len[] = getBytesFromInteger(msg.length);//message length encoded in 4 bytes
        byte length = (byte) msg.length;
        //32 bits from encoding message length in header (4 bytes)
        if (msg.length + 32 > img.length) {
            throw new IllegalArgumentException("File not long enough!");
        }
        try {
            encodeTextLength(img, len);
            //encodeText(img, msg);
            encodeTextLast4Bits(img, msg);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return image;
    }

    private BufferedImage createWorkingCopy(BufferedImage image) {
        BufferedImage new_img = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D graphics = new_img.createGraphics();
        graphics.drawRenderedImage(image, null);
        graphics.dispose(); //release all allocated memory for this image
        return new_img;
    }

    private byte[] getImageDataBytes(BufferedImage image) {
        WritableRaster raster = image.getRaster();
        DataBufferByte buffer = (DataBufferByte) raster.getDataBuffer();
        return buffer.getData();
    }

    private byte[] getBytesFromInteger(int i) {
        byte byte3 = (byte) ((i & 0xFF000000) >>> 24); //0
        byte byte2 = (byte) ((i & 0x00FF0000) >>> 16); //0
        byte byte1 = (byte) ((i & 0x0000FF00) >>> 8); //0
        byte byte0 = (byte) ((i & 0x000000FF));
        return (new byte[]{byte3, byte2, byte1, byte0});
    }

    private byte[] encodeText(byte[] image, byte[] addition) {
        int currentByte = 32;
        for (int i = 0; i < addition.length; ++i) {
            int add = addition[i];
            for (int bit = 7; bit >= 0; --bit, ++currentByte) {
                int b = (add >>> bit) & 1;
                image[currentByte] = (byte) ((image[currentByte] & 0xFE) | b);
            }
        }
        return image;
    }

    private byte[] encodeTextLength(byte[] image, byte[] messageLength) {
        int currentByte = 0;
        //pętla po każdym bajcie
        for (int i = 0; i < messageLength.length; ++i) {
            //petla po każdym bicie w bajcie
            int messageLengthByte = messageLength[i];
            for (int bit = 7; bit >= 0; --bit, ++currentByte) {
                int b = (messageLengthByte >>> bit) & 1;
                image[currentByte] = (byte) ((image[currentByte] & 0xFE) | b);
            }
        }
        return image;
    }

    private byte[] decodeText(byte[] image) {
        int length = 0;
        int offset = 32;
        //loop through 32 bytes of data to determine text length
        for (int i = 0; i < 32; ++i)
        {
            length = (length << 1) | (image[i] & 1);
        }
        if (length <= 0) {
            throw new IllegalArgumentException("Cannot find encoded message");
        }
        byte[] result = new byte[length];

        for (int b = 0; b < result.length; ++b) {
            for (int i = 0; i < 8; ++i, ++offset) {
                //assign bit: [(new byte value) << 1] OR [(text byte) AND 1]
                result[b] = (byte) ((result[b] << 1) | (image[offset] & 1));
            }
        }
        return result;
    }

    private byte[] encodeTextLast2Bits(byte[] image, byte[] addition) {
        int currentByte = 32;
        for (int i = 0; i < addition.length; ++i) {
            int add = addition[i];
            for (int bit = 6; bit >= 0; bit-=2, ++currentByte) {
                int aa = add >>> bit;
                int bb = aa & 3;
                int b = (add >>> bit) & 3; //mask 0000 0011
                image[currentByte] = (byte) ((image[currentByte] & 0xFC) | b); //0xFC = 1111 1100
            }
        }
        return image;
    }

    private byte[] decodeTextLast2Bits(byte[] image) {
        int length = 0;
        int offset = 32;
        //loop through 32 bytes of data to determine text length
        for (int i = 0; i < 32; ++i)
        {
            length = (length << 1) | (image[i] & 1);
        }
        if (length <= 0) {
            throw new IllegalArgumentException("Cannot find encoded message");
        }
        byte[] result = new byte[length];

        for (int b = 0; b < result.length; ++b) {
            for (int i = 0; i < 4; ++i, ++offset) {
                result[b] = (byte) ((result[b] << 2) | (image[offset] & 3));
            }
        }
        return result;
    }

    private byte[] encodeTextLast4Bits(byte[] image, byte[] addition) {
        int currentByte = 32;
        for (int i = 0; i < addition.length; ++i) {
            int add = addition[i];
            for (int bit = 4; bit >= 0; bit-=4, ++currentByte) {
                int aa = add >>> bit;
                int bb = aa & 0xF;
                int b = (add >>> bit) & 0xF; //mask 0000 1111
                image[currentByte] = (byte) ((image[currentByte] & 0xF0) | b); //0xF0 = 1111 0000
            }
        }
        return image;
    }

    private byte[] decodeTextLast4Bits(byte[] image) {
        int length = 0;
        int offset = 32;
        //loop through 32 bytes of data to determine text length
        for (int i = 0; i < 32; ++i)
        {
            length = (length << 1) | (image[i] & 1);
        }
        if (length <= 0) {
            throw new IllegalArgumentException("Cannot find encoded message");
        }
        byte[] result = new byte[length];

        for (int b = 0; b < result.length; ++b) {
            for (int i = 0; i < 2; ++i, ++offset) {
                result[b] = (byte) ((result[b] << 4) | (image[offset] & 0xF));
            }
        }
        return result;
    }



    public File getSourceImage() {
        return sourceImage;
    }

    public void setSourceImage(File sourceImage) {
        this.sourceImage = sourceImage;
    }

    public BufferedImage getTargetImage() {
        return targetImage;
    }

    public void setTargetImage(BufferedImage targetImage) {
        this.targetImage = targetImage;
    }

    public int getBitEncryptionCount() {
        return bitEncryptionCount;
    }

    public void setBitEncryptionCount(int bitEncryptionCount) {
        this.bitEncryptionCount = bitEncryptionCount;
    }

    public ArrayList<String> getErrors() {
        return errors;
    }

    public void setErrors(ArrayList<String> errors) {
        this.errors = errors;
    }

    public String getTextToHide() {
        return textToHide;
    }

    public void setTextToHide(String textToHide) {
        this.textToHide = textToHide;
    }
}
