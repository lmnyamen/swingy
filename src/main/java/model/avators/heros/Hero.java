package model.avators.heros;

import lombok.Getter;
import lombok.Setter;
//import model.helpers.View;
import model.avators.avator;
import view.Map;

import java.util.Random;

@Getter
@Setter
public abstract class Hero extends avator {

    private int xp;
    private Map seer;

    Hero(){

    }

    Hero(String name) {
        this.name = name;
        this.level = 1;
        this.xp = 0;
    }

    public void register(Map map) {
        seer = map;
    }

    private void updateMap() {
        seer.updateHeroPosition();
    }

    public void setPosition(int x, int y) {
        this.x += x;
        this.y += y;
        updateMap();
    }


    public void riseUp() {
        this.level += 1;
        int current = this.level;
        this.attack += current;
        this.defense += current;
        this.lf += current;

        System.out.println(this.name + " has risen. Current circle:: " + this.level);
        System.out.println(" ::Attack: " + this.attack);
        System.out.println(" ::Defense: " + this.defense);
        System.out.println(" ::Lf: " + this.lf);
    }

    public void attack(avator avator) {
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
            int xpEarned = 0;

            String str = this.getType();
            if (str.equals("WonderWoman"))
                System.out.println(this.name + ":: Such a rush!!");
            else if (str.equals("StarGirl"))
                System.out.println(this.name + ":: Take that!!");
            else if (str.equals("BlackWidow"))
                System.out.println(this.name + ":: Oh dear me!!");

            //just to see how it works
            if (str.equals("WonderWoman")) {
                xpEarned = 750;
                this.xp += xpEarned;
            } else if (str.equals("StarGirl")) {
                xpEarned = 500;
                this.xp += xpEarned;
            } else if (str.equals("BlackWidow")) {
                xpEarned = 250;
                this.xp += xpEarned;
            }

            if (str.equals("Lex")) {
                xpEarned = 750;
                this.xp += xpEarned;
            } else if (str.equals("Joker")) {
                xpEarned = 500;
                this.xp += xpEarned;
            }

            System.out.println("You earned " + xpEarned + "xp");
            if (this.xp >= (this.level * 1000 + Math.pow(this.level - 1, 2) * 450))
                riseUp();
        }
    }

    public void defend (avator avator, int damage) {
        int finalDamage = damage - this.defense;

        if (finalDamage <= 0)
            finalDamage = 1;
        this.lf -= finalDamage;
        System.out.println(avator.getName() + " inflicted " + finalDamage + "harm to " + this.name);
        if (this.lf <= 0)
            System.out.println(this.name + " has been smoked!!!");
    }

}
