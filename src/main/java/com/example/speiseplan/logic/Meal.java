package com.example.speiseplan.logic;

import com.itextpdf.io.source.ByteArrayOutputStream;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

public class Meal implements Serializable {

    public static final long serialVersionUID = Week.serialVersionUID;

    String name;
    double price;
    SerializableImage picture;

    ArrayList<Person> customers = new ArrayList<>(0);

    public Meal(String n, double price_, String picturePath) {

        name = n;
        price = price_;
        try {
            picture = createBufferedImage(picturePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SerializableImage createBufferedImage(String path) throws IOException {
        java.awt.Image awtImage = ImageIO.read(new URL("file:" + path));

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
        com.itextpdf.io.source.ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ImageIO.write(scaledAwtImage, "jpeg", bout);
        byte[] imageBytes = bout.toByteArray();
        return new SerializableImage(scaledAwtImage);

    }

    public double getPrice() {
        return price;
    }

    public String getPriceString() {
        return String.valueOf(getPrice());
    }

    public void setPrice(double newPrice) {
        price = newPrice;
    }

    @Override
    public String toString() {
        return name;
    }


    public String getName() {
        return name;
    }

    public BufferedImage getPicture() {
        //WritableImage image = new FileInputStream(picture);
        return picture.getImage();
    }


    int countOrders() {
        System.out.println(customers.size());
        return customers.size();
    }

    public ArrayList<Person> getCustomers() {

        return customers;

    }

    public void addCustomer(Person customer) {
        customers.add(customer);

    }

    public String giveAffected() {
        StringBuilder affected = new StringBuilder(customers.get(0).firstName + " " + customers.get(0).surName);
        for (int i = 1; i < customers.size(); i++) {
            affected.append("\n").append(customers.get(i).firstName).append(" ").append(customers.get(i).surName);
        }
        return affected.toString();
    }
}
