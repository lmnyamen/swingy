package controller;

import view.console;
import model.Database;
import java.sql.*;
//import model.avators.heros.*;
import view.GUI;
import static model.helpers.Universal.bIsGUI;

public class Main {
    public Connection connection;
    public static void main(String[] args){
        try{
            Database.dbCreate();
            if (args[0].equals("console")){
                console.begin();
            }else if (args[0].equals("gui")) {
                //start gui
                bIsGUI = true;
                GUI gui = new GUI();
            }

        }
        catch (ArrayIndexOutOfBoundsException e){
           System.out.println("please add console or gui");
        }
    }
}
