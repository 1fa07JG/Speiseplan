package com.example.speiseplan.output;

import com.example.speiseplan.logic.Meal;
import com.example.speiseplan.logic.Week;
import com.itextpdf.io.IOException;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;

public class CreatePdfMenu {

    public static void producePdfMenu(Week kw, String dest, LocalDate[] days) throws java.io.IOException {
        if (dest.equals("")) {
            dest = "./pdf/Essensplan.pdf";
        }

        PdfWriter writer = new PdfWriter(dest);

        // Creating a PdfDocument
        PdfDocument pdfDoc = new PdfDocument(writer);
        pdfDoc.setDefaultPageSize(PageSize.A4.rotate());


        // Adding a new page
        pdfDoc.addNewPage();
        Document document = new Document(pdfDoc);
        float[] pointColumnWidths = {80F, 150F, 150F, 150F, 150F, 150F};
        Table table = new Table(pointColumnWidths);
        Cell cell0 = new Cell();   // Creating a cell
        String para0 = "KW " + kw.getKw();
        Paragraph paragraph0 = new Paragraph(para0);
        cell0.add(paragraph0);         // Adding content to the cell
        table.addCell(cell0);      // Adding cell to the table

        String[] wochenTage = {"Montag " + printDate(days[0]), "Dienstag " + printDate(days[1]), "Mittwoch " + printDate(days[2]), "Donnerstag " + printDate(days[3]), "Freitag " + printDate(days[4])};


        for (String s : wochenTage) {
            Cell cell1 = new Cell();   // Creating a cell
            cell1.add(new Paragraph(s));         // Adding content to the cell
            table.addCell(cell1);      // Adding cell to the table
        }


        for (int i = 0; i < 2; i++) {

            char menu = (char) ('A' + i);
            Cell cellMenu = new Cell();   // Creating a cell
            String paraMenu = "Menü " + menu;
            Paragraph paragraph10 = new Paragraph(paraMenu);
            cellMenu.add(paragraph10);         // Adding content to the cell
            table.addCell(cellMenu);      // Adding cell to the table


            for (int y = 0; y < 5; y++) {
                Meal shortCut = kw.days[y].getMeals().get(i);
                table.addCell(createMealEntry(shortCut.getName(), shortCut.getPrice(), shortCut.getPicture()));
            }
        }
        document.add(table);
        // Closing the document
        document.close();

        Desktop desktop = Desktop.getDesktop();
        File preview = new File(dest);
        desktop.open(preview);

        System.out.println("Speiseplan erstellt");

    }

    private static String printDate(LocalDate date) {
        return date.getDayOfMonth() + "." + date.getMonthValue() + "." + date.getYear();
    }

    private static Cell createMealEntry(String name, double price, BufferedImage picture) throws java.io.IOException {
        Cell cell = new Cell();

        Paragraph beschreibung = new Paragraph(name + "\n" + price + "€");
        cell.add(beschreibung);

        Image img;
        try {
            img = scaleImage(picture);
        } catch (Exception FileNotFoundException) {
            //BufferedImage bufferedImage =MenuController.unBuffer( Meal.createBufferedImage("./src/main/resources/com/example/speiseplan/image/Exception.jpg"));
            //FileInputStream in=new FileInputStream("./src/main/resources/com/example/speiseplan/image/Exception.jpg");
            ImageData data = ImageDataFactory.create("./src/main/resources/com/example/speiseplan/image/Exception.jpg");
            img = new Image(data);
            //data = ImageDataFactory.create();
        }


        cell.add(img.setAutoScale(true));

        return cell;

    }

    private static com.itextpdf.layout.element.Image scaleImage(BufferedImage scaledAwtImage) throws IOException, java.io.IOException {

        com.itextpdf.io.source.ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ImageIO.write(scaledAwtImage, "jpeg", bout);
        byte[] imageBytes = bout.toByteArray();
        ImageData imageData = ImageDataFactory.create(imageBytes);
        Image i = new com.itextpdf.layout.element.Image(imageData);
        return new com.itextpdf.layout.element.Image(imageData);
    }


}
