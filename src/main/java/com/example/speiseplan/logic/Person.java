package com.example.speiseplan.logic;

public class Person {
    String surName;
    String firstName;
    double[] bill = new double[5];
    String[] lunch = new String[5];

    public void setLunch(int index, String newLunch) {
        lunch[index] = newLunch;
    }

    public Person(String sn, String fn) {
        surName = sn;
        firstName = fn;
        for (int i = 0; i <
                5; i++) {
            lunch[i] = "";
        }

    }

    public void setPrice(int index, double price) {
        bill[index] = price;
    }

    public String giveString() {
        return "Person: " + surName + ", " + firstName;
    }


    public double[] getBill() {
        return bill;
    }

    public String printBill() {
        StringBuilder paperBill = new StringBuilder(firstName + " " + surName);

        for (int i = 0; i < 5; i++) {
            paperBill.append("\n").append(bill[i]);

        }
        return paperBill.toString();
    }

    public String printOrder() {
        StringBuilder weeksOrder = new StringBuilder(firstName + " " + surName);

        for (int i = 0; i < 5; i++) {
            weeksOrder.append("\n").append(lunch[i]).append(" ").append(bill[i]);

        }
        weeksOrder.append("\nGesamtpreis ").append(countBill());
        return weeksOrder.toString();
    }

    private double countBill() {
        double entirePrice = 0.0;
        for (int i = 0; i < 5; i++) {
            entirePrice = entirePrice + bill[i];
        }
        int substitute = (int) ((entirePrice * 100) + 0.5);
        entirePrice = (double) substitute / 100;
        return entirePrice;
    }
}

