package com.example.speiseplan.output;

import com.example.speiseplan.logic.Day;
import com.example.speiseplan.logic.Meal;
import com.example.speiseplan.logic.Week;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import static com.example.speiseplan.output.CreatePdfMenu.producePdfMenu;

class CreatePdfMenuTest {
    @Test
    void pdfTest() throws MalformedURLException, FileNotFoundException {
        producePdfMenu(setUp(), "./pdf/Essensplan.pdf");
    }


    private static Week setUp() {
        Meal lunch0 = new Meal("Fasan im Federkleid", 3.9, "./src/main/resources/com/example/speiseplan/image/Fasan_im_Federkleid.jpg");
        Meal lunch1 = new Meal("Marillenknödel", 3.9, "./src/main/resources/com/example/speiseplan/image/Marillenknoedel.jpg");
        Meal lunch2 = new Meal("Fish and Chips", 3.9, "./src/main/resources/com/example/speiseplan/image/Fish_and_Chips.jpg");
        Meal lunch3 = new Meal("Bratkartoffeln", 3.9, "./src/main/resources/com/example/speiseplan/image/Bratkartoffeln.jpg");
        Meal lunch4 = new Meal("Schwan im Federkleid", 3.9, "./src/main/resources/com/example/speiseplan/image/Schwan_im_Federkleid.jpg");
        Meal lunch5 = new Meal("Risotto", 3.9, "./src/main/resources/com/example/speiseplan/image/Risotto.jpg");
        Meal lunch6 = new Meal("Rehrücken", 3.9, "./src/main/resources/com/example/speiseplan/image/Rehruecken.jpg");
        Meal lunch7 = new Meal("Marzipan Früchte", 3.9, "./src/main/resources/com/example/speiseplan/image/Marzipan_Fruechte.jpg");
        Meal lunch8 = new Meal("Filet vom Narwal", 3.9, "./src/main/resources/com/example/speiseplan/image/Filet_vom_Narwal.jpg");
        Meal lunch9 = new Meal("Kaiserschmarrn", 3.9, "./src/main/resources/com/example/speiseplan/image/Kaiserschmarrn.jpg");

        Day monday = new Day("Montag");
        Day tuesday = new Day("Dienstag");
        Day wednesday = new Day("Mittwoch");
        Day thursday = new Day("Donnerstag");
        Day friday = new Day("Freitag");
        Week workWeek;

        monday.addMeal(lunch0);
        monday.addMeal(lunch1);

        tuesday.addMeal(lunch2);
        tuesday.addMeal(lunch3);

        wednesday.addMeal(lunch4);
        wednesday.addMeal(lunch5);

        thursday.addMeal(lunch6);
        thursday.addMeal(lunch7);

        friday.addMeal(lunch8);
        friday.addMeal(lunch9);

        Day[] days = new Day[]{monday, tuesday, wednesday, thursday, friday};
        workWeek = new Week(days, 35);
        return workWeek;
    }
}

