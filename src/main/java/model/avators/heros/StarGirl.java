package model.avators.heros;

public abstract class StarGirl extends Hero {
    public StarGirl(){
        super();
    }
    public StarGirl(String name){
        super(name);
        this.type = "StarGirl";
        this.attack = 15;
        this.defense = 10;
        this.lf = 150;
    }
}
