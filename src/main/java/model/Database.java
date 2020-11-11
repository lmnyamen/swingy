package model;

import java.sql.*;
import model.avators.heros.*;
import static model.helpers.Universal.nbHero;
import static model.helpers.Universal.bIsHero;
import controller.AvatorCreator;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Database {


    //database variables
    private static Database database;
    private Statement statement;
    private Connection connection;

    private PreparedStatement prepared;
    private ResultSet resultSet;

    private static final String heroTable = "heros";
    private static final String dbId = "id";
    private static final String dbName = "name";
    private static final String dbType = "type";
    private static final String dbLevel = "level";
    private static final String dbXP = "xp";
    private static final String dbAttack = "attack";
    private static final String dbDefense = "defense";
    private static final String dbLF = "lf";

    private static final String createHeroTable =
            "CREATE TABLE IF NOT EXISTS " + heroTable +
                    "(" + dbId + " INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    dbName + " TEXT, " + "type" + " TEXT, " +
                    dbLevel + " INTEGER, " + dbXP + " INTEGER, " +
                    dbAttack + " INTEGER, " + dbDefense + " INTEGER," +
                    dbLF + " INTEGER )";

    private static final String insertAngelTable =
            "INSERT INTO " + heroTable + "(" +
                    dbName + "," + dbType + "," +
                    dbLevel + "," + dbXP + "," +
                    dbAttack + "," + dbDefense + "," +
                    dbLF + ") VALUES(?,?,?,?,?,?,?)";

    private static final String updateheroTable = "UPDATE " + heroTable + " SET " +
            dbLevel + " = ? , " + dbXP + " = ? , " + dbAttack + " = ? , " + dbDefense + " = ? ," +
            dbLF + " = ? " + " WHERE " + dbName + " = ?";

    private static final String getHeroTable =
            "SELECT * FROM " + heroTable;

    private static final String getHeroData =
            "SELECT * FROM " + heroTable +
                    " WHERE " + dbName + " = ?";

    private static final String deleteHeroTable =
            "DELETE from " + heroTable + " WHERE " + dbName + " = ?";


    //functions


    //get instance of the database (synchronization look-up)

    public static Database getInstance() {
        if (database == null) {
            database = new Database();
        }
        return (database);
    }
    //create a database

    public static void dbCreate() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3307", "root", "123456"
            );
            //System.out.println("you fucked");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS Swingy");
        } catch (Exception e) {
            System.out.println("dbCreate:: " + e.getClass() + ":: " + e.getMessage());
            //System.exit(0);
        }

    }

    // connection and create table on database

    private Connection dbConnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3307/swingy", "root", "123456"
            );
            //System.out.println("you fucked");
            statement = connection.createStatement();
            statement.executeUpdate(createHeroTable);
        } catch (Exception e) {
            System.out.println("dbConnect:: " + e.getClass() + ":: " + e.getMessage());
            //System.exit(0);
        }

        return (connection);
    }


    //insert into database
    public void insertHero(Hero hero) {
        try {
            connection = this.dbConnect();
            //check for duplicates before insertion
            if (duplicateHero(connection, hero))
                System.out.println(" please no duplicates here!");
            else {
                prepared = connection.prepareStatement(insertAngelTable);
                prepared.setString(1, hero.getName());
                prepared.setString(2, hero.getType());
                prepared.setInt(3, hero.getLevel());
                prepared.setInt(4, hero.getXp());
                prepared.setInt(5, hero.getAttack());
                prepared.setInt(6, hero.getDefense());
                prepared.setInt(7, hero.getLf());
                prepared.executeUpdate();
                System.out.println("Database: ** " + hero.getName() + " ** Created");
            }
        } catch (Exception e) {
            System.out.println("InsertHero:: " + e.getClass() + ":: " + e.getMessage());
        }
    }


    //duplicate Heros
    private boolean duplicateHero(Connection connection, Hero hero) {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(getHeroTable);
            while (resultSet.next()) {
                if (hero.getName().equals(resultSet.getString(dbName)))
                    return true;
            }
        } catch (Exception e) {
            System.out.println("DuplicateHero:: " + e.getClass() + ":: " + e.getMessage());
        }
        return false;
    }


    //update hero
    public void updateHero(Hero hero) {
        try {
            connection = this.dbConnect();
            prepared = connection.prepareStatement(updateheroTable);
            prepared.setInt(1, hero.getLevel());
            prepared.setInt(2, hero.getXp());
            prepared.setInt(3, hero.getAttack());
            prepared.setInt(4, hero.getDefense());
            prepared.setInt(5, hero.getLf());
            prepared.setString(6, hero.getName());
            prepared.executeUpdate();
        } catch (Exception e) {
            System.out.println("UpdateHero:: SQL Exception : " + e.getClass() + ":: " + e.getMessage());
            System.exit(0);
        }
    }

    //delete hero
    public void deleteHero(String input) {
        try {
            connection = this.dbConnect();
            prepared = connection.prepareStatement(deleteHeroTable);
            prepared.setString(1, input);
            prepared.executeUpdate();
        } catch (Exception e) {
            System.out.println("Delete:: SQL exception : " + e.getClass() + "::" + e.getMessage());
            System.exit(0);
        }
    }


    //print database
    public void printDatabase() {
        try {
            StringBuilder string = new StringBuilder();
            connection = this.dbConnect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(getHeroTable);
            while (resultSet.next()) {
                 bIsHero = true;
                string.append("Name: ").append(resultSet.getString(dbName)).append("\n")
                        .append("Type: ").append(resultSet.getString(dbType)).append("\n")
                        .append("Level: ").append(resultSet.getString(dbLevel)).append("\n")
                        .append("XP: ").append(resultSet.getString(dbXP)).append("\n")
                        .append("Attack: ").append(resultSet.getString(dbAttack)).append("\n")
                        .append("Defense: ").append(resultSet.getString(dbDefense)).append("\n")
                        .append("LF: ").append(resultSet.getString(dbLF)).append("\n\n");
                  nbHero += 1;
            }
            System.out.println(string.toString());
        } catch (Exception e) {
            System.out.println("PrintDB: " + e.getClass() + "::" + e.getMessage());
            System.exit(0);
        }
    }


    //extract hero details
    public Hero HeroDetails(String name) {
        Hero hero = null;
        try {
            connection = this.dbConnect();
            prepared = connection.prepareStatement(getHeroData);
            prepared.setString(1, name);
            resultSet = prepared.executeQuery();

            if(resultSet.next()) {
                if (resultSet.getString(dbType).equals("WonderWoman"))
                    hero = (Hero) AvatorCreator.newHero(name, "WonderWoman");
                else if (resultSet.getString(dbType).equals("StarGirl"))
                    hero = (Hero) AvatorCreator.newHero(name, "StarGirl");
                else if (resultSet.getString(dbType).equals("BlackWidow"))
                    hero = (Hero) AvatorCreator.newHero(name, "BlackWidow");

                assert hero != null;
                hero.setLevel(resultSet.getInt(dbLevel));
                hero.setXp(resultSet.getInt(dbXP));
                hero.setAttack(resultSet.getInt(dbAttack));
                hero.setDefense(resultSet.getInt(dbDefense));
                hero.setLf(resultSet.getInt(dbLF));
            }
        }catch (Exception e) {
            System.out.print("HeroDetails: " + e.getClass() + ":: " + e.getMessage());
            System.exit(0);
        }

        return hero;
    }



    //extract database
    public List<Hero> extractDatabase() {
        try {
            List<Hero> heroesList = new ArrayList();

            connection = this.dbConnect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(getHeroTable);

            while (resultSet.next()) {
                bIsHero = true;
                Hero hero = null;
                if (resultSet.getString(dbType).equals("WonderWoman"))
                    hero = new WonderWoman() {
                    };
                else if (resultSet.getString(dbType).equals("StarGirl"))
                    hero = new StarGirl() {
                    };
                else if (resultSet.getString(dbType).equals("BlackWidow"))
                    hero = new BlackWidow() {
                    };

                assert hero != null;
                hero.setName(resultSet.getString(dbName));
                hero.setType(resultSet.getString(dbType));
                hero.setLevel(resultSet.getInt(dbLevel));
                hero.setXp(resultSet.getInt(dbXP));
                hero.setAttack(resultSet.getInt(dbAttack));
                hero.setDefense(resultSet.getInt(dbDefense));
                hero.setLf(resultSet.getInt(dbLF));
                heroesList.add(hero);
            }
            return heroesList;
        } catch (Exception e) {
            System.out.println("ExtractDatabase: " + e.getClass() + ":: " + e.getMessage());
            System.exit(0);
        }
        return null;
    }


}