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
        producePdfMenu(setUp());
    }


    private static Week setUp() {
        Meal lunch0 = new Meal("Fasan im Federkleid");
        Meal lunch1 = new Meal("Marillenknödel");
        Meal lunch2 = new Meal("Fish and Chips");
        Meal lunch3 = new Meal("Bratkartoffeln");
        Meal lunch4 = new Meal("Schwan im Federkleid");
        Meal lunch5 = new Meal("Risotto");
        Meal lunch6 = new Meal("Rehrücken");
        Meal lunch7 = new Meal("Marzipan Früchte");
        Meal lunch8 = new Meal("Filet vom Narwal");
        Meal lunch9 = new Meal("Kaiserschmarrn");

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
        workWeek = new Week(days);
        return workWeek;
    }
}

