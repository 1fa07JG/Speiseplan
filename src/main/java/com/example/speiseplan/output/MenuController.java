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
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;


public class MenuController {

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

        Meal monA =new Meal(txtAreaFoodMonA.getText());
        monA.setPrice(getPrice(priceMonA));
        Meal monB =new Meal(txtAreaFoodMonB.getText());
        monB.setPrice(getPrice(priceMonB));
        Day mon=new Day("Monday");
        Meal tueA =new Meal(txtAreaFoodTueA.getText());
        tueA.setPrice(getPrice(priceTueA));
        Meal tueB =new Meal(txtAreaFoodTueB.getText());
        tueB.setPrice(getPrice(priceTueB));
        Day tue=new Day("Tuesday");
        Meal wedA =new Meal(txtAreaFoodWedA.getText());
        wedA.setPrice(getPrice(priceWedA));
        Meal wedB =new Meal(txtAreaFoodWedB.getText());
        wedB.setPrice(getPrice(priceWedB));
        Day wed=new Day("Wednesday");
        Meal thuA =new Meal(txtAreaFoodThuA.getText());
        thuA.setPrice(getPrice(priceThuA));
        Meal thuB =new Meal(txtAreaFoodThuB.getText());
        thuB.setPrice(getPrice(priceThuB));
        Day thu=new Day("Thursday");
        Meal friA =new Meal(txtAreaFoodFriA.getText());
        friA.setPrice(getPrice(priceFriA));
        Meal friB =new Meal(txtAreaFoodFriB.getText());
        friB.setPrice(getPrice(priceFriB));
        Day fri=new Day("Friday");
            Day[] days=new Day[]{mon,tue,wed,thu,fri};
            Week kw=new Week(days);
            //write a method to get the Pdf file on week and reassign the button show preview to save the pdf
        terminate();}
    }


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

    @FXML
    public void initialize() {
        grid.setStyle("-fx-background-color: #20e3e6;");
    }

    // integrate pictures into the meal class, so you can add Pictures to the meal class
    String getPicture(ImageView img) {
        return img.getImage().getUrl();
    }

    @FXML
    void setPicture(MouseEvent event) throws IOException {
        // imageChooser einfügen
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Food Picture");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(null);
        FileInputStream file = new FileInputStream(selectedFile);
        Image image = new Image(file);

        ImageView source = (ImageView) event.getSource();

        source.setImage(image);
    }

    @FXML
    void deleteMenu(ActionEvent event) throws FileNotFoundException {

        ArrayList<String> name = new ArrayList<>(Arrays.asList("deleteMonA", "deleteMonB", "deleteTueA", "deleteTueB", "deleteWedA", "deleteWedB"
                , "deleteThuA", "deleteThuB", "deleteFriA", "deleteFriB"));
        ArrayList<TextArea> textAreas = new ArrayList<>(Arrays.asList(txtAreaFoodMonA, txtAreaFoodMonB, txtAreaFoodTueA, txtAreaFoodTueB, txtAreaFoodWedA, txtAreaFoodWedB, txtAreaFoodThuA,
                txtAreaFoodThuB, txtAreaFoodFriA, txtAreaFoodFriB));
        ArrayList<TextField> textFields = new ArrayList<>(Arrays.asList(priceMonA, priceMonB, priceTueA, priceTueB, priceWedA, priceWedB,
                priceThuA, priceThuB, priceFriA, priceFriB));
        ArrayList<ImageView> ImageViews = new ArrayList<>(Arrays.asList(picMonA, picMonB, picTueA, picTueB, picWedA, picWedB, picThuA
                , picThuB, picFriA, picFriB));
        Button b = (Button) event.getSource();
        String id = b.getId();

        int index = name.indexOf(id);
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

    @FXML
    void terminate() {
        System.exit(0);
    }

}