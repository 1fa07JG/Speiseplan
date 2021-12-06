package com.example.speiseplan.output;

import com.example.speiseplan.logic.Meal;
import com.example.speiseplan.logic.Week;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

@SuppressWarnings("ClassEscapesDefinedScope")
public class CreatePdfMenu {

    public static void producePdfMenu(Week kw) throws FileNotFoundException, MalformedURLException {
        String dest = "./pdf/Essensplan.pdf";
        PdfWriter writer = new PdfWriter(dest);

        // Creating a PdfDocument
        PdfDocument pdfDoc = new PdfDocument(writer);
        pdfDoc.setDefaultPageSize(PageSize.A4.rotate());


        // Adding a new page
        pdfDoc.addNewPage();
        Document document = new Document(pdfDoc);
        float[] pointColumnWidths = {150F, 150F, 150F, 150F, 150F, 150F};
        Table table = new Table(pointColumnWidths);
        Cell cell0 = new Cell();   // Creating a cell
        String para0 = "KW 35";
        Paragraph paragraph0 = new Paragraph(para0);
        cell0.add(paragraph0);         // Adding content to the cell
        table.addCell(cell0);      // Adding cell to the table

        String[] wochenTage = {"Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag"};


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
                table.addCell(createMealEntry(shortCut.getName(), shortCut.getPrice(),shortCut.getPicture()));
            }
        }
        document.add(table);


        // Closing the document
        document.close();
        System.out.println("Speiseplan erstellt");

    }

    private static Cell createMealEntry(String name, double price,String picture) throws MalformedURLException {
        Cell cell = new Cell();

        Paragraph beschreibung = new Paragraph(name + "\n" + price + "€");
        cell.add(beschreibung);

        ImageData data;
        try {
            String imageFile = picture;
            data = ImageDataFactory.create(imageFile);

        } catch (Exception FileNotFoundException) {
            data = ImageDataFactory.create("./src/main/resources/com/example/speiseplan/image/Exception.jpg");
        }
        Image img = new Image(data);
        cell.add(img.setAutoScale(true));

        return cell;

    }



}
