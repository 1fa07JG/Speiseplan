package com.example.speiseplan.playground;

import com.itextpdf.io.IOException;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class Skalieren {

    public static void main(String[] args) throws java.io.IOException {

        String dest = "./pdf/skalieren.pdf";
        PdfWriter writer = new PdfWriter(dest);

        PdfDocument pdfDoc = new PdfDocument(writer);
        pdfDoc.setDefaultPageSize(PageSize.A4.rotate());
        Document document = new Document(pdfDoc);

        com.itextpdf.layout.element.Image x = scaleImage("C:\\Users\\bfz\\IdeaProjects\\Speiseplan\\src\\main\\resources\\com\\example\\speiseplan\\image\\Obatzter.jpg");

        document.add(x);

        document.add(new Paragraph("Eine Zeile Text"));

        document.close();
        System.out.println("Erzeuge skalieren.pdf");
    }

    private static com.itextpdf.layout.element.Image scaleImage(String path) throws IOException, java.io.IOException {

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

        ImageData imageData = ImageDataFactory.create(imageBytes);

        com.itextpdf.layout.element.Image i = new com.itextpdf.layout.element.Image(imageData);
        System.out.println("Height: " + i.getImageHeight() + " expected Height: " + scaledHeight + " Width: " + i.getImageWidth());
        return new com.itextpdf.layout.element.Image(imageData);
    }
}
