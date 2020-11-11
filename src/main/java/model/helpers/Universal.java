package model.helpers;

import javax.swing.*;

import model.avators.heros.Hero;
import model.avators.villans.*;
import view.*;

public class Universal {
    public static int nbHero = 0;

    public static Boolean bFightPhase = false;
    public static Boolean bEncounterPhase = false;
    public static Boolean bIsGUI = false;
    public static Boolean bIsHero = false;

    public static Hero hero;
    public static Villan villan;
    public static Map map;
    public static JTextArea logTextArea;
}
