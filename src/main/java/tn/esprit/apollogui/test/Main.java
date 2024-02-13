package tn.esprit.apollogui.test;

import tn.esprit.apollogui.utils.MyDatabase;

public class Main {
    public static void main(String[] args) {

        MyDatabase db= MyDatabase.getInstance();
        MyDatabase db2= MyDatabase.getInstance();


        System.out.println(db);
        System.out.println(db2);

    }
}
