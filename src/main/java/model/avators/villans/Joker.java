package model.avators.villans;

//import model.avators.villans.Villan;

public  class Joker extends Villan {

    public Joker(int level) {
        super(level);
        this.name = "Joker";
        this.type = "Human";
        this.attack = 30 * this.level;
        this.defense = 2 * this.level;
        this.lf = 10 * this.level;
    }


}
