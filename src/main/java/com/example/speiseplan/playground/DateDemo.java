package com.example.speiseplan.playground;

import java.time.LocalDate;
import java.time.Month;

public class DateDemo {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        LocalDate newYear = LocalDate.of(today.getYear(), Month.JANUARY, 1);
        System.out.println(newYear.getDayOfYear());
        for (int i = 1; i < 31; i++) {
            LocalDate y = LocalDate.of(2008, Month.DECEMBER, i);
            getWeekDemo(y);
        }
        for (int i = 1; i < 31; i++) {
            LocalDate y = LocalDate.of(2008, Month.JANUARY, i);
            getWeekDemo(y);
        }
    }

    public static void getWeekDemo(LocalDate date) {
        int umrechnung;

        int year = date.getYear();
        LocalDate firstDay = LocalDate.of(year, Month.JANUARY, 1);
        System.out.println(firstDay);

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
        System.out.println(umrechnung);
        System.out.println(date.getDayOfYear());
        int daysCW = (date.getDayOfYear()) + umrechnung;
        System.out.println(daysCW);
        int calendarWeek = (daysCW - 1) / 7;
        calendarWeek++;
        System.out.println(calendarWeek + " " + date.toString() + " " + date.getDayOfWeek().toString());
    }
}
