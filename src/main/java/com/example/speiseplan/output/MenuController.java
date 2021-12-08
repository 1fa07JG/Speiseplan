package com.example.speiseplan.output;

import com.example.speiseplan.logic.Day;
import com.example.speiseplan.logic.Meal;
import com.example.speiseplan.logic.Week;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;

import static com.example.speiseplan.output.CreatePdfMenu.producePdfMenu;


public class MenuController {

    public String[] picturePath = new String[10];
    //TODO variable hinzufügen in der die bild Url eingetragen wird die Suchfunktion aus der Löschen Methode verwenden
    @FXML
    private Button deleteMonA;
    @FXML
    private TextArea txtAreaFoodMonA;
    @FXML
    private TextField priceMonA;
    @FXML
    private ImageView picMonA;
    @FXML
    private Button deleteTueA;
    @FXML
    private TextArea txtAreaFoodTueA;
    @FXML
    private TextField priceTueA;
    @FXML
    private ImageView picTueA;
    @FXML
    private Button deleteWedA;
    @FXML
    private TextArea txtAreaFoodWedA;
    @FXML
    private TextField priceWedA;
    @FXML
    private ImageView picWedA;
    @FXML
    private Button deleteThuA;
    @FXML
    private TextArea txtAreaFoodThuA;
    @FXML
    private TextField priceThuA;
    @FXML
    private ImageView picThuA;
    @FXML
    private Button deleteFriA;
    @FXML
    private TextArea txtAreaFoodFriA;
    @FXML
    private TextField priceFriA;
    @FXML
    private ImageView picFriA;
    @FXML
    private Button deleteMonB;
    @FXML
    private TextArea txtAreaFoodMonB;
    @FXML
    private TextField priceMonB;
    @FXML
    private ImageView picMonB;
    @FXML
    private Button deleteTueB;
    @FXML
    private TextArea txtAreaFoodTueB;
    @FXML
    private TextField priceTueB;
    @FXML
    private ImageView picTueB;
    @FXML
    private Button deleteWedB;
    @FXML
    private TextArea txtAreaFoodWedB;
    @FXML
    private TextField priceWedB;
    @FXML
    private ImageView picWedB;
    @FXML
    private Button deleteThuB;
    @FXML
    private TextArea txtAreaFoodThuB;
    @FXML
    private TextField priceThuB;
    @FXML
    private ImageView picThuB;
    @FXML
    private Button deleteFriB;
    @FXML
    private TextArea txtAreaFoodFriB;
    @FXML
    private TextField priceFriB;
    @FXML
    private ImageView picFriB;
    @FXML
    private CheckBox freeMon;
    @FXML
    private CheckBox freeTue;
    @FXML
    private CheckBox freeWed;
    @FXML
    private CheckBox freeThu;
    @FXML
    private CheckBox freeFri;
    @FXML
    private Button exit;
    @FXML
    private Label message;
    @FXML
    private Label messagePrice;
    @FXML
    private Label kw;
    @FXML
    private GridPane grid;
    @FXML
    private DatePicker date;
    @FXML
    private Button verify;


    public MenuController() {
    }

    @FXML
    void getWeek(ActionEvent event) {

        DatePicker datePicker = ((DatePicker) event.getSource());
        LocalDate date = datePicker.getValue();
        int umrechnung;

        int year = date.getYear();
        LocalDate firstDay = LocalDate.of(year, Month.JANUARY, 1);

        //   LocalDate first=date.getYear();
        switch (firstDay.getDayOfWeek()) {
            case MONDAY -> umrechnung = 0;
            case TUESDAY -> umrechnung = 1;
            case WEDNESDAY -> umrechnung = 2;
            case THURSDAY -> umrechnung = 3;
            case FRIDAY -> umrechnung = -3;
            case SATURDAY -> umrechnung = -2;
            case SUNDAY -> umrechnung = -1;
            default -> throw new IllegalStateException("Unexpected value: " + firstDay.getDayOfWeek());
        }
        int daysCW = (date.getDayOfYear()) + umrechnung;
        int calendarWeek = daysCW / 7;
        calendarWeek++;
        kw.setText("KW: " + calendarWeek);
    }

    @FXML
    void createPdf(ActionEvent event) throws MalformedURLException, FileNotFoundException {
        if (checkInput()) {
            findEmptyPicture();
            Week week = getContent();
            for (String s : picturePath) {
                System.out.println(s);
            }
            System.out.println();
            //System.out.println(week.printMenu());
            producePdfMenu(week);

            //write a method to get the Pdf file on week and reassign the button
            // show preview to save the pdf
        }

    }


    //entfernt Fehler Markierungen
    @FXML
    void getWhite(KeyEvent event) {
        TextArea t = ((TextArea) event.getSource());
        t.getStylesheets().remove("/failure.css");
        System.out.println("called get white");
    }


    double getPrice(TextField text) {
        String s = text.getText();
        s = s.replace(',', '.');
        s = s.replaceAll("[\\p{L}+*~#%&$§?!@-]", "0");
        return Double.parseDouble(s);
    }


    @FXML
    void save() {
        if (checkInput()) {
            getContent();
            //write a method to get the Pdf file on week and reassign the button show preview to save the pdf
            terminate();
        }
    }


    //sammelt die Eingabe daten und bringt sie in ein Format, das die Logic Klassen verarbeiten können
    private Week getContent() {
        Meal monA = new Meal(txtAreaFoodMonA.getText(), getPrice(priceMonA), picturePath[0]);
        Meal monB = new Meal(txtAreaFoodMonB.getText(), getPrice(priceMonB), picturePath[1]);
        Day mon = new Day("Monday", monA, monB);

        Meal tueA = new Meal(txtAreaFoodTueA.getText(), getPrice(priceTueA), picturePath[2]);
        Meal tueB = new Meal(txtAreaFoodTueB.getText(), getPrice(priceTueB), picturePath[3]);
        Day tue = new Day("Tuesday", tueA, tueB);

        Meal wedA = new Meal(txtAreaFoodWedA.getText(), getPrice(priceWedA), picturePath[4]);
        Meal wedB = new Meal(txtAreaFoodWedB.getText(), getPrice(priceWedB), picturePath[5]);
        Day wed = new Day("Wednesday", wedA, wedB);

        Meal thuA = new Meal(txtAreaFoodThuA.getText(), getPrice(priceThuA), picturePath[6]);
        Meal thuB = new Meal(txtAreaFoodThuB.getText(), getPrice(priceThuB), picturePath[7]);
        Day thu = new Day("Thursday", thuA, thuB);

        Meal friA = new Meal(txtAreaFoodFriA.getText(), getPrice(priceFriA), picturePath[8]);
        Meal friB = new Meal(txtAreaFoodFriB.getText(), getPrice(priceFriB), picturePath[9]);
        Day fri = new Day("Friday", friA, friB);

        Day[] days = new Day[]{mon, tue, wed, thu, fri};

        return new Week(days);
    }


    // Gibt Testdaten ein, um nicht jedes Mal Standard Testdaten einzugeben
    @FXML
    private void setContent(ActionEvent event) throws FileNotFoundException {
        txtAreaFoodMonA.setText("Dampfnudel");
        priceMonA.setText("3.9");
        setPicture("./src/main/resources/com/example/speiseplan/image/Dampfnudeln.jpg", picMonA);
        txtAreaFoodMonB.setText("Aal");
        priceMonB.setText("3.9");
        setPicture("./src/main/resources/com/example/speiseplan/image/Aal.jpg", picMonB);
        txtAreaFoodTueA.setText("Kaiserschmarrn");
        priceTueA.setText("3.9");
        setPicture("./src/main/resources/com/example/speiseplan/image/Kaiserschmarrn.jpg", picTueA);
        txtAreaFoodTueB.setText("Karpfen");
        priceTueB.setText("3.9");
        setPicture("./src/main/resources/com/example/speiseplan/image/Karpfen.jpg", picTueB);
        txtAreaFoodWedA.setText("Semmelknödel");
        priceWedA.setText("3.9");
        setPicture("./src/main/resources/com/example/speiseplan/image/Semmelknödel.jpg", picWedA);
        txtAreaFoodWedB.setText("Forelle");
        priceWedB.setText("3.9");
        setPicture("./src/main/resources/com/example/speiseplan/image/Forelle.jpg", picWedB);
        txtAreaFoodThuA.setText("Brotzeit");
        priceThuA.setText("3.9");
        setPicture("./src/main/resources/com/example/speiseplan/image/Brotzeit.jpg", picThuA);
        txtAreaFoodThuB.setText("Obatzter");
        priceThuB.setText("3.9");
        setPicture("./src/main/resources/com/example/speiseplan/image/Obatzter.jpg", picThuB);
        txtAreaFoodFriA.setText("Bratkartoffeln");
        priceFriA.setText("3.9");
        setPicture("./src/main/resources/com/example/speiseplan/image/Bratkartoffeln.jpg", picFriA);
        txtAreaFoodFriB.setText("Renke");
        priceFriB.setText("3.9");
        setPicture("./src/main/resources/com/example/speiseplan/image/Renke.jpg", picFriB);


    }

    private void setPicture(String name, ImageView imgVw) throws FileNotFoundException {
        picturePath[search(imgVw.getId())] = name;
        FileInputStream input = new FileInputStream(name);
        Image noImage = new Image(input);
        imgVw.setImage(noImage);
    }

    //findet und korrigiert eingaben die das Programm nicht verarbeiten kann.
    @FXML
    boolean checkInput() {
        boolean ready = true;
        ArrayList<TextArea> textAreas = new ArrayList<>(Arrays.asList(txtAreaFoodMonA, txtAreaFoodMonB,
                txtAreaFoodTueA, txtAreaFoodTueB, txtAreaFoodWedA, txtAreaFoodWedB, txtAreaFoodThuA,
                txtAreaFoodThuB, txtAreaFoodFriA, txtAreaFoodFriB));
        ArrayList<TextField> textFields = new ArrayList<>(Arrays.asList(priceMonA, priceMonB, priceTueA,
                priceTueB, priceWedA, priceWedB,
                priceThuA, priceThuB, priceFriA, priceFriB));
        int failure = 0;
        int standardPrice = 0;
        for (TextArea t : textAreas) {


            if (t.getText().equals("")) {
                t.getStylesheets().add(("/failure.css"));

                System.out.println(t.getStyle());
                failure++;
                System.out.println("mindestens eine Speise hat keinen Namen ");
                ready = false;
            } else {
                t.getStylesheets().remove("/failure.css");
            }
        }
        for (TextField f : textFields) {


            if (f.getText().equals("")) {
                f.getStylesheets().add(("/failure.css"));
                f.setText("3.9");
                standardPrice++;
                ready = false;
                f.setText(String.valueOf(getPrice(f)));
            } else if (f.getText().equals("0.0")) {
                f.getStylesheets().add(("/failure.css"));
                f.setText("3.9");
                standardPrice++;
                ready = false;
                f.setText(String.valueOf(getPrice(f)));
            } else if (f.getText().equals("Feiertag")) {
                System.out.println("called holiday");
            } else {
                f.getStylesheets().remove("/failure.css");
                f.setText(String.valueOf(getPrice(f)));
            }

        }


        switch ((failure)) {
            case 0 -> message.setText("");
            case 1 -> message.setText("Eine Speise hat keinen Namen ");
            default -> message.setText(failure + " Speisen haben keinen Namen ");
        }
        switch ((standardPrice)) {
            case 0 -> messagePrice.setText("");
            case 1 -> messagePrice.setText("Für eine Speise wurde der Preis von 3,9 € festgelegt ");
            default -> messagePrice.setText("Für " + standardPrice + " Speisen wurde der Preis fo 3,9 € festgelegt");
        }

        return ready;
    }

    @FXML
    void setHoliday(ActionEvent event) throws FileNotFoundException {
        ArrayList<String> name = new ArrayList<>(Arrays.asList("freeMon", null, "freeTue", null, "freeWed", null, "freeThu", null, "freeFri"));


        ArrayList<TextArea> textAreas = new ArrayList<>(Arrays.asList(txtAreaFoodMonA, txtAreaFoodMonB,
                txtAreaFoodTueA, txtAreaFoodTueB, txtAreaFoodWedA, txtAreaFoodWedB, txtAreaFoodThuA,
                txtAreaFoodThuB, txtAreaFoodFriA, txtAreaFoodFriB));
        ArrayList<TextField> textFields = new ArrayList<>(Arrays.asList(priceMonA, priceMonB, priceTueA,
                priceTueB, priceWedA, priceWedB,
                priceThuA, priceThuB, priceFriA, priceFriB));
        ArrayList<ImageView> ImageViews = new ArrayList<>(Arrays.asList(picMonA, picMonB, picTueA, picTueB,
                picWedA, picWedB, picThuA
                , picThuB, picFriA, picFriB));
        CheckBox box = (CheckBox) event.getSource();

        int index = name.indexOf(box.getId());
        TextArea refFood = textAreas.get(index);
        TextField refPrice = textFields.get(index);
        ImageView refImage = ImageViews.get(index);
        TextArea ref2Food = textAreas.get(index + 1);
        TextField ref2Price = textFields.get(index + 1);
        ImageView ref2Image = ImageViews.get(index + 1);
        if (!box.isSelected()) {
            refFood.setText("Feiertag");
            refPrice.setText("Feiertag");
            setPicture("./src/main/resources/com/example/speiseplan/image/Empty.jpg", refImage);
            ref2Food.setText("Feiertag");
            ref2Price.setText("Feiertag");
            setPicture("./src/main/resources/com/example/speiseplan/image/Empty.jpg", ref2Image);
            refFood.setDisable(true);
            ref2Food.setDisable(true);
            refPrice.setDisable(true);
            ref2Price.setDisable(true);
            refImage.setDisable(true);
            ref2Image.setDisable(true);
            System.out.println("Called handler deleteMenu()");
        } else {
            refFood.setText("");
            refPrice.setText("");
            ref2Food.setText("");
            ref2Price.setText("");
            refFood.setDisable(false);
            ref2Food.setDisable(false);
            refPrice.setDisable(false);
            ref2Price.setDisable(false);
            refImage.setDisable(false);
            ref2Image.setDisable(false);
            setPicture("./src/main/resources/com/example/speiseplan/image/keinBild.png", refImage);
            setPicture("./src/main/resources/com/example/speiseplan/image/keinBild.png", ref2Image);
        }


    }

    private void findEmptyPicture() {
        for (int i = 0; i < picturePath.length; i++) {

            if (picturePath[i] == null) {
                picturePath[i] = "./src/main/resources/com/example/speiseplan/image/Empty.jpg";
            }

        }
    }

    @FXML
    public void initialize() {
        grid.setStyle("-fx-background-color: #20e3e6;");
    }

    @FXML
    void choosePicture(MouseEvent event) throws IOException {
        // imageChooser einfügen
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Food Picture");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(null);
        String link = "//" + selectedFile.getAbsolutePath();
        FileInputStream fileIn = new FileInputStream(selectedFile);

        Image image = new Image(fileIn);

        ImageView source = (ImageView) event.getSource();

        System.out.println(link);
        source.setImage(image);
        picturePath[search(source.getId())] = link;
    }

    int search(String id) {
        id = id.replaceFirst("delete", "");
        id = id.replaceFirst("pic", "");
        id = id.replaceFirst("price", "");
        id = id.replaceFirst("txtArea", "");
        ArrayList<String> name = new ArrayList<>(Arrays.asList("MonA", "MonB", "TueA", "TueB", "WedA", "WedB"
                , "ThuA", "ThuB", "FriA", "FriB"));
        return name.indexOf(id);
    }

    // folder chooser
    //löscht ein Menü
    @FXML
    void deleteMenu(ActionEvent event) throws FileNotFoundException {

        ArrayList<TextArea> textAreas = new ArrayList<>(Arrays.asList(txtAreaFoodMonA, txtAreaFoodMonB,
                txtAreaFoodTueA, txtAreaFoodTueB, txtAreaFoodWedA, txtAreaFoodWedB, txtAreaFoodThuA,
                txtAreaFoodThuB, txtAreaFoodFriA, txtAreaFoodFriB));
        ArrayList<TextField> textFields = new ArrayList<>(Arrays.asList(priceMonA, priceMonB, priceTueA,
                priceTueB, priceWedA, priceWedB,
                priceThuA, priceThuB, priceFriA, priceFriB));
        ArrayList<ImageView> ImageViews = new ArrayList<>(Arrays.asList(picMonA, picMonB, picTueA, picTueB,
                picWedA, picWedB, picThuA
                , picThuB, picFriA, picFriB));
        Button b = (Button) event.getSource();

        int index = search(b.getId());
        TextArea refFood = textAreas.get(index);
        TextField refPrice = textFields.get(index);
        ImageView refImage = ImageViews.get(index);
        refFood.setText("");
        refPrice.setText("");
        setPicture("./src/main/resources/com/example/speiseplan/image/keinBild.png", refImage);
        System.out.println("Called handler deleteMenu()");
    }

    @FXML
    void terminate() {
        System.exit(0);
    }

}