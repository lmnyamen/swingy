package view;

import controller.AvatorCreator;
import view.Display;
import java.util.*;
import controller.GameManager;
import controller.MapCreator;
import model.Database;
import model.avators.heros.Hero;

import static model.helpers.Universal.*;
import static view.Display.*;


import java.util.Scanner;

public class console {
    public static void begin(){
        System.out.println("*****Console View*****");
        Display.displayMenu();
        Scanner input = new Scanner(System.in);

        while(input.hasNextLine()) {
            try {
                //System.out.println(input);
                String arg = input.nextLine();
                Integer i = Integer.parseInt(arg);
                if (i.equals(1) || i.equals(2) || i.equals(3) || i.equals(4)){
                    if (i == 1){
                        createHero();
                        break;
                    }
                    else if (i == 2){
                        selectHero();
                        break;
                    }
                    else if ( i == 3){
                        bIsGUI = true;
                        GUI gui = new GUI();
                        return;
                    }
                    else if (i == 4){
                        System.out.println("Goodbye!");
                        break;
                    }
                }else{
                    System.out.println("Invalid input please Follow instructions");
                    Display.displayMenu();
                }

                //break;
                input.close();
            }
            catch (Exception e){
                System.out.println("ConsoleBegin:: Invalid Input");
            }
        }
    }

    private static void createHero() {
        Display.displayHero();
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            try {
                String takeIn = input.nextLine();
                Integer v = Integer.parseInt(takeIn);
                if (v.equals(1) || v.equals(2) || v.equals(3)) {
                    if (v == 1)
                        nameHero("WonderWoman");
                    else if (v == 2)
                        nameHero("StarGirl");
                    else if(v == 3)
                        nameHero("BlackWidow");
                    Display.displayMenu();
                } else {
                    System.out.println("Let's try that again");
                    Display.displayHero();
                }
            } catch (Exception e) {
                System.out.print("ConsoleCreateHero:: Invalid Input");
            }
        }
        Display.displayMenu();
    }

    public static void nameHero(String type) {
        System.out.println("Name Your HERO::");
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            String takenIn = input.nextLine();
            if (AvatorCreator.newHero(takenIn, type) !=  null) {
                //System.out.println("StarGirl");
                Database.getInstance().insertHero((Hero) AvatorCreator.newHero(takenIn, type));
                break ;
            }
            else
                System.out.println("Name your Hero::");
        }
    }

    private static void selectHero() {
        System.out.println("Select Your Hero::");
        Database.getInstance().printDatabase();
        if (!bIsHero) {
            System.out.println("No heros exist in this plain!!!");
            Display.displayMenu();
            return ;
        } else {
            System.out.println("Select Your hero By Name And get bloody.::");
        }
        bIsHero = false;
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            String takeIn = input.nextLine();
            List<Hero> heros = Database.getInstance().extractDatabase();
            int identical = 0;
            for (Hero a : heros) {
                if (a.getName().equals(takeIn)) {
                    hero = Database.getInstance().HeroDetails(a.getName());
                    map = MapCreator.generateMap(hero);
                    moveHero();
                    identical = 1;
                }
            }
            if (identical == 0)
                System.out.println("::Choose What Is Given Or Create Your Own!!!");
        }
    }


    public static void moveHero() {
        Display.displayDirections();
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            try {
                String takeIn = input.nextLine();
                Integer v = Integer.parseInt(takeIn);
                if (v.equals(1) || v.equals(2) || v.equals(3) || v.equals(4)) {
                    GameManager.move(v);
                    GameManager.win();
                } else if (takeIn.equals("5")) {
                    System.out.println("::Level -- " + hero.getLevel());
                    System.out.println("::XP -- " + hero.getXp());
                    System.out.println("::HP -- " + hero.getLf());
                    System.out.println("::Attack -- " + hero.getAttack());
                    System.out.println("::Defense -- " + hero.getDefense());
                } else {
                    System.out.println("Follow Instructions!!!");
                }
                Display.displayDirections();
            } catch (Exception e){
                System.out.println("MoveAngel:: Invalid Input");
            }
        }
        input.close();
    }


}
