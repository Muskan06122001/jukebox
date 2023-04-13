package Util;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbutil {
    public static Connection getConnection(){
        Connection connection=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            //System.out.println("Driver Loaded");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/LatestProject","root","Muskan@123");
           // System.out.println("Connection established");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return connection;
        }

    }



