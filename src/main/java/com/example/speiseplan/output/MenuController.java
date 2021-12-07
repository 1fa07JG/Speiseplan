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

    public MenuController() {
    }

    @FXML
    private Button verify;

    public String[] picturePath =new String[10];

    @FXML
    void getWeek(ActionEvent event) {

        DatePicker datePicker= ((DatePicker) event.getSource());
        LocalDate date = datePicker.getValue();
        int umrechnung;

        int year = date.getYear();
        LocalDate firstDay= LocalDate.of(year, Month.JANUARY, 1);

      //   LocalDate first=date.getYear();
        switch (firstDay.getDayOfWeek()){
            case MONDAY -> umrechnung=0;
            case TUESDAY -> umrechnung=1;
            case WEDNESDAY -> umrechnung=2;
            case THURSDAY -> umrechnung=3;
            case FRIDAY -> umrechnung=-3;
            case SATURDAY -> umrechnung=-2;
            case SUNDAY -> umrechnung=-1;
            default -> throw new IllegalStateException("Unexpected value: " + firstDay.getDayOfWeek());
        }
        int daysCW=(date.getDayOfYear())+umrechnung;
        int calendarWeek=daysCW/7;
        calendarWeek++;
        kw.setText("KW: "+calendarWeek);
    }

    @FXML
    void createPdf(ActionEvent event) throws MalformedURLException, FileNotFoundException {
        if (checkInput()){
            findEmptyPicture();
            Week week=getContent();
            for (String s:picturePath) {
                System.out.println(s);
            }
            System.out.println();
            //System.out.println(week.printMenu());
            producePdfMenu(week);

            //write a method to get the Pdf file on week and reassign the button show preview to save the pdf
            }

    }

    //entfernt Fehler Markierungen
    @FXML
    void getWhite(KeyEvent event) {
        TextArea t= ((TextArea) event.getSource());
        t.getStylesheets().remove("/failure.css");
                System.out.println("called get white");
    }
    double getPrice(TextField text){
        String s=text.getText();
        s=s.replace(',','.');
        s=s.replaceAll("[\\p{L}+*~#%&$§?!@-]", "0");
        return Double.parseDouble(s);
    }

    @FXML
    void save(){
        if (checkInput()){
            getContent();

            //write a method to get the Pdf file on week and reassign the button show preview to save the pdf
        terminate();}
    }

    //sammelt die Eingabe daten und Bringt sie in ein Format das die Logic Klassen verabeiten können
    private Week getContent() {
        Meal monA =new Meal(txtAreaFoodMonA.getText(), getPrice(priceMonA),picturePath[0]);
        Meal monB =new Meal(txtAreaFoodMonB.getText(), getPrice(priceMonB),picturePath[1]);
        Day mon=new Day("Monday", monA, monB);

        Meal tueA =new Meal(txtAreaFoodTueA.getText(),getPrice(priceTueA),picturePath[2]);
        Meal tueB =new Meal(txtAreaFoodTueB.getText(),getPrice(priceTueB),picturePath[3]);
        Day tue=new Day("Tuesday",tueA,tueB);

        Meal wedA =new Meal(txtAreaFoodWedA.getText(),getPrice(priceWedA),picturePath[4]);
        Meal wedB =new Meal(txtAreaFoodWedB.getText(),getPrice(priceWedB),picturePath[5]);
        Day wed=new Day("Wednesday",wedA,wedB);

        Meal thuA =new Meal(txtAreaFoodThuA.getText(),getPrice(priceThuA),picturePath[6]);
        Meal thuB =new Meal(txtAreaFoodThuB.getText(),getPrice(priceThuB),picturePath[7]);
        Day thu=new Day("Thursday",thuA,thuB);

        Meal friA =new Meal(txtAreaFoodFriA.getText(),getPrice(priceFriA),picturePath[8]);
        Meal friB =new Meal(txtAreaFoodFriB.getText(),getPrice(priceFriB),picturePath[9]);
        Day fri=new Day("Friday",friA,friB);

        Day[] days=new Day[]{mon,tue,wed,thu,fri};

        return new Week(days);
    }

    //findet und korigirt eingaben die das Programm nicht verarbeiten kann.
    @FXML
    boolean checkInput() {
        boolean ready=true;
        ArrayList<TextArea> textAreas = new ArrayList<>(Arrays.asList(txtAreaFoodMonA, txtAreaFoodMonB, txtAreaFoodTueA, txtAreaFoodTueB, txtAreaFoodWedA, txtAreaFoodWedB, txtAreaFoodThuA,
                txtAreaFoodThuB, txtAreaFoodFriA, txtAreaFoodFriB));
        ArrayList<TextField> textFields = new ArrayList<>(Arrays.asList(priceMonA, priceMonB, priceTueA, priceTueB, priceWedA, priceWedB,
                priceThuA, priceThuB, priceFriA, priceFriB));
        int failure= 0;
        int standardPrice=0;
        for (TextArea t : textAreas) {


            if (t.getText().equals("")) {
                t.getStylesheets().add(("/failure.css"));

                System.out.println(t.getStyle());
                failure++;
                System.out.println("mindestens eine Speise hat keinen Namen ");
                ready=false;
            }else {
                t.getStylesheets().remove("/failure.css");
            }
        }
        for (TextField f : textFields) {


            if (f.getText().equals("")) {
                f.getStylesheets().add(("/failure.css"));
                f.setText("3.9");
                standardPrice++;
                ready=false;
            }else if (f.getText().equals("0.0")){
                f.getStylesheets().add(("/failure.css"));
                f.setText("3.9");
                standardPrice++;
                ready=false;
            }
            else {
               f.getStylesheets().remove("/failure.css");
            }
            f.setText(String.valueOf(getPrice(f)));
        }



        switch ((failure)) {
            case 0 -> message.setText("");
            case 1 -> message.setText("Eine Speise hat keinen Namen ");
            default -> message.setText(failure + " Speisen haben keinen Namen ");
        }
        switch ((standardPrice)) {
            case 0 -> messagePrice.setText("");
            case 1 -> messagePrice.setText("Für eine Speise wurde der Preis von 3,9 € festgelegt ");
            default -> messagePrice.setText("Für "+standardPrice+" Speisen wurde der Preis fo 3,9 € festgelegt");
        }

        return ready;
    }

    private void findEmptyPicture() {
        for (int i=0;i<picturePath.length;i++) {

            if (picturePath[i]==null){
                picturePath[i]="./src/main/resources/com/example/speiseplan/image/Empty.jpg";
            }

        }
    }

    @FXML
    public void initialize() {
        grid.setStyle("-fx-background-color: #20e3e6;");
    }

    @FXML
    void setPicture(MouseEvent event) throws IOException {
        // imageChooser einfügen
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Food Picture");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(null);
        String link="//"+selectedFile.getAbsolutePath();
        FileInputStream fileIn = new FileInputStream(selectedFile);

        Image image = new Image(fileIn);


        ImageView source = (ImageView) event.getSource();

        System.out.println(link);
        source.setImage(image);
        picturePath[search(source.getId())]=link;
    }

    int search(String id){
        id=id.replaceFirst("delete","");
        id=id.replaceFirst("pic","");
        id=id.replaceFirst("price","");
        id=id.replaceFirst("txtArea","");
        ArrayList<String> name = new ArrayList<>(Arrays.asList("MonA", "MonB", "TueA", "TueB", "WedA", "WedB"
                , "ThuA", "ThuB", "FriA", "FriB"));
        return name.indexOf(id);
    }
// folder chooser
    //Löscht ein Menü
    @FXML
    void deleteMenu(ActionEvent event) throws FileNotFoundException {

        ArrayList<TextArea> textAreas = new ArrayList<>(Arrays.asList(txtAreaFoodMonA, txtAreaFoodMonB, txtAreaFoodTueA, txtAreaFoodTueB, txtAreaFoodWedA, txtAreaFoodWedB, txtAreaFoodThuA,
                txtAreaFoodThuB, txtAreaFoodFriA, txtAreaFoodFriB));
        ArrayList<TextField> textFields = new ArrayList<>(Arrays.asList(priceMonA, priceMonB, priceTueA, priceTueB, priceWedA, priceWedB,
                priceThuA, priceThuB, priceFriA, priceFriB));
        ArrayList<ImageView> ImageViews = new ArrayList<>(Arrays.asList(picMonA, picMonB, picTueA, picTueB, picWedA, picWedB, picThuA
                , picThuB, picFriA, picFriB));
        Button b = (Button) event.getSource();

        int index = search(b.getId());
        TextArea refFood = textAreas.get(index);
        TextField refPrice = textFields.get(index);
        ImageView refImage = ImageViews.get(index);

        refFood.setText("");
        refPrice.setText("");
        FileInputStream input = new FileInputStream("src/main/resources/images/keinBild.png");
        Image noImage = new Image(input);
        refImage.setImage(noImage);
        System.out.println("Called handler deleteMenu()");
    }
    // Gibt Testdaten ein um nicht jedes mal Standard Testdaten einzugeben
    @FXML
    private void setContent(ActionEvent event) {
        txtAreaFoodMonA.setText("Dampfnudel");
        priceMonA.setText("3.9");
        txtAreaFoodMonB.setText("Aal");
        priceMonB.setText("3.9");
        txtAreaFoodTueA.setText("Kaiserschmarn");
        priceTueA.setText("3.9");
        txtAreaFoodTueB.setText("Karpfen");
        priceTueB.setText("3.9");
        txtAreaFoodWedA.setText("Semmelknödel");
        priceWedA.setText("3.9");
        txtAreaFoodWedB.setText("Forelle");
        priceWedB.setText("3.9");
        txtAreaFoodThuA.setText("Brotzeit");
        priceThuA.setText("3.9");
        txtAreaFoodThuB.setText("Obatzter");
        priceThuB.setText("3.9");
        txtAreaFoodFriA.setText("Bratkartofelen");
        priceFriA.setText("3.9");
        txtAreaFoodFriB.setText("Renke");
        priceFriB.setText("3.9");


    }

    @FXML
    void terminate() {
        System.exit(0);
    }

}