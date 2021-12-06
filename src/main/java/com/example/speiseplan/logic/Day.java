package com.example.speiseplan.logic;

import java.util.ArrayList;

public class Day {

    String name;

    ArrayList<Meal> foodList = new ArrayList<>();
    int weekdayIndex;

    public void setWeekdayIndex(int newWeekdayIndex) {
        weekdayIndex = newWeekdayIndex;
    }

    public Day(String n) {
        name = n;
    }

    public Day(String n, Meal a, Meal b) {
        this(n);
        this.addMeal(a);
        this.addMeal(b);
    }


    public void addMeal(Meal newFood) {
        foodList.add(newFood);
    }

    @Override
    public String toString() {
        return name + ":\nMenü A: " + foodList.get(0).toString() + "\nMenü B: " + foodList.get(1).toString();
    }

    public ArrayList<Meal> getMeals() {

        return foodList;
    }

    public void order(Person customer, int index) {
        foodList.get(index).addCustomer(customer);
        customer.setPrice(weekdayIndex, foodList.get(index).price);
        customer.setLunch(weekdayIndex, foodList.get(index).name);

    }
}
