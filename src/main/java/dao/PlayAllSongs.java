package dao;
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

import bean.Song;
import util.Dbutil;
import exception.InvalidChoiceException;
import org.apache.commons.lang3.StringUtils;


public class PlayAllSongs {

    static Scanner sc = new Scanner(System.in);

    public static void playSongs() throws UnsupportedAudioFileException, SQLException, LineUnavailableException, IOException {
        Connection con = Dbutil.getConnection();
        Statement st = con.createStatement();
        Dbutil.getConnection();
        List<Integer> li = new ArrayList<>();
        List<Integer> li3 = new ArrayList<>();
        List<String> li2 = new ArrayList<>();
        boolean flag = true;
        int Id ;
        do {
            try {

                Play.playSong();
                for (Song s1 : Play.list) {
                    li.add(s1.getSongId());
                }


                ResultSet res1 = st.executeQuery("select *  from MySongs");
                while (res1.next()) {
                    li3.add(res1.getInt(1));
                }
                ResultSet res2=st.executeQuery("select * from MySongs");
                while(res2.next()){
                    li2.add(res2.getString(2));
                }

                System.out.println("\t\t***-----Available SongId-----***\t\t\n");
                System.out.println(li3+"\n");
                System.out.println("\t\t***-----Available SongName-----***\t\t\n");
                System.out.println(li2+"\n");


                System.out.println("Enter SongId");
                Id = sc.nextInt();
                ResultSet res = st.executeQuery("select * from MySongs where SongId='" + Id + "'");


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
                System.out.println(e+"\n");
            }

        } while (flag);

    }
}








