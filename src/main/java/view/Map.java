package view;


import java.util.Random;
import lombok.Getter;
import model.avators.heros.Hero;
import model.avators.avator;
import static model.helpers.Universal.*;

@Getter
public class Map {
    private int [][]map;
    public int mapSize;
    private int[]oldPos = new int[]{-1, -1};
    private Hero hero;

    public Map(int mapSize){
        this.mapSize = mapSize;
        this.map = new int [mapSize][mapSize];
    }

    public void updateHeroPosition() {
        this.map[oldPos[0]][oldPos[1]] = 0;
        oldPos[0] = hero.getX();
        oldPos[1] = hero.getY();

        if (this.map[hero.getX()][hero.getY()] == 2)
            this.map[hero.getX()][hero.getY()] = 8;
        else
            this.map[hero.getX()][hero.getY()] = 1;
        if (!bIsGUI)
            displayMap();
    }

    private void displayMap() {
        for (int[] line: map) {
            for (int col : line) {
                String box = col + " ";
                System.out.print(box);
            }
            System.out.println();
        }
    }

    public void registerHero(avator avator) {
        this.hero = (Hero) avator;
        this.hero.register(this);
        this.hero.setX(mapSize / 2);
        this.hero.setY(mapSize / 2);
        oldPos[0] = this.hero.getX();
        oldPos[1] = this.hero.getY();
        this.map[mapSize / 2][mapSize / 2] = 1;
    }

    public void spawnVillans() {
        for (int v = 0; v < mapSize; v++) {
            for (int t = 0; t < mapSize; t++) {
                if (map[v][t] != 1) {
                    int ran = new Random().nextInt(3);
                    if (ran == 1)
                        map[v][t] = 2;
                }
            }
        }
        displayMap();
    }
}