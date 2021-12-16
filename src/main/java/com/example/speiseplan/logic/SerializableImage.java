package com.example.speiseplan.logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class SerializableImage implements Serializable {

    public static final long serialVersionUID = Week.serialVersionUID;

    transient BufferedImage image;

    public SerializableImage(BufferedImage bufferd) {
        image = bufferd;
    }

    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {

        out.defaultWriteObject();
        ImageIO.write(image, "png", out); // png is lossless

    }

    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {

        in.defaultReadObject();
        image = (ImageIO.read(in));
    }

    public BufferedImage getImage() {
        return image;
    }
}

