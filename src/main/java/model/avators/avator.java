package model.avators;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public abstract class avator {
    @NotNull
    @Size(min = 4, max = 15)
    protected String name;
    protected String type;

    protected int attack;
    protected int defense;

    protected int lf;
    protected int xp; //trial -- not meant to be here
    protected int level;

    protected int x;
    protected int y;

    public abstract void attack(avator avator);
    public abstract void defend(avator avator, int damage);
}
