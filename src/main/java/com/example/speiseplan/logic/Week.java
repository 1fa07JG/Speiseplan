package com.example.speiseplan.logic;

import com.example.speiseplan.output.CreatePdfMenu;
import com.example.speiseplan.output.MenuController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.time.LocalDate;

public class Week implements Serializable {

    @Serial
    public static final long serialVersionUID = 1;

    int kw;

    public Day[] days;

    public int getKw() {
        return kw;
    }


    public Week(Day[] newDays, int kalender) {

        days = newDays;
        for (int i = 0; i < 5; i++) {
            days[i].setWeekdayIndex(i);
        }
        kw = kalender;
    }

    public void printPdf() throws IOException {
        CreatePdfMenu.producePdfMenu(this, "./pdf/Essensplan.pdf", MenuController.createWeek(LocalDate.now()));
    }

    public long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String printMenu() {

        return "Essensplan:\n" + days[0].toString() +
                "\n" + days[1].toString() +
                "\n" + days[2].toString() +
                "\n" + days[3].toString() +
                "\n" + days[4].toString();
    }
}
