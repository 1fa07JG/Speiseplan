package com.example.speiseplan.logic;

import com.itextpdf.layout.element.Image;

import java.io.Serializable;
import java.util.ArrayList;

public class Meal implements Serializable {

    String name;
    double price;
    String picture;

    ArrayList<Person> customers = new ArrayList<>(0);

    public Meal(String n, double price_, String pic) {

        name = n;
        price = price_;
        picture = pic;
    }


    public double getPrice() {
        return price;
    }

    public String getPriceString() {
        return String.valueOf(getPrice());
    }

    public void setPrice(double newPrice) {
        price = newPrice;
    }

    @Override
    public String toString() {
        return name;
    }


    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }


    int countOrders() {
        System.out.println(customers.size());
        return customers.size();
    }

    public ArrayList<Person> getCustomers() {

        return customers;

    }

    public void addCustomer(Person customer) {
        customers.add(customer);

    }

    public String giveAffected() {
        StringBuilder affected = new StringBuilder(customers.get(0).firstName + " " + customers.get(0).surName);
        for (int i = 1; i < customers.size(); i++) {
            affected.append("\n").append(customers.get(i).firstName).append(" ").append(customers.get(i).surName);
        }
        return affected.toString();
    }
}
