package com.example.speiseplan.playground;

import com.example.speiseplan.logic.SerialImage;
import com.example.speiseplan.logic.Week;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

public class SerializeImage implements Serializable {
    @Serial
    private static final long serialVersionUID = 1;
    static SerialImage img;


    public static void main(String[] args) throws IOException {
        String path = "someImage.dat";
        img = createImage();
        serializeObject(img, path);
        deSerializeObject(path);
    }

    private static SerialImage createImage() throws IOException {
        String path = "./src/main/resources/com/example/speiseplan/image/Exception.jpg";
        Image awtImage = ImageIO.read(new URL("file:" + path));
        int orgWidth = awtImage.getWidth(null);
        int orgHeight = awtImage.getHeight(null);
        int scaledWidth = 180;
        double scalingFactor = (double) orgWidth / scaledWidth;
        int scaledHeight = (int) (orgHeight / scalingFactor);
        System.out.println(awtImage.getHeight(null) + "   " + awtImage.getWidth(null));
        BufferedImage scaledAwtImage = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = scaledAwtImage.createGraphics();
        g.drawImage(awtImage, 0, 0, scaledWidth, scaledHeight, null);
        g.dispose();
        return new SerialImage(scaledAwtImage);
    }

    public BufferedImage getPicture() {
        //WritableImage image = new FileInputStream(picture);
        return img.getImage();
    }

    private static void serializeObject(SerialImage serial, String path) {

        FileOutputStream fos;
        ObjectOutputStream out;

        try {
            fos = new FileOutputStream(path);
            out = new ObjectOutputStream(fos);
            out.writeObject(serial);
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    private static SerialImage deSerializeObject(String path) {

        SerialImage serialImage = null;
        FileInputStream fis;
        ObjectInputStream in;
        try {
            fis = new FileInputStream(path);
            in = new ObjectInputStream(fis);
            serialImage = (SerialImage) in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        assert serialImage != null;

        return serialImage;

    }

}
