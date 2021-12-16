package com.example.speiseplan.logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerialImage implements Serializable {
    transient BufferedImage images;

    SerialImage(BufferedImage bufferd) {
        images = bufferd;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();


        ImageIO.write(images, "png", out); // png is lossless

    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        final int imageCount = in.readInt();
        images = (ImageIO.read(in));
    }

    public BufferedImage getImage() {
        return images;
    }
}

