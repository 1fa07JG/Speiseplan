package com.example.speiseplan.logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class PersonTest {

    public Meal lunch0 = new Meal("Fasan im Federkleid", 6, "./src/main/resources/com/example/speiseplan/image/Fasan_im_Federkleid.jpg");
    public Meal lunch1 = new Meal("Knödel in Schokoladen Soße", 8, "./src/main/resources/com/example/speiseplan/image/Marillenknoedel.jpg");
    public Meal lunch2 = new Meal("Fish and Chips", 3.9, "./src/main/resources/com/example/speiseplan/image/Fish_and_Chips.jpg");
    public Meal lunch3 = new Meal("Bratkartoffeln", 5, "./src/main/resources/com/example/speiseplan/image/Bratkartoffeln.jpg");
    public Meal lunch4 = new Meal("Schwan im Federkleid", 3.9, "./src/main/resources/com/example/speiseplan/image/Schwan_im_Federkleid.jpg");
    public Meal lunch5 = new Meal("Risotto", 2.5, "./src/main/resources/com/example/speiseplan/image/Risotto.jpg");
    public Meal lunch6 = new Meal("Hirschrücken mit chili garniert", 7, "./src/main/resources/com/example/speiseplan/image/Rehruecken.jpg");
    public Meal lunch7 = new Meal("Zuckerschloss", 3.9, "./src/main/resources/com/example/speiseplan/image/Marzipan_Fruechte.jpg");
    public Meal lunch8 = new Meal("Pastete vom Narwhal", 3.9, "./src/main/resources/com/example/speiseplan/image/Filet_vom_Narwal.jpg");
    public Meal lunch9 = new Meal("Kaiserschmarrn", 4.5, "./src/main/resources/com/example/speiseplan/image/Kaiserschmarrn.jpg");

    public Day monday = new Day("Montag");
    public Day tuesday = new Day("Dienstag");
    public Day wednesday = new Day("Mittwoch");
    public Day thursday = new Day("Donnerstag");
    public Day friday = new Day("Freitag");
    public Person lucretia = new Person("Borgia", "Lucretia");
    public Person rodrigo = new Person("Borgia", "Rodrigo");

    public Week workWeek;

    public PersonTest() {


        monday.addMeal(lunch0);
        monday.addMeal(lunch1);

        tuesday.addMeal(lunch2);
        tuesday.addMeal(lunch3);

        wednesday.addMeal(lunch4);
        wednesday.addMeal(lunch5);

        thursday.addMeal(lunch6);
        thursday.addMeal(lunch7);

        friday.addMeal(lunch8);
        friday.addMeal(lunch9);

        Day[] days = new Day[]{monday, tuesday, wednesday, thursday, friday};
        workWeek = new Week(days);

    }

    @Test
    void testBill() {
        Person cesare = setUpBillTest();
        Assertions.assertAll(
                () -> Assertions.assertArrayEquals(new double[]{5.5, 3.9, 3.9, 3.9, 3.9}, cesare.getBill()),
                () -> Assertions.assertArrayEquals(new double[]{0, 0, 0, 3.9, 0}, lucretia.getBill()),
                () -> Assertions.assertArrayEquals(new double[]{0, 0, 0, 0, 0}, rodrigo.getBill()));
    }

    @Test
    void testPrintBill() {
        Person cesare = setUpBillTest();
        String cesareBill = "Cesare Borgia\n5.5\n3.9\n3.9\n3.9\n3.9";
        String lucretiasBill = "Lucretia Borgia\n0.0\n0.0\n0.0\n3.9\n0.0";
        String rodrigoBill = "Rodrigo Borgia\n0.0\n0.0\n0.0\n0.0\n0.0";
        Assertions.assertAll(
                () -> Assertions.assertEquals(cesareBill, cesare.printBill()),
                () -> Assertions.assertEquals(lucretiasBill, lucretia.printBill()),
                () -> Assertions.assertEquals(rodrigoBill, rodrigo.printBill()));


    }

    @Test
    void testWeekOrder() {
        Person cesare = setUpBillTest();
        String cesareBill = "Cesare Borgia\nFasan im Federkleid 5.5\nFish and Chips 3.9\nSchwan im Federkleid 3.9\nZuckerschloss 3.9\nPastete vom Narwhal 3.9\nGesamtpreis 21.1";
        String lucretiasBill = "Lucretia Borgia\n 0.0\n 0.0\n 0.0\nZuckerschloss 3.9\n 0.0\nGesamtpreis 3.9";
        String rodrigoBill = "Rodrigo Borgia\n 0.0\n 0.0\n 0.0\n 0.0\n 0.0\nGesamtpreis 0.0";
        Assertions.assertAll(
                () -> Assertions.assertEquals(cesareBill, cesare.printOrder()),
                () -> Assertions.assertEquals(lucretiasBill, lucretia.printOrder()),
                () -> Assertions.assertEquals(rodrigoBill, rodrigo.printOrder()));

    }

    private Person setUpBillTest() {
        Person cesare = new Person("Borgia", "Cesare");

        monday.foodList.get(0).setPrice(5.5);
        monday.order(cesare, 0);
        tuesday.order(cesare, 0);
        wednesday.order(cesare, 0);
        thursday.order(cesare, 1);
        friday.order(cesare, 0);
        thursday.order(lucretia, 1);
        return cesare;
    }

    @Test
    void testHoliday() {
        Holiday freeDay = new Holiday("Montag");
        Day[] freeDays = new Day[]{freeDay, tuesday, wednesday, thursday, friday};
        Week freeWeek = new Week(freeDays);
        String menuList = """
                Essensplan:
                Montag:
                Feiertag
                Dienstag:
                Menü A: Fish and Chips
                Menü B: Bratkartoffeln
                Mittwoch:
                Menü A: Schwan im Federkleid
                Menü B: Risotto
                Donnerstag:
                Menü A: Hirschrücken mit chili garniert
                Menü B: Zuckerschloss
                Freitag:
                Menü A: Pastete vom Narwhal
                Menü B: Kaiserschmarrn""";
        System.out.println(menuList);
        Assertions.assertAll(

                () -> Assertions.assertEquals(menuList, freeWeek.printMenu()));
    }

    @Test
    void testOrderCount() {
        monday.order(lucretia, 0);
        monday.order(rodrigo, 0);
        monday.order(lucretia, 1);
        Assertions.assertAll(
                () -> Assertions.assertEquals(0, tuesday.getMeals().get(0).countOrders()),
                () -> Assertions.assertEquals(1, monday.getMeals().get(1).countOrders()),
                () -> Assertions.assertEquals(2, monday.getMeals().get(0).countOrders())
        );
    }


    @Test
    void testOrder() {
        Day einTag = createEinTag();
        Assertions.assertEquals("Forelle", einTag.getMeals().get(0).getName());
        Assertions.assertEquals("Steak", einTag.getMeals().get(1).getName());
        Person giovanni = new Person("Borgia", "Giovanni");
        einTag.order(giovanni, 0);
        Assertions.assertTrue(einTag.getMeals().get(0).getCustomers().contains(giovanni));
        Person juan = new Person("Borgia", "Juan");
        Assertions.assertThrows(RuntimeException.class, () -> einTag.order(juan, 3));
    }

    private Day createEinTag() {
        Meal forelle = new Meal("Forelle", 5, "");
        Meal steak = new Meal("Steak", 3.5, "");
        Day einTag = new Day("Neujahr");
        einTag.addMeal(forelle);
        einTag.addMeal(steak);
        return einTag;
    }

    @Test
    void testPrice() {
        lunch0.setPrice(3.9);
        lunch1.setPrice(5);
        lunch2.setPrice(4);
        Assertions.assertAll(
                () -> Assertions.assertEquals(3.9, lunch0.getPrice()),
                () -> Assertions.assertEquals(5, lunch1.getPrice()),
                () -> Assertions.assertEquals(4, lunch2.getPrice()));
    }

    @Test
    public void testMenu() {
        String menuList = """
                Essensplan:
                Montag:
                Menü A: Fasan im Federkleid
                Menü B: Knödel in Schokoladen Soße
                Dienstag:
                Menü A: Fish and Chips
                Menü B: Bratkartoffeln
                Mittwoch:
                Menü A: Schwan im Federkleid
                Menü B: Risotto
                Donnerstag:
                Menü A: Hirschrücken mit chili garniert
                Menü B: Zuckerschloss
                Freitag:
                Menü A: Pastete vom Narwhal
                Menü B: Kaiserschmarrn""";
        System.out.println(menuList);
        Assertions.assertAll(

                () -> Assertions.assertEquals(menuList, workWeek.printMenu())
        );
    }

    //vermuten
    @Test
    public void testGiveString() {
        Assertions.assertAll(
                () -> Assertions.assertEquals("Person: Borgia, Lucretia", new Person("Borgia", "Lucretia").giveString()),
                () -> Assertions.assertEquals("Person: Sforza, Giovanni", new Person("Sforza", "Giovanni").giveString()),
                () -> Assertions.assertEquals("Person: Borgia, Cesare", new Person("Borgia", "Cesare").giveString()));
    }


}