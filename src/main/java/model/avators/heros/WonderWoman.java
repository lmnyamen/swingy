package model.avators.heros;

public abstract class WonderWoman extends Hero {
    public WonderWoman(){
        super();
    }
    public WonderWoman(String name){
        super(name);
        this.type = "WonderWoman";
        this.attack = 15;
        this.defense = 10;
        this.lf = 200;
    }
}
