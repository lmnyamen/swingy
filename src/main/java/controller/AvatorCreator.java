package controller;

import model.avators.*;
import model.avators.heros.BlackWidow;
import model.avators.heros.StarGirl;
import model.avators.heros.WonderWoman;
import model.avators.villans.Joker;
import model.avators.villans.Lex;

public class AvatorCreator {
    private static  avator avator;

    public static avator newHero(String name, String type) {
        if (type.equals("WonderWoman"))
            avator = new WonderWoman(name) {
            };
        else if (type.equals("StarGirl"))
            avator = new StarGirl(name) {
            };
        else if (type.equals("BlackWidow"))
            avator = new BlackWidow(name) {
            };
        return (avator);
    }

    public static avator newVillan(avator hero, String type) {
        if (type.equals("Lex"))
            avator = new Lex(hero.getLevel());
        else if (type.equals("Joker"))
            avator = new Joker(hero.getLevel());
        return(avator);
    }
}
