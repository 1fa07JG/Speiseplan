package com.example.speiseplan.playground;

import com.example.speiseplan.logic.Week;

import java.io.*;

public class DemoPerson implements Serializable {
    final static long serialVersionUID = 1;

    String vorname;
    String nachname;
    int alter;
    String geschlect;

    public static void main(String[] args) throws InvalidClassException {
        //DemoPerson wannheda=new DemoPerson("Clarke","Grifin",18);
        //serializeObject(wannheda,"ark.dat" );
        deSerializeObject("ark.dat");


    }

    DemoPerson(String v, String n, int a, String geschlect) {
        vorname = v;
        nachname = n;
        alter = a;
        geschlect = geschlect;
    }

    private static void serializeObject(DemoPerson weekSerial, String path) {

        FileOutputStream fos;
        ObjectOutputStream out;

        try {
            fos = new FileOutputStream(path);
            out = new ObjectOutputStream(fos);
            out.writeObject(weekSerial);
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    private static DemoPerson deSerializeObject(String path) throws InvalidClassException {

        DemoPerson weekDeSerial = null;
        FileInputStream fis;
        ObjectInputStream in;
        try {
            fis = new FileInputStream(path);
            in = new ObjectInputStream(fis);
            weekDeSerial = (DemoPerson) in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        assert weekDeSerial != null;
        System.out.println(weekDeSerial.toString());
        weekDeSerial.print();
        return weekDeSerial;


    }


    void print() {
        System.out.println(vorname + nachname + alter);
    }

}
