package com.example.speiseplan.logic;

import java.util.ArrayList;

public class Meal {

    String name;
    double price;

    ArrayList<Person> customers = new ArrayList<>(0);

    public Meal(String n) {

        name = n;
        price = 3.9;
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
