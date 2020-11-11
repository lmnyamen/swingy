package model.avators.villans;

public class Lex extends Villan {
    public Lex(int level){
        super(level);
        this.name = "Lex";
        this.type = "MetaHuman";
        this.attack = 30 * this.level;
        this.defense = 2 * this.level;
        this.lf = 10 * this.level;
    }
}
