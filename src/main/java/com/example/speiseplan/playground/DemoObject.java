package com.example.speiseplan.playground;

import java.io.*;

public class DemoObject implements Serializable {

    @Serial
    private static final long serialVersionUID = 0;

    String name;
    int valueA;
    int valueB;


    public DemoObject(String name, int valueA, int valueB) {
        this.name = name;
        this.valueA = valueA;
        this.valueB = valueB;
    }

    public static void main(String[] args) {

        String path = "somefile.dat";
        DemoObject originalObject = new DemoObject("Paulchen Panther", 1, 42);

        System.out.println("Original object = " + originalObject);
        serializeObject(originalObject, path);

        System.out.println();

        DemoObject deserializedObject = deSerializeObject(path);
        System.out.println("Deserialized object = " + deserializedObject);

    }

    private static void serializeObject(DemoObject object, String path) {

        FileOutputStream fos = null;
        ObjectOutputStream out = null;

        try {
            fos = new FileOutputStream(path);
            out = new ObjectOutputStream(fos);
            out.writeObject(object);
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    private static DemoObject deSerializeObject(String path) {

        DemoObject object = null;
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(path);
            in = new ObjectInputStream(fis);
            object = (DemoObject) in.readObject();
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return object;

    }

    @Override
    public String toString() {
        return "DemoObject{" +
                "name='" + name + '\'' +
                ", valueA=" + valueA +
                ", valueB=" + valueB +
                '}';
    }
}
