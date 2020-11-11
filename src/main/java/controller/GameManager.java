package controller;


import  model.avators.villans.Villan;
import model.avators.heros.Hero;
import view.Map.*;
import view.console;
import view.Display;
import model.Database;

import static controller.AvatorCreator.newVillan;
import static model.helpers.Universal.*;

import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class GameManager {

    private static int[] oldMove = new int[2];

    public static void win() {
        if (hero.getX() == map.getMapSize() - 1 || hero.getY() == map.getMapSize() - 1 ||
                hero.getX() == 0 || hero.getY() == 0) {
            System.out.println("Quest Complete!!!");
            double xpSum = hero.getLevel() * 1000 + Math.pow(hero.getLevel() - 1, 2) * 450;

            int xp = (int) Math.round(xpSum);
            hero.setXp(xp);
            map = MapCreator.generateMap(hero);
            if (!bIsGUI)
                console.moveHero();
        }
    }

    public static void battleOrFlee() {
        if (!bIsGUI)
            Display.displaySurvival();
        Scanner input = new Scanner(System.in);
        if (!bIsGUI) {
            while (input.hasNextLine()) {
                String takeIn = input.nextLine();
                int v  = Integer.parseInt(takeIn);
                if (v == 1 || v == 2) {
                    if (v == 1) {
                        battle(2);
                        return;
                    } else {
                        flee();
                        return;
                    }
                } else {
                    System.out.println("Choose!!!");
                    Display.displaySurvival();
                }
            }
        } else
            bEncounterPhase = true;
    }

    public static void battle(int v) {
        if (v == 1 ) {
            System.out.print(villan.getName() + " attacks!!");
            while (hero.getLf() > 0 && villan.getLf() > 0) {
                villan.attack(hero);
                villan.attack(hero);
                if (hero.getLf() > 0)
                    hero.attack(villan);
            }
        } else {
            System.out.println(hero.getName() + " attacks!!");
            while (hero.getLf() > 0 && villan.getLf() > 0) {
                hero.attack(villan);
                hero.attack(villan);
                if (villan.getLf() > 0)
                    villan.attack(hero);
            }
        }

        if (hero.getLf() <= 0) {
            System.out.println("Quest Failed!!!");
            if (!bIsGUI)
                console.begin();
        }
        else if (villan.getLf() <= 0) {
            Database.getInstance().updateHero(hero);
            hero.setPosition(0, 0);
            System.out.print("Quest Successfully Completed!!!!");
        }
    }

    public static void flee() {
        int rand = new Random().nextInt(2);
        if (rand == 1) {
            System.out.print("Nowhere To Run!!!");
            battle(1);
        } else if (rand == 2) {
            System.out.print("Cowardice!!!");
            hero.setPosition(oldMove[0] * -1, oldMove[1] * -1);
        }
        bFightPhase = false;
    }

    public static void move(int way) {
        int north = 1;
        int east = 2;
        int south = 3;
        int west = 4;

        if (way == north) { //-10
            hero.setPosition(-1, 0);
            oldMove[0] = -1;
            oldMove[1] = 0;
        } else if (way == east) { //01
            hero.setPosition(0, 1);
            oldMove[0] = 0;
            oldMove[1] = 1;
        } else if (way == south) { //10
            hero.setPosition(1, 0);
            oldMove[0] = 1;
            oldMove[1] = 0;
        } else if (way == west) { //0-1
            hero.setPosition(0, -1);
            oldMove[0] = 0;
            oldMove[1] = -1;
        }

        // Demon encounter
        if (map.getMap()[hero.getX()][hero.getY()] == 8) {
            bFightPhase = true;
            int ran = new Random().nextInt(3);
            if (ran == 3)
                villan = (Villan) newVillan(hero, "Lex");
            else
                villan = (Villan) newVillan(hero, "Joker");
            System.out.println("Villan Approached:: " + villan.getName() + " of level " + villan.getLevel());
            battleOrFlee();
        }
    }
}