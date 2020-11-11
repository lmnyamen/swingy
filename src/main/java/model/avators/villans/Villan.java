package model.avators.villans;

import model.avators.avator;
import java.util.Random;

public  abstract class Villan extends avator {
    Villan(int level){
        this.level = level;
    }


    public  void attack(avator avator) {
        System.out.println(this.getName() + " is attacking");
        int highLevel = 0;
        if (this.getType().equals("Lex")){
            int rand = new Random().nextInt(4);
            if (rand == 3){
                System.out.println("High Level Hit!!!");
                highLevel = this.level * 2;
            }
        }

        avator.defend(this, this.attack);
        if (avator.getLf() <= 0) {
            if (avator.getType().equals("WonderWoman"))
                System.out.println("I am the hell and the high water");
            else if (avator.getType().equals("StarGirl"))
                System.out.println("Kneel, peasant");
            else if (avator.getType().equals("BlackWidow"))
                System.out.println("If it's loving that you want");
        }
    }

    public void defend(avator avator, int damage) {
        int finalDamage = damage - this.defense;

        if (finalDamage <= 0)
            finalDamage = 1;
        this.lf -= finalDamage;
        System.out.println(avator.getName() + " inflicted " + finalDamage + " harm to " + this.name);
        if (lf <= 0)
            System.out.println(this.name + " has died!!!");
    }


}
