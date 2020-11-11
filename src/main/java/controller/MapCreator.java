package controller;

import model.avators.heros.Hero;
import model.helpers.*;
import model.avators.avator;
import view.Map;

public class MapCreator {
    public static Map generateMap(avator hero) {
        int mapSize = (hero.getLevel() -1) * 5 + 10 - (hero.getLevel() % 2);

        if (mapSize > 19)
            mapSize = 19;
        Map map = new Map(mapSize);
        map.registerHero(hero);
        System.out.println(hero.getName() + " is here ");
        map.spawnVillans();
        return (map);
    }
}
