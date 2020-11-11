package model.avators.heros;

public abstract class BlackWidow extends Hero {
    public BlackWidow(){
        super();
    }
    public BlackWidow(String name){
        super(name);
        this.type = "BlackWidow";
        this.attack = 15;
        this.defense = 10;
        this.lf = 100;
    }
}
