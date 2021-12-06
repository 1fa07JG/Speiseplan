package com.example.speiseplan.logic;

import java.util.ArrayList;

import static com.example.speiseplan.logic.Helper.getReplace;

public class Meal {

    String name;
    double price;
    String picture;

    ArrayList<Person> customers = new ArrayList<>(0);

    public Meal(String n) {

        name = n;
        price = -99.99;
        picture ="./src/main/resources/com/example/speiseplan/image/" + getReplace(n) + ".jpg";
    }
    public Meal(String n, double _price) {

        this(n);
        this.price = _price;
    }

    public Meal(String n, double price_,String pic) {

        this(n);
        this.price = price_;
        picture=pic;
    }

    int countOrders() {
        System.out.println(customers.size());
        return customers.size();
    }

    public double getPrice() {
        return price;
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
