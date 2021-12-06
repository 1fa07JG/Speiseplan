package com.example.speiseplan.logic;

public class Helper {

    public static String getReplace(String para) {
        String replace = para.replace(' ', '_');
        replace = replace.replace("ü", "ue");
        replace = replace.replace("ä", "ae");
        replace = replace.replace("ö", "oe");
        replace = replace.replace("ß", "ss");
        replace = replace.replace("Ü", "Ue");
        replace = replace.replace("Ä", "Ae");
        replace = replace.replace("Ö", "Oe");
        return replace;
    }
}
