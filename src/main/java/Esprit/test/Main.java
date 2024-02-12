package Esprit.test;

import Esprit.utils.Database;

public class Main {
    public static void main(String[] args){
        Database db= Database.getInstance();
        Database db1= Database.getInstance();
        System.out.println(db);
        System.out.println(db1);
    }
}
