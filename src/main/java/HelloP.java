
import java.sql.*;
import javax.sql.*;

public class HelloP {
    public static void main(String[] args){
        //System.out.println("Hello lona" + " " + args[0]);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3307","root","123456"
            );
            System.out.println("you fucked");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS Swingy");
        }
        catch (Exception e){System.out.println(e);}
   }
}


