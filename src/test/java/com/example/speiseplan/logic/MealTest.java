package com.example.speiseplan.logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MealTest {
    Person lucretia = new Person("Borgia", "Lucretia");
    Person cesare = new Person("Borgia", "Cesare");
    Meal bread = new Meal("Brot", 3.9, "");
    Meal soup = new Meal("Suppe", 5, "");

    @Test
    void testGiveAffected() {
        setup();

        Assertions.assertAll(
                () -> Assertions.assertEquals("Cesare Borgia", soup.giveAffected()),
                () -> Assertions.assertEquals("Lucretia Borgia\nCesare Borgia", bread.giveAffected()));
    }


    @Test
    void testCountCustomers() {

        setup();
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, soup.countOrders()),
                () -> Assertions.assertEquals(2, bread.countOrders()));
    }

    private void setup() {
        bread.addCustomer(lucretia);
        bread.addCustomer(cesare);
        soup.addCustomer(cesare);
    }


}