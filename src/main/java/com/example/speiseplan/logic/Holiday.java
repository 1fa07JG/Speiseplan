package com.example.speiseplan.logic;

public class Holiday extends Day {
    Meal noMeal = new Meal("Feiertag", 0.00,
            "./src/main/resources/com/example/speiseplan/image/Empty.jpg");

    public Holiday(String n) {
        super(n);
        super.addMeal(noMeal);
        super.addMeal(noMeal);
    }

    @Override
    public boolean isHoliday() {
        return true;
    }

    @Override
    public String toString() {
        return name + ":\nFeiertag";
    }
}
