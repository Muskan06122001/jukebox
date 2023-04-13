package DAO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import Exception.NotANumberException;
import Bean.Songs;
import Util.Dbutil;
import Exception.InvalidChoiceException;
import org.apache.commons.lang3.StringUtils;


public class PlayAllSongs {

    static Scanner sc = new Scanner(System.in);

    public static void playSongs() throws UnsupportedAudioFileException, SQLException, LineUnavailableException, IOException, NotANumberException {
        Connection con = Dbutil.getConnection();
        Statement st = con.createStatement();
        Dbutil.getConnection();
        List<Integer> li = new ArrayList<>();

        boolean flag = true;
        int Id = 0;
        do {
            try {

                Play.playsongs();
                for (Songs s1 : Play.list) {
                    li.add(s1.getSongId());
                }
                System.out.printf("%s\n",
                        StringUtils.center("***-----Available SongId-----***\n", 100));

                ResultSet res1 = st.executeQuery("select distinct SongId from mysongs");
                while (res1.next()) {
                    System.out.println(res1.getString(1));
                }
                System.out.println("Enter SongId");
                Id = sc.nextInt();
                ResultSet res = st.executeQuery("select * from mysongs where SongId='" + Id + "'");


                if (li.contains(Id)) {
                    while (res.next()) {
                        System.out.print("+---------------------------------------------------------------------------------------------------------------------------------+\n");
                        System.out.format("|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|",
                                StringUtils.center("SongId", 20),
                                StringUtils.center("SongName", 20),
                                StringUtils.center("Album", 25),
                                StringUtils.center("Genre", 20),
                                StringUtils.center("Artist", 20),
                                StringUtils.center("Duration", 20));

                        System.out.println();

                        System.out.format("|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|",
                                StringUtils.center(String.valueOf(res.getInt(1)), 20),
                                StringUtils.center(res.getString(2), 20),
                                StringUtils.center(res.getString(3), 25),
                                StringUtils.center(res.getString(4), 20),
                                StringUtils.center(res.getString(5), 20),
                                StringUtils.center(res.getString(6), 20));

                        System.out.println();
                        System.out.print("+---------------------------------------------------------------------------------------------------------------------------------+\n");
                        flag = false;
                        Play.playing(Id);
                    }
                    //System.out.println();
                } else {
                    throw new InvalidChoiceException("Invalid");
                }

            } catch (FileNotFoundException | InvalidChoiceException | InputMismatchException e) {
                System.out.println(e);
            }

        } while (flag);


    }
}








