package com.example.speiseplan.logic;

public class Holiday extends Day {

    public Holiday(String n) {
        super(n);
    }

    @Override
    public String toString() {
        return name + ":\nFeiertag";
    }
}
