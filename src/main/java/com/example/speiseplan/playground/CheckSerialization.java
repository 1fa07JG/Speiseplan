package com.example.speiseplan.playground;

import com.example.speiseplan.logic.Day;
import com.example.speiseplan.logic.Meal;
import com.example.speiseplan.logic.Week;

import java.io.*;

public class CheckSerialization implements Serializable {


    @Serial
    private static final long serialVersionUID = 1;


    // ein Wochenobjekt erzeugen

    // das Objekt schreiben

    // das Objekt lesen


    public static void main(String[] args) {
        Meal lunch0 = new Meal("Fasan im Federkleid", 6, "./src/main/resources/com/example/speiseplan/image/Fasan_im_Federkleid.jpg");
        Meal lunch1 = new Meal("Knödel in Schokoladen Soße", 8, "./src/main/resources/com/example/speiseplan/image/Marillenknoedel.jpg");
        Meal lunch2 = new Meal("Fish and Chips", 3.9, "./src/main/resources/com/example/speiseplan/image/Fish_and_Chips.jpg");
        Meal lunch3 = new Meal("Bratkartoffeln", 5, "./src/main/resources/com/example/speiseplan/image/Bratkartoffeln.jpg");
        Meal lunch4 = new Meal("Schwan im Federkleid", 3.9, "./src/main/resources/com/example/speiseplan/image/Schwan_im_Federkleid.jpg");
        Meal lunch5 = new Meal("Risotto", 2.5, "./src/main/resources/com/example/speiseplan/image/Risotto.jpg");
        Meal lunch6 = new Meal("Hirschrücken mit chili garniert", 7, "./src/main/resources/com/example/speiseplan/image/Rehruecken.jpg");
        Meal lunch7 = new Meal("Zuckerschloss", 3.9, "./src/main/resources/com/example/speiseplan/image/Marzipan_Fruechte.jpg");
        Meal lunch8 = new Meal("Pastete vom Narwhal", 3.9, "./src/main/resources/com/example/speiseplan/image/Filet_vom_Narwal.jpg");
        Meal lunch9 = new Meal("Kaiserschmarrn", 4.5, "./src/main/resources/com/example/speiseplan/image/Kaiserschmarrn.jpg");

        Day monday = new Day("Montag", lunch0, lunch1);
        Day tuesday = new Day("Dienstag", lunch2, lunch3);
        Day wednesday = new Day("Mittwoch", lunch4, lunch5);
        Day thursday = new Day("Donnerstag", lunch6, lunch7);
        Day friday = new Day("Freitag", lunch8, lunch9);
        Day[] days = new Day[]{monday, tuesday, wednesday, thursday, friday};

        Week workWeek = new Week(days, 35);

        String path = "sometime.dat";

        System.out.println("Original object = " + workWeek.printMenu());
        serializeObject(workWeek, path);

        System.out.println();

        Week deserializedObject = deSerializeObject(path);
        System.out.println("Deserialized object = " + deserializedObject.printMenu());

    }

    private static void serializeObject(Week week, String path) {

        FileOutputStream fos;
        ObjectOutputStream out;

        try {
            fos = new FileOutputStream(path);
            out = new ObjectOutputStream(fos);
            out.writeObject(week);
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    private static Week deSerializeObject(String path) {

        Week week = null;
        FileInputStream fis;
        ObjectInputStream in;
        try {
            fis = new FileInputStream(path);
            in = new ObjectInputStream(fis);
            week = (Week) in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        assert week != null;
        week.printMenu();
        return week;

    }


}


