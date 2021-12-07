package com.example.speiseplan.logic;

import com.example.speiseplan.output.CreatePdfMenu;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

public class Week {

    public Day[] days;

    public Week(Day[] newDays) {

        days = newDays;
        for (int i = 0; i < 5; i++) {
            days[i].setWeekdayIndex(i);
        }
    }

    public void printPdf() throws MalformedURLException, FileNotFoundException {
        CreatePdfMenu.producePdfMenu(this);
    }

    public String printMenu() {

        return "Essensplan:\n" + days[0].toString() +
                "\n" + days[1].toString() +
                "\n" + days[2].toString() +
                "\n" + days[3].toString() +
                "\n" + days[4].toString();
    }
}
