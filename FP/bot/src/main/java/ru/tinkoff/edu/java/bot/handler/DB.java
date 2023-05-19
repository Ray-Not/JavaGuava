package ru.tinkoff.edu.java.bot.handler;

import java.util.ArrayList;

public class DB {

    static ArrayList<String> list = new ArrayList();

    public static void addLink(String link) {
        list.add(link);
    }

    public static void rmLink(String link) {
        list.remove(link);
    }

    public static String getListParse() {

        int i = 1;

        String out_list = "";

        for(String element: list){

            out_list += element;

            if(i != list.size()) {
                out_list += ", ";
            }
            i++;
        }
        return out_list;
    }

    public static boolean listIsEmpty() {
        return list.isEmpty();
    }

    public static boolean linkContain(String link) {
        return list.contains(link);
    }
}
