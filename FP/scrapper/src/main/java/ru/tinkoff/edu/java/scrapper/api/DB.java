package ru.tinkoff.edu.java.scrapper.api;

import java.util.ArrayList;

public class DB {

    static ArrayList<Long> tgChatIDList = new ArrayList();

    public static void addId(Long id) {
        tgChatIDList.add(id);
    }

    public static void rmId(Long id) {
        tgChatIDList.remove(id);
    }

    public static String getListParse() {

        int i = 1;

        String out_list = "";

        for(Long element: tgChatIDList){

            out_list += element;

            if(i != tgChatIDList.size()) {
                out_list += ", ";
            }
            i++;
        }
        return out_list;
    }

    public static boolean listIsEmpty() {
        return tgChatIDList.isEmpty();
    }

    public static boolean linkContain(Long id) {
        return tgChatIDList.contains(id);
    }
}
