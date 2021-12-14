package com.example.speiseplan.output;

import com.example.speiseplan.logic.Day;
import com.example.speiseplan.logic.Holiday;
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

import java.io.*;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;

import static com.example.speiseplan.output.CreatePdfMenu.producePdfMenu;


public class MenuController {

    public String[] picturePath = new String[10];
    public int kalenderWeek = -1;//sollte obsolet werden kann zu fehlern führen
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
    private Button openButton;
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

    //methoden in eine neue Reihenfolge 1. Variablen 2. Hilfsmethoden 3.von der Gui direkt verwendete
    // Methoden 3.1 ober rand 3.2 GridPane 3.3 unter rand

    //Hilfsmethoden
    //Output
    //Input
    //eingabe

    double getPrice(TextField text) {
        String s = text.getText();
        s = s.replace(',', '.');
        s = s.replaceAll("[\\p{L}+*~#%&$§?!@-]", "0");
        return Double.parseDouble(s);
    }

    //Methoden Ober rand
    //Methoden in der GridPane
    //Methoden unter rand

    @FXML
    void readDatePicker(ActionEvent event) {

        DatePicker datePicker = ((DatePicker) event.getSource());
        LocalDate date = datePicker.getValue();
        getWeek(date);

    }

    private void getWeek(LocalDate date) {
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
        kalenderWeek = calendarWeek;
        kw.setText(String.valueOf(calendarWeek));
    }

    @FXML
    void createPdf(ActionEvent event) throws IOException {
        if (checkInput()) {
            findEmptyPicture();
            Week week = getContent();
            for (String s : picturePath) {
                System.out.println(s);
            }
            System.out.println();
            //System.out.println(week.printMenu());
            producePdfMenu(week, findSavePath("pdf", "Essensplan"));

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





    @FXML
    void save() {
        if (checkInput()) {
            getContent();
            //write a method to get the Pdf file on week and reassign the button show preview to save the pdf
            serializeObject(getContent(), findSavePath("dat", "Week"));
            terminate();
        }
    }

    @FXML
    void open(ActionEvent event) throws FileNotFoundException {
        setContent(deSerializeObject(openFile()));
    }


    //sammelt die Eingabe daten und bringt sie in ein Format, das die Logic Klassen verarbeiten können
    private Week getContent() {
        Meal monA = new Meal(txtAreaFoodMonA.getText(), getPrice(priceMonA), picturePath[0]);
        Meal monB = new Meal(txtAreaFoodMonB.getText(), getPrice(priceMonB), picturePath[1]);
        Day mon = createDay("Monday", monA, monB);

        Meal tueA = new Meal(txtAreaFoodTueA.getText(), getPrice(priceTueA), picturePath[2]);
        Meal tueB = new Meal(txtAreaFoodTueB.getText(), getPrice(priceTueB), picturePath[3]);
        Day tue = createDay("Tuesday", tueA, tueB);

        Meal wedA = new Meal(txtAreaFoodWedA.getText(), getPrice(priceWedA), picturePath[4]);
        Meal wedB = new Meal(txtAreaFoodWedB.getText(), getPrice(priceWedB), picturePath[5]);
        Day wed = createDay("Wednesday", wedA, wedB);

        Meal thuA = new Meal(txtAreaFoodThuA.getText(), getPrice(priceThuA), picturePath[6]);
        Meal thuB = new Meal(txtAreaFoodThuB.getText(), getPrice(priceThuB), picturePath[7]);
        Day thu = createDay("Thursday", thuA, thuB);

        Meal friA = new Meal(txtAreaFoodFriA.getText(), getPrice(priceFriA), picturePath[8]);
        Meal friB = new Meal(txtAreaFoodFriB.getText(), getPrice(priceFriB), picturePath[9]);
        Day fri = createDay("Friday", friA, friB);

        Day[] days = new Day[]{mon, tue, wed, thu, fri};
        //Integer.getInteger(kw.getText())
        if (kalenderWeek == -1) {
            LocalDate date = LocalDate.now();
            getWeek(date);
        }

        return new Week(days, kalenderWeek);
    }

    Day createDay(String name, Meal A, Meal B) {
        if (A.getName().equals("Feiertag")) {
            return new Holiday(name);
        } else {
            return new Day(name, A, B);
        }

    }

    public void setContent(Week week) throws FileNotFoundException {
        if (week.getSerialVersionUID() == 0) {
            txtAreaFoodMonA.setText(week.days[0].getMeals().get(0).getName());
            priceMonA.setText(week.days[0].getMeals().get(0).getPriceString());
            setPicture(week.days[0].getMeals().get(0).getPicture(), picMonA);

            txtAreaFoodMonB.setText(week.days[0].getMeals().get(1).getName());
            priceMonB.setText(week.days[0].getMeals().get(1).getPriceString());
            setPicture(week.days[0].getMeals().get(1).getPicture(), picMonB);

            txtAreaFoodTueA.setText(week.days[1].getMeals().get(0).getName());
            priceTueA.setText(week.days[1].getMeals().get(0).getPriceString());
            setPicture(week.days[1].getMeals().get(0).getPicture(), picTueA);

            txtAreaFoodTueB.setText(week.days[1].getMeals().get(1).getName());
            priceTueB.setText(week.days[1].getMeals().get(1).getPriceString());
            setPicture(week.days[1].getMeals().get(1).getPicture(), picTueB);

            txtAreaFoodWedA.setText(week.days[2].getMeals().get(0).getName());
            priceWedA.setText(week.days[2].getMeals().get(0).getPriceString());
            setPicture(week.days[2].getMeals().get(0).getPicture(), picWedA);

            txtAreaFoodWedB.setText(week.days[2].getMeals().get(1).getName());
            priceWedB.setText(week.days[2].getMeals().get(1).getPriceString());
            setPicture(week.days[2].getMeals().get(1).getPicture(), picWedB);

            txtAreaFoodThuA.setText(week.days[3].getMeals().get(0).getName());
            priceThuA.setText(week.days[3].getMeals().get(0).getPriceString());
            setPicture(week.days[3].getMeals().get(0).getPicture(), picThuA);

            txtAreaFoodThuB.setText(week.days[3].getMeals().get(1).getName());
            priceThuB.setText(week.days[3].getMeals().get(1).getPriceString());
            setPicture(week.days[3].getMeals().get(1).getPicture(), picThuB);

            txtAreaFoodFriA.setText(week.days[4].getMeals().get(0).getName());
            priceFriA.setText(week.days[4].getMeals().get(0).getPriceString());
            setPicture(week.days[4].getMeals().get(0).getPicture(), picFriA);

            txtAreaFoodFriB.setText(week.days[4].getMeals().get(1).getName());
            priceFriB.setText(week.days[4].getMeals().get(1).getPriceString());
            setPicture(week.days[4].getMeals().get(1).getPicture(), picFriB);


            CheckBox[] weekDays = new CheckBox[]{freeMon, freeTue, freeWed, freeThu, freeFri};
            int[] index = new int[]{0, 2, 4, 6, 8};
            for (int i = 0; i < 5; i++) {
                if (week.days[i].isHoliday()) {
                    weekDays[i].setSelected(false);
                    setHoliday(weekDays[i], index[i]);
                    System.out.println("called setHoliday while Loading");
                }
            }


            kw.setText(String.valueOf(week.getKw()));
        } else {
            message.setText("Die Geöffnete Datei ist veraltet");
        }
    }


    // Gibt Testdaten ein, um nicht jedes Mal Standard Testdaten einzugeben
    @FXML
    private void createTestContent(ActionEvent event) throws FileNotFoundException {
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

        kw.setText(String.valueOf(35));
    }

    private void setPicture(String name, ImageView imgVw) throws FileNotFoundException {
        picturePath[search(imgVw.getId())] = name;
        FileInputStream input;
        try {
            input = new FileInputStream(name);
        } catch (Exception FileNotFoundException) {
            input = new FileInputStream("./src/main/resources/com/example/speiseplan/image/keinBild.png");
        }

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
                if (getPrice(f) > 50) {
                    f.setText("39.00");
                } else {
                    f.setText(String.valueOf(getPrice(f)));
                }
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
    void determineHoliday(ActionEvent event) throws FileNotFoundException {
        ArrayList<String> name = new ArrayList<>(Arrays.asList("freeMon", null, "freeTue", null, "freeWed", null, "freeThu", null, "freeFri"));


        CheckBox box = (CheckBox) event.getSource();

        int index = name.indexOf(box.getId());
        setHoliday(box, index);


    }

    private void setHoliday(CheckBox box, int index) throws FileNotFoundException {
        ArrayList<TextArea> textAreas = new ArrayList<>(Arrays.asList(txtAreaFoodMonA, txtAreaFoodMonB,
                txtAreaFoodTueA, txtAreaFoodTueB, txtAreaFoodWedA, txtAreaFoodWedB, txtAreaFoodThuA,
                txtAreaFoodThuB, txtAreaFoodFriA, txtAreaFoodFriB));
        ArrayList<TextField> textFields = new ArrayList<>(Arrays.asList(priceMonA, priceMonB, priceTueA,
                priceTueB, priceWedA, priceWedB,
                priceThuA, priceThuB, priceFriA, priceFriB));
        ArrayList<ImageView> ImageViews = new ArrayList<>(Arrays.asList(picMonA, picMonB, picTueA, picTueB,
                picWedA, picWedB, picThuA
                , picThuB, picFriA, picFriB));
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

    String findSavePath(String type, String initial) {

        FileChooser file = new FileChooser();
        file.setTitle("Save add");
        file.setInitialFileName(initial);
        file.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Files", "*." + type));
        File selectedFile = file.showSaveDialog(null);
        return "//" + selectedFile.getAbsolutePath();
    }

    String openFile() {
        message.setText("");
        FileChooser file = new FileChooser();
        file.setTitle("Save add");
        file.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Files", "*." + "dat"));
        File selectedFile = file.showOpenDialog(null);
        return "//" + selectedFile.getAbsolutePath();
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

    private static void serializeObject(Week weekSerial, String path) {

        FileOutputStream fos;
        ObjectOutputStream out;

        try {
            fos = new FileOutputStream(path);
            out = new ObjectOutputStream(fos);
            out.writeObject(weekSerial);
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    private static Week deSerializeObject(String path) {

        Week weekDeSerial = null;
        FileInputStream fis;
        ObjectInputStream in;
        try {
            fis = new FileInputStream(path);
            in = new ObjectInputStream(fis);
            weekDeSerial = (Week) in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        assert weekDeSerial != null;
        weekDeSerial.printMenu();
        return weekDeSerial;

    }

    @FXML
    void terminate() {
        System.exit(0);
    }

}